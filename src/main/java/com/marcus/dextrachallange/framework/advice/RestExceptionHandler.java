package com.marcus.dextrachallange.framework.advice;

import com.marcus.dextrachallange.exception.ErrorMessage;
import com.marcus.dextrachallange.exception.HouseNotFoundException;
import com.marcus.dextrachallange.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.NoResultException;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public RestExceptionHandler() {
    }

    @ExceptionHandler({HouseNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleEntityNotFound(HouseNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(MessageUtil.get(ex.getMessage()));
        return buildResponseEntity(Pair.of(errorMessage, HttpStatus.NOT_FOUND));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(getDefaultMessageOfFields(ex)), status);
    }

    @ResponseBody
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorMessage> noResultExceptionHandler(NoResultException e) {
        return new ResponseEntity<>(new ErrorMessage(MessageUtil.get(e.getMessage())), HttpStatus.NOT_FOUND);
    }

    ResponseEntity<ErrorMessage> buildResponseEntity(Pair<ErrorMessage, HttpStatus> errorMessageHttpStatusPair) {
        return new ResponseEntity<>(errorMessageHttpStatusPair.getLeft(), errorMessageHttpStatusPair.getRight());
    }

    private String getDefaultMessageOfFields(MethodArgumentNotValidException ex) {
        return ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList())
                .toString();
    }


}
