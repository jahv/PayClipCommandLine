package jahv.payclip.commandline.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jahv.payclip.commandline.UtilsForTest;
import jahv.payclip.commandline.controller.CommandController;
import jahv.payclip.commandline.domain.Transaction;
import jahv.payclip.commandline.exceptions.SerializeException;
import jahv.payclip.commandline.service.TransactionService;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AddTransactionCommandController.class})
public class AddTransactionCommandControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private AddTransactionCommandController addTransactionCommandController;

    private TransactionService transactionServiceMock;


    @Before
    public void setUpt() {
        transactionServiceMock = PowerMock.createMock(TransactionService.class);

        addTransactionCommandController = new AddTransactionCommandController(transactionServiceMock);
    }

    @Test
    public void buildTest() {
        String  transactionJson_part1 = "{ \"amount\": 1.23, \"description\": \"Joes Tacos\", ";
        String  transactionJson_part2 = "\"date\":\"2018-12-30\", \"user_id\": 345 }";
        String args[] = new String[]{"123", "ADD", transactionJson_part1, transactionJson_part2};

        CommandController<Transaction> transaction = addTransactionCommandController.build(args);

        Assert.assertTrue(transaction instanceof AddTransactionCommandController);
        Assert.assertEquals(transactionJson_part1 + transactionJson_part2, ((AddTransactionCommandController)transaction).getJson());
        Assert.assertEquals(123, transaction.getUserId());
    }

    @Test(expected = SerializeException.class)
    public void executeTest_WrongJson() {
        int userId = 123;
        String  transactionJson_part1 = "{ \"amount\": 1.23, \"description\": \"Joes Tacos\", ";

        String args[] = new String[]{"123", "ADD", transactionJson_part1};

        CommandController<Transaction> transaction = addTransactionCommandController.build(args);
        addTransactionCommandController.execute();
    }

    @Test
    public void executeTest() throws Exception {
        int userId = 123;

        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.JULY, 23, 0, 0, 0);
        calendar.clear(Calendar.MILLISECOND);
        Date date =  calendar.getTime();

        Transaction transactionExpected = UtilsForTest.generateTransaction(1,"Desc", userId, date);
        String  transactionJson = objectMapper.writeValueAsString(transactionExpected);

        String args[] = new String[]{"123", "ADD", transactionJson};

        CommandController<Transaction> transaction = addTransactionCommandController.build(args);

        EasyMock.expect(transactionServiceMock.save(EasyMock.eq(userId), EasyMock.eq(transactionExpected))).andReturn(transactionExpected);

        PowerMock.replayAll();

        addTransactionCommandController.execute();

        Assert.assertEquals(transactionExpected, transaction.getResult());

        PowerMock.verifyAll();
    }


}
