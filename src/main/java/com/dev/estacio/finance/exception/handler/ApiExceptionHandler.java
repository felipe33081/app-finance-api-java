package com.dev.estacio.finance.exception.handler;

import com.dev.estacio.finance.exception.ObjectNotNullException;
import com.dev.estacio.finance.exception.UserAlreadyExistsException;
import com.dev.estacio.finance.exception.UserInvalidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.BindException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String INVALIDATION_MESSAGE = "Um ou mais campos são inválidos! Preencha corretamente e tente novamente.";

    private static final String GENERIC_ERROR_MESSAGE = "Aconteceu uma ação inesperada durante o processo da solicitação. " +
            "Tente novamente e, se o problema persistir, entre em contato com o administrador.";

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationException(ex, headers, status, request);
    }

    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidationException(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleValidationException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiError.Field> fields = getFieldsWithError(ex);
        var apiError = ApiError
                .builder()
                .status(status.value())
                .description(INVALIDATION_MESSAGE)
                .detail("generic.validation.exception.detail.message")
                .fields(fields)
                .build();

        headers.setContentType(APPLICATION_PROBLEM_JSON);
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleUncaughtException(Exception ex, WebRequest request) {
        var status = INTERNAL_SERVER_ERROR;
        var apiError = ApiError
                .builder()
                .description(GENERIC_ERROR_MESSAGE)
                .status(status.value())
                .detail(ex.getMessage())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(ObjectNotNullException.class)
    protected ResponseEntity<Object> handleEntityNotFound(ObjectNotNullException ex, WebRequest request) {
        var status = NOT_FOUND;
        var apiError = ApiError.builder()
                .description(ex.getMessage())
                .status(status.value())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(UserInvalidException.class)
    protected ResponseEntity<Object> handleUserInvalidException(UserInvalidException ex, WebRequest request) {
        var status = UNAUTHORIZED;
        var apiError = ApiError.builder()
                .description(ex.getMessage())
                .status(status.value())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        var status = BAD_REQUEST;
        var apiError = ApiError.builder()
                .description(ex.getMessage())
                .status(status.value())
                .build();

        var headers = new HttpHeaders();
        headers.setContentType(APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError.builder()
                .status(status.value())
                .description(status.getReasonPhrase())
                .build();

        headers.setContentType(APPLICATION_PROBLEM_JSON);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private List<ApiError.Field> getFieldsWithError(Exception ex) {
        return ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> ApiError.Field
                        .builder()
                        .name(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .toList();
    }

}
