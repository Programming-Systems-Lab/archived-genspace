<?php


abstract class BaseUserVisibility extends BaseObject  implements Persistent {


	
	protected static $peer;


	
	protected $username;


	
	protected $uservisibility;


	
	protected $id;

	
	protected $aRegistration;

	
	protected $alreadyInSave = false;

	
	protected $alreadyInValidation = false;

	
	public function getUsername()
	{

		return $this->username;
	}

	
	public function getUservisibility()
	{

		return $this->uservisibility;
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
			$this->modifiedColumns[] = UserVisibilityPeer::USERNAME;
		}

		if ($this->aRegistration !== null && $this->aRegistration->getUsername() !== $v) {
			$this->aRegistration = null;
		}

	} 
	
	public function setUservisibility($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->uservisibility !== $v) {
			$this->uservisibility = $v;
			$this->modifiedColumns[] = UserVisibilityPeer::USERVISIBILITY;
		}

	} 
	
	public function setId($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->id !== $v) {
			$this->id = $v;
			$this->modifiedColumns[] = UserVisibilityPeer::ID;
		}

	} 
	
	public function hydrate(ResultSet $rs, $startcol = 1)
	{
		try {

			$this->username = $rs->getString($startcol + 0);

			$this->uservisibility = $rs->getInt($startcol + 1);

			$this->id = $rs->getInt($startcol + 2);

			$this->resetModified();

			$this->setNew(false);

						return $startcol + 3; 
		} catch (Exception $e) {
			throw new PropelException("Error populating UserVisibility object", $e);
		}
	}

	
	public function delete($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(UserVisibilityPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			UserVisibilityPeer::doDelete($this, $con);
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
			$con = Propel::getConnection(UserVisibilityPeer::DATABASE_NAME);
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
					$pk = UserVisibilityPeer::doInsert($this, $con);
					$affectedRows += 1; 										 										 
					$this->setId($pk);  
					$this->setNew(false);
				} else {
					$affectedRows += UserVisibilityPeer::doUpdate($this, $con);
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


			if (($retval = UserVisibilityPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}



			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = UserVisibilityPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->getByPosition($pos);
	}

	
	public function getByPosition($pos)
	{
		switch($pos) {
			case 0:
				return $this->getUsername();
				break;
			case 1:
				return $this->getUservisibility();
				break;
			case 2:
				return $this->getId();
				break;
			default:
				return null;
				break;
		} 	}

	
	public function toArray($keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = UserVisibilityPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getUsername(),
			$keys[1] => $this->getUservisibility(),
			$keys[2] => $this->getId(),
		);
		return $result;
	}

	
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = UserVisibilityPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->setByPosition($pos, $value);
	}

	
	public function setByPosition($pos, $value)
	{
		switch($pos) {
			case 0:
				$this->setUsername($value);
				break;
			case 1:
				$this->setUservisibility($value);
				break;
			case 2:
				$this->setId($value);
				break;
		} 	}

	
	public function fromArray($arr, $keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = UserVisibilityPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setUsername($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setUservisibility($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setId($arr[$keys[2]]);
	}

	
	public function buildCriteria()
	{
		$criteria = new Criteria(UserVisibilityPeer::DATABASE_NAME);

		if ($this->isColumnModified(UserVisibilityPeer::USERNAME)) $criteria->add(UserVisibilityPeer::USERNAME, $this->username);
		if ($this->isColumnModified(UserVisibilityPeer::USERVISIBILITY)) $criteria->add(UserVisibilityPeer::USERVISIBILITY, $this->uservisibility);
		if ($this->isColumnModified(UserVisibilityPeer::ID)) $criteria->add(UserVisibilityPeer::ID, $this->id);

		return $criteria;
	}

	
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(UserVisibilityPeer::DATABASE_NAME);

		$criteria->add(UserVisibilityPeer::ID, $this->id);

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

		$copyObj->setUservisibility($this->uservisibility);


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
			self::$peer = new UserVisibilityPeer();
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