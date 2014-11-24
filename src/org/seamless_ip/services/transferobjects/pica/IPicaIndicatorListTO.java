/* gromit: IPicaIndicatorListTO .java 
 * ==============================================================================
 * (This code was generated by a tool)
 * List of Transport Object class for IPicaIndicatorTO .java
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

package org.seamless_ip.services.transferobjects.pica;

import org.seamless_ip.ontologies.pica.IPicaIndicator;
import org.seamless_ip.services.transferobjects.pica.IPicaIndicatorTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.pica.IPicaIndicator", readonly=false, used=true)
@SuppressWarnings("serial")
public class IPicaIndicatorListTO<TOType, OntoType> extends ArrayList<IPicaIndicatorTO<TOType, OntoType>> implements Serializable, Cloneable	
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
    public IPicaIndicatorListTO<TOType, OntoType> assignFrom(Set<IPicaIndicator> o)
    {
        clear();
        if (o != null) {
            for (IPicaIndicator obj : o)
            {
    	//this is the transport object (TO) management of interfaces fields!
		try {
			//obj is an instance of the interface IPicaIndicator    
			String TOclassname = obj.getClass().getName().replace("org.seamless_ip.ontologies", "org.seamless_ip.services.transferobjects").concat("TO");
			IPicaIndicatorTO instance = ((IPicaIndicatorTO)(Class.forName(TOclassname).newInstance()));
			instance.assignFrom(obj);
			add(instance);
		} catch (Exception e /*ClassNotFoundException, InstantiationException ,IllegalAccessException*/ ){
			e.printStackTrace();
		}
            }
        }
        return this;
    }



    
    public Set<IPicaIndicator> assignTo(Set<IPicaIndicator> o)
    {
    	// Have to avoid creating persistent instances of NUTSregion with the
    	// same ID, this will cause an exception when the method is called in
    	// a session context (which it usually will be). So instead a slightly
    	// more complex update algorithm is used.
        
        if (o != null) {
        	
        	// remove no longer used items
        	Iterator<IPicaIndicator> iter = o.iterator();
        	while (iter.hasNext())
        	{
        		IPicaIndicator dbItem = iter.next();
        		boolean found = false;
        		for (IPicaIndicatorTO<TOType, OntoType> toItem : this)
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
            for (IPicaIndicatorTO<TOType, OntoType> toItem : this) {
            	for (IPicaIndicator dbItem : o) {
            		if (dbItem.getId() != null && toItem.getId() != null && dbItem.getId().compareTo(toItem.getId()) == 0) {            			
            		}
            	}
            }
            
        	// add new items
            for (IPicaIndicatorTO<TOType, OntoType> toItem : this)
            {
            	boolean found = false;
            	for (IPicaIndicator dbItem : o)
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
                    //o.add(toItem.assignTo(new IPicaIndicator()));
	         }   
            }            	
        }
        return o;
    }    


    public boolean equalsTo(Set<IPicaIndicator> o)
    {
        if (o == null)
            return false;

        IPicaIndicatorListTO<TOType, OntoType> to = new IPicaIndicatorListTO<TOType, OntoType>().assignFrom(o);
        return to.equals(this);
    }


    @Override
    @SuppressWarnings("unchecked")
    public IPicaIndicatorListTO<TOType, OntoType> clone()
    {
        return (IPicaIndicatorListTO<TOType, OntoType>) super.clone();
    }
}
	
