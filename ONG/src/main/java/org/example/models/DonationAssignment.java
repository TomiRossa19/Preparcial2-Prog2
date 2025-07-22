package org.example.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "donation_assignment")
public class DonationAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donation_id")
    private Donation donationID;

    @Column(name = "notes")
    private String notes;

    @Column(name = "assigned_date")
    private LocalDate assignedDate;

    public DonationAssignment(){

    }

    public DonationAssignment(Donation donationID, String notes, LocalDate assignedDate) {
        this.donationID = donationID;
        this.notes = notes;
        this.assignedDate = assignedDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDonationID(Donation donationID) {
        this.donationID = donationID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Long getId() {
        return id;
    }

    public Donation getDonationID() {
        return donationID;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }
}
