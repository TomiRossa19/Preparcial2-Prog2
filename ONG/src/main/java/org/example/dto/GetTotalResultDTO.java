package org.example.dto;

import org.example.enums.DonorTypeEnum;

import java.math.BigDecimal;

public class GetTotalResultDTO {
    public DonorTypeEnum donorType;
    public Long donationQuantity;
    public BigDecimal totalAmount;

    public GetTotalResultDTO(DonorTypeEnum donorType, Long donationQuantity, BigDecimal totalAmount) {
        this.donorType = donorType;
        this.donationQuantity = donationQuantity;
        this.totalAmount = totalAmount;
    }

    public void setDonorType(DonorTypeEnum donorType) {
        this.donorType = donorType;
    }

    public void setDonationQuantity(Long donationQuantity) {
        this.donationQuantity = donationQuantity;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public DonorTypeEnum getDonorType() {
        return donorType;
    }

    public Long getDonationQuantity() {
        return donationQuantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

}
