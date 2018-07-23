package jahv.payclip.commandline.controller;

import jahv.payclip.commandline.domain.CommandEnum;
import jahv.payclip.commandline.exceptions.ArgumentsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandControllerTest {

    private CommandController commandControllerTest;

    @Before
    public void setUp() {
        commandControllerTest = new CommandController() {
            @Override
            public void execute() { }

            @Override
            public String printResult() { return null; }

            @Override
            public Object getResult() { return null; }

            @Override
            public void setResult(Object result) { }
        };
    }

    @Test(expected = ArgumentsException.class)
    public void validateArgsTest_ADD_NotEnough() {
        String args[] = new String[]{};
        commandControllerTest.validateArgs(args, CommandEnum.ADD);
    }

    @Test
    public void validateArgsTest_ADD_Ok() {
        String args[] = new String[]{"123", "ADD", "JSON"};
        commandControllerTest.validateArgs(args, CommandEnum.ADD);
    }

    @Test(expected = ArgumentsException.class)
    public void validateArgsTest_LIST_NotEnough() {
        String args[] = new String[]{"1"};
        commandControllerTest.validateArgs(args, CommandEnum.LIST);
    }

    @Test
    public void validateArgsTest_LIST_Ok() {
        String args[] = new String[]{"123", "LIST"};
        commandControllerTest.validateArgs(args, CommandEnum.LIST);
    }

    @Test(expected = ArgumentsException.class)
    public void validateArgsTest_SHOW_NotEnough() {
        String args[] = new String[]{"1"};
        commandControllerTest.validateArgs(args, CommandEnum.SHOW);
    }

    @Test
    public void validateArgsTest_SHOW_Ok() {
        String args[] = new String[]{"123", "SHOW"};
        commandControllerTest.validateArgs(args, CommandEnum.SHOW);
    }

    @Test(expected = ArgumentsException.class)
    public void validateArgsTest_SUM_NotEnough() {
        String args[] = new String[]{"1"};
        commandControllerTest.validateArgs(args, CommandEnum.SUM);
    }

    @Test
    public void validateArgsTest_SUM_Ok() {
        String args[] = new String[]{"123", "SHOW"};
        commandControllerTest.validateArgs(args, CommandEnum.SUM);
    }

    @Test(expected = ArgumentsException.class)
    public void validateArgsTest_NotNumeric() {
        String args[] = new String[]{"abc", "abc"};
        commandControllerTest.validateArgs(args, CommandEnum.ADD);
    }

    @Test
    public void buildTest(){
        String args[] = new String[]{"123", "COMMAND"};
        CommandController commandController = commandControllerTest.build(args);

        Assert.assertEquals(123, commandController.getUserId());
    }
}
