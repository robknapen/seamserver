/*  
 * LoggingInterceptor.java; Jun 5, 2009
 * ==============================================================================
 * This work has been carried out as part of the SEAMLESS Integrated Framework
 * project, EU 6th Framework Programme, contract no. 010036-2 and/or as part
 * of the SEAMLESS association.
 *
 * Copyright (c) 2009 The SEAMLESS Association.
 *
 * For more information: http://www.seamlessassociation.org;
 * email: info@seamless-if.org
 *
 * The contents of this file is subject to the SEAMLESS Association License for 
 * software infrastructure and model components Version 1.1 (the "License");
 * you may not use this file except in compliance with the License. You may 
 * obtain a copy of the License at http://www.seamlessassociation.org/License.htm
 * 
 * Software distributed under the License is distributed on an "AS IS"  basis, 
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for 
 * the specific governing rights and limitations.
 *
 * The Initial Developers of the Original Code are:
 * - Ioannis Athanasiadis; IDSIA Dalle Molle Institute for Artificial Intelligence
 * - Benny Johnsson; Lund University
 * - Rob Knapen; Alterra, Wageningen UR
 * - Hongtao Li; IDSIA Dalle Molle Institute for Artificial Intelligence
 * - Michiel Rop; Alterra, Wageningen UR / ilionX
 * - Lorenzo Ruinelli; IDSIA Dalle Molle Institute for Artificial Intelligence
 * ================================================================================
 * Contributor(s): N/A
 * ================================================================================
 */
package org.seamless_ip.services.dao.utils;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * Simple Spring Interceptor class to do basic logging.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public class LoggingInterceptor implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

	private static final long serialVersionUID = 1L;
	private static Log log = null;

	public LoggingInterceptor() {
		// void
	}

	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		log = LogFactory.getLog(arg2.getClass());
		String msg = "Begin method: " + arg2.getClass().getSimpleName() + "."
				+ arg0.getName();
		log.info(msg);
		System.out.println(new Date().toString() + " - " + msg);
	}

	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		log = LogFactory.getLog(arg3.getClass());
		String msg = "  End method: " + arg3.getClass().getSimpleName() + "."
				+ arg1.getName();
		log.info(msg);
		System.out.println(new Date().toString() + " - " + msg);
	}

	public void afterThrowing(Method m, Object[] args, Object target,
			Throwable ex) {
		log = LogFactory.getLog(target.getClass());
		String msg = "Exception in method: "
				+ target.getClass().getSimpleName() + "." + m.getName()
				+ ". Exception is: " + ex.getMessage();
		log.error(msg);
		System.err.println(new Date().toString() + " - " + msg);
	}

}
