public class ReturnBody implements Body{
    private String returnData;

    @Override
    public void parse(String code) {
        returnData = code.split(" ", 2)[1].replace(';', ' ');
    }
}
