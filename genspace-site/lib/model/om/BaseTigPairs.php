<?php


/**
 * Base class that represents a row from the 'tig_pairs' table.
 *
 * 
 *
 * @package    propel.generator.lib.model.om
 */
abstract class BaseTigPairs extends BaseObject  implements Persistent
{

	/**
	 * Peer class name
	 */
  const PEER = 'TigPairsPeer';

	/**
	 * The Peer class.
	 * Instance provides a convenient way of calling static methods on a class
	 * that calling code may not be able to identify.
	 * @var        TigPairsPeer
	 */
	protected static $peer;

	/**
	 * The value for the nid field.
	 * @var        string
	 */
	protected $nid;

	/**
	 * The value for the uid field.
	 * @var        string
	 */
	protected $uid;

	/**
	 * The value for the pkey field.
	 * @var        string
	 */
	protected $pkey;

	/**
	 * The value for the pval field.
	 * @var        string
	 */
	protected $pval;

	/**
	 * The value for the id field.
	 * @var        int
	 */
	protected $id;

	/**
	 * @var        TigNodes
	 */
	protected $aTigNodes;

	/**
	 * @var        TigUsers
	 */
	protected $aTigUsers;

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
	 * Get the [nid] column value.
	 * 
	 * @return     string
	 */
	public function getNid()
	{
		return $this->nid;
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
	 * Get the [pkey] column value.
	 * 
	 * @return     string
	 */
	public function getPkey()
	{
		return $this->pkey;
	}

	/**
	 * Get the [pval] column value.
	 * 
	 * @return     string
	 */
	public function getPval()
	{
		return $this->pval;
	}

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
	 * Set the value of [nid] column.
	 * 
	 * @param      string $v new value
	 * @return     TigPairs The current object (for fluent API support)
	 */
	public function setNid($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->nid !== $v) {
			$this->nid = $v;
			$this->modifiedColumns[] = TigPairsPeer::NID;
		}

		if ($this->aTigNodes !== null && $this->aTigNodes->getNid() !== $v) {
			$this->aTigNodes = null;
		}

		return $this;
	} // setNid()

	/**
	 * Set the value of [uid] column.
	 * 
	 * @param      string $v new value
	 * @return     TigPairs The current object (for fluent API support)
	 */
	public function setUid($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->uid !== $v) {
			$this->uid = $v;
			$this->modifiedColumns[] = TigPairsPeer::UID;
		}

		if ($this->aTigUsers !== null && $this->aTigUsers->getUid() !== $v) {
			$this->aTigUsers = null;
		}

