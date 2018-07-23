package jahv.payclip.commandline.domain;

public enum CommandEnum {
    ADD("Add a transaction to the user specified in <user_id> using the information specified in " +
            "<transaction_json>", "<transaction_json>", true, 3),
    SHOW("Return the transaction specified in the <transaction_id>", "<transaction_id>", false, 2),
    LIST("Print all the transactions associated with the user specified by <user_id>", "",true, 2),
    SUM("Sum all the transactions associated with the user specified by <user_id>", "", true, 2);

    private String description;
    private String commandOptions;
    private boolean displayCommandName;
    private int reqArgs;

    CommandEnum(String description, String commandOptions, boolean displayCommandName, int reqArgs) {
        this.description = description;
        this.commandOptions = commandOptions;
        this.displayCommandName = displayCommandName;
        this.reqArgs = reqArgs;
    }

    public static CommandEnum getCommand(String value) {
        for(CommandEnum c : CommandEnum.values()) {
            if(c.name().equalsIgnoreCase(value)) {
                return c;
            }
        }
        return SHOW;
    }

    public String getDescription() {
        return description;
    }

    public String getCommandOptions() {
        return commandOptions;
    }

    public boolean isDisplayCommandName() {
        return displayCommandName;
    }

    public int getReqArgs() {
        return reqArgs;
    }

    public String usage() {
        StringBuilder usage = new StringBuilder("<user_id> ");
        if (this.isDisplayCommandName()) {
            usage.append(this.name()).append(" ");
        }
        usage.append(this.getCommandOptions());

        return usage.toString();
    }

    public static String getHelp() {
        StringBuilder help = new StringBuilder("Usage:");
        help.append("\n");
        for(CommandEnum commandEnum : CommandEnum.values()) {
            help.append("\t").append(commandEnum)
                    .append(": ").append(commandEnum.usage()).append("\n")
                    .append("\t\t").append(commandEnum.getDescription()).append("\n");
        }

        return help.toString();
    }

}
