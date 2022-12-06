package subway.domain.constants;

public enum ErrorMessage {
    STATION_NOT_FOUND("존재하지 않는 역 이름입니다."),
    STATION_NOT_IN_LINE("노선에 등록되지 않은 역 이름입니다."),
    DEPART_AND_ARRIVE_SAME("출발역과 도착역은 동일할 수 없습니다."),
    PATH_NOT_FOUND("출발역과 도착역 간 이동 가능한 경로가 존재하지 않습니다."),
    PATH_INVALID("계산한 경로가 올바르지 않습니다.");

    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
