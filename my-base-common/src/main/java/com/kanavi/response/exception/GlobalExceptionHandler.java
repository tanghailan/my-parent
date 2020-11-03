package com.kanavi.response.exception;

import com.kanavi.response.api.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * Created by thl
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ResponseBean handle(ApiException e) {
        if (e.getErrorCode() != null) {
            log.error(e.getMessage());
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = ArithmeticException.class)
    public ResponseBean handleNumException(ArithmeticException e) {
        log.error(e.getMessage());
        return ResponseBean.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseBean handleNullPointException(NullPointerException e) {
        log.error(e.getMessage());
        return ResponseBean.error(e.getMessage());
    }
}
