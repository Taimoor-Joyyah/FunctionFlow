public class FlowParser {
    private PrototypeBody prototype;
    private BlockBody mainBlock;
    private final String code;

    public FlowParser(String code) {
        this.code = encode(code);
    }

    public void parse() {
        System.out.println(code);
        int mainOpen = code.indexOf('{');
        int mainClose = code.lastIndexOf('}');

        var prototypeString = code.substring(0, mainOpen).trim();
        var prototypeBody = new PrototypeBody();
        prototypeBody.parse(prototypeString);
        prototype = prototypeBody;

        var mainString = code.substring(mainOpen + 1, mainClose).trim();
        var body = new BlockBody();
        body.parse(mainString);
        mainBlock = body;
    }

    public static String encode(String code) {
        var builder = new StringBuilder();
        var search = '\0';
        for (var character : code.toCharArray()) {
            if (search == '\0') {
                if (character == '\'')
                    search = '\'';
                else if (character == '\"')
                    search = '\"';
            }
            else {
                if (character != search){
                    if (character == ';') {
                        builder.append("\\SEMI_COLON\\");
                        continue;
                    } else if (character == '{') {
                        builder.append("\\OPEN_BRACKET\\");
                        continue;
                    }
                }
                else
                    search = '\0';
            }
            builder.append(character);
        }
        return builder.toString();
    }

    public static String decode(String code) {
        return code.replace("\\SEMI_COLON\\", ";")
                   .replace("\\OPEN_BRACKET\\", "{");
    }

    protected static int otherEnd(int index, String code) {
        int level = 0;
        for (int i = index + 1; i < code.length(); i++) {
            var character = code.charAt(i);
            if (character == '{') level++;
            else if (character == '}') level--;
            if (level == -1) return i;
        }
        return -1;
    }

    protected static String nextWord(int index, String code) {
        var builder = new StringBuilder();
        for (int i = index; i < code.length(); i++) {
            var character = code.charAt(i);
            if (!Character.isWhitespace(character))
                builder.append(character);
            else if (!builder.isEmpty())
                break;
        }
        return builder.toString();
    }
}
