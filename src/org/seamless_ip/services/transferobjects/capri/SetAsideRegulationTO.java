/* gromit: SetAsideRegulationTO .java
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
package org.seamless_ip.services.transferobjects.capri;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Long;
import org.seamless_ip.ontologies.capri.SetAsideRegulation;
import org.seamless_ip.services.transferobjects.capri.CountryAggregateTO;
import org.seamless_ip.services.transferobjects.capri.ICAPRIParameterTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.capri.SetAsideRegulation", readonly=false, used=true)
@SuppressWarnings("serial")
public class SetAsideRegulationTO extends AbstractTO implements ICAPRIParameterTO<SetAsideRegulationTO, SetAsideRegulation>, Serializable, Cloneable 	
{
	private Long _id;		
	private Float _valuemin;		
	private Float _valuemax;		
	private CountryAggregateTO _countryaggregate;		
	private Float _baselineValueMin/*transient field*/;		
	private Float _baselineValueMax/*transient field*/;		

	/* 
	 * Notes: field commented as 'transient field' are only used to exchange information
	 * between client and server.
	 * 
	 * - They are not persistent (it doesn't exists any field on the database to save that values)
	 * - They are not assigned in the TO object method (assignFrom, assignTO)
	 * - They are not considered in the TO equality methods (hashCode, equals)
	 * 
	 * To use this field you have to do the assignment manually (e.g on the DAO classes that use the 
	 * TO). In the client you will be able to check the content of the filed. 
	 * 
	 */


	public SetAsideRegulationTO()
	{
		super();
		_countryaggregate = new CountryAggregateTO();
	}

	public static SetAsideRegulation createDBInstance()
	{
		SetAsideRegulation dbItem = new SetAsideRegulation();
		dbItem.setCountryAggregate(CountryAggregateTO.createDBInstance());
		return dbItem;
	}


	public static SetAsideRegulation createDBInstance(SetAsideRegulationTO toItem)
	{
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}
	
    public static void releaseDBInstance(SetAsideRegulation dbItem)
    {
        if (dbItem != null) {
            dbItem.setCountryAggregate(null);
        }
    }

    public SetAsideRegulationTO assignFrom(SetAsideRegulation target)	
	{
		if (target != null)
		{
			try {
				_countryaggregate .assignFrom(target.getCountryAggregate());
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
					_valuemax = target.getValueMax();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_valuemin = target.getValueMin();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
		}
		return this;
	}
    
    public SetAsideRegulation assignTo(SetAsideRegulation target)
    {
        if (target != null) 
        {
			target.setCountryAggregate(_countryaggregate .assignTo(target.getCountryAggregate()));
			target.setId(_id);			
			target.setValueMax(_valuemax);			
			target.setValueMin(_valuemin);			
        }
        return target;
    }	
    
    public boolean equalsTo(SetAsideRegulation target)
    {
        if (target == null)
            return false;

        SetAsideRegulationTO targetTO = new SetAsideRegulationTO().assignFrom(target);
        return targetTO.equals(this);
    }
    
    @Override
    public SetAsideRegulationTO clone()
    {
    	SetAsideRegulationTO clone = new SetAsideRegulationTO();
		clone.setBaslineValueMax(_baselineValueMax/*transient field*/);
		clone.setBaslineValueMin(_baselineValueMin/*transient field*/);
		clone.setCountryAggregate(_countryaggregate);
		clone.setId(_id);
		clone.setValueMax(_valuemax);
		clone.setValueMin(_valuemin);
        return clone;
    }    
    
    @Override
    public int hashCode()
    {
        int result = 0;
		result = 31 * result + (_id != null ? _id .hashCode() : 0);
		result = 31 * result + (_valuemin != null ? _valuemin .hashCode() : 0);
		result = 31 * result + (_valuemax != null ? _valuemax .hashCode() : 0);
		result = 31 * result + (_countryaggregate != null ? _countryaggregate .hashCode() : 0);
        return result;
    }    
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof SetAsideRegulationTO)) return false;

        SetAsideRegulationTO classTO = (SetAsideRegulationTO) o;

		if (_id != null ? !_id .equals(classTO._id) : classTO._id != null)
			return false;
		if (_valuemin != null ? !_valuemin .equals(classTO._valuemin) : classTO._valuemin != null)
			return false;
		if (_valuemax != null ? !_valuemax .equals(classTO._valuemax) : classTO._valuemax != null)
			return false;
		if (_countryaggregate != null ? !_countryaggregate .equals(classTO._countryaggregate) : classTO._countryaggregate != null)
			return false;
        
        return true;
    }
    
    public Float getBaselineValueMax()
    {
		return _baselineValueMax/*transient field*/;
    }
    public Float getBaselineValueMin()
    {
		return _baselineValueMin/*transient field*/;
    }
    public CountryAggregateTO getCountryAggregate()
    {
		return _countryaggregate;
    }
    public Long getId()
    {
		return _id;
    }
    public Float getValueMax()
    {
		return _valuemax;
    }
    public Float getValueMin()
    {
		return _valuemin;
    }
    public void setBaslineValueMax(Float value)
    {
		_baselineValueMax/*transient field*/ = value;
    }
    public void setBaslineValueMin(Float value)
    {
		_baselineValueMin/*transient field*/ = value;
    }
    public void setCountryAggregate(CountryAggregateTO value)
    {
		_countryaggregate = value;
    }
    public void setId(Long value)
    {
		_id = value;
    }
    public void setValueMax(Float value)
    {
		_valuemax = value;
    }
    public void setValueMin(Float value)
    {
		_valuemin = value;
    }
}