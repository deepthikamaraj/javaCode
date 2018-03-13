package com.cognizant.opserv.sp.core.dao;

import java.io.Serializable;
import java.util.List;
 
import javax.persistence.EntityManager;

import com.cognizant.peg.core.util.JPAQuery;

/**
 * Interface represents implementation of generic DAO. The implementation for
 * GenericDAO could support any JPA Implementation like OpenJPA or Hibernate or
 * Toplink providers
 * 
 * @param <T>
 *            Object type
 * @param <I>
 *            Primary Key type
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface GisDAO {

	/**
	 * Remove an entity object from the persistent unit
	 * 
	 * @param entity
	 *            object to be removed.
	 * 
	 */
	<T extends Serializable> void remove(T entity);

	/**
	 * Stores a new entity object in to the persistent store
	 * 
	 * @param entity
	 *            object to be persisted
	 * 
	 */
	<T extends Serializable> T store(T entity);

	/**
	 * Save all changes made to an existing entity object
	 * 
	 * @param entity
	 *            object to be persisted
	 * 
	 */
	<T extends Serializable> T update(T entity);

	/**
	 * Retrieve an object based on given primary key value.
	 * 
	 * @param Object
	 *            the primary key value, it could be a primitive value or a
	 *            composite class object.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	<T, I extends Serializable> T get(Class<T> clazz, I objectId);

	/**
	 * Retrieve entities based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param JPAQuery
	 *            The query criteria to be searched
	 * @param index
	 *            The index of the search
	 * @param maxresult
	 *            The page size of the search
	 * @return an Object if it exists against given criteria. Returns null if
	 *         not found
	 */
	<T extends Object> List<T> findEntities(JPAQuery jpaQuery, int index, int maxresult);

	/**
	 * Fetch count of records based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param JPAQuery
	 *            The query criteria to be searched
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	<T extends Object> List<T> countEntities(JPAQuery jpaQuery);

	/**
	 * Retrieve entities based on given search criteria using JPA named query.
	 * 
	 * @param queryName
	 *            The named query name
	 * @param queryParam
	 *            The named query parameters
	 * @param index
	 *            The index of the search
	 * @param maxresult
	 *            The page size of the search
	 * @return an Object if it exists against given criteria. Returns null if
	 *         not found
	 */
	<T extends Object> List<T> findEntitiesByNamedQuery(final String queryName, final Object queryParam,
			final int index, final int maxresult);

	/**
	 * Fetch count of records based on given search criteria using JPA named query.
	 * 
	 * @param queryName
	 *            The named query name
	 * @param queryParam
	 *            The named query parameters
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	<T extends Object> List<T> countEntitiesNamedQuery(String queryName, Object queryParam);

	/**
	 * Retrieve records based on given search criteria using JPA named query.
	 * 
	 * @param queryName
	 *            The named query name
	 * @param queryParam
	 *            The named query parameters
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	<T extends Object> List<T> findEntitiesByNamedQuery(final String queryName, final Object queryParam);
	
	/**
	 * Find entities by named query for date.
	 *
	 * @param <T> the generic type
	 * @param queryName the query name
	 * @param list the list
	 * @param index the index
	 * @param maxResult the max result
	 * @return the list
	 */
	<T extends Object> List<T> findEntitiesByNamedQueryForDate(final String queryName,final List<Object> list,int index,int maxResult);
	
	/**
	 * Find entities by named query multi cond.
	 *
	 * @param <T> the generic type
	 * @param queryName the query name
	 * @param queryParam the query param
	 * @param index the index
	 * @param maxresult the maxresult
	 * @return the list
	 */
	<T extends Object> List<T> findEntitiesByNamedQueryMultiCond(final String queryName, final List queryParam,
			final int index, final int maxresult);

	/**
	 * Method to execute a Native SQL query.
	 * 
	 * @param queryName
	 *            The named query name
	 *  @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	<T extends Object> List<T> findByNativeQuery(final String queryName);
	
	/**
	 * Execute update native query.
	 *
	 * @param <T> the generic type
	 * @param queryName the query name
	 * @return the integer
	 */
	<T extends Object> Integer executeUpdateNativeQuery(final String queryName);
	
	/**
	 * Find entities by named query.
	 *
	 * @param <T> the generic type
	 * @param queryName the query name
	 * @return the list
	 */
	<T extends Object> List<T> findEntitiesByNamedQuery(final String queryName);
	
	 /**
 	 * Update entities by named query multi cond.
 	 *
 	 * @param queryName the query name
 	 * @param queryParam the query param
 	 * @param index the index
 	 * @param maxresult the maxresult
 	 */
 	void updateEntitiesByNamedQueryMultiCond(final String queryName, final List queryParam,
			final int index, final int maxresult);
	 
 	/**
 	 * Find entities by named in query.
 	 *
 	 * @param <T> the generic type
 	 * @param queryName the query name
 	 * @param queryParam the query param
 	 * @param index the index
 	 * @param maxresult the maxresult
 	 * @return the list
 	 */
 	<T extends Object> List<T> findEntitiesByNamedInQuery(final String queryName, final List queryParam,
			final int index, final int maxresult);
	
	/**
	 * Execute native query.
	 *
	 * @param queryName the query name
	 * @return the int
	 */
	int executeNativeQuery(final String queryName);
	
	/**
	 * Find entities by query.
	 *
	 * @param queryName the query name
	 * @param trans the trans
	 * @return the list
	 */
	List<?> findEntitiesByQuery(final String queryName,Class<?> trans);
	
	 /**
 	 * Find entities by build query.
 	 *
 	 * @param <T> the generic type
 	 * @param query the query
 	 * @param index the index
 	 * @param maxresult the maxresult
 	 * @return the list
 	 */
 	<T extends Object> List<T> findEntitiesByBuildQuery(final String query, final int index, final int maxresult);
	
	 /**
 	 * Gets the entity manager from jpa.
 	 *
 	 * @return the entity manager from jpa
 	 */
 	EntityManager getEntityManagerFromJPA();
	 
	 /**
 	 * Find entities by build queries.
 	 *
 	 * @param <T> the generic type
 	 * @param query the query
 	 * @param queryParam the query param
 	 * @param index the index
 	 * @param maxresult the maxresult
 	 * @return the list
 	 */
 	<T extends Object> List<T> findEntitiesByBuildQueries(final String query,final List queryParam, final int index, final int maxresult);

	/**
	 * Count entities by named query multi cond.
	 *
	 * @param <T> the generic type
	 * @param queryName the query name
	 * @param queryParam the query param
	 * @param index the index
	 * @param maxresult the maxresult
	 * @return the object
	 */
	<T extends Object> Object countEntitiesByNamedQueryMultiCond(
				final String queryName, final List queryParam, final int index,
				final int maxresult);
		
	 /**
 	 * Find by native query multi cond.
 	 *
 	 * @param queryName the query name
 	 * @param queryParam the query param
 	 * @param index the index
 	 * @param maxresult the maxresult
 	 * @return the list
 	 */
 	public List findByNativeQueryMultiCond(final String queryName,
                   final List queryParam, final int index, final int maxresult) ;
	                
     /**
      * Generate id.
      *
      * @param group the group
      * @param table the table
      * @param valueCol the value col
      * @param pKeyColumn the key column
      * @return the long
      */
     public Long generateID(final String group,final String table,final String valueCol,final String pKeyColumn ) ;
}
