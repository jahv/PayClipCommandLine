package jahv.payclip.commandline.utils;

import org.junit.Assert;
import org.junit.Test;

public class HelpersTest {

    @Test
    public void isNumericTest() {
        Assert.assertTrue(Helpers.isNumeric("1"));
        Assert.assertTrue(Helpers.isNumeric("12"));
        Assert.assertTrue(Helpers.isNumeric("123"));
        Assert.assertTrue(Helpers.isNumeric("1234"));
        Assert.assertTrue(Helpers.isNumeric("12345"));

        Assert.assertFalse(Helpers.isNumeric("a"));
        Assert.assertFalse(Helpers.isNumeric("a1"));
        Assert.assertFalse(Helpers.isNumeric("1a"));
        Assert.assertFalse(Helpers.isNumeric("1.2"));
    }

    @Test
    public void intValueTest() {
        Assert.assertEquals(1, Helpers.intValue("1"));
        Assert.assertEquals(12, Helpers.intValue("12"));
    }
}
