package ggoa.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CompensationDetails {
    private String pan;
    private String bankName;
    private int accountNumber;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date payDate;
    private int basicPay;
    private int hra;
    private int conveyance;
    private  int specialAllowance;
    private int grossSalary;
    private int tds;
    private int totalDeduction;
    private String grossSalaryInWords;

    public int getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(int basicPay) {
        this.basicPay = basicPay;
    }

    public int getHra() {
        return hra;
    }

    public void setHra(int hra) {
        this.hra = hra;
    }

    public int getConveyance() {
        return conveyance;
    }

    public void setConveyance(int conveyance) {
        this.conveyance = conveyance;
    }

    public int getSpecialAllowance() {
        return specialAllowance;
    }

    public void setSpecialAllowance(int specialAllowance) {
        this.specialAllowance = specialAllowance;
    }

    public int getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(int grossSalary) {
        this.grossSalary = grossSalary;
    }

    public int getTds() {
        return tds;
    }

    public void setTds(int tds) {
        this.tds = tds;
    }

    public int getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(int totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getGrossSalaryInWords() {
        return grossSalaryInWords;
    }

    public void setGrossSalaryInWords(String grossSalaryInWords) {
        this.grossSalaryInWords = grossSalaryInWords;
    }
}
