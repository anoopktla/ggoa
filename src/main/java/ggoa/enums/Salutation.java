package ggoa.enums;


public enum Salutation {
    Mr("Mr"),
    Miss("Miss"),
    Mrs("Mrs"),
    Dr("Dr");
    private String salutation;
    Salutation(String salutation){
        this.salutation = salutation;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }
}
