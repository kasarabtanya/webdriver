package utils.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import objects.googlecloud.Calculator;
import objects.pastebin.Paste;

import java.io.File;
import java.io.IOException;

public class JacksonParser {

    /**
     * @return returns Paste object from  file
     */
    public static Paste getPaste(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Paste paste = mapper.readValue(file, Paste.class);
        return paste;
    }

    /**
     * @return returns Calculator object from  file"
     */
    public static Calculator getCalculator(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Calculator calculator = mapper.readValue(file, Calculator.class);
        return calculator;
    }
}
