package io.bankbridge.model;

public class BankModel {

    private String bic;
    private String name;
    private String countryCode;
    private String auth;

    public BankModel() {
    }

    public BankModel(String bic, String name) {
        this.bic = bic;
        this.name = name;
    }

    public String getBic() {
        return bic;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getAuth() {
        return auth;
    }
}