		return $this;
	} // setUid()

	/**
	 * Set the value of [pkey] column.
	 * 
	 * @param      string $v new value
	 * @return     TigPairs The current object (for fluent API support)
	 */
	public function setPkey($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->pkey !== $v) {
			$this->pkey = $v;
			$this->modifiedColumns[] = TigPairsPeer::PKEY;
		}

		return $this;
	} // setPkey()

	/**
	 * Set the value of [pval] column.
	 * 
	 * @param      string $v new value
	 * @return     TigPairs The current object (for fluent API support)
	 */
	public function setPval($v)
	{
		if ($v !== null) {
			$v = (string) $v;
		}

		if ($this->pval !== $v) {
			$this->pval = $v;
			$this->modifiedColumns[] = TigPairsPeer::PVAL;
		}

		return $this;
	} // setPval()

	/**
	 * Set the value of [id] column.
	 * 
	 * @param      int $v new value
	 * @return     TigPairs The current object (for fluent API support)
	 */
	public function setId($v)
	{
		if ($v !== null) {
			$v = (int) $v;
		}

		if ($this->id !== $v) {
			$this->id = $v;
			$this->modifiedColumns[] = TigPairsPeer::ID;
		}

		return $this;
	} // setId()

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

			$this->nid = ($row[$startcol + 0] !== null) ? (string) $row[$startcol + 0] : null;
			$this->uid = ($row[$startcol + 1] !== null) ? (string) $row[$startcol + 1] : null;
			$this->pkey = ($row[$startcol + 2] !== null) ? (string) $row[$startcol + 2] : null;
			$this->pval = ($row[$startcol + 3] !== null) ? (string) $row[$startcol + 3] : null;
			$this->id = ($row[$startcol + 4] !== null) ? (int) $row[$startcol + 4] : null;
			$this->resetModified();

			$this->setNew(false);

			if ($rehydrate) {
				$this->ensureConsistency();
			}

			return $startcol + 5; // 5 = TigPairsPeer::NUM_COLUMNS - TigPairsPeer::NUM_LAZY_LOAD_COLUMNS).

		} catch (Exception $e) {
			throw new PropelException("Error populating TigPairs object", $e);
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

		if ($this->aTigNodes !== null && $this->nid !== $this->aTigNodes->getNid()) {
			$this->aTigNodes = null;
		}
		if ($this->aTigUsers !== null && $this->uid !== $this->aTigUsers->getUid()) {
			$this->aTigUsers = null;
		}
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
			$con = Propel::getConnection(TigPairsPeer::DATABASE_NAME, Propel::CONNECTION_READ);
		}

		// We don't need to alter the object instance pool; we're just modifying this instance
		// already in the pool.

		$stmt = TigPairsPeer::doSelectStmt($this->buildPkeyCriteria(), $con);
		$row = $stmt->fetch(PDO::FETCH_NUM);
		$stmt->closeCursor();
		if (!$row) {
			throw new PropelException('Cannot find matching row in the database to reload object values.');
		}
		$this->hydrate($row, 0, true); // rehydrate

		if ($deep) {  // also de-associate any related objects?

			$this->aTigNodes = null;
			$this->aTigUsers = null;
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
			$con = Propel::getConnection(TigPairsPeer::DATABASE_NAME, Propel::CONNECTION_WRITE);
		}
		
		$con->beginTransaction();
		try {
			$ret = $this->preDelete($con);
			if ($ret) {
				TigPairsQuery::create()
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
			$con = Propel::getConnection(TigPairsPeer::DATABASE_NAME, Propel::CONNECTION_WRITE);
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
				TigPairsPeer::addInstanceToPool($this);
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

			// We call the save method on the following object(s) if they
			// were passed to this object by their coresponding set
			// method.  This object relates to these object(s) by a
			// foreign key reference.

			if ($this->aTigNodes !== null) {
				if ($this->aTigNodes->isModified() || $this->aTigNodes->isNew()) {
					$affectedRows += $this->aTigNodes->save($con);
				}
				$this->setTigNodes($this->aTigNodes);
			}

			if ($this->aTigUsers !== null) {
				if ($this->aTigUsers->isModified() || $this->aTigUsers->isNew()) {
					$affectedRows += $this->aTigUsers->save($con);
				}
				$this->setTigUsers($this->aTigUsers);
			}

			if ($this->isNew() ) {
				$this->modifiedColumns[] = TigPairsPeer::ID;
			}

			// If this object has been modified, then save it to the database.
			if ($this->isModified()) {
				if ($this->isNew()) {
					$criteria = $this->buildCriteria();
					if ($criteria->keyContainsValue(TigPairsPeer::ID) ) {
						throw new PropelException('Cannot insert a value for auto-increment primary key ('.TigPairsPeer::ID.')');
					}

					// remove pkey col since this table uses auto-increment and passing a null value for it is not valid 
					$criteria->remove(TigPairsPeer::ID);

					$pk = BasePeer::doInsert($criteria, $con);
					$affectedRows += 1;
					$this->setId($pk);  //[IMV] update autoincrement primary key
					$this->setNew(false);
				} else {
					$affectedRows += TigPairsPeer::doUpdate($this, $con);
				}

				$this->resetModified(); // [HL] After being saved an object is no longer 'modified'
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


			// We call the validate method on the following object(s) if they
			// were passed to this object by their coresponding set
			// method.  This object relates to these object(s) by a
			// foreign key reference.

			if ($this->aTigNodes !== null) {
				if (!$this->aTigNodes->validate($columns)) {
					$failureMap = array_merge($failureMap, $this->aTigNodes->getValidationFailures());
				}
			}

			if ($this->aTigUsers !== null) {
				if (!$this->aTigUsers->validate($columns)) {
					$failureMap = array_merge($failureMap, $this->aTigUsers->getValidationFailures());
				}
			}


			if (($retval = TigPairsPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
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
		$pos = TigPairsPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
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
				return $this->getNid();
				break;
			case 1:
				return $this->getUid();
				break;
			case 2:
				return $this->getPkey();
				break;
			case 3:
				return $this->getPval();
				break;
			case 4:
				return $this->getId();
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
	 * @param     boolean $includeForeignObjects (optional) Whether to include hydrated related objects. Default to FALSE.
	 *
	 * @return    array an associative array containing the field names (as keys) and field values
	 */
	public function toArray($keyType = BasePeer::TYPE_PHPNAME, $includeLazyLoadColumns = true, $includeForeignObjects = false)
	{
		$keys = TigPairsPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getNid(),
			$keys[1] => $this->getUid(),
			$keys[2] => $this->getPkey(),
			$keys[3] => $this->getPval(),
			$keys[4] => $this->getId(),
		);
		if ($includeForeignObjects) {
			if (null !== $this->aTigNodes) {
				$result['TigNodes'] = $this->aTigNodes->toArray($keyType, $includeLazyLoadColumns, true);
			}
			if (null !== $this->aTigUsers) {
				$result['TigUsers'] = $this->aTigUsers->toArray($keyType, $includeLazyLoadColumns, true);
			}
		}
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
		$pos = TigPairsPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
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
				$this->setNid($value);
				break;
			case 1:
				$this->setUid($value);
				break;
			case 2:
				$this->setPkey($value);
				break;
			case 3:
				$this->setPval($value);
				break;
			case 4:
				$this->setId($value);
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
		$keys = TigPairsPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setNid($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setUid($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setPkey($arr[$keys[2]]);
		if (array_key_exists($keys[3], $arr)) $this->setPval($arr[$keys[3]]);
		if (array_key_exists($keys[4], $arr)) $this->setId($arr[$keys[4]]);
	}

	/**
	 * Build a Criteria object containing the values of all modified columns in this object.
	 *
	 * @return     Criteria The Criteria object containing all modified values.
	 */
	public function buildCriteria()
	{
		$criteria = new Criteria(TigPairsPeer::DATABASE_NAME);

		if ($this->isColumnModified(TigPairsPeer::NID)) $criteria->add(TigPairsPeer::NID, $this->nid);
		if ($this->isColumnModified(TigPairsPeer::UID)) $criteria->add(TigPairsPeer::UID, $this->uid);
		if ($this->isColumnModified(TigPairsPeer::PKEY)) $criteria->add(TigPairsPeer::PKEY, $this->pkey);
		if ($this->isColumnModified(TigPairsPeer::PVAL)) $criteria->add(TigPairsPeer::PVAL, $this->pval);
		if ($this->isColumnModified(TigPairsPeer::ID)) $criteria->add(TigPairsPeer::ID, $this->id);

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
		$criteria = new Criteria(TigPairsPeer::DATABASE_NAME);
		$criteria->add(TigPairsPeer::ID, $this->id);

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
	 * @param      object $copyObj An object of TigPairs (or compatible) type.
	 * @param      boolean $deepCopy Whether to also copy all rows that refer (by fkey) to the current row.
	 * @throws     PropelException
	 */
	public function copyInto($copyObj, $deepCopy = false)
	{
		$copyObj->setNid($this->nid);
		$copyObj->setUid($this->uid);
		$copyObj->setPkey($this->pkey);
		$copyObj->setPval($this->pval);

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
	 * @return     TigPairs Clone of current object.
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
	 * @return     TigPairsPeer
	 */
	public function getPeer()
	{
		if (self::$peer === null) {
			self::$peer = new TigPairsPeer();
		}
		return self::$peer;
	}

	/**
	 * Declares an association between this object and a TigNodes object.
	 *
	 * @param      TigNodes $v
	 * @return     TigPairs The current object (for fluent API support)
	 * @throws     PropelException
	 */
	public function setTigNodes(TigNodes $v = null)
	{
		if ($v === null) {
			$this->setNid(NULL);
		} else {
			$this->setNid($v->getNid());
		}

		$this->aTigNodes = $v;

		// Add binding for other direction of this n:n relationship.
		// If this object has already been added to the TigNodes object, it will not be re-added.
		if ($v !== null) {
			$v->addTigPairs($this);
		}

		return $this;
	}


	/**
	 * Get the associated TigNodes object
	 *
	 * @param      PropelPDO Optional Connection object.
	 * @return     TigNodes The associated TigNodes object.
	 * @throws     PropelException
	 */
	public function getTigNodes(PropelPDO $con = null)
	{
		if ($this->aTigNodes === null && (($this->nid !== "" && $this->nid !== null))) {
			$this->aTigNodes = TigNodesQuery::create()->findPk($this->nid, $con);
			/* The following can be used additionally to
			   guarantee the related object contains a reference
			   to this object.  This level of coupling may, however, be
			   undesirable since it could result in an only partially populated collection
			   in the referenced object.
			   $this->aTigNodes->addTigPairss($this);
			 */
		}
		return $this->aTigNodes;
	}

	/**
	 * Declares an association between this object and a TigUsers object.
	 *
	 * @param      TigUsers $v
	 * @return     TigPairs The current object (for fluent API support)
	 * @throws     PropelException
	 */
	public function setTigUsers(TigUsers $v = null)
	{
		if ($v === null) {
			$this->setUid(NULL);
		} else {
			$this->setUid($v->getUid());
		}

		$this->aTigUsers = $v;

		// Add binding for other direction of this n:n relationship.
		// If this object has already been added to the TigUsers object, it will not be re-added.
		if ($v !== null) {
			$v->addTigPairs($this);
		}

		return $this;
	}


	/**
	 * Get the associated TigUsers object
	 *
	 * @param      PropelPDO Optional Connection object.
	 * @return     TigUsers The associated TigUsers object.
	 * @throws     PropelException
	 */
	public function getTigUsers(PropelPDO $con = null)
	{
		if ($this->aTigUsers === null && (($this->uid !== "" && $this->uid !== null))) {
			$this->aTigUsers = TigUsersQuery::create()->findPk($this->uid, $con);
			/* The following can be used additionally to
			   guarantee the related object contains a reference
			   to this object.  This level of coupling may, however, be
			   undesirable since it could result in an only partially populated collection
			   in the referenced object.
			   $this->aTigUsers->addTigPairss($this);
			 */
		}
		return $this->aTigUsers;
	}

	/**
	 * Clears the current object and sets all attributes to their default values
	 */
	public function clear()
	{
		$this->nid = null;
		$this->uid = null;
		$this->pkey = null;
		$this->pval = null;
		$this->id = null;
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
		} // if ($deep)

		$this->aTigNodes = null;
		$this->aTigUsers = null;
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

} // BaseTigPairs
