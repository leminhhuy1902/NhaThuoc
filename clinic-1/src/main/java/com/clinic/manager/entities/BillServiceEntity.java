package com.clinic.manager.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "billservice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillServiceEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "id_medicine")
        private String idMedicine;

        @Column(name = "id_user")
        private Long idUser;

        @Column(name = "id_patient")
        private Long idPatient;

        @Column(name = "day")
        private Long day;

        @Column(name = "price")
        private Integer price;

        @Column(name = "status")
        private Integer status;

        @Column(name = "status_pay")
        private Integer statusPay;

        @Column(name = "diagnose")
        private String diagnose;

}
