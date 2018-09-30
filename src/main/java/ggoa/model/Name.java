package ggoa.model;

public class Name {
    private String firstName;
    private String lastName;
    private String salutation;

    public Name(String firstName, String lastName, String salutation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salutation = salutation;
    }
    public Name(){}
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salutation='" + salutation + '\'' +
                '}';
    }
}
