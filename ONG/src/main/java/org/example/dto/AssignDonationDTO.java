package org.example.dto;

import org.example.models.Donation;

import java.time.LocalDate;

public class AssignDonationDTO {
    public Donation donationID;
    public LocalDate assignationDate;
    public String notes;

    public AssignDonationDTO(Donation donationID, LocalDate assignationDate, String notes) {
        this.donationID = donationID;
        this.assignationDate = assignationDate;
        this.notes = notes;
    }

    public void setDonationID(Donation donationID) {
        this.donationID = donationID;
    }

    public void setAssignationDate(LocalDate assignationDate) {
        this.assignationDate = assignationDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Donation getDonationID() {
        return donationID;
    }

    public LocalDate getAssignationDate() {
        return assignationDate;
    }

    public String getNotes() {
        return notes;
    }
}
