package io.bankbridge.handler;

import io.bankbridge.client.BankClient;
import io.bankbridge.dto.BankDTO;
import io.bankbridge.model.BankModel;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BanksRemoteCallsHandlerTest {

    @Test
    public void handle() {

        String expectedResult = "[{\"id\":\"101\",\"name\":\"test bank\"}]";

        BankClient dummyBankClient = Mockito.mock(BankClient.class);

        Map<String,String> dummyConfig = new HashMap<>();
        dummyConfig.put("test bank","http://localhost:5678/test");

        BankModel dummyModel = new BankModel("101","test bank");

        when(dummyBankClient.getBankDetails(dummyConfig.get("test bank"))).thenReturn(dummyModel);

        BanksRemoteCallsHandler testHandler = new BanksRemoteCallsHandler(dummyBankClient,dummyConfig);
        String actualResult = testHandler.handle();
        Assert.assertEquals(expectedResult,actualResult);

    }
}