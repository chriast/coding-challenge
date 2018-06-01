package io.bankbridge;

import static spark.Spark.get;
import static spark.Spark.port;

import io.bankbridge.handler.BanksCacheBasedHandler;
import io.bankbridge.handler.BanksRemoteCallsHandler;

public class Main {

    public static void main(String[] args) throws Exception {

        port(8080);

        BanksCacheBasedHandler banksCacheBasedHandler = new BanksCacheBasedHandler();
        BanksRemoteCallsHandler banksRemoteCallsHandler = new BanksRemoteCallsHandler();
        get("/v1/banks/all", (request, response) -> banksCacheBasedHandler.handle());
        get("/v2/banks/all", (request, response) -> banksRemoteCallsHandler.handle());
    }
}