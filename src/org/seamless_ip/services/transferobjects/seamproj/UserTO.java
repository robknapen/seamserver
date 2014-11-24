/*  
 * UserTO.java; Jun 5, 2009
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
import java.util.HashSet;
import java.util.Set;

import org.seamless_ip.ontologies.seamproj.IRole;
import org.seamless_ip.ontologies.seamproj.Project;
import org.seamless_ip.ontologies.seamproj.User;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;

/**
 * Transfer object for User related data.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
@SuppressWarnings({"serial", "unchecked"})
public class UserTO extends AbstractTO
		implements TO<UserTO, User>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteUserTO;
	
	private String accountName;
	private String password;
	private String endDate;
	private String firstName;
	private String lastName;
	private String email;
	private String institute;
	private IRoleListTO roleListTO;

	// Not supported yet:
	// Image

	public UserTO() {
		super();

		// leave null for security reasons!
		accountName = null;
		password = null;
		endDate = null;
		roleListTO = new IRoleListTO();

		firstName = "New User First Name";
		lastName = "New User Last Name";
		email = "";
		institute = "";

	}

	public static User createDBInstance() {
		User dbItem = new User();
		dbItem.setRoles(new HashSet<IRole>());
		// dbItem.setImage(ImageTO.createDBInstance());
		return dbItem;
	}

	public static User createDBInstance(UserTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(User dbItem) {
		if (dbItem != null) {
			dbItem.setRoles(null);
			dbItem.setImage(null);
		}
	}

	public UserTO assignFrom(User o) {
		if (o != null) {
			setId(o.getId());
			setAccountName(o.getAccountName());
			setPassword(o.getPassword());
			setEndDate(o.getEndDate());
			setFirstName(o.getFirstName());
			setLastName(o.getLastName());
			setEmail(o.getEmail());
			setInstitute(o.getInstitute());
			roleListTO.assignFrom(o.getRoles());

		}
		return this;
	}

	public User assignTo(User target) {

		if (target != null) {
			Set<IRole> roles;
			target.setId(id);
			target.setEndDate(endDate);
			// TODO: manage correctly reference to interface, following code
			// give error: target.setRoles(_roles .assignTo(target.getRoles()));
			target.setInstitute(institute);
			target.setAccountName(accountName);
			target.setPassword(password);
			target.setLastName(lastName);
			target.setFirstName(firstName);
			target.setEmail(email);
			// assign IRoleTO instances in roleListTO to Role instances in roles
			roles = roleListTO.assignTo(target.getRoles());
			target.setRoles(roles);
			// target.setImage(_image .assignTo(target.getImage()));
		}
		return target;
	}

	public User assignTO(User target, Set<Project> projectsOfUser) {
		if (target != null) {
			Set<IRole> roles;
			target.setId(id);
			target.setEndDate(endDate);
			// TODO: manage correctly reference to interface, following code
			// give error: target.setRoles(_roles .assignTo(target.getRoles()));
			target.setInstitute(institute);
			target.setAccountName(accountName);
			target.setPassword(password);
			target.setLastName(lastName);
			target.setFirstName(firstName);
			target.setEmail(email);
			// assign IRoleTO instances in roleListTO to Role instances in roles
			roles = roleListTO.assignTo(target.getRoles(), projectsOfUser);
			target.setRoles(roles);
			// target.setImage(_image .assignTo(target.getImage()));
		}
		return target;
	}

	public boolean equalsTo(User o) {
		if (o == null)
			return false;

		UserTO to = new UserTO().assignFrom(o);
		// changed by Michiel Rop
		return to.equals(this);
	}

	public UserTO clone() {
		UserTO to = new UserTO();
		to.setId(getId());
		to.setAccountName(getAccountName());
		to.setPassword(getPassword());
		to.setEndDate(getEndDate());
		to.setFirstName(getFirstName());
		to.setLastName(getLastName());
		to.setEmail(getEmail());
		to.setInstitute(getInstitute());
		return to;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UserTO))
			return false;

		UserTO userTO = (UserTO) o;

		if (accountName != null ? !accountName.equals(userTO.accountName)
				: userTO.accountName != null)
			return false;
		if (email != null ? !email.equals(userTO.email) : userTO.email != null)
			return false;
		if (endDate != null ? !endDate.equals(userTO.endDate)
				: userTO.endDate != null)
			return false;
		if (firstName != null ? !firstName.equals(userTO.firstName)
				: userTO.firstName != null)
			return false;
		if (id != null ? !id.equals(userTO.id) : userTO.id != null)
			return false;
		if (institute != null ? !institute.equals(userTO.institute)
				: userTO.institute != null)
			return false;
		if (lastName != null ? !lastName.equals(userTO.lastName)
				: userTO.lastName != null)
			return false;
		if (password != null ? !password.equals(userTO.password)
				: userTO.password != null)
			return false;

		// FIXME: Can not compare on roles because of ontology error...
		// if (roles != null ? !roles.equals(userTO.roles) : userTO.roles !=
		// null)
		// return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result
				+ (accountName != null ? accountName.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (institute != null ? institute.hashCode() : 0);

		// FIXME: Can not compare on roles because of ontology error...
		// result = 31 * result + (roles != null ? roles.hashCode() : 0);

		return result;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public void setRoles(IRoleListTO iRoleListTO) {
		this.roleListTO = iRoleListTO;
	}

	public IRoleListTO getRoles() {
		return this.roleListTO;
	}

}
