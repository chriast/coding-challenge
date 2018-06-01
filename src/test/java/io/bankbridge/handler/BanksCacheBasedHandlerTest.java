package io.bankbridge.handler;

import io.bankbridge.cache.BanksCache;
import io.bankbridge.dto.BankDTO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BanksCacheBasedHandlerTest {

    @Test
    public void handle() {
        String expectedResult = "[{\"id\":\"101\",\"name\":\"testname\"}]";

        List<BankDTO> banks = new ArrayList<>();
        BankDTO bankDTO = new BankDTO("101","testname");
        banks.add(bankDTO);

        BanksCache banksCache = Mockito.mock(BanksCache.class);
        BanksCacheBasedHandler testHandler = new BanksCacheBasedHandler(banksCache);
        when(banksCache.getAllFromCache()).thenReturn(banks);
        String actualResult = testHandler.handle();

        Assert.assertEquals(expectedResult,actualResult);
    }
}