<?php


/**
 * Base class that represents a query for the 'TOOLRATING' table.
 *
 * 
 *
 * @method     ToolratingQuery orderById($order = Criteria::ASC) Order by the ID column
 * @method     ToolratingQuery orderByCreatedat($order = Criteria::ASC) Order by the CREATEDAT column
 * @method     ToolratingQuery orderByRating($order = Criteria::ASC) Order by the RATING column
 * @method     ToolratingQuery orderByToolId($order = Criteria::ASC) Order by the TOOL_ID column
 * @method     ToolratingQuery orderByCreatorId($order = Criteria::ASC) Order by the CREATOR_ID column
 *
 * @method     ToolratingQuery groupById() Group by the ID column
 * @method     ToolratingQuery groupByCreatedat() Group by the CREATEDAT column
 * @method     ToolratingQuery groupByRating() Group by the RATING column
 * @method     ToolratingQuery groupByToolId() Group by the TOOL_ID column
 * @method     ToolratingQuery groupByCreatorId() Group by the CREATOR_ID column
 *
 * @method     ToolratingQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     ToolratingQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     ToolratingQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     Toolrating findOne(PropelPDO $con = null) Return the first Toolrating matching the query
 * @method     Toolrating findOneOrCreate(PropelPDO $con = null) Return the first Toolrating matching the query, or a new Toolrating object populated from the query conditions when no match is found
 *
 * @method     Toolrating findOneById(int $ID) Return the first Toolrating filtered by the ID column
 * @method     Toolrating findOneByCreatedat(string $CREATEDAT) Return the first Toolrating filtered by the CREATEDAT column
 * @method     Toolrating findOneByRating(int $RATING) Return the first Toolrating filtered by the RATING column
 * @method     Toolrating findOneByToolId(int $TOOL_ID) Return the first Toolrating filtered by the TOOL_ID column
 * @method     Toolrating findOneByCreatorId(int $CREATOR_ID) Return the first Toolrating filtered by the CREATOR_ID column
 *
 * @method     array findById(int $ID) Return Toolrating objects filtered by the ID column
 * @method     array findByCreatedat(string $CREATEDAT) Return Toolrating objects filtered by the CREATEDAT column
 * @method     array findByRating(int $RATING) Return Toolrating objects filtered by the RATING column
 * @method     array findByToolId(int $TOOL_ID) Return Toolrating objects filtered by the TOOL_ID column
 * @method     array findByCreatorId(int $CREATOR_ID) Return Toolrating objects filtered by the CREATOR_ID column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseToolratingQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseToolratingQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Toolrating', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new ToolratingQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    ToolratingQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof ToolratingQuery) {
			return $criteria;
		}
		$query = new ToolratingQuery();
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
	 * @return    Toolrating|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = ToolratingPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    ToolratingQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(ToolratingPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    ToolratingQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(ToolratingPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the ID column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolratingQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(ToolratingPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the CREATEDAT column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolratingQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(ToolratingPeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(ToolratingPeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolratingPeer::CREATEDAT, $createdat, $comparison);
	}

	/**
	 * Filter the query on the RATING column
	 * 
	 * @param     int|array $rating The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolratingQuery The current query, for fluid interface
	 */
	public function filterByRating($rating = null, $comparison = null)
	{
		if (is_array($rating)) {
			$useMinMax = false;
			if (isset($rating['min'])) {
				$this->addUsingAlias(ToolratingPeer::RATING, $rating['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($rating['max'])) {
				$this->addUsingAlias(ToolratingPeer::RATING, $rating['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolratingPeer::RATING, $rating, $comparison);
	}

	/**
	 * Filter the query on the TOOL_ID column
	 * 
	 * @param     int|array $toolId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolratingQuery The current query, for fluid interface
	 */
	public function filterByToolId($toolId = null, $comparison = null)
	{
		if (is_array($toolId)) {
			$useMinMax = false;
			if (isset($toolId['min'])) {
				$this->addUsingAlias(ToolratingPeer::TOOL_ID, $toolId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($toolId['max'])) {
				$this->addUsingAlias(ToolratingPeer::TOOL_ID, $toolId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolratingPeer::TOOL_ID, $toolId, $comparison);
	}

	/**
	 * Filter the query on the CREATOR_ID column
	 * 
	 * @param     int|array $creatorId The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolratingQuery The current query, for fluid interface
	 */
	public function filterByCreatorId($creatorId = null, $comparison = null)
	{
		if (is_array($creatorId)) {
			$useMinMax = false;
			if (isset($creatorId['min'])) {
				$this->addUsingAlias(ToolratingPeer::CREATOR_ID, $creatorId['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($creatorId['max'])) {
				$this->addUsingAlias(ToolratingPeer::CREATOR_ID, $creatorId['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolratingPeer::CREATOR_ID, $creatorId, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Toolrating $toolrating Object to remove from the list of results
	 *
	 * @return    ToolratingQuery The current query, for fluid interface
	 */
	public function prune($toolrating = null)
	{
		if ($toolrating) {
			$this->addUsingAlias(ToolratingPeer::ID, $toolrating->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseToolratingQuery
