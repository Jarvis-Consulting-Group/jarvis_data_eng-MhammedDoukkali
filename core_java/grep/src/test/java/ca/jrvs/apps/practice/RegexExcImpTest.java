package ca.jrvs.apps.practice;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImpTest extends TestCase {


    @Test
    public void testMatchJpeg() {
        RegexExcImp regexExcImp = new RegexExcImp();
        assertTrue(regexExcImp.matchJpeg("sdfsdfsdf.jpg"));
        assertFalse(regexExcImp.matchJpeg("sdfsdfsdf.sfdgdg"));


    }

    @Test
    public void testMatchIp() {
        RegexExcImp regexExcImp = new RegexExcImp();
        assertTrue( regexExcImp.matchJpeg("sdfsdfsdf.jpg"));
        assertFalse(regexExcImp.matchJpeg("sdfsdfsdf.sfdgdg"));
    }
    @Test
    public void testIsEmptyLine() {
    }
}