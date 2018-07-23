package jahv.payclip.commandline.controller;

import jahv.payclip.commandline.domain.CommandEnum;
import jahv.payclip.commandline.exceptions.ArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class CommandControllerFactory {

    private CommandController addTransactionCommandController;
    private CommandController showTransactionCommandController;
    private CommandController listTransactionsCommandController;
    private CommandController sumTransactionsCommandController;

    @Autowired
    public CommandControllerFactory(@Qualifier("AddTransactionCommandController") CommandController addTransactionCommandController,
                                    @Qualifier("ShowTransactionCommandController") CommandController showTransactionCommandController,
                                    @Qualifier("ListTransactionsCommandController") CommandController listTransactionsCommandController,
                                    @Qualifier("SumTransactionCommandController") CommandController sumTransactionsCommandController) {
        this.addTransactionCommandController = addTransactionCommandController;
        this.showTransactionCommandController = showTransactionCommandController;
        this.listTransactionsCommandController = listTransactionsCommandController;
        this.sumTransactionsCommandController = sumTransactionsCommandController;
    }

    public CommandController getCommand(String[] args) {
        validateArgs(args);
        CommandEnum command = CommandEnum.getCommand(args[1]);

        switch(command) {
            case ADD:
                return addTransactionCommandController;
            case SHOW:
                return showTransactionCommandController;
            case LIST:
                return listTransactionsCommandController;
            case SUM:
                return sumTransactionsCommandController;
        }
        return null;
    }

    private void validateArgs(String[] args) {
        if(args.length < 2) {
            throw new ArgumentsException(CommandEnum.getHelp());
        }
    }
}
