package com.kanavi.response.exception;

import com.kanavi.response.api.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * Created by thl
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseBean handle(ApiException e) {
        if (e.getErrorCode() != null) {
            log.error(e.getMessage());
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.error(e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseBean handleRuntime(RuntimeException e) {
        if (e.getMessage() != null) {
            log.error(e.getMessage());
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseBean handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return ResponseBean.error(e.getMessage());
    }

    @ExceptionHandler(value = ArithmeticException.class)
    public ResponseBean handleNumException(ArithmeticException e) {
        log.error(e.getMessage());
        return ResponseBean.error(e.getMessage());
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseBean handleNullPointException(NullPointerException e) {
        log.error(e.getMessage());
        return ResponseBean.error(e.getMessage());
    }
}
