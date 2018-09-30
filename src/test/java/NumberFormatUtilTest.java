package ggoa.test;
import org.junit.*;
import ggoa.util.NumberFormatUtil;

import static org.junit.Assert.assertNotNull;

public class NumberFormatUtilTest {
    private NumberFormatUtil numberFormatUtil;






    @Test
    public void testNumber(){

        assertNotNull(numberFormatUtil.numberToWords(3));
        assertNotNull(numberFormatUtil.numberToWords(0));
        assertNotNull(numberFormatUtil.numberToWords(-10));

    }
}