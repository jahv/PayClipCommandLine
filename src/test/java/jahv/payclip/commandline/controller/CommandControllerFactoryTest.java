package jahv.payclip.commandline.controller;

import jahv.payclip.commandline.controller.impl.AddTransactionCommandController;
import jahv.payclip.commandline.controller.impl.ListTransactionsCommandController;
import jahv.payclip.commandline.controller.impl.ShowTransactionCommandController;
import jahv.payclip.commandline.controller.impl.SumTransactionCommandController;
import jahv.payclip.commandline.exceptions.ArgumentsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

public class CommandControllerFactoryTest {

    private CommandControllerFactory commandControllerFactory;

    private CommandController addTransactionCommandController;
    private CommandController showTransactionCommandController;
    private CommandController listTransactionsCommandController;
    private CommandController sumTransactionsCommandController;

    @Before
    public void setUp() {
        addTransactionCommandController = PowerMock.createMock(AddTransactionCommandController.class);
        showTransactionCommandController = PowerMock.createMock(ShowTransactionCommandController.class);
        listTransactionsCommandController = PowerMock.createMock(ListTransactionsCommandController.class);
        sumTransactionsCommandController = PowerMock.createMock(SumTransactionCommandController.class);

        commandControllerFactory = new CommandControllerFactory(addTransactionCommandController,
                showTransactionCommandController,
                listTransactionsCommandController,
                sumTransactionsCommandController);
    }

    @Test(expected = ArgumentsException.class)
    public void getCommandTest_emptyArgs() {
        String[] args = new String[]{};
        commandControllerFactory.getCommand(args);
    }

    @Test(expected = ArgumentsException.class)
    public void getCommandTest_ArgsLen1() {
        String[] args = new String[]{"1"};
        commandControllerFactory.getCommand(args);
    }

    @Test
    public void getCommandTest_Add() {
        String[] args = new String[]{"1", "add"};
        CommandController commandController = commandControllerFactory.getCommand(args);
        Assert.assertTrue(commandController instanceof AddTransactionCommandController);
    }

    @Test
    public void getCommandTest_List() {
        String[] args = new String[]{"1", "list"};
        CommandController commandController = commandControllerFactory.getCommand(args);
        Assert.assertTrue(commandController instanceof ListTransactionsCommandController);
    }

    @Test
    public void getCommandTest_Sum() {
        String[] args = new String[]{"1", "sum"};
        CommandController commandController = commandControllerFactory.getCommand(args);
        Assert.assertTrue(commandController instanceof SumTransactionCommandController);
    }

    @Test
    public void getCommandTest_Show() {
        String[] args = new String[]{"1", "123"};
        CommandController commandController = commandControllerFactory.getCommand(args);
        Assert.assertTrue(commandController instanceof ShowTransactionCommandController);

        args = new String[]{"1", "abc"};
        commandController = commandControllerFactory.getCommand(args);
        Assert.assertTrue(commandController instanceof ShowTransactionCommandController);

        args = new String[]{"1", "anyValue"};
        commandController = commandControllerFactory.getCommand(args);
        Assert.assertTrue(commandController instanceof ShowTransactionCommandController);
    }
}
