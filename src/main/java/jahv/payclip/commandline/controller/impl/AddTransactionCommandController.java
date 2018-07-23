package jahv.payclip.commandline.controller.impl;

import jahv.payclip.commandline.controller.CommandController;
import jahv.payclip.commandline.domain.CommandEnum;
import jahv.payclip.commandline.domain.Transaction;
import jahv.payclip.commandline.service.TransactionService;
import jahv.payclip.commandline.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("AddTransactionCommandController")
public class AddTransactionCommandController extends CommandController<Transaction> {

    private TransactionService transactionService;
    private String json;

    @Autowired
    public AddTransactionCommandController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void execute() {
        Transaction transaction = Helpers.serializeTransaction(json);
        result = transactionService.save(userId, transaction);
    }

    @Override
    public CommandController<Transaction> build(String[] args) {
        validateArgs(args, CommandEnum.ADD);
        json = this.getJson(args);
        return super.build(args);
    }

    @Override
    public String printResult() {
        return Helpers.deserializeTransaction(result);
    }

    @Override
    public Transaction getResult() {
        return this.result;
    }

    @Override
    public void setResult(Transaction result) {
        this.result = result;
    }

    private String getJson(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=2; i<args.length; i++) {
            stringBuilder.append(args[i]);
        }
        return stringBuilder.toString();
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
