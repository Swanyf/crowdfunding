package test;

import org.junit.Test;

import com.swan.crowdfunding.util.StringMd5Utils;

public class StringTest {
    
    @Test
    public void testMD5() {
        String source = "123123";
        String md5 = StringMd5Utils.md5(source);
        System.out.println(md5);
    }

}
