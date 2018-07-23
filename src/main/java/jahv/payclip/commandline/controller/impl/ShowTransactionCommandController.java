package jahv.payclip.commandline.controller.impl;

import jahv.payclip.commandline.controller.CommandController;
import jahv.payclip.commandline.domain.CommandEnum;
import jahv.payclip.commandline.domain.Transaction;
import jahv.payclip.commandline.service.TransactionService;
import jahv.payclip.commandline.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ShowTransactionCommandController")
public class ShowTransactionCommandController extends CommandController<Transaction> {

    private String transactionId;

    private TransactionService transactionService;

    @Autowired
    public ShowTransactionCommandController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void execute() {
        result = transactionService.show(userId, transactionId);
    }

    @Override
    public CommandController build(String[] args) {
        validateArgs(args, CommandEnum.SHOW);
        transactionId = args[1];
        return super.build(args);
    }

    @Override
    public String printResult() {
        String response = "Transaction  not found";
        if(result != null) {
            response = Helpers.deserializeTransaction(result);
        }
        return response;
    }

    @Override
    public Transaction getResult() {
        return result;
    }

    @Override
    public void setResult(Transaction result) {
        this.result = result;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
