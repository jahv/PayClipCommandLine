package jahv.payclip.commandline.service.impl;

import jahv.payclip.commandline.dao.TransactionsDAO;
import jahv.payclip.commandline.domain.SumTransaction;
import jahv.payclip.commandline.domain.Transaction;
import jahv.payclip.commandline.service.TransactionService;
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


}
