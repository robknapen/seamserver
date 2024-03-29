/* gromit: ApplicationRoleTO .java
 * ==============================================================================
 * (This code was generated by a tool)
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
import java.lang.Long;
import java.lang.String;
import java.util.HashSet;
import org.seamless_ip.ontologies.seamproj.ApplicationRole;
import org.seamless_ip.ontologies.seamproj.Permission;
import org.seamless_ip.services.transferobjects.seamproj.IRoleTO;
import org.seamless_ip.services.transferobjects.seamproj.PermissionListTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.seamproj.ApplicationRole", readonly=true, used=true)
@SuppressWarnings("serial")
public class ApplicationRoleTO extends AbstractTO implements IRoleTO<ApplicationRoleTO, ApplicationRole>, Serializable, Cloneable 	
{
	private String _title;		
	private PermissionListTO _permissions;		
	private Long _id;		



	public ApplicationRoleTO()
	{
		super();
		_permissions = new PermissionListTO();
	}

	public static ApplicationRole createDBInstance()
	{
		ApplicationRole dbItem = new ApplicationRole();
		dbItem.setPermissions(new HashSet<Permission>());
		return dbItem;
	}


	public static ApplicationRole createDBInstance(ApplicationRoleTO toItem)
	{
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}
	
    public static void releaseDBInstance(ApplicationRole dbItem)
    {
        if (dbItem != null) {
            dbItem.setPermissions(null);
        }
    }

    public ApplicationRoleTO assignFrom(ApplicationRole target)	
	{
		if (target != null)
		{
			try {
					_id = target.getId();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
				_permissions .assignFrom(target.getPermissions());
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}		
			try {
					_title = target.getTitle();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
		}
		return this;
	}
    
    public ApplicationRole assignTo(ApplicationRole target)
    {
        if (target != null) 
        {
			target.setPermissions(_permissions .assignTo(target.getPermissions()));
			target.setId(_id);			
			target.setTitle(_title);			
        }
        return target;
    }	
    
    public boolean equalsTo(ApplicationRole target)
    {
        if (target == null)
            return false;

        ApplicationRoleTO targetTO = new ApplicationRoleTO().assignFrom(target);
        return targetTO.equals(this);
    }
    
    @Override
    public ApplicationRoleTO clone()
    {
    	ApplicationRoleTO clone = new ApplicationRoleTO();
		clone.setId(_id);
		clone.setPermissions(_permissions);
		clone.setTitle(_title);
        return clone;
    }    
    
    @Override
    public int hashCode()
    {
        int result = 0;
		result = 31 * result + (_title != null ? _title .hashCode() : 0);
		result = 31 * result + (_permissions != null ? _permissions .hashCode() : 0);
		result = 31 * result + (_id != null ? _id .hashCode() : 0);
        return result;
    }    
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ApplicationRoleTO)) return false;

        ApplicationRoleTO classTO = (ApplicationRoleTO) o;

		if (_title != null ? !_title .equals(classTO._title) : classTO._title != null)
			return false;
		if (_permissions != null ? !_permissions .equals(classTO._permissions) : classTO._permissions != null)
			return false;
		if (_id != null ? !_id .equals(classTO._id) : classTO._id != null)
			return false;
        
        return true;
    }
    
    public Long getId()
    {
		return _id;
    }
    public PermissionListTO getPermissions()
    {
		return _permissions;
    }
    public String getTitle()
    {
		return _title;
    }
    public void setId(Long value)
    {
		_id = value;
    }
    public void setPermissions(PermissionListTO value)
    {
		_permissions = value;
    }
    public void setTitle(String value)
    {
		_title = value;
    }
}