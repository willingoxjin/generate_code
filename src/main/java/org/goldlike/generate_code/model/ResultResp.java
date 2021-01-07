package org.goldlike.generate_code.model;

/**
 * @author Jin.Nie
 * @since 2020-12-25
 */
public class ResultResp {

    private Integer status;
    private String msg;
    private Object obj;

    public ResultResp() {
    }


    public ResultResp(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public static ResultResp ok(String msg, Object obj) {
        return new ResultResp(200, msg, obj);
    }


    public static ResultResp ok(String msg) {
        return new ResultResp(200, msg, null);
    }


    public static ResultResp error(String msg,Object obj) {
        return new ResultResp(500, msg, obj);
    }


    public static ResultResp error(String msg) {
        return new ResultResp(500, msg, null);
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
