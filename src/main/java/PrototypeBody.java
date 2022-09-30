public class PrototypeBody implements Body {
    private String name;
    private String returnType;
    private String[] parameters;

    @Override
    public void parse(String code) {
        var open = code.indexOf('(');
        var close = code.indexOf(')');

        var elements = code.substring(0, open).split(" ");
        name = elements[elements.length - 1].trim();
        returnType = elements[elements.length - 2].trim();

        var parameterString = code.substring(open + 1, close);
        parameters = parameterString.split(",");
        for (int i = 0; i < parameters.length; i++) parameters[i] = parameters[i].trim();
    }
}
