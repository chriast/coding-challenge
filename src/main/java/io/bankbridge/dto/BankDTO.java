package io.bankbridge.dto;

import java.util.Objects;

//This replaces the previous use of a map for storing the data that is to be printed.
public class BankDTO {
    private String name;
    private String id;

    public BankDTO() {
    }

    public BankDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankDTO bankDTO = (BankDTO) o;
        return Objects.equals(id, bankDTO.id) &&
                Objects.equals(name, bankDTO.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
