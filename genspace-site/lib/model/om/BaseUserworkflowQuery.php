<?php


/**
 * Base class that represents a query for the 'USERWORKFLOW' table.
 *
 * 
 *
 * @method     UserworkflowQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     UserworkflowQuery orderByName($order = Criteria::ASC) Order by the NAME column
 * @method     UserworkflowQuery orderByWorkflowId($order = Criteria::ASC) Order by the WORKFLOW_ID column
 * @method     UserworkflowQuery orderByOwnerId($order = Criteria::ASC) Order by the OWNER_ID column
 * @method     UserworkflowQuery orderByFolderId($order = Criteria::ASC) Order by the FOLDER_ID column
 * @method     UserworkflowQuery orderByCreatedat($order = Criteria::ASC) Order by the CREATEDAT column
 *
 * @method     UserworkflowQuery groupById() Group by the ID column
 * @method     UserworkflowQuery groupByName() Group by the NAME column
 * @method     UserworkflowQuery groupByWorkflowId() Group by the WORKFLOW_ID column
 * @method     UserworkflowQuery groupByOwnerId() Group by the OWNER_ID column
 * @method     UserworkflowQuery groupByFolderId() Group by the FOLDER_ID column
 * @method     UserworkflowQuery groupByCreatedat() Group by the CREATEDAT column
 *
 * @method     UserworkflowQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     UserworkflowQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     UserworkflowQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Userworkflow findOne(PropelPDO $con = null) Return the first Userworkflow matching the query
 * @method     Userworkflow findOneOrCreate(PropelPDO $con = null) Return the first Userworkflow matching the query, or a new Userworkflow object populated from the query conditions when no match is found
 *
 * @method     Userworkflow findOneById(int $ID) Return the first Userworkflow filtered by the ID column
 * @method     Userworkflow findOneByName(string $NAME) Return the first Userworkflow filtered by the NAME column
 * @method     Userworkflow findOneByWorkflowId(int $WORKFLOW_ID) Return the first Userworkflow filtered by the WORKFLOW_ID column
 * @method     Userworkflow findOneByOwnerId(int $OWNER_ID) Return the first Userworkflow filtered by the OWNER_ID column
 * @method     Userworkflow findOneByFolderId(int $FOLDER_ID) Return the first Userworkflow filtered by the FOLDER_ID column
 * @method     Userworkflow findOneByCreatedat(string $CREATEDAT) Return the first Userworkflow filtered by the CREATEDAT column
 *
 * @method     array findById(int $ID) Return Userworkflow objects filtered by the ID column
 * @method     array findByName(string $NAME) Return Userworkflow objects filtered by the NAME column
 * @method     array findByWorkflowId(int $WORKFLOW_ID) Return Userworkflow objects filtered by the WORKFLOW_ID column
 * @method     array findByOwnerId(int $OWNER_ID) Return Userworkflow objects filtered by the OWNER_ID column
 * @method     array findByFolderId(int $FOLDER_ID) Return Userworkflow objects filtered by the FOLDER_ID column
 * @method     array findByCreatedat(string $CREATEDAT) Return Userworkflow objects filtered by the CREATEDAT column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseUserworkflowQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseUserworkflowQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Userworkflow', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new UserworkflowQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    UserworkflowQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof UserworkflowQuery) {
			return $criteria;
		}
		$query = new UserworkflowQuery();
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
	 * @return    Userworkflow|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = UserworkflowPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    UserworkflowQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(UserworkflowPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    UserworkflowQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(UserworkflowPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserworkflowQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(UserworkflowPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the NAME column
	 * 
	 * @param     string $name The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserworkflowQuery The current query, for fluid interface
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
		return $this->addUsingAlias(UserworkflowPeer::NAME, $name, $comparison);
	}

	/**
	 * Filter the query on the WORKFLOW_ID column
	 * 
	 * @param     int|array $workflowId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserworkflowQuery The current query, for fluid interface
	 */
	public function filterByWorkflowId($workflowId = null, $comparison = null)
	{
		if (is_array($workflowId)) {
			$useMinMax = false;
			if (isset($workflowId['min'])) {
				$this->addUsingAlias(UserworkflowPeer::WORKFLOW_ID, $workflowId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($workflowId['max'])) {
				$this->addUsingAlias(UserworkflowPeer::WORKFLOW_ID, $workflowId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(UserworkflowPeer::WORKFLOW_ID, $workflowId, $comparison);
	}

	/**
	 * Filter the query on the OWNER_ID column
	 * 
	 * @param     int|array $ownerId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserworkflowQuery The current query, for fluid interface
	 */
	public function filterByOwnerId($ownerId = null, $comparison = null)
	{
		if (is_array($ownerId)) {
			$useMinMax = false;
			if (isset($ownerId['min'])) {
				$this->addUsingAlias(UserworkflowPeer::OWNER_ID, $ownerId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($ownerId['max'])) {
				$this->addUsingAlias(UserworkflowPeer::OWNER_ID, $ownerId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(UserworkflowPeer::OWNER_ID, $ownerId, $comparison);
	}

	/**
	 * Filter the query on the FOLDER_ID column
	 * 
	 * @param     int|array $folderId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserworkflowQuery The current query, for fluid interface
	 */
	public function filterByFolderId($folderId = null, $comparison = null)
	{
		if (is_array($folderId)) {
			$useMinMax = false;
			if (isset($folderId['min'])) {
				$this->addUsingAlias(UserworkflowPeer::FOLDER_ID, $folderId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($folderId['max'])) {
				$this->addUsingAlias(UserworkflowPeer::FOLDER_ID, $folderId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(UserworkflowPeer::FOLDER_ID, $folderId, $comparison);
	}

	/**
	 * Filter the query on the CREATEDAT column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    UserworkflowQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(UserworkflowPeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(UserworkflowPeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(UserworkflowPeer::CREATEDAT, $createdat, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Userworkflow $userworkflow Object to remove from the list of results
	 *
	 * @return    UserworkflowQuery The current query, for fluid interface
	 */
	public function prune($userworkflow = null)
	{
		if ($userworkflow) {
			$this->addUsingAlias(UserworkflowPeer::ID, $userworkflow->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseUserworkflowQuery
