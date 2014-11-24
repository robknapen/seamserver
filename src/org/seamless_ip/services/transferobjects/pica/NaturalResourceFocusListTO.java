/* gromit: NaturalResourceFocusListTO .java 
 * ==============================================================================
 * (This code was generated by a tool)
 * List of Transport Object class for NaturalResourceFocusTO .java
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

import org.seamless_ip.ontologies.pica.NaturalResourceFocus;
import org.seamless_ip.services.transferobjects.pica.NaturalResourceFocusTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.pica.NaturalResourceFocus", readonly=false, used=true)
@SuppressWarnings("serial")
public class NaturalResourceFocusListTO extends ArrayList<NaturalResourceFocusTO> implements Serializable, Cloneable	
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
    

    public NaturalResourceFocusListTO assignFrom(Set<NaturalResourceFocus> o)
    {
        clear();
        if (o != null) {
            for (NaturalResourceFocus obj : o)
            {
    			add(new NaturalResourceFocusTO().assignFrom(obj));
            }
        }
        return this;
    }



    
    public Set<NaturalResourceFocus> assignTo(Set<NaturalResourceFocus> o)
    {
    	// Have to avoid creating persistent instances of NUTSregion with the
    	// same ID, this will cause an exception when the method is called in
    	// a session context (which it usually will be). So instead a slightly
    	// more complex update algorithm is used.
        
        if (o != null) {
        	
        	// remove no longer used items
        	Iterator<NaturalResourceFocus> iter = o.iterator();
        	while (iter.hasNext())
        	{
        		NaturalResourceFocus dbItem = iter.next();
        		boolean found = false;
        		for (NaturalResourceFocusTO toItem : this)
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
            for (NaturalResourceFocusTO toItem : this) {
            	for (NaturalResourceFocus dbItem : o) {
            		if (dbItem.getId() != null && toItem.getId() != null && dbItem.getId().compareTo(toItem.getId()) == 0) {            			
            		}
            	}
            }
            
        	// add new items
            for (NaturalResourceFocusTO toItem : this)
            {
            	boolean found = false;
            	for (NaturalResourceFocus dbItem : o)
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
					o.add(toItem.assignTo(new NaturalResourceFocus()));
	         }   
            }            	
        }
        return o;
    }    


    public boolean equalsTo(Set<NaturalResourceFocus> o)
    {
        if (o == null)
            return false;

        NaturalResourceFocusListTO to = new NaturalResourceFocusListTO().assignFrom(o);
        return to.equals(this);
    }


    @Override
    public NaturalResourceFocusListTO clone()
    {
        return (NaturalResourceFocusListTO) super.clone();
    }
}
	
