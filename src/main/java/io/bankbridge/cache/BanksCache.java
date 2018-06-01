package io.bankbridge.cache;

import java.util.ArrayList;
import java.util.List;

import io.bankbridge.dto.BankDTO;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bankbridge.model.BankModelList;

public class BanksCache {


    private static BanksCache instance;
    private CacheManager cacheManager;

    private BanksCache() {
    }

    /**
     * a singleton is used in order to prevent duplicate caches.
     *
     * @return instance of cache
     **/
    public static synchronized BanksCache getInstance() {
        if (instance == null) {
            instance = new BanksCache();
            instance.init();
        }
        return instance;
    }

    //constructor for unit testing
    BanksCache(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    private void init() {
        //fill the cache
        cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder().withCache("banks", CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10)))
                .build();
        cacheManager.init();
        Cache<String, String> cache = cacheManager.getCache("banks", String.class, String.class);
        BankModelList models = loadResource();
        if (models != null) {
            models.getBanks().forEach(b -> cache.put(b.getBic(), b.getName()));
        }
    }

    /**
     * access the json DB and retrieve the data from within
     *
     * @return BankModelList instance
     **/
    private BankModelList loadResource() {
        try {//read json, add banks to cache
            return new ObjectMapper().readValue(
                    Thread.currentThread().getContextClassLoader().getResource("banks-v1.json"), BankModelList.class);
        } catch (Exception e) {
            throw new RuntimeException("Error while processing request for Cache Handler");
        }
    }

    public List<BankDTO> getAllFromCache() {
        //read all banks from the cache
        List<BankDTO> result = new ArrayList<>();

        cacheManager.getCache("banks", String.class, String.class).forEach(entry -> {
            BankDTO bank = new BankDTO(entry.getKey(), entry.getValue());
            result.add(bank);
        });

        return result;
    }
}
