package jahv.payclip.commandline.service;

import jahv.payclip.commandline.domain.SumTransaction;
import jahv.payclip.commandline.domain.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction save(int userId, Transaction transaction);

    Transaction show(int userId, String transactionId);

    List<Transaction> list(int userId);

    SumTransaction sum(int userId);
}
