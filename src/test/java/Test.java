public class Test {
    public static void main(String[] args) {
        String functionCode = """
                public int factorial(int number) {
                    if (number < 0) {
                        System.out.println("Number must be < 0");
                        return -1;
                    }
                    int fact = 1;
                    while (number > 1) {
                        fact *= number;
                        number--;
                    }
                    return fact;
                }
                """;
        var parser = new FlowParser(functionCode);
        parser.parse();
    }
}
