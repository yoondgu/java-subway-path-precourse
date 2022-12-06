package subway.view.constants;

public enum InputMessage {
    COMMAND_HEADER("원하는 기능을 선택하세요."),

    STATION_TO_DEPART("출발역을 입력하세요."),
    STATION_TO_ARRIVE("도착역을 입력하세요.")
    ;

    private final String value;

    InputMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
