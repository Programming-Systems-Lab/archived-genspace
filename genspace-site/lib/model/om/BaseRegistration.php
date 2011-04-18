<?php


/**
 * Base class that represents a row from the 'registration' table.
 *
 * 
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseRegistration extends BaseObject  implements Persistent
{

	/**
	 * Peer class name
	 */
  const PEER = 'RegistrationPeer';

	/**
	 * The Peer class.
	 * Instance provides a convenient way of calling static methods on a class
	 * that calling code may not be able to identify.
	 * @var        RegistrationPeer
	 */
	protected static $peer;

	/**
	 * The value for the id field.
	 * @var        int
	 */
	protected $id;

	/**
	 * The value for the phone field.
	 * @var        string
	 */
	protected $phone;

	/**
	 * The value for the interests field.
	 * @var        string
	 */
	protected $interests;

	/**
	 * The value for the state field.
	 * @var        string
	 */
	protected $state;

	/**
	 * The value for the online_status field.
	 * @var        int
	 */
	protected $online_status;

	/**
	 * The value for the password field.
	 * @var        string
	 */
	protected $password;

	/**
	 * The value for the city field.
	 * @var        string
	 */
	protected $city;

	/**
	 * The value for the username field.
	 * @var        string
	 */
	protected $username;

	/**
	 * The value for the createdat field.
	 * @var        string
	 */
	protected $createdat;

	/**
	 * The value for the first_name field.
	 * @var        string
	 */
	protected $first_name;

	/**
	 * The value for the datavisibility field.
	 * @var        int
	 */
	protected $datavisibility;

	/**
	 * The value for the work_title field.
	 * @var        string
	 */
	protected $work_title;

	/**
	 * The value for the last_name field.
	 * @var        string
	 */
	protected $last_name;

	/**
	 * The value for the zipcode field.
	 * @var        string
	 */
	protected $zipcode;

	/**
	 * The value for the lab_affiliation field.
	 * @var        string
	 */
	protected $lab_affiliation;

	/**
	 * The value for the addr1 field.
	 * @var        string
	 */
	protected $addr1;

	/**
	 * The value for the addr2 field.
	 * @var        string
	 */
	protected $addr2;

	/**
	 * The value for the email field.
	 * @var        string
	 */
	protected $email;

	/**
	 * The value for the logdata field.
	 * @var        int
	 */
	protected $logdata;

	/**
	 * The value for the rootfolder_id field.
	 * @var        int
	 */
	protected $rootfolder_id;

	/**
	 * @var        array Toolcomments[] Collection to store aggregation of Toolcomments objects.
	 */
	protected $collToolcommentss;

	/**
	 * @var        array Toolratings[] Collection to store aggregation of Toolratings objects.
	 */
	protected $collToolratingss;

	/**
	 * @var        array Workflowcomments[] Collection to store aggregation of Workflowcomments objects.
	 */
	protected $collWorkflowcommentss;

	/**
	 * @var        array Workflowratings[] Collection to store aggregation of Workflowratings objects.
	 */
	protected $collWorkflowratingss;

	/**
	 * @var        array Annotation[] Collection to store aggregation of Annotation objects.
	 */
	protected $collAnnotations;

	/**
	 * @var        array History[] Collection to store aggregation of History objects.
	 */
	protected $collHistorys;

	/**
	 * @var        array Workspace[] Collection to store aggregation of Workspace objects.
	 */
	protected $collWorkspaces;

	/**
	 * @var        array WorkspaceUser[] Collection to store aggregation of WorkspaceUser objects.
	 */
	protected $collWorkspaceUsers;

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
	 * Get the [id] column value.
	 * 
	 * @return     int
	 */
	public function getId()
	{
		return $this->id;
	}

	/**
	 * Get the [phone] column value.
	 * 
	 * @return     string
	 */
	public function getPhone()
	{
		return $this->phone;
	}

	/**
	 * Get the [interests] column value.
	 * 
	 * @return     string
	 */
	public function getInterests()
	{
		return $this->interests;
	}

	/**
	 * Get the [state] column value.
	 * 
	 * @return     string
	 */
	public function getState()
	{
		return $this->state;
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
	 * Get the [password] column value.
	 * 
	 * @return     string
	 */
	public function getPassword()
	{
		return $this->password;
	}

	/**
	 * Get the [city] column value.
	 * 
	 * @return     string
	 */
	public function getCity()
	{
		return $this->city;
	}

	/**
	 * Get the [username] column value.
	 * 
	 * @return     string
	 */
	public function getUsername()
	{
		return $this->username;
	}

	/**
	 * Get the [optionally formatted] temporal [createdat] column value.
	 * 
	 *
	 * @param      string $format The date/time format string (either date()-style or strftime()-style).
	 *							If format is NULL, then the raw DateTime object will be returned.
	 * @return     mixed Formatted date/time value as string or DateTime object (if format is NULL), NULL if column is NULL
	 * @throws     PropelException - if unable to parse/validate the date/time value.
	 */
	public function getCreatedat($format = 'Y-m-d H:i:s')
	{
		if ($this->createdat === null) {
			return null;
		}



		try {
			$dt = new DateTime($this->createdat);
		} catch (Exception $x) {
			throw new PropelException("Internally stored date/time/timestamp value could not be converted to DateTime: " . var_export($this->createdat, true), $x);
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
	 * Get the [first_name] column value.
	 * 
	 * @return     string
	 */
	public function getFirstName()
	{
		return $this->first_name;
	}

	/**
	 * Get the [datavisibility] column value.
	 * 
	 * @return     int
	 */
	public function getDatavisibility()
	{
		return $this->datavisibility;
	}

	/**
	 * Get the [work_title] column value.
	 * 
	 * @return     string
	 */
	public function getWorkTitle()
	{
		return $this->work_title;
	}

	/**
	 * Get the [last_name] column value.
	 * 
	 * @return     string
	 */
	public function getLastName()
	{
		return $this->last_name;
	}

	/**
	 * Get the [zipcode] column value.
	 * 
	 * @return     string
	 */
	public function getZipcode()
	{
		return $this->zipcode;
	}

	/**
	 * Get the [lab_affiliation] column value.
	 * 
	 * @return     string
	 */
	public function getLabAffiliation()
	{
		return $this->lab_affiliation;
	}

	/**
	 * Get the [addr1] column value.
	 * 
	 * @return     string
	 */
	public function getAddr1()
	{
		return $this->addr1;
	}

	/**
	 * Get the [addr2] column value.
	 * 
	 * @return     string
	 */
	public function getAddr2()
	{
		return $this->addr2;
	}

	/**
	 * Get the [email] column value.
	 * 
	 * @return     string
	 */
	public function getEmail()
	{
		return $this->email;
	}

	/**
	 * Get the [logdata] column value.
	 * 
	 * @return     int
	 */
	public function getLogdata()
	{
		return $this->logdata;
	}

	/**
	 * Get the [rootfolder_id] column value.
	 * 
	 * @return     int
	 */
	public function getRootfolderId()
	{
		return $this->rootfolder_id;
	}

	/**
	 * Set the value of [id] column.
	 * 
	 * @param      int $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setId($v)
	{
		if ($v !== null) {
			$v = (int) $v;
		}

		if ($this->id !== $v) {
			$this->id = $v;
			$this->modifiedColumns[] = RegistrationPeer::ID;
		}

		return $this;
	} // setId()

	/**
	 * Set the value of [phone] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setPhone($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->phone !== $v) {
			$this->phone = $v;
			$this->modifiedColumns[] = RegistrationPeer::PHONE;
		}

		return $this;
	} // setPhone()

	/**
	 * Set the value of [interests] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setInterests($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->interests !== $v) {
			$this->interests = $v;
			$this->modifiedColumns[] = RegistrationPeer::INTERESTS;
		}

		return $this;
	} // setInterests()

	/**
	 * Set the value of [state] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setState($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->state !== $v) {
			$this->state = $v;
			$this->modifiedColumns[] = RegistrationPeer::STATE;
		}

		return $this;
	} // setState()

	/**
	 * Set the value of [online_status] column.
	 * 
	 * @param      int $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setOnlineStatus($v)
	{
		if ($v !== null) {
			$v = (int) $v;
		}

		if ($this->online_status !== $v) {
			$this->online_status = $v;
			$this->modifiedColumns[] = RegistrationPeer::ONLINE_STATUS;
		}

		return $this;
	} // setOnlineStatus()

	/**
	 * Set the value of [password] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setPassword($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->password !== $v) {
			$this->password = $v;
			$this->modifiedColumns[] = RegistrationPeer::PASSWORD;
		}

		return $this;
	} // setPassword()

	/**
	 * Set the value of [city] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setCity($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->city !== $v) {
			$this->city = $v;
			$this->modifiedColumns[] = RegistrationPeer::CITY;
		}

		return $this;
	} // setCity()

	/**
	 * Set the value of [username] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setUsername($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->username !== $v) {
			$this->username = $v;
			$this->modifiedColumns[] = RegistrationPeer::USERNAME;
		}

		return $this;
	} // setUsername()

	/**
	 * Sets the value of [createdat] column to a normalized version of the date/time value specified.
	 * 
	 * @param      mixed $v string, integer (timestamp), or DateTime value.  Empty string will
	 *						be treated as NULL for temporal objects.
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setCreatedat($v)
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

		if ( $this->createdat !== null || $dt !== null ) {
			// (nested ifs are a little easier to read in this case)

			$currNorm = ($this->createdat !== null && $tmpDt = new DateTime($this->createdat)) ? $tmpDt->format('Y-m-d H:i:s') : null;
			$newNorm = ($dt !== null) ? $dt->format('Y-m-d H:i:s') : null;

			if ( ($currNorm !== $newNorm) // normalized values don't match 
					)
			{
				$this->createdat = ($dt ? $dt->format('Y-m-d H:i:s') : null);
				$this->modifiedColumns[] = RegistrationPeer::CREATEDAT;
			}
		} // if either are not null

		return $this;
	} // setCreatedat()

	/**
	 * Set the value of [first_name] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setFirstName($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->first_name !== $v) {
			$this->first_name = $v;
			$this->modifiedColumns[] = RegistrationPeer::FIRST_NAME;
		}

		return $this;
	} // setFirstName()

	/**
	 * Set the value of [datavisibility] column.
	 * 
	 * @param      int $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setDatavisibility($v)
	{
		if ($v !== null) {
			$v = (int) $v;
		}

		if ($this->datavisibility !== $v) {
			$this->datavisibility = $v;
			$this->modifiedColumns[] = RegistrationPeer::DATAVISIBILITY;
		}

		return $this;
	} // setDatavisibility()

	/**
	 * Set the value of [work_title] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setWorkTitle($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->work_title !== $v) {
			$this->work_title = $v;
			$this->modifiedColumns[] = RegistrationPeer::WORK_TITLE;
		}

		return $this;
	} // setWorkTitle()

	/**
	 * Set the value of [last_name] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setLastName($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->last_name !== $v) {
			$this->last_name = $v;
			$this->modifiedColumns[] = RegistrationPeer::LAST_NAME;
		}

		return $this;
	} // setLastName()

	/**
	 * Set the value of [zipcode] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setZipcode($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->zipcode !== $v) {
			$this->zipcode = $v;
			$this->modifiedColumns[] = RegistrationPeer::ZIPCODE;
		}

		return $this;
	} // setZipcode()

	/**
	 * Set the value of [lab_affiliation] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setLabAffiliation($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->lab_affiliation !== $v) {
			$this->lab_affiliation = $v;
			$this->modifiedColumns[] = RegistrationPeer::LAB_AFFILIATION;
		}

		return $this;
	} // setLabAffiliation()

	/**
	 * Set the value of [addr1] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setAddr1($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->addr1 !== $v) {
			$this->addr1 = $v;
			$this->modifiedColumns[] = RegistrationPeer::ADDR1;
		}

		return $this;
	} // setAddr1()

	/**
	 * Set the value of [addr2] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setAddr2($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->addr2 !== $v) {
			$this->addr2 = $v;
			$this->modifiedColumns[] = RegistrationPeer::ADDR2;
		}

		return $this;
	} // setAddr2()

	/**
	 * Set the value of [email] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setEmail($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->email !== $v) {
			$this->email = $v;
			$this->modifiedColumns[] = RegistrationPeer::EMAIL;
		}

		return $this;
	} // setEmail()

	/**
	 * Set the value of [logdata] column.
	 * 
	 * @param      int $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setLogdata($v)
	{
		if ($v !== null) {
			$v = (int) $v;
		}

		if ($this->logdata !== $v) {
			$this->logdata = $v;
			$this->modifiedColumns[] = RegistrationPeer::LOGDATA;
		}

		return $this;
	} // setLogdata()

	/**
	 * Set the value of [rootfolder_id] column.
	 * 
	 * @param      int $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setRootfolderId($v)
	{
		if ($v !== null) {
			$v = (int) $v;
		}

		if ($this->rootfolder_id !== $v) {
			$this->rootfolder_id = $v;
			$this->modifiedColumns[] = RegistrationPeer::ROOTFOLDER_ID;
		}

		return $this;
	} // setRootfolderId()

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

			$this->id = ($row[$startcol + 0] !== null) ? (int) $row[$startcol + 0] : null;
			$this->phone = ($row[$startcol + 1] !== null) ? (string) $row[$startcol + 1] : null;
			$this->interests = ($row[$startcol + 2] !== null) ? (string) $row[$startcol + 2] : null;
			$this->state = ($row[$startcol + 3] !== null) ? (string) $row[$startcol + 3] : null;
			$this->online_status = ($row[$startcol + 4] !== null) ? (int) $row[$startcol + 4] : null;
			$this->password = ($row[$startcol + 5] !== null) ? (string) $row[$startcol + 5] : null;
			$this->city = ($row[$startcol + 6] !== null) ? (string) $row[$startcol + 6] : null;
			$this->username = ($row[$startcol + 7] !== null) ? (string) $row[$startcol + 7] : null;
			$this->createdat = ($row[$startcol + 8] !== null) ? (string) $row[$startcol + 8] : null;
			$this->first_name = ($row[$startcol + 9] !== null) ? (string) $row[$startcol + 9] : null;
			$this->datavisibility = ($row[$startcol + 10] !== null) ? (int) $row[$startcol + 10] : null;
			$this->work_title = ($row[$startcol + 11] !== null) ? (string) $row[$startcol + 11] : null;
			$this->last_name = ($row[$startcol + 12] !== null) ? (string) $row[$startcol + 12] : null;
			$this->zipcode = ($row[$startcol + 13] !== null) ? (string) $row[$startcol + 13] : null;
			$this->lab_affiliation = ($row[$startcol + 14] !== null) ? (string) $row[$startcol + 14] : null;
			$this->addr1 = ($row[$startcol + 15] !== null) ? (string) $row[$startcol + 15] : null;
			$this->addr2 = ($row[$startcol + 16] !== null) ? (string) $row[$startcol + 16] : null;
			$this->email = ($row[$startcol + 17] !== null) ? (string) $row[$startcol + 17] : null;
			$this->logdata = ($row[$startcol + 18] !== null) ? (int) $row[$startcol + 18] : null;
			$this->rootfolder_id = ($row[$startcol + 19] !== null) ? (int) $row[$startcol + 19] : null;
			$this->resetModified();

			$this->setNew(false);

			if ($rehydrate) {
				$this->ensureConsistency();
			}

			return $startcol + 20; // 20 = RegistrationPeer::NUM_COLUMNS - RegistrationPeer::NUM_LAZY_LOAD_COLUMNS).

		} catch (Exception $e) {
			throw new PropelException("Error populating Registration object", $e);
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
			$con = Propel::getConnection(RegistrationPeer::DATABASE_NAME, Propel::CONNECTION_READ);
		}

		// We don't need to alter the object instance pool; we're just modifying this instance
		// already in the pool.

		$stmt = RegistrationPeer::doSelectStmt($this->buildPkeyCriteria(), $con);
		$row = $stmt->fetch(PDO::FETCH_NUM);
		$stmt->closeCursor();
		if (!$row) {
			throw new PropelException('Cannot find matching row in the database to reload object values.');
		}
		$this->hydrate($row, 0, true); // rehydrate

		if ($deep) {  // also de-associate any related objects?

			$this->collToolcommentss = null;

			$this->collToolratingss = null;

			$this->collWorkflowcommentss = null;

			$this->collWorkflowratingss = null;

			$this->collAnnotations = null;

			$this->collHistorys = null;

			$this->collWorkspaces = null;

			$this->collWorkspaceUsers = null;

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
			$con = Propel::getConnection(RegistrationPeer::DATABASE_NAME, Propel::CONNECTION_WRITE);
		}
		
		$con->beginTransaction();
		try {
			$ret = $this->preDelete($con);
			if ($ret) {
				RegistrationQuery::create()
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
			$con = Propel::getConnection(RegistrationPeer::DATABASE_NAME, Propel::CONNECTION_WRITE);
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
				RegistrationPeer::addInstanceToPool($this);
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
				$this->modifiedColumns[] = RegistrationPeer::ID;
			}

			// If this object has been modified, then save it to the database.
			if ($this->isModified()) {
				if ($this->isNew()) {
					$criteria = $this->buildCriteria();
					if ($criteria->keyContainsValue(RegistrationPeer::ID) ) {
						throw new PropelException('Cannot insert a value for auto-increment primary key ('.RegistrationPeer::ID.')');
					}

					// remove pkey col since this table uses auto-increment and passing a null value for it is not valid 
					$criteria->remove(RegistrationPeer::ID);

					$pk = BasePeer::doInsert($criteria, $con);
					$affectedRows = 1;
					$this->setId($pk);  //[IMV] update autoincrement primary key
					$this->setNew(false);
				} else {
					$affectedRows = RegistrationPeer::doUpdate($this, $con);
				}

				$this->resetModified(); // [HL] After being saved an object is no longer 'modified'
			}

			if ($this->collToolcommentss !== null) {
				foreach ($this->collToolcommentss as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collToolratingss !== null) {
				foreach ($this->collToolratingss as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collWorkflowcommentss !== null) {
				foreach ($this->collWorkflowcommentss as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collWorkflowratingss !== null) {
				foreach ($this->collWorkflowratingss as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collAnnotations !== null) {
				foreach ($this->collAnnotations as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collHistorys !== null) {
				foreach ($this->collHistorys as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collWorkspaces !== null) {
				foreach ($this->collWorkspaces as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collWorkspaceUsers !== null) {
				foreach ($this->collWorkspaceUsers as $referrerFK) {
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


			if (($retval = RegistrationPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}


				if ($this->collToolcommentss !== null) {
					foreach ($this->collToolcommentss as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collToolratingss !== null) {
					foreach ($this->collToolratingss as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collWorkflowcommentss !== null) {
					foreach ($this->collWorkflowcommentss as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collWorkflowratingss !== null) {
					foreach ($this->collWorkflowratingss as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collAnnotations !== null) {
					foreach ($this->collAnnotations as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collHistorys !== null) {
					foreach ($this->collHistorys as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collWorkspaces !== null) {
					foreach ($this->collWorkspaces as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collWorkspaceUsers !== null) {
					foreach ($this->collWorkspaceUsers as $referrerFK) {
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
		$pos = RegistrationPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
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
				return $this->getId();
				break;
			case 1:
				return $this->getPhone();
				break;
			case 2:
				return $this->getInterests();
				break;
			case 3:
				return $this->getState();
				break;
			case 4:
				return $this->getOnlineStatus();
				break;
			case 5:
				return $this->getPassword();
				break;
			case 6:
				return $this->getCity();
				break;
			case 7:
				return $this->getUsername();
				break;
			case 8:
				return $this->getCreatedat();
				break;
			case 9:
				return $this->getFirstName();
				break;
			case 10:
				return $this->getDatavisibility();
				break;
			case 11:
				return $this->getWorkTitle();
				break;
			case 12:
				return $this->getLastName();
				break;
			case 13:
				return $this->getZipcode();
				break;
			case 14:
				return $this->getLabAffiliation();
				break;
			case 15:
				return $this->getAddr1();
				break;
			case 16:
				return $this->getAddr2();
				break;
			case 17:
				return $this->getEmail();
				break;
			case 18:
				return $this->getLogdata();
				break;
			case 19:
				return $this->getRootfolderId();
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
		$keys = RegistrationPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getId(),
			$keys[1] => $this->getPhone(),
			$keys[2] => $this->getInterests(),
			$keys[3] => $this->getState(),
			$keys[4] => $this->getOnlineStatus(),
			$keys[5] => $this->getPassword(),
			$keys[6] => $this->getCity(),
			$keys[7] => $this->getUsername(),
			$keys[8] => $this->getCreatedat(),
			$keys[9] => $this->getFirstName(),
			$keys[10] => $this->getDatavisibility(),
			$keys[11] => $this->getWorkTitle(),
			$keys[12] => $this->getLastName(),
			$keys[13] => $this->getZipcode(),
			$keys[14] => $this->getLabAffiliation(),
			$keys[15] => $this->getAddr1(),
			$keys[16] => $this->getAddr2(),
			$keys[17] => $this->getEmail(),
			$keys[18] => $this->getLogdata(),
			$keys[19] => $this->getRootfolderId(),
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
		$pos = RegistrationPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
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
				$this->setId($value);
				break;
			case 1:
				$this->setPhone($value);
				break;
			case 2:
				$this->setInterests($value);
				break;
			case 3:
				$this->setState($value);
				break;
			case 4:
				$this->setOnlineStatus($value);
				break;
			case 5:
				$this->setPassword($value);
				break;
			case 6:
				$this->setCity($value);
				break;
			case 7:
				$this->setUsername($value);
				break;
			case 8:
				$this->setCreatedat($value);
				break;
			case 9:
				$this->setFirstName($value);
				break;
			case 10:
				$this->setDatavisibility($value);
				break;
			case 11:
				$this->setWorkTitle($value);
				break;
			case 12:
				$this->setLastName($value);
				break;
			case 13:
				$this->setZipcode($value);
				break;
			case 14:
				$this->setLabAffiliation($value);
				break;
			case 15:
				$this->setAddr1($value);
				break;
			case 16:
				$this->setAddr2($value);
				break;
			case 17:
				$this->setEmail($value);
				break;
			case 18:
				$this->setLogdata($value);
				break;
			case 19:
				$this->setRootfolderId($value);
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
		$keys = RegistrationPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setId($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setPhone($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setInterests($arr[$keys[2]]);
		if (array_key_exists($keys[3], $arr)) $this->setState($arr[$keys[3]]);
		if (array_key_exists($keys[4], $arr)) $this->setOnlineStatus($arr[$keys[4]]);
		if (array_key_exists($keys[5], $arr)) $this->setPassword($arr[$keys[5]]);
		if (array_key_exists($keys[6], $arr)) $this->setCity($arr[$keys[6]]);
		if (array_key_exists($keys[7], $arr)) $this->setUsername($arr[$keys[7]]);
		if (array_key_exists($keys[8], $arr)) $this->setCreatedat($arr[$keys[8]]);
		if (array_key_exists($keys[9], $arr)) $this->setFirstName($arr[$keys[9]]);
		if (array_key_exists($keys[10], $arr)) $this->setDatavisibility($arr[$keys[10]]);
		if (array_key_exists($keys[11], $arr)) $this->setWorkTitle($arr[$keys[11]]);
		if (array_key_exists($keys[12], $arr)) $this->setLastName($arr[$keys[12]]);
		if (array_key_exists($keys[13], $arr)) $this->setZipcode($arr[$keys[13]]);
		if (array_key_exists($keys[14], $arr)) $this->setLabAffiliation($arr[$keys[14]]);
		if (array_key_exists($keys[15], $arr)) $this->setAddr1($arr[$keys[15]]);
		if (array_key_exists($keys[16], $arr)) $this->setAddr2($arr[$keys[16]]);
		if (array_key_exists($keys[17], $arr)) $this->setEmail($arr[$keys[17]]);
		if (array_key_exists($keys[18], $arr)) $this->setLogdata($arr[$keys[18]]);
		if (array_key_exists($keys[19], $arr)) $this->setRootfolderId($arr[$keys[19]]);
	}

	/**
	 * Build a Criteria object containing the values of all modified columns in this object.
	 *
	 * @return     Criteria The Criteria object containing all modified values.
	 */
	public function buildCriteria()
	{
		$criteria = new Criteria(RegistrationPeer::DATABASE_NAME);

		if ($this->isColumnModified(RegistrationPeer::ID)) $criteria->add(RegistrationPeer::ID, $this->id);
		if ($this->isColumnModified(RegistrationPeer::PHONE)) $criteria->add(RegistrationPeer::PHONE, $this->phone);
		if ($this->isColumnModified(RegistrationPeer::INTERESTS)) $criteria->add(RegistrationPeer::INTERESTS, $this->interests);
		if ($this->isColumnModified(RegistrationPeer::STATE)) $criteria->add(RegistrationPeer::STATE, $this->state);
		if ($this->isColumnModified(RegistrationPeer::ONLINE_STATUS)) $criteria->add(RegistrationPeer::ONLINE_STATUS, $this->online_status);
		if ($this->isColumnModified(RegistrationPeer::PASSWORD)) $criteria->add(RegistrationPeer::PASSWORD, $this->password);
		if ($this->isColumnModified(RegistrationPeer::CITY)) $criteria->add(RegistrationPeer::CITY, $this->city);
		if ($this->isColumnModified(RegistrationPeer::USERNAME)) $criteria->add(RegistrationPeer::USERNAME, $this->username);
		if ($this->isColumnModified(RegistrationPeer::CREATEDAT)) $criteria->add(RegistrationPeer::CREATEDAT, $this->createdat);
		if ($this->isColumnModified(RegistrationPeer::FIRST_NAME)) $criteria->add(RegistrationPeer::FIRST_NAME, $this->first_name);
		if ($this->isColumnModified(RegistrationPeer::DATAVISIBILITY)) $criteria->add(RegistrationPeer::DATAVISIBILITY, $this->datavisibility);
		if ($this->isColumnModified(RegistrationPeer::WORK_TITLE)) $criteria->add(RegistrationPeer::WORK_TITLE, $this->work_title);
		if ($this->isColumnModified(RegistrationPeer::LAST_NAME)) $criteria->add(RegistrationPeer::LAST_NAME, $this->last_name);
		if ($this->isColumnModified(RegistrationPeer::ZIPCODE)) $criteria->add(RegistrationPeer::ZIPCODE, $this->zipcode);
		if ($this->isColumnModified(RegistrationPeer::LAB_AFFILIATION)) $criteria->add(RegistrationPeer::LAB_AFFILIATION, $this->lab_affiliation);
		if ($this->isColumnModified(RegistrationPeer::ADDR1)) $criteria->add(RegistrationPeer::ADDR1, $this->addr1);
		if ($this->isColumnModified(RegistrationPeer::ADDR2)) $criteria->add(RegistrationPeer::ADDR2, $this->addr2);
		if ($this->isColumnModified(RegistrationPeer::EMAIL)) $criteria->add(RegistrationPeer::EMAIL, $this->email);
		if ($this->isColumnModified(RegistrationPeer::LOGDATA)) $criteria->add(RegistrationPeer::LOGDATA, $this->logdata);
		if ($this->isColumnModified(RegistrationPeer::ROOTFOLDER_ID)) $criteria->add(RegistrationPeer::ROOTFOLDER_ID, $this->rootfolder_id);

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
		$criteria = new Criteria(RegistrationPeer::DATABASE_NAME);
		$criteria->add(RegistrationPeer::ID, $this->id);

		return $criteria;
	}

	/**
	 * Returns the primary key for this object (row).
	 * @return     int
	 */
	public function getPrimaryKey()
	{
		return $this->getId();
	}

	/**
	 * Generic method to set the primary key (id column).
	 *
	 * @param      int $key Primary key.
	 * @return     void
	 */
	public function setPrimaryKey($key)
	{
		$this->setId($key);
	}

	/**
	 * Returns true if the primary key for this object is null.
	 * @return     boolean
	 */
	public function isPrimaryKeyNull()
	{
		return null === $this->getId();
	}

	/**
	 * Sets contents of passed object to values from current object.
	 *
	 * If desired, this method can also make copies of all associated (fkey referrers)
	 * objects.
	 *
	 * @param      object $copyObj An object of Registration (or compatible) type.
	 * @param      boolean $deepCopy Whether to also copy all rows that refer (by fkey) to the current row.
	 * @throws     PropelException
	 */
	public function copyInto($copyObj, $deepCopy = false)
	{
		$copyObj->setPhone($this->phone);
		$copyObj->setInterests($this->interests);
		$copyObj->setState($this->state);
		$copyObj->setOnlineStatus($this->online_status);
		$copyObj->setPassword($this->password);
		$copyObj->setCity($this->city);
		$copyObj->setUsername($this->username);
		$copyObj->setCreatedat($this->createdat);
		$copyObj->setFirstName($this->first_name);
		$copyObj->setDatavisibility($this->datavisibility);
		$copyObj->setWorkTitle($this->work_title);
		$copyObj->setLastName($this->last_name);
		$copyObj->setZipcode($this->zipcode);
		$copyObj->setLabAffiliation($this->lab_affiliation);
		$copyObj->setAddr1($this->addr1);
		$copyObj->setAddr2($this->addr2);
		$copyObj->setEmail($this->email);
		$copyObj->setLogdata($this->logdata);
		$copyObj->setRootfolderId($this->rootfolder_id);

		if ($deepCopy) {
			// important: temporarily setNew(false) because this affects the behavior of
			// the getter/setter methods for fkey referrer objects.
			$copyObj->setNew(false);

			foreach ($this->getToolcommentss() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addToolcomments($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getToolratingss() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addToolratings($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getWorkflowcommentss() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addWorkflowcomments($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getWorkflowratingss() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addWorkflowratings($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getAnnotations() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addAnnotation($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getHistorys() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addHistory($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getWorkspaces() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addWorkspace($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getWorkspaceUsers() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addWorkspaceUser($relObj->copy($deepCopy));
				}
			}

		} // if ($deepCopy)


		$copyObj->setNew(true);
		$copyObj->setId(NULL); // this is a auto-increment column, so set to default value
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
	 * @return     Registration Clone of current object.
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
	 * @return     RegistrationPeer
	 */
	public function getPeer()
	{
		if (self::$peer === null) {
			self::$peer = new RegistrationPeer();
		}
		return self::$peer;
	}

	/**
	 * Clears out the collToolcommentss collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addToolcommentss()
	 */
	public function clearToolcommentss()
	{
		$this->collToolcommentss = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collToolcommentss collection.
	 *
	 * By default this just sets the collToolcommentss collection to an empty array (like clearcollToolcommentss());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initToolcommentss()
	{
		$this->collToolcommentss = new PropelObjectCollection();
		$this->collToolcommentss->setModel('Toolcomments');
	}

	/**
	 * Gets an array of Toolcomments objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array Toolcomments[] List of Toolcomments objects
	 * @throws     PropelException
	 */
	public function getToolcommentss($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collToolcommentss || null !== $criteria) {
			if ($this->isNew() && null === $this->collToolcommentss) {
				// return empty collection
				$this->initToolcommentss();
			} else {
				$collToolcommentss = ToolcommentsQuery::create(null, $criteria)
					->filterByRegistration($this)
					->find($con);
				if (null !== $criteria) {
					return $collToolcommentss;
				}
				$this->collToolcommentss = $collToolcommentss;
			}
		}
		return $this->collToolcommentss;
	}

	/**
	 * Returns the number of related Toolcomments objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related Toolcomments objects.
	 * @throws     PropelException
	 */
	public function countToolcommentss(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collToolcommentss || null !== $criteria) {
			if ($this->isNew() && null === $this->collToolcommentss) {
				return 0;
			} else {
				$query = ToolcommentsQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistration($this)
					->count($con);
			}
		} else {
			return count($this->collToolcommentss);
		}
	}

	/**
	 * Method called to associate a Toolcomments object to this object
	 * through the Toolcomments foreign key attribute.
	 *
	 * @param      Toolcomments $l Toolcomments
	 * @return     void
	 * @throws     PropelException
	 */
	public function addToolcomments(Toolcomments $l)
	{
		if ($this->collToolcommentss === null) {
			$this->initToolcommentss();
		}
		if (!$this->collToolcommentss->contains($l)) { // only add it if the **same** object is not already associated
			$this->collToolcommentss[]= $l;
			$l->setRegistration($this);
		}
	}

	/**
	 * Clears out the collToolratingss collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addToolratingss()
	 */
	public function clearToolratingss()
	{
		$this->collToolratingss = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collToolratingss collection.
	 *
	 * By default this just sets the collToolratingss collection to an empty array (like clearcollToolratingss());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initToolratingss()
	{
		$this->collToolratingss = new PropelObjectCollection();
		$this->collToolratingss->setModel('Toolratings');
	}

	/**
	 * Gets an array of Toolratings objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array Toolratings[] List of Toolratings objects
	 * @throws     PropelException
	 */
	public function getToolratingss($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collToolratingss || null !== $criteria) {
			if ($this->isNew() && null === $this->collToolratingss) {
				// return empty collection
				$this->initToolratingss();
			} else {
				$collToolratingss = ToolratingsQuery::create(null, $criteria)
					->filterByRegistration($this)
					->find($con);
				if (null !== $criteria) {
					return $collToolratingss;
				}
				$this->collToolratingss = $collToolratingss;
			}
		}
		return $this->collToolratingss;
	}

	/**
	 * Returns the number of related Toolratings objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related Toolratings objects.
	 * @throws     PropelException
	 */
	public function countToolratingss(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collToolratingss || null !== $criteria) {
			if ($this->isNew() && null === $this->collToolratingss) {
				return 0;
			} else {
				$query = ToolratingsQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistration($this)
					->count($con);
			}
		} else {
			return count($this->collToolratingss);
		}
	}

	/**
	 * Method called to associate a Toolratings object to this object
	 * through the Toolratings foreign key attribute.
	 *
	 * @param      Toolratings $l Toolratings
	 * @return     void
	 * @throws     PropelException
	 */
	public function addToolratings(Toolratings $l)
	{
		if ($this->collToolratingss === null) {
			$this->initToolratingss();
		}
		if (!$this->collToolratingss->contains($l)) { // only add it if the **same** object is not already associated
			$this->collToolratingss[]= $l;
			$l->setRegistration($this);
		}
	}

	/**
	 * Clears out the collWorkflowcommentss collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addWorkflowcommentss()
	 */
	public function clearWorkflowcommentss()
	{
		$this->collWorkflowcommentss = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collWorkflowcommentss collection.
	 *
	 * By default this just sets the collWorkflowcommentss collection to an empty array (like clearcollWorkflowcommentss());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initWorkflowcommentss()
	{
		$this->collWorkflowcommentss = new PropelObjectCollection();
		$this->collWorkflowcommentss->setModel('Workflowcomments');
	}

	/**
	 * Gets an array of Workflowcomments objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array Workflowcomments[] List of Workflowcomments objects
	 * @throws     PropelException
	 */
	public function getWorkflowcommentss($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collWorkflowcommentss || null !== $criteria) {
			if ($this->isNew() && null === $this->collWorkflowcommentss) {
				// return empty collection
				$this->initWorkflowcommentss();
			} else {
				$collWorkflowcommentss = WorkflowcommentsQuery::create(null, $criteria)
					->filterByRegistration($this)
					->find($con);
				if (null !== $criteria) {
					return $collWorkflowcommentss;
				}
				$this->collWorkflowcommentss = $collWorkflowcommentss;
			}
		}
		return $this->collWorkflowcommentss;
	}

	/**
	 * Returns the number of related Workflowcomments objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related Workflowcomments objects.
	 * @throws     PropelException
	 */
	public function countWorkflowcommentss(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collWorkflowcommentss || null !== $criteria) {
			if ($this->isNew() && null === $this->collWorkflowcommentss) {
				return 0;
			} else {
				$query = WorkflowcommentsQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistration($this)
					->count($con);
			}
		} else {
			return count($this->collWorkflowcommentss);
		}
	}

	/**
	 * Method called to associate a Workflowcomments object to this object
	 * through the Workflowcomments foreign key attribute.
	 *
	 * @param      Workflowcomments $l Workflowcomments
	 * @return     void
	 * @throws     PropelException
	 */
	public function addWorkflowcomments(Workflowcomments $l)
	{
		if ($this->collWorkflowcommentss === null) {
			$this->initWorkflowcommentss();
		}
		if (!$this->collWorkflowcommentss->contains($l)) { // only add it if the **same** object is not already associated
			$this->collWorkflowcommentss[]= $l;
			$l->setRegistration($this);
		}
	}

	/**
	 * Clears out the collWorkflowratingss collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addWorkflowratingss()
	 */
	public function clearWorkflowratingss()
	{
		$this->collWorkflowratingss = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collWorkflowratingss collection.
	 *
	 * By default this just sets the collWorkflowratingss collection to an empty array (like clearcollWorkflowratingss());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initWorkflowratingss()
	{
		$this->collWorkflowratingss = new PropelObjectCollection();
		$this->collWorkflowratingss->setModel('Workflowratings');
	}

	/**
	 * Gets an array of Workflowratings objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array Workflowratings[] List of Workflowratings objects
	 * @throws     PropelException
	 */
	public function getWorkflowratingss($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collWorkflowratingss || null !== $criteria) {
			if ($this->isNew() && null === $this->collWorkflowratingss) {
				// return empty collection
				$this->initWorkflowratingss();
			} else {
				$collWorkflowratingss = WorkflowratingsQuery::create(null, $criteria)
					->filterByRegistration($this)
					->find($con);
				if (null !== $criteria) {
					return $collWorkflowratingss;
				}
				$this->collWorkflowratingss = $collWorkflowratingss;
			}
		}
		return $this->collWorkflowratingss;
	}

	/**
	 * Returns the number of related Workflowratings objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related Workflowratings objects.
	 * @throws     PropelException
	 */
	public function countWorkflowratingss(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collWorkflowratingss || null !== $criteria) {
			if ($this->isNew() && null === $this->collWorkflowratingss) {
				return 0;
			} else {
				$query = WorkflowratingsQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistration($this)
					->count($con);
			}
		} else {
			return count($this->collWorkflowratingss);
		}
	}

	/**
	 * Method called to associate a Workflowratings object to this object
	 * through the Workflowratings foreign key attribute.
	 *
	 * @param      Workflowratings $l Workflowratings
	 * @return     void
	 * @throws     PropelException
	 */
	public function addWorkflowratings(Workflowratings $l)
	{
		if ($this->collWorkflowratingss === null) {
			$this->initWorkflowratingss();
		}
		if (!$this->collWorkflowratingss->contains($l)) { // only add it if the **same** object is not already associated
			$this->collWorkflowratingss[]= $l;
			$l->setRegistration($this);
		}
	}

	/**
	 * Clears out the collAnnotations collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addAnnotations()
	 */
	public function clearAnnotations()
	{
		$this->collAnnotations = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collAnnotations collection.
	 *
	 * By default this just sets the collAnnotations collection to an empty array (like clearcollAnnotations());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initAnnotations()
	{
		$this->collAnnotations = new PropelObjectCollection();
		$this->collAnnotations->setModel('Annotation');
	}

	/**
	 * Gets an array of Annotation objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array Annotation[] List of Annotation objects
	 * @throws     PropelException
	 */
	public function getAnnotations($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collAnnotations || null !== $criteria) {
			if ($this->isNew() && null === $this->collAnnotations) {
				// return empty collection
				$this->initAnnotations();
			} else {
				$collAnnotations = AnnotationQuery::create(null, $criteria)
					->filterByRegistration($this)
					->find($con);
				if (null !== $criteria) {
					return $collAnnotations;
				}
				$this->collAnnotations = $collAnnotations;
			}
		}
		return $this->collAnnotations;
	}

	/**
	 * Returns the number of related Annotation objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related Annotation objects.
	 * @throws     PropelException
	 */
	public function countAnnotations(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collAnnotations || null !== $criteria) {
			if ($this->isNew() && null === $this->collAnnotations) {
				return 0;
			} else {
				$query = AnnotationQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistration($this)
					->count($con);
			}
		} else {
			return count($this->collAnnotations);
		}
	}

	/**
	 * Method called to associate a Annotation object to this object
	 * through the Annotation foreign key attribute.
	 *
	 * @param      Annotation $l Annotation
	 * @return     void
	 * @throws     PropelException
	 */
	public function addAnnotation(Annotation $l)
	{
		if ($this->collAnnotations === null) {
			$this->initAnnotations();
		}
		if (!$this->collAnnotations->contains($l)) { // only add it if the **same** object is not already associated
			$this->collAnnotations[]= $l;
			$l->setRegistration($this);
		}
	}


	/**
	 * If this collection has already been initialized with
	 * an identical criteria, it returns the collection.
	 * Otherwise if this Registration is new, it will return
	 * an empty collection; or if this Registration has previously
	 * been saved, it will retrieve related Annotations from storage.
	 *
	 * This method is protected by default in order to keep the public
	 * api reasonable.  You can provide public methods for those you
	 * actually need in Registration.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @param      string $join_behavior optional join type to use (defaults to Criteria::LEFT_JOIN)
	 * @return     PropelCollection|array Annotation[] List of Annotation objects
	 */
	public function getAnnotationsJoinWorkspace($criteria = null, $con = null, $join_behavior = Criteria::LEFT_JOIN)
	{
		$query = AnnotationQuery::create(null, $criteria);
		$query->joinWith('Workspace', $join_behavior);

		return $this->getAnnotations($query, $con);
	}

	/**
	 * Clears out the collHistorys collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addHistorys()
	 */
	public function clearHistorys()
	{
		$this->collHistorys = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collHistorys collection.
	 *
	 * By default this just sets the collHistorys collection to an empty array (like clearcollHistorys());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initHistorys()
	{
		$this->collHistorys = new PropelObjectCollection();
		$this->collHistorys->setModel('History');
	}

	/**
	 * Gets an array of History objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array History[] List of History objects
	 * @throws     PropelException
	 */
	public function getHistorys($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collHistorys || null !== $criteria) {
			if ($this->isNew() && null === $this->collHistorys) {
				// return empty collection
				$this->initHistorys();
			} else {
				$collHistorys = HistoryQuery::create(null, $criteria)
					->filterByRegistration($this)
					->find($con);
				if (null !== $criteria) {
					return $collHistorys;
				}
				$this->collHistorys = $collHistorys;
			}
		}
		return $this->collHistorys;
	}

	/**
	 * Returns the number of related History objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related History objects.
	 * @throws     PropelException
	 */
	public function countHistorys(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collHistorys || null !== $criteria) {
			if ($this->isNew() && null === $this->collHistorys) {
				return 0;
			} else {
				$query = HistoryQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistration($this)
					->count($con);
			}
		} else {
			return count($this->collHistorys);
		}
	}

	/**
	 * Method called to associate a History object to this object
	 * through the History foreign key attribute.
	 *
	 * @param      History $l History
	 * @return     void
	 * @throws     PropelException
	 */
	public function addHistory(History $l)
	{
		if ($this->collHistorys === null) {
			$this->initHistorys();
		}
		if (!$this->collHistorys->contains($l)) { // only add it if the **same** object is not already associated
			$this->collHistorys[]= $l;
			$l->setRegistration($this);
		}
	}


	/**
	 * If this collection has already been initialized with
	 * an identical criteria, it returns the collection.
	 * Otherwise if this Registration is new, it will return
	 * an empty collection; or if this Registration has previously
	 * been saved, it will retrieve related Historys from storage.
	 *
	 * This method is protected by default in order to keep the public
	 * api reasonable.  You can provide public methods for those you
	 * actually need in Registration.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @param      string $join_behavior optional join type to use (defaults to Criteria::LEFT_JOIN)
	 * @return     PropelCollection|array History[] List of History objects
	 */
	public function getHistorysJoinWorkspace($criteria = null, $con = null, $join_behavior = Criteria::LEFT_JOIN)
	{
		$query = HistoryQuery::create(null, $criteria);
		$query->joinWith('Workspace', $join_behavior);

		return $this->getHistorys($query, $con);
	}

	/**
	 * Clears out the collWorkspaces collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addWorkspaces()
	 */
	public function clearWorkspaces()
	{
		$this->collWorkspaces = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collWorkspaces collection.
	 *
	 * By default this just sets the collWorkspaces collection to an empty array (like clearcollWorkspaces());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initWorkspaces()
	{
		$this->collWorkspaces = new PropelObjectCollection();
		$this->collWorkspaces->setModel('Workspace');
	}

	/**
	 * Gets an array of Workspace objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array Workspace[] List of Workspace objects
	 * @throws     PropelException
	 */
	public function getWorkspaces($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collWorkspaces || null !== $criteria) {
			if ($this->isNew() && null === $this->collWorkspaces) {
				// return empty collection
				$this->initWorkspaces();
			} else {
				$collWorkspaces = WorkspaceQuery::create(null, $criteria)
					->filterByRegistration($this)
					->find($con);
				if (null !== $criteria) {
					return $collWorkspaces;
				}
				$this->collWorkspaces = $collWorkspaces;
			}
		}
		return $this->collWorkspaces;
	}

	/**
	 * Returns the number of related Workspace objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related Workspace objects.
	 * @throws     PropelException
	 */
	public function countWorkspaces(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collWorkspaces || null !== $criteria) {
			if ($this->isNew() && null === $this->collWorkspaces) {
				return 0;
			} else {
				$query = WorkspaceQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistration($this)
					->count($con);
			}
		} else {
			return count($this->collWorkspaces);
		}
	}

	/**
	 * Method called to associate a Workspace object to this object
	 * through the Workspace foreign key attribute.
	 *
	 * @param      Workspace $l Workspace
	 * @return     void
	 * @throws     PropelException
	 */
	public function addWorkspace(Workspace $l)
	{
		if ($this->collWorkspaces === null) {
			$this->initWorkspaces();
		}
		if (!$this->collWorkspaces->contains($l)) { // only add it if the **same** object is not already associated
			$this->collWorkspaces[]= $l;
			$l->setRegistration($this);
		}
	}

	/**
	 * Clears out the collWorkspaceUsers collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addWorkspaceUsers()
	 */
	public function clearWorkspaceUsers()
	{
		$this->collWorkspaceUsers = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collWorkspaceUsers collection.
	 *
	 * By default this just sets the collWorkspaceUsers collection to an empty array (like clearcollWorkspaceUsers());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initWorkspaceUsers()
	{
		$this->collWorkspaceUsers = new PropelObjectCollection();
		$this->collWorkspaceUsers->setModel('WorkspaceUser');
	}

	/**
	 * Gets an array of WorkspaceUser objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array WorkspaceUser[] List of WorkspaceUser objects
	 * @throws     PropelException
	 */
	public function getWorkspaceUsers($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collWorkspaceUsers || null !== $criteria) {
			if ($this->isNew() && null === $this->collWorkspaceUsers) {
				// return empty collection
				$this->initWorkspaceUsers();
			} else {
				$collWorkspaceUsers = WorkspaceUserQuery::create(null, $criteria)
					->filterByRegistration($this)
					->find($con);
				if (null !== $criteria) {
					return $collWorkspaceUsers;
				}
				$this->collWorkspaceUsers = $collWorkspaceUsers;
			}
		}
		return $this->collWorkspaceUsers;
	}

	/**
	 * Returns the number of related WorkspaceUser objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related WorkspaceUser objects.
	 * @throws     PropelException
	 */
	public function countWorkspaceUsers(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collWorkspaceUsers || null !== $criteria) {
			if ($this->isNew() && null === $this->collWorkspaceUsers) {
				return 0;
			} else {
				$query = WorkspaceUserQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistration($this)
					->count($con);
			}
		} else {
			return count($this->collWorkspaceUsers);
		}
	}

	/**
	 * Method called to associate a WorkspaceUser object to this object
	 * through the WorkspaceUser foreign key attribute.
	 *
	 * @param      WorkspaceUser $l WorkspaceUser
	 * @return     void
	 * @throws     PropelException
	 */
	public function addWorkspaceUser(WorkspaceUser $l)
	{
		if ($this->collWorkspaceUsers === null) {
			$this->initWorkspaceUsers();
		}
		if (!$this->collWorkspaceUsers->contains($l)) { // only add it if the **same** object is not already associated
			$this->collWorkspaceUsers[]= $l;
			$l->setRegistration($this);
		}
	}


	/**
	 * If this collection has already been initialized with
	 * an identical criteria, it returns the collection.
	 * Otherwise if this Registration is new, it will return
	 * an empty collection; or if this Registration has previously
	 * been saved, it will retrieve related WorkspaceUsers from storage.
	 *
	 * This method is protected by default in order to keep the public
	 * api reasonable.  You can provide public methods for those you
	 * actually need in Registration.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @param      string $join_behavior optional join type to use (defaults to Criteria::LEFT_JOIN)
	 * @return     PropelCollection|array WorkspaceUser[] List of WorkspaceUser objects
	 */
	public function getWorkspaceUsersJoinWorkspace($criteria = null, $con = null, $join_behavior = Criteria::LEFT_JOIN)
	{
		$query = WorkspaceUserQuery::create(null, $criteria);
		$query->joinWith('Workspace', $join_behavior);

		return $this->getWorkspaceUsers($query, $con);
	}


	/**
	 * If this collection has already been initialized with
	 * an identical criteria, it returns the collection.
	 * Otherwise if this Registration is new, it will return
	 * an empty collection; or if this Registration has previously
	 * been saved, it will retrieve related WorkspaceUsers from storage.
	 *
	 * This method is protected by default in order to keep the public
	 * api reasonable.  You can provide public methods for those you
	 * actually need in Registration.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @param      string $join_behavior optional join type to use (defaults to Criteria::LEFT_JOIN)
	 * @return     PropelCollection|array WorkspaceUser[] List of WorkspaceUser objects
	 */
	public function getWorkspaceUsersJoinAccess($criteria = null, $con = null, $join_behavior = Criteria::LEFT_JOIN)
	{
		$query = WorkspaceUserQuery::create(null, $criteria);
		$query->joinWith('Access', $join_behavior);

		return $this->getWorkspaceUsers($query, $con);
	}

	/**
	 * Clears the current object and sets all attributes to their default values
	 */
	public function clear()
	{
		$this->id = null;
		$this->phone = null;
		$this->interests = null;
		$this->state = null;
		$this->online_status = null;
		$this->password = null;
		$this->city = null;
		$this->username = null;
		$this->createdat = null;
		$this->first_name = null;
		$this->datavisibility = null;
		$this->work_title = null;
		$this->last_name = null;
		$this->zipcode = null;
		$this->lab_affiliation = null;
		$this->addr1 = null;
		$this->addr2 = null;
		$this->email = null;
		$this->logdata = null;
		$this->rootfolder_id = null;
		$this->alreadyInSave = false;
		$this->alreadyInValidation = false;
		$this->clearAllReferences();
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
			if ($this->collToolcommentss) {
				foreach ((array) $this->collToolcommentss as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collToolratingss) {
				foreach ((array) $this->collToolratingss as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collWorkflowcommentss) {
				foreach ((array) $this->collWorkflowcommentss as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collWorkflowratingss) {
				foreach ((array) $this->collWorkflowratingss as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collAnnotations) {
				foreach ((array) $this->collAnnotations as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collHistorys) {
				foreach ((array) $this->collHistorys as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collWorkspaces) {
				foreach ((array) $this->collWorkspaces as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collWorkspaceUsers) {
				foreach ((array) $this->collWorkspaceUsers as $o) {
					$o->clearAllReferences($deep);
				}
			}
		} // if ($deep)

		$this->collToolcommentss = null;
		$this->collToolratingss = null;
		$this->collWorkflowcommentss = null;
		$this->collWorkflowratingss = null;
		$this->collAnnotations = null;
		$this->collHistorys = null;
		$this->collWorkspaces = null;
		$this->collWorkspaceUsers = null;
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

} // BaseRegistration
