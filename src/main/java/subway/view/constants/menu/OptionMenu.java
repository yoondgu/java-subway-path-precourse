package subway.view.constants.menu;

public enum OptionMenu implements Menu {
    PATH_CREATE("구간 등록", OptionCommand.BY_DISTANCE),
    PATH_DELETE("구간 삭제", OptionCommand.BY_TIME),
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
