<?php


abstract class BaseNetworkVisibility extends BaseObject  implements Persistent {


	
	protected static $peer;


	
	protected $id;


	
	protected $username;


	
	protected $user_data_option;


	
	protected $networkname;

	
	protected $aRegistration;

	
	protected $alreadyInSave = false;

	
	protected $alreadyInValidation = false;

	
	public function getId()
	{

		return $this->id;
	}

	
	public function getUsername()
	{

		return $this->username;
	}

	
	public function getUserDataOption()
	{

		return $this->user_data_option;
	}

	
	public function getNetworkname()
	{

		return $this->networkname;
	}

	
	public function setId($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->id !== $v) {
			$this->id = $v;
			$this->modifiedColumns[] = NetworkVisibilityPeer::ID;
		}

	} 
	
	public function setUsername($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->username !== $v) {
			$this->username = $v;
			$this->modifiedColumns[] = NetworkVisibilityPeer::USERNAME;
		}

		if ($this->aRegistration !== null && $this->aRegistration->getUsername() !== $v) {
			$this->aRegistration = null;
		}

	} 
	
	public function setUserDataOption($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->user_data_option !== $v) {
			$this->user_data_option = $v;
			$this->modifiedColumns[] = NetworkVisibilityPeer::USER_DATA_OPTION;
		}

	} 
	
	public function setNetworkname($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->networkname !== $v) {
			$this->networkname = $v;
			$this->modifiedColumns[] = NetworkVisibilityPeer::NETWORKNAME;
		}

	} 
	
	public function hydrate(ResultSet $rs, $startcol = 1)
	{
		try {

			$this->id = $rs->getInt($startcol + 0);

			$this->username = $rs->getString($startcol + 1);

			$this->user_data_option = $rs->getInt($startcol + 2);

			$this->networkname = $rs->getString($startcol + 3);

			$this->resetModified();

			$this->setNew(false);

						return $startcol + 4; 
		} catch (Exception $e) {
			throw new PropelException("Error populating NetworkVisibility object", $e);
		}
	}

	
	public function delete($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(NetworkVisibilityPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			NetworkVisibilityPeer::doDelete($this, $con);
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
			$con = Propel::getConnection(NetworkVisibilityPeer::DATABASE_NAME);
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
					$pk = NetworkVisibilityPeer::doInsert($this, $con);
					$affectedRows += 1; 										 										 
					$this->setId($pk);  
					$this->setNew(false);
				} else {
					$affectedRows += NetworkVisibilityPeer::doUpdate($this, $con);
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


			if (($retval = NetworkVisibilityPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}



			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = NetworkVisibilityPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->getByPosition($pos);
	}

	
	public function getByPosition($pos)
	{
		switch($pos) {
			case 0:
				return $this->getId();
				break;
			case 1:
				return $this->getUsername();
				break;
			case 2:
				return $this->getUserDataOption();
				break;
			case 3:
				return $this->getNetworkname();
				break;
			default:
				return null;
				break;
		} 	}

	
	public function toArray($keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = NetworkVisibilityPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getId(),
			$keys[1] => $this->getUsername(),
			$keys[2] => $this->getUserDataOption(),
			$keys[3] => $this->getNetworkname(),
		);
		return $result;
	}

	
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = NetworkVisibilityPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->setByPosition($pos, $value);
	}

	
	public function setByPosition($pos, $value)
	{
		switch($pos) {
			case 0:
				$this->setId($value);
				break;
			case 1:
				$this->setUsername($value);
				break;
			case 2:
				$this->setUserDataOption($value);
				break;
			case 3:
				$this->setNetworkname($value);
				break;
		} 	}

	
	public function fromArray($arr, $keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = NetworkVisibilityPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setId($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setUsername($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setUserDataOption($arr[$keys[2]]);
		if (array_key_exists($keys[3], $arr)) $this->setNetworkname($arr[$keys[3]]);
	}

	
	public function buildCriteria()
	{
		$criteria = new Criteria(NetworkVisibilityPeer::DATABASE_NAME);

		if ($this->isColumnModified(NetworkVisibilityPeer::ID)) $criteria->add(NetworkVisibilityPeer::ID, $this->id);
		if ($this->isColumnModified(NetworkVisibilityPeer::USERNAME)) $criteria->add(NetworkVisibilityPeer::USERNAME, $this->username);
		if ($this->isColumnModified(NetworkVisibilityPeer::USER_DATA_OPTION)) $criteria->add(NetworkVisibilityPeer::USER_DATA_OPTION, $this->user_data_option);
		if ($this->isColumnModified(NetworkVisibilityPeer::NETWORKNAME)) $criteria->add(NetworkVisibilityPeer::NETWORKNAME, $this->networkname);

		return $criteria;
	}

	
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(NetworkVisibilityPeer::DATABASE_NAME);

		$criteria->add(NetworkVisibilityPeer::ID, $this->id);

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

		$copyObj->setUserDataOption($this->user_data_option);

		$copyObj->setNetworkname($this->networkname);


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
			self::$peer = new NetworkVisibilityPeer();
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