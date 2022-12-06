package subway.view.constants;

public enum ErrorMessage {
    INPUT_INVALID_COMMAND("기능 선택 키워드가 올바르지 않습니다."),
    INPUT_BLANK_NAME("이름은 공백 또는 빈 문자열일 수 없습니다."),
    INPUT_NOT_A_NUMBER_INDEX("구간 순서는 자연수가 아닌 값일 수 없습니다.");

    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
