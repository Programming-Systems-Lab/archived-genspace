<?php


/**
 * Base class that represents a row from the 'tig_users' table.
 *
 * 
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseTigUsers extends BaseObject  implements Persistent
{

	/**
	 * Peer class name
	 */
  const PEER = 'TigUsersPeer';

	/**
	 * The Peer class.
	 * Instance provides a convenient way of calling static methods on a class
	 * that calling code may not be able to identify.
	 * @var        TigUsersPeer
	 */
	protected static $peer;

	/**
	 * The value for the uid field.
	 * @var        string
	 */
	protected $uid;

	/**
	 * The value for the user_id field.
	 * @var        string
	 */
	protected $user_id;

	/**
	 * The value for the sha1_user_id field.
	 * @var        string
	 */
	protected $sha1_user_id;

	/**
	 * The value for the user_pw field.
	 * @var        string
	 */
	protected $user_pw;

	/**
	 * The value for the acc_create_time field.
	 * Note: this column has a database default value of: (expression) CURRENT_TIMESTAMP
	 * @var        string
	 */
	protected $acc_create_time;

	/**
	 * The value for the last_login field.
	 * Note: this column has a database default value of: '-0001-11-30 00:00:00'
	 * @var        string
	 */
	protected $last_login;

	/**
	 * The value for the last_logout field.
	 * Note: this column has a database default value of: '-0001-11-30 00:00:00'
	 * @var        string
	 */
	protected $last_logout;

	/**
	 * The value for the online_status field.
	 * Note: this column has a database default value of: 0
	 * @var        int
	 */
	protected $online_status;

	/**
	 * The value for the failed_logins field.
	 * Note: this column has a database default value of: 0
	 * @var        int
	 */
	protected $failed_logins;

	/**
	 * The value for the account_status field.
	 * Note: this column has a database default value of: 1
	 * @var        int
	 */
	protected $account_status;

	/**
	 * @var        array TigNodes[] Collection to store aggregation of TigNodes objects.
	 */
	protected $collTigNodess;

	/**
	 * @var        array TigPairs[] Collection to store aggregation of TigPairs objects.
	 */
	protected $collTigPairss;

	/**
	 * Flag to prevent endless save loop, if this object is referenced
	 * by another object which falls in this transaction.
	 * @var        boolean
	 */
	protected $alreadyInSave = false;

	/**
	 * Flag to prevent endless validation loop, if this object is referenced
	 * by another object which falls in this transaction.
	 * @var        boolean
	 */
	protected $alreadyInValidation = false;

	/**
	 * Applies default values to this object.
	 * This method should be called from the object's constructor (or
	 * equivalent initialization method).
	 * @see        __construct()
	 */
	public function applyDefaultValues()
	{
		$this->last_login = '-0001-11-30 00:00:00';
		$this->last_logout = '-0001-11-30 00:00:00';
		$this->online_status = 0;
		$this->failed_logins = 0;
		$this->account_status = 1;
	}

	/**
	 * Initializes internal state of BaseTigUsers object.
	 * @see        applyDefaults()
	 */
	public function __construct()
	{
		parent::__construct();
		$this->applyDefaultValues();
	}

	/**
	 * Get the [uid] column value.
	 * 
	 * @return     string
	 */
	public function getUid()
	{
		return $this->uid;
	}

	/**
	 * Get the [user_id] column value.
	 * 
	 * @return     string
	 */
	public function getUserId()
	{
		return $this->user_id;
	}

	/**
	 * Get the [sha1_user_id] column value.
	 * 
	 * @return     string
	 */
	public function getSha1UserId()
	{
		return $this->sha1_user_id;
	}

	/**
	 * Get the [user_pw] column value.
	 * 
	 * @return     string
	 */
	public function getUserPw()
	{
		return $this->user_pw;
	}

	/**
	 * Get the [optionally formatted] temporal [acc_create_time] column value.
	 * 
	 *
	 * @param      string $format The date/time format string (either date()-style or strftime()-style).
	 *							If format is NULL, then the raw DateTime object will be returned.
	 * @return     mixed Formatted date/time value as string or DateTime object (if format is NULL), NULL if column is NULL
	 * @throws     PropelException - if unable to parse/validate the date/time value.
	 */
	public function getAccCreateTime($format = 'Y-m-d H:i:s')
	{
		if ($this->acc_create_time === null) {
			return null;
		}



		try {
			$dt = new DateTime($this->acc_create_time);
		} catch (Exception $x) {
			throw new PropelException("Internally stored date/time/timestamp value could not be converted to DateTime: " . var_export($this->acc_create_time, true), $x);
		}

		if ($format === null) {
			// Because propel.useDateTimeClass is TRUE, we return a DateTime object.
			return $dt;
		} elseif (strpos($format, '%') !== false) {
			return strftime($format, $dt->format('U'));
		} else {
			return $dt->format($format);
		}
	}

	/**
	 * Get the [optionally formatted] temporal [last_login] column value.
	 * 
	 *
	 * @param      string $format The date/time format string (either date()-style or strftime()-style).
	 *							If format is NULL, then the raw DateTime object will be returned.
	 * @return     mixed Formatted date/time value as string or DateTime object (if format is NULL), NULL if column is NULL
	 * @throws     PropelException - if unable to parse/validate the date/time value.
	 */
	public function getLastLogin($format = 'Y-m-d H:i:s')
	{
		if ($this->last_login === null) {
			return null;
		}



		try {
			$dt = new DateTime($this->last_login);
		} catch (Exception $x) {
			throw new PropelException("Internally stored date/time/timestamp value could not be converted to DateTime: " . var_export($this->last_login, true), $x);
		}

		if ($format === null) {
			// Because propel.useDateTimeClass is TRUE, we return a DateTime object.
			return $dt;
		} elseif (strpos($format, '%') !== false) {
			return strftime($format, $dt->format('U'));
		} else {
			return $dt->format($format);
		}
	}

	/**
	 * Get the [optionally formatted] temporal [last_logout] column value.
	 * 
	 *
	 * @param      string $format The date/time format string (either date()-style or strftime()-style).
	 *							If format is NULL, then the raw DateTime object will be returned.
	 * @return     mixed Formatted date/time value as string or DateTime object (if format is NULL), NULL if column is NULL
	 * @throws     PropelException - if unable to parse/validate the date/time value.
	 */
	public function getLastLogout($format = 'Y-m-d H:i:s')
	{
		if ($this->last_logout === null) {
			return null;
		}



		try {
			$dt = new DateTime($this->last_logout);
		} catch (Exception $x) {
			throw new PropelException("Internally stored date/time/timestamp value could not be converted to DateTime: " . var_export($this->last_logout, true), $x);
		}

		if ($format === null) {
			// Because propel.useDateTimeClass is TRUE, we return a DateTime object.
			return $dt;
		} elseif (strpos($format, '%') !== false) {
			return strftime($format, $dt->format('U'));
		} else {
			return $dt->format($format);
		}
	}

	/**
	 * Get the [online_status] column value.
	 * 
	 * @return     int
	 */
	public function getOnlineStatus()
	{
		return $this->online_status;
	}

	/**
	 * Get the [failed_logins] column value.
	 * 
	 * @return     int
	 */
	public function getFailedLogins()
	{
		return $this->failed_logins;
	}

	/**
	 * Get the [account_status] column value.
	 * 
	 * @return     int
	 */
	public function getAccountStatus()
	{
		return $this->account_status;
	}

	/**
	 * Set the value of [uid] column.
	 * 
	 * @param      string $v new value
	 * @return     TigUsers The current object (for fluent API support)
	 */
	public function setUid($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->uid !== $v) {
			$this->uid = $v;
			$this->modifiedColumns[] = TigUsersPeer::UID;
		}

		return $this;
	} // setUid()

	/**
	 * Set the value of [user_id] column.
	 * 
	 * @param      string $v new value
	 * @return     TigUsers The current object (for fluent API support)
	 */
	public function setUserId($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->user_id !== $v) {
			$this->user_id = $v;
			$this->modifiedColumns[] = TigUsersPeer::USER_ID;
		}

		return $this;
	} // setUserId()

	/**
	 * Set the value of [sha1_user_id] column.
	 * 
	 * @param      string $v new value
	 * @return     TigUsers The current object (for fluent API support)
	 */
	public function setSha1UserId($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->sha1_user_id !== $v) {
			$this->sha1_user_id = $v;
			$this->modifiedColumns[] = TigUsersPeer::SHA1_USER_ID;
		}

		return $this;
	} // setSha1UserId()

	/**
	 * Set the value of [user_pw] column.
	 * 
	 * @param      string $v new value
	 * @return     TigUsers The current object (for fluent API support)
	 */
	public function setUserPw($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->user_pw !== $v) {
			$this->user_pw = $v;
			$this->modifiedColumns[] = TigUsersPeer::USER_PW;
		}

		return $this;
	} // setUserPw()

	/**
	 * Sets the value of [acc_create_time] column to a normalized version of the date/time value specified.
	 * 
	 * @param      mixed $v string, integer (timestamp), or DateTime value.  Empty string will
	 *						be treated as NULL for temporal objects.
	 * @return     TigUsers The current object (for fluent API support)
	 */
	public function setAccCreateTime($v)
	{
		// we treat '' as NULL for temporal objects because DateTime('') == DateTime('now')
		// -- which is unexpected, to say the least.
		if ($v === null || $v === '') {
			$dt = null;
		} elseif ($v instanceof DateTime) {
			$dt = $v;
		} else {
			// some string/numeric value passed; we normalize that so that we can
			// validate it.
			try {
				if (is_numeric($v)) { // if it's a unix timestamp
					$dt = new DateTime('@'.$v, new DateTimeZone('UTC'));
					// We have to explicitly specify and then change the time zone because of a
					// DateTime bug: http://bugs.php.net/bug.php?id=43003
					$dt->setTimeZone(new DateTimeZone(date_default_timezone_get()));
				} else {
					$dt = new DateTime($v);
				}
			} catch (Exception $x) {
				throw new PropelException('Error parsing date/time value: ' . var_export($v, true), $x);
			}
		}

		if ( $this->acc_create_time !== null || $dt !== null ) {
			// (nested ifs are a little easier to read in this case)

			$currNorm = ($this->acc_create_time !== null && $tmpDt = new DateTime($this->acc_create_time)) ? $tmpDt->format('Y-m-d H:i:s') : null;
			$newNorm = ($dt !== null) ? $dt->format('Y-m-d H:i:s') : null;

			if ( ($currNorm !== $newNorm) // normalized values don't match 
					)
			{
				$this->acc_create_time = ($dt ? $dt->format('Y-m-d H:i:s') : null);
				$this->modifiedColumns[] = TigUsersPeer::ACC_CREATE_TIME;
			}
		} // if either are not null

		return $this;
	} // setAccCreateTime()

	/**
	 * Sets the value of [last_login] column to a normalized version of the date/time value specified.
	 * 
	 * @param      mixed $v string, integer (timestamp), or DateTime value.  Empty string will
	 *						be treated as NULL for temporal objects.
	 * @return     TigUsers The current object (for fluent API support)
	 */
	public function setLastLogin($v)
	{
		// we treat '' as NULL for temporal objects because DateTime('') == DateTime('now')
		// -- which is unexpected, to say the least.
		if ($v === null || $v === '') {
			$dt = null;
		} elseif ($v instanceof DateTime) {
			$dt = $v;
		} else {
			// some string/numeric value passed; we normalize that so that we can
			// validate it.
			try {
				if (is_numeric($v)) { // if it's a unix timestamp
					$dt = new DateTime('@'.$v, new DateTimeZone('UTC'));
					// We have to explicitly specify and then change the time zone because of a
					// DateTime bug: http://bugs.php.net/bug.php?id=43003
					$dt->setTimeZone(new DateTimeZone(date_default_timezone_get()));
				} else {
					$dt = new DateTime($v);
				}
			} catch (Exception $x) {
				throw new PropelException('Error parsing date/time value: ' . var_export($v, true), $x);
			}
		}

		if ( $this->last_login !== null || $dt !== null ) {
			// (nested ifs are a little easier to read in this case)

			$currNorm = ($this->last_login !== null && $tmpDt = new DateTime($this->last_login)) ? $tmpDt->format('Y-m-d H:i:s') : null;
			$newNorm = ($dt !== null) ? $dt->format('Y-m-d H:i:s') : null;

			if ( ($currNorm !== $newNorm) // normalized values don't match 
					|| ($dt->format('Y-m-d H:i:s') === '-0001-11-30 00:00:00') // or the entered value matches the default
					)
			{
				$this->last_login = ($dt ? $dt->format('Y-m-d H:i:s') : null);
				$this->modifiedColumns[] = TigUsersPeer::LAST_LOGIN;
			}
		} // if either are not null

		return $this;
	} // setLastLogin()

	/**
	 * Sets the value of [last_logout] column to a normalized version of the date/time value specified.
	 * 
	 * @param      mixed $v string, integer (timestamp), or DateTime value.  Empty string will
	 *						be treated as NULL for temporal objects.
	 * @return     TigUsers The current object (for fluent API support)
	 */
	public function setLastLogout($v)
	{
		// we treat '' as NULL for temporal objects because DateTime('') == DateTime('now')
		// -- which is unexpected, to say the least.
		if ($v === null || $v === '') {
			$dt = null;
		} elseif ($v instanceof DateTime) {
			$dt = $v;
		} else {
			// some string/numeric value passed; we normalize that so that we can
			// validate it.
			try {
				if (is_numeric($v)) { // if it's a unix timestamp
					$dt = new DateTime('@'.$v, new DateTimeZone('UTC'));
					// We have to explicitly specify and then change the time zone because of a
					// DateTime bug: http://bugs.php.net/bug.php?id=43003
					$dt->setTimeZone(new DateTimeZone(date_default_timezone_get()));
				} else {
					$dt = new DateTime($v);
				}
			} catch (Exception $x) {
				throw new PropelException('Error parsing date/time value: ' . var_export($v, true), $x);
			}
		}

		if ( $this->last_logout !== null || $dt !== null ) {
			// (nested ifs are a little easier to read in this case)

			$currNorm = ($this->last_logout !== null && $tmpDt = new DateTime($this->last_logout)) ? $tmpDt->format('Y-m-d H:i:s') : null;
			$newNorm = ($dt !== null) ? $dt->format('Y-m-d H:i:s') : null;

			if ( ($currNorm !== $newNorm) // normalized values don't match 
					|| ($dt->format('Y-m-d H:i:s') === '-0001-11-30 00:00:00') // or the entered value matches the default
					)
			{
				$this->last_logout = ($dt ? $dt->format('Y-m-d H:i:s') : null);
				$this->modifiedColumns[] = TigUsersPeer::LAST_LOGOUT;
			}
		} // if either are not null

		return $this;
	} // setLastLogout()

	/**
	 * Set the value of [online_status] column.
	 * 
	 * @param      int $v new value
	 * @return     TigUsers The current object (for fluent API support)
	 */
	public function setOnlineStatus($v)
	{
		if ($v !== null) {
			$v = (int) $v;
		}

		if ($this->online_status !== $v || $this->isNew()) {
			$this->online_status = $v;
			$this->modifiedColumns[] = TigUsersPeer::ONLINE_STATUS;
		}

		return $this;
	} // setOnlineStatus()

	/**
	 * Set the value of [failed_logins] column.
	 * 
	 * @param      int $v new value
	 * @return     TigUsers The current object (for fluent API support)
	 */
	public function setFailedLogins($v)
	{
		if ($v !== null) {
			$v = (int) $v;
		}

		if ($this->failed_logins !== $v || $this->isNew()) {
			$this->failed_logins = $v;
			$this->modifiedColumns[] = TigUsersPeer::FAILED_LOGINS;
		}

		return $this;
	} // setFailedLogins()

	/**
	 * Set the value of [account_status] column.
	 * 
	 * @param      int $v new value
	 * @return     TigUsers The current object (for fluent API support)
	 */
	public function setAccountStatus($v)
	{
		if ($v !== null) {
			$v = (int) $v;
		}

		if ($this->account_status !== $v || $this->isNew()) {
			$this->account_status = $v;
			$this->modifiedColumns[] = TigUsersPeer::ACCOUNT_STATUS;
		}

		return $this;
	} // setAccountStatus()

	/**
	 * Indicates whether the columns in this object are only set to default values.
	 *
	 * This method can be used in conjunction with isModified() to indicate whether an object is both
	 * modified _and_ has some values set which are non-default.
	 *
	 * @return     boolean Whether the columns in this object are only been set with default values.
	 */
	public function hasOnlyDefaultValues()
	{
			if ($this->last_login !== '-0001-11-30 00:00:00') {
				return false;
			}

			if ($this->last_logout !== '-0001-11-30 00:00:00') {
				return false;
			}

			if ($this->online_status !== 0) {
				return false;
			}

			if ($this->failed_logins !== 0) {
				return false;
			}

			if ($this->account_status !== 1) {
				return false;
			}

		// otherwise, everything was equal, so return TRUE
		return true;
	} // hasOnlyDefaultValues()

	/**
	 * Hydrates (populates) the object variables with values from the database resultset.
	 *
	 * An offset (0-based "start column") is specified so that objects can be hydrated
	 * with a subset of the columns in the resultset rows.  This is needed, for example,
	 * for results of JOIN queries where the resultset row includes columns from two or
	 * more tables.
	 *
	 * @param      array $row The row returned by PDOStatement->fetch(PDO::FETCH_NUM)
	 * @param      int $startcol 0-based offset column which indicates which restultset column to start with.
	 * @param      boolean $rehydrate Whether this object is being re-hydrated from the database.
	 * @return     int next starting column
	 * @throws     PropelException  - Any caught Exception will be rewrapped as a PropelException.
	 */
	public function hydrate($row, $startcol = 0, $rehydrate = false)
	{
		try {

			$this->uid = ($row[$startcol + 0] !== null) ? (string) $row[$startcol + 0] : null;
			$this->user_id = ($row[$startcol + 1] !== null) ? (string) $row[$startcol + 1] : null;
			$this->sha1_user_id = ($row[$startcol + 2] !== null) ? (string) $row[$startcol + 2] : null;
			$this->user_pw = ($row[$startcol + 3] !== null) ? (string) $row[$startcol + 3] : null;
			$this->acc_create_time = ($row[$startcol + 4] !== null) ? (string) $row[$startcol + 4] : null;
			$this->last_login = ($row[$startcol + 5] !== null) ? (string) $row[$startcol + 5] : null;
			$this->last_logout = ($row[$startcol + 6] !== null) ? (string) $row[$startcol + 6] : null;
			$this->online_status = ($row[$startcol + 7] !== null) ? (int) $row[$startcol + 7] : null;
			$this->failed_logins = ($row[$startcol + 8] !== null) ? (int) $row[$startcol + 8] : null;
			$this->account_status = ($row[$startcol + 9] !== null) ? (int) $row[$startcol + 9] : null;
			$this->resetModified();

			$this->setNew(false);

			if ($rehydrate) {
				$this->ensureConsistency();
			}

			return $startcol + 10; // 10 = TigUsersPeer::NUM_COLUMNS - TigUsersPeer::NUM_LAZY_LOAD_COLUMNS).

		} catch (Exception $e) {
			throw new PropelException("Error populating TigUsers object", $e);
		}
	}

	/**
	 * Checks and repairs the internal consistency of the object.
	 *
	 * This method is executed after an already-instantiated object is re-hydrated
	 * from the database.  It exists to check any foreign keys to make sure that
	 * the objects related to the current object are correct based on foreign key.
	 *
	 * You can override this method in the stub class, but you should always invoke
	 * the base method from the overridden method (i.e. parent::ensureConsistency()),
	 * in case your model changes.
	 *
	 * @throws     PropelException
	 */
	public function ensureConsistency()
	{

	} // ensureConsistency

	/**
	 * Reloads this object from datastore based on primary key and (optionally) resets all associated objects.
	 *
	 * This will only work if the object has been saved and has a valid primary key set.
	 *
	 * @param      boolean $deep (optional) Whether to also de-associated any related objects.
	 * @param      PropelPDO $con (optional) The PropelPDO connection to use.
	 * @return     void
	 * @throws     PropelException - if this object is deleted, unsaved or doesn't have pk match in db
	 */
	public function reload($deep = false, PropelPDO $con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("Cannot reload a deleted object.");
		}

		if ($this->isNew()) {
			throw new PropelException("Cannot reload an unsaved object.");
		}

		if ($con === null) {
			$con = Propel::getConnection(TigUsersPeer::DATABASE_NAME, Propel::CONNECTION_READ);
		}

		// We don't need to alter the object instance pool; we're just modifying this instance
		// already in the pool.

		$stmt = TigUsersPeer::doSelectStmt($this->buildPkeyCriteria(), $con);
		$row = $stmt->fetch(PDO::FETCH_NUM);
		$stmt->closeCursor();
		if (!$row) {
			throw new PropelException('Cannot find matching row in the database to reload object values.');
		}
		$this->hydrate($row, 0, true); // rehydrate

		if ($deep) {  // also de-associate any related objects?

			$this->collTigNodess = null;

			$this->collTigPairss = null;

		} // if (deep)
	}

	/**
	 * Removes this object from datastore and sets delete attribute.
	 *
	 * @param      PropelPDO $con
	 * @return     void
	 * @throws     PropelException
	 * @see        BaseObject::setDeleted()
	 * @see        BaseObject::isDeleted()
	 */
	public function delete(PropelPDO $con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(TigUsersPeer::DATABASE_NAME, Propel::CONNECTION_WRITE);
		}
		
		$con->beginTransaction();
		try {
			$ret = $this->preDelete($con);
			if ($ret) {
				TigUsersQuery::create()
					->filterByPrimaryKey($this->getPrimaryKey())
					->delete($con);
				$this->postDelete($con);
				$con->commit();
				$this->setDeleted(true);
			} else {
				$con->commit();
			}
		} catch (PropelException $e) {
			$con->rollBack();
			throw $e;
		}
	}

	/**
	 * Persists this object to the database.
	 *
	 * If the object is new, it inserts it; otherwise an update is performed.
	 * All modified related objects will also be persisted in the doSave()
	 * method.  This method wraps all precipitate database operations in a
	 * single transaction.
	 *
	 * @param      PropelPDO $con
	 * @return     int The number of rows affected by this insert/update and any referring fk objects' save() operations.
	 * @throws     PropelException
	 * @see        doSave()
	 */
	public function save(PropelPDO $con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("You cannot save an object that has been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(TigUsersPeer::DATABASE_NAME, Propel::CONNECTION_WRITE);
		}
		
		$con->beginTransaction();
		$isInsert = $this->isNew();
		try {
			$ret = $this->preSave($con);
			if ($isInsert) {
				$ret = $ret && $this->preInsert($con);
			} else {
				$ret = $ret && $this->preUpdate($con);
			}
			if ($ret) {
				$affectedRows = $this->doSave($con);
				if ($isInsert) {
					$this->postInsert($con);
				} else {
					$this->postUpdate($con);
				}
				$this->postSave($con);
				TigUsersPeer::addInstanceToPool($this);
			} else {
				$affectedRows = 0;
			}
			$con->commit();
			return $affectedRows;
		} catch (PropelException $e) {
			$con->rollBack();
			throw $e;
		}
	}

	/**
	 * Performs the work of inserting or updating the row in the database.
	 *
	 * If the object is new, it inserts it; otherwise an update is performed.
	 * All related objects are also updated in this method.
	 *
	 * @param      PropelPDO $con
	 * @return     int The number of rows affected by this insert/update and any referring fk objects' save() operations.
	 * @throws     PropelException
	 * @see        save()
	 */
	protected function doSave(PropelPDO $con)
	{
		$affectedRows = 0; // initialize var to track total num of affected rows
		if (!$this->alreadyInSave) {
			$this->alreadyInSave = true;

			if ($this->isNew() ) {
				$this->modifiedColumns[] = TigUsersPeer::UID;
			}

			// If this object has been modified, then save it to the database.
			if ($this->isModified()) {
				if ($this->isNew()) {
					$criteria = $this->buildCriteria();
					if ($criteria->keyContainsValue(TigUsersPeer::UID) ) {
						throw new PropelException('Cannot insert a value for auto-increment primary key ('.TigUsersPeer::UID.')');
					}

					// remove pkey col since this table uses auto-increment and passing a null value for it is not valid 
					$criteria->remove(TigUsersPeer::UID);

					$pk = BasePeer::doInsert($criteria, $con);
					$affectedRows = 1;
					$this->setUid($pk);  //[IMV] update autoincrement primary key
					$this->setNew(false);
				} else {
					$affectedRows = TigUsersPeer::doUpdate($this, $con);
				}

				$this->resetModified(); // [HL] After being saved an object is no longer 'modified'
			}

			if ($this->collTigNodess !== null) {
				foreach ($this->collTigNodess as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collTigPairss !== null) {
				foreach ($this->collTigPairss as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			$this->alreadyInSave = false;

		}
		return $affectedRows;
	} // doSave()

	/**
	 * Array of ValidationFailed objects.
	 * @var        array ValidationFailed[]
	 */
	protected $validationFailures = array();

	/**
	 * Gets any ValidationFailed objects that resulted from last call to validate().
	 *
	 *
	 * @return     array ValidationFailed[]
	 * @see        validate()
	 */
	public function getValidationFailures()
	{
		return $this->validationFailures;
	}

	/**
	 * Validates the objects modified field values and all objects related to this table.
	 *
	 * If $columns is either a column name or an array of column names
	 * only those columns are validated.
	 *
	 * @param      mixed $columns Column name or an array of column names.
	 * @return     boolean Whether all columns pass validation.
	 * @see        doValidate()
	 * @see        getValidationFailures()
	 */
	public function validate($columns = null)
	{
		$res = $this->doValidate($columns);
		if ($res === true) {
			$this->validationFailures = array();
			return true;
		} else {
			$this->validationFailures = $res;
			return false;
		}
	}

	/**
	 * This function performs the validation work for complex object models.
	 *
	 * In addition to checking the current object, all related objects will
	 * also be validated.  If all pass then <code>true</code> is returned; otherwise
	 * an aggreagated array of ValidationFailed objects will be returned.
	 *
	 * @param      array $columns Array of column names to validate.
	 * @return     mixed <code>true</code> if all validations pass; array of <code>ValidationFailed</code> objets otherwise.
	 */
	protected function doValidate($columns = null)
	{
		if (!$this->alreadyInValidation) {
			$this->alreadyInValidation = true;
			$retval = null;

			$failureMap = array();


			if (($retval = TigUsersPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}


				if ($this->collTigNodess !== null) {
					foreach ($this->collTigNodess as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collTigPairss !== null) {
					foreach ($this->collTigPairss as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}


			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	/**
	 * Retrieves a field from the object by name passed in as a string.
	 *
	 * @param      string $name name
	 * @param      string $type The type of fieldname the $name is of:
	 *                     one of the class type constants BasePeer::TYPE_PHPNAME, BasePeer::TYPE_STUDLYPHPNAME
	 *                     BasePeer::TYPE_COLNAME, BasePeer::TYPE_FIELDNAME, BasePeer::TYPE_NUM
	 * @return     mixed Value of field.
	 */
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = TigUsersPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		$field = $this->getByPosition($pos);
		return $field;
	}

	/**
	 * Retrieves a field from the object by Position as specified in the xml schema.
	 * Zero-based.
	 *
	 * @param      int $pos position in xml schema
	 * @return     mixed Value of field at $pos
	 */
	public function getByPosition($pos)
	{
		switch($pos) {
			case 0:
				return $this->getUid();
				break;
			case 1:
				return $this->getUserId();
				break;
			case 2:
				return $this->getSha1UserId();
				break;
			case 3:
				return $this->getUserPw();
				break;
			case 4:
				return $this->getAccCreateTime();
				break;
			case 5:
				return $this->getLastLogin();
				break;
			case 6:
				return $this->getLastLogout();
				break;
			case 7:
				return $this->getOnlineStatus();
				break;
			case 8:
				return $this->getFailedLogins();
				break;
			case 9:
				return $this->getAccountStatus();
				break;
			default:
				return null;
				break;
		} // switch()
	}

	/**
	 * Exports the object as an array.
	 *
	 * You can specify the key type of the array by passing one of the class
	 * type constants.
	 *
	 * @param     string  $keyType (optional) One of the class type constants BasePeer::TYPE_PHPNAME, BasePeer::TYPE_STUDLYPHPNAME,
	 *                    BasePeer::TYPE_COLNAME, BasePeer::TYPE_FIELDNAME, BasePeer::TYPE_NUM. 
	 *                    Defaults to BasePeer::TYPE_PHPNAME.
	 * @param     boolean $includeLazyLoadColumns (optional) Whether to include lazy loaded columns. Defaults to TRUE.
	 *
	 * @return    array an associative array containing the field names (as keys) and field values
	 */
	public function toArray($keyType = BasePeer::TYPE_PHPNAME, $includeLazyLoadColumns = true)
	{
		$keys = TigUsersPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getUid(),
			$keys[1] => $this->getUserId(),
			$keys[2] => $this->getSha1UserId(),
			$keys[3] => $this->getUserPw(),
			$keys[4] => $this->getAccCreateTime(),
			$keys[5] => $this->getLastLogin(),
			$keys[6] => $this->getLastLogout(),
			$keys[7] => $this->getOnlineStatus(),
			$keys[8] => $this->getFailedLogins(),
			$keys[9] => $this->getAccountStatus(),
		);
		return $result;
	}

	/**
	 * Sets a field from the object by name passed in as a string.
	 *
	 * @param      string $name peer name
	 * @param      mixed $value field value
	 * @param      string $type The type of fieldname the $name is of:
	 *                     one of the class type constants BasePeer::TYPE_PHPNAME, BasePeer::TYPE_STUDLYPHPNAME
	 *                     BasePeer::TYPE_COLNAME, BasePeer::TYPE_FIELDNAME, BasePeer::TYPE_NUM
	 * @return     void
	 */
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = TigUsersPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->setByPosition($pos, $value);
	}

	/**
	 * Sets a field from the object by Position as specified in the xml schema.
	 * Zero-based.
	 *
	 * @param      int $pos position in xml schema
	 * @param      mixed $value field value
	 * @return     void
	 */
	public function setByPosition($pos, $value)
	{
		switch($pos) {
			case 0:
				$this->setUid($value);
				break;
			case 1:
				$this->setUserId($value);
				break;
			case 2:
				$this->setSha1UserId($value);
				break;
			case 3:
				$this->setUserPw($value);
				break;
			case 4:
				$this->setAccCreateTime($value);
				break;
			case 5:
				$this->setLastLogin($value);
				break;
			case 6:
				$this->setLastLogout($value);
				break;
			case 7:
				$this->setOnlineStatus($value);
				break;
			case 8:
				$this->setFailedLogins($value);
				break;
			case 9:
				$this->setAccountStatus($value);
				break;
		} // switch()
	}

	/**
	 * Populates the object using an array.
	 *
	 * This is particularly useful when populating an object from one of the
	 * request arrays (e.g. $_POST).  This method goes through the column
	 * names, checking to see whether a matching key exists in populated
	 * array. If so the setByName() method is called for that column.
	 *
	 * You can specify the key type of the array by additionally passing one
	 * of the class type constants BasePeer::TYPE_PHPNAME, BasePeer::TYPE_STUDLYPHPNAME,
	 * BasePeer::TYPE_COLNAME, BasePeer::TYPE_FIELDNAME, BasePeer::TYPE_NUM.
	 * The default key type is the column's phpname (e.g. 'AuthorId')
	 *
	 * @param      array  $arr     An array to populate the object from.
	 * @param      string $keyType The type of keys the array uses.
	 * @return     void
	 */
	public function fromArray($arr, $keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = TigUsersPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setUid($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setUserId($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setSha1UserId($arr[$keys[2]]);
		if (array_key_exists($keys[3], $arr)) $this->setUserPw($arr[$keys[3]]);
		if (array_key_exists($keys[4], $arr)) $this->setAccCreateTime($arr[$keys[4]]);
		if (array_key_exists($keys[5], $arr)) $this->setLastLogin($arr[$keys[5]]);
		if (array_key_exists($keys[6], $arr)) $this->setLastLogout($arr[$keys[6]]);
		if (array_key_exists($keys[7], $arr)) $this->setOnlineStatus($arr[$keys[7]]);
		if (array_key_exists($keys[8], $arr)) $this->setFailedLogins($arr[$keys[8]]);
		if (array_key_exists($keys[9], $arr)) $this->setAccountStatus($arr[$keys[9]]);
	}

	/**
	 * Build a Criteria object containing the values of all modified columns in this object.
	 *
	 * @return     Criteria The Criteria object containing all modified values.
	 */
	public function buildCriteria()
	{
		$criteria = new Criteria(TigUsersPeer::DATABASE_NAME);

		if ($this->isColumnModified(TigUsersPeer::UID)) $criteria->add(TigUsersPeer::UID, $this->uid);
		if ($this->isColumnModified(TigUsersPeer::USER_ID)) $criteria->add(TigUsersPeer::USER_ID, $this->user_id);
		if ($this->isColumnModified(TigUsersPeer::SHA1_USER_ID)) $criteria->add(TigUsersPeer::SHA1_USER_ID, $this->sha1_user_id);
		if ($this->isColumnModified(TigUsersPeer::USER_PW)) $criteria->add(TigUsersPeer::USER_PW, $this->user_pw);
		if ($this->isColumnModified(TigUsersPeer::ACC_CREATE_TIME)) $criteria->add(TigUsersPeer::ACC_CREATE_TIME, $this->acc_create_time);
		if ($this->isColumnModified(TigUsersPeer::LAST_LOGIN)) $criteria->add(TigUsersPeer::LAST_LOGIN, $this->last_login);
		if ($this->isColumnModified(TigUsersPeer::LAST_LOGOUT)) $criteria->add(TigUsersPeer::LAST_LOGOUT, $this->last_logout);
		if ($this->isColumnModified(TigUsersPeer::ONLINE_STATUS)) $criteria->add(TigUsersPeer::ONLINE_STATUS, $this->online_status);
		if ($this->isColumnModified(TigUsersPeer::FAILED_LOGINS)) $criteria->add(TigUsersPeer::FAILED_LOGINS, $this->failed_logins);
		if ($this->isColumnModified(TigUsersPeer::ACCOUNT_STATUS)) $criteria->add(TigUsersPeer::ACCOUNT_STATUS, $this->account_status);

		return $criteria;
	}

	/**
	 * Builds a Criteria object containing the primary key for this object.
	 *
	 * Unlike buildCriteria() this method includes the primary key values regardless
	 * of whether or not they have been modified.
	 *
	 * @return     Criteria The Criteria object containing value(s) for primary key(s).
	 */
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(TigUsersPeer::DATABASE_NAME);
		$criteria->add(TigUsersPeer::UID, $this->uid);

		return $criteria;
	}

	/**
	 * Returns the primary key for this object (row).
	 * @return     string
	 */
	public function getPrimaryKey()
	{
		return $this->getUid();
	}

	/**
	 * Generic method to set the primary key (uid column).
	 *
	 * @param      string $key Primary key.
	 * @return     void
	 */
	public function setPrimaryKey($key)
	{
		$this->setUid($key);
	}

	/**
	 * Returns true if the primary key for this object is null.
	 * @return     boolean
	 */
	public function isPrimaryKeyNull()
	{
		return null === $this->getUid();
	}

	/**
	 * Sets contents of passed object to values from current object.
	 *
	 * If desired, this method can also make copies of all associated (fkey referrers)
	 * objects.
	 *
	 * @param      object $copyObj An object of TigUsers (or compatible) type.
	 * @param      boolean $deepCopy Whether to also copy all rows that refer (by fkey) to the current row.
	 * @throws     PropelException
	 */
	public function copyInto($copyObj, $deepCopy = false)
	{
		$copyObj->setUserId($this->user_id);
		$copyObj->setSha1UserId($this->sha1_user_id);
		$copyObj->setUserPw($this->user_pw);
		$copyObj->setAccCreateTime($this->acc_create_time);
		$copyObj->setLastLogin($this->last_login);
		$copyObj->setLastLogout($this->last_logout);
		$copyObj->setOnlineStatus($this->online_status);
		$copyObj->setFailedLogins($this->failed_logins);
		$copyObj->setAccountStatus($this->account_status);

		if ($deepCopy) {
			// important: temporarily setNew(false) because this affects the behavior of
			// the getter/setter methods for fkey referrer objects.
			$copyObj->setNew(false);

			foreach ($this->getTigNodess() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addTigNodes($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getTigPairss() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addTigPairs($relObj->copy($deepCopy));
				}
			}

		} // if ($deepCopy)


		$copyObj->setNew(true);
		$copyObj->setUid(NULL); // this is a auto-increment column, so set to default value
	}

	/**
	 * Makes a copy of this object that will be inserted as a new row in table when saved.
	 * It creates a new object filling in the simple attributes, but skipping any primary
	 * keys that are defined for the table.
	 *
	 * If desired, this method can also make copies of all associated (fkey referrers)
	 * objects.
	 *
	 * @param      boolean $deepCopy Whether to also copy all rows that refer (by fkey) to the current row.
	 * @return     TigUsers Clone of current object.
	 * @throws     PropelException
	 */
	public function copy($deepCopy = false)
	{
		// we use get_class(), because this might be a subclass
		$clazz = get_class($this);
		$copyObj = new $clazz();
		$this->copyInto($copyObj, $deepCopy);
		return $copyObj;
	}

	/**
	 * Returns a peer instance associated with this om.
	 *
	 * Since Peer classes are not to have any instance attributes, this method returns the
	 * same instance for all member of this class. The method could therefore
	 * be static, but this would prevent one from overriding the behavior.
	 *
	 * @return     TigUsersPeer
	 */
	public function getPeer()
	{
		if (self::$peer === null) {
			self::$peer = new TigUsersPeer();
		}
		return self::$peer;
	}

	/**
	 * Clears out the collTigNodess collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addTigNodess()
	 */
	public function clearTigNodess()
	{
		$this->collTigNodess = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collTigNodess collection.
	 *
	 * By default this just sets the collTigNodess collection to an empty array (like clearcollTigNodess());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initTigNodess()
	{
		$this->collTigNodess = new PropelObjectCollection();
		$this->collTigNodess->setModel('TigNodes');
	}

	/**
	 * Gets an array of TigNodes objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this TigUsers is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array TigNodes[] List of TigNodes objects
	 * @throws     PropelException
	 */
	public function getTigNodess($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collTigNodess || null !== $criteria) {
			if ($this->isNew() && null === $this->collTigNodess) {
				// return empty collection
				$this->initTigNodess();
			} else {
				$collTigNodess = TigNodesQuery::create(null, $criteria)
					->filterByTigUsers($this)
					->find($con);
				if (null !== $criteria) {
					return $collTigNodess;
				}
				$this->collTigNodess = $collTigNodess;
			}
		}
		return $this->collTigNodess;
	}

	/**
	 * Returns the number of related TigNodes objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related TigNodes objects.
	 * @throws     PropelException
	 */
	public function countTigNodess(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collTigNodess || null !== $criteria) {
			if ($this->isNew() && null === $this->collTigNodess) {
				return 0;
			} else {
				$query = TigNodesQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByTigUsers($this)
					->count($con);
			}
		} else {
			return count($this->collTigNodess);
		}
	}

	/**
	 * Method called to associate a TigNodes object to this object
	 * through the TigNodes foreign key attribute.
	 *
	 * @param      TigNodes $l TigNodes
	 * @return     void
	 * @throws     PropelException
	 */
	public function addTigNodes(TigNodes $l)
	{
		if ($this->collTigNodess === null) {
			$this->initTigNodess();
		}
		if (!$this->collTigNodess->contains($l)) { // only add it if the **same** object is not already associated
			$this->collTigNodess[]= $l;
			$l->setTigUsers($this);
		}
	}

	/**
	 * Clears out the collTigPairss collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addTigPairss()
	 */
	public function clearTigPairss()
	{
		$this->collTigPairss = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collTigPairss collection.
	 *
	 * By default this just sets the collTigPairss collection to an empty array (like clearcollTigPairss());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initTigPairss()
	{
		$this->collTigPairss = new PropelObjectCollection();
		$this->collTigPairss->setModel('TigPairs');
	}

	/**
	 * Gets an array of TigPairs objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this TigUsers is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array TigPairs[] List of TigPairs objects
	 * @throws     PropelException
	 */
	public function getTigPairss($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collTigPairss || null !== $criteria) {
			if ($this->isNew() && null === $this->collTigPairss) {
				// return empty collection
				$this->initTigPairss();
			} else {
				$collTigPairss = TigPairsQuery::create(null, $criteria)
					->filterByTigUsers($this)
					->find($con);
				if (null !== $criteria) {
					return $collTigPairss;
				}
				$this->collTigPairss = $collTigPairss;
			}
		}
		return $this->collTigPairss;
	}

	/**
	 * Returns the number of related TigPairs objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related TigPairs objects.
	 * @throws     PropelException
	 */
	public function countTigPairss(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collTigPairss || null !== $criteria) {
			if ($this->isNew() && null === $this->collTigPairss) {
				return 0;
			} else {
				$query = TigPairsQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByTigUsers($this)
					->count($con);
			}
		} else {
			return count($this->collTigPairss);
		}
	}

	/**
	 * Method called to associate a TigPairs object to this object
	 * through the TigPairs foreign key attribute.
	 *
	 * @param      TigPairs $l TigPairs
	 * @return     void
	 * @throws     PropelException
	 */
	public function addTigPairs(TigPairs $l)
	{
		if ($this->collTigPairss === null) {
			$this->initTigPairss();
		}
		if (!$this->collTigPairss->contains($l)) { // only add it if the **same** object is not already associated
			$this->collTigPairss[]= $l;
			$l->setTigUsers($this);
		}
	}


	/**
	 * If this collection has already been initialized with
	 * an identical criteria, it returns the collection.
	 * Otherwise if this TigUsers is new, it will return
	 * an empty collection; or if this TigUsers has previously
	 * been saved, it will retrieve related TigPairss from storage.
	 *
	 * This method is protected by default in order to keep the public
	 * api reasonable.  You can provide public methods for those you
	 * actually need in TigUsers.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @param      string $join_behavior optional join type to use (defaults to Criteria::LEFT_JOIN)
	 * @return     PropelCollection|array TigPairs[] List of TigPairs objects
	 */
	public function getTigPairssJoinTigNodes($criteria = null, $con = null, $join_behavior = Criteria::LEFT_JOIN)
	{
		$query = TigPairsQuery::create(null, $criteria);
		$query->joinWith('TigNodes', $join_behavior);

		return $this->getTigPairss($query, $con);
	}

	/**
	 * Clears the current object and sets all attributes to their default values
	 */
	public function clear()
	{
		$this->uid = null;
		$this->user_id = null;
		$this->sha1_user_id = null;
		$this->user_pw = null;
		$this->acc_create_time = null;
		$this->last_login = null;
		$this->last_logout = null;
		$this->online_status = null;
		$this->failed_logins = null;
		$this->account_status = null;
		$this->alreadyInSave = false;
		$this->alreadyInValidation = false;
		$this->clearAllReferences();
		$this->applyDefaultValues();
		$this->resetModified();
		$this->setNew(true);
		$this->setDeleted(false);
	}

	/**
	 * Resets all collections of referencing foreign keys.
	 *
	 * This method is a user-space workaround for PHP's inability to garbage collect objects
	 * with circular references.  This is currently necessary when using Propel in certain
	 * daemon or large-volumne/high-memory operations.
	 *
	 * @param      boolean $deep Whether to also clear the references on all associated objects.
	 */
	public function clearAllReferences($deep = false)
	{
		if ($deep) {
			if ($this->collTigNodess) {
				foreach ((array) $this->collTigNodess as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collTigPairss) {
				foreach ((array) $this->collTigPairss as $o) {
					$o->clearAllReferences($deep);
				}
			}
		} // if ($deep)

		$this->collTigNodess = null;
		$this->collTigPairss = null;
	}

	/**
	 * Catches calls to virtual methods
	 */
	public function __call($name, $params)
	{
		if (preg_match('/get(\w+)/', $name, $matches)) {
			$virtualColumn = $matches[1];
			if ($this->hasVirtualColumn($virtualColumn)) {
				return $this->getVirtualColumn($virtualColumn);
			}
			// no lcfirst in php<5.3...
			$virtualColumn[0] = strtolower($virtualColumn[0]);
			if ($this->hasVirtualColumn($virtualColumn)) {
				return $this->getVirtualColumn($virtualColumn);
			}
		}
		return parent::__call($name, $params);
	}

} // BaseTigUsers
