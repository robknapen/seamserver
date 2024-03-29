/* gromit: PriceTO .java
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
package org.seamless_ip.services.transferobjects.farmopt;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Long;
import org.seamless_ip.ontologies.farmopt.Price;
import org.seamless_ip.services.transferobjects.crop.IProductTO;
import org.seamless_ip.services.transferobjects.farm.NUTSregionTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.farmopt.Price", readonly=false, used=true)
@SuppressWarnings("serial")
public class PriceTO extends AbstractTO implements TO<PriceTO, Price>, Serializable, Cloneable 	
{
	private NUTSregionTO _region;		
	private Long _id;		
	private IProductTO<Object, Object> _product;
	private Float _value;		
	private Float _baselinevalue/*transient field*/;		

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


	public PriceTO()
	{
		super();
		_region = new NUTSregionTO();
	}

	public static Price createDBInstance()
	{
		Price dbItem = new Price();
		dbItem.setRegion(NUTSregionTO.createDBInstance());
		return dbItem;
	}


	public static Price createDBInstance(PriceTO toItem)
	{
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}
	
    public static void releaseDBInstance(Price dbItem)
    {
        if (dbItem != null) {
            dbItem.setProduct(null);
            dbItem.setRegion(null);
        }
    }

	@SuppressWarnings("unchecked")
    public PriceTO assignFrom(Price target)	
	{
		if (target != null)
		{
			try {
					_id = target.getId();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			//this is the transport object (TO) management of interfaces fields!
			Object instanceofinterface = null;
			try {
				instanceofinterface = target.getProduct();				
			} catch (NullPointerException e)
			{
				//to catch hibernate exception throwed when null value is getted				
			}	
			if (instanceofinterface != null)
			{
				try {
					
					String TOclassname = instanceofinterface.getClass().getName().replace("org.seamless_ip.ontologies", "org.seamless_ip.services.transferobjects").concat("TO");
					_product = (IProductTO)((IProductTO)(Class.forName(TOclassname).newInstance())).assignFrom(instanceofinterface);
					_product .assignFrom(target.getProduct());
				} catch (Exception e /*ClassNotFoundException, InstantiationException ,IllegalAccessException*/ ){
					e.printStackTrace();
				}
			}	
			try {
				_region .assignFrom(target.getRegion());
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
    
    public Price assignTo(Price target)
    {
        if (target != null) 
        {
			//TODO: manage correctly reference to interface, following code give error: target.setProduct(_product .assignTo(target.getProduct()));
			target.setRegion(_region .assignTo(target.getRegion()));
			target.setId(_id);			
			target.setValue(_value);			
        }
        return target;
    }	
    
    public boolean equalsTo(Price target)
    {
        if (target == null)
            return false;

        PriceTO targetTO = new PriceTO().assignFrom(target);
        return targetTO.equals(this);
    }
    
    @Override
    public PriceTO clone()
    {
    	PriceTO clone = new PriceTO();
		clone.setId(_id);
		clone.setProduct(_product);
		clone.setRegion(_region);
		clone.setValue(_value);
		clone.setbaselineValue(_baselinevalue/*transient field*/);
        return clone;
    }    
    
    @Override
    public int hashCode()
    {
        int result = 0;
		result = 31 * result + (_region != null ? _region .hashCode() : 0);
		result = 31 * result + (_id != null ? _id .hashCode() : 0);
		result = 31 * result + (_product != null ? _product .hashCode() : 0);
		result = 31 * result + (_value != null ? _value .hashCode() : 0);
        return result;
    }    
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PriceTO)) return false;

        PriceTO classTO = (PriceTO) o;

		if (_region != null ? !_region .equals(classTO._region) : classTO._region != null)
			return false;
		if (_id != null ? !_id .equals(classTO._id) : classTO._id != null)
			return false;
		if (_product != null ? !_product .equals(classTO._product) : classTO._product != null)
			return false;
		if (_value != null ? !_value .equals(classTO._value) : classTO._value != null)
			return false;
        
        return true;
    }
    
    public Long getId()
    {
		return _id;
    }
	@SuppressWarnings("unchecked")
    public IProductTO getProduct()
    {
		return _product;
    }
    public NUTSregionTO getRegion()
    {
		return _region;
    }
    public Float getValue()
    {
		return _value;
    }
    public Float getbaselineValue()
    {
		return _baselinevalue/*transient field*/;
    }
    public void setId(Long value)
    {
		_id = value;
    }
	@SuppressWarnings("unchecked")
    public void setProduct(IProductTO value)
    {
		_product = value;
    }
    public void setRegion(NUTSregionTO value)
    {
		_region = value;
    }
    public void setValue(Float value)
    {
		_value = value;
    }
    public void setbaselineValue(Float value)
    {
		_baselinevalue/*transient field*/ = value;
    }
}