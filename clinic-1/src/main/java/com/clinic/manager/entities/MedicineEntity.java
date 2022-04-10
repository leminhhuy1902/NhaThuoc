package com.clinic.manager.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "medicine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineEntity {


        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        private String id;


        @Column(name = "id_unit")
        private Long idUnit;


        @Column(name = "name")
        private String name;


        @Column(name = "hsd")
        private String HSD;


        @Column(name = "price")
        private Integer price;




}
