/* gromit: ISubsidyListTO .java 
 * ==============================================================================
 * (This code was generated by a tool)
 * List of Transport Object class for ISubsidyTO .java
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

import org.seamless_ip.ontologies.farmopt.ISubsidy;
import org.seamless_ip.services.transferobjects.farmopt.ISubsidyTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.farmopt.ISubsidy", readonly=false, used=true)
@SuppressWarnings("serial")
public class ISubsidyListTO<TOType, OntoType> extends ArrayList<ISubsidyTO<TOType, OntoType>> implements Serializable, Cloneable	
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
    public ISubsidyListTO<TOType, OntoType> assignFrom(Set<ISubsidy> o)
    {
        clear();
        if (o != null) {
            for (ISubsidy obj : o)
            {
    	//this is the transport object (TO) management of interfaces fields!
		try {
			//obj is an instance of the interface ISubsidy    
			String TOclassname = obj.getClass().getName().replace("org.seamless_ip.ontologies", "org.seamless_ip.services.transferobjects").concat("TO");
			ISubsidyTO instance = ((ISubsidyTO)(Class.forName(TOclassname).newInstance()));
			instance.assignFrom(obj);
			add(instance);
		} catch (Exception e /*ClassNotFoundException, InstantiationException ,IllegalAccessException*/ ){
			e.printStackTrace();
		}
            }
        }
        return this;
    }



    
    public Set<ISubsidy> assignTo(Set<ISubsidy> o)
    {
    	// Have to avoid creating persistent instances of NUTSregion with the
    	// same ID, this will cause an exception when the method is called in
    	// a session context (which it usually will be). So instead a slightly
    	// more complex update algorithm is used.
        
        if (o != null) {
        	
        	// remove no longer used items
        	Iterator<ISubsidy> iter = o.iterator();
        	while (iter.hasNext())
        	{
        		ISubsidy dbItem = iter.next();
        		boolean found = false;
        		for (ISubsidyTO<TOType, OntoType> toItem : this)
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
            for (ISubsidyTO<TOType, OntoType> toItem : this) {
            	for (ISubsidy dbItem : o) {
            		if (dbItem.getId() != null && toItem.getId() != null && dbItem.getId().compareTo(toItem.getId()) == 0) {            			
						if (dbItem.getSubsidy().compareTo(toItem.getSubsidy()) != 0) {						
							//Update all Floats 
							dbItem.setSubsidy(toItem.getSubsidy());
            			}
            		}
            	}
            }
            
        	// add new items
            for (ISubsidyTO<TOType, OntoType> toItem : this)
            {
            	boolean found = false;
            	for (ISubsidy dbItem : o)
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
                    //o.add(toItem.assignTo(new ISubsidy()));
	         }   
            }            	
        }
        return o;
    }    


    public boolean equalsTo(Set<ISubsidy> o)
    {
        if (o == null)
            return false;

        ISubsidyListTO<TOType, OntoType> to = new ISubsidyListTO<TOType, OntoType>().assignFrom(o);
        return to.equals(this);
    }


    @Override
    @SuppressWarnings("unchecked")
    public ISubsidyListTO<TOType, OntoType> clone()
    {
        return (ISubsidyListTO<TOType, OntoType>) super.clone();
    }
}
	
