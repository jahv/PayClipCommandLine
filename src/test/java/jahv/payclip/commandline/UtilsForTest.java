package jahv.payclip.commandline;

import jahv.payclip.commandline.domain.Transaction;

import java.util.Date;
import java.util.UUID;

public class UtilsForTest {

    public static Transaction generateTransaction(double amount, String description, int userId, Date date) {
        Transaction transaction = new Transaction();

        transaction.setTransactionId(UUID.randomUUID());
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setDate(date);
        transaction.setUserId(userId);

        return transaction;
    }
}
