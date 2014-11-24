/* gromit: ExchangeRatesTO .java
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
import org.seamless_ip.ontologies.capri.ExchangeRates;
import org.seamless_ip.services.transferobjects.capri.CountryAggregateTO;
import org.seamless_ip.services.transferobjects.capri.ICAPRIParameterTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.capri.ExchangeRates", readonly=false, used=true)
@SuppressWarnings("serial")
public class ExchangeRatesTO extends AbstractTO implements ICAPRIParameterTO<ExchangeRatesTO, ExchangeRates>, Serializable, Cloneable 	
{
	private Long _id;		
	private Float _value;		
	private CountryAggregateTO _tocountryaggregate;		
	private CountryAggregateTO _fromcountryaggregate;		
	private Float _baselineValue/*transient field*/;		



	public ExchangeRatesTO()
	{
		super();
		_tocountryaggregate = new CountryAggregateTO();
		_fromcountryaggregate = new CountryAggregateTO();
	}

	public static ExchangeRates createDBInstance()
	{
		ExchangeRates dbItem = new ExchangeRates();
		dbItem.setFromCountryAggregate(CountryAggregateTO.createDBInstance());
		dbItem.setToCountryAggregate(CountryAggregateTO.createDBInstance());
		return dbItem;
	}


	public static ExchangeRates createDBInstance(ExchangeRatesTO toItem)
	{
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}
	
    public static void releaseDBInstance(ExchangeRates dbItem)
    {
        if (dbItem != null) {
            dbItem.setFromCountryAggregate(null);
            dbItem.setToCountryAggregate(null);
        }
    }

    public ExchangeRatesTO assignFrom(ExchangeRates target)	
	{
		if (target != null)
		{
			try {
				_fromcountryaggregate .assignFrom(target.getFromCountryAggregate());
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
				_tocountryaggregate .assignFrom(target.getToCountryAggregate());
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
    
    public ExchangeRates assignTo(ExchangeRates target)
    {
        if (target != null) 
        {
			target.setFromCountryAggregate(_fromcountryaggregate .assignTo(target.getFromCountryAggregate()));
			target.setToCountryAggregate(_tocountryaggregate .assignTo(target.getToCountryAggregate()));
			target.setId(_id);			
			target.setValue(_value);			
        }
        return target;
    }	
    
    public boolean equalsTo(ExchangeRates target)
    {
        if (target == null)
            return false;

        ExchangeRatesTO targetTO = new ExchangeRatesTO().assignFrom(target);
        return targetTO.equals(this);
    }
    
    @Override
    public ExchangeRatesTO clone()
    {
    	ExchangeRatesTO clone = new ExchangeRatesTO();
		clone.setBaselineValue(_baselineValue/*transient field*/);
		clone.setFromCountryAggregate(_fromcountryaggregate);
		clone.setId(_id);
		clone.setToCountryAggregate(_tocountryaggregate);
		clone.setValue(_value);
        return clone;
    }    
    
    @Override
    public int hashCode()
    {
        int result = 0;
		result = 31 * result + (_id != null ? _id .hashCode() : 0);
		result = 31 * result + (_value != null ? _value .hashCode() : 0);
		result = 31 * result + (_tocountryaggregate != null ? _tocountryaggregate .hashCode() : 0);
		result = 31 * result + (_fromcountryaggregate != null ? _fromcountryaggregate .hashCode() : 0);
		result = 31 * result + (_baselineValue/*transient field*/ != null ? _baselineValue/*transient field*/ .hashCode() : 0);
        return result;
    }    
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ExchangeRatesTO)) return false;

        ExchangeRatesTO classTO = (ExchangeRatesTO) o;

		if (_id != null ? !_id .equals(classTO._id) : classTO._id != null)
			return false;
		if (_value != null ? !_value .equals(classTO._value) : classTO._value != null)
			return false;
		if (_tocountryaggregate != null ? !_tocountryaggregate .equals(classTO._tocountryaggregate) : classTO._tocountryaggregate != null)
			return false;
		if (_fromcountryaggregate != null ? !_fromcountryaggregate .equals(classTO._fromcountryaggregate) : classTO._fromcountryaggregate != null)
			return false;
		if (_baselineValue/*transient field*/ != null ? !_baselineValue/*transient field*/ .equals(classTO._baselineValue/*transient field*/) : classTO._baselineValue/*transient field*/ != null)
			return false;
        
        return true;
    }
    
    public Float getBaselineValue()
    {
		return _baselineValue/*transient field*/;
    }
    public CountryAggregateTO getFromCountryAggregate()
    {
		return _fromcountryaggregate;
    }
    public Long getId()
    {
		return _id;
    }
    public CountryAggregateTO getToCountryAggregate()
    {
		return _tocountryaggregate;
    }
    public Float getValue()
    {
		return _value;
    }
    public void setBaselineValue(Float value)
    {
		_baselineValue/*transient field*/ = value;
    }
    public void setFromCountryAggregate(CountryAggregateTO value)
    {
		_fromcountryaggregate = value;
    }
    public void setId(Long value)
    {
		_id = value;
    }
    public void setToCountryAggregate(CountryAggregateTO value)
    {
		_tocountryaggregate = value;
    }
    public void setValue(Float value)
    {
		_value = value;
    }
}