package io.bankbridge.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.bankbridge.client.BankClient;
import io.bankbridge.dto.BankDTO;

public class BanksRemoteCallsHandler {

    private Map<String, String> config;
    private BankClient bankClient;

    public BanksRemoteCallsHandler() throws Exception {
        init();
    }

    BanksRemoteCallsHandler(BankClient bankClient, Map<String, String> config) {
        this.config = config;
        this.bankClient = bankClient;
    }

    @SuppressWarnings("unchecked")
    private void init() throws Exception {
        this.bankClient = new BankClient();
        config = new ObjectMapper()
                .readValue(Thread.currentThread().getContextClassLoader().getResource("banks-v2.json"), Map.class);
    }

    public String handle() {
        List<BankDTO> banks = new ArrayList<>();
        for (Map.Entry<String, String> entry : config.entrySet()) {
            String id = bankClient.getBankDetails(entry.getValue()).getBic();
            banks.add(new BankDTO(entry.getKey(), id));
        }
        try {
            return new ObjectMapper().writeValueAsString(banks);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while processing request for Remote calls handler");
        }
    }

}
