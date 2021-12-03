package utils;

public class StringFormatterUtil {
    /**
     * @return xpath in String format for dynamic locators"
     */
    public static String composeXpath(String locator, String value) {
        return String.format(locator, value);
    }
}
