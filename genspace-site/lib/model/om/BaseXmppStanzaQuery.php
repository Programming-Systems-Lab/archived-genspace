<?php


/**
 * Base class that represents a query for the 'xmpp_stanza' table.
 *
 * 
 *
 * @method     XmppStanzaQuery orderById($order = Criteria::ASC) Order by the id column
 * @method     XmppStanzaQuery orderByStanza($order = Criteria::ASC) Order by the stanza column
 *
 * @method     XmppStanzaQuery groupById() Group by the id column
 * @method     XmppStanzaQuery groupByStanza() Group by the stanza column
 *
 * @method     XmppStanzaQuery leftJoin($relation) Adds a LEFT JOIN clause to the query
 * @method     XmppStanzaQuery rightJoin($relation) Adds a RIGHT JOIN clause to the query
 * @method     XmppStanzaQuery innerJoin($relation) Adds a INNER JOIN clause to the query
 *
 * @method     XmppStanza findOne(PropelPDO $con = null) Return the first XmppStanza matching the query
 * @method     XmppStanza findOneOrCreate(PropelPDO $con = null) Return the first XmppStanza matching the query, or a new XmppStanza object populated from the query conditions when no match is found
 *
 * @method     XmppStanza findOneById(string $id) Return the first XmppStanza filtered by the id column
 * @method     XmppStanza findOneByStanza(string $stanza) Return the first XmppStanza filtered by the stanza column
 *
 * @method     array findById(string $id) Return XmppStanza objects filtered by the id column
 * @method     array findByStanza(string $stanza) Return XmppStanza objects filtered by the stanza column
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseXmppStanzaQuery extends ModelCriteria
{

	/**
	 * Initializes internal state of BaseXmppStanzaQuery object.
	 *
	 * @param     string $dbName The dabase name
	 * @param     string $modelName The phpName of a model, e.g. 'Book'
	 * @param     string $modelAlias The alias for the model in this query, e.g. 'b'
	 */
	public function __construct($dbName = 'propel', $modelName = 'XmppStanza', $modelAlias = null)
	{
		parent::__construct($dbName, $modelName, $modelAlias);
	}

	/**
	 * Returns a new XmppStanzaQuery object.
	 *
	 * @param     string $modelAlias The alias of a model in the query
	 * @param     Criteria $criteria Optional Criteria to build the query from
	 *
	 * @return    XmppStanzaQuery
	 */
	public static function create($modelAlias = null, $criteria = null)
	{
		if ($criteria instanceof XmppStanzaQuery) {
			return $criteria;
		}
		$query = new XmppStanzaQuery();
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
	 * @return    XmppStanza|array|mixed the result, formatted by the current formatter
	 */
	public function findPk($key, $con = null)
	{
		if ((null !== ($obj = XmppStanzaPeer::getInstanceFromPool((string) $key))) && $this->getFormatter()->isObjectFormatter()) {
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
	 * @return    XmppStanzaQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKey($key)
	{
		return $this->addUsingAlias(XmppStanzaPeer::ID, $key, Criteria::EQUAL);
	}

	/**
	 * Filter the query by a list of primary keys
	 *
	 * @param     array $keys The list of primary key to use for the query
	 *
	 * @return    XmppStanzaQuery The current query, for fluid interface
	 */
	public function filterByPrimaryKeys($keys)
	{
		return $this->addUsingAlias(XmppStanzaPeer::ID, $keys, Criteria::IN);
	}

	/**
	 * Filter the query on the id column
	 * 
	 * @param     string|array $id The value to use as filter.
	 *            Accepts an associative array('min' => $minValue, 'max' => $maxValue)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    XmppStanzaQuery The current query, for fluid interface
	 */
	public function filterById($id = null, $comparison = null)
	{
		if (is_array($id) && null === $comparison) {
			$comparison = Criteria::IN;
		}
		return $this->addUsingAlias(XmppStanzaPeer::ID, $id, $comparison);
	}

	/**
	 * Filter the query on the stanza column
	 * 
	 * @param     string $stanza The value to use as filter.
	 *            Accepts wildcards (* and % trigger a LIKE)
	 * @param     string $comparison Operator to use for the column comparison, defaults to Criteria::EQUAL
	 *
	 * @return    XmppStanzaQuery The current query, for fluid interface
	 */
	public function filterByStanza($stanza = null, $comparison = null)
	{
		if (null === $comparison) {
			if (is_array($stanza)) {
				$comparison = Criteria::IN;
			} elseif (preg_match('/[\%\*]/', $stanza)) {
				$stanza = str_replace('*', '%', $stanza);
				$comparison = Criteria::LIKE;
			}
		}
		return $this->addUsingAlias(XmppStanzaPeer::STANZA, $stanza, $comparison);
	}

	/**
	 * Exclude object from result
	 *
	 * @param     XmppStanza $xmppStanza Object to remove from the list of results
	 *
	 * @return    XmppStanzaQuery The current query, for fluid interface
	 */
	public function prune($xmppStanza = null)
	{
		if ($xmppStanza) {
			$this->addUsingAlias(XmppStanzaPeer::ID, $xmppStanza->getId(), Criteria::NOT_EQUAL);
	  }
	  
		return $this;
	}

} // BaseXmppStanzaQuery
