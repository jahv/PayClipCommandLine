package jahv.payclip.commandline.controller;

import jahv.payclip.commandline.domain.CommandEnum;
import jahv.payclip.commandline.exceptions.ArgumentsException;
import jahv.payclip.commandline.utils.Helpers;

public abstract class CommandController<F> {

    protected int userId;
    protected F result;

    public abstract void execute();
    public abstract String printResult();
    public abstract F getResult();
    public abstract void setResult(F result);

    public CommandController build(String[] args) {
        userId = Helpers.intValue(args[0]);
        return this;
    }

    protected void validateArgs(String[] args, CommandEnum commandEnum) {
        if(args.length < commandEnum.getReqArgs() || !Helpers.isNumeric(args[0])) {
            throw new ArgumentsException("Usage: " + commandEnum.usage());
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
