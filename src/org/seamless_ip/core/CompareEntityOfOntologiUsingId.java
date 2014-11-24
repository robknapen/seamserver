/*  
 * CompareEntityOfOntologiUsingId.java; Jun 5, 2009
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

package org.seamless_ip.core;

import java.lang.reflect.Method;
import java.util.Comparator;

/*
 * used to order set of entitys
 *
 *e.g
 *
 *     Narrative narrative = PersistenceUtil.getEntityManager().find(Narrative.class, _narrativeId);
 *     SortedSet<NarrativeOption> sorted = new TreeSet<NarrativeOption>(new CompareEntityOfHontologiUsingId());
 *     sorted.addAll(narrative.getNarrativeOptions()); //will order NarrativeOptions
 *
 * Created on 26. settembre 2007, 15:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

public class CompareEntityOfOntologiUsingId implements Comparator<Object> {
	public int compare(Object object1, Object object2) {
		Object e1 = object1;
		Object e2 = object2;
		try {
			Method getid1 = e1.getClass().getMethod("getId");
			Method getid2 = e2.getClass().getMethod("getId");
			Object id1 = getid1.invoke(e1, new Object[] {});
			Object id2 = getid2.invoke(e2, new Object[] {});

			if ((id1 != null) && (id2 != null)) {
				return ((Long) id1).compareTo((Long) id2);
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}

}