package com.hui.hhjr.sms;

import com.hui.hhjr.sms.util.SmsProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hui 1154437939@qq.com
 * @version 2022/6/21 16:18
 * @since JDK8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilsTest {

    @Test
    public void testProperties(){
        System.out.println(SmsProperties.REGION_ID);
        System.out.println(SmsProperties.KEY_ID);
        System.out.println(SmsProperties.KEY_SECRET);
        System.out.println(SmsProperties.TEMPLATE_CODE);
        System.out.println(SmsProperties.SIGN_NAME);
    }
}
