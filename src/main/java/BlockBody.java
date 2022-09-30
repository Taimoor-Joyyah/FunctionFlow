import java.util.ArrayList;
import java.util.List;

public class BlockBody implements Body {
    private final List<Body> bodies;

    public BlockBody() {
        bodies = new ArrayList<>();
    }

    @Override
    public void parse(String code) {
        int index = 0;
        int statementIndex;
        int conditionalIndex;
        while (index + 1 < code.length()) {
            statementIndex = code.indexOf(';', index);
            conditionalIndex = code.indexOf('{', index);
            if (conditionalIndex >= 0 && conditionalIndex < statementIndex) {
                var close = FlowParser.otherEnd(conditionalIndex, code);
                if (FlowParser.nextWord(close + 1, code).equals("else")) {
                    var open = code.indexOf('{', close);
                    close = FlowParser.otherEnd(open, code);
                }
                var conditionalString = code.substring(index, close + 1);
                var conditionalBody = new ConditionBody();
                conditionalBody.parse(conditionalString);
                bodies.add(conditionalBody);

                index = close + 1;
            } else if (statementIndex >= 0) {
                int close = index;
                do {
                    if (FlowParser.nextWord(close, code).equals("return")) {
                        statementBlocking(code, index, close);
                        returnBlocking(code, close);
                        return;
                    }
                    close = statementIndex;
                    statementIndex = code.indexOf(';', close + 1);
                } while (statementIndex >= 0 && (conditionalIndex == -1 || statementIndex < conditionalIndex));

                statementBlocking(code, index, close);

                index = close + 1;
            }
            else if (code.substring(index).isBlank()) break;
        }
    }

    private void statementBlocking(String code, int index, int close) {
        var statementBlockString = code.substring(index, close + 1);
        if (!statementBlockString.isBlank()) {
            var statementBlockBody = new StatementBlockBody();
            statementBlockBody.parse(statementBlockString);
            bodies.add(statementBlockBody);
        }
    }

    private void returnBlocking(String code, int index) {
        int open = code.indexOf("return", index);
        int close = code.indexOf(";", index);
        var returnString = code.substring(open, close + 1);
        var returnBody = new ReturnBody();
        returnBody.parse(returnString);
        bodies.add(returnBody);
    }
}
