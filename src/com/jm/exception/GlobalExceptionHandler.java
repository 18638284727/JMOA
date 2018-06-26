package com.jm.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
public class GlobalExceptionHandler{

	@ResponseBody
	@ExceptionHandler({RuntimeException.class})
	public Map<String, Object> globalExceptionHandler(Exception exception)
	{
		Map<String, Object> exceptions = new HashMap<String, Object>();
		System.out.println("GlobalExceptionHandler:" + exception.getClass());
		System.out.println("GlobalExceptionHandler:" + exception.getMessage());
		exceptions.put("classs", exception.getClass());
		exceptions.put("message", exception.getMessage());
		return exceptions;
	}	
}
