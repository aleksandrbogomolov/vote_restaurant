package com.aleksandrbogomolov.vote_restaurant.controllers;

import com.aleksandrbogomolov.vote_restaurant.util.exception.ErrorInfo;
import com.aleksandrbogomolov.vote_restaurant.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

@SuppressWarnings("OverloadedMethodsWithSameNumberOfParameters")
@ControllerAdvice(annotations = RestController.class)
public interface ExceptionInfoHandler {

    Logger LOGGER = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    default ErrorInfo handleError(HttpServletRequest req, NotFoundException e) {
        return logAndGetErrorInfo(req, e, false);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    default ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException exc) {
        LOGGER.error("BindException at request " + req.getRequestURL());
        return logAndGetErrorInfo(req, new DataIntegrityViolationException("User with this email already present in application"), true);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    default ErrorInfo unprocessable(HttpServletRequest req, BindingResult result) {
        LOGGER.error("BindException at request " + req.getRequestURL());
        StringBuilder sb = new StringBuilder();
        result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("<br>"));
        return logAndGetErrorInfo(req, new ValidationException(sb.toString()), false);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @Order(Ordered.LOWEST_PRECEDENCE)
    default ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true);
    }

    default ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException) {
        if (logException) {
            LOGGER.error("Exception at request " + req.getRequestURL(), e);
        } else {
            LOGGER.warn("Exception at request " + req.getRequestURL() + ": " + e.toString());
        }
        return new ErrorInfo(req.getRequestURL(), e);
    }
}
