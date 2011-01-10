<?php


/**
 * Base class that represents a query for the 'user_networks' table.
 *
 * 
 *
 * @method     UserNetworksQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     UserNetworksQuery orderByNetwork($order = Criteria::ASC) Order by the network column
 *
 * @method     UserNetworksQuery groupByUsername() Group by the username column
 * @method     UserNetworksQuery groupByNetwork() Group by the network column
 *
 * @method     UserNetworksQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     UserNetworksQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     UserNetworksQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     UserNetworksQuery leftJoinNetworksRelatedByUsername($relationAlias = null) Adds a LEFT JOIN clause to the query using the NetworksRelatedByUsername relation
 * @method     UserNetworksQuery rightJoinNetworksRelatedByUsername($relationAlias = null) Adds a RIGHT JOIN clause to the query using the NetworksRelatedByUsername relation
 * @method     UserNetworksQuery innerJoinNetworksRelatedByUsername($relationAlias = null) Adds a INNER JOIN clause to the query using the NetworksRelatedByUsername relation
 *
 * @method     UserNetworksQuery leftJoinNetworksRelatedByNetwork($relationAlias = null) Adds a LEFT JOIN clause to the query using the NetworksRelatedByNetwork relation
 * @method     UserNetworksQuery rightJoinNetworksRelatedByNetwork($relationAlias = null) Adds a RIGHT JOIN clause to the query using the NetworksRelatedByNetwork relation
 * @method     UserNetworksQuery innerJoinNetworksRelatedByNetwork($relationAlias = null) Adds a INNER JOIN clause to the query using the NetworksRelatedByNetwork relation
 *
 * @method     UserNetworks findOne(PropelPDO $con = null) Return the first UserNetworks matching the query
 * @method     UserNetworks findOneOrCreate(PropelPDO $con = null) Return the first UserNetworks matching the query, or a new UserNetworks object populated from the query conditions when no match is found
 *
 * @method     UserNetworks findOneByUsername(string $username) Return the first UserNetworks filtered by the username column
 * @method     UserNetworks findOneByNetwork(string $network) Return the first UserNetworks filtered by the network column
 *
 * @method     array findByUsername(string $username) Return UserNetworks objects filtered by the username column
 * @method     array findByNetwork(string $network) Return UserNetworks objects filtered by the network column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseUserNetworksQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseUserNetworksQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'UserNetworks', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new UserNetworksQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    UserNetworksQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof UserNetworksQuery) {
			return $criteria;
		}
		$query = new UserNetworksQuery();
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
	 * <code>
	 * $obj = $c->findPk(array(12, 34), $con);
	 * </code>
	 * @param     array[$username, $network] $key Primary key to use for the query
	 * @param     PropelPDO $con an optional connection object
	 *
	 * @return    UserNetworks|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = UserNetworksPeer::getInstanceFromPool(serialize(array((string) $key[0], (string) $key[1]))))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * $objs = $c->findPks(array(array(12, 56), array(832, 123), array(123, 456)), $con);
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
	 * @return    UserNetworksQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		$this->addUsingAlias(UserNetworksPeer::USERNAME, $key[0], Criteria::EQUAL);
		$this->addUsingAlias(UserNetworksPeer::NETWORK, $key[1], Criteria::EQUAL);
		
		return $this;
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    UserNetworksQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		if (empty($keys)) {
			return $this->add(null, '1<>1', Criteria::CUSTOM);
		}
		foreach ($keys as $key) {
			$cton0 = $this->getNewCriterion(UserNetworksPeer::USERNAME, $key[0], Criteria::EQUAL);
			$cton1 = $this->getNewCriterion(UserNetworksPeer::NETWORK, $key[1], Criteria::EQUAL);
			$cton0->addAnd($cton1);
			$this->addOr($cton0);
		}
		
		return $this;
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserNetworksQuery The current query, for fluid interface
	 */
	public function filterByUsername($username = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($username)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $username)) {
				$username = str_replace('*', '%', $username);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(UserNetworksPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the network column
	 * 
	 * @param     string $network The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserNetworksQuery The current query, for fluid interface
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
		return $this->addUsingAlias(UserNetworksPeer::NETWORK, $network, $comparison);
	}

	/**
	 * Filter the query by a related Networks object
	 *
	 * @param     Networks $networks  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserNetworksQuery The current query, for fluid interface
	 */
	public function filterByNetworksRelatedByUsername($networks, $comparison = null)
	{
		return $this
			->addUsingAlias(UserNetworksPeer::USERNAME, $networks->getId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the NetworksRelatedByUsername relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    UserNetworksQuery The current query, for fluid interface
	 */
	public function joinNetworksRelatedByUsername($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('NetworksRelatedByUsername');
		
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
			$this->addJoinObject($join, 'NetworksRelatedByUsername');
		}
		
		return $this;
	}

	/**
	 * Use the NetworksRelatedByUsername relation Networks object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    NetworksQuery A secondary query class using the current class as primary query
	 */
	public function useNetworksRelatedByUsernameQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinNetworksRelatedByUsername($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'NetworksRelatedByUsername', 'NetworksQuery');
	}

	/**
	 * Filter the query by a related Networks object
	 *
	 * @param     Networks $networks  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserNetworksQuery The current query, for fluid interface
	 */
	public function filterByNetworksRelatedByNetwork($networks, $comparison = null)
	{
		return $this
			->addUsingAlias(UserNetworksPeer::NETWORK, $networks->getNetwork(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the NetworksRelatedByNetwork relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    UserNetworksQuery The current query, for fluid interface
	 */
	public function joinNetworksRelatedByNetwork($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('NetworksRelatedByNetwork');
		
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
			$this->addJoinObject($join, 'NetworksRelatedByNetwork');
		}
		
		return $this;
	}

	/**
	 * Use the NetworksRelatedByNetwork relation Networks object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    NetworksQuery A secondary query class using the current class as primary query
	 */
	public function useNetworksRelatedByNetworkQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinNetworksRelatedByNetwork($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'NetworksRelatedByNetwork', 'NetworksQuery');
	}

	/**
	 * Exclude object from result
	 *
	 * @param     UserNetworks $userNetworks Object to remove from the list of results
	 *
	 * @return    UserNetworksQuery The current query, for fluid interface
	 */
	public function prune($userNetworks = null)
	{
		if ($userNetworks) {
			$this->addCond('pruneCond0', $this->getAliasedColName(UserNetworksPeer::USERNAME), $userNetworks->getUsername(), Criteria::NOT_EQUAL);
			$this->addCond('pruneCond1', $this->getAliasedColName(UserNetworksPeer::NETWORK), $userNetworks->getNetwork(), Criteria::NOT_EQUAL);
			$this->combine(array('pruneCond0', 'pruneCond1'), Criteria::LOGICAL_OR);
	  }
	  
		return $this;
	}

} // BaseUserNetworksQuery
