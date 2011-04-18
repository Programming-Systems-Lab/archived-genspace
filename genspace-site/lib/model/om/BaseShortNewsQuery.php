<?php


/**
 * Base class that represents a query for the 'short_news' table.
 *
 * 
 *
 * @method     ShortNewsQuery orderBySnid($order = Criteria::ASC) Order by the snid column
 * @method     ShortNewsQuery orderByPublishingTime($order = Criteria::ASC) Order by the publishing_time column
 * @method     ShortNewsQuery orderByNewsType($order = Criteria::ASC) Order by the news_type column
 * @method     ShortNewsQuery orderByAuthor($order = Criteria::ASC) Order by the author column
 * @method     ShortNewsQuery orderBySubject($order = Criteria::ASC) Order by the subject column
 * @method     ShortNewsQuery orderByBody($order = Criteria::ASC) Order by the body column
 *
 * @method     ShortNewsQuery groupBySnid() Group by the snid column
 * @method     ShortNewsQuery groupByPublishingTime() Group by the publishing_time column
 * @method     ShortNewsQuery groupByNewsType() Group by the news_type column
 * @method     ShortNewsQuery groupByAuthor() Group by the author column
 * @method     ShortNewsQuery groupBySubject() Group by the subject column
 * @method     ShortNewsQuery groupByBody() Group by the body column
 *
 * @method     ShortNewsQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     ShortNewsQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     ShortNewsQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     ShortNews findOne(PropelPDO $con = null) Return the first ShortNews matching the query
 * @method     ShortNews findOneOrCreate(PropelPDO $con = null) Return the first ShortNews matching the query, or a new ShortNews object populated from the query conditions when no match is found
 *
 * @method     ShortNews findOneBySnid(string $snid) Return the first ShortNews filtered by the snid column
 * @method     ShortNews findOneByPublishingTime(string $publishing_time) Return the first ShortNews filtered by the publishing_time column
 * @method     ShortNews findOneByNewsType(string $news_type) Return the first ShortNews filtered by the news_type column
 * @method     ShortNews findOneByAuthor(string $author) Return the first ShortNews filtered by the author column
 * @method     ShortNews findOneBySubject(string $subject) Return the first ShortNews filtered by the subject column
 * @method     ShortNews findOneByBody(string $body) Return the first ShortNews filtered by the body column
 *
 * @method     array findBySnid(string $snid) Return ShortNews objects filtered by the snid column
 * @method     array findByPublishingTime(string $publishing_time) Return ShortNews objects filtered by the publishing_time column
 * @method     array findByNewsType(string $news_type) Return ShortNews objects filtered by the news_type column
 * @method     array findByAuthor(string $author) Return ShortNews objects filtered by the author column
 * @method     array findBySubject(string $subject) Return ShortNews objects filtered by the subject column
 * @method     array findByBody(string $body) Return ShortNews objects filtered by the body column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseShortNewsQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseShortNewsQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'ShortNews', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new ShortNewsQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    ShortNewsQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof ShortNewsQuery) {
			return $criteria;
		}
		$query = new ShortNewsQuery();
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
	 * @return    ShortNews|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = ShortNewsPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    ShortNewsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(ShortNewsPeer::SNID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    ShortNewsQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(ShortNewsPeer::SNID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the snid column
	 * 
	 * @param     string|array $snid The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ShortNewsQuery The current query, for fluid interface
	 */
	public function filterBySnid($snid = null, $comparison = null)
	{
		if (is_array($snid) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(ShortNewsPeer::SNID, $snid, $comparison);
	}

	/**
	 * Filter the query on the publishing_time column
	 * 
	 * @param     string|array $publishingTime The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ShortNewsQuery The current query, for fluid interface
	 */
	public function filterByPublishingTime($publishingTime = null, $comparison = null)
	{
		if (is_array($publishingTime)) {
			$useMinMax = false;
			if (isset($publishingTime['min'])) {
				$this->addUsingAlias(ShortNewsPeer::PUBLISHING_TIME, $publishingTime['min'], Criteria::GREATER_EQUAL);
				$useMinMax = true;
			}
			if (isset($publishingTime['max'])) {
				$this->addUsingAlias(ShortNewsPeer::PUBLISHING_TIME, $publishingTime['max'], Criteria::LESS_EQUAL);
				$useMinMax = true;
			}
			if ($useMinMax) {
				return $this;
			}
			if (null === $comparison) {
				$comparison = Criteria::IN;
			}
		}
		return $this->addUsingAlias(ShortNewsPeer::PUBLISHING_TIME, $publishingTime, $comparison);
	}

	/**
	 * Filter the query on the news_type column
	 * 
	 * @param     string $newsType The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ShortNewsQuery The current query, for fluid interface
	 */
	public function filterByNewsType($newsType = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($newsType)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $newsType)) {
				$newsType = str_replace('*', '%', $newsType);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(ShortNewsPeer::NEWS_TYPE, $newsType, $comparison);
	}

	/**
	 * Filter the query on the author column
	 * 
	 * @param     string $author The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ShortNewsQuery The current query, for fluid interface
	 */
	public function filterByAuthor($author = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($author)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $author)) {
				$author = str_replace('*', '%', $author);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(ShortNewsPeer::AUTHOR, $author, $comparison);
	}

	/**
	 * Filter the query on the subject column
	 * 
	 * @param     string $subject The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ShortNewsQuery The current query, for fluid interface
	 */
	public function filterBySubject($subject = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($subject)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $subject)) {
				$subject = str_replace('*', '%', $subject);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(ShortNewsPeer::SUBJECT, $subject, $comparison);
	}

	/**
	 * Filter the query on the body column
	 * 
	 * @param     string $body The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    ShortNewsQuery The current query, for fluid interface
	 */
	public function filterByBody($body = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($body)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $body)) {
				$body = str_replace('*', '%', $body);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(ShortNewsPeer::BODY, $body, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     ShortNews $shortNews Object to remove from the list of results
	 *
	 * @return    ShortNewsQuery The current query, for fluid interface
	 */
	public function prune($shortNews = null)
	{
		if ($shortNews) {
			$this->addUsingAlias(ShortNewsPeer::SNID, $shortNews->getSnid(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseShortNewsQuery
