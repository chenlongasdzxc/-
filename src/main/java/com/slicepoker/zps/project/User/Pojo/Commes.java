package com.slicepoker.zps.project.User.Pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Zps
 **/
@Data
public class Commes<T> implements Serializable {

    //定义错误或成功信息
    private String message;
    //错误或成功代码
    private String code;
    //对象数据
    private T data;

    //空方法
    private Commes(){

    }

    /*private Commes(T data){ this("OK","200",data); }*/

    private Commes(String code, String message){ this(message,code,null); }

    private Commes(String message, String code, T data) {
        this.message=message;
        this.code=code;
        this.data=data;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message=message; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code=code; }

    public T getData() { return data; }

    public void setData(T data) { this.data=data; }

    //设置code，message方法
    public static <T> Commes<T> errorMes(String code,String message){ return new Commes(code,message); }

    //设置code，message，data方法
    public static <T> Commes<T> errorMes(String code,String message,T data){ return new Commes(code,message,data); }

    //成功
    public static <T> Commes<T> success(T data){return  new Commes("success","200",data);}

    //成功信息
    public static <T> Commes<T> successMes(){ return new Commes("200","success");}

    //参数错误方法
    public static Commes badRequestError(){ return new Commes("400","参数错误");}


    public static Commes innerError(String message){ return new Commes("500",message);}
}
