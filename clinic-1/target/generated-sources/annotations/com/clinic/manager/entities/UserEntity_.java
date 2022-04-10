package com.clinic.manager.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserEntity.class)
public abstract class UserEntity_ {

	public static volatile SingularAttribute<UserEntity, String> password;
	public static volatile SingularAttribute<UserEntity, String> address;
	public static volatile SingularAttribute<UserEntity, String> gender;
	public static volatile SingularAttribute<UserEntity, Long> idRole;
	public static volatile SingularAttribute<UserEntity, String> phone;
	public static volatile SingularAttribute<UserEntity, String> name;
	public static volatile SingularAttribute<UserEntity, Long> id;
	public static volatile SingularAttribute<UserEntity, String> email;

	public static final String PASSWORD = "password";
	public static final String ADDRESS = "address";
	public static final String GENDER = "gender";
	public static final String ID_ROLE = "idRole";
	public static final String PHONE = "phone";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String EMAIL = "email";

}

