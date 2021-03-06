package io.bankbridge.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bankbridge.cache.BanksCache;
import io.bankbridge.dto.BankDTO;

import java.util.List;

public class BanksCacheBased {
    private final BanksCache bankCache;

    public BanksCacheBased() {
        bankCache = BanksCache.getInstance();
    }

    BanksCacheBased(BanksCache banksCache) {
        this.bankCache = banksCache;
    }

    public String handle() {
        List<BankDTO> banks = bankCache.getAllFromCache();
        try {
            return new ObjectMapper().writeValueAsString(banks);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while processing request");
        }
    }

}
