package com.skyline.common.exception;

import com.skyline.common.constants.message.MessageEnum;
import com.skyline.common.page.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：统一异常处理
 * @author skyline
 * @date 2017-08-27
 */
@ControllerAdvice
public class ExceptionCommonHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultInfo handler(Exception e){
        if(e instanceof SimpleServiceException){
            SimpleServiceException simple = (SimpleServiceException) e;
            return ResultInfo.failure(simple.getCode(),simple.getErrMessage());
        }else{
            return ResultInfo.response(MessageEnum.SYSTEM_EXCEPTION);
        }
    }

}
