package com.clinic.manager.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BillServiceEntity.class)
public abstract class BillServiceEntity_ {

	public static volatile SingularAttribute<BillServiceEntity, String> idMedicine;
	public static volatile SingularAttribute<BillServiceEntity, Long> idUser;
	public static volatile SingularAttribute<BillServiceEntity, Integer> price;
	public static volatile SingularAttribute<BillServiceEntity, String> diagnose;
	public static volatile SingularAttribute<BillServiceEntity, Long> idPatient;
	public static volatile SingularAttribute<BillServiceEntity, Long> id;
	public static volatile SingularAttribute<BillServiceEntity, Long> day;
	public static volatile SingularAttribute<BillServiceEntity, Integer> statusPay;
	public static volatile SingularAttribute<BillServiceEntity, Integer> status;

	public static final String ID_MEDICINE = "idMedicine";
	public static final String ID_USER = "idUser";
	public static final String PRICE = "price";
	public static final String DIAGNOSE = "diagnose";
	public static final String ID_PATIENT = "idPatient";
	public static final String ID = "id";
	public static final String DAY = "day";
	public static final String STATUS_PAY = "statusPay";
	public static final String STATUS = "status";

}

