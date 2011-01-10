<?php


/**
 * Base class that represents a query for the 'networks' table.
 *
 * 
 *
 * @method     NetworksQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     NetworksQuery orderByNetwork($order = Criteria::ASC) Order by the network column
 *
 * @method     NetworksQuery groupById() Group by the id column
 * @method     NetworksQuery groupByNetwork() Group by the network column
 *
 * @method     NetworksQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     NetworksQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     NetworksQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     NetworksQuery leftJoinUserNetworksRelatedByUsername($relationAlias = null) Adds a LEFT JOIN clause to the query using the UserNetworksRelatedByUsername relation
 * @method     NetworksQuery rightJoinUserNetworksRelatedByUsername($relationAlias = null) Adds a RIGHT JOIN clause to the query using the UserNetworksRelatedByUsername relation
 * @method     NetworksQuery innerJoinUserNetworksRelatedByUsername($relationAlias = null) Adds a INNER JOIN clause to the query using the UserNetworksRelatedByUsername relation
 *
 * @method     NetworksQuery leftJoinUserNetworksRelatedByNetwork($relationAlias = null) Adds a LEFT JOIN clause to the query using the UserNetworksRelatedByNetwork relation
 * @method     NetworksQuery rightJoinUserNetworksRelatedByNetwork($relationAlias = null) Adds a RIGHT JOIN clause to the query using the UserNetworksRelatedByNetwork relation
 * @method     NetworksQuery innerJoinUserNetworksRelatedByNetwork($relationAlias = null) Adds a INNER JOIN clause to the query using the UserNetworksRelatedByNetwork relation
 *
 * @method     Networks findOne(PropelPDO $con = null) Return the first Networks matching the query
 * @method     Networks findOneOrCreate(PropelPDO $con = null) Return the first Networks matching the query, or a new Networks object populated from the query conditions when no match is found
 *
 * @method     Networks findOneById(int $id) Return the first Networks filtered by the id column
 * @method     Networks findOneByNetwork(string $network) Return the first Networks filtered by the network column
 *
 * @method     array findById(int $id) Return Networks objects filtered by the id column
 * @method     array findByNetwork(string $network) Return Networks objects filtered by the network column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseNetworksQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseNetworksQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Networks', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new NetworksQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    NetworksQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof NetworksQuery) {
			return $criteria;
		}
		$query = new NetworksQuery();
		if (null !== $modelAlias) {
			$query->setModelAlias($modelAlias);
		}
		if ($criteria instanceof Criteria) {
			$query->mergeWith($criteria);
		}
		return $query;
	}

	/**
	 * Find object by primary key
	 * Use instance pooling to avoid a database query if the object exists
	 * <code>
	 * $obj  = $c->findPk(12, $con);
	 * </code>
	 * @param     mixed $key Primary key to use for the query
	 * @param     PropelPDO $con an optional connection object
	 *
	 * @return    Networks|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = NetworksPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
			// the object is alredy in the instance pool
			return $obj;
		} else {
			// the object has not been requested yet, or the formatter is not an object formatter
			$criteria = $this->isKeepQuery() ? clone $this : $this;
			$stmt = $criteria
				->filterByPrimaryKey($key)
				->getSelectStatement($con);
			return $criteria->getFormatter()->init($criteria)->formatOne($stmt);
		}
	}

	/**
	 * Find objects by primary key
	 * <code>
	 * $objs = $c->findPks(array(12, 56, 832), $con);
	 * </code>
	 * @param     array $keys Primary keys to use for the query
	 * @param     PropelPDO $con an optional connection object
	 *
	 * @return    PropelObjectCollection|array|mixed the list of results, formatted by the current formatter
	 */
	public function findPks($keys, $con = null)
	{	
		$criteria = $this->isKeepQuery() ? clone $this : $this;
		return $this
			->filterByPrimaryKeys($keys)
			->find($con);
	}

	/**
	 * Filter the query by primary key
	 *
	 * @param     mixed $key Primary key to use for the query
	 *
	 * @return    NetworksQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(NetworksPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    NetworksQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(NetworksPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworksQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(NetworksPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the network column
	 * 
	 * @param     string $network The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworksQuery The current query, for fluid interface
	 */
	public function filterByNetwork($network = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($network)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $network)) {
				$network = str_replace('*', '%', $network);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(NetworksPeer::NETWORK, $network, $comparison);
	}

	/**
	 * Filter the query by a related UserNetworks object
	 *
	 * @param     UserNetworks $userNetworks  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworksQuery The current query, for fluid interface
	 */
	public function filterByUserNetworksRelatedByUsername($userNetworks, $comparison = null)
	{
		return $this
			->addUsingAlias(NetworksPeer::ID, $userNetworks->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the UserNetworksRelatedByUsername relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    NetworksQuery The current query, for fluid interface
	 */
	public function joinUserNetworksRelatedByUsername($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('UserNetworksRelatedByUsername');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'UserNetworksRelatedByUsername');
		}
		
		return $this;
	}

	/**
	 * Use the UserNetworksRelatedByUsername relation UserNetworks object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    UserNetworksQuery A secondary query class using the current class as primary query
	 */
	public function useUserNetworksRelatedByUsernameQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinUserNetworksRelatedByUsername($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'UserNetworksRelatedByUsername', 'UserNetworksQuery');
	}

	/**
	 * Filter the query by a related UserNetworks object
	 *
	 * @param     UserNetworks $userNetworks  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    NetworksQuery The current query, for fluid interface
	 */
	public function filterByUserNetworksRelatedByNetwork($userNetworks, $comparison = null)
	{
		return $this
			->addUsingAlias(NetworksPeer::NETWORK, $userNetworks->getNetwork(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the UserNetworksRelatedByNetwork relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    NetworksQuery The current query, for fluid interface
	 */
	public function joinUserNetworksRelatedByNetwork($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('UserNetworksRelatedByNetwork');
		
		// create a ModelJoin object for this join
		$join = new ModelJoin();
		$join->setJoinType($joinType);
		$join->setRelationMap($relationMap, $this->useAliasInSQL ? $this->getModelAlias() : null, $relationAlias);
		if ($previousJoin = $this->getPreviousJoin()) {
			$join->setPreviousJoin($previousJoin);
		}
		
		// add the ModelJoin to the current object
		if($relationAlias) {
			$this->addAlias($relationAlias, $relationMap->getRightTable()->getName());
			$this->addJoinObject($join, $relationAlias);
		} else {
			$this->addJoinObject($join, 'UserNetworksRelatedByNetwork');
		}
		
		return $this;
	}

	/**
	 * Use the UserNetworksRelatedByNetwork relation UserNetworks object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    UserNetworksQuery A secondary query class using the current class as primary query
	 */
	public function useUserNetworksRelatedByNetworkQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinUserNetworksRelatedByNetwork($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'UserNetworksRelatedByNetwork', 'UserNetworksQuery');
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Networks $networks Object to remove from the list of results
	 *
	 * @return    NetworksQuery The current query, for fluid interface
	 */
	public function prune($networks = null)
	{
		if ($networks) {
			$this->addUsingAlias(NetworksPeer::ID, $networks->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseNetworksQuery
