package jahv.payclip.commandline.dao;

import jahv.payclip.commandline.UtilsForTest;
import jahv.payclip.commandline.dao.impl.TransactionDAOImpl;
import jahv.payclip.commandline.domain.Transaction;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransactionDAOTest {

    private static final String FILE_NAME = "payclip.transactions.test";

    private TransactionsDAO transactionsDAO;
    private File fileTest;


    @Before
    public void setUp() {
        fileTest = FileUtils.getFile(FILE_NAME);
        transactionsDAO = new TransactionDAOImpl();
        ((TransactionDAOImpl) transactionsDAO).setFileName(FILE_NAME);
    }

    @After
    public void tearDown() {
        FileUtils.deleteQuietly(fileTest);
    }

    @Test
    public void daoTest() {

        int userId1 = 1;
        int userId2 = 2;

        Date date = generateDate(22, Calendar.JULY, 2018);

        Transaction transaction1 = UtilsForTest.generateTransaction(1.0, "1", userId1, date);
        Transaction transaction2 = UtilsForTest.generateTransaction(2.0, "2", userId1, date);
        Transaction transaction3 = UtilsForTest.generateTransaction(3.0, "3", userId1, date);

        Transaction transaction4 = UtilsForTest.generateTransaction(4.0, "4", userId2, date);
        Transaction transaction5 = UtilsForTest.generateTransaction(5.0, "5", userId2, date);

        List<Transaction> transactionsForUser1 = new ArrayList<>();
        transactionsForUser1.add(transaction1);
        transactionsForUser1.add(transaction2);
        transactionsForUser1.add(transaction3);

        List<Transaction> transactionsForUser2 = new ArrayList<>();
        transactionsForUser2.add(transaction4);
        transactionsForUser2.add(transaction5);

        //Save data
        transactionsDAO.save(transaction1);
        transactionsDAO.save(transaction2);
        transactionsDAO.save(transaction3);
        transactionsDAO.save(transaction4);
        transactionsDAO.save(transaction5);

        //Test get by userId and transactionId
        Transaction transactionSaved = transactionsDAO.get(userId1, transaction1.getTransactionId().toString());
        Assert.assertEquals(transaction1, transactionSaved);

        transactionSaved = transactionsDAO.get(userId1, transaction2.getTransactionId().toString());
        Assert.assertEquals(transaction2, transactionSaved);

        transactionSaved = transactionsDAO.get(userId2, transaction5.getTransactionId().toString());
        Assert.assertEquals(transaction5, transactionSaved);

        transactionSaved = transactionsDAO.get(userId1, transaction5.getTransactionId().toString());
        Assert.assertNull(transactionSaved);

        transactionSaved = transactionsDAO.get(userId2, transaction1.getTransactionId().toString());
        Assert.assertNull(transactionSaved);

        //Test get by userId
        List<Transaction> transactionListSaved = transactionsDAO.get(userId1);
        Assert.assertTrue(transactionsForUser1.containsAll(transactionListSaved));
        Assert.assertTrue(transactionListSaved.containsAll(transactionsForUser1));

        transactionListSaved = transactionsDAO.get(userId2);
        Assert.assertTrue(transactionsForUser2.containsAll(transactionListSaved));
        Assert.assertTrue(transactionListSaved.containsAll(transactionsForUser2));

        transactionListSaved = transactionsDAO.get(999);
        Assert.assertTrue(transactionListSaved.isEmpty());
    }

    private Date generateDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        calendar.clear(Calendar.MILLISECOND);
        return calendar.getTime();
    }
}
