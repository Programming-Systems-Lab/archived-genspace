<?php


/**
 * Base class that represents a query for the 'TOOLCOMMENT' table.
 *
 * 
 *
 * @method     ToolcommentQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     ToolcommentQuery orderByCreatedat($order = Criteria::ASC) Order by the CREATEDAT column
 * @method     ToolcommentQuery orderByComment($order = Criteria::ASC) Order by the COMMENT column
 * @method     ToolcommentQuery orderByCreatorId($order = Criteria::ASC) Order by the CREATOR_ID column
 * @method     ToolcommentQuery orderByToolId($order = Criteria::ASC) Order by the TOOL_ID column
 *
 * @method     ToolcommentQuery groupById() Group by the ID column
 * @method     ToolcommentQuery groupByCreatedat() Group by the CREATEDAT column
 * @method     ToolcommentQuery groupByComment() Group by the COMMENT column
 * @method     ToolcommentQuery groupByCreatorId() Group by the CREATOR_ID column
 * @method     ToolcommentQuery groupByToolId() Group by the TOOL_ID column
 *
 * @method     ToolcommentQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     ToolcommentQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     ToolcommentQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Toolcomment findOne(PropelPDO $con = null) Return the first Toolcomment matching the query
 * @method     Toolcomment findOneOrCreate(PropelPDO $con = null) Return the first Toolcomment matching the query, or a new Toolcomment object populated from the query conditions when no match is found
 *
 * @method     Toolcomment findOneById(int $ID) Return the first Toolcomment filtered by the ID column
 * @method     Toolcomment findOneByCreatedat(string $CREATEDAT) Return the first Toolcomment filtered by the CREATEDAT column
 * @method     Toolcomment findOneByComment(string $COMMENT) Return the first Toolcomment filtered by the COMMENT column
 * @method     Toolcomment findOneByCreatorId(int $CREATOR_ID) Return the first Toolcomment filtered by the CREATOR_ID column
 * @method     Toolcomment findOneByToolId(int $TOOL_ID) Return the first Toolcomment filtered by the TOOL_ID column
 *
 * @method     array findById(int $ID) Return Toolcomment objects filtered by the ID column
 * @method     array findByCreatedat(string $CREATEDAT) Return Toolcomment objects filtered by the CREATEDAT column
 * @method     array findByComment(string $COMMENT) Return Toolcomment objects filtered by the COMMENT column
 * @method     array findByCreatorId(int $CREATOR_ID) Return Toolcomment objects filtered by the CREATOR_ID column
 * @method     array findByToolId(int $TOOL_ID) Return Toolcomment objects filtered by the TOOL_ID column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseToolcommentQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseToolcommentQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Toolcomment', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new ToolcommentQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    ToolcommentQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof ToolcommentQuery) {
			return $criteria;
		}
		$query = new ToolcommentQuery();
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
	 * @return    Toolcomment|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = ToolcommentPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    ToolcommentQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(ToolcommentPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    ToolcommentQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(ToolcommentPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolcommentQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(ToolcommentPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the CREATEDAT column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolcommentQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(ToolcommentPeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(ToolcommentPeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolcommentPeer::CREATEDAT, $createdat, $comparison);
	}

	/**
	 * Filter the query on the COMMENT column
	 * 
	 * @param     string $comment The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolcommentQuery The current query, for fluid interface
	 */
	public function filterByComment($comment = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($comment)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $comment)) {
				$comment = str_replace('*', '%', $comment);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(ToolcommentPeer::COMMENT, $comment, $comparison);
	}

	/**
	 * Filter the query on the CREATOR_ID column
	 * 
	 * @param     int|array $creatorId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolcommentQuery The current query, for fluid interface
	 */
	public function filterByCreatorId($creatorId = null, $comparison = null)
	{
		if (is_array($creatorId)) {
			$useMinMax = false;
			if (isset($creatorId['min'])) {
				$this->addUsingAlias(ToolcommentPeer::CREATOR_ID, $creatorId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($creatorId['max'])) {
				$this->addUsingAlias(ToolcommentPeer::CREATOR_ID, $creatorId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolcommentPeer::CREATOR_ID, $creatorId, $comparison);
	}

	/**
	 * Filter the query on the TOOL_ID column
	 * 
	 * @param     int|array $toolId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolcommentQuery The current query, for fluid interface
	 */
	public function filterByToolId($toolId = null, $comparison = null)
	{
		if (is_array($toolId)) {
			$useMinMax = false;
			if (isset($toolId['min'])) {
				$this->addUsingAlias(ToolcommentPeer::TOOL_ID, $toolId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($toolId['max'])) {
				$this->addUsingAlias(ToolcommentPeer::TOOL_ID, $toolId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolcommentPeer::TOOL_ID, $toolId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Toolcomment $toolcomment Object to remove from the list of results
	 *
	 * @return    ToolcommentQuery The current query, for fluid interface
	 */
	public function prune($toolcomment = null)
	{
		if ($toolcomment) {
			$this->addUsingAlias(ToolcommentPeer::ID, $toolcomment->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseToolcommentQuery
