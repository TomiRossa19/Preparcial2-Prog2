package org.example.dto;

import java.math.BigDecimal;

public class GetCollectionDTO {
    public String category;
    public Long receivedDonations;
    public Long assignedDonations;
    public BigDecimal totalCollected;

    public GetCollectionDTO(String category, Long receivedDonations, Long assignedDonations, BigDecimal totalCollected) {
        this.category = category;
        this.receivedDonations = receivedDonations;
        this.assignedDonations = assignedDonations;
        this.totalCollected = totalCollected;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setReceivedDonations(Long receivedDonations) {
        this.receivedDonations = receivedDonations;
    }

    public void setAssignedDonations(Long assignedDonations) {
        this.assignedDonations = assignedDonations;
    }

    public void setTotalCollected(BigDecimal totalCollected) {
        this.totalCollected = totalCollected;
    }

    public String getCategory() {
        return category;
    }

    public Long getReceivedDonations() {
        return receivedDonations;
    }

    public Long getAssignedDonations() {
        return assignedDonations;
    }

    public BigDecimal getTotalCollected() {
        return totalCollected;
    }
}
