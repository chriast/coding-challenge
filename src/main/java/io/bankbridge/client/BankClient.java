package io.bankbridge.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.bankbridge.model.BankModel;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class BankClient {
    private final Client client = ClientBuilder.newClient();

    public BankModel getBankDetails(String bankUri) {
        URI uri = UriBuilder.fromPath(bankUri).build();
        Response resp = client.target(uri).request().get();
        return processResponse(resp);
    }

    private BankModel processResponse(Response resp) {
        String results;
        try {
            results = resp.readEntity(String.class);
            return new ObjectMapper().readValue(results, BankModel.class);
        } catch (Exception e) {
            throw new RuntimeException("Error while processing request");
        }
    }
}


