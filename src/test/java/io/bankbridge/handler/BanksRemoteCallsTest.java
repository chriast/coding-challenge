package io.bankbridge.handler;

import io.bankbridge.client.BankClient;
import io.bankbridge.model.BankModel;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class BanksRemoteCallsTest {

    @Test
    public void handle() {

        String expectedResult = "[{\"name\":\"test bank\",\"id\":\"101\"}]";

        BankClient dummyBankClient = Mockito.mock(BankClient.class);

        Map<String,String> dummyConfig = new HashMap<>();
        dummyConfig.put("test bank","http://localhost:5678/test");

        BankModel dummyModel = new BankModel("101","test bank");

        when(dummyBankClient.getBankDetails(dummyConfig.get("test bank"))).thenReturn(dummyModel);

        BanksRemoteCalls testHandler = new BanksRemoteCalls(dummyBankClient,dummyConfig);
        String actualResult = testHandler.handle();
        Assert.assertEquals(expectedResult,actualResult);

    }
}