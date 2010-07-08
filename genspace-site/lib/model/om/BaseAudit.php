<?php


abstract class BaseAudit extends BaseObject  implements Persistent {


	
	protected static $peer;


	
	protected $username;


	
	protected $action;


	
	protected $tablename;


	
	protected $beforevalue;


	
	protected $aftervalue;


	
	protected $time;


	
	protected $id;

	
	protected $aRegistration;

	
	protected $alreadyInSave = false;

	
	protected $alreadyInValidation = false;

	
	public function getUsername()
	{

		return $this->username;
	}

	
	public function getAction()
	{

		return $this->action;
	}

	
	public function getTablename()
	{

		return $this->tablename;
	}

	
	public function getBeforevalue()
	{

		return $this->beforevalue;
	}

	
	public function getAftervalue()
	{

		return $this->aftervalue;
	}

	
	public function getTime($format = 'Y-m-d H:i:s')
	{

		if ($this->time === null || $this->time === '') {
			return null;
		} elseif (!is_int($this->time)) {
						$ts = strtotime($this->time);
			if ($ts === -1 || $ts === false) { 				throw new PropelException("Unable to parse value of [time] as date/time value: " . var_export($this->time, true));
			}
		} else {
			$ts = $this->time;
		}
		if ($format === null) {
			return $ts;
		} elseif (strpos($format, '%') !== false) {
			return strftime($format, $ts);
		} else {
			return date($format, $ts);
		}
	}

	
	public function getId()
	{

		return $this->id;
	}

	
	public function setUsername($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->username !== $v) {
			$this->username = $v;
			$this->modifiedColumns[] = AuditPeer::USERNAME;
		}

