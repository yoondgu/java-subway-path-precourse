package subway.view.constants;

public enum Format {
    INFO("[INFO] %s"),
    HEADER("## %s"),
    ERROR("[ERROR] %s"),
    MENU("%s. %s"),
    TOTAL_DISTANCE("총 거리: %.0fkm"),
    TOTAL_TIME("총 시간: %.0f분");

    private final String value;

    Format(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
