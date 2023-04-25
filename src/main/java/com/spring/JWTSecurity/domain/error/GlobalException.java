package com.spring.JWTSecurity.domain.error;

import com.spring.JWTSecurity.app.responses.BaseResponse;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<Object>> handlerResourceNotFound(ResourceNotFoundException exception) {

        BaseResponse<Object> baseResponse =
                BaseResponse.builder()
                        .code(String.format(HttpStatus.NOT_FOUND.toString()))
                        .data(exception.getLocalizedMessage())
                        .success(false)
                        .build();

        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //Handle Token Refresh Exception
    @ExceptionHandler(value = TokenRefreshException.class)
    public ResponseEntity<BaseResponse<Object>> handleTokenRefreshException(TokenRefreshException exception) {

        BaseResponse<Object> baseResponse =
                BaseResponse.builder()
                        .code(String.format(HttpStatus.FORBIDDEN.toString()))
                        .data(exception.getLocalizedMessage())
                        .success(false)
                        .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.FORBIDDEN);
    }
}
