package jahv.payclip.commandline.controller.impl;

import jahv.payclip.commandline.controller.CommandController;
import jahv.payclip.commandline.domain.CommandEnum;
import jahv.payclip.commandline.domain.Transaction;
import jahv.payclip.commandline.service.TransactionService;
import jahv.payclip.commandline.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ListTransactionsCommandController")
public class ListTransactionsCommandController extends CommandController<List<Transaction>> {

    private TransactionService transactionService;

    @Autowired
    public ListTransactionsCommandController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public CommandController build(String[] args) {
        validateArgs(args, CommandEnum.LIST);
        return super.build(args);
    }

    @Override
    public void execute() {
        result = transactionService.list(userId);
    }

    @Override
    public String printResult() {
        return Helpers.deserializeTransaction(result);
    }

    @Override
    public List<Transaction> getResult() {
        return result;
    }

    @Override
    public void setResult(List<Transaction> result) {
        this.result = result;
    }
}
