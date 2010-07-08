<?php


abstract class BaseDataVisibility extends BaseObject  implements Persistent {


	
	protected static $peer;


	
	protected $username;


	
	protected $logdata;


	
	protected $datavisibility;

	
	protected $aRegistration;

	
	protected $alreadyInSave = false;

	
	protected $alreadyInValidation = false;

	
	public function getUsername()
	{

		return $this->username;
	}

	
	public function getLogdata()
	{

		return $this->logdata;
	}

	
	public function getDatavisibility()
	{

		return $this->datavisibility;
	}

	
	public function setUsername($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->username !== $v) {
			$this->username = $v;
			$this->modifiedColumns[] = DataVisibilityPeer::USERNAME;
		}

		if ($this->aRegistration !== null && $this->aRegistration->getUsername() !== $v) {
			$this->aRegistration = null;
		}

	} 
	
	public function setLogdata($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->logdata !== $v) {
			$this->logdata = $v;
			$this->modifiedColumns[] = DataVisibilityPeer::LOGDATA;
		}

	} 
	
	public function setDatavisibility($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->datavisibility !== $v) {
			$this->datavisibility = $v;
			$this->modifiedColumns[] = DataVisibilityPeer::DATAVISIBILITY;
		}

	} 
	
	public function hydrate(ResultSet $rs, $startcol = 1)
	{
		try {

			$this->username = $rs->getString($startcol + 0);

			$this->logdata = $rs->getInt($startcol + 1);

			$this->datavisibility = $rs->getInt($startcol + 2);

			$this->resetModified();

			$this->setNew(false);

						return $startcol + 3; 
		} catch (Exception $e) {
			throw new PropelException("Error populating DataVisibility object", $e);
		}
	}

	
	public function delete($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(DataVisibilityPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			DataVisibilityPeer::doDelete($this, $con);
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
			$con = Propel::getConnection(DataVisibilityPeer::DATABASE_NAME);
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
					$pk = DataVisibilityPeer::doInsert($this, $con);
					$affectedRows += 1; 										 										 
					$this->setNew(false);
				} else {
					$affectedRows += DataVisibilityPeer::doUpdate($this, $con);
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


			if (($retval = DataVisibilityPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}



			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = DataVisibilityPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->getByPosition($pos);
	}

	
	public function getByPosition($pos)
	{
		switch($pos) {
			case 0:
				return $this->getUsername();
				break;
			case 1:
				return $this->getLogdata();
				break;
			case 2:
				return $this->getDatavisibility();
				break;
			default:
				return null;
				break;
		} 	}

	
	public function toArray($keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = DataVisibilityPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getUsername(),
			$keys[1] => $this->getLogdata(),
			$keys[2] => $this->getDatavisibility(),
		);
		return $result;
	}

	
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = DataVisibilityPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->setByPosition($pos, $value);
	}

	
	public function setByPosition($pos, $value)
	{
		switch($pos) {
			case 0:
				$this->setUsername($value);
				break;
			case 1:
				$this->setLogdata($value);
				break;
			case 2:
				$this->setDatavisibility($value);
				break;
		} 	}

	
	public function fromArray($arr, $keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = DataVisibilityPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setUsername($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setLogdata($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setDatavisibility($arr[$keys[2]]);
	}

	
	public function buildCriteria()
	{
		$criteria = new Criteria(DataVisibilityPeer::DATABASE_NAME);

		if ($this->isColumnModified(DataVisibilityPeer::USERNAME)) $criteria->add(DataVisibilityPeer::USERNAME, $this->username);
		if ($this->isColumnModified(DataVisibilityPeer::LOGDATA)) $criteria->add(DataVisibilityPeer::LOGDATA, $this->logdata);
		if ($this->isColumnModified(DataVisibilityPeer::DATAVISIBILITY)) $criteria->add(DataVisibilityPeer::DATAVISIBILITY, $this->datavisibility);

		return $criteria;
	}

	
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(DataVisibilityPeer::DATABASE_NAME);

		$criteria->add(DataVisibilityPeer::USERNAME, $this->username);

		return $criteria;
	}

	
	public function getPrimaryKey()
	{
		return $this->getUsername();
	}

	
	public function setPrimaryKey($key)
	{
		$this->setUsername($key);
	}

	
	public function copyInto($copyObj, $deepCopy = false)
	{

		$copyObj->setLogdata($this->logdata);

		$copyObj->setDatavisibility($this->datavisibility);


		$copyObj->setNew(true);

		$copyObj->setUsername(NULL); 
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
			self::$peer = new DataVisibilityPeer();
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