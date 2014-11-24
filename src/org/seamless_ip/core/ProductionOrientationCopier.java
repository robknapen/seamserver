/*  
 * ProductionOrientationCopier.java; Jun 5, 2009
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

import java.util.ArrayList;
import java.util.List;

import org.seamless_ip.ontologies.livestock.BeefManagement;
import org.seamless_ip.ontologies.livestock.DairyManagement;
import org.seamless_ip.ontologies.livestock.GrassManagementAlternative;
import org.seamless_ip.ontologies.prodent.NutrientManagement;
import org.seamless_ip.ontologies.prodent.ProductionOrientation;
import org.seamless_ip.ontologies.prodent.WaterManagement;

public class ProductionOrientationCopier extends
		EntityCopier<ProductionOrientation> {

	public ProductionOrientationCopier(ProductionOrientation entitytobecopied) {
		super(entitytobecopied);

	}

	private ProductionOrientation _NEWProductionOrientation;
	private NutrientManagement _NEWNutrientManagement;
	private WaterManagement _NEWWaterManagement;

	@Override
	public ProductionOrientation executeCopy() throws Exception {
		_NEWProductionOrientation = DataClone.cloneEntity(
				ProductionOrientation.class, _entitytobecopied);

		/*
		 * cloneSetDuplicateItems management...please if new record are copied,
		 * manage they also in the executeDelete() method
		 */
		_NEWProductionOrientation.setBeefManagements(DataClone
				.cloneSetDuplicateItems(new Class[] { BeefManagement.class },
						_entitytobecopied.getBeefManagements()));
		_NEWProductionOrientation.setDairyManagements(DataClone
				.cloneSetDuplicateItems(new Class[] { DairyManagement.class },
						_entitytobecopied.getDairyManagements()));
		_NEWProductionOrientation.setGrassManagementAlternatives(DataClone
				.cloneSetDuplicateItems(
						new Class[] { GrassManagementAlternative.class },
						_entitytobecopied.getGrassManagementAlternatives()));
		_NEWProductionOrientation.setSmallBeefRuminantManagements(DataClone
				.cloneSetDuplicateItems(new Class[] { BeefManagement.class },
						_entitytobecopied.getSmallBeefRuminantManagements()));
		_NEWProductionOrientation.setSmallDairyRuminantManagements(DataClone
				.cloneSetDuplicateItems(new Class[] { DairyManagement.class },
						_entitytobecopied.getSmallDairyRuminantManagements()));

		/*
		 * others management...please if new record are copied, manage they also
		 * in the executeDelete() method
		 */
		_NEWNutrientManagement = DataClone.cloneEntity(
				NutrientManagement.class, _entitytobecopied
						.getNutrientmanagement());
		_NEWNutrientManagement.setCrops(DataClone.cloneSet(_entitytobecopied
				.getNutrientmanagement().getCrops()));
		_NEWProductionOrientation.setNutrientmanagement(_NEWNutrientManagement);

		_NEWWaterManagement = DataClone.cloneEntity(WaterManagement.class,
				_entitytobecopied.getWatermanagement());
		_NEWWaterManagement.setCrops(DataClone.cloneSet(_entitytobecopied
				.getWatermanagement().getCrops()));
		_NEWProductionOrientation.setWatermanagement(_NEWWaterManagement);

		_NEWProductionOrientation.setLabel_en("Enter a name");
		_NEWProductionOrientation.setLabel_gms("ALTE");

		return _NEWProductionOrientation;
	}

	@Override
	public List<String> executeDelete() throws Exception {
		List<String> sqlcmdlist = new ArrayList<String>();

		/*
		 * cloneSetDuplicateItems deletion management...
		 */
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"productionorientationbeefmanagements",
				"productionorientation_id", _entitytobecopied.getId(),
				new Class[] { BeefManagement.class },
				new String[] { "beefmanagement" }, _entitytobecopied
						.getBeefManagements()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"productionorientationdairymanagements",
				"productionorientation_id", _entitytobecopied.getId(),
				new Class[] { DairyManagement.class },
				new String[] { "dairymanagement" }, _entitytobecopied
						.getDairyManagements()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"productionorientationgrassmanagementalternatives",
				"productionorientation_id", _entitytobecopied.getId(),
				new Class[] { GrassManagementAlternative.class },
				new String[] { "grassmanagementalternative" },
				_entitytobecopied.getGrassManagementAlternatives()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"productionorientationsmallbeefruminantmanagements",
				"productionorientation_id", _entitytobecopied.getId(),
				new Class[] { BeefManagement.class },
				new String[] { "beefmanagement" }, _entitytobecopied
						.getSmallBeefRuminantManagements()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"productionorientationsmalldairyruminantmanagements",
				"productionorientation_id", _entitytobecopied.getId(),
				new Class[] { DairyManagement.class },
				new String[] { "dairymanagement" }, _entitytobecopied
						.getSmallDairyRuminantManagements()));
		/*
		 * others deletion management
		 */
		sqlcmdlist.add(String.format(
				"delete from productionorientation where id = %s",
				_entitytobecopied.getId()));

		sqlcmdlist.addAll(DataClone.deleteCloneSet("nutrientmanagementcrops",
				"nutrientmanagement_id", _entitytobecopied
						.getNutrientmanagement().getId()));
		sqlcmdlist.add(String.format(
				"delete from nutrientmanagement where id = %s",
				_entitytobecopied.getNutrientmanagement().getId()));

		sqlcmdlist.addAll(DataClone.deleteCloneSet("watermanagementcrops",
				"watermanagement_id", _entitytobecopied.getWatermanagement()
						.getId()));
		sqlcmdlist.add(String.format(
				"delete from watermanagement where id = %s", _entitytobecopied
						.getWatermanagement().getId()));

		return sqlcmdlist;
	}
}
