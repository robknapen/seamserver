/* gromit: IPenaltyListTO .java 
 * ==============================================================================
 * (This code was generated by a tool)
 * List of Transport Object class for IPenaltyTO .java
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

import org.seamless_ip.ontologies.farmopt.IPenalty;
import org.seamless_ip.services.transferobjects.farmopt.IPenaltyTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.farmopt.IPenalty", readonly=false, used=true)
@SuppressWarnings("serial")
public class IPenaltyListTO<TOType, OntoType> extends ArrayList<IPenaltyTO<TOType, OntoType>> implements Serializable, Cloneable	
{
    @SuppressWarnings("unused")
    private Long id;


    public Long getId()
    {
        return null; // intentionally, this object can not be persisted!
    }


    public void setId(Long id)
    {
        this.id = id;
    }
    

    @SuppressWarnings("unchecked")
    public IPenaltyListTO<TOType, OntoType> assignFrom(Set<IPenalty> o)
    {
        clear();
        if (o != null) {
            for (IPenalty obj : o)
            {
    	//this is the transport object (TO) management of interfaces fields!
		try {
			//obj is an instance of the interface IPenalty    
			String TOclassname = obj.getClass().getName().replace("org.seamless_ip.ontologies", "org.seamless_ip.services.transferobjects").concat("TO");
			IPenaltyTO instance = ((IPenaltyTO)(Class.forName(TOclassname).newInstance()));
			instance.assignFrom(obj);
			add(instance);
		} catch (Exception e /*ClassNotFoundException, InstantiationException ,IllegalAccessException*/ ){
			e.printStackTrace();
		}
            }
        }
        return this;
    }



    
    public Set<IPenalty> assignTo(Set<IPenalty> o)
    {
    	// Have to avoid creating persistent instances of NUTSregion with the
    	// same ID, this will cause an exception when the method is called in
    	// a session context (which it usually will be). So instead a slightly
    	// more complex update algorithm is used.
        
        if (o != null) {
        	
        	// remove no longer used items
        	Iterator<IPenalty> iter = o.iterator();
        	while (iter.hasNext())
        	{
        		IPenalty dbItem = iter.next();
        		boolean found = false;
        		for (IPenaltyTO<TOType, OntoType> toItem : this)
        		{
        			if (dbItem.getId().equals(toItem.getId()))
        			{
        				found = true;
        				break;
        			}
        		}
        		if (!found)
        			iter.remove();
        	}

            //Update just update Float values, we assume that only Float feld of item(s) 
            //can be updated! 
            for (IPenaltyTO<TOType, OntoType> toItem : this) {
            	for (IPenalty dbItem : o) {
            		if (dbItem.getId() != null && toItem.getId() != null && dbItem.getId().compareTo(toItem.getId()) == 0) {            			
						if (dbItem.getNitrogenUse().compareTo(toItem.getNitrogenUse()) != 0) {						
							//Update all Floats 
							dbItem.setNitrogenUse(toItem.getNitrogenUse());
            			}
						if (dbItem.getNitrogenUseOrganic().compareTo(toItem.getNitrogenUseOrganic()) != 0) {						
							//Update all Floats 
							dbItem.setNitrogenUseOrganic(toItem.getNitrogenUseOrganic());
            			}
						if (dbItem.getErosion().compareTo(toItem.getErosion()) != 0) {						
							//Update all Floats 
							dbItem.setErosion(toItem.getErosion());
            			}
						if (dbItem.getPesticidePressure().compareTo(toItem.getPesticidePressure()) != 0) {						
							//Update all Floats 
							dbItem.setPesticidePressure(toItem.getPesticidePressure());
            			}
						if (dbItem.getNitrateleaching().compareTo(toItem.getNitrateleaching()) != 0) {						
							//Update all Floats 
							dbItem.setNitrateleaching(toItem.getNitrateleaching());
            			}
						if (dbItem.getIrrigationWaterAvailability().compareTo(toItem.getIrrigationWaterAvailability()) != 0) {						
							//Update all Floats 
							dbItem.setIrrigationWaterAvailability(toItem.getIrrigationWaterAvailability());
            			}
						if (dbItem.getSoilOrganicMatter().compareTo(toItem.getSoilOrganicMatter()) != 0) {						
							//Update all Floats 
							dbItem.setSoilOrganicMatter(toItem.getSoilOrganicMatter());
            			}
						if (dbItem.getPenalty().compareTo(toItem.getPenalty()) != 0) {						
							//Update all Floats 
							dbItem.setPenalty(toItem.getPenalty());
            			}
            		}
            	}
            }
            
        	// add new items
            for (IPenaltyTO<TOType, OntoType> toItem : this)
            {
            	boolean found = false;
            	for (IPenalty dbItem : o)
            	{
               		if (toItem.getId() == null)//the id null, 'found' is still false...it will be added
    					break;
               		
            		if (toItem.getId().equals(dbItem.getId()))
            		{
            			found = true;
            			break;
            		}
            	}
            	if (!found)
            	{
                    //TODO: Need to know specific type here!!
                    //o.add(toItem.assignTo(new IPenalty()));
	         }   
            }            	
        }
        return o;
    }    


    public boolean equalsTo(Set<IPenalty> o)
    {
        if (o == null)
            return false;

        IPenaltyListTO<TOType, OntoType> to = new IPenaltyListTO<TOType, OntoType>().assignFrom(o);
        return to.equals(this);
    }


    @Override
    @SuppressWarnings("unchecked")
    public IPenaltyListTO<TOType, OntoType> clone()
    {
        return (IPenaltyListTO<TOType, OntoType>) super.clone();
    }
}
	
