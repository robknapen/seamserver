/* gromit: SubsidisedExportTO .java
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
import org.seamless_ip.ontologies.capri.SubsidisedExport;
import org.seamless_ip.services.transferobjects.capri.CountryAggregateTO;
import org.seamless_ip.services.transferobjects.capri.ICAPRIParameterTO;
import org.seamless_ip.services.transferobjects.crop.ProductGroupTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.capri.SubsidisedExport", readonly=false, used=true)
@SuppressWarnings("serial")
public class SubsidisedExportTO extends AbstractTO implements ICAPRIParameterTO<SubsidisedExportTO, SubsidisedExport>, Serializable, Cloneable 	
{
	private ProductGroupTO _productgroup;		
	private Long _id;		
	private Float _value;		
	private CountryAggregateTO _countryaggregate;		
	private Float _baselineValue/*transient field*/;		



	public SubsidisedExportTO()
	{
		super();
		_productgroup = new ProductGroupTO();
		_countryaggregate = new CountryAggregateTO();
	}

	public static SubsidisedExport createDBInstance()
	{
		SubsidisedExport dbItem = new SubsidisedExport();
		dbItem.setCountryAggregate(CountryAggregateTO.createDBInstance());
		dbItem.setProductGroup(ProductGroupTO.createDBInstance());
		return dbItem;
	}


	public static SubsidisedExport createDBInstance(SubsidisedExportTO toItem)
	{
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}
	
    public static void releaseDBInstance(SubsidisedExport dbItem)
    {
        if (dbItem != null) {
            dbItem.setCountryAggregate(null);
            dbItem.setProductGroup(null);
        }
    }

    public SubsidisedExportTO assignFrom(SubsidisedExport target)	
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
    
    public SubsidisedExport assignTo(SubsidisedExport target)
    {
        if (target != null) 
        {
			target.setCountryAggregate(_countryaggregate .assignTo(target.getCountryAggregate()));
			target.setProductGroup(_productgroup .assignTo(target.getProductGroup()));
			target.setId(_id);			
			target.setValue(_value);			
        }
        return target;
    }	
    
    public boolean equalsTo(SubsidisedExport target)
    {
        if (target == null)
            return false;

        SubsidisedExportTO targetTO = new SubsidisedExportTO().assignFrom(target);
        return targetTO.equals(this);
    }
    
    @Override
    public SubsidisedExportTO clone()
    {
    	SubsidisedExportTO clone = new SubsidisedExportTO();
		clone.setBaselineValue(_baselineValue/*transient field*/);
		clone.setCountryAggregate(_countryaggregate);
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
		result = 31 * result + (_countryaggregate != null ? _countryaggregate .hashCode() : 0);
		result = 31 * result + (_baselineValue/*transient field*/ != null ? _baselineValue/*transient field*/ .hashCode() : 0);
        return result;
    }    
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof SubsidisedExportTO)) return false;

        SubsidisedExportTO classTO = (SubsidisedExportTO) o;

		if (_productgroup != null ? !_productgroup .equals(classTO._productgroup) : classTO._productgroup != null)
			return false;
		if (_id != null ? !_id .equals(classTO._id) : classTO._id != null)
			return false;
		if (_value != null ? !_value .equals(classTO._value) : classTO._value != null)
			return false;
		if (_countryaggregate != null ? !_countryaggregate .equals(classTO._countryaggregate) : classTO._countryaggregate != null)
			return false;
		if (_baselineValue/*transient field*/ != null ? !_baselineValue/*transient field*/ .equals(classTO._baselineValue/*transient field*/) : classTO._baselineValue/*transient field*/ != null)
			return false;
        
        return true;
    }
    
    public Float getBaselineValue()
    {
		return _baselineValue/*transient field*/;
    }
    public CountryAggregateTO getCountryAggregate()
    {
		return _countryaggregate;
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
    public void setCountryAggregate(CountryAggregateTO value)
    {
		_countryaggregate = value;
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