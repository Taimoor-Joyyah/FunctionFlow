import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) throws IOException {
        String functionCode;
        functionCode = Files.readString(Paths.get("example02"));
        System.out.println(functionCode);
        var parser = new FlowParser(functionCode);
        parser.parse();
    }


//    public void parse(String code) {
//        int index = 0;
//        int statementIndex;
//        int conditionalIndex;
//        while (index + 1 < code.length()) {
//            statementIndex = code.indexOf(';', index);
//            conditionalIndex = code.indexOf('{', index);
//            if (conditionalIndex >= 0 && conditionalIndex < statementIndex) {
//                var close = FlowParser.otherEnd(conditionalIndex, code);
//                if (FlowParser.nextWord(close + 1, code).equals("else")) {
//                    var open = code.indexOf('{', close);
//                    close = FlowParser.otherEnd(open, code);
//                }
//                var conditionalString = code.substring(index, close + 1);
//                var conditionalBody = new ConditionBody();
//                conditionalBody.parse(conditionalString);
//                bodies.add(conditionalBody);
//                index = close + 1;
//            } else {
//                if (statementIndex >= 0) {
//                    int close = index;
//                    statementBlocking(code, index, close);
//                    index = close + 1;
//                } else {
//                    if (code.substring(index).isBlank()) {
//                        break;
//                    }
//                }
//            }
//        }
//    }

}
