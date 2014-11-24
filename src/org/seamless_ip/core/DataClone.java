/*  
 * DataClone.java; Jun 5, 2009
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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

/* class DataClone
 * 
 * collect static methods to be used to duplicate rows of the db using 
 * Hibernate beans
 * 
 */
public class DataClone {

	private static Logger logger = Logger.getLogger(DataClone.class.getName());

	// collect the number of rows inserted
	public static int counter = 0;

	/*
	 * public static <E> E cloneEntity(Class<E> clazz, E entity) throws
	 * Exception
	 * 
	 * Create an entity cloning @entity (@entity is a instance of Class<E>). All
	 * field are copied (except Set and Id).
	 * 
	 * Return an clone of @entity.
	 * 
	 * Sample: we would like to copy the Problem [id=1]
	 * 
	 * //original Problem id=1 description="this is just a sample"
	 * spatialscale=10 models=(id=12, id=15, id=20) //a set of model!
	 * 
	 * //clone Problem id=2 description="this is just a sample" spatialscale=10
	 * models=() //using cloneEntity set are not copied!!!! (use cloneSet and
	 * cloneSetDuplicateItems)
	 * 
	 * > Problem problemtobecloned = currentSession().get(Problem.class, 1l);
	 * //retrieve the original from the db > Problem clonedproblem =
	 * DataClone.cloneEntity(Problem.class, problemtobecloned); //get a cloned
	 * instance
	 */
	public static <E> E cloneEntity(Class<E> clazz, E entity) throws Exception {
		E _NEWentity;
		Constructor<E> _constructor;

		// creation of new instance
		_constructor = clazz.getDeclaredConstructor(new Class[] {});
		_NEWentity = _constructor.newInstance(new Object[] {});

		//
		for (Method item : clazz.getMethods()) {
			if (item.getName().startsWith("get"))
				if ((!item.getName().equals("getClass"))
						&& (!item.getName().equals("getId"))/*
															 * &&
															 * (!item.getName(
															 * ).equals(
															 * "getHibernateLazyInitializer"
															 * ))
															 */) {

					logger.debug(item.getName());
					String namewithoutget = item.getName().substring(3);

					// this method has to be called
					Object result = item.invoke(entity, new Object[] {});

					if (result != null) {
						if ((item.getReturnType().getName()
								.equals("java.lang.String"))
								|| (item.getReturnType().getName()
										.equals("java.lang.Boolean"))
								|| (item.getReturnType().getName()
										.equals("java.lang.Integer"))
								|| (item.getReturnType().getName()
										.equals("java.lang.Long"))
								|| (item.getReturnType().getName()
										.equals("java.lang.Float"))) {

							Method setmethod = clazz.getMethod("set"
									+ namewithoutget, item.getReturnType());
							setmethod.invoke(_NEWentity,
									new Object[] { result });
							logger.debug("invoked " + clazz.getSimpleName()
									+ "." + setmethod.getName() + "(" + result
									+ ")");

						} else if (item.getReturnType().getName().equals(
								"java.util.Set")) {

						} else { // another entity

							Method setmethod = clazz.getMethod("set"
									+ namewithoutget, item.getReturnType());
							setmethod.invoke(_NEWentity,
									new Object[] { result });
							logger.debug("invoked " + clazz.getSimpleName()
									+ "." + setmethod.getName() + "(" + result
									+ ")");
						}
					}
				}
		}

		logger.info("cloneEntity: created new " + clazz.getSimpleName());
		DataClone.counter++;
		return _NEWentity;
	}

