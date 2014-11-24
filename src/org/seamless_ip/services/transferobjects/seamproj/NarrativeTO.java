/* gromit: NarrativeTO .java
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
import org.seamless_ip.ontologies.seamproj.Narrative;
import org.seamless_ip.ontologies.seamproj.NarrativeOption;
import org.seamless_ip.services.transferobjects.seamproj.NarrativeOptionListTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.seamproj.Narrative", readonly=false, used=true)
@SuppressWarnings("serial")
public class NarrativeTO extends AbstractTO implements TO<NarrativeTO, Narrative>, Serializable, Cloneable 	
{
	private String _title;		
	private String _description;		
	private NarrativeOptionListTO _narrativeoptions;		
	private Long _id;		



	public NarrativeTO()
	{
		super();
		_narrativeoptions = new NarrativeOptionListTO();
	}

	public static Narrative createDBInstance()
	{
		Narrative dbItem = new Narrative();
		dbItem.setNarrativeOptions(new HashSet<NarrativeOption>());
		return dbItem;
	}


	public static Narrative createDBInstance(NarrativeTO toItem)
	{
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}
	
    public static void releaseDBInstance(Narrative dbItem)
    {
        if (dbItem != null) {
            dbItem.setNarrativeOptions(null);
        }
    }

    public NarrativeTO assignFrom(Narrative target)	
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
				_narrativeoptions .assignFrom(target.getNarrativeOptions());
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
    
    public Narrative assignTo(Narrative target)
    {
        if (target != null) 
        {
			target.setNarrativeOptions(_narrativeoptions .assignTo(target.getNarrativeOptions()));
			target.setDescription(_description);			
			target.setId(_id);			
			target.setTitle(_title);			
        }
        return target;
    }	
    
    public boolean equalsTo(Narrative target)
    {
        if (target == null)
            return false;

        NarrativeTO targetTO = new NarrativeTO().assignFrom(target);
        return targetTO.equals(this);
    }
    
    @Override
    public NarrativeTO clone()
    {
    	NarrativeTO clone = new NarrativeTO();
		clone.setDescription(_description);
		clone.setId(_id);
		clone.setNarrativeOptions(_narrativeoptions);
		clone.setTitle(_title);
        return clone;
    }    
    
    @Override
    public int hashCode()
    {
        int result = 0;
		result = 31 * result + (_title != null ? _title .hashCode() : 0);
		result = 31 * result + (_description != null ? _description .hashCode() : 0);
		result = 31 * result + (_narrativeoptions != null ? _narrativeoptions .hashCode() : 0);
		result = 31 * result + (_id != null ? _id .hashCode() : 0);
        return result;
    }    
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof NarrativeTO)) return false;

        NarrativeTO classTO = (NarrativeTO) o;

		if (_title != null ? !_title .equals(classTO._title) : classTO._title != null)
			return false;
		if (_description != null ? !_description .equals(classTO._description) : classTO._description != null)
			return false;
		if (_narrativeoptions != null ? !_narrativeoptions .equals(classTO._narrativeoptions) : classTO._narrativeoptions != null)
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
    public NarrativeOptionListTO getNarrativeOptions()
    {
		return _narrativeoptions;
    }
    public String getTitle()
    {
		return _title;
    }
    public void setDescription(String value)
    {
		_description = value;
    }
    public void setId(Long value)
    {
		_id = value;
    }
    public void setNarrativeOptions(NarrativeOptionListTO value)
    {
		_narrativeoptions = value;
    }
    public void setTitle(String value)
    {
		_title = value;
    }
}