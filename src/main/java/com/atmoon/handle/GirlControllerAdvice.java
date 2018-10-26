package com.atmoon.handle;

import com.atmoon.controller.GirlController;
import com.atmoon.exception.GirlException;
import com.atmoon.utils.ResultUtil;
import com.atmoon.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackageClasses = GirlController.class)
public class GirlControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(GirlException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request,GirlException ex) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(ResultUtil.error(ex.getCode(),ex.getMessage()),status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);

    }
}