	/*
	 * public static <E >Set<E> cloneSet(Set<E> set) throws Exception {
	 * 
	 * Create a new set with all entity of @set To copy set and assign it to
	 * instances avoiding
	 * "Hibernate exception for duplicate reference to the same Set"
	 * 
	 * Return an clone of @set.
	 * 
	 * Sample: we would like to copy the set of model of Problem [id=1] to be
	 * able to assign it to Problem [id=2]
	 * 
	 * 
	 * //starting situation Problem id=1 models=(id=12, id=15, id=20) //a set of
	 * model!
	 * 
	 * Problem id=2 models=()
	 * 
	 * //after cloneSet Problem id=1 models=(id=12, id=15, id=20)
	 * 
	 * Problem id=2 models=(id=12, id=15, id=20)
	 * 
	 * > Problem problem_one = currentSession().get(Problem.class, 1l);
	 * //retrieve data from the db > Problem problem_two =
	 * currentSession().get(Problem.class, 2l); //retrieve data from the db >
	 * Set<Model> clonedset = DataClone.cloneSet(problem_one.getModels()); //get
	 * a cloned instance of a set > problem_two.setModels(clonedset);//note:
	 * problem_two.setModels(problem_one.getModels()) will produce an exception
	 * when saving!
	 */
	public static <E> Set<E> cloneSet(Set<E> set) throws Exception {
		Set<E> _NEWset = new HashSet<E>();

		String descrforlog = null;
		// I sort the set of entity by id
		// to save record in the same order (by id) as in the original)
		Set<E> sortedset = new TreeSet<E>(new CompareEntityOfOntologiUsingId());
		sortedset.addAll(set);

		for (E item : sortedset) {
			_NEWset.add(item);
			if (descrforlog == null)
				descrforlog = item.getClass().getSimpleName();
		}

		logger.info("cloneSet: created " + _NEWset.size()
				+ " relation(s) to entity");
		DataClone.counter += _NEWset.size();
		return _NEWset;
	}

	/*
	 * public static <I, T extends I> List<String>
	 * deleteCloneSetDuplicateItems(String relationtable, String
	 * id_field_ofrelationtable, Long id_value_ofrelationtable, Class<?>[]
	 * clazza, String[] tablea, Set<I> set) throws Exception
	 * 
	 * 
	 * Generate sql script to remove row inserted using CloneSetDuplicateItems
	 * 
	 * Return an clone of list of sql statements for deletion.
	 * 
	 * Sample: I would like to remove cloned rows (inserted before using
	 * CloneSetDuplicateItems).
	 * 
	 * //starting situation Problem id=1 models=(id=12, id=15, id=20) //a set of
	 * model!
	 * 
	 * Problem id=2 models=()
	 * 
	 * //after cloneSetDuplicateItems Problem id=1 models=(id=12, id=15, id=20)
	 * 
	 * Problem id=2 models=(id=21, id=22, id=23)//this are cloned!
	 * 
	 * ...the only way to delete cloned rows is generate SQL statement.
	 * 
	 * > Problem problem_two = currentSession().get(Problem.class, 2l);
	 * //retrieve data from the db > List<String> sqlcmd =
	 * deleteCloneSetDuplicateItems("problemmodels", > "problem_id", > 2, > new
	 * String[]{Model.class}, "model", > problem_two.getModels());
	 * 
	 * Notes: - sqlcmd will contains following statements:
	 * "delete from problemmodels where problem_id = 2"
	 * "delete from model where id = 21" "delete from model where id = 22"
	 * "delete from model where id = 23" - sqlcmd can be executed inside loop: >
	 * for (String sqlcmd: sqlcmdlist) >
	 * currentSession().createSQLQuery(sqlcmd).executeUpdate();
	 * 
	 * another sample: we have to delete record from generic set
	 * 
	 * > PolicyOpion policyoption = currentSession().get(PolicyOpion.class,
	 * 11l); //retrieve data from the db > List<String> sqlcmd =
	 * DataClone.deleteCloneSetDuplicateItems("policyoptionpolicymeasures", >
	 * "policyoption_id", > 11l, > new Class[]{Penalty.class,
	 * SubsidyCrossCompliance.class, Tax.class}, new String[]{"penalty",
	 * "subsidycrosscompliance", "tax"},//class and table names should match
	 * (same order!) > policyoption.getPolicyMeasures()));
	 * 
	 * Notes: - sqlcmd will contains following statements:
	 * "delete from policyoptionpolicymeasures where policyoption_id = 11"
	 * "delete from penalty where id in (244873,244874)"
	 * "delete from subsidycrosscompliance where id in (244875,244876)"
	 * "delete from tax where id in (244877)" - policyoptionpolicymeasures can
	 * reference record of tables: penaltysubsidy, crosscompliance, tax. This
	 * has to be specified in arguments @clazza and @tablea
	 */
	public static <I, T extends I> List<String> deleteCloneSetDuplicateItems(
			String relationtable, String id_field_ofrelationtable,
			Long id_value_ofrelationtable, Class<?>[] clazza, String[] tablea,
			Set<I> set) throws Exception {
		Map<String, StringBuffer> map = new HashMap<String, StringBuffer>();

		List<String> sqlcmdlist = new ArrayList<String>();

		// delete relation (it remove n record)
		sqlcmdlist.add(String.format("delete from %s where %s = %s",
				relationtable, id_field_ofrelationtable,
				id_value_ofrelationtable));

		// delete items
		Boolean resolved;
		for (I item : set) {
			resolved = false;
			for (int i = 0; i < clazza.length; i++) {
				if (item.getClass().equals(clazza[i])) {
					resolved = true;
					if (!map.containsKey(tablea[i])) {
						map.put(tablea[i], new StringBuffer());
						map.get(tablea[i]).append(String.format("%s", getId(item)));
					} else {
						map.get(tablea[i]).append(String.format(",%s", getId(item)));
					}
					break;
				}
			}
			if (!resolved) {
				throw new Exception(String.format(
						"BadUsageException - %s.class not present in the @clazza parameter",
						item.getClass().getSimpleName())
						);
			}
		}
		
		for (String table : tablea)
			if (map.containsKey(table))
				sqlcmdlist.add(String.format("delete from %s where id in (%s)",
						table, map.get(table).toString()));

		return sqlcmdlist;
	}

