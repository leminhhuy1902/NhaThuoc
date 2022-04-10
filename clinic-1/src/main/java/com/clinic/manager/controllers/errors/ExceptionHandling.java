
package com.clinic.manager.controllers.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.violations.ConstraintViolationProblem;
import io.swagger.annotations.Api;

import com.clinic.manager.exception.NotFoundException;
import com.clinic.manager.dto.FieldErrorDto;
import com.clinic.manager.dto.enums.ResponseType;

/**
 * Handler for general exceptions.
 */
@ControllerAdvice
@Api( tags = "Error")
public class ExceptionHandling implements ProblemHandling {

    public static final String RESPONS_BODY_FEILDS_ERROR_FIELD = "fieldErrors";

    /**
     * Process a problem.
     * @param entity contains the original response for of the problem.
     * @param request 
     * @return processed problem response.
     */
    @Override
    public ResponseEntity<Problem> process(ResponseEntity<Problem> entity, NativeWebRequest request) {
        if (entity == null) {
            return null;
        }

        Problem problem = entity.getBody();

        if (!(problem instanceof ConstraintViolationProblem || problem instanceof NotFoundException
                || problem instanceof DefaultProblem)) {
            return entity;
        }

        ProblemBuilder builder = Problem.builder();

        // Problem throw from validate by @Valid annotation
        if (problem instanceof ConstraintViolationProblem) {

            List<FieldErrorDto> fieldErrors = ((ConstraintViolationProblem) problem).getViolations().stream()
                    .map(FieldErrorDto::map).collect(Collectors.toList());
            rebuildProblem(builder, ResponseType.BAD_REQUEST);
            builder.with(RESPONS_BODY_FEILDS_ERROR_FIELD, fieldErrors);
        } else if (problem instanceof NotFoundException) {
            builder.withDetail(problem.getDetail());
            builder.withTitle(ResponseType.NOT_FOUND.getMessage());
            builder.withStatus(Status.NOT_FOUND);
        } else {
            // Unknown error throw in runtime
            ResponseType type = ResponseType.getUnhanldeType(problem.getStatus().getStatusCode());
            rebuildProblem(builder, type);
        }

        return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
    }

    /**
     * Rebuild a problem with a builder and response type.
     * @param builder The builder to use.
     * @param responseType the response type.
     */
    private void rebuildProblem(ProblemBuilder builder, ResponseType responseType) {
        builder.withDetail(responseType.getMessage());
    }
}
