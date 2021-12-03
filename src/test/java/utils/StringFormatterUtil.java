package utils;

public class StringFormatterUtil {
    /**
     * @return  String format of xpath for dynamic locators"
     */
    public static String composeXpath(String locator, String value) {
        return String.format(locator, value);
    }
}
