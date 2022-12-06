package subway.view.constants.menu;

public enum MainMenu implements Menu {
    SEARCH_ROAD("경로 조회", MainCommand.SEARCH_ROAD),
    QUIT("종료", MainCommand.QUIT);

    private final String value;
    private final MainCommand command;

    MainMenu(String value, MainCommand command) {
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
