package ggoa.enums;

public enum TransactionType {

    CREDIT("CR"),DEBIT("DR");
    private String transactionType;
    private TransactionType(String transactionType){
        this.transactionType = transactionType;
    }

}
