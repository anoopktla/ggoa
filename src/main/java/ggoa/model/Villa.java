package ggoa.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Document(collection = "Villas")
public class Villa {

    private String id;

    private String number;
    private String name;
    private Map<String,BigDecimal> balance;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, BigDecimal> getBalance() {
        return balance;
    }

    public void setBalance(Map<String, BigDecimal> balance) {
        this.balance = balance;
    }
}