/* gromit: PenaltyTO .java
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
import java.lang.Boolean;
import java.lang.Float;
import java.lang.Long;
import org.seamless_ip.ontologies.farmopt.Penalty;
import org.seamless_ip.services.transferobjects.farm.NUTSregionTO;
import org.seamless_ip.services.transferobjects.farmopt.IPolicyMeasureTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.farmopt.Penalty", readonly=false, used=true)
@SuppressWarnings("serial")
public class PenaltyTO extends AbstractTO implements IPolicyMeasureTO<PenaltyTO, Penalty>, Serializable, Cloneable 	
{
	private Float _rulelevel/*transient field*/;		
	private NUTSregionTO _nutsregion;		
	private Long _id;		
	private Float _soilorganicmatter;		
	private Float _pesticidepressure;		
	private Float _penalty;		
	private Float _nitrogenuseorganic;		
	private Float _nitrogenuse;		
	private Float _nitrateleaching;		
	private Float _irrigationwateravailability;		
	private Float _erosion;		
	private Boolean _minimum;		

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


	public PenaltyTO()
	{
		super();
		_nutsregion = new NUTSregionTO();
	}

	public static Penalty createDBInstance()
	{
		Penalty dbItem = new Penalty();
		dbItem.setMinimum(new Boolean(false));
		dbItem.setNUTSRegion(NUTSregionTO.createDBInstance());
		return dbItem;
	}


	public static Penalty createDBInstance(PenaltyTO toItem)
	{
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}
	
    public static void releaseDBInstance(Penalty dbItem)
    {
        if (dbItem != null) {
            dbItem.setNUTSRegion(null);
        }
    }

    public PenaltyTO assignFrom(Penalty target)	
	{
		if (target != null)
		{
			try {
					_erosion = target.getErosion();
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
					_irrigationwateravailability = target.getIrrigationWaterAvailability();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_minimum = target.getMinimum();
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
					_nitrateleaching = target.getNitrateleaching();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_nitrogenuse = target.getNitrogenUse();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_nitrogenuseorganic = target.getNitrogenUseOrganic();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_penalty = target.getPenalty();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_pesticidepressure = target.getPesticidePressure();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_soilorganicmatter = target.getSoilOrganicMatter();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
		}
		return this;
	}
    
    public Penalty assignTo(Penalty target)
    {
        if (target != null) 
        {
			target.setNUTSRegion(_nutsregion .assignTo(target.getNUTSRegion()));
			target.setErosion(_erosion);			
			target.setId(_id);			
			target.setIrrigationWaterAvailability(_irrigationwateravailability);			
			target.setMinimum(_minimum);			
			target.setNitrateleaching(_nitrateleaching);			
			target.setNitrogenUse(_nitrogenuse);			
			target.setNitrogenUseOrganic(_nitrogenuseorganic);			
			target.setPenalty(_penalty);			
			target.setPesticidePressure(_pesticidepressure);			
			target.setSoilOrganicMatter(_soilorganicmatter);			
        }
        return target;
    }	
    
    public boolean equalsTo(Penalty target)
    {
        if (target == null)
            return false;

        PenaltyTO targetTO = new PenaltyTO().assignFrom(target);
        return targetTO.equals(this);
    }
    
    @Override
    public PenaltyTO clone()
    {
    	PenaltyTO clone = new PenaltyTO();
		clone.setErosion(_erosion);
		clone.setId(_id);
		clone.setIrrigationWaterAvailability(_irrigationwateravailability);
		clone.setMinimum(_minimum);
		clone.setNUTSRegion(_nutsregion);
		clone.setNitrateleaching(_nitrateleaching);
		clone.setNitrogenUse(_nitrogenuse);
		clone.setNitrogenUseOrganic(_nitrogenuseorganic);
		clone.setPenalty(_penalty);
		clone.setPesticidePressure(_pesticidepressure);
		clone.setSoilOrganicMatter(_soilorganicmatter);
		clone.setruleLevel(_rulelevel/*transient field*/);
        return clone;
    }    
    
    @Override
    public int hashCode()
    {
        int result = 0;
		result = 31 * result + (_nutsregion != null ? _nutsregion .hashCode() : 0);
		result = 31 * result + (_id != null ? _id .hashCode() : 0);
		result = 31 * result + (_soilorganicmatter != null ? _soilorganicmatter .hashCode() : 0);
		result = 31 * result + (_pesticidepressure != null ? _pesticidepressure .hashCode() : 0);
		result = 31 * result + (_penalty != null ? _penalty .hashCode() : 0);
		result = 31 * result + (_nitrogenuseorganic != null ? _nitrogenuseorganic .hashCode() : 0);
		result = 31 * result + (_nitrogenuse != null ? _nitrogenuse .hashCode() : 0);
		result = 31 * result + (_nitrateleaching != null ? _nitrateleaching .hashCode() : 0);
		result = 31 * result + (_irrigationwateravailability != null ? _irrigationwateravailability .hashCode() : 0);
		result = 31 * result + (_erosion != null ? _erosion .hashCode() : 0);
		result = 31 * result + (_minimum != null ? _minimum .hashCode() : 0);
        return result;
    }    
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PenaltyTO)) return false;

        PenaltyTO classTO = (PenaltyTO) o;

		if (_nutsregion != null ? !_nutsregion .equals(classTO._nutsregion) : classTO._nutsregion != null)
			return false;
		if (_id != null ? !_id .equals(classTO._id) : classTO._id != null)
			return false;
		if (_soilorganicmatter != null ? !_soilorganicmatter .equals(classTO._soilorganicmatter) : classTO._soilorganicmatter != null)
			return false;
		if (_pesticidepressure != null ? !_pesticidepressure .equals(classTO._pesticidepressure) : classTO._pesticidepressure != null)
			return false;
		if (_penalty != null ? !_penalty .equals(classTO._penalty) : classTO._penalty != null)
			return false;
		if (_nitrogenuseorganic != null ? !_nitrogenuseorganic .equals(classTO._nitrogenuseorganic) : classTO._nitrogenuseorganic != null)
			return false;
		if (_nitrogenuse != null ? !_nitrogenuse .equals(classTO._nitrogenuse) : classTO._nitrogenuse != null)
			return false;
		if (_nitrateleaching != null ? !_nitrateleaching .equals(classTO._nitrateleaching) : classTO._nitrateleaching != null)
			return false;
		if (_irrigationwateravailability != null ? !_irrigationwateravailability .equals(classTO._irrigationwateravailability) : classTO._irrigationwateravailability != null)
			return false;
		if (_erosion != null ? !_erosion .equals(classTO._erosion) : classTO._erosion != null)
			return false;
		if (_minimum != null ? !_minimum .equals(classTO._minimum) : classTO._minimum != null)
			return false;
        
        return true;
    }
    
    public Float getErosion()
    {
		return _erosion;
    }
    public Long getId()
    {
		return _id;
    }
    public Float getIrrigationWaterAvailability()
    {
		return _irrigationwateravailability;
    }
    public Boolean getMinimum()
    {
		return _minimum;
    }
    public NUTSregionTO getNUTSRegion()
    {
		return _nutsregion;
    }
    public Float getNitrateleaching()
    {
		return _nitrateleaching;
    }
    public Float getNitrogenUse()
    {
		return _nitrogenuse;
    }
    public Float getNitrogenUseOrganic()
    {
		return _nitrogenuseorganic;
    }
    public Float getPenalty()
    {
		return _penalty;
    }
    public Float getPesticidePressure()
    {
		return _pesticidepressure;
    }
    public Float getSoilOrganicMatter()
    {
		return _soilorganicmatter;
    }
    public Float getruleLevel()
    {
		return _rulelevel/*transient field*/;
    }
    public void setErosion(Float value)
    {
		_erosion = value;
    }
    public void setId(Long value)
    {
		_id = value;
    }
    public void setIrrigationWaterAvailability(Float value)
    {
		_irrigationwateravailability = value;
    }
    public void setMinimum(Boolean value)
    {
		_minimum = value;
    }
    public void setNUTSRegion(NUTSregionTO value)
    {
		_nutsregion = value;
    }
    public void setNitrateleaching(Float value)
    {
		_nitrateleaching = value;
    }
    public void setNitrogenUse(Float value)
    {
		_nitrogenuse = value;
    }
    public void setNitrogenUseOrganic(Float value)
    {
		_nitrogenuseorganic = value;
    }
    public void setPenalty(Float value)
    {
		_penalty = value;
    }
    public void setPesticidePressure(Float value)
    {
		_pesticidepressure = value;
    }
    public void setSoilOrganicMatter(Float value)
    {
		_soilorganicmatter = value;
    }
    public void setruleLevel(Float value)
    {
		_rulelevel/*transient field*/ = value;
    }
}