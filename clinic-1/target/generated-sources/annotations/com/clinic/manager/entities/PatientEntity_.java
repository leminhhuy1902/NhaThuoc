package com.clinic.manager.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PatientEntity.class)
public abstract class PatientEntity_ {

	public static volatile SingularAttribute<PatientEntity, String> address;
	public static volatile SingularAttribute<PatientEntity, String> gender;
	public static volatile SingularAttribute<PatientEntity, String> phone;
	public static volatile SingularAttribute<PatientEntity, String> name;
	public static volatile SingularAttribute<PatientEntity, Long> id;
	public static volatile SingularAttribute<PatientEntity, Integer> age;

	public static final String ADDRESS = "address";
	public static final String GENDER = "gender";
	public static final String PHONE = "phone";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String AGE = "age";

}

