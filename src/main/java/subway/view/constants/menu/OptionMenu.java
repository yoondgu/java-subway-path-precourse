package subway.view.constants.menu;

public enum OptionMenu implements Menu {
    PATH_CREATE("최단 거리", OptionCommand.BY_DISTANCE),
    PATH_DELETE("최단 시간", OptionCommand.BY_TIME),
    BACK("돌아가기", OptionCommand.BACK);

    private final String value;
    private final OptionCommand command;

    OptionMenu(String value, OptionCommand command) {
        this.value = value;
        this.command = command;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getCommandKey() {
        return command.getKey();
    }
}
