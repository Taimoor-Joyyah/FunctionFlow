import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) throws IOException {
        String functionCode;
        functionCode = Files.readString(Paths.get("src/test/resources/example01"));
        var parser = new FlowParser(functionCode);
        parser.parse();
    }
}
