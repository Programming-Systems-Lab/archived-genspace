<?php


abstract class BaseInbox extends BaseObject  implements Persistent {


	
	protected static $peer;


	
	protected $messageid;


	
	protected $date;


	
	protected $fromuser;


	
	protected $touser;


	
	protected $message;

	
	protected $aRegistrationRelatedByFromuser;

	
	protected $aRegistrationRelatedByTouser;

	
	protected $alreadyInSave = false;

	
	protected $alreadyInValidation = false;

	
	public function getMessageid()
	{

		return $this->messageid;
	}

	
	public function getDate($format = 'Y-m-d H:i:s')
	{

		if ($this->date === null || $this->date === '') {
			return null;
		} elseif (!is_int($this->date)) {
						$ts = strtotime($this->date);
			if ($ts === -1 || $ts === false) { 				throw new PropelException("Unable to parse value of [date] as date/time value: " . var_export($this->date, true));
			}
		} else {
			$ts = $this->date;
		}
		if ($format === null) {
			return $ts;
		} elseif (strpos($format, '%') !== false) {
			return strftime($format, $ts);
		} else {
			return date($format, $ts);
		}
	}

	
	public function getFromuser()
	{

		return $this->fromuser;
	}

	
	public function getTouser()
	{

		return $this->touser;
	}

	
	public function getMessage()
	{

		return $this->message;
	}

	
	public function setMessageid($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->messageid !== $v) {
			$this->messageid = $v;
			$this->modifiedColumns[] = InboxPeer::MESSAGEID;
		}

	} 
	
	public function setDate($v)
	{

		if ($v !== null && !is_int($v)) {
			$ts = strtotime($v);
			if ($ts === -1 || $ts === false) { 				throw new PropelException("Unable to parse date/time value for [date] from input: " . var_export($v, true));
			}
		} else {
			$ts = $v;
		}
		if ($this->date !== $ts) {
			$this->date = $ts;
			$this->modifiedColumns[] = InboxPeer::DATE;
		}

	} 
	
	public function setFromuser($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->fromuser !== $v) {
			$this->fromuser = $v;
			$this->modifiedColumns[] = InboxPeer::FROMUSER;
		}

		if ($this->aRegistrationRelatedByFromuser !== null && $this->aRegistrationRelatedByFromuser->getUsername() !== $v) {
			$this->aRegistrationRelatedByFromuser = null;
		}

	} 
	
	public function setTouser($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->touser !== $v) {
			$this->touser = $v;
			$this->modifiedColumns[] = InboxPeer::TOUSER;
		}

		if ($this->aRegistrationRelatedByTouser !== null && $this->aRegistrationRelatedByTouser->getUsername() !== $v) {
			$this->aRegistrationRelatedByTouser = null;
		}

	} 
	
	public function setMessage($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->message !== $v) {
			$this->message = $v;
			$this->modifiedColumns[] = InboxPeer::MESSAGE;
		}

	} 
	
	public function hydrate(ResultSet $rs, $startcol = 1)
	{
		try {

			$this->messageid = $rs->getInt($startcol + 0);

			$this->date = $rs->getTimestamp($startcol + 1, null);

			$this->fromuser = $rs->getString($startcol + 2);

			$this->touser = $rs->getString($startcol + 3);

			$this->message = $rs->getString($startcol + 4);

			$this->resetModified();

			$this->setNew(false);

						return $startcol + 5; 
		} catch (Exception $e) {
			throw new PropelException("Error populating Inbox object", $e);
		}
	}

	
	public function delete($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(InboxPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			InboxPeer::doDelete($this, $con);
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
			$con = Propel::getConnection(InboxPeer::DATABASE_NAME);
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


												
			if ($this->aRegistrationRelatedByFromuser !== null) {
				if ($this->aRegistrationRelatedByFromuser->isModified()) {
					$affectedRows += $this->aRegistrationRelatedByFromuser->save($con);
				}
				$this->setRegistrationRelatedByFromuser($this->aRegistrationRelatedByFromuser);
			}

			if ($this->aRegistrationRelatedByTouser !== null) {
				if ($this->aRegistrationRelatedByTouser->isModified()) {
					$affectedRows += $this->aRegistrationRelatedByTouser->save($con);
				}
				$this->setRegistrationRelatedByTouser($this->aRegistrationRelatedByTouser);
			}


						if ($this->isModified()) {
				if ($this->isNew()) {
					$pk = InboxPeer::doInsert($this, $con);
					$affectedRows += 1; 										 										 
					$this->setMessageid($pk);  
					$this->setNew(false);
				} else {
					$affectedRows += InboxPeer::doUpdate($this, $con);
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


												
			if ($this->aRegistrationRelatedByFromuser !== null) {
				if (!$this->aRegistrationRelatedByFromuser->validate($columns)) {
					$failureMap = array_merge($failureMap, $this->aRegistrationRelatedByFromuser->getValidationFailures());
				}
			}

			if ($this->aRegistrationRelatedByTouser !== null) {
				if (!$this->aRegistrationRelatedByTouser->validate($columns)) {
					$failureMap = array_merge($failureMap, $this->aRegistrationRelatedByTouser->getValidationFailures());
				}
			}


			if (($retval = InboxPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}



			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = InboxPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->getByPosition($pos);
	}

	
	public function getByPosition($pos)
	{
		switch($pos) {
			case 0:
				return $this->getMessageid();
				break;
			case 1:
				return $this->getDate();
				break;
			case 2:
				return $this->getFromuser();
				break;
			case 3:
				return $this->getTouser();
				break;
			case 4:
				return $this->getMessage();
				break;
			default:
				return null;
				break;
		} 	}

	
	public function toArray($keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = InboxPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getMessageid(),
			$keys[1] => $this->getDate(),
			$keys[2] => $this->getFromuser(),
			$keys[3] => $this->getTouser(),
			$keys[4] => $this->getMessage(),
		);
		return $result;
	}

	
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = InboxPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->setByPosition($pos, $value);
	}

	
	public function setByPosition($pos, $value)
	{
		switch($pos) {
			case 0:
				$this->setMessageid($value);
				break;
			case 1:
				$this->setDate($value);
				break;
			case 2:
				$this->setFromuser($value);
				break;
			case 3:
				$this->setTouser($value);
				break;
			case 4:
				$this->setMessage($value);
				break;
		} 	}

	
	public function fromArray($arr, $keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = InboxPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setMessageid($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setDate($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setFromuser($arr[$keys[2]]);
		if (array_key_exists($keys[3], $arr)) $this->setTouser($arr[$keys[3]]);
		if (array_key_exists($keys[4], $arr)) $this->setMessage($arr[$keys[4]]);
	}

	
	public function buildCriteria()
	{
		$criteria = new Criteria(InboxPeer::DATABASE_NAME);

		if ($this->isColumnModified(InboxPeer::MESSAGEID)) $criteria->add(InboxPeer::MESSAGEID, $this->messageid);
		if ($this->isColumnModified(InboxPeer::DATE)) $criteria->add(InboxPeer::DATE, $this->date);
		if ($this->isColumnModified(InboxPeer::FROMUSER)) $criteria->add(InboxPeer::FROMUSER, $this->fromuser);
		if ($this->isColumnModified(InboxPeer::TOUSER)) $criteria->add(InboxPeer::TOUSER, $this->touser);
		if ($this->isColumnModified(InboxPeer::MESSAGE)) $criteria->add(InboxPeer::MESSAGE, $this->message);

		return $criteria;
	}

	
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(InboxPeer::DATABASE_NAME);

		$criteria->add(InboxPeer::MESSAGEID, $this->messageid);

		return $criteria;
	}

	
	public function getPrimaryKey()
	{
		return $this->getMessageid();
	}

	
	public function setPrimaryKey($key)
	{
		$this->setMessageid($key);
	}

	
	public function copyInto($copyObj, $deepCopy = false)
	{

		$copyObj->setDate($this->date);

		$copyObj->setFromuser($this->fromuser);

		$copyObj->setTouser($this->touser);

		$copyObj->setMessage($this->message);


		$copyObj->setNew(true);

		$copyObj->setMessageid(NULL); 
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
			self::$peer = new InboxPeer();
		}
		return self::$peer;
	}

	
	public function setRegistrationRelatedByFromuser($v)
	{


		if ($v === null) {
			$this->setFromuser(NULL);
		} else {
			$this->setFromuser($v->getUsername());
		}


		$this->aRegistrationRelatedByFromuser = $v;
	}


	
	public function getRegistrationRelatedByFromuser($con = null)
	{
		if ($this->aRegistrationRelatedByFromuser === null && (($this->fromuser !== "" && $this->fromuser !== null))) {
						$this->aRegistrationRelatedByFromuser = RegistrationPeer::retrieveByPK($this->fromuser, $con);

			
		}
		return $this->aRegistrationRelatedByFromuser;
	}

	
	public function setRegistrationRelatedByTouser($v)
	{


		if ($v === null) {
			$this->setTouser(NULL);
		} else {
			$this->setTouser($v->getUsername());
		}


		$this->aRegistrationRelatedByTouser = $v;
	}


	
	public function getRegistrationRelatedByTouser($con = null)
	{
		if ($this->aRegistrationRelatedByTouser === null && (($this->touser !== "" && $this->touser !== null))) {
						$this->aRegistrationRelatedByTouser = RegistrationPeer::retrieveByPK($this->touser, $con);

			
		}
		return $this->aRegistrationRelatedByTouser;
	}

} 