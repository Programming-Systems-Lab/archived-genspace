<?php


/**
 * Base class that represents a query for the 'tig_pairs' table.
 *
 * 
 *
 * @method     TigPairsQuery orderByNid($order = Criteria::ASC) Order by the nid column
 * @method     TigPairsQuery orderByUid($order = Criteria::ASC) Order by the uid column
 * @method     TigPairsQuery orderByPkey($order = Criteria::ASC) Order by the pkey column
 * @method     TigPairsQuery orderByPval($order = Criteria::ASC) Order by the pval column
 * @method     TigPairsQuery orderById($order = Criteria::ASC) Order by the id column
 *
 * @method     TigPairsQuery groupByNid() Group by the nid column
 * @method     TigPairsQuery groupByUid() Group by the uid column
 * @method     TigPairsQuery groupByPkey() Group by the pkey column
 * @method     TigPairsQuery groupByPval() Group by the pval column
 * @method     TigPairsQuery groupById() Group by the id column
 *
 * @method     TigPairsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     TigPairsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     TigPairsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     TigPairsQuery leftJoinTigNodes($relationAlias = null) Adds a LEFT JOIN clause to the query using the TigNodes relation
 * @method     TigPairsQuery rightJoinTigNodes($relationAlias = null) Adds a RIGHT JOIN clause to the query using the TigNodes relation
 * @method     TigPairsQuery innerJoinTigNodes($relationAlias = null) Adds a INNER JOIN clause to the query using the TigNodes relation
 *
 * @method     TigPairsQuery leftJoinTigUsers($relationAlias = null) Adds a LEFT JOIN clause to the query using the TigUsers relation
 * @method     TigPairsQuery rightJoinTigUsers($relationAlias = null) Adds a RIGHT JOIN clause to the query using the TigUsers relation
 * @method     TigPairsQuery innerJoinTigUsers($relationAlias = null) Adds a INNER JOIN clause to the query using the TigUsers relation
 *
 * @method     TigPairs findOne(PropelPDO $con = null) Return the first TigPairs matching the query
 * @method     TigPairs findOneOrCreate(PropelPDO $con = null) Return the first TigPairs matching the query, or a new TigPairs object populated from the query conditions when no match is found
 *
 * @method     TigPairs findOneByNid(string $nid) Return the first TigPairs filtered by the nid column
 * @method     TigPairs findOneByUid(string $uid) Return the first TigPairs filtered by the uid column
 * @method     TigPairs findOneByPkey(string $pkey) Return the first TigPairs filtered by the pkey column
 * @method     TigPairs findOneByPval(string $pval) Return the first TigPairs filtered by the pval column
 * @method     TigPairs findOneById(int $id) Return the first TigPairs filtered by the id column
 *
 * @method     array findByNid(string $nid) Return TigPairs objects filtered by the nid column
 * @method     array findByUid(string $uid) Return TigPairs objects filtered by the uid column
 * @method     array findByPkey(string $pkey) Return TigPairs objects filtered by the pkey column
 * @method     array findByPval(string $pval) Return TigPairs objects filtered by the pval column
 * @method     array findById(int $id) Return TigPairs objects filtered by the id column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseTigPairsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseTigPairsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'TigPairs', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new TigPairsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    TigPairsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof TigPairsQuery) {
			return $criteria;
		}
		$query = new TigPairsQuery();
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
	 * @return    TigPairs|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = TigPairsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(TigPairsPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(TigPairsPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the nid column
	 * 
	 * @param     string|array $nid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function filterByNid($nid = null, $comparison = null)
	{
		if (is_array($nid)) {
			$useMinMax = false;
			if (isset($nid['min'])) {
				$this->addUsingAlias(TigPairsPeer::NID, $nid['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($nid['max'])) {
				$this->addUsingAlias(TigPairsPeer::NID, $nid['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TigPairsPeer::NID, $nid, $comparison);
	}

	/**
	 * Filter the query on the uid column
	 * 
	 * @param     string|array $uid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function filterByUid($uid = null, $comparison = null)
	{
		if (is_array($uid)) {
			$useMinMax = false;
			if (isset($uid['min'])) {
				$this->addUsingAlias(TigPairsPeer::UID, $uid['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($uid['max'])) {
				$this->addUsingAlias(TigPairsPeer::UID, $uid['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(TigPairsPeer::UID, $uid, $comparison);
	}

	/**
	 * Filter the query on the pkey column
	 * 
	 * @param     string $pkey The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function filterByPkey($pkey = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($pkey)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $pkey)) {
				$pkey = str_replace('*', '%', $pkey);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(TigPairsPeer::PKEY, $pkey, $comparison);
	}

	/**
	 * Filter the query on the pval column
	 * 
	 * @param     string $pval The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function filterByPval($pval = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($pval)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $pval)) {
				$pval = str_replace('*', '%', $pval);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(TigPairsPeer::PVAL, $pval, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(TigPairsPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query by a related TigNodes object
	 *
	 * @param     TigNodes $tigNodes  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function filterByTigNodes($tigNodes, $comparison = null)
	{
		return $this
			->addUsingAlias(TigPairsPeer::NID, $tigNodes->getNid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the TigNodes relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function joinTigNodes($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('TigNodes');
		
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
			$this->addJoinObject($join, 'TigNodes');
		}
		
		return $this;
	}

	/**
	 * Use the TigNodes relation TigNodes object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigNodesQuery A secondary query class using the current class as primary query
	 */
	public function useTigNodesQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinTigNodes($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'TigNodes', 'TigNodesQuery');
	}

	/**
	 * Filter the query by a related TigUsers object
	 *
	 * @param     TigUsers $tigUsers  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function filterByTigUsers($tigUsers, $comparison = null)
	{
		return $this
			->addUsingAlias(TigPairsPeer::UID, $tigUsers->getUid(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the TigUsers relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function joinTigUsers($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('TigUsers');
		
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
			$this->addJoinObject($join, 'TigUsers');
		}
		
		return $this;
	}

	/**
	 * Use the TigUsers relation TigUsers object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    TigUsersQuery A secondary query class using the current class as primary query
	 */
	public function useTigUsersQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinTigUsers($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'TigUsers', 'TigUsersQuery');
	}

	/**
	 * Exclude object from result
	 *
	 * @param     TigPairs $tigPairs Object to remove from the list of results
	 *
	 * @return    TigPairsQuery The current query, for fluid interface
	 */
	public function prune($tigPairs = null)
	{
		if ($tigPairs) {
			$this->addUsingAlias(TigPairsPeer::ID, $tigPairs->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseTigPairsQuery
