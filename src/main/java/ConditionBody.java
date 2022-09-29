public class ConditionBody implements Body {
    private StructureType structure;
    private String condition;
    private BlockBody trueBody;
    private BlockBody falseBody;

    @Override
    public void parse(String code) {
        int bodyOpen = code.indexOf('{');
        int bodyClose = FlowParser.otherEnd(bodyOpen, code);

        var prototype = code.substring(0, bodyOpen).trim();
        var open = prototype.indexOf('(');
        var close = prototype.indexOf(')');

        condition = prototype.substring(open + 1, close);
        setStructure(prototype.substring(0, open).trim());

        var trueBlockString = code.substring(bodyOpen + 1, bodyClose);
        var trueBlock = new BlockBody();
        trueBlock.parse(trueBlockString);
        trueBody = trueBlock;

        if (structure == StructureType.IF) {
            var elseCode = code.substring(bodyClose + 1).trim();
            if (!elseCode.isBlank()) {
                int elseOpen = elseCode.indexOf('{');
                int elseClose = elseCode.lastIndexOf('}');

                var falseBlockString = elseCode.substring(elseOpen + 1, elseClose);
                var falseBlock = new BlockBody();
                falseBlock.parse(falseBlockString);
                falseBody = falseBlock;
            }
        }
    }

    private void setStructure(String code) {
        structure = switch (code) {
            case "if" -> StructureType.IF;
            case "while" -> StructureType.WHILE;
            case "for" -> StructureType.FOR;
            default -> null;
        };
    }
}
