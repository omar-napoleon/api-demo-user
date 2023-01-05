package com.example.demouser.handler;


import com.example.demouser.dto.ExceptionResponseDto;
import com.example.demouser.exception.CustomException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionCustomHandler {

    private ExceptionResponseDto sendResponse(HttpStatus status, Throwable ex) {
        return new ExceptionResponseDto(status.getReasonPhrase(), ex.getMessage());
    }

    private ResponseEntity<ExceptionResponseDto> sendResponse(HttpStatus status, String ex) {
       return new ResponseEntity<>(new ExceptionResponseDto(status.getReasonPhrase(), ex), status);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ExceptionResponseDto> handleCustomException(CustomException ex) {
        return sendResponse(ex.getHttpStatus(), ex.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponseDto handleInvalidParameterException(RuntimeException ex) {
        return sendResponse(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler({ResourceNotFoundException.class,
            UsernameNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponseDto handleUsernameNotFoundException(RuntimeException ex) {
        return sendResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponseDto handleException(Exception ex) {
        return sendResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponseDto handleException(RuntimeException ex) {
        return sendResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler(value = AuthenticationException.class)
    public ExceptionResponseDto handleAuthenticationExceptions(AuthenticationException ex, HttpServletResponse response) {
        return sendResponse(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponseDto handleRuntimeException(Throwable ex) {

        return sendResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponseDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        // Get the error messages for invalid fields
        List<FieldError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldError(fieldError.getField(),fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        List<ExceptionResponseDto> messages = errors.stream()
                .map(error ->
                        new ExceptionResponseDto(
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                error.getDefaultMessage())
                )
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages);
    }
}
