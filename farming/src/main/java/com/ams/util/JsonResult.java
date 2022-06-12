package com.ams.util;


import java.util.ArrayList;
import java.util.List;

public class JsonResult {

    /*	@ApiModelProperty(value = "成功标志：1-成功 0-失败", example = "1")*/
    private int code = 1;  // 是否成功
    /*	@ApiModelProperty(value = "返回消息", example = "操作成功")*/
    private String msg = "操作成功";  // 提示信息
    /*@ApiModelProperty("返回数据")*/
    private Object data;  // 数据


    public JsonResult() {

    }

    public JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }



    public static JsonResult success(Object data, String msg) {
        JsonResult result = new JsonResult();
        result.setCode(1);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static JsonResult success(Object data) {
        JsonResult result = new JsonResult();
        result.setCode(1);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    public static JsonResult success() {
        JsonResult result = new JsonResult();
        result.setCode(1);
        result.setMsg("操作成功");
        return result;
    }

    public static JsonResult error(String msg, Integer code) {
        JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static JsonResult error(String msg) {
        JsonResult result = new JsonResult();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }

    public static JsonResult error() {
        JsonResult result = new JsonResult();
        result.setCode(0);
        result.setMsg("操作失败");
        return result;
    }


    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    public static <T> List<T> castList(Object obj, Class<T> clazz)
    {
        List<T> result = new ArrayList<T>();
        if(obj instanceof List<?>)
        {
            for (Object o : (List<?>) obj)
            {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
