package subway.view.constants.menu;

import static subway.view.constants.ErrorMessage.INPUT_INVALID_COMMAND;

import java.util.Arrays;

public enum OptionCommand {
    BY_DISTANCE("1"),
    BY_TIME("2"),
    BACK("B");

    private final String key;

    OptionCommand(String key) {
        this.key = key;
    }

    public static OptionCommand find(String key) {
        return Arrays.stream(values())
                .filter(value -> value.key.equals(key))
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(INPUT_INVALID_COMMAND.getValue());
                });
    }

    public String getKey() {
        return key;
    }
}
