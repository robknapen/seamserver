/* gromit: GrassManagementTO .java
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
package org.seamless_ip.services.transferobjects.activity;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import org.seamless_ip.ontologies.activity.GrassManagement;
import org.seamless_ip.services.transferobjects.farm.NUTSregionTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.activity.GrassManagement", readonly=false, used=true)
@SuppressWarnings("serial")
public class GrassManagementTO extends AbstractTO implements TO<GrassManagementTO, GrassManagement>, Serializable, Cloneable 	
{
	private String _label_gms;		
	private NUTSregionTO _nutsregion;		
	private Long _id;		
	private Integer _numberofcuts;		
	private Integer _beginofgrazingperiod;		
	private Float _variablecostsofharvestingofsilage;		
	private Float _variablecostsofharvestingofpastureforgrazing;		
	private Float _variablecostsofharvestingofhay;		
	private Float _variablecostsofharvestingoffreshfodder;		
	private Float _variablecostsconcerninggrasslandwithoutcostsofharvest;		
	private Float _overallbiomass;		
	private Float _nitrogenuseorganic;		
	private Float _nitrogenuse;		
	private Float _endofgrazingperiod;		
	private Float _biomassforsilage;		
	private Float _biomassforpasturegrazing;		
	private Float _biomassforhay;		
	private Float _biomassforfreshfodder;		



	public GrassManagementTO()
	{
		super();
		_nutsregion = new NUTSregionTO();
	}

	public static GrassManagement createDBInstance()
	{
		GrassManagement dbItem = new GrassManagement();
		dbItem.setBeginOfGrazingPeriod(new Integer(0));
		dbItem.setNUTSRegion(NUTSregionTO.createDBInstance());
		dbItem.setNumberOfCuts(new Integer(0));
		return dbItem;
	}


	public static GrassManagement createDBInstance(GrassManagementTO toItem)
	{
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}
	
    public static void releaseDBInstance(GrassManagement dbItem)
    {
        if (dbItem != null) {
            dbItem.setNUTSRegion(null);
        }
    }

    public GrassManagementTO assignFrom(GrassManagement target)	
	{
		if (target != null)
		{
			try {
					_beginofgrazingperiod = target.getBeginOfGrazingPeriod();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_biomassforfreshfodder = target.getBiomassForFreshFodder();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_biomassforhay = target.getBiomassForHay();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_biomassforpasturegrazing = target.getBiomassForPastureGrazing();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_biomassforsilage = target.getBiomassForSilage();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_endofgrazingperiod = target.getEndOfGrazingPeriod();
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
					_label_gms = target.getLabel_gms();
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
					_numberofcuts = target.getNumberOfCuts();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_overallbiomass = target.getOverallBiomass();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_variablecostsconcerninggrasslandwithoutcostsofharvest = target.getVariableCostsConcerningGrasslandWithoutCostsOfHarvest();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_variablecostsofharvestingoffreshfodder = target.getVariableCostsOfHarvestingOfFreshFodder();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_variablecostsofharvestingofhay = target.getVariableCostsOfHarvestingOfHay();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_variablecostsofharvestingofpastureforgrazing = target.getVariableCostsOfHarvestingOfPastureForGrazing();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
			try {
					_variablecostsofharvestingofsilage = target.getVariableCostsOfHarvestingOfSilage();
			} catch(Exception e)
			{
				//to catch hibernate exception throwed when null value is getted
			}			
		}
		return this;
	}
    
    public GrassManagement assignTo(GrassManagement target)
    {
        if (target != null) 
        {
			target.setNUTSRegion(_nutsregion .assignTo(target.getNUTSRegion()));
			target.setBeginOfGrazingPeriod(_beginofgrazingperiod);			
			target.setBiomassForFreshFodder(_biomassforfreshfodder);			
			target.setBiomassForHay(_biomassforhay);			
			target.setBiomassForPastureGrazing(_biomassforpasturegrazing);			
			target.setBiomassForSilage(_biomassforsilage);			
			target.setEndOfGrazingPeriod(_endofgrazingperiod);			
			target.setId(_id);			
			target.setLabel_gms(_label_gms);			
			target.setNitrogenUse(_nitrogenuse);			
			target.setNitrogenUseOrganic(_nitrogenuseorganic);			
			target.setNumberOfCuts(_numberofcuts);			
			target.setOverallBiomass(_overallbiomass);			
			target.setVariableCostsConcerningGrasslandWithoutCostsOfHarvest(_variablecostsconcerninggrasslandwithoutcostsofharvest);			
			target.setVariableCostsOfHarvestingOfFreshFodder(_variablecostsofharvestingoffreshfodder);			
			target.setVariableCostsOfHarvestingOfHay(_variablecostsofharvestingofhay);			
			target.setVariableCostsOfHarvestingOfPastureForGrazing(_variablecostsofharvestingofpastureforgrazing);			
			target.setVariableCostsOfHarvestingOfSilage(_variablecostsofharvestingofsilage);			
        }
        return target;
    }	
    
    public boolean equalsTo(GrassManagement target)
    {
        if (target == null)
            return false;

        GrassManagementTO targetTO = new GrassManagementTO().assignFrom(target);
        return targetTO.equals(this);
    }
    
    @Override
    public GrassManagementTO clone()
    {
    	GrassManagementTO clone = new GrassManagementTO();
		clone.setBeginOfGrazingPeriod(_beginofgrazingperiod);
		clone.setBiomassForFreshFodder(_biomassforfreshfodder);
		clone.setBiomassForHay(_biomassforhay);
		clone.setBiomassForPastureGrazing(_biomassforpasturegrazing);
		clone.setBiomassForSilage(_biomassforsilage);
		clone.setEndOfGrazingPeriod(_endofgrazingperiod);
		clone.setId(_id);
		clone.setLabel_gms(_label_gms);
		clone.setNUTSRegion(_nutsregion);
		clone.setNitrogenUse(_nitrogenuse);
		clone.setNitrogenUseOrganic(_nitrogenuseorganic);
		clone.setNumberOfCuts(_numberofcuts);
		clone.setOverallBiomass(_overallbiomass);
		clone.setVariableCostsConcerningGrasslandWithoutCostsOfHarvest(_variablecostsconcerninggrasslandwithoutcostsofharvest);
		clone.setVariableCostsOfHarvestingOfFreshFodder(_variablecostsofharvestingoffreshfodder);
		clone.setVariableCostsOfHarvestingOfHay(_variablecostsofharvestingofhay);
		clone.setVariableCostsOfHarvestingOfPastureForGrazing(_variablecostsofharvestingofpastureforgrazing);
		clone.setVariableCostsOfHarvestingOfSilage(_variablecostsofharvestingofsilage);
        return clone;
    }    
    
    @Override
    public int hashCode()
    {
        int result = 0;
		result = 31 * result + (_label_gms != null ? _label_gms .hashCode() : 0);
		result = 31 * result + (_nutsregion != null ? _nutsregion .hashCode() : 0);
		result = 31 * result + (_id != null ? _id .hashCode() : 0);
		result = 31 * result + (_numberofcuts != null ? _numberofcuts .hashCode() : 0);
		result = 31 * result + (_beginofgrazingperiod != null ? _beginofgrazingperiod .hashCode() : 0);
		result = 31 * result + (_variablecostsofharvestingofsilage != null ? _variablecostsofharvestingofsilage .hashCode() : 0);
		result = 31 * result + (_variablecostsofharvestingofpastureforgrazing != null ? _variablecostsofharvestingofpastureforgrazing .hashCode() : 0);
		result = 31 * result + (_variablecostsofharvestingofhay != null ? _variablecostsofharvestingofhay .hashCode() : 0);
		result = 31 * result + (_variablecostsofharvestingoffreshfodder != null ? _variablecostsofharvestingoffreshfodder .hashCode() : 0);
		result = 31 * result + (_variablecostsconcerninggrasslandwithoutcostsofharvest != null ? _variablecostsconcerninggrasslandwithoutcostsofharvest .hashCode() : 0);
		result = 31 * result + (_overallbiomass != null ? _overallbiomass .hashCode() : 0);
		result = 31 * result + (_nitrogenuseorganic != null ? _nitrogenuseorganic .hashCode() : 0);
		result = 31 * result + (_nitrogenuse != null ? _nitrogenuse .hashCode() : 0);
		result = 31 * result + (_endofgrazingperiod != null ? _endofgrazingperiod .hashCode() : 0);
		result = 31 * result + (_biomassforsilage != null ? _biomassforsilage .hashCode() : 0);
		result = 31 * result + (_biomassforpasturegrazing != null ? _biomassforpasturegrazing .hashCode() : 0);
		result = 31 * result + (_biomassforhay != null ? _biomassforhay .hashCode() : 0);
		result = 31 * result + (_biomassforfreshfodder != null ? _biomassforfreshfodder .hashCode() : 0);
        return result;
    }    
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof GrassManagementTO)) return false;

        GrassManagementTO classTO = (GrassManagementTO) o;

		if (_label_gms != null ? !_label_gms .equals(classTO._label_gms) : classTO._label_gms != null)
			return false;
		if (_nutsregion != null ? !_nutsregion .equals(classTO._nutsregion) : classTO._nutsregion != null)
			return false;
		if (_id != null ? !_id .equals(classTO._id) : classTO._id != null)
			return false;
		if (_numberofcuts != null ? !_numberofcuts .equals(classTO._numberofcuts) : classTO._numberofcuts != null)
			return false;
		if (_beginofgrazingperiod != null ? !_beginofgrazingperiod .equals(classTO._beginofgrazingperiod) : classTO._beginofgrazingperiod != null)
			return false;
		if (_variablecostsofharvestingofsilage != null ? !_variablecostsofharvestingofsilage .equals(classTO._variablecostsofharvestingofsilage) : classTO._variablecostsofharvestingofsilage != null)
			return false;
		if (_variablecostsofharvestingofpastureforgrazing != null ? !_variablecostsofharvestingofpastureforgrazing .equals(classTO._variablecostsofharvestingofpastureforgrazing) : classTO._variablecostsofharvestingofpastureforgrazing != null)
			return false;
		if (_variablecostsofharvestingofhay != null ? !_variablecostsofharvestingofhay .equals(classTO._variablecostsofharvestingofhay) : classTO._variablecostsofharvestingofhay != null)
			return false;
		if (_variablecostsofharvestingoffreshfodder != null ? !_variablecostsofharvestingoffreshfodder .equals(classTO._variablecostsofharvestingoffreshfodder) : classTO._variablecostsofharvestingoffreshfodder != null)
			return false;
		if (_variablecostsconcerninggrasslandwithoutcostsofharvest != null ? !_variablecostsconcerninggrasslandwithoutcostsofharvest .equals(classTO._variablecostsconcerninggrasslandwithoutcostsofharvest) : classTO._variablecostsconcerninggrasslandwithoutcostsofharvest != null)
			return false;
		if (_overallbiomass != null ? !_overallbiomass .equals(classTO._overallbiomass) : classTO._overallbiomass != null)
			return false;
		if (_nitrogenuseorganic != null ? !_nitrogenuseorganic .equals(classTO._nitrogenuseorganic) : classTO._nitrogenuseorganic != null)
			return false;
		if (_nitrogenuse != null ? !_nitrogenuse .equals(classTO._nitrogenuse) : classTO._nitrogenuse != null)
			return false;
		if (_endofgrazingperiod != null ? !_endofgrazingperiod .equals(classTO._endofgrazingperiod) : classTO._endofgrazingperiod != null)
			return false;
		if (_biomassforsilage != null ? !_biomassforsilage .equals(classTO._biomassforsilage) : classTO._biomassforsilage != null)
			return false;
		if (_biomassforpasturegrazing != null ? !_biomassforpasturegrazing .equals(classTO._biomassforpasturegrazing) : classTO._biomassforpasturegrazing != null)
			return false;
		if (_biomassforhay != null ? !_biomassforhay .equals(classTO._biomassforhay) : classTO._biomassforhay != null)
			return false;
		if (_biomassforfreshfodder != null ? !_biomassforfreshfodder .equals(classTO._biomassforfreshfodder) : classTO._biomassforfreshfodder != null)
			return false;
        
        return true;
    }
    
    public Integer getBeginOfGrazingPeriod()
    {
		return _beginofgrazingperiod;
    }
    public Float getBiomassForFreshFodder()
    {
		return _biomassforfreshfodder;
    }
    public Float getBiomassForHay()
    {
		return _biomassforhay;
    }
    public Float getBiomassForPastureGrazing()
    {
		return _biomassforpasturegrazing;
    }
    public Float getBiomassForSilage()
    {
		return _biomassforsilage;
    }
    public Float getEndOfGrazingPeriod()
    {
		return _endofgrazingperiod;
    }
    public Long getId()
    {
		return _id;
    }
    public String getLabel_gms()
    {
		return _label_gms;
    }
    public NUTSregionTO getNUTSRegion()
    {
		return _nutsregion;
    }
    public Float getNitrogenUse()
    {
		return _nitrogenuse;
    }
    public Float getNitrogenUseOrganic()
    {
		return _nitrogenuseorganic;
    }
    public Integer getNumberOfCuts()
    {
		return _numberofcuts;
    }
    public Float getOverallBiomass()
    {
		return _overallbiomass;
    }
    public Float getVariableCostsConcerningGrasslandWithoutCostsOfHarvest()
    {
		return _variablecostsconcerninggrasslandwithoutcostsofharvest;
    }
    public Float getVariableCostsOfHarvestingOfFreshFodder()
    {
		return _variablecostsofharvestingoffreshfodder;
    }
    public Float getVariableCostsOfHarvestingOfHay()
    {
		return _variablecostsofharvestingofhay;
    }
    public Float getVariableCostsOfHarvestingOfPastureForGrazing()
    {
		return _variablecostsofharvestingofpastureforgrazing;
    }
    public Float getVariableCostsOfHarvestingOfSilage()
    {
		return _variablecostsofharvestingofsilage;
    }
    public void setBeginOfGrazingPeriod(Integer value)
    {
		_beginofgrazingperiod = value;
    }
    public void setBiomassForFreshFodder(Float value)
    {
		_biomassforfreshfodder = value;
    }
    public void setBiomassForHay(Float value)
    {
		_biomassforhay = value;
    }
    public void setBiomassForPastureGrazing(Float value)
    {
		_biomassforpasturegrazing = value;
    }
    public void setBiomassForSilage(Float value)
    {
		_biomassforsilage = value;
    }
    public void setEndOfGrazingPeriod(Float value)
    {
		_endofgrazingperiod = value;
    }
    public void setId(Long value)
    {
		_id = value;
    }
    public void setLabel_gms(String value)
    {
		_label_gms = value;
    }
    public void setNUTSRegion(NUTSregionTO value)
    {
		_nutsregion = value;
    }
    public void setNitrogenUse(Float value)
    {
		_nitrogenuse = value;
    }
    public void setNitrogenUseOrganic(Float value)
    {
		_nitrogenuseorganic = value;
    }
    public void setNumberOfCuts(Integer value)
    {
		_numberofcuts = value;
    }
    public void setOverallBiomass(Float value)
    {
		_overallbiomass = value;
    }
    public void setVariableCostsConcerningGrasslandWithoutCostsOfHarvest(Float value)
    {
		_variablecostsconcerninggrasslandwithoutcostsofharvest = value;
    }
    public void setVariableCostsOfHarvestingOfFreshFodder(Float value)
    {
		_variablecostsofharvestingoffreshfodder = value;
    }
    public void setVariableCostsOfHarvestingOfHay(Float value)
    {
		_variablecostsofharvestingofhay = value;
    }
    public void setVariableCostsOfHarvestingOfPastureForGrazing(Float value)
    {
		_variablecostsofharvestingofpastureforgrazing = value;
    }
    public void setVariableCostsOfHarvestingOfSilage(Float value)
    {
		_variablecostsofharvestingofsilage = value;
    }
}