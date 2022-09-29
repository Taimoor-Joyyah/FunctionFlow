public class ReturnBody implements Body{
    private String returnData;

    @Override
    public void parse(String code) {
        var index = code.indexOf(';');
        returnData = code.substring(7, index);
    }
}
