public class StatementBody implements Body{
    private String statement;
    @Override
    public void parse(String code) {
        statement = code.trim();
    }
}
