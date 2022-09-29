public class PrototypeBody implements Body {
    private String name;
    private String returnType;
    private String[] parameters;

    @Override
    public void parse(String code) {
        var open = code.indexOf('(');
        var close = code.indexOf(')');

        var elements = code.substring(0, open).split(" ");
        name = elements[elements.length - 1];
        returnType = elements[elements.length - 2];

        var parameterString = code.substring(open + 1, close);
        parameters = parameterString.split(",");
    }
}
