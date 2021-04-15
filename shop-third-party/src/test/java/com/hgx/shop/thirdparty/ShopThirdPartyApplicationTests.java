package com.hgx.shop.thirdparty;

import com.hgx.shop.thirdparty.component.SmsComponent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShopThirdPartyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void sendSms(){
        SmsComponent smsComponent = new SmsComponent();
        smsComponent.sendSmsCode("18814182105","888888");
    }

}
