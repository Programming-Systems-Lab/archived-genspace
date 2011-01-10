<?php


/**
 * Base class that represents a query for the 'Outbox' table.
 *
 * 
 *
 * @method     OutboxQuery orderByMessageid($order = Criteria::ASC) Order by the MessageID column
 * @method     OutboxQuery orderByDate($order = Criteria::ASC) Order by the Date column
 * @method     OutboxQuery orderByFromuser($order = Criteria::ASC) Order by the FromUser column
 * @method     OutboxQuery orderByTouser($order = Criteria::ASC) Order by the ToUser column
 * @method     OutboxQuery orderByMessage($order = Criteria::ASC) Order by the Message column
 *
 * @method     OutboxQuery groupByMessageid() Group by the MessageID column
 * @method     OutboxQuery groupByDate() Group by the Date column
 * @method     OutboxQuery groupByFromuser() Group by the FromUser column
 * @method     OutboxQuery groupByTouser() Group by the ToUser column
 * @method     OutboxQuery groupByMessage() Group by the Message column
 *
 * @method     OutboxQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     OutboxQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     OutboxQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     OutboxQuery leftJoinRegistrationRelatedByFromuser($relationAlias = null) Adds a LEFT JOIN clause to the query using the RegistrationRelatedByFromuser relation
 * @method     OutboxQuery rightJoinRegistrationRelatedByFromuser($relationAlias = null) Adds a RIGHT JOIN clause to the query using the RegistrationRelatedByFromuser relation
 * @method     OutboxQuery innerJoinRegistrationRelatedByFromuser($relationAlias = null) Adds a INNER JOIN clause to the query using the RegistrationRelatedByFromuser relation
 *
 * @method     OutboxQuery leftJoinRegistrationRelatedByTouser($relationAlias = null) Adds a LEFT JOIN clause to the query using the RegistrationRelatedByTouser relation
 * @method     OutboxQuery rightJoinRegistrationRelatedByTouser($relationAlias = null) Adds a RIGHT JOIN clause to the query using the RegistrationRelatedByTouser relation
 * @method     OutboxQuery innerJoinRegistrationRelatedByTouser($relationAlias = null) Adds a INNER JOIN clause to the query using the RegistrationRelatedByTouser relation
 *
 * @method     Outbox findOne(PropelPDO $con = null) Return the first Outbox matching the query
 * @method     Outbox findOneOrCreate(PropelPDO $con = null) Return the first Outbox matching the query, or a new Outbox object populated from the query conditions when no match is found
 *
 * @method     Outbox findOneByMessageid(int $MessageID) Return the first Outbox filtered by the MessageID column
 * @method     Outbox findOneByDate(string $Date) Return the first Outbox filtered by the Date column
 * @method     Outbox findOneByFromuser(string $FromUser) Return the first Outbox filtered by the FromUser column
 * @method     Outbox findOneByTouser(string $ToUser) Return the first Outbox filtered by the ToUser column
 * @method     Outbox findOneByMessage(string $Message) Return the first Outbox filtered by the Message column
 *
 * @method     array findByMessageid(int $MessageID) Return Outbox objects filtered by the MessageID column
 * @method     array findByDate(string $Date) Return Outbox objects filtered by the Date column
 * @method     array findByFromuser(string $FromUser) Return Outbox objects filtered by the FromUser column
 * @method     array findByTouser(string $ToUser) Return Outbox objects filtered by the ToUser column
 * @method     array findByMessage(string $Message) Return Outbox objects filtered by the Message column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseOutboxQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseOutboxQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Outbox', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new OutboxQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    OutboxQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof OutboxQuery) {
			return $criteria;
		}
		$query = new OutboxQuery();
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
	 * @return    Outbox|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = OutboxPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(OutboxPeer::MESSAGEID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(OutboxPeer::MESSAGEID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the MessageID column
	 * 
	 * @param     int|array $messageid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function filterByMessageid($messageid = null, $comparison = null)
	{
		if (is_array($messageid) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(OutboxPeer::MESSAGEID, $messageid, $comparison);
	}

	/**
	 * Filter the query on the Date column
	 * 
	 * @param     string|array $date The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function filterByDate($date = null, $comparison = null)
	{
		if (is_array($date)) {
			$useMinMax = false;
			if (isset($date['min'])) {
				$this->addUsingAlias(OutboxPeer::DATE, $date['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($date['max'])) {
				$this->addUsingAlias(OutboxPeer::DATE, $date['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(OutboxPeer::DATE, $date, $comparison);
	}

	/**
	 * Filter the query on the FromUser column
	 * 
	 * @param     string $fromuser The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function filterByFromuser($fromuser = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($fromuser)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $fromuser)) {
				$fromuser = str_replace('*', '%', $fromuser);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(OutboxPeer::FROMUSER, $fromuser, $comparison);
	}

	/**
	 * Filter the query on the ToUser column
	 * 
	 * @param     string $touser The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function filterByTouser($touser = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($touser)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $touser)) {
				$touser = str_replace('*', '%', $touser);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(OutboxPeer::TOUSER, $touser, $comparison);
	}

	/**
	 * Filter the query on the Message column
	 * 
	 * @param     string $message The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function filterByMessage($message = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($message)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $message)) {
				$message = str_replace('*', '%', $message);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(OutboxPeer::MESSAGE, $message, $comparison);
	}

	/**
	 * Filter the query by a related Registration object
	 *
	 * @param     Registration $registration  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function filterByRegistrationRelatedByFromuser($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(OutboxPeer::FROMUSER, $registration->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the RegistrationRelatedByFromuser relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function joinRegistrationRelatedByFromuser($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('RegistrationRelatedByFromuser');
		
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
			$this->addJoinObject($join, 'RegistrationRelatedByFromuser');
		}
		
		return $this;
	}

	/**
	 * Use the RegistrationRelatedByFromuser relation Registration object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery A secondary query class using the current class as primary query
	 */
	public function useRegistrationRelatedByFromuserQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinRegistrationRelatedByFromuser($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'RegistrationRelatedByFromuser', 'RegistrationQuery');
	}

	/**
	 * Filter the query by a related Registration object
	 *
	 * @param     Registration $registration  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function filterByRegistrationRelatedByTouser($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(OutboxPeer::TOUSER, $registration->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the RegistrationRelatedByTouser relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function joinRegistrationRelatedByTouser($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('RegistrationRelatedByTouser');
		
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
			$this->addJoinObject($join, 'RegistrationRelatedByTouser');
		}
		
		return $this;
	}

	/**
	 * Use the RegistrationRelatedByTouser relation Registration object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery A secondary query class using the current class as primary query
	 */
	public function useRegistrationRelatedByTouserQuery($relationAlias = null, $joinType = Criteria::INNER_JOIN)
	{
		return $this
			->joinRegistrationRelatedByTouser($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'RegistrationRelatedByTouser', 'RegistrationQuery');
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Outbox $outbox Object to remove from the list of results
	 *
	 * @return    OutboxQuery The current query, for fluid interface
	 */
	public function prune($outbox = null)
	{
		if ($outbox) {
			$this->addUsingAlias(OutboxPeer::MESSAGEID, $outbox->getMessageid(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseOutboxQuery
