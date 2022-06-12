package com.ams.config;

import com.ams.abnormal.AmsException;
import com.ams.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.connection.PoolException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * @ProjectName: AMS
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理
 * @Date: 2022/5/5 9:22
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 处理所有参数校验时抛出的异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public JsonResult handleBindException(BindException ex) {
        StringBuilder msg=new StringBuilder();
        ex.getAllErrors().forEach(x->{
            msg.append(x.getDefaultMessage()).append(",");
        });
        return JsonResult.error(msg.toString().substring(0,msg.length()-1));
    }
    /**
     * 处理业务异常处理
     */
    @ExceptionHandler(AmsException.class)
    @ResponseBody
    public JsonResult handleRRException(AmsException e) {
        return JsonResult.error(e.getMessage());
    }



    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public JsonResult handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return JsonResult.error("路径不存在，请检查路径是否正确",404);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public JsonResult handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return JsonResult.error("数据库中已存在该记录");
    }



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return JsonResult.error("操作失败，" + e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public JsonResult HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        StringBuffer sb = new StringBuffer();
        sb.append("不支持");
        sb.append(e.getMethod());
        sb.append("请求方法，");
        sb.append("支持以下");
        String[] methods = e.getSupportedMethods();
        if (methods != null) {
            for (String str : methods) {
                sb.append(str);
                sb.append("、");
            }
        }
        log.error(sb.toString(), e);
        return JsonResult.error( sb.toString(),405);
    }

    /**
     * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public JsonResult handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error(e.getMessage(), e);
        return JsonResult.error("文件大小超出10MB限制, 请压缩或降低文件质量! ");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public JsonResult handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        return JsonResult.error("字段太长,超出数据库字段的长度");
    }

    @ExceptionHandler(PoolException.class)
    @ResponseBody
    public JsonResult handlePoolException(PoolException e) {
        log.error(e.getMessage(), e);
        return JsonResult.error("Redis 连接异常!");
    }
}