package org.example.dto;

import org.example.enums.DonorTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddDonationDTO {
    public String donorName;
    public DonorTypeEnum donorType;
    public BigDecimal amount;
    public LocalDate donationDate;
    public String category;

    public AddDonationDTO(DonorTypeEnum donorType, String donorName, BigDecimal monto, LocalDate donationDate, String category) {
        this.donorType = donorType;
        this.donorName = donorName;
        this.amount = monto;
        this.donationDate = donationDate;
        this.category = category;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public void setDonorType(DonorTypeEnum donorType) {
        this.donorType = donorType;
    }

    public void setAmount(BigDecimal monto) {
        this.amount = monto;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDonorName() {
        return donorName;
    }

    public DonorTypeEnum getDonorType() {
        return donorType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public String getCategory() {
        return category;
    }
}
