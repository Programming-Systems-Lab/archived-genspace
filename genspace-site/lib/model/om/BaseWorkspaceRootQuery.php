<?php


/**
 * Base class that represents a query for the 'workspace_root' table.
 *
 * 
 *
 * @method     WorkspaceRootQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     WorkspaceRootQuery orderByLocation($order = Criteria::ASC) Order by the location column
 *
 * @method     WorkspaceRootQuery groupById() Group by the id column
 * @method     WorkspaceRootQuery groupByLocation() Group by the location column
 *
 * @method     WorkspaceRootQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     WorkspaceRootQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     WorkspaceRootQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     WorkspaceRoot findOne(PropelPDO $con = null) Return the first WorkspaceRoot matching the query
 * @method     WorkspaceRoot findOneOrCreate(PropelPDO $con = null) Return the first WorkspaceRoot matching the query, or a new WorkspaceRoot object populated from the query conditions when no match is found
 *
 * @method     WorkspaceRoot findOneById(int $id) Return the first WorkspaceRoot filtered by the id column
 * @method     WorkspaceRoot findOneByLocation(string $location) Return the first WorkspaceRoot filtered by the location column
 *
 * @method     array findById(int $id) Return WorkspaceRoot objects filtered by the id column
 * @method     array findByLocation(string $location) Return WorkspaceRoot objects filtered by the location column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseWorkspaceRootQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseWorkspaceRootQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'WorkspaceRoot', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new WorkspaceRootQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    WorkspaceRootQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof WorkspaceRootQuery) {
			return $criteria;
		}
		$query = new WorkspaceRootQuery();
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
	 * @return    WorkspaceRoot|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = WorkspaceRootPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    WorkspaceRootQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(WorkspaceRootPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    WorkspaceRootQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(WorkspaceRootPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceRootQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(WorkspaceRootPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the location column
	 * 
	 * @param     string $location The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    WorkspaceRootQuery The current query, for fluid interface
	 */
	public function filterByLocation($location = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($location)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $location)) {
				$location = str_replace('*', '%', $location);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(WorkspaceRootPeer::LOCATION, $location, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     WorkspaceRoot $workspaceRoot Object to remove from the list of results
	 *
	 * @return    WorkspaceRootQuery The current query, for fluid interface
	 */
	public function prune($workspaceRoot = null)
	{
		if ($workspaceRoot) {
			$this->addUsingAlias(WorkspaceRootPeer::ID, $workspaceRoot->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseWorkspaceRootQuery
