package jahv.payclip.commandline.controller.impl;

import jahv.payclip.commandline.controller.CommandController;

import jahv.payclip.commandline.domain.CommandEnum;
import jahv.payclip.commandline.domain.SumTransaction;
import jahv.payclip.commandline.service.TransactionService;
import jahv.payclip.commandline.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SumTransactionCommandController")
public class SumTransactionCommandController extends CommandController<SumTransaction> {

    private TransactionService transactionService;

    @Autowired
    public SumTransactionCommandController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void execute() {
        result = transactionService.sum(userId);
    }

    @Override
    public CommandController build(String[] args) {
        validateArgs(args, CommandEnum.SUM);
        return super.build(args);
    }

    @Override
    public String printResult() {
        return Helpers.deserializeTransaction(result);
    }

    @Override
    public SumTransaction getResult() {
        return result;
    }

    @Override
    public void setResult(SumTransaction result) {
        this.result = result;
    }
}
