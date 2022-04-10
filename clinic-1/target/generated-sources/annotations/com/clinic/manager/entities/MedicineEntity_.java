package com.clinic.manager.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MedicineEntity.class)
public abstract class MedicineEntity_ {

	public static volatile SingularAttribute<MedicineEntity, Integer> price;
	public static volatile SingularAttribute<MedicineEntity, String> name;
	public static volatile SingularAttribute<MedicineEntity, String> HSD;
	public static volatile SingularAttribute<MedicineEntity, Long> idUnit;
	public static volatile SingularAttribute<MedicineEntity, String> id;

	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String H_SD = "HSD";
	public static final String ID_UNIT = "idUnit";
	public static final String ID = "id";

}

