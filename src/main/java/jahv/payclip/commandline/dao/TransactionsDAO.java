package jahv.payclip.commandline.dao;

import jahv.payclip.commandline.domain.Transaction;

import java.util.List;

public interface TransactionsDAO {

    Transaction save(Transaction transaction);
    Transaction get(int userId, String transactionId);
    List<Transaction> get(int userId);


}
