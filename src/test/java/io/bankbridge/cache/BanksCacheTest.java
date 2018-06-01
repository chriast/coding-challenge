package io.bankbridge.cache;

import io.bankbridge.dto.BankDTO;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BanksCacheTest {

    @Test
    public void getAllFromCache() {
        CacheManager cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder().withCache("banks", CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10)))
                .build();
        cacheManager.init();

        Cache<String, String> cache = cacheManager.getCache("banks", String.class, String.class);
        cache.put("101", "testname");
        BanksCache banksCache = new BanksCache(cacheManager);

        List<BankDTO> banks = new ArrayList<>();
        BankDTO bankDTO = new BankDTO("101","testname");
        banks.add(bankDTO);

        Assert.assertEquals(banksCache.getAllFromCache(),banks);
    }
}