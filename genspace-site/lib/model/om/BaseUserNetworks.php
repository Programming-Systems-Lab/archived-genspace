<?php


abstract class BaseUserNetworks extends BaseObject  implements Persistent {


	
	protected static $peer;


	
	protected $username;


	
	protected $network;

	
	protected $aUserNetworksRelatedByUsername;

	
	protected $aUserNetworksRelatedByNetwork;

	
	protected $collUserNetworkssRelatedByUsername;

	
	protected $lastUserNetworksRelatedByUsernameCriteria = null;

	
	protected $collUserNetworkssRelatedByNetwork;

	
	protected $lastUserNetworksRelatedByNetworkCriteria = null;

	
	protected $alreadyInSave = false;

	
	protected $alreadyInValidation = false;

	
	public function getUsername()
	{

		return $this->username;
	}

	
	public function getNetwork()
	{

		return $this->network;
	}

	
	public function setUsername($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->username !== $v) {
			$this->username = $v;
			$this->modifiedColumns[] = UserNetworksPeer::USERNAME;
		}

		if ($this->aUserNetworksRelatedByUsername !== null && $this->aUserNetworksRelatedByUsername->getNetwork() !== $v) {
			$this->aUserNetworksRelatedByUsername = null;
		}

	} 
	
	public function setNetwork($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->network !== $v) {
			$this->network = $v;
			$this->modifiedColumns[] = UserNetworksPeer::NETWORK;
		}

		if ($this->aUserNetworksRelatedByNetwork !== null && $this->aUserNetworksRelatedByNetwork->getNetwork() !== $v) {
			$this->aUserNetworksRelatedByNetwork = null;
		}

	} 
	
	public function hydrate(ResultSet $rs, $startcol = 1)
	{
		try {

			$this->username = $rs->getString($startcol + 0);

			$this->network = $rs->getString($startcol + 1);

			$this->resetModified();

			$this->setNew(false);

						return $startcol + 2; 
		} catch (Exception $e) {
			throw new PropelException("Error populating UserNetworks object", $e);
		}
	}

	
	public function delete($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(UserNetworksPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			UserNetworksPeer::doDelete($this, $con);
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
			$con = Propel::getConnection(UserNetworksPeer::DATABASE_NAME);
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


												
			if ($this->aUserNetworksRelatedByUsername !== null) {
				if ($this->aUserNetworksRelatedByUsername->isModified()) {
					$affectedRows += $this->aUserNetworksRelatedByUsername->save($con);
				}
				$this->setUserNetworksRelatedByUsername($this->aUserNetworksRelatedByUsername);
			}

			if ($this->aUserNetworksRelatedByNetwork !== null) {
				if ($this->aUserNetworksRelatedByNetwork->isModified()) {
					$affectedRows += $this->aUserNetworksRelatedByNetwork->save($con);
				}
				$this->setUserNetworksRelatedByNetwork($this->aUserNetworksRelatedByNetwork);
			}


						if ($this->isModified()) {
				if ($this->isNew()) {
					$pk = UserNetworksPeer::doInsert($this, $con);
					$affectedRows += 1; 										 										 
					$this->setNew(false);
				} else {
					$affectedRows += UserNetworksPeer::doUpdate($this, $con);
				}
				$this->resetModified(); 			}

			if ($this->collUserNetworkssRelatedByUsername !== null) {
				foreach($this->collUserNetworkssRelatedByUsername as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collUserNetworkssRelatedByNetwork !== null) {
				foreach($this->collUserNetworkssRelatedByNetwork as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

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


												
			if ($this->aUserNetworksRelatedByUsername !== null) {
				if (!$this->aUserNetworksRelatedByUsername->validate($columns)) {
					$failureMap = array_merge($failureMap, $this->aUserNetworksRelatedByUsername->getValidationFailures());
				}
			}

			if ($this->aUserNetworksRelatedByNetwork !== null) {
				if (!$this->aUserNetworksRelatedByNetwork->validate($columns)) {
					$failureMap = array_merge($failureMap, $this->aUserNetworksRelatedByNetwork->getValidationFailures());
				}
			}


			if (($retval = UserNetworksPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}



			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = UserNetworksPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->getByPosition($pos);
	}

	
	public function getByPosition($pos)
	{
		switch($pos) {
			case 0:
				return $this->getUsername();
				break;
			case 1:
				return $this->getNetwork();
				break;
			default:
				return null;
				break;
		} 	}

	
	public function toArray($keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = UserNetworksPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getUsername(),
			$keys[1] => $this->getNetwork(),
		);
		return $result;
	}

	
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = UserNetworksPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->setByPosition($pos, $value);
	}

	
	public function setByPosition($pos, $value)
	{
		switch($pos) {
			case 0:
				$this->setUsername($value);
				break;
			case 1:
				$this->setNetwork($value);
				break;
		} 	}

	
	public function fromArray($arr, $keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = UserNetworksPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setUsername($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setNetwork($arr[$keys[1]]);
	}

	
	public function buildCriteria()
	{
		$criteria = new Criteria(UserNetworksPeer::DATABASE_NAME);

		if ($this->isColumnModified(UserNetworksPeer::USERNAME)) $criteria->add(UserNetworksPeer::USERNAME, $this->username);
		if ($this->isColumnModified(UserNetworksPeer::NETWORK)) $criteria->add(UserNetworksPeer::NETWORK, $this->network);

		return $criteria;
	}

	
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(UserNetworksPeer::DATABASE_NAME);

		$criteria->add(UserNetworksPeer::USERNAME, $this->username);
		$criteria->add(UserNetworksPeer::NETWORK, $this->network);

		return $criteria;
	}

	
	public function getPrimaryKey()
	{
		$pks = array();

		$pks[0] = $this->getUsername();

		$pks[1] = $this->getNetwork();

		return $pks;
	}

	
	public function setPrimaryKey($keys)
	{

		$this->setUsername($keys[0]);

		$this->setNetwork($keys[1]);

	}

	
	public function copyInto($copyObj, $deepCopy = false)
	{


		if ($deepCopy) {
									$copyObj->setNew(false);

			foreach($this->getUserNetworkssRelatedByUsername() as $relObj) {
				if($this->getPrimaryKey() === $relObj->getPrimaryKey()) {
						continue;
				}

				$copyObj->addUserNetworksRelatedByUsername($relObj->copy($deepCopy));
			}

			foreach($this->getUserNetworkssRelatedByNetwork() as $relObj) {
				if($this->getPrimaryKey() === $relObj->getPrimaryKey()) {
						continue;
				}

				$copyObj->addUserNetworksRelatedByNetwork($relObj->copy($deepCopy));
			}

		} 

		$copyObj->setNew(true);

		$copyObj->setUsername(NULL); 
		$copyObj->setNetwork(NULL); 
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
			self::$peer = new UserNetworksPeer();
		}
		return self::$peer;
	}

	
	public function setUserNetworksRelatedByUsername($v)
	{


		if ($v === null) {
			$this->setUsername(NULL);
		} else {
			$this->setUsername($v->getNetwork());
		}


		$this->aUserNetworksRelatedByUsername = $v;
	}


	
	public function getUserNetworksRelatedByUsername($con = null)
	{
		if ($this->aUserNetworksRelatedByUsername === null && (($this->username !== "" && $this->username !== null))) {
						$this->aUserNetworksRelatedByUsername = UserNetworksPeer::retrieveByPK($this->username, $con);

			
		}
		return $this->aUserNetworksRelatedByUsername;
	}

	
	public function setUserNetworksRelatedByNetwork($v)
	{


		if ($v === null) {
			$this->setNetwork(NULL);
		} else {
			$this->setNetwork($v->getNetwork());
		}


		$this->aUserNetworksRelatedByNetwork = $v;
	}


	
	public function getUserNetworksRelatedByNetwork($con = null)
	{
		if ($this->aUserNetworksRelatedByNetwork === null && (($this->network !== "" && $this->network !== null))) {
						$this->aUserNetworksRelatedByNetwork = UserNetworksPeer::retrieveByPK($this->network, $con);

			
		}
		return $this->aUserNetworksRelatedByNetwork;
	}

	
	public function initUserNetworkssRelatedByUsername()
	{
		if ($this->collUserNetworkssRelatedByUsername === null) {
			$this->collUserNetworkssRelatedByUsername = array();
		}
	}

	
	public function getUserNetworkssRelatedByUsername($criteria = null, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		if ($this->collUserNetworkssRelatedByUsername === null) {
			if ($this->isNew()) {
			   $this->collUserNetworkssRelatedByUsername = array();
			} else {

				$criteria->add(UserNetworksPeer::USERNAME, $this->getNetwork());

				UserNetworksPeer::addSelectColumns($criteria);
				$this->collUserNetworkssRelatedByUsername = UserNetworksPeer::doSelect($criteria, $con);
			}
		} else {
						if (!$this->isNew()) {
												

				$criteria->add(UserNetworksPeer::USERNAME, $this->getNetwork());

				UserNetworksPeer::addSelectColumns($criteria);
				if (!isset($this->lastUserNetworksRelatedByUsernameCriteria) || !$this->lastUserNetworksRelatedByUsernameCriteria->equals($criteria)) {
					$this->collUserNetworkssRelatedByUsername = UserNetworksPeer::doSelect($criteria, $con);
				}
			}
		}
		$this->lastUserNetworksRelatedByUsernameCriteria = $criteria;
		return $this->collUserNetworkssRelatedByUsername;
	}

	
	public function countUserNetworkssRelatedByUsername($criteria = null, $distinct = false, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		$criteria->add(UserNetworksPeer::USERNAME, $this->getNetwork());

		return UserNetworksPeer::doCount($criteria, $distinct, $con);
	}

	
	public function addUserNetworksRelatedByUsername(UserNetworks $l)
	{
		$this->collUserNetworkssRelatedByUsername[] = $l;
		$l->setUserNetworksRelatedByUsername($this);
	}

	
	public function initUserNetworkssRelatedByNetwork()
	{
		if ($this->collUserNetworkssRelatedByNetwork === null) {
			$this->collUserNetworkssRelatedByNetwork = array();
		}
	}

	
	public function getUserNetworkssRelatedByNetwork($criteria = null, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		if ($this->collUserNetworkssRelatedByNetwork === null) {
			if ($this->isNew()) {
			   $this->collUserNetworkssRelatedByNetwork = array();
			} else {

				$criteria->add(UserNetworksPeer::NETWORK, $this->getNetwork());

				UserNetworksPeer::addSelectColumns($criteria);
				$this->collUserNetworkssRelatedByNetwork = UserNetworksPeer::doSelect($criteria, $con);
			}
		} else {
						if (!$this->isNew()) {
												

				$criteria->add(UserNetworksPeer::NETWORK, $this->getNetwork());

				UserNetworksPeer::addSelectColumns($criteria);
				if (!isset($this->lastUserNetworksRelatedByNetworkCriteria) || !$this->lastUserNetworksRelatedByNetworkCriteria->equals($criteria)) {
					$this->collUserNetworkssRelatedByNetwork = UserNetworksPeer::doSelect($criteria, $con);
				}
			}
		}
		$this->lastUserNetworksRelatedByNetworkCriteria = $criteria;
		return $this->collUserNetworkssRelatedByNetwork;
	}

	
	public function countUserNetworkssRelatedByNetwork($criteria = null, $distinct = false, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		$criteria->add(UserNetworksPeer::NETWORK, $this->getNetwork());

		return UserNetworksPeer::doCount($criteria, $distinct, $con);
	}

	
	public function addUserNetworksRelatedByNetwork(UserNetworks $l)
	{
		$this->collUserNetworkssRelatedByNetwork[] = $l;
		$l->setUserNetworksRelatedByNetwork($this);
	}

} 