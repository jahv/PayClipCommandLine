package jahv.payclip.commandline.service.impl;

import jahv.payclip.commandline.dao.TransactionsDAO;
import jahv.payclip.commandline.domain.SumTransaction;
import jahv.payclip.commandline.domain.Transaction;
import jahv.payclip.commandline.service.TransactionService;
import jahv.payclip.commandline.utils.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionsDAO transactionsDAO;

    @Autowired
    public TransactionServiceImpl(TransactionsDAO transactionsDAO) {
        this.transactionsDAO = transactionsDAO;
    }

    public Transaction save(int userId, Transaction transaction) {
        validateUserIdMatch(userId, transaction);

        transaction.generateUniqueTransactionId();

        return transactionsDAO.save(transaction);
    }

    @Override
    public Transaction show(int userId, String transactionId) {
        return transactionsDAO.get(userId, transactionId);
    }

    @Override
    public List<Transaction> list(int userId) {
        return transactionsDAO.get(userId);
    }

    @Override
    public SumTransaction sum(int userId) {
        SumTransaction sumTransaction = new SumTransaction();

        double sum = 0.0;
        List<Transaction> transactions = transactionsDAO.get(userId);

        for(Transaction t : transactions) {
            sum += t.getAmount();
        }

        sumTransaction.setUserId(userId);
        sumTransaction.setSum(sum);

        return sumTransaction;
    }

    private void validateUserIdMatch(int userId, Transaction t) {
        if(userId != t.getUserId()) {
            String message = "WARN: user_id in the JSON does not match the user_id in the command. Application will use user_id from the command";
            Helpers.printMessage(message);
            t.setUserId(userId);
        }
    }


}
