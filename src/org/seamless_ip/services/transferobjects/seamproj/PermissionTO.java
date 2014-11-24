/*  
 * PermissionTO.java; Jun 5, 2009
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
package org.seamless_ip.services.transferobjects.seamproj;

import java.io.Serializable;

import org.seamless_ip.ontologies.seamproj.Permission;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;

/**
 * Warning: This class is not auto-generated!
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
@SuppressWarnings("serial")
public class PermissionTO extends AbstractTO
		implements TO<PermissionTO, Permission>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwritePermissionTO;
	
	private String right;
	private String task;

	public PermissionTO() {
		super();
		right = "New Permission Right";
		task = "New Permission Task";
	}

	public static Permission createDBInstance() {
		return new Permission();
	}

	public static Permission createDBInstance(PermissionTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(Permission dbItem) {
		// void
	}

	public PermissionTO assignFrom(Permission o) {
		if (o != null) {
			setId(o.getId());
			setRight(o.getRight());
			setTask(o.getTask());
		}
		return this;
	}

	public Permission assignTo(Permission o) {
		Permission result = o;
		if ((o != null) && (!equalsTo(o))) {
			result = new Permission();
			result.setId(getId());
			result.setRight(getRight());
			result.setTask(getTask());
		}
		return result;
	}

	public boolean equalsTo(Permission o) {
		if (o == null)
			return false;

		PermissionTO to = new PermissionTO().assignFrom(o);
		return to.equals(this);
	}

	public PermissionTO clone() {
		PermissionTO clone = new PermissionTO();
		clone.setId(getId());
		clone.setRight(getRight());
		clone.setTask(getTask());
		return clone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PermissionTO))
			return false;

		PermissionTO that = (PermissionTO) o;

		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (right != null ? !right.equals(that.right) : that.right != null)
			return false;
		if (task != null ? !task.equals(that.task) : that.task != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (right != null ? right.hashCode() : 0);
		result = 31 * result + (task != null ? task.hashCode() : 0);
		return result;
	}

	public String getRight() {
		return right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
}
