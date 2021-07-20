package com.laptrinhjavaweb.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtil {

	@SuppressWarnings("deprecation")
	public  <T> T toModel(Class<T> clazz, HttpServletRequest request) {
		
		T object =null;
			try {
				object=clazz.newInstance();
				BeanUtils.populate(object, request.getParameterMap()); 
				// move the data (key, value) in request and populate it to Object
				// just like convert json to object    
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.print(e.getMessage());
		}
		
		return object;
		
		
	}
}

