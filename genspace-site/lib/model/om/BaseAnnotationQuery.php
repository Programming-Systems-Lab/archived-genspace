<?php


/**
 * Base class that represents a query for the 'annotation' table.
 *
 * 
 *
 * @method     AnnotationQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     AnnotationQuery orderByWspid($order = Criteria::ASC) Order by the wspid column
 * @method     AnnotationQuery orderByAnnotation($order = Criteria::ASC) Order by the annotation column
 * @method     AnnotationQuery orderByCreator($order = Criteria::ASC) Order by the creator column
 * @method     AnnotationQuery orderByCreatedat($order = Criteria::ASC) Order by the createdAt column
 *
 * @method     AnnotationQuery groupById() Group by the id column
 * @method     AnnotationQuery groupByWspid() Group by the wspid column
 * @method     AnnotationQuery groupByAnnotation() Group by the annotation column
 * @method     AnnotationQuery groupByCreator() Group by the creator column
 * @method     AnnotationQuery groupByCreatedat() Group by the createdAt column
 *
 * @method     AnnotationQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     AnnotationQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     AnnotationQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     AnnotationQuery leftJoinWorkspace($relationAlias = null) Adds a LEFT JOIN clause to the query using the Workspace relation
 * @method     AnnotationQuery rightJoinWorkspace($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Workspace relation
 * @method     AnnotationQuery innerJoinWorkspace($relationAlias = null) Adds a INNER JOIN clause to the query using the Workspace relation
 *
 * @method     AnnotationQuery leftJoinRegistration($relationAlias = null) Adds a LEFT JOIN clause to the query using the Registration relation
 * @method     AnnotationQuery rightJoinRegistration($relationAlias = null) Adds a RIGHT JOIN clause to the query using the Registration relation
 * @method     AnnotationQuery innerJoinRegistration($relationAlias = null) Adds a INNER JOIN clause to the query using the Registration relation
 *
 * @method     Annotation findOne(PropelPDO $con = null) Return the first Annotation matching the query
 * @method     Annotation findOneOrCreate(PropelPDO $con = null) Return the first Annotation matching the query, or a new Annotation object populated from the query conditions when no match is found
 *
 * @method     Annotation findOneById(int $id) Return the first Annotation filtered by the id column
 * @method     Annotation findOneByWspid(int $wspid) Return the first Annotation filtered by the wspid column
 * @method     Annotation findOneByAnnotation(string $annotation) Return the first Annotation filtered by the annotation column
 * @method     Annotation findOneByCreator(int $creator) Return the first Annotation filtered by the creator column
 * @method     Annotation findOneByCreatedat(string $createdAt) Return the first Annotation filtered by the createdAt column
 *
 * @method     array findById(int $id) Return Annotation objects filtered by the id column
 * @method     array findByWspid(int $wspid) Return Annotation objects filtered by the wspid column
 * @method     array findByAnnotation(string $annotation) Return Annotation objects filtered by the annotation column
 * @method     array findByCreator(int $creator) Return Annotation objects filtered by the creator column
 * @method     array findByCreatedat(string $createdAt) Return Annotation objects filtered by the createdAt column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseAnnotationQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseAnnotationQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'Annotation', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new AnnotationQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    AnnotationQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof AnnotationQuery) {
			return $criteria;
		}
		$query = new AnnotationQuery();
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
	 * @return    Annotation|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = AnnotationPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(AnnotationPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(AnnotationPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     int|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(AnnotationPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the wspid column
	 * 
	 * @param     int|array $wspid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function filterByWspid($wspid = null, $comparison = null)
	{
		if (is_array($wspid)) {
			$useMinMax = false;
			if (isset($wspid['min'])) {
				$this->addUsingAlias(AnnotationPeer::WSPID, $wspid['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($wspid['max'])) {
				$this->addUsingAlias(AnnotationPeer::WSPID, $wspid['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(AnnotationPeer::WSPID, $wspid, $comparison);
	}

	/**
	 * Filter the query on the annotation column
	 * 
	 * @param     string $annotation The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function filterByAnnotation($annotation = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($annotation)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $annotation)) {
				$annotation = str_replace('*', '%', $annotation);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(AnnotationPeer::ANNOTATION, $annotation, $comparison);
	}

	/**
	 * Filter the query on the creator column
	 * 
	 * @param     int|array $creator The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function filterByCreator($creator = null, $comparison = null)
	{
		if (is_array($creator)) {
			$useMinMax = false;
			if (isset($creator['min'])) {
				$this->addUsingAlias(AnnotationPeer::CREATOR, $creator['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($creator['max'])) {
				$this->addUsingAlias(AnnotationPeer::CREATOR, $creator['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(AnnotationPeer::CREATOR, $creator, $comparison);
	}

	/**
	 * Filter the query on the createdAt column
	 * 
	 * @param     string|array $createdat The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function filterByCreatedat($createdat = null, $comparison = null)
	{
		if (is_array($createdat)) {
			$useMinMax = false;
			if (isset($createdat['min'])) {
				$this->addUsingAlias(AnnotationPeer::CREATEDAT, $createdat['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($createdat['max'])) {
				$this->addUsingAlias(AnnotationPeer::CREATEDAT, $createdat['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(AnnotationPeer::CREATEDAT, $createdat, $comparison);
	}

	/**
	 * Filter the query by a related Workspace object
	 *
	 * @param     Workspace $workspace  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function filterByWorkspace($workspace, $comparison = null)
	{
		return $this
			->addUsingAlias(AnnotationPeer::WSPID, $workspace->getId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Workspace relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function joinWorkspace($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Workspace');
		
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
			$this->addJoinObject($join, 'Workspace');
		}
		
		return $this;
	}

	/**
	 * Use the Workspace relation Workspace object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    WorkspaceQuery A secondary query class using the current class as primary query
	 */
	public function useWorkspaceQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinWorkspace($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Workspace', 'WorkspaceQuery');
	}

	/**
	 * Filter the query by a related Registration object
	 *
	 * @param     Registration $registration  the related object to use as filter
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function filterByRegistration($registration, $comparison = null)
	{
		return $this
			->addUsingAlias(AnnotationPeer::CREATOR, $registration->getId(), $comparison);
	}

	/**
	 * Adds a JOIN clause to the query using the Registration relation
	 * 
	 * @param     string $relationAlias optional alias for the relation
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function joinRegistration($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		$tableMap = $this->getTableMap();
		$relationMap = $tableMap->getRelation('Registration');
		
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
			$this->addJoinObject($join, 'Registration');
		}
		
		return $this;
	}

	/**
	 * Use the Registration relation Registration object
	 *
	 * @see       useQuery()
	 * 
	 * @param     string $relationAlias optional alias for the relation,
	 *                                   to be used as main alias in the secondary query
	 * @param     string $joinType Accepted values are null, 'left join', 'right join', 'inner join'
	 *
	 * @return    RegistrationQuery A secondary query class using the current class as primary query
	 */
	public function useRegistrationQuery($relationAlias = null, $joinType = Criteria::LEFT_JOIN)
	{
		return $this
			->joinRegistration($relationAlias, $joinType)
			->useQuery($relationAlias ? $relationAlias : 'Registration', 'RegistrationQuery');
	}

	/**
	 * Exclude object from result
	 *
	 * @param     Annotation $annotation Object to remove from the list of results
	 *
	 * @return    AnnotationQuery The current query, for fluid interface
	 */
	public function prune($annotation = null)
	{
		if ($annotation) {
			$this->addUsingAlias(AnnotationPeer::ID, $annotation->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseAnnotationQuery
