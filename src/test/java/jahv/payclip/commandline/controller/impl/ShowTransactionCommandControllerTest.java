package jahv.payclip.commandline.controller.impl;

import jahv.payclip.commandline.controller.CommandController;
import jahv.payclip.commandline.domain.Transaction;
import jahv.payclip.commandline.service.TransactionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

public class ShowTransactionCommandControllerTest {

    private ShowTransactionCommandController showTransactionCommandController;

    private TransactionService transactionServiceMock;


    @Before
    public void setUpt() {
        transactionServiceMock = PowerMock.createMock(TransactionService.class);
        showTransactionCommandController = new ShowTransactionCommandController(transactionServiceMock);
    }

    @Test
    public void buildTest() {
        String uuid = "UUID";
        String args[] = new String[]{"123", uuid};

        CommandController<Transaction> transaction = showTransactionCommandController.build(args);

        Assert.assertTrue(transaction instanceof ShowTransactionCommandController);
        Assert.assertEquals(uuid, ((ShowTransactionCommandController)transaction).getTransactionId());
        Assert.assertEquals(123, transaction.getUserId());
    }

}
