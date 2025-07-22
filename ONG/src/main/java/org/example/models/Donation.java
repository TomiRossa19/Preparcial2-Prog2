package org.example.models;

import jakarta.persistence.*;
import org.example.dto.DonorTypeEnum;
import org.example.dto.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Long id;

    @Column(name = "donor_name")
    private String donorName;

    @Enumerated(EnumType.STRING)
    @Column(name = "donor_type")
    private DonorTypeEnum donorType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "donation_date")
    private LocalDate donationDate;

    @Column(name = "category")
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    public Donation(){

    }

    public Donation(String donorName, DonorTypeEnum donorType, BigDecimal amount, LocalDate donationDate, String category, StatusEnum status) {
        this.donorName = donorName;
        this.donorType = donorType;
        this.amount = amount;
        this.donationDate = donationDate;
        this.category = category;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public void setDonorType(DonorTypeEnum donorType) {
        this.donorType = donorType;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Long getId() {
        return id;
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

    public StatusEnum getStatus() {
        return status;
    }
}
