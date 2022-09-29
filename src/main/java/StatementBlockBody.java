import java.util.ArrayList;
import java.util.List;

public class StatementBlockBody implements Body {
    private final List<StatementBody> statementList;

    public StatementBlockBody() {
        statementList = new ArrayList<>();
    }

    @Override
    public void parse(String code) {
        var builder = new StringBuilder();
        for (var character : code.toCharArray()) {
            builder.append(character);

            if (character == ';') {
                var statement = new StatementBody();
                statement.parse(builder.toString());
                statementList.add(statement);

                builder = new StringBuilder();
            }
        }
    }
}