		if ($this->aRegistration !== null && $this->aRegistration->getUsername() !== $v) {
			$this->aRegistration = null;
		}

	} 
	
	public function setAction($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->action !== $v) {
			$this->action = $v;
			$this->modifiedColumns[] = AuditPeer::ACTION;
		}

	} 
	
	public function setTablename($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->tablename !== $v) {
			$this->tablename = $v;
			$this->modifiedColumns[] = AuditPeer::TABLENAME;
		}

	} 
	
	public function setBeforevalue($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->beforevalue !== $v) {
			$this->beforevalue = $v;
			$this->modifiedColumns[] = AuditPeer::BEFOREVALUE;
		}

	} 
	
	public function setAftervalue($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->aftervalue !== $v) {
			$this->aftervalue = $v;
			$this->modifiedColumns[] = AuditPeer::AFTERVALUE;
		}

	} 
	
	public function setTime($v)
	{

		if ($v !== null && !is_int($v)) {
			$ts = strtotime($v);
			if ($ts === -1 || $ts === false) { 				throw new PropelException("Unable to parse date/time value for [time] from input: " . var_export($v, true));
			}
		} else {
			$ts = $v;
		}
		if ($this->time !== $ts) {
			$this->time = $ts;
			$this->modifiedColumns[] = AuditPeer::TIME;
		}

	} 
	
	public function setId($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->id !== $v) {
			$this->id = $v;
			$this->modifiedColumns[] = AuditPeer::ID;
		}

	} 
	
	public function hydrate(ResultSet $rs, $startcol = 1)
	{
		try {

			$this->username = $rs->getString($startcol + 0);

			$this->action = $rs->getString($startcol + 1);

			$this->tablename = $rs->getString($startcol + 2);

			$this->beforevalue = $rs->getString($startcol + 3);

			$this->aftervalue = $rs->getString($startcol + 4);

			$this->time = $rs->getTimestamp($startcol + 5, null);

			$this->id = $rs->getInt($startcol + 6);

			$this->resetModified();

			$this->setNew(false);

						return $startcol + 7; 
		} catch (Exception $e) {
			throw new PropelException("Error populating Audit object", $e);
		}
	}

	
	public function delete($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(AuditPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			AuditPeer::doDelete($this, $con);
			$this->setDeleted(true);
			$con->commit();
		} catch (PropelException $e) {
			$con->rollback();
			throw $e;
		}
	}

	
	public function save($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("You cannot save an object that has been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(AuditPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			$affectedRows = $this->doSave($con);
			$con->commit();
			return $affectedRows;
		} catch (PropelException $e) {
			$con->rollback();
			throw $e;
		}
	}

	
	protected function doSave($con)
	{
		$affectedRows = 0; 		if (!$this->alreadyInSave) {
			$this->alreadyInSave = true;


												
			if ($this->aRegistration !== null) {
				if ($this->aRegistration->isModified()) {
					$affectedRows += $this->aRegistration->save($con);
				}
				$this->setRegistration($this->aRegistration);
			}


						if ($this->isModified()) {
				if ($this->isNew()) {
					$pk = AuditPeer::doInsert($this, $con);
					$affectedRows += 1; 										 										 
					$this->setId($pk);  
					$this->setNew(false);
				} else {
					$affectedRows += AuditPeer::doUpdate($this, $con);
				}
				$this->resetModified(); 			}

			$this->alreadyInSave = false;
		}
		return $affectedRows;
	} 
	
	protected $validationFailures = array();

	
	public function getValidationFailures()
	{
		return $this->validationFailures;
	}

	
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

	
	protected function doValidate($columns = null)
	{
		if (!$this->alreadyInValidation) {
			$this->alreadyInValidation = true;
			$retval = null;

			$failureMap = array();


												
			if ($this->aRegistration !== null) {
				if (!$this->aRegistration->validate($columns)) {
					$failureMap = array_merge($failureMap, $this->aRegistration->getValidationFailures());
				}
			}


			if (($retval = AuditPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}



			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = AuditPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->getByPosition($pos);
	}

	
	public function getByPosition($pos)
	{
		switch($pos) {
			case 0:
				return $this->getUsername();
				break;
			case 1:
				return $this->getAction();
				break;
			case 2:
				return $this->getTablename();
				break;
			case 3:
				return $this->getBeforevalue();
				break;
			case 4:
				return $this->getAftervalue();
				break;
			case 5:
				return $this->getTime();
				break;
			case 6:
				return $this->getId();
				break;
			default:
				return null;
				break;
		} 	}

	
	public function toArray($keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = AuditPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getUsername(),
			$keys[1] => $this->getAction(),
			$keys[2] => $this->getTablename(),
			$keys[3] => $this->getBeforevalue(),
			$keys[4] => $this->getAftervalue(),
			$keys[5] => $this->getTime(),
			$keys[6] => $this->getId(),
		);
		return $result;
	}

	
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = AuditPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->setByPosition($pos, $value);
	}

	
	public function setByPosition($pos, $value)
	{
		switch($pos) {
			case 0:
				$this->setUsername($value);
				break;
			case 1:
				$this->setAction($value);
				break;
			case 2:
				$this->setTablename($value);
				break;
			case 3:
				$this->setBeforevalue($value);
				break;
			case 4:
				$this->setAftervalue($value);
				break;
			case 5:
				$this->setTime($value);
				break;
			case 6:
				$this->setId($value);
				break;
		} 	}

	
	public function fromArray($arr, $keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = AuditPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setUsername($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setAction($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setTablename($arr[$keys[2]]);
		if (array_key_exists($keys[3], $arr)) $this->setBeforevalue($arr[$keys[3]]);
		if (array_key_exists($keys[4], $arr)) $this->setAftervalue($arr[$keys[4]]);
		if (array_key_exists($keys[5], $arr)) $this->setTime($arr[$keys[5]]);
		if (array_key_exists($keys[6], $arr)) $this->setId($arr[$keys[6]]);
	}

	
	public function buildCriteria()
	{
		$criteria = new Criteria(AuditPeer::DATABASE_NAME);

		if ($this->isColumnModified(AuditPeer::USERNAME)) $criteria->add(AuditPeer::USERNAME, $this->username);
		if ($this->isColumnModified(AuditPeer::ACTION)) $criteria->add(AuditPeer::ACTION, $this->action);
		if ($this->isColumnModified(AuditPeer::TABLENAME)) $criteria->add(AuditPeer::TABLENAME, $this->tablename);
		if ($this->isColumnModified(AuditPeer::BEFOREVALUE)) $criteria->add(AuditPeer::BEFOREVALUE, $this->beforevalue);
		if ($this->isColumnModified(AuditPeer::AFTERVALUE)) $criteria->add(AuditPeer::AFTERVALUE, $this->aftervalue);
		if ($this->isColumnModified(AuditPeer::TIME)) $criteria->add(AuditPeer::TIME, $this->time);
		if ($this->isColumnModified(AuditPeer::ID)) $criteria->add(AuditPeer::ID, $this->id);

		return $criteria;
	}

	
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(AuditPeer::DATABASE_NAME);

		$criteria->add(AuditPeer::ID, $this->id);

		return $criteria;
	}

	
	public function getPrimaryKey()
	{
		return $this->getId();
	}

	
	public function setPrimaryKey($key)
	{
		$this->setId($key);
	}

	
	public function copyInto($copyObj, $deepCopy = false)
	{

		$copyObj->setUsername($this->username);

		$copyObj->setAction($this->action);

		$copyObj->setTablename($this->tablename);

		$copyObj->setBeforevalue($this->beforevalue);

		$copyObj->setAftervalue($this->aftervalue);

		$copyObj->setTime($this->time);


		$copyObj->setNew(true);

		$copyObj->setId(NULL); 
	}

	
	public function copy($deepCopy = false)
	{
				$clazz = get_class($this);
		$copyObj = new $clazz();
		$this->copyInto($copyObj, $deepCopy);
		return $copyObj;
	}

	
	public function getPeer()
	{
		if (self::$peer === null) {
			self::$peer = new AuditPeer();
		}
		return self::$peer;
	}

	
	public function setRegistration($v)
	{


		if ($v === null) {
			$this->setUsername(NULL);
		} else {
			$this->setUsername($v->getUsername());
		}


		$this->aRegistration = $v;
	}


	
	public function getRegistration($con = null)
	{
		if ($this->aRegistration === null && (($this->username !== "" && $this->username !== null))) {
						$this->aRegistration = RegistrationPeer::retrieveByPK($this->username, $con);

			
		}
		return $this->aRegistration;
	}

} 