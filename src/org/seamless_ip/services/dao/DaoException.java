/*  
 * DaoException
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

package org.seamless_ip.services.dao;

/**
 * Runtime exception class to distinguish server DAO exceptions from all
 * other possible exceptions.
 * 
 * @author Rob Knapen; Alterra, Wageningen University, The Netherlands
 *
 */
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;


	/**
	 * Creates an exception for the specified cause and message.
	 * 
	 * @param cause
	 * @param message
	 */
	public DaoException(Throwable cause, String message) {
		super(message, cause);
	}
	
	
	/**
	 * Creates an exception for the specified cause and formatted message
	 * based on the given format and arguments.
	 * 
	 * @param cause
	 * @param format
	 * @param args
	 */
	public DaoException(Throwable cause, String format, Object... args) {
		super(String.format(format, args), cause);
	}
	
	
	/**
	 * Creates an exception with a formatted message based on the given
	 * format and arguments.
	 * 
	 * @param format
	 * @param args
	 */
	public DaoException(String format, Object... args) {
		super(String.format(format, args));
	}
	
}
