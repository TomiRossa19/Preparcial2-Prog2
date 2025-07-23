package org.example.services;

import jakarta.persistence.Table;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.example.dto.AddDonationDTO;
import org.example.dto.AssignDonationDTO;
import org.example.dto.GetCollectionDTO;
import org.example.dto.GetTotalResultDTO;
import org.example.enums.DonorTypeEnum;
import org.example.enums.StatusEnum;
import org.example.models.Donation;
import org.example.models.DonationAssignment;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.ArrayList;
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
            CriteriaQuery<Tuple> cq = cb.createTupleQuery();
            Root<Donation> root = cq.from(Donation.class);
            cq.groupBy(root.get("donorType"));
            cq.orderBy(cb.desc(root.get("amount")));
            cq.multiselect(
                    root.get("donorType").alias("donorType"),
                    cb.count(root).alias("donationCount"),
                    cb.sum(root.get("amount")).alias("totalAmount")
            );
            List<Tuple> groupedDonations = session.createQuery(cq).getResultList();
            List<GetTotalResultDTO> getTotalResultDTOS = new ArrayList<>();

            for (Tuple donation : groupedDonations){
                GetTotalResultDTO getTotalResultDTO = new GetTotalResultDTO((DonorTypeEnum) donation.get("donorType"), (Long) donation.get("donationCount"), (BigDecimal) donation.get("totalAmount"));
                getTotalResultDTOS.add(getTotalResultDTO);
            }

            return getTotalResultDTOS;
        }
    }
    public List<GetCollectionDTO> getCollection(){
        try(Session session = HibernateUtil.getSession()){
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> cq = cb.createTupleQuery();
            Root<Donation> root = cq.from(Donation.class);
            cq.groupBy(root.get("category"));

            Expression<Long> receivedCount = cb.sum(cb.<Long>selectCase().when(cb.equal(root.get("status"), StatusEnum.RECEIVED), 1L).otherwise(0L));
            Expression<Long> assignedCount = cb.sum(cb.<Long>selectCase().when(cb.equal(root.get("status"), StatusEnum.ASSIGNED), 1L).otherwise(0L));
            Expression<BigDecimal> totalAmount = cb.sum(root.get("amount"));

            cq.multiselect(root.get("category").alias("category"), receivedCount.alias("receivedCount"), assignedCount.alias("assignedCount"), totalAmount.alias("totalAmount"));

            cq.orderBy(cb.desc(totalAmount));

            List<Tuple> tuples = session.createQuery(cq).getResultList();

            List<GetCollectionDTO> getCollectionDTOS = new ArrayList<>();

            for (Tuple tuple : tuples){
                GetCollectionDTO getCollectionDTO = new GetCollectionDTO((String) tuple.get("category"), (Long) tuple.get("receivedCount"),  (Long) tuple.get("assignedCount"), (BigDecimal) tuple.get("totalAmount"));
                getCollectionDTOS.add(getCollectionDTO);
            }

            return getCollectionDTOS;
        }
    }
}
