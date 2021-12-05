package objects.pastebin;

public class Paste {
    String code;
    String title;
    String expectedResult;
    String highlightedElement;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getHighlightedElement() {
        return highlightedElement;
    }

    public void setHighlightedElement(String highlightedElement) {
        this.highlightedElement = highlightedElement;
    }
}
