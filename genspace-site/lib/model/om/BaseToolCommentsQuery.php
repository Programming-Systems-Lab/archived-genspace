<?php


/**
 * Base class that represents a query for the 'tool_comments' table.
 *
 * 
 *
 * @method     ToolCommentsQuery orderByPk($order = Criteria::ASC) Order by the pk column
 * @method     ToolCommentsQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     ToolCommentsQuery orderByComment($order = Criteria::ASC) Order by the comment column
 * @method     ToolCommentsQuery orderByUsername($order = Criteria::ASC) Order by the username column
 * @method     ToolCommentsQuery orderByPostedOn($order = Criteria::ASC) Order by the posted_on column
 *
 * @method     ToolCommentsQuery groupByPk() Group by the pk column
 * @method     ToolCommentsQuery groupById() Group by the id column
 * @method     ToolCommentsQuery groupByComment() Group by the comment column
 * @method     ToolCommentsQuery groupByUsername() Group by the username column
 * @method     ToolCommentsQuery groupByPostedOn() Group by the posted_on column
 *
 * @method     ToolCommentsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     ToolCommentsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     ToolCommentsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     ToolComments findOne(PropelPDO $con = null) Return the first ToolComments matching the query
 * @method     ToolComments findOneOrCreate(PropelPDO $con = null) Return the first ToolComments matching the query, or a new ToolComments object populated from the query conditions when no match is found
 *
 * @method     ToolComments findOneByPk(int $pk) Return the first ToolComments filtered by the pk column
 * @method     ToolComments findOneById(int $id) Return the first ToolComments filtered by the id column
 * @method     ToolComments findOneByComment(string $comment) Return the first ToolComments filtered by the comment column
 * @method     ToolComments findOneByUsername(string $username) Return the first ToolComments filtered by the username column
 * @method     ToolComments findOneByPostedOn(string $posted_on) Return the first ToolComments filtered by the posted_on column
 *
 * @method     array findByPk(int $pk) Return ToolComments objects filtered by the pk column
 * @method     array findById(int $id) Return ToolComments objects filtered by the id column
 * @method     array findByComment(string $comment) Return ToolComments objects filtered by the comment column
 * @method     array findByUsername(string $username) Return ToolComments objects filtered by the username column
 * @method     array findByPostedOn(string $posted_on) Return ToolComments objects filtered by the posted_on column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseToolCommentsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseToolCommentsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'ToolComments', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new ToolCommentsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    ToolCommentsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof ToolCommentsQuery) {
			return $criteria;
		}
		$query = new ToolCommentsQuery();
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
	 * @return    ToolComments|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = ToolCommentsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    ToolCommentsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(ToolCommentsPeer::PK, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    ToolCommentsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(ToolCommentsPeer::PK, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the pk column
	 * 
	 * @param     int|array $pk The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolCommentsQuery The current query, for fluid interface
	 */
	public function filterByPk($pk = null, $comparison = null)
	{
		if (is_array($pk) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(ToolCommentsPeer::PK, $pk, $comparison);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolCommentsQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id)) {
			$useMinMax = false;
			if (isset($id['min'])) {
				$this->addUsingAlias(ToolCommentsPeer::ID, $id['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($id['max'])) {
				$this->addUsingAlias(ToolCommentsPeer::ID, $id['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolCommentsPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the comment column
	 * 
	 * @param     string $comment The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolCommentsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(ToolCommentsPeer::COMMENT, $comment, $comparison);
	}

	/**
	 * Filter the query on the username column
	 * 
	 * @param     string $username The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolCommentsQuery The current query, for fluid interface
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
		return $this->addUsingAlias(ToolCommentsPeer::USERNAME, $username, $comparison);
	}

	/**
	 * Filter the query on the posted_on column
	 * 
	 * @param     string|array $postedOn The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ToolCommentsQuery The current query, for fluid interface
	 */
	public function filterByPostedOn($postedOn = null, $comparison = null)
	{
		if (is_array($postedOn)) {
			$useMinMax = false;
			if (isset($postedOn['min'])) {
				$this->addUsingAlias(ToolCommentsPeer::POSTED_ON, $postedOn['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($postedOn['max'])) {
				$this->addUsingAlias(ToolCommentsPeer::POSTED_ON, $postedOn['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ToolCommentsPeer::POSTED_ON, $postedOn, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     ToolComments $toolComments Object to remove from the list of results
	 *
	 * @return    ToolCommentsQuery The current query, for fluid interface
	 */
	public function prune($toolComments = null)
	{
		if ($toolComments) {
			$this->addUsingAlias(ToolCommentsPeer::PK, $toolComments->getPk(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseToolCommentsQuery
