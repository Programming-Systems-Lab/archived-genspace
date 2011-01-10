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
	 * The value for the username field.
	 * @var        string
	 */
	protected $username;

	/**
	 * The value for the password field.
	 * @var        string
	 */
	protected $password;

	/**
	 * The value for the email field.
	 * @var        string
	 */
	protected $email;

	/**
	 * The value for the im_email field.
	 * @var        string
	 */
	protected $im_email;

	/**
	 * The value for the im_password field.
	 * @var        string
	 */
	protected $im_password;

	/**
	 * The value for the first_name field.
	 * @var        string
	 */
	protected $first_name;

	/**
	 * The value for the last_name field.
	 * @var        string
	 */
	protected $last_name;

	/**
	 * The value for the work_title field.
	 * @var        string
	 */
	protected $work_title;

	/**
	 * The value for the phone field.
	 * @var        string
	 */
	protected $phone;

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
	 * The value for the city field.
	 * @var        string
	 */
	protected $city;

	/**
	 * The value for the state field.
	 * @var        string
	 */
	protected $state;

	/**
	 * The value for the zipcode field.
	 * @var        string
	 */
	protected $zipcode;

	/**
	 * @var        DataVisibility one-to-one related DataVisibility object
	 */
	protected $singleDataVisibility;

	/**
	 * @var        array UserVisibility[] Collection to store aggregation of UserVisibility objects.
	 */
	protected $collUserVisibilitys;

	/**
	 * @var        array Audit[] Collection to store aggregation of Audit objects.
	 */
	protected $collAudits;

	/**
	 * @var        array NetworkVisibility[] Collection to store aggregation of NetworkVisibility objects.
	 */
	protected $collNetworkVisibilitys;

	/**
	 * @var        array Outbox[] Collection to store aggregation of Outbox objects.
	 */
	protected $collOutboxsRelatedByFromuser;

	/**
	 * @var        array Outbox[] Collection to store aggregation of Outbox objects.
	 */
	protected $collOutboxsRelatedByTouser;

	/**
	 * @var        array Inbox[] Collection to store aggregation of Inbox objects.
	 */
	protected $collInboxsRelatedByFromuser;

	/**
	 * @var        array Inbox[] Collection to store aggregation of Inbox objects.
	 */
	protected $collInboxsRelatedByTouser;

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
	 * Get the [username] column value.
	 * 
	 * @return     string
	 */
	public function getUsername()
	{
		return $this->username;
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
	 * Get the [email] column value.
	 * 
	 * @return     string
	 */
	public function getEmail()
	{
		return $this->email;
	}

	/**
	 * Get the [im_email] column value.
	 * 
	 * @return     string
	 */
	public function getImEmail()
	{
		return $this->im_email;
	}

	/**
	 * Get the [im_password] column value.
	 * 
	 * @return     string
	 */
	public function getImPassword()
	{
		return $this->im_password;
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
	 * Get the [last_name] column value.
	 * 
	 * @return     string
	 */
	public function getLastName()
	{
		return $this->last_name;
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
	 * Get the [phone] column value.
	 * 
	 * @return     string
	 */
	public function getPhone()
	{
		return $this->phone;
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
	 * Get the [city] column value.
	 * 
	 * @return     string
	 */
	public function getCity()
	{
		return $this->city;
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
	 * Get the [zipcode] column value.
	 * 
	 * @return     string
	 */
	public function getZipcode()
	{
		return $this->zipcode;
	}

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
	 * Set the value of [im_email] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setImEmail($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->im_email !== $v) {
			$this->im_email = $v;
			$this->modifiedColumns[] = RegistrationPeer::IM_EMAIL;
		}

		return $this;
	} // setImEmail()

	/**
	 * Set the value of [im_password] column.
	 * 
	 * @param      string $v new value
	 * @return     Registration The current object (for fluent API support)
	 */
	public function setImPassword($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->im_password !== $v) {
			$this->im_password = $v;
			$this->modifiedColumns[] = RegistrationPeer::IM_PASSWORD;
		}

		return $this;
	} // setImPassword()

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

			$this->username = ($row[$startcol + 0] !== null) ? (string) $row[$startcol + 0] : null;
			$this->password = ($row[$startcol + 1] !== null) ? (string) $row[$startcol + 1] : null;
			$this->email = ($row[$startcol + 2] !== null) ? (string) $row[$startcol + 2] : null;
			$this->im_email = ($row[$startcol + 3] !== null) ? (string) $row[$startcol + 3] : null;
			$this->im_password = ($row[$startcol + 4] !== null) ? (string) $row[$startcol + 4] : null;
			$this->first_name = ($row[$startcol + 5] !== null) ? (string) $row[$startcol + 5] : null;
			$this->last_name = ($row[$startcol + 6] !== null) ? (string) $row[$startcol + 6] : null;
			$this->work_title = ($row[$startcol + 7] !== null) ? (string) $row[$startcol + 7] : null;
			$this->phone = ($row[$startcol + 8] !== null) ? (string) $row[$startcol + 8] : null;
			$this->lab_affiliation = ($row[$startcol + 9] !== null) ? (string) $row[$startcol + 9] : null;
			$this->addr1 = ($row[$startcol + 10] !== null) ? (string) $row[$startcol + 10] : null;
			$this->addr2 = ($row[$startcol + 11] !== null) ? (string) $row[$startcol + 11] : null;
			$this->city = ($row[$startcol + 12] !== null) ? (string) $row[$startcol + 12] : null;
			$this->state = ($row[$startcol + 13] !== null) ? (string) $row[$startcol + 13] : null;
			$this->zipcode = ($row[$startcol + 14] !== null) ? (string) $row[$startcol + 14] : null;
			$this->resetModified();

			$this->setNew(false);

			if ($rehydrate) {
				$this->ensureConsistency();
			}

			return $startcol + 15; // 15 = RegistrationPeer::NUM_COLUMNS - RegistrationPeer::NUM_LAZY_LOAD_COLUMNS).

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

			$this->singleDataVisibility = null;

			$this->collUserVisibilitys = null;

			$this->collAudits = null;

			$this->collNetworkVisibilitys = null;

			$this->collOutboxsRelatedByFromuser = null;

			$this->collOutboxsRelatedByTouser = null;

			$this->collInboxsRelatedByFromuser = null;

			$this->collInboxsRelatedByTouser = null;

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


			// If this object has been modified, then save it to the database.
			if ($this->isModified()) {
				if ($this->isNew()) {
					$criteria = $this->buildCriteria();
					$pk = BasePeer::doInsert($criteria, $con);
					$affectedRows = 1;
					$this->setNew(false);
				} else {
					$affectedRows = RegistrationPeer::doUpdate($this, $con);
				}

				$this->resetModified(); // [HL] After being saved an object is no longer 'modified'
			}

			if ($this->singleDataVisibility !== null) {
				if (!$this->singleDataVisibility->isDeleted()) {
						$affectedRows += $this->singleDataVisibility->save($con);
				}
			}

			if ($this->collUserVisibilitys !== null) {
				foreach ($this->collUserVisibilitys as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collAudits !== null) {
				foreach ($this->collAudits as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collNetworkVisibilitys !== null) {
				foreach ($this->collNetworkVisibilitys as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collOutboxsRelatedByFromuser !== null) {
				foreach ($this->collOutboxsRelatedByFromuser as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collOutboxsRelatedByTouser !== null) {
				foreach ($this->collOutboxsRelatedByTouser as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collInboxsRelatedByFromuser !== null) {
				foreach ($this->collInboxsRelatedByFromuser as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collInboxsRelatedByTouser !== null) {
				foreach ($this->collInboxsRelatedByTouser as $referrerFK) {
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


				if ($this->singleDataVisibility !== null) {
					if (!$this->singleDataVisibility->validate($columns)) {
						$failureMap = array_merge($failureMap, $this->singleDataVisibility->getValidationFailures());
					}
				}

				if ($this->collUserVisibilitys !== null) {
					foreach ($this->collUserVisibilitys as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collAudits !== null) {
					foreach ($this->collAudits as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collNetworkVisibilitys !== null) {
					foreach ($this->collNetworkVisibilitys as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collOutboxsRelatedByFromuser !== null) {
					foreach ($this->collOutboxsRelatedByFromuser as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collOutboxsRelatedByTouser !== null) {
					foreach ($this->collOutboxsRelatedByTouser as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collInboxsRelatedByFromuser !== null) {
					foreach ($this->collInboxsRelatedByFromuser as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collInboxsRelatedByTouser !== null) {
					foreach ($this->collInboxsRelatedByTouser as $referrerFK) {
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
				return $this->getUsername();
				break;
			case 1:
				return $this->getPassword();
				break;
			case 2:
				return $this->getEmail();
				break;
			case 3:
				return $this->getImEmail();
				break;
			case 4:
				return $this->getImPassword();
				break;
			case 5:
				return $this->getFirstName();
				break;
			case 6:
				return $this->getLastName();
				break;
			case 7:
				return $this->getWorkTitle();
				break;
			case 8:
				return $this->getPhone();
				break;
			case 9:
				return $this->getLabAffiliation();
				break;
			case 10:
				return $this->getAddr1();
				break;
			case 11:
				return $this->getAddr2();
				break;
			case 12:
				return $this->getCity();
				break;
			case 13:
				return $this->getState();
				break;
			case 14:
				return $this->getZipcode();
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
			$keys[0] => $this->getUsername(),
			$keys[1] => $this->getPassword(),
			$keys[2] => $this->getEmail(),
			$keys[3] => $this->getImEmail(),
			$keys[4] => $this->getImPassword(),
			$keys[5] => $this->getFirstName(),
			$keys[6] => $this->getLastName(),
			$keys[7] => $this->getWorkTitle(),
			$keys[8] => $this->getPhone(),
			$keys[9] => $this->getLabAffiliation(),
			$keys[10] => $this->getAddr1(),
			$keys[11] => $this->getAddr2(),
			$keys[12] => $this->getCity(),
			$keys[13] => $this->getState(),
			$keys[14] => $this->getZipcode(),
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
				$this->setUsername($value);
				break;
			case 1:
				$this->setPassword($value);
				break;
			case 2:
				$this->setEmail($value);
				break;
			case 3:
				$this->setImEmail($value);
				break;
			case 4:
				$this->setImPassword($value);
				break;
			case 5:
				$this->setFirstName($value);
				break;
			case 6:
				$this->setLastName($value);
				break;
			case 7:
				$this->setWorkTitle($value);
				break;
			case 8:
				$this->setPhone($value);
				break;
			case 9:
				$this->setLabAffiliation($value);
				break;
			case 10:
				$this->setAddr1($value);
				break;
			case 11:
				$this->setAddr2($value);
				break;
			case 12:
				$this->setCity($value);
				break;
			case 13:
				$this->setState($value);
				break;
			case 14:
				$this->setZipcode($value);
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

		if (array_key_exists($keys[0], $arr)) $this->setUsername($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setPassword($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setEmail($arr[$keys[2]]);
		if (array_key_exists($keys[3], $arr)) $this->setImEmail($arr[$keys[3]]);
		if (array_key_exists($keys[4], $arr)) $this->setImPassword($arr[$keys[4]]);
		if (array_key_exists($keys[5], $arr)) $this->setFirstName($arr[$keys[5]]);
		if (array_key_exists($keys[6], $arr)) $this->setLastName($arr[$keys[6]]);
		if (array_key_exists($keys[7], $arr)) $this->setWorkTitle($arr[$keys[7]]);
		if (array_key_exists($keys[8], $arr)) $this->setPhone($arr[$keys[8]]);
		if (array_key_exists($keys[9], $arr)) $this->setLabAffiliation($arr[$keys[9]]);
		if (array_key_exists($keys[10], $arr)) $this->setAddr1($arr[$keys[10]]);
		if (array_key_exists($keys[11], $arr)) $this->setAddr2($arr[$keys[11]]);
		if (array_key_exists($keys[12], $arr)) $this->setCity($arr[$keys[12]]);
		if (array_key_exists($keys[13], $arr)) $this->setState($arr[$keys[13]]);
		if (array_key_exists($keys[14], $arr)) $this->setZipcode($arr[$keys[14]]);
	}

	/**
	 * Build a Criteria object containing the values of all modified columns in this object.
	 *
	 * @return     Criteria The Criteria object containing all modified values.
	 */
	public function buildCriteria()
	{
		$criteria = new Criteria(RegistrationPeer::DATABASE_NAME);

		if ($this->isColumnModified(RegistrationPeer::USERNAME)) $criteria->add(RegistrationPeer::USERNAME, $this->username);
		if ($this->isColumnModified(RegistrationPeer::PASSWORD)) $criteria->add(RegistrationPeer::PASSWORD, $this->password);
		if ($this->isColumnModified(RegistrationPeer::EMAIL)) $criteria->add(RegistrationPeer::EMAIL, $this->email);
		if ($this->isColumnModified(RegistrationPeer::IM_EMAIL)) $criteria->add(RegistrationPeer::IM_EMAIL, $this->im_email);
		if ($this->isColumnModified(RegistrationPeer::IM_PASSWORD)) $criteria->add(RegistrationPeer::IM_PASSWORD, $this->im_password);
		if ($this->isColumnModified(RegistrationPeer::FIRST_NAME)) $criteria->add(RegistrationPeer::FIRST_NAME, $this->first_name);
		if ($this->isColumnModified(RegistrationPeer::LAST_NAME)) $criteria->add(RegistrationPeer::LAST_NAME, $this->last_name);
		if ($this->isColumnModified(RegistrationPeer::WORK_TITLE)) $criteria->add(RegistrationPeer::WORK_TITLE, $this->work_title);
		if ($this->isColumnModified(RegistrationPeer::PHONE)) $criteria->add(RegistrationPeer::PHONE, $this->phone);
		if ($this->isColumnModified(RegistrationPeer::LAB_AFFILIATION)) $criteria->add(RegistrationPeer::LAB_AFFILIATION, $this->lab_affiliation);
		if ($this->isColumnModified(RegistrationPeer::ADDR1)) $criteria->add(RegistrationPeer::ADDR1, $this->addr1);
		if ($this->isColumnModified(RegistrationPeer::ADDR2)) $criteria->add(RegistrationPeer::ADDR2, $this->addr2);
		if ($this->isColumnModified(RegistrationPeer::CITY)) $criteria->add(RegistrationPeer::CITY, $this->city);
		if ($this->isColumnModified(RegistrationPeer::STATE)) $criteria->add(RegistrationPeer::STATE, $this->state);
		if ($this->isColumnModified(RegistrationPeer::ZIPCODE)) $criteria->add(RegistrationPeer::ZIPCODE, $this->zipcode);

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
		$criteria->add(RegistrationPeer::USERNAME, $this->username);

		return $criteria;
	}

	/**
	 * Returns the primary key for this object (row).
	 * @return     string
	 */
	public function getPrimaryKey()
	{
		return $this->getUsername();
	}

	/**
	 * Generic method to set the primary key (username column).
	 *
	 * @param      string $key Primary key.
	 * @return     void
	 */
	public function setPrimaryKey($key)
	{
		$this->setUsername($key);
	}

	/**
	 * Returns true if the primary key for this object is null.
	 * @return     boolean
	 */
	public function isPrimaryKeyNull()
	{
		return null === $this->getUsername();
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
		$copyObj->setUsername($this->username);
		$copyObj->setPassword($this->password);
		$copyObj->setEmail($this->email);
		$copyObj->setImEmail($this->im_email);
		$copyObj->setImPassword($this->im_password);
		$copyObj->setFirstName($this->first_name);
		$copyObj->setLastName($this->last_name);
		$copyObj->setWorkTitle($this->work_title);
		$copyObj->setPhone($this->phone);
		$copyObj->setLabAffiliation($this->lab_affiliation);
		$copyObj->setAddr1($this->addr1);
		$copyObj->setAddr2($this->addr2);
		$copyObj->setCity($this->city);
		$copyObj->setState($this->state);
		$copyObj->setZipcode($this->zipcode);

		if ($deepCopy) {
			// important: temporarily setNew(false) because this affects the behavior of
			// the getter/setter methods for fkey referrer objects.
			$copyObj->setNew(false);

			$relObj = $this->getDataVisibility();
			if ($relObj) {
				$copyObj->setDataVisibility($relObj->copy($deepCopy));
			}

			foreach ($this->getUserVisibilitys() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addUserVisibility($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getAudits() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addAudit($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getNetworkVisibilitys() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addNetworkVisibility($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getOutboxsRelatedByFromuser() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addOutboxRelatedByFromuser($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getOutboxsRelatedByTouser() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addOutboxRelatedByTouser($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getInboxsRelatedByFromuser() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addInboxRelatedByFromuser($relObj->copy($deepCopy));
				}
			}

			foreach ($this->getInboxsRelatedByTouser() as $relObj) {
				if ($relObj !== $this) {  // ensure that we don't try to copy a reference to ourselves
					$copyObj->addInboxRelatedByTouser($relObj->copy($deepCopy));
				}
			}

		} // if ($deepCopy)


		$copyObj->setNew(true);
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
	 * Gets a single DataVisibility object, which is related to this object by a one-to-one relationship.
	 *
	 * @param      PropelPDO $con optional connection object
	 * @return     DataVisibility
	 * @throws     PropelException
	 */
	public function getDataVisibility(PropelPDO $con = null)
	{

		if ($this->singleDataVisibility === null && !$this->isNew()) {
			$this->singleDataVisibility = DataVisibilityQuery::create()->findPk($this->getPrimaryKey(), $con);
		}

		return $this->singleDataVisibility;
	}

	/**
	 * Sets a single DataVisibility object as related to this object by a one-to-one relationship.
	 *
	 * @param      DataVisibility $v DataVisibility
	 * @return     Registration The current object (for fluent API support)
	 * @throws     PropelException
	 */
	public function setDataVisibility(DataVisibility $v = null)
	{
		$this->singleDataVisibility = $v;

		// Make sure that that the passed-in DataVisibility isn't already associated with this object
		if ($v !== null && $v->getRegistration() === null) {
			$v->setRegistration($this);
		}

		return $this;
	}

	/**
	 * Clears out the collUserVisibilitys collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addUserVisibilitys()
	 */
	public function clearUserVisibilitys()
	{
		$this->collUserVisibilitys = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collUserVisibilitys collection.
	 *
	 * By default this just sets the collUserVisibilitys collection to an empty array (like clearcollUserVisibilitys());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initUserVisibilitys()
	{
		$this->collUserVisibilitys = new PropelObjectCollection();
		$this->collUserVisibilitys->setModel('UserVisibility');
	}

	/**
	 * Gets an array of UserVisibility objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array UserVisibility[] List of UserVisibility objects
	 * @throws     PropelException
	 */
	public function getUserVisibilitys($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collUserVisibilitys || null !== $criteria) {
			if ($this->isNew() && null === $this->collUserVisibilitys) {
				// return empty collection
				$this->initUserVisibilitys();
			} else {
				$collUserVisibilitys = UserVisibilityQuery::create(null, $criteria)
					->filterByRegistration($this)
					->find($con);
				if (null !== $criteria) {
					return $collUserVisibilitys;
				}
				$this->collUserVisibilitys = $collUserVisibilitys;
			}
		}
		return $this->collUserVisibilitys;
	}

	/**
	 * Returns the number of related UserVisibility objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related UserVisibility objects.
	 * @throws     PropelException
	 */
	public function countUserVisibilitys(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collUserVisibilitys || null !== $criteria) {
			if ($this->isNew() && null === $this->collUserVisibilitys) {
				return 0;
			} else {
				$query = UserVisibilityQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistration($this)
					->count($con);
			}
		} else {
			return count($this->collUserVisibilitys);
		}
	}

	/**
	 * Method called to associate a UserVisibility object to this object
	 * through the UserVisibility foreign key attribute.
	 *
	 * @param      UserVisibility $l UserVisibility
	 * @return     void
	 * @throws     PropelException
	 */
	public function addUserVisibility(UserVisibility $l)
	{
		if ($this->collUserVisibilitys === null) {
			$this->initUserVisibilitys();
		}
		if (!$this->collUserVisibilitys->contains($l)) { // only add it if the **same** object is not already associated
			$this->collUserVisibilitys[]= $l;
			$l->setRegistration($this);
		}
	}

	/**
	 * Clears out the collAudits collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addAudits()
	 */
	public function clearAudits()
	{
		$this->collAudits = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collAudits collection.
	 *
	 * By default this just sets the collAudits collection to an empty array (like clearcollAudits());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initAudits()
	{
		$this->collAudits = new PropelObjectCollection();
		$this->collAudits->setModel('Audit');
	}

	/**
	 * Gets an array of Audit objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array Audit[] List of Audit objects
	 * @throws     PropelException
	 */
	public function getAudits($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collAudits || null !== $criteria) {
			if ($this->isNew() && null === $this->collAudits) {
				// return empty collection
				$this->initAudits();
			} else {
				$collAudits = AuditQuery::create(null, $criteria)
					->filterByRegistration($this)
					->find($con);
				if (null !== $criteria) {
					return $collAudits;
				}
				$this->collAudits = $collAudits;
			}
		}
		return $this->collAudits;
	}

	/**
	 * Returns the number of related Audit objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related Audit objects.
	 * @throws     PropelException
	 */
	public function countAudits(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collAudits || null !== $criteria) {
			if ($this->isNew() && null === $this->collAudits) {
				return 0;
			} else {
				$query = AuditQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistration($this)
					->count($con);
			}
		} else {
			return count($this->collAudits);
		}
	}

	/**
	 * Method called to associate a Audit object to this object
	 * through the Audit foreign key attribute.
	 *
	 * @param      Audit $l Audit
	 * @return     void
	 * @throws     PropelException
	 */
	public function addAudit(Audit $l)
	{
		if ($this->collAudits === null) {
			$this->initAudits();
		}
		if (!$this->collAudits->contains($l)) { // only add it if the **same** object is not already associated
			$this->collAudits[]= $l;
			$l->setRegistration($this);
		}
	}

	/**
	 * Clears out the collNetworkVisibilitys collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addNetworkVisibilitys()
	 */
	public function clearNetworkVisibilitys()
	{
		$this->collNetworkVisibilitys = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collNetworkVisibilitys collection.
	 *
	 * By default this just sets the collNetworkVisibilitys collection to an empty array (like clearcollNetworkVisibilitys());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initNetworkVisibilitys()
	{
		$this->collNetworkVisibilitys = new PropelObjectCollection();
		$this->collNetworkVisibilitys->setModel('NetworkVisibility');
	}

	/**
	 * Gets an array of NetworkVisibility objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array NetworkVisibility[] List of NetworkVisibility objects
	 * @throws     PropelException
	 */
	public function getNetworkVisibilitys($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collNetworkVisibilitys || null !== $criteria) {
			if ($this->isNew() && null === $this->collNetworkVisibilitys) {
				// return empty collection
				$this->initNetworkVisibilitys();
			} else {
				$collNetworkVisibilitys = NetworkVisibilityQuery::create(null, $criteria)
					->filterByRegistration($this)
					->find($con);
				if (null !== $criteria) {
					return $collNetworkVisibilitys;
				}
				$this->collNetworkVisibilitys = $collNetworkVisibilitys;
			}
		}
		return $this->collNetworkVisibilitys;
	}

	/**
	 * Returns the number of related NetworkVisibility objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related NetworkVisibility objects.
	 * @throws     PropelException
	 */
	public function countNetworkVisibilitys(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collNetworkVisibilitys || null !== $criteria) {
			if ($this->isNew() && null === $this->collNetworkVisibilitys) {
				return 0;
			} else {
				$query = NetworkVisibilityQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistration($this)
					->count($con);
			}
		} else {
			return count($this->collNetworkVisibilitys);
		}
	}

	/**
	 * Method called to associate a NetworkVisibility object to this object
	 * through the NetworkVisibility foreign key attribute.
	 *
	 * @param      NetworkVisibility $l NetworkVisibility
	 * @return     void
	 * @throws     PropelException
	 */
	public function addNetworkVisibility(NetworkVisibility $l)
	{
		if ($this->collNetworkVisibilitys === null) {
			$this->initNetworkVisibilitys();
		}
		if (!$this->collNetworkVisibilitys->contains($l)) { // only add it if the **same** object is not already associated
			$this->collNetworkVisibilitys[]= $l;
			$l->setRegistration($this);
		}
	}

	/**
	 * Clears out the collOutboxsRelatedByFromuser collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addOutboxsRelatedByFromuser()
	 */
	public function clearOutboxsRelatedByFromuser()
	{
		$this->collOutboxsRelatedByFromuser = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collOutboxsRelatedByFromuser collection.
	 *
	 * By default this just sets the collOutboxsRelatedByFromuser collection to an empty array (like clearcollOutboxsRelatedByFromuser());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initOutboxsRelatedByFromuser()
	{
		$this->collOutboxsRelatedByFromuser = new PropelObjectCollection();
		$this->collOutboxsRelatedByFromuser->setModel('Outbox');
	}

	/**
	 * Gets an array of Outbox objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array Outbox[] List of Outbox objects
	 * @throws     PropelException
	 */
	public function getOutboxsRelatedByFromuser($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collOutboxsRelatedByFromuser || null !== $criteria) {
			if ($this->isNew() && null === $this->collOutboxsRelatedByFromuser) {
				// return empty collection
				$this->initOutboxsRelatedByFromuser();
			} else {
				$collOutboxsRelatedByFromuser = OutboxQuery::create(null, $criteria)
					->filterByRegistrationRelatedByFromuser($this)
					->find($con);
				if (null !== $criteria) {
					return $collOutboxsRelatedByFromuser;
				}
				$this->collOutboxsRelatedByFromuser = $collOutboxsRelatedByFromuser;
			}
		}
		return $this->collOutboxsRelatedByFromuser;
	}

	/**
	 * Returns the number of related Outbox objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related Outbox objects.
	 * @throws     PropelException
	 */
	public function countOutboxsRelatedByFromuser(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collOutboxsRelatedByFromuser || null !== $criteria) {
			if ($this->isNew() && null === $this->collOutboxsRelatedByFromuser) {
				return 0;
			} else {
				$query = OutboxQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistrationRelatedByFromuser($this)
					->count($con);
			}
		} else {
			return count($this->collOutboxsRelatedByFromuser);
		}
	}

	/**
	 * Method called to associate a Outbox object to this object
	 * through the Outbox foreign key attribute.
	 *
	 * @param      Outbox $l Outbox
	 * @return     void
	 * @throws     PropelException
	 */
	public function addOutboxRelatedByFromuser(Outbox $l)
	{
		if ($this->collOutboxsRelatedByFromuser === null) {
			$this->initOutboxsRelatedByFromuser();
		}
		if (!$this->collOutboxsRelatedByFromuser->contains($l)) { // only add it if the **same** object is not already associated
			$this->collOutboxsRelatedByFromuser[]= $l;
			$l->setRegistrationRelatedByFromuser($this);
		}
	}

	/**
	 * Clears out the collOutboxsRelatedByTouser collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addOutboxsRelatedByTouser()
	 */
	public function clearOutboxsRelatedByTouser()
	{
		$this->collOutboxsRelatedByTouser = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collOutboxsRelatedByTouser collection.
	 *
	 * By default this just sets the collOutboxsRelatedByTouser collection to an empty array (like clearcollOutboxsRelatedByTouser());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initOutboxsRelatedByTouser()
	{
		$this->collOutboxsRelatedByTouser = new PropelObjectCollection();
		$this->collOutboxsRelatedByTouser->setModel('Outbox');
	}

	/**
	 * Gets an array of Outbox objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array Outbox[] List of Outbox objects
	 * @throws     PropelException
	 */
	public function getOutboxsRelatedByTouser($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collOutboxsRelatedByTouser || null !== $criteria) {
			if ($this->isNew() && null === $this->collOutboxsRelatedByTouser) {
				// return empty collection
				$this->initOutboxsRelatedByTouser();
			} else {
				$collOutboxsRelatedByTouser = OutboxQuery::create(null, $criteria)
					->filterByRegistrationRelatedByTouser($this)
					->find($con);
				if (null !== $criteria) {
					return $collOutboxsRelatedByTouser;
				}
				$this->collOutboxsRelatedByTouser = $collOutboxsRelatedByTouser;
			}
		}
		return $this->collOutboxsRelatedByTouser;
	}

	/**
	 * Returns the number of related Outbox objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related Outbox objects.
	 * @throws     PropelException
	 */
	public function countOutboxsRelatedByTouser(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collOutboxsRelatedByTouser || null !== $criteria) {
			if ($this->isNew() && null === $this->collOutboxsRelatedByTouser) {
				return 0;
			} else {
				$query = OutboxQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistrationRelatedByTouser($this)
					->count($con);
			}
		} else {
			return count($this->collOutboxsRelatedByTouser);
		}
	}

	/**
	 * Method called to associate a Outbox object to this object
	 * through the Outbox foreign key attribute.
	 *
	 * @param      Outbox $l Outbox
	 * @return     void
	 * @throws     PropelException
	 */
	public function addOutboxRelatedByTouser(Outbox $l)
	{
		if ($this->collOutboxsRelatedByTouser === null) {
			$this->initOutboxsRelatedByTouser();
		}
		if (!$this->collOutboxsRelatedByTouser->contains($l)) { // only add it if the **same** object is not already associated
			$this->collOutboxsRelatedByTouser[]= $l;
			$l->setRegistrationRelatedByTouser($this);
		}
	}

	/**
	 * Clears out the collInboxsRelatedByFromuser collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addInboxsRelatedByFromuser()
	 */
	public function clearInboxsRelatedByFromuser()
	{
		$this->collInboxsRelatedByFromuser = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collInboxsRelatedByFromuser collection.
	 *
	 * By default this just sets the collInboxsRelatedByFromuser collection to an empty array (like clearcollInboxsRelatedByFromuser());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initInboxsRelatedByFromuser()
	{
		$this->collInboxsRelatedByFromuser = new PropelObjectCollection();
		$this->collInboxsRelatedByFromuser->setModel('Inbox');
	}

	/**
	 * Gets an array of Inbox objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array Inbox[] List of Inbox objects
	 * @throws     PropelException
	 */
	public function getInboxsRelatedByFromuser($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collInboxsRelatedByFromuser || null !== $criteria) {
			if ($this->isNew() && null === $this->collInboxsRelatedByFromuser) {
				// return empty collection
				$this->initInboxsRelatedByFromuser();
			} else {
				$collInboxsRelatedByFromuser = InboxQuery::create(null, $criteria)
					->filterByRegistrationRelatedByFromuser($this)
					->find($con);
				if (null !== $criteria) {
					return $collInboxsRelatedByFromuser;
				}
				$this->collInboxsRelatedByFromuser = $collInboxsRelatedByFromuser;
			}
		}
		return $this->collInboxsRelatedByFromuser;
	}

	/**
	 * Returns the number of related Inbox objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related Inbox objects.
	 * @throws     PropelException
	 */
	public function countInboxsRelatedByFromuser(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collInboxsRelatedByFromuser || null !== $criteria) {
			if ($this->isNew() && null === $this->collInboxsRelatedByFromuser) {
				return 0;
			} else {
				$query = InboxQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistrationRelatedByFromuser($this)
					->count($con);
			}
		} else {
			return count($this->collInboxsRelatedByFromuser);
		}
	}

	/**
	 * Method called to associate a Inbox object to this object
	 * through the Inbox foreign key attribute.
	 *
	 * @param      Inbox $l Inbox
	 * @return     void
	 * @throws     PropelException
	 */
	public function addInboxRelatedByFromuser(Inbox $l)
	{
		if ($this->collInboxsRelatedByFromuser === null) {
			$this->initInboxsRelatedByFromuser();
		}
		if (!$this->collInboxsRelatedByFromuser->contains($l)) { // only add it if the **same** object is not already associated
			$this->collInboxsRelatedByFromuser[]= $l;
			$l->setRegistrationRelatedByFromuser($this);
		}
	}

	/**
	 * Clears out the collInboxsRelatedByTouser collection
	 *
	 * This does not modify the database; however, it will remove any associated objects, causing
	 * them to be refetched by subsequent calls to accessor method.
	 *
	 * @return     void
	 * @see        addInboxsRelatedByTouser()
	 */
	public function clearInboxsRelatedByTouser()
	{
		$this->collInboxsRelatedByTouser = null; // important to set this to NULL since that means it is uninitialized
	}

	/**
	 * Initializes the collInboxsRelatedByTouser collection.
	 *
	 * By default this just sets the collInboxsRelatedByTouser collection to an empty array (like clearcollInboxsRelatedByTouser());
	 * however, you may wish to override this method in your stub class to provide setting appropriate
	 * to your application -- for example, setting the initial array to the values stored in database.
	 *
	 * @return     void
	 */
	public function initInboxsRelatedByTouser()
	{
		$this->collInboxsRelatedByTouser = new PropelObjectCollection();
		$this->collInboxsRelatedByTouser->setModel('Inbox');
	}

	/**
	 * Gets an array of Inbox objects which contain a foreign key that references this object.
	 *
	 * If the $criteria is not null, it is used to always fetch the results from the database.
	 * Otherwise the results are fetched from the database the first time, then cached.
	 * Next time the same method is called without $criteria, the cached collection is returned.
	 * If this Registration is new, it will return
	 * an empty collection or the current collection; the criteria is ignored on a new object.
	 *
	 * @param      Criteria $criteria optional Criteria object to narrow the query
	 * @param      PropelPDO $con optional connection object
	 * @return     PropelCollection|array Inbox[] List of Inbox objects
	 * @throws     PropelException
	 */
	public function getInboxsRelatedByTouser($criteria = null, PropelPDO $con = null)
	{
		if(null === $this->collInboxsRelatedByTouser || null !== $criteria) {
			if ($this->isNew() && null === $this->collInboxsRelatedByTouser) {
				// return empty collection
				$this->initInboxsRelatedByTouser();
			} else {
				$collInboxsRelatedByTouser = InboxQuery::create(null, $criteria)
					->filterByRegistrationRelatedByTouser($this)
					->find($con);
				if (null !== $criteria) {
					return $collInboxsRelatedByTouser;
				}
				$this->collInboxsRelatedByTouser = $collInboxsRelatedByTouser;
			}
		}
		return $this->collInboxsRelatedByTouser;
	}

	/**
	 * Returns the number of related Inbox objects.
	 *
	 * @param      Criteria $criteria
	 * @param      boolean $distinct
	 * @param      PropelPDO $con
	 * @return     int Count of related Inbox objects.
	 * @throws     PropelException
	 */
	public function countInboxsRelatedByTouser(Criteria $criteria = null, $distinct = false, PropelPDO $con = null)
	{
		if(null === $this->collInboxsRelatedByTouser || null !== $criteria) {
			if ($this->isNew() && null === $this->collInboxsRelatedByTouser) {
				return 0;
			} else {
				$query = InboxQuery::create(null, $criteria);
				if($distinct) {
					$query->distinct();
				}
				return $query
					->filterByRegistrationRelatedByTouser($this)
					->count($con);
			}
		} else {
			return count($this->collInboxsRelatedByTouser);
		}
	}

	/**
	 * Method called to associate a Inbox object to this object
	 * through the Inbox foreign key attribute.
	 *
	 * @param      Inbox $l Inbox
	 * @return     void
	 * @throws     PropelException
	 */
	public function addInboxRelatedByTouser(Inbox $l)
	{
		if ($this->collInboxsRelatedByTouser === null) {
			$this->initInboxsRelatedByTouser();
		}
		if (!$this->collInboxsRelatedByTouser->contains($l)) { // only add it if the **same** object is not already associated
			$this->collInboxsRelatedByTouser[]= $l;
			$l->setRegistrationRelatedByTouser($this);
		}
	}

	/**
	 * Clears the current object and sets all attributes to their default values
	 */
	public function clear()
	{
		$this->username = null;
		$this->password = null;
		$this->email = null;
		$this->im_email = null;
		$this->im_password = null;
		$this->first_name = null;
		$this->last_name = null;
		$this->work_title = null;
		$this->phone = null;
		$this->lab_affiliation = null;
		$this->addr1 = null;
		$this->addr2 = null;
		$this->city = null;
		$this->state = null;
		$this->zipcode = null;
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
			if ($this->singleDataVisibility) {
				$this->singleDataVisibility->clearAllReferences($deep);
			}
			if ($this->collUserVisibilitys) {
				foreach ((array) $this->collUserVisibilitys as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collAudits) {
				foreach ((array) $this->collAudits as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collNetworkVisibilitys) {
				foreach ((array) $this->collNetworkVisibilitys as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collOutboxsRelatedByFromuser) {
				foreach ((array) $this->collOutboxsRelatedByFromuser as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collOutboxsRelatedByTouser) {
				foreach ((array) $this->collOutboxsRelatedByTouser as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collInboxsRelatedByFromuser) {
				foreach ((array) $this->collInboxsRelatedByFromuser as $o) {
					$o->clearAllReferences($deep);
				}
			}
			if ($this->collInboxsRelatedByTouser) {
				foreach ((array) $this->collInboxsRelatedByTouser as $o) {
					$o->clearAllReferences($deep);
				}
			}
		} // if ($deep)

		$this->singleDataVisibility = null;
		$this->collUserVisibilitys = null;
		$this->collAudits = null;
		$this->collNetworkVisibilitys = null;
		$this->collOutboxsRelatedByFromuser = null;
		$this->collOutboxsRelatedByTouser = null;
		$this->collInboxsRelatedByFromuser = null;
		$this->collInboxsRelatedByTouser = null;
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
