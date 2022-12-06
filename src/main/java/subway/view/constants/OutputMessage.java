package subway.view.constants;

public enum OutputMessage {
    MAIN_DISPLAY_HEADER("메인 화면"),
    OPTION_DISPLAY_HEADER("경로 기준"),

    BORDER_LINE("---");

    private final String value;

    OutputMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
