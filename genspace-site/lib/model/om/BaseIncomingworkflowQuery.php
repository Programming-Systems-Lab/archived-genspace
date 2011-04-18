<?php


/**
 * Base class that represents a query for the 'INCOMINGWORKFLOW' table.
 *
 * 
 *
 * @method     IncomingworkflowQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     IncomingworkflowQuery orderByCreatedat($order = Criteria::ASC) Order by the CREATEDAT column
 * @method     IncomingworkflowQuery orderByName($order = Criteria::ASC) Order by the NAME column
 * @method     IncomingworkflowQuery orderBySenderId($order = Criteria::ASC) Order by the SENDER_ID column
 * @method     IncomingworkflowQuery orderByWorkflowId($order = Criteria::ASC) Order by the WORKFLOW_ID column
 * @method     IncomingworkflowQuery orderByReceiverId($order = Criteria::ASC) Order by the RECEIVER_ID column
 *
 * @method     IncomingworkflowQuery groupById() Group by the ID column
 * @method     IncomingworkflowQuery groupByCreatedat() Group by the CREATEDAT column
 * @method     IncomingworkflowQuery groupByName() Group by the NAME column
 * @method     IncomingworkflowQuery groupBySenderId() Group by the SENDER_ID column
 * @method     IncomingworkflowQuery groupByWorkflowId() Group by the WORKFLOW_ID column
 * @method     IncomingworkflowQuery groupByReceiverId() Group by the RECEIVER_ID column
 *
 * @method     IncomingworkflowQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     IncomingworkflowQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     IncomingworkflowQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Incomingworkflow findOne(PropelPDO $con = null) Return the first Incomingworkflow matching the query
 * @method     Incomingworkflow findOneOrCreate(PropelPDO $con = null) Return the first Incomingworkflow matching the query, or a new Incomingworkflow object populated from the query conditions when no match is found
 *
 * @method     Incomingworkflow findOneById(int $ID) Return the first Incomingworkflow filtered by the ID column
 * @method     Incomingworkflow findOneByCreatedat(string $CREATEDAT) Return the first Incomingworkflow filtered by the CREATEDAT column
 * @method     Incomingworkflow findOneByName(string $NAME) Return the first Incomingworkflow filtered by the NAME column
 * @method     Incomingworkflow findOneBySenderId(int $SENDER_ID) Return the first Incomingworkflow filtered by the SENDER_ID column
 * @method     Incomingworkflow findOneByWorkflowId(int $WORKFLOW_ID) Return the first Incomingworkflow filtered by the WORKFLOW_ID column
 * @method     Incomingworkflow findOneByReceiverId(int $RECEIVER_ID) Return the first Incomingworkflow filtered by the RECEIVER_ID column
 *
 * @method     array findById(int $ID) Return Incomingworkflow objects filtered by the ID column
 * @method     array findByCreatedat(string $CREATEDAT) Return Incomingworkflow objects filtered by the CREATEDAT column
 * @method     array findByName(string $NAME) Return Incomingworkflow objects filtered by the NAME column
 * @method     array findBySenderId(int $SENDER_ID) Return Incomingworkflow objects filtered by the SENDER_ID column
 * @method     array findByWorkflowId(int $WORKFLOW_ID) Return Incomingworkflow objects filtered by the WORKFLOW_ID column
 * @method     array findByReceiverId(int $RECEIVER_ID) Return Incomingworkflow objects filtered by the RECEIVER_ID column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseIncomingworkflowQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseIncomingworkflowQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Incomingworkflow', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new IncomingworkflowQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    IncomingworkflowQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof IncomingworkflowQuery) {
			return $criteria;
		}
		$query = new IncomingworkflowQuery();
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
	 * @return    Incomingworkflow|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = IncomingworkflowPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    IncomingworkflowQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(IncomingworkflowPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    IncomingworkflowQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(IncomingworkflowPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    IncomingworkflowQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(IncomingworkflowPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the CREATEDAT column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    IncomingworkflowQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(IncomingworkflowPeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(IncomingworkflowPeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(IncomingworkflowPeer::CREATEDAT, $createdat, $comparison);
	}

	/**
	 * Filter the query on the NAME column
	 * 
	 * @param     string $name The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    IncomingworkflowQuery The current query, for fluid interface
	 */
	public function filterByName($name = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($name)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $name)) {
				$name = str_replace('*', '%', $name);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(IncomingworkflowPeer::NAME, $name, $comparison);
	}

	/**
	 * Filter the query on the SENDER_ID column
	 * 
	 * @param     int|array $senderId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    IncomingworkflowQuery The current query, for fluid interface
	 */
	public function filterBySenderId($senderId = null, $comparison = null)
	{
		if (is_array($senderId)) {
			$useMinMax = false;
			if (isset($senderId['min'])) {
				$this->addUsingAlias(IncomingworkflowPeer::SENDER_ID, $senderId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($senderId['max'])) {
				$this->addUsingAlias(IncomingworkflowPeer::SENDER_ID, $senderId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(IncomingworkflowPeer::SENDER_ID, $senderId, $comparison);
	}

	/**
	 * Filter the query on the WORKFLOW_ID column
	 * 
	 * @param     int|array $workflowId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    IncomingworkflowQuery The current query, for fluid interface
	 */
	public function filterByWorkflowId($workflowId = null, $comparison = null)
	{
		if (is_array($workflowId)) {
			$useMinMax = false;
			if (isset($workflowId['min'])) {
				$this->addUsingAlias(IncomingworkflowPeer::WORKFLOW_ID, $workflowId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($workflowId['max'])) {
				$this->addUsingAlias(IncomingworkflowPeer::WORKFLOW_ID, $workflowId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(IncomingworkflowPeer::WORKFLOW_ID, $workflowId, $comparison);
	}

	/**
	 * Filter the query on the RECEIVER_ID column
	 * 
	 * @param     int|array $receiverId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    IncomingworkflowQuery The current query, for fluid interface
	 */
	public function filterByReceiverId($receiverId = null, $comparison = null)
	{
		if (is_array($receiverId)) {
			$useMinMax = false;
			if (isset($receiverId['min'])) {
				$this->addUsingAlias(IncomingworkflowPeer::RECEIVER_ID, $receiverId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($receiverId['max'])) {
				$this->addUsingAlias(IncomingworkflowPeer::RECEIVER_ID, $receiverId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(IncomingworkflowPeer::RECEIVER_ID, $receiverId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Incomingworkflow $incomingworkflow Object to remove from the list of results
	 *
	 * @return    IncomingworkflowQuery The current query, for fluid interface
	 */
	public function prune($incomingworkflow = null)
	{
		if ($incomingworkflow) {
			$this->addUsingAlias(IncomingworkflowPeer::ID, $incomingworkflow->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseIncomingworkflowQuery
