package com.zw.smart.account.handler;

import com.zw.smart.account.common.result.ResponseEntity;
import com.zw.smart.account.common.result.ResponseStatus;
import com.zw.smart.account.execption.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局统一的异常处理
 *
 * @author zhangwei
 */
//@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.error(ResponseStatus.SYSTEM_INNER_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity handlerServiceException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.error(ResponseStatus.BUSINESS_UNKNOW_ERROR);
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity handlerBindException(BindException e) {
            List<ObjectError> errors=e.getAllErrors();
        return ResponseEntity.error(ResponseStatus.SYSTEM_INNER_ERROR,errors);
    }
}
