/* gromit: SupplyShiftTO.java
 * ==============================================================================
 * Manually copied from DemandShiftTO as a short term solution.
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
import org.seamless_ip.ontologies.capri.SupplyShift;
import org.seamless_ip.services.transferobjects.capri.CountryTO;
import org.seamless_ip.services.transferobjects.capri.ICAPRIParameterTO;
import org.seamless_ip.services.transferobjects.crop.ProductGroupTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.capri.SupplyShift", readonly=false, used=true)
@SuppressWarnings("serial")
public class SupplyShiftTO extends AbstractTO implements ICAPRIParameterTO<SupplyShiftTO, SupplyShift>, Serializable, Cloneable 	
{
	private ProductGroupTO _productgroup;		
	private Long _id;		
	private Float _value;		
	private CountryTO _country;		
	private Float _baselineValue/*transient field*/;		



	public SupplyShiftTO()
	{
		super();
		_productgroup = new ProductGroupTO();
		_country = new CountryTO();
	}

	public static SupplyShift createDBInstance()
	{
		SupplyShift dbItem = new SupplyShift();
		dbItem.setCountry(CountryTO.createDBInstance());
		dbItem.setProductGroup(ProductGroupTO.createDBInstance());
		return dbItem;
	}


	public static SupplyShift createDBInstance(SupplyShiftTO toItem)
	{
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}
	
    public static void releaseDBInstance(SupplyShift dbItem)
    {
        if (dbItem != null) {
            dbItem.setCountry(null);
            dbItem.setProductGroup(null);
        }
    }

    public SupplyShiftTO assignFrom(SupplyShift target)	
	{
		if (target != null)
		{
			try {
				_country .assignFrom(target.getCountry());
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
				_productgroup .assignFrom(target.getProductGroup());
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}		
			try {
					_value = target.getValue();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
		}
		return this;
	}
    
    public SupplyShift assignTo(SupplyShift target)
    {
        if (target != null) 
        {
			target.setCountry(_country .assignTo(target.getCountry()));
			target.setProductGroup(_productgroup .assignTo(target.getProductGroup()));
			target.setId(_id);			
			target.setValue(_value);			
        }
        return target;
    }	
    
    public boolean equalsTo(SupplyShift target)
    {
        if (target == null)
            return false;

        SupplyShiftTO targetTO = new SupplyShiftTO().assignFrom(target);
        return targetTO.equals(this);
    }
    
    @Override
    public SupplyShiftTO clone()
    {
    	SupplyShiftTO clone = new SupplyShiftTO();
		clone.setBaselineValue(_baselineValue/*transient field*/);
		clone.setCountry(_country);
		clone.setId(_id);
		clone.setProductGroup(_productgroup);
		clone.setValue(_value);
        return clone;
    }    
    
    @Override
    public int hashCode()
    {
        int result = 0;
		result = 31 * result + (_productgroup != null ? _productgroup .hashCode() : 0);
		result = 31 * result + (_id != null ? _id .hashCode() : 0);
		result = 31 * result + (_value != null ? _value .hashCode() : 0);
		result = 31 * result + (_country != null ? _country .hashCode() : 0);
		result = 31 * result + (_baselineValue/*transient field*/ != null ? _baselineValue/*transient field*/ .hashCode() : 0);
        return result;
    }    
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof SupplyShiftTO)) return false;

        SupplyShiftTO classTO = (SupplyShiftTO) o;

		if (_productgroup != null ? !_productgroup .equals(classTO._productgroup) : classTO._productgroup != null)
			return false;
		if (_id != null ? !_id .equals(classTO._id) : classTO._id != null)
			return false;
		if (_value != null ? !_value .equals(classTO._value) : classTO._value != null)
			return false;
		if (_country != null ? !_country .equals(classTO._country) : classTO._country != null)
			return false;
		if (_baselineValue/*transient field*/ != null ? !_baselineValue/*transient field*/ .equals(classTO._baselineValue/*transient field*/) : classTO._baselineValue/*transient field*/ != null)
			return false;
        
        return true;
    }
    
    public Float getBaselineValue()
    {
		return _baselineValue/*transient field*/;
    }
    public CountryTO getCountry()
    {
		return _country;
    }
    public Long getId()
    {
		return _id;
    }
    public ProductGroupTO getProductGroup()
    {
		return _productgroup;
    }
    public Float getValue()
    {
		return _value;
    }
    public void setBaselineValue(Float value)
    {
		_baselineValue/*transient field*/ = value;
    }
    public void setCountry(CountryTO value)
    {
		_country = value;
    }
    public void setId(Long value)
    {
		_id = value;
    }
    public void setProductGroup(ProductGroupTO value)
    {
		_productgroup = value;
    }
    public void setValue(Float value)
    {
		_value = value;
    }
}