	/*
	 * public static List<String> deleteCloneSet(String relationtable, String
	 * id_field_ofrelationtable, Long id_value_ofrelationtable) throws Exception
	 * 
	 * Generate sql script to remove row inserted using CloneSet. Method
	 * deleteCloneSet will remove row from relation table only (since CloneSet
	 * insert only item in relation table)
	 * 
	 * Return an clone of list of sql statements for deletion.
	 * 
	 * Sample: I would like to remove cloned rows (inserted before using
	 * CloneSet).
	 * 
	 * //starting situation Problem id=1 models=(id=12, id=15, id=20) //a set of
	 * model!
	 * 
	 * Problem id=2 models=()
	 * 
	 * //after cloneSet Problem id=1 models=(id=12, id=15, id=20)
	 * 
	 * Problem id=2 models=(id=12, id=15, id=20)
	 * 
	 * ...the only way to delete cloned rows is generate SQL statement.
	 * 
	 * > List<String> sqlcmd = DataClone.deleteCloneSet("problemmodels",
	 * "problem_id", 2);
	 * 
	 * Notes: - sqlcmd will contains following statement:
	 * "delete from problemmodels where problem_id = 2"
	 */
	public static List<String> deleteCloneSet(String relationtable,
			String id_field_ofrelationtable, Long id_value_ofrelationtable)
			throws Exception {
		List<String> sqlcmdlist = new ArrayList<String>();

		// delete relation (it remove n record)
		sqlcmdlist.add(String.format("delete from %s where %s = %s",
				relationtable, id_field_ofrelationtable,
				id_value_ofrelationtable));

		return sqlcmdlist;
	}

	private static Long getId(Object instance) throws Exception {
		for (Method method : instance.getClass().getMethods()) {
			Object result;
			if (method.getName().equals("getId")) {
				result = method.invoke(instance, new Object[] {});
				return (Long) result;
			}
		}
		throw new Exception(
				String
						.format(
								"BadUsageException - instance of object %s doesn't contains any id",
								instance.getClass().getName()));
	}

