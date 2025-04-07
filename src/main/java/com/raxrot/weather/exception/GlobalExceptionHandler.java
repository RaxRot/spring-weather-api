package com.raxrot.weather.exception;

import com.raxrot.weather.dto.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto handleGenericException(HttpServletRequest request,Exception ex) {
        return ErrorDto.builder()
                .timestamp(new Date())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errors(Collections.singletonList(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()))
                .path(request.getServletPath())
                .build();
    }

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorDto handleNotFound(HttpServletRequest request, LocationNotFoundException ex) {
        return ErrorDto.builder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .errors(Collections.singletonList(ex.getMessage()))
                .path(request.getServletPath())
                .build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto handleValidationException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return ErrorDto.builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .path(request.getServletPath())
                .build();
    }

    @ExceptionHandler(DuplicateLocationException.class)
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    @ResponseBody
    public ErrorDto handleDuplicate(HttpServletRequest request, DuplicateLocationException ex) {
        return ErrorDto.builder()
                .timestamp(new Date())
                .status(HttpStatus.CONFLICT.value())
                .errors(Collections.singletonList(ex.getMessage()))
                .path(request.getServletPath())
                .build();
    }
}
