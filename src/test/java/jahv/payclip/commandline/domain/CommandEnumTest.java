package jahv.payclip.commandline.domain;

import org.junit.Assert;
import org.junit.Test;

public class CommandEnumTest {

    @Test
    public void getCommandTest() {
        CommandEnum commandEnum = CommandEnum.getCommand("add");
        Assert.assertEquals(CommandEnum.ADD, commandEnum);
        commandEnum = CommandEnum.getCommand("ADD");
        Assert.assertEquals(CommandEnum.ADD, commandEnum);
        commandEnum = CommandEnum.getCommand("Add");
        Assert.assertEquals(CommandEnum.ADD, commandEnum);

        commandEnum = CommandEnum.getCommand("list");
        Assert.assertEquals(CommandEnum.LIST, commandEnum);
        commandEnum = CommandEnum.getCommand("LIST");
        Assert.assertEquals(CommandEnum.LIST, commandEnum);
        commandEnum = CommandEnum.getCommand("List");
        Assert.assertEquals(CommandEnum.LIST, commandEnum);

        commandEnum = CommandEnum.getCommand("sum");
        Assert.assertEquals(CommandEnum.SUM, commandEnum);
        commandEnum = CommandEnum.getCommand("SUM");
        Assert.assertEquals(CommandEnum.SUM, commandEnum);
        commandEnum = CommandEnum.getCommand("Sum");
        Assert.assertEquals(CommandEnum.SUM, commandEnum);

        commandEnum = CommandEnum.getCommand("abc");
        Assert.assertEquals(CommandEnum.SHOW, commandEnum);
        commandEnum = CommandEnum.getCommand("123");
        Assert.assertEquals(CommandEnum.SHOW, commandEnum);
        commandEnum = CommandEnum.getCommand("Anything123");
        Assert.assertEquals(CommandEnum.SHOW, commandEnum);
    }

}