	/*
	 * static <I, T extends I> Set<I> cloneSetDuplicateItems(Class[] clazza,
	 * Set<I> set)
	 * 
	 * Clone @set as clone of each element of @set
	 * 
	 * Support the management of Set of Interfaces
	 * 
	 * I is the interface T are all possible instance of I
	 * 
	 * Return an clone of @set.
	 * 
	 * Sample: we would like to copy (duplicating items) the set of model of
	 * Problem [id=1] to be able to assign it to Problem [id=2]
	 * 
	 * 
	 * //starting situation Problem id=1 models=(id=12, id=15, id=20) //a set of
	 * model!
	 * 
	 * Problem id=2 models=()
	 * 
	 * //after cloneSetDuplicateItems Problem id=1 models=(id=12, id=15, id=20)
	 * 
	 * Problem id=2 models=(id=21, id=22, id=23)
	 * 
	 * Notes: Model [id=21] will be a clone of Model [id=12] > Model
	 * model_twentyone = DataClone.cloneEntity(Model.class, model_twelve) Model
	 * [id=22] will be a clone of Model [id=15] > Model model_twentytwo =
	 * DataClone.cloneEntity(Model.class, model_fifteen) Model [id=23] will be a
	 * clone of Model [id=20] > Model model_twentythree =
	 * DataClone.cloneEntity(Model.class, model_twenty)
	 * 
	 * > Problem problem_one = currentSession().get(Problem.class, 1l);
	 * //retrieve data from the db > Problem problem_two =
	 * currentSession().get(Problem.class, 2l); //retrieve data from the db >
	 * Set<Model> clonedset = DataClone.cloneSetDuplicateItems(new
	 * Class[]{Model.class}, problem_one.getModels()); >
	 * problem_two.setModels(clonedset);
	 */
	@SuppressWarnings("unchecked")
	public static <I, T extends I> Set<I> cloneSetDuplicateItems(
			Class[] clazza, Set<I> set) throws Exception {
		return cloneSetDuplicateItems(clazza, set, null);
	}

	/*
	 * public static <I, T extends I> Set<I> cloneSetDuplicateItems(Class[]
	 * clazza, Set<I> set, Map<I, I> map_original_copied) throws Exception {
	 * 
	 * As cloneSetDuplicateItems in @map_original_copied are stored origianl
	 * values-copy of original value.
	 */
	@SuppressWarnings("unchecked")
	public static <I, T extends I> Set<I> cloneSetDuplicateItems(
			Class[] clazza, Set<I> set, Map<I, I> map_original_copied)
			throws Exception {
		if (map_original_copied != null)
			map_original_copied.clear();

		Constructor<T> _constructor;

		Set<I> _NEWset = new HashSet<I>();
		Class<T> _clazz = null;

		// I sort the set of entity by id
		// to save record in the same order (by id) as in the original)
		Set<I> sortedset = new TreeSet<I>(new CompareEntityOfOntologiUsingId());
		sortedset.addAll(set);

		for (I item : sortedset) {
			_clazz = null;
			for (Class<T> clazz : clazza) {
				if (item.getClass().equals(clazz)) {
					_clazz = clazz;
					break;
				}
			}
			if (_clazz == null)
				throw new Exception(
						String
								.format(
										"BadUsageException - %s.class not present in the @clazza parameter",
										item.getClass().getSimpleName()));

			// creation of new instance
			_constructor = _clazz.getDeclaredConstructor(new Class[] {});
			T newitem = _constructor.newInstance(new Object[] {});

			// clone new instance
			newitem = DataClone.cloneEntity(_clazz, (T) item);

			_NEWset.add(newitem);
			if (map_original_copied != null)
				map_original_copied.put(item, newitem);
		}

		logger.info("cloneSetDuplicateItems: created " + _NEWset.size()
				+ " entity(s)/relation(s) to entity");
		DataClone.counter += _NEWset.size();
		return _NEWset;
	}
}
