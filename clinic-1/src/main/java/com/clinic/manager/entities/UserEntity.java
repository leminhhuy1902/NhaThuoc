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
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @Column(name = "id_role")
        private Long idRole;


        @Column(name = "name")
        private String name;


        @Column(name = "gender")
        private String gender;


        @Column(name = "address")
        private String address;


        @Column(name = "phone")
        private String phone;


        @Column(name = "email")
        private String email;


        @Column(name = "password")
        private String password;




}
