package com.cognizant.opserv.sp.core.dao;

import java.io.Serializable;
import java.util.Iterator; 
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.cfg.ObjectNameNormalizer;
import org.hibernate.dialect.Dialect;
import org.hibernate.id.MultipleHiLoPerTableGenerator;
import org.hibernate.internal.SessionImpl;
import org.hibernate.type.BigIntegerType;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for GenericDAO.
 * 
 * @param <T>
 *            Object type
 * @param <I>
 *            Primary Key type
 * @author JCoE team
 * @version 1.0
 * 
 */

@SuppressWarnings({ "unchecked", "deprecation","rawtypes" })
public class GisDAOImpl extends JpaDaoSupport implements GisDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(GisDAOImpl.class);

	/**
	 * Stores a new entity object in to the persistent store
	 * 
	 * @param entity
	 *            object to be persisted
	 * 
	 */
	public <T extends Serializable> T store(final T toStore) {
		LOGGER.info("=========== Store ===========");
		LOGGER.info("Store Entity : " + toStore);		
		getJpaTemplate().persist(toStore);
		getJpaTemplate().flush();
		return toStore;
	}

	/**
	 * Save all changes made to an existing entity object
	 * 
	 * @param entity
	 *            object to be persisted
	 * 
	 */
	public <T extends Serializable> T update(final T toStore) {
		LOGGER.info("=========== Update ===========");
		LOGGER.info("Udate Entity : " + toStore);
		T merge = getJpaTemplate().merge(toStore);
		getJpaTemplate().flush();
		return merge;
	}

	/**
	 * Remove an entity object from the persistent unit
	 * 
	 * @param entity
	 *            object to be removed.
	 * 
	 */
	public <T extends Serializable> void remove(final T entity) {
		LOGGER.info("=========== Remove Entity ===========");
		LOGGER.info("Enity : " + entity);
		getJpaTemplate().flush();
		getJpaTemplate().remove(entity);
		
	}

	/**
	 * Retrieve an object based on given primary key value.
	 * 
	 * @param Object
	 *            the primary key value, it could be a primitive value or a
	 *            composite class object.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public <T, I extends Serializable> T get(final Class<T> clazz, final I identification) {
		LOGGER.info("=========== Find By Id ===========");
		LOGGER.info("Id : " + identification);
		return getJpaTemplate().find(clazz, identification);
	}

	/**
	 * Retrieve entities based on given search criteria.
	 * 
	 * @param queryName
	 *            The queryName to be searched
	 * @param index
	 *            The index of the search
	 * @param maxresult
	 *            The page size of the search
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public <T extends Object> List<T> findEntitiesByNamedQuery(final String queryName, final Object queryParam,
			final int index, final int maxresult) {
		return (List<T>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
				//LOGGER.info("=========== findEntities ===========");
				//LOGGER.info("queryName : " + queryName + "index : " + index + "maxresult : " + maxresult);

				final Query queryNamed = entityMgr.createNamedQuery(queryName);
				queryNamed.setParameter(1, queryParam);
				if (index != -1) {
					queryNamed.setFirstResult(index);
				}
				if (maxresult != -1) {
					queryNamed.setMaxResults(maxresult);
				}
				return queryNamed.getResultList();
			}
		});
	}

	/**
	 * Retrieve entities based on given search criteria.
	 * 
	 * @param queryName
	 *            The queryName to be searched
	 * @param index
	 *            The index of the search
	 * @param maxresult
	 *            The page size of the search
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */

	public <T extends Object> List<T> findEntitiesByNamedQuery(final String queryName, final Object queryParam) {
		return (List<T>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
				LOGGER.info("=========== findEntities ===========");

				final Query namedQuery = entityMgr.createNamedQuery(queryName);
				namedQuery.setParameter(1, queryParam);
				return namedQuery.getResultList();
			}
		});
	}
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
	public <T extends Object> List<T> findEntitiesByNamedQueryForDate(final String queryName, final List<Object> list, int index, int maxResult) {
		LOGGER.info("=========== findEntitiesByNamedQueryForDate ===========");
		LOGGER.info("queryName :::::::: " + queryName + "======== queryParam :::::::: " + list);

		return (List<T>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {

				final Query queryJPQL = entityMgr.createNamedQuery(queryName);
				for(int pos = 0; pos < list.size();pos++){
					queryJPQL.setParameter(pos+1,list.get(pos));
				}				
				return queryJPQL.getResultList();
			}
		});
	}
	
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
	@Transactional
	public <T extends Object> List<T> findEntities(final JPAQuery jpaQuery, final int index, final int maxresult) {
		return (List<T>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
				final String query = jpaQuery.executeQueryLiteral();
				final Query queryJPQL = entityMgr.createQuery(query);
				
				/*Code Added to cache the query if cacheable is true */
				
				if(jpaQuery.isCacheable()){
					queryJPQL.setHint("org.hibernate.cacheable", true);
					queryJPQL.setHint("org.hibernate.cacheRegion", "query.entityQueryCache");
				}			
				
				if (index != -1) {
					queryJPQL.setFirstResult(index);
				}
				if (maxresult != -1) {
					queryJPQL.setMaxResults(maxresult);
				}
				final List<T> result = queryJPQL.getResultList();
				return result;
				
			}
		});
	}

	/**
	 * Fetch count of records based on given search criteria using Dynamic
	 * JPAQL.
	 * 
	 * @param JPAQuery
	 *            The query criteria to be searched
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */

	public <T extends Object> List<T> countEntities(final JPAQuery jpaQuery) {
		return (List<T>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
				final String query = jpaQuery.executeQueryLiteral();
				final Query queryJPQL = entityMgr.createQuery(query);
				final List<T> result = queryJPQL.getResultList();
				return result;
			}
		});
	}

	/**
	 * Fetch count of records based on given search criteria using JPA named
	 * query.
	 * 
	 * @param queryName
	 *            The named query name
	 * @param queryParam
	 *            The named query parameters
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */

	public <T extends Object> List<T> countEntitiesNamedQuery(final String queryName, final Object queryParam) {
		//LOGGER.info("=========== countEntitiesNamedQuery ===========");
		//LOGGER.info("queryName : " + queryName + "queryParam : " + queryParam);

		return (List<T>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {

				final Query queryJPQL = entityMgr.createNamedQuery(queryName);
				queryJPQL.setParameter(1, queryParam);
				return queryJPQL.getResultList();
			}
		});
	}

	/**
	 * Method to execute a Native SQL query.
	 * 
	 * @param queryName
	 *            The named query name
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public List findByNativeQuery(final String queryName) {
		return (List) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
				LOGGER.info("=========== findEntities ===========");
				final Query queryJPQL = entityMgr.createNativeQuery(queryName);				
				return queryJPQL.getResultList();
			}
		});
	}
	
	/**
	 * Method to execute a Native SQL query for INSERT, CREATE, UPDATE or DELETE.
	 * 
	 * @param queryName
	 *            The named query name
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	@Transactional
	public Integer executeUpdateNativeQuery(final String queryName) {
		return (Integer) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
				//LOGGER.info("=========== executeUpdateNativeQuery ===========");
				final Query queryJPQL = entityMgr.createNativeQuery(queryName);
				int sqlStatusCode;
				//entityMgr.getTransaction().begin();
				sqlStatusCode = queryJPQL.executeUpdate();
				//entityMgr.getTransaction().commit();
				return Integer.valueOf(sqlStatusCode);
			}
		});
	}
	
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
	@Transactional
	public <T extends Object> List<T> findEntitiesByNamedQueryMultiCond(final String queryName, final List queryParam,
	final int index, final int maxresult) {
		return (List<T>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
				//LOGGER.info("=========== findEntities ===========");
				//LOGGER.info("queryName : " + queryName + "index : " + index + "maxresult : " + maxresult);
		
				final Query queryNamed = entityMgr.createNamedQuery(queryName);
				
				//Setting the Query Cache for the query
				//queryNamed.setHint("org.hibernate.cacheable", true);
				//queryNamed.setHint("org.hibernate.cacheRegion", "query.entityQueryCache");
					
				if(queryParam != null){
					Iterator itr = queryParam.iterator();
					int paramCounter = 1;
					while(itr.hasNext()){
						queryNamed.setParameter(paramCounter, itr.next());
						paramCounter++;
					}
				}
				if (index != -1) {
					queryNamed.setFirstResult(index);
				}
				if (maxresult != -1) {
					queryNamed.setMaxResults(maxresult);
				}
				return queryNamed.getResultList();
			}
		});
	}
		
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
	public <T extends Object> List<T> findEntitiesByNamedInQuery(final String queryName, final List queryParam,
				final int index, final int maxresult){
			return (List<T>) getJpaTemplate().execute(new JpaCallback() {
				public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
					//LOGGER.info("=========== findEntities ===========");
					//LOGGER.info("queryName : " + queryName + "index : " + index + "maxresult : " + maxresult);

					final Query queryNamed = entityMgr.createNamedQuery(queryName);
					if(queryParam != null){
						queryNamed.setParameter("paramList", queryParam);
					}
					if (index != -1) {
						queryNamed.setFirstResult(index);
					}
					if (maxresult != -1) {
						queryNamed.setMaxResults(maxresult);
					}
					return queryNamed.getResultList();
				}
			});

	}
	 /**
 	 * Update entities by named query multi cond.
 	 *
 	 * @param queryName the query name
 	 * @param queryParam the query param
 	 * @param index the index
 	 * @param maxresult the maxresult
 	 */
		@Override
		public void updateEntitiesByNamedQueryMultiCond(final String queryName,
				final List queryParam, final int index, final int maxresult) {
			 getJpaTemplate().execute(new JpaCallback() {				 
					public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
						//LOGGER.info("=========== findEntities ===========");
						//LOGGER.info("queryName : " + queryName + "index : " + index + "maxresult : " + maxresult);

						final Query queryNamed = entityMgr.createNamedQuery(queryName);
						if(queryParam != null){
							Iterator itr = queryParam.iterator();
							int paramCounter = 1;
							while(itr.hasNext()){
								queryNamed.setParameter(paramCounter, itr.next());
								paramCounter++;
							}
						}
						if (index != -1) {
							queryNamed.setFirstResult(index);
						}
						if (maxresult != -1) {
							queryNamed.setMaxResults(maxresult);
						}
						return queryNamed.executeUpdate();
					}
				});
			
		}
		/**
		 * Find entities by named query.
		 *
		 * @param <T> the generic type
		 * @param queryName the query name
		 * @return the list
		 */
		@Override
		public <T> List<T> findEntitiesByNamedQuery(final String queryName) {
			return (List<T>) getJpaTemplate().execute(new JpaCallback() {
				public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
					//LOGGER.info("=========== findEntities ===========");
					final Query namedQuery = entityMgr.createNamedQuery(queryName);					
					return namedQuery.getResultList();
				}
			});
		}

		/**
	 	 * Gets the entity manager from jpa.
	 	 *
	 	 * @return the entity manager from jpa
	 	 */
		public EntityManager getEntityManagerFromJPA()
		{
			 return getJpaTemplate().getEntityManagerFactory().createEntityManager();
		}
			
		/**
		 * Execute update native query.
		 *
		 * @param <T> the generic type
		 * @param queryName the query name
		 * @return the integer
		 */
		@Override
		public int executeNativeQuery(final String queryName) {
			return (int) getJpaTemplate().execute(new JpaCallback() {
				public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
					//LOGGER.info("=========== findEntities ===========");
					final Query queryJPQL = entityMgr.createNativeQuery(queryName);				
					return queryJPQL.executeUpdate();
				}
			});
		}
		/**
		 * Find entities by query.
		 *
		 * @param queryName the query name
		 * @param trans the trans
		 * @return the list
		 */		
		@Override
		public List<?> findEntitiesByQuery(final String queryName,final Class<?> transformer) {
			return (List<?>) getJpaTemplate().execute(new JpaCallback() {
				public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
					//LOGGER.info("=========== findEntities ===========");
					final Query namedQuery = entityMgr.createNativeQuery(queryName,transformer);
					//namedQuery.unwrap(DataSetDescription.class);
					return namedQuery.getResultList();
				}
			});
		}
		 /**
	 	 * Find entities by build query.
	 	 *
	 	 * @param <T> the generic type
	 	 * @param query the query
	 	 * @param index the index
	 	 * @param maxresult the maxresult
	 	 * @return the list
	 	 */
		public <T extends Object> List<T> findEntitiesByBuildQuery(final String query, final int index, final int maxresult){
			return (List<T>) getJpaTemplate().execute(new JpaCallback() {
				public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
					//LOGGER.info("=========== findEntities ===========");
					//LOGGER.info("queryName : " + query + "index : " + index + "maxresult : " + maxresult);

					final Query queryNamed = entityMgr.createQuery(query);
					
					if (index != -1) {
						queryNamed.setFirstResult(index);
					}
					if (maxresult != -1) {
						queryNamed.setMaxResults(maxresult);
					}
					return queryNamed.getResultList();
				}
			});

		}		
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
		public <T extends Object> List<T> findEntitiesByBuildQueries(final String query,final List queryParam, final int index, final int maxresult){
			return (List<T>) getJpaTemplate().execute(new JpaCallback() {
				public Object doInJpa(final EntityManager entityMgr) throws PersistenceException {
					//LOGGER.info("=========== findEntities ===========");
					//LOGGER.info("queryName : " + query + "index : " + index + "maxresult : " + maxresult);

					final Query queryNamed = entityMgr.createQuery(query);
					if(queryParam != null){
						final Iterator itr = queryParam.iterator();
						int paramCounter = 1;
						while(itr.hasNext()){
							queryNamed.setParameter(paramCounter, itr.next());
							paramCounter++;
						}
					}
					if (index != -1) {
						queryNamed.setFirstResult(index);
					}
					if (maxresult != -1) {
						queryNamed.setMaxResults(maxresult);
					}
					return queryNamed.getResultList();
				}
			});

		}
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
		//Added For CR Locking
		@Transactional
		public <T extends Object> Object countEntitiesByNamedQueryMultiCond(
				final String queryName, final List queryParam, final int index,
				final int maxresult) {
			return (Object) getJpaTemplate().execute(new JpaCallback() {
				public Object doInJpa(final EntityManager entityMgr)
						throws PersistenceException {
					LOGGER.info("=========== findEntities ===========");
					LOGGER.info("queryName : " + queryName + "index : " + index
							+ "maxresult : " + maxresult);

					final Query queryNamed = entityMgr.createNamedQuery(queryName);

					// Setting the Query Cache for the query
					/*
					 * queryNamed.setHint("org.hibernate.cacheable", true);
					 * queryNamed.setHint("org.hibernate.cacheRegion",
					 * "query.entityQueryCache");
					 */

					if (queryParam != null) {
						Iterator itr = queryParam.iterator();
						int paramCounter = 1;
						while (itr.hasNext()) {
							queryNamed.setParameter(paramCounter, itr.next());
							paramCounter++;
						}
					}
					if (index != -1) {
						queryNamed.setFirstResult(index);
					}
					if (maxresult != -1) {
						queryNamed.setMaxResults(maxresult);
					}
					return queryNamed.getSingleResult();
				}
			});
		}	
		
		/**
	       * Method to execute a Native SQL query with a MultiCondition positional
	       * parameter.
	       * 
	        * @param queryName
	       *            The named query name
	       * @return an Object if it exists against given query parameter. Returns
	       *         null if not found
	       */
	       public List findByNativeQueryMultiCond(final String queryName,
	                     final List queryParam, final int index, final int maxresult) {
	          return (List) getJpaTemplate().execute(new JpaCallback() {
                 public Object doInJpa(final EntityManager entityMgr)
                              throws PersistenceException {
                       // LOGGER.info("=========== findEntities ===========");

                       final Query queryJPQL = entityMgr.createNativeQuery(queryName);

                       if (queryParam != null) {
                              Iterator itr = queryParam.iterator();
                              int paramCounter = 1;
                              while (itr.hasNext()) {
                                     queryJPQL.setParameter(paramCounter, itr.next());
                                     paramCounter++;
                              }
                       }
                       if (index != -1) {
                              queryJPQL.setFirstResult(index);
                       }
                       if (maxresult != -1) {
                              queryJPQL.setMaxResults(maxresult);
                       }

                       return queryJPQL.getResultList();
                 }
          });
	}      
   /**
    * Generate id.
    *
    * @param group the group
    * @param table the table
    * @param valueCol the value col
    * @param pKeyColumn the key column
    * @return the long
    */
	/*Code To generate Unique Number From  t_unique_key*/
	@Override
	public Long generateID(final String group, final String table,
			final String valueCol, final String pKeyColumn) {
		return (Long) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager entityMgr)
					throws PersistenceException {

				ObjectNameNormalizer normalizer = new ObjectNameNormalizer() {

					@Override
					protected boolean isUseQuotedIdentifiersGlobally() {
						return false;
					}

					@Override
					protected NamingStrategy getNamingStrategy() {
						return new Configuration().getNamingStrategy();
					}
				};

				MultipleHiLoPerTableGenerator gen = new MultipleHiLoPerTableGenerator();
				Properties properties = new Properties();
				properties.put("table", table);
				properties.put("value_column", valueCol);
				properties.put("primary_key_column", pKeyColumn);
				properties.put("primary_key_value", group);
				properties.put("hibernate.dialect",
						entityMgr.getEntityManagerFactory().getProperties()
								.get("hibernate.dialect"));
				// "org.hibernate.dialect.MySQL5Dialect"
				properties.put("identifier_normalizer", normalizer);
				gen.configure(new BigIntegerType(), properties,
						Dialect.getDialect(properties));
				Serializable ss = gen.generate(
						(SessionImpl) entityMgr.getDelegate(), new Object());
				return Long.parseLong(ss.toString());
			}
		});
	}

}
