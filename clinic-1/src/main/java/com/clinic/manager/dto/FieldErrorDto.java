package com.clinic.manager.dto;

import org.zalando.problem.violations.Violation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO to describe an invalid field input.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorDto {
	private String field;
	private String messageCode;

	/**
	 * Map {@link org.zalando.problem.violations.Violation} TO {@link FieldErrorDto}
	 * @param violation The violation to map from.
	 * @return The {@link FieldErrorDto} mapped to.
	 */
	public static FieldErrorDto map(Violation violation) {
		return new FieldErrorDto(violation.getField(), violation.getMessage());
	}
}
