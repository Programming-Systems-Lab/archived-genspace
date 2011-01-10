<?php


/**
 * Base class that represents a query for the 'Inbox' table.
 *
 * 
 *
 * @method     InboxQuery orderByMessageid($order = Criteria::ASC) Order by the MessageID column
 * @method     InboxQuery orderByDate($order = Criteria::ASC) Order by the Date column
 * @method     InboxQuery orderByFromuser($order = Criteria::ASC) Order by the FromUser column
 * @method     InboxQuery orderByTouser($order = Criteria::ASC) Order by the ToUser column
 * @method     InboxQuery orderByMessage($order = Criteria::ASC) Order by the Message column
 *
 * @method     InboxQuery groupByMessageid() Group by the MessageID column
 * @method     InboxQuery groupByDate() Group by the Date column
 * @method     InboxQuery groupByFromuser() Group by the FromUser column
 * @method     InboxQuery groupByTouser() Group by the ToUser column
 * @method     InboxQuery groupByMessage() Group by the Message column
 *
 * @method     InboxQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     InboxQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     InboxQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     InboxQuery leftJoinRegistrationRelatedByFromuser($relationAlias = null) Adds a LEFT JOIN clause to the query using the RegistrationRelatedByFromuser relation
 * @method     InboxQuery rightJoinRegistrationRelatedByFromuser($relationAlias = null) Adds a RIGHT JOIN clause to the query using the RegistrationRelatedByFromuser relation
 * @method     InboxQuery innerJoinRegistrationRelatedByFromuser($relationAlias = null) Adds a INNER JOIN clause to the query using the RegistrationRelatedByFromuser relation
 *
 * @method     InboxQuery leftJoinRegistrationRelatedByTouser($relationAlias = null) Adds a LEFT JOIN clause to the query using the RegistrationRelatedByTouser relation
 * @method     InboxQuery rightJoinRegistrationRelatedByTouser($relationAlias = null) Adds a RIGHT JOIN clause to the query using the RegistrationRelatedByTouser relation
 * @method     InboxQuery innerJoinRegistrationRelatedByTouser($relationAlias = null) Adds a INNER JOIN clause to the query using the RegistrationRelatedByTouser relation
 *
 * @method     Inbox findOne(PropelPDO $con = null) Return the first Inbox matching the query
 * @method     Inbox findOneOrCreate(PropelPDO $con = null) Return the first Inbox matching the query, or a new Inbox object populated from the query conditions when no match is found
 *
 * @method     Inbox findOneByMessageid(int $MessageID) Return the first Inbox filtered by the MessageID column
 * @method     Inbox findOneByDate(string $Date) Return the first Inbox filtered by the Date column
 * @method     Inbox findOneByFromuser(string $FromUser) Return the first Inbox filtered by the FromUser column
 * @method     Inbox findOneByTouser(string $ToUser) Return the first Inbox filtered by the ToUser column
 * @method     Inbox findOneByMessage(string $Message) Return the first Inbox filtered by the Message column
 *
 * @method     array findByMessageid(int $MessageID) Return Inbox objects filtered by the MessageID column
 * @method     array findByDate(string $Date) Return Inbox objects filtered by the Date column
 * @method     array findByFromuser(string $FromUser) Return Inbox objects filtered by the FromUser column
 * @method     array findByTouser(string $ToUser) Return Inbox objects filtered by the ToUser column
 * @method     array findByMessage(string $Message) Return Inbox objects filtered by the Message column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseInboxQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseInboxQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Inbox', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new InboxQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    InboxQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof InboxQuery) {
			return $criteria;
		}
		$query = new InboxQuery();
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
	 * @return    Inbox|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = InboxPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    InboxQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(InboxPeer::MESSAGEID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    InboxQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(InboxPeer::MESSAGEID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the MessageID column
	 * 
	 * @param     int|array $messageid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    InboxQuery The current query, for fluid interface
	 */
	public function filterByMessageid($messageid = null, $comparison = null)
	{
		if (is_array($messageid) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(InboxPeer::MESSAGEID, $messageid, $comparison);
	}

	/**
	 * Filter the query on the Date column
	 * 
	 * @param     string|array $date The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    InboxQuery The current query, for fluid interface
	 */
	public function filterByDate($date = null, $comparison = null)
	{
		if (is_array($date)) {
			$useMinMax = false;
			if (isset($date['min'])) {
				$this->addUsingAlias(InboxPeer::DATE, $date['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($date['max'])) {
				$this->addUsingAlias(InboxPeer::DATE, $date['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(InboxPeer::DATE, $date, $comparison);
	}

	/**
	 * Filter the query on the FromUser column
	 * 
	 * @param     string $fromuser The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    InboxQuery The current query, for fluid interface
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
		return $this->addUsingAlias(InboxPeer::FROMUSER, $fromuser, $comparison);
	}

	/**
	 * Filter the query on the ToUser column
	 * 
	 * @param     string $touser The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    InboxQuery The current query, for fluid interface
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
		return $this->addUsingAlias(InboxPeer::TOUSER, $touser, $comparison);
	}

	/**
	 * Filter the query on the Message column
	 * 
	 * @param     string $message The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    InboxQuery The current query, for fluid interface
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
		return $this->addUsingAlias(InboxPeer::MESSAGE, $message, $comparison);
	}

	/**
	 * Filter the query by a related Registration object
	 *
	 * @param     Registration $registration  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    InboxQuery The current query, for fluid interface
	 */
	public function filterByRegistrationRelatedByFromuser($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(InboxPeer::FROMUSER, $registration->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the RegistrationRelatedByFromuser relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    InboxQuery The current query, for fluid interface
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
	 * @return    InboxQuery The current query, for fluid interface
	 */
	public function filterByRegistrationRelatedByTouser($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(InboxPeer::TOUSER, $registration->getUsername(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the RegistrationRelatedByTouser relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    InboxQuery The current query, for fluid interface
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
	 * @param     Inbox $inbox Object to remove from the list of results
	 *
	 * @return    InboxQuery The current query, for fluid interface
	 */
	public function prune($inbox = null)
	{
		if ($inbox) {
			$this->addUsingAlias(InboxPeer::MESSAGEID, $inbox->getMessageid(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseInboxQuery
