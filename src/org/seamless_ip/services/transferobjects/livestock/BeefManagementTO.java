/* gromit: BeefManagementTO .java
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
package org.seamless_ip.services.transferobjects.livestock;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import org.seamless_ip.ontologies.livestock.BeefManagement;
import org.seamless_ip.services.transferobjects.farm.NUTSregionTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.livestock.BeefManagement", readonly=false, used=true)
@SuppressWarnings("serial")
public class BeefManagementTO extends AbstractTO implements TO<BeefManagementTO, BeefManagement>, Serializable, Cloneable 	
{
	private String _name;		
	private String _description;		
	private NUTSregionTO _nutsregion;		
	private Long _id;		



	public BeefManagementTO()
	{
		super();
		_nutsregion = new NUTSregionTO();
	}

	public static BeefManagement createDBInstance()
	{
		BeefManagement dbItem = new BeefManagement();
		dbItem.setNUTSRegion(NUTSregionTO.createDBInstance());
		return dbItem;
	}


	public static BeefManagement createDBInstance(BeefManagementTO toItem)
	{
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}
	
    public static void releaseDBInstance(BeefManagement dbItem)
    {
        if (dbItem != null) {
            dbItem.setNUTSRegion(null);
        }
    }

    public BeefManagementTO assignFrom(BeefManagement target)	
	{
		if (target != null)
		{
			try {
					_description = target.getDescription();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_id = target.getId();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
				_nutsregion .assignFrom(target.getNUTSRegion());
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}		
			try {
					_name = target.getName();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
		}
		return this;
	}
    
    public BeefManagement assignTo(BeefManagement target)
    {
        if (target != null) 
        {
			target.setNUTSRegion(_nutsregion .assignTo(target.getNUTSRegion()));
			target.setDescription(_description);			
			target.setId(_id);			
			target.setName(_name);			
        }
        return target;
    }	
    
    public boolean equalsTo(BeefManagement target)
    {
        if (target == null)
            return false;

        BeefManagementTO targetTO = new BeefManagementTO().assignFrom(target);
        return targetTO.equals(this);
    }
    
    @Override
    public BeefManagementTO clone()
    {
    	BeefManagementTO clone = new BeefManagementTO();
		clone.setDescription(_description);
		clone.setId(_id);
		clone.setNUTSRegion(_nutsregion);
		clone.setName(_name);
        return clone;
    }    
    
    @Override
    public int hashCode()
    {
        int result = 0;
		result = 31 * result + (_name != null ? _name .hashCode() : 0);
		result = 31 * result + (_description != null ? _description .hashCode() : 0);
		result = 31 * result + (_nutsregion != null ? _nutsregion .hashCode() : 0);
		result = 31 * result + (_id != null ? _id .hashCode() : 0);
        return result;
    }    
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof BeefManagementTO)) return false;

        BeefManagementTO classTO = (BeefManagementTO) o;

		if (_name != null ? !_name .equals(classTO._name) : classTO._name != null)
			return false;
		if (_description != null ? !_description .equals(classTO._description) : classTO._description != null)
			return false;
		if (_nutsregion != null ? !_nutsregion .equals(classTO._nutsregion) : classTO._nutsregion != null)
			return false;
		if (_id != null ? !_id .equals(classTO._id) : classTO._id != null)
			return false;
        
        return true;
    }
    
    public String getDescription()
    {
		return _description;
    }
    public Long getId()
    {
		return _id;
    }
    public NUTSregionTO getNUTSRegion()
    {
		return _nutsregion;
    }
    public String getName()
    {
		return _name;
    }
    public void setDescription(String value)
    {
		_description = value;
    }
    public void setId(Long value)
    {
		_id = value;
    }
    public void setNUTSRegion(NUTSregionTO value)
    {
		_nutsregion = value;
    }
    public void setName(String value)
    {
		_name = value;
    }
}