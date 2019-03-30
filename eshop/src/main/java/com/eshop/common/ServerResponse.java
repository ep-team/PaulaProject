package com.eshop.common;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)//不要null.没有null
//保证序列化json的时候,如果是null的对象,key也会消失
public class ServerResponse<T> implements Serializable {
	private int status;
	private String msg;
	
	//在返回的时候可以指定泛型指定的内容,也可以强制指定泛型内容
	private T data;
	
	//构造器的权限都为private
	
	private ServerResponse(int status) {
		this.status = status;
	}
	
	/*
	 * 当第二个参数为非String的时候调用 ServiceResponse(int status, T data)
	 * 如果第二个参数是String的时候调用 ServiceResponse(int status, String msg)
	 */
	private ServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}
	
	private ServerResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	private ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	//不让isSuccess加载到json里
	@JsonIgnore//使之不在json序列化结果当中
	public boolean isSuccess() {
		return this.status == ResponseCode.SUCCESS.getCode();
	}

	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}
	
	public static <T> ServerResponse<T> createBySuccess() {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
	}
	
	public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
	}
	
	public static <T> ServerResponse<T> createBySuccess(T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
	}
	
	public static <T> ServerResponse<T> createBySuccess(String msg,T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
	}
	
	public static <T> ServerResponse<T> createByError() {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
	}
	
	public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
	}
	
	public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
		return new ServerResponse<T>(errorCode,errorMessage);
	}
}
