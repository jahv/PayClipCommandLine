package jahv.payclip.commandline.service;

import jahv.payclip.commandline.UtilsForTest;
import jahv.payclip.commandline.dao.TransactionsDAO;
import jahv.payclip.commandline.domain.SumTransaction;
import jahv.payclip.commandline.domain.Transaction;
import jahv.payclip.commandline.service.impl.TransactionServiceImpl;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {TransactionServiceImpl.class})
public class TransactionServiceTest {

    private TransactionService transactionService;
    private TransactionsDAO transactionsDAOMock;

    @Before
    public void setUp() {
        transactionsDAOMock = PowerMock.createMock(TransactionsDAO.class);
        transactionService = new TransactionServiceImpl(transactionsDAOMock);
    }

    @Test
    public void saveTest() {
        int userId = 345;
        Date date = new Date();

        Transaction transaction = UtilsForTest.generateTransaction(1.0, "description", userId, date);
        transaction.setTransactionId(null);

        EasyMock.expect(transactionsDAOMock.save(transaction)).andReturn(transaction);

        PowerMock.replayAll();
        Transaction transactionSaved = transactionService.save(userId, transaction);

        Assert.assertNotNull(transactionSaved);
        Assert.assertNotNull(transactionSaved.getTransactionId());
        Assert.assertEquals(1.0, transactionSaved.getAmount(), 0);
        Assert.assertEquals("description", transactionSaved.getDescription());
        Assert.assertEquals(345, transactionSaved.getUserId());
        Assert.assertEquals(date, transaction.getDate());

        PowerMock.verifyAll();
    }

    @Test
    public void saveTest_UserIdNotMatch() {
        int userX = 123;
        int userId = 345;
        Date date = new Date();

        Transaction transaction = UtilsForTest.generateTransaction(1.0, "description", userId, date);
        transaction.setTransactionId(null);

        EasyMock.expect(transactionsDAOMock.save(transaction)).andReturn(transaction);

        PowerMock.replayAll();
        Transaction transactionSaved = transactionService.save(userX, transaction);

        Assert.assertNotNull(transactionSaved);
        Assert.assertNotNull(transactionSaved.getTransactionId());
        Assert.assertEquals(1.0, transactionSaved.getAmount(), 0);
        Assert.assertEquals("description", transactionSaved.getDescription());
        Assert.assertEquals(userX, transactionSaved.getUserId());
        Assert.assertEquals(date, transaction.getDate());

        PowerMock.verifyAll();
    }

    @Test
    public void sumTest() {
        int userId1 = 123;
        int userId2 = 456;
        int userIdX = 999;
        Date date = new Date();

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

        EasyMock.expect(transactionsDAOMock.get(userId1)).andReturn(transactionsForUser1);
        EasyMock.expect(transactionsDAOMock.get(userId2)).andReturn(transactionsForUser2);
        EasyMock.expect(transactionsDAOMock.get(userIdX)).andReturn(Collections.emptyList());

        PowerMock.replayAll();

        SumTransaction expectedSumTransaction = new SumTransaction();
        expectedSumTransaction.setUserId(userId1);
        expectedSumTransaction.setSum(6.0);
        SumTransaction sumTransaction = transactionService.sum(userId1);
        Assert.assertEquals(expectedSumTransaction, sumTransaction);

        expectedSumTransaction = new SumTransaction();
        expectedSumTransaction.setUserId(userId2);
        expectedSumTransaction.setSum(9.0);
        sumTransaction = transactionService.sum(userId2);
        Assert.assertEquals(expectedSumTransaction, sumTransaction);

        expectedSumTransaction = new SumTransaction();
        expectedSumTransaction.setUserId(userIdX);
        expectedSumTransaction.setSum(0.0);
        sumTransaction = transactionService.sum(userIdX);
        Assert.assertEquals(expectedSumTransaction, sumTransaction);

        PowerMock.verifyAll();
    }

}
