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
            } else if (statementIndex >= 0 && (statementIndex < conditionalIndex || conditionalIndex == -1)) {
                if (FlowParser.nextWord(index, code).equals("return")) {
                    int open = code.indexOf("return", index);
                    int close = code.indexOf(";", index);
                    var returnString = code.substring(open, close + 1);
                    var returnBody = new ReturnBody();
                    returnBody.parse(returnString);
                    bodies.add(returnBody);

                    index = close + 1;
                    continue;
                }
                int close;
                do {
                    close = statementIndex;
                    statementIndex = code.indexOf(';', close + 1);
                } while (statementIndex >= 0 && (statementIndex < conditionalIndex || conditionalIndex == -1));

                var statementBlockString = code.substring(index, close + 1);
                var statementBlockBody = new StatementBlockBody();
                statementBlockBody.parse(statementBlockString);
                bodies.add(statementBlockBody);

                index = close + 1;
            }
            else if (code.substring(index).isBlank()) break;
        }
    }
}
