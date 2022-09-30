public class Test {
    public static void main(String[] args) {
//        String functionCode = """
//                public int factorial(int number) {
//                    if (number < 0) {
//                        System.out.println("Number must be < 0");
//                        return -1;
//                    }
//                    int fact = 1;
//                    while (number > 1) {
//                        fact *= number;
//                        number--;
//                    }
//                    return fact;
//                }
//                """;
        String functionCode = """
    public void parse(String code) {
        int index = 0;
        int statementIndex;
        int conditionalIndex;
        while (index + 1 < code.length()) {
            statementIndex = code.indexOf(, index);
            conditionalIndex = code.indexOf(, index);
            if (conditionalIndex >= 0 && conditionalIndex < statementIndex) {
                var close = FlowParser.otherEnd(conditionalIndex, code);
                if (FlowParser.nextWord(close + 1, code).equals("else")) {
                    var open = code.indexOf(, close);
                    close = FlowParser.otherEnd(open, code);
                }
                var conditionalString = code.substring(index, close + 1);
                var conditionalBody = new ConditionBody();
                conditionalBody.parse(conditionalString);
                bodies.add(conditionalBody);
                index = close + 1;
            } else {
                if (statementIndex >= 0) {
                    int close = index;
                    statementBlocking(code, index, close);
                    index = close + 1;
                } else {
                    if (code.substring(index).isBlank()) {
                        break;
                    }
                }
            }
        }
    }
                """;
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
