package org.example.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import org.example.dto.AddDonationDTO;
import org.example.dto.AssignDonationDTO;
import org.example.dto.GetTotalResultDTO;
import org.example.enums.StatusEnum;
import org.example.models.Donation;
import org.example.models.DonationAssignment;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class Logica {
    private static Logica instance;

    private Logica(){

    }

    public Logica getInstance(){
        if (instance == null){
            return new Logica();
        }
        return instance;
    }

    public boolean addDonation(AddDonationDTO addDonationDTO){
        try(Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            Donation donation = new Donation(addDonationDTO.getDonorName(), addDonationDTO.getDonorType(), addDonationDTO.getAmount(), addDonationDTO.getDonationDate(), addDonationDTO.getCategory(), StatusEnum.RECEIVED);
            session.persist(donation);
            session.getTransaction().commit();
            return true;
        }
    }

    public boolean assignDonation(AssignDonationDTO assignDonationDTO){
        try(Session session = HibernateUtil.getSession()){
            Donation donation = session.get(Donation.class, assignDonationDTO.getDonationID());
            if (donation == null){
                System.out.println("La donación no existe");
                return false;
            }else if (donation.getStatus() == StatusEnum.RECEIVED){
                DonationAssignment donationAssignment = new DonationAssignment(assignDonationDTO.getDonationID(), assignDonationDTO.getNotes(), assignDonationDTO.getAssignationDate());
                session.beginTransaction();
                session.persist(donationAssignment);
                session.getTransaction().commit();
                return true;
            }else{
                System.out.println("La donación ya fue asignada");
                return false;
            }
        }
    }

    public List<GetTotalResultDTO> getTotal(){
        try(Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Donation> cq = cb.createQuery(Donation.class);
            Root<Donation> root = cq.from(Donation.class);
            cq.groupBy(root.get("donor_type"));
            cq.orderBy(cb.desc(root.get("total_amount")));

            List<Donation> donations = session.createQuery(cq).getResultList();

            for (){

            }
        }
    }
}
