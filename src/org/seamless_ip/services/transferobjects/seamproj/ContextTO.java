/* gromit: ContextTO .java
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
import org.seamless_ip.ontologies.farm.RepresentativeFarm;
import org.seamless_ip.ontologies.prodent.ProductionOrientation;
import org.seamless_ip.ontologies.seamproj.Context;
import org.seamless_ip.services.transferobjects.farm.RepresentativeFarmListTO;
import org.seamless_ip.services.transferobjects.prodent.ProductionOrientationListTO;
import org.seamless_ip.services.transferobjects.seamproj.NarrativeTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.seamproj.Context", readonly=false, used=true)
@SuppressWarnings("serial")
public class ContextTO extends AbstractTO implements TO<ContextTO, Context>, Serializable, Cloneable 	
{
	private String _label_en;		
	private RepresentativeFarmListTO _representativefarm;		
	private ProductionOrientationListTO _productionorientation;		
	private NarrativeTO _narrative;		
	private Long _id;		
	private Long _baselineId/*transient field*/;		



	public ContextTO()
	{
		super();
		_representativefarm = new RepresentativeFarmListTO();
		_productionorientation = new ProductionOrientationListTO();
		_narrative = new NarrativeTO();
	}

	public static Context createDBInstance()
	{
		Context dbItem = new Context();
		dbItem.setNarrative(NarrativeTO.createDBInstance());
		dbItem.setProductionOrientation(new HashSet<ProductionOrientation>());
		dbItem.setRepresentativeFarm(new HashSet<RepresentativeFarm>());
		return dbItem;
	}


	public static Context createDBInstance(ContextTO toItem)
	{
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}
	
    public static void releaseDBInstance(Context dbItem)
    {
        if (dbItem != null) {
            dbItem.setNarrative(null);
            dbItem.setProductionOrientation(null);
            dbItem.setRepresentativeFarm(null);
        }
    }

    public ContextTO assignFrom(Context target)	
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
					_label_en = target.getLabel_en();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
				_narrative .assignFrom(target.getNarrative());
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}		
			try {
				_productionorientation .assignFrom(target.getProductionOrientation());
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}		
			try {
				_representativefarm .assignFrom(target.getRepresentativeFarm());
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}		
		}
		return this;
	}
    
    public Context assignTo(Context target)
    {
        if (target != null) 
        {
			target.setNarrative(_narrative .assignTo(target.getNarrative()));
			target.setProductionOrientation(_productionorientation .assignTo(target.getProductionOrientation()));
			target.setRepresentativeFarm(_representativefarm .assignTo(target.getRepresentativeFarm()));
			target.setId(_id);			
			target.setLabel_en(_label_en);			
        }
        return target;
    }	
    
    public boolean equalsTo(Context target)
    {
        if (target == null)
            return false;

        ContextTO targetTO = new ContextTO().assignFrom(target);
        return targetTO.equals(this);
    }
    
    @Override
    public ContextTO clone()
    {
    	ContextTO clone = new ContextTO();
		clone.setBaselineId(_baselineId/*transient field*/);
		clone.setId(_id);
		clone.setLabel_en(_label_en);
		clone.setNarrative(_narrative);
		clone.setProductionOrientation(_productionorientation);
		clone.setRepresentativeFarm(_representativefarm);
        return clone;
    }    
    
    @Override
    public int hashCode()
    {
        int result = 0;
		result = 31 * result + (_label_en != null ? _label_en .hashCode() : 0);
		result = 31 * result + (_representativefarm != null ? _representativefarm .hashCode() : 0);
		result = 31 * result + (_productionorientation != null ? _productionorientation .hashCode() : 0);
		result = 31 * result + (_narrative != null ? _narrative .hashCode() : 0);
		result = 31 * result + (_id != null ? _id .hashCode() : 0);
		result = 31 * result + (_baselineId/*transient field*/ != null ? _baselineId/*transient field*/ .hashCode() : 0);
        return result;
    }    
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ContextTO)) return false;

        ContextTO classTO = (ContextTO) o;

		if (_label_en != null ? !_label_en .equals(classTO._label_en) : classTO._label_en != null)
			return false;
		if (_representativefarm != null ? !_representativefarm .equals(classTO._representativefarm) : classTO._representativefarm != null)
			return false;
		if (_productionorientation != null ? !_productionorientation .equals(classTO._productionorientation) : classTO._productionorientation != null)
			return false;
		if (_narrative != null ? !_narrative .equals(classTO._narrative) : classTO._narrative != null)
			return false;
		if (_id != null ? !_id .equals(classTO._id) : classTO._id != null)
			return false;
		if (_baselineId/*transient field*/ != null ? !_baselineId/*transient field*/ .equals(classTO._baselineId/*transient field*/) : classTO._baselineId/*transient field*/ != null)
			return false;
        
        return true;
    }
    
    public Long getBaselineId()
    {
		return _baselineId/*transient field*/;
    }
    public Long getId()
    {
		return _id;
    }
    public String getLabel_en()
    {
		return _label_en;
    }
    public NarrativeTO getNarrative()
    {
		return _narrative;
    }
    public ProductionOrientationListTO getProductionOrientation()
    {
		return _productionorientation;
    }
    public RepresentativeFarmListTO getRepresentativeFarm()
    {
		return _representativefarm;
    }
    public void setBaselineId(Long value)
    {
		_baselineId/*transient field*/ = value;
    }
    public void setId(Long value)
    {
		_id = value;
    }
    public void setLabel_en(String value)
    {
		_label_en = value;
    }
    public void setNarrative(NarrativeTO value)
    {
		_narrative = value;
    }
    public void setProductionOrientation(ProductionOrientationListTO value)
    {
		_productionorientation = value;
    }
    public void setRepresentativeFarm(RepresentativeFarmListTO value)
    {
		_representativefarm = value;
    }
}