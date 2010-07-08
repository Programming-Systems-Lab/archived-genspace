<?php


abstract class BaseRegistration extends BaseObject  implements Persistent {


	
	protected static $peer;


	
	protected $username;


	
	protected $password;


	
	protected $email;


	
	protected $im_email;


	
	protected $im_password;


	
	protected $first_name;


	
	protected $last_name;


	
	protected $work_title;


	
	protected $phone;


	
	protected $lab_affiliation;


	
	protected $addr1;


	
	protected $addr2;


	
	protected $city;


	
	protected $state;


	
	protected $zipcode;

	
	protected $collDataVisibilitys;

	
	protected $lastDataVisibilityCriteria = null;

	
	protected $collUserVisibilitys;

	
	protected $lastUserVisibilityCriteria = null;

	
	protected $collAudits;

	
	protected $lastAuditCriteria = null;

	
	protected $collNetworkVisibilitys;

	
	protected $lastNetworkVisibilityCriteria = null;

	
	protected $collOutboxsRelatedByFromuser;

	
	protected $lastOutboxRelatedByFromuserCriteria = null;

	
	protected $collOutboxsRelatedByTouser;

	
	protected $lastOutboxRelatedByTouserCriteria = null;

	
	protected $collInboxsRelatedByFromuser;

	
	protected $lastInboxRelatedByFromuserCriteria = null;

	
	protected $collInboxsRelatedByTouser;

	
	protected $lastInboxRelatedByTouserCriteria = null;

	
	protected $alreadyInSave = false;

	
	protected $alreadyInValidation = false;

	
	public function getUsername()
	{

		return $this->username;
	}

	
	public function getPassword()
	{

		return $this->password;
	}

	
	public function getEmail()
	{

		return $this->email;
	}

	
	public function getImEmail()
	{

		return $this->im_email;
	}

	
	public function getImPassword()
	{

		return $this->im_password;
	}

	
	public function getFirstName()
	{

		return $this->first_name;
	}

	
	public function getLastName()
	{

		return $this->last_name;
	}

	
	public function getWorkTitle()
	{

		return $this->work_title;
	}

	
	public function getPhone()
	{

		return $this->phone;
	}

	
	public function getLabAffiliation()
	{

		return $this->lab_affiliation;
	}

	
	public function getAddr1()
	{

		return $this->addr1;
	}

	
	public function getAddr2()
	{

		return $this->addr2;
	}

	
	public function getCity()
	{

		return $this->city;
	}

	
	public function getState()
	{

		return $this->state;
	}

	
	public function getZipcode()
	{

		return $this->zipcode;
	}

	
	public function setUsername($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->username !== $v) {
			$this->username = $v;
			$this->modifiedColumns[] = RegistrationPeer::USERNAME;
		}

	} 
	
	public function setPassword($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->password !== $v) {
			$this->password = $v;
			$this->modifiedColumns[] = RegistrationPeer::PASSWORD;
		}

	} 
	
	public function setEmail($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->email !== $v) {
			$this->email = $v;
			$this->modifiedColumns[] = RegistrationPeer::EMAIL;
		}

	} 
	
	public function setImEmail($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->im_email !== $v) {
			$this->im_email = $v;
			$this->modifiedColumns[] = RegistrationPeer::IM_EMAIL;
		}

	} 
	
	public function setImPassword($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->im_password !== $v) {
			$this->im_password = $v;
			$this->modifiedColumns[] = RegistrationPeer::IM_PASSWORD;
		}

	} 
	
	public function setFirstName($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->first_name !== $v) {
			$this->first_name = $v;
			$this->modifiedColumns[] = RegistrationPeer::FIRST_NAME;
		}

	} 
	
	public function setLastName($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->last_name !== $v) {
			$this->last_name = $v;
			$this->modifiedColumns[] = RegistrationPeer::LAST_NAME;
		}

	} 
	
	public function setWorkTitle($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->work_title !== $v) {
			$this->work_title = $v;
			$this->modifiedColumns[] = RegistrationPeer::WORK_TITLE;
		}

	} 
	
	public function setPhone($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->phone !== $v) {
			$this->phone = $v;
			$this->modifiedColumns[] = RegistrationPeer::PHONE;
		}

	} 
	
	public function setLabAffiliation($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->lab_affiliation !== $v) {
			$this->lab_affiliation = $v;
			$this->modifiedColumns[] = RegistrationPeer::LAB_AFFILIATION;
		}

	} 
	
	public function setAddr1($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->addr1 !== $v) {
			$this->addr1 = $v;
			$this->modifiedColumns[] = RegistrationPeer::ADDR1;
		}

	} 
	
	public function setAddr2($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->addr2 !== $v) {
			$this->addr2 = $v;
			$this->modifiedColumns[] = RegistrationPeer::ADDR2;
		}

	} 
	
	public function setCity($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->city !== $v) {
			$this->city = $v;
			$this->modifiedColumns[] = RegistrationPeer::CITY;
		}

	} 
	
	public function setState($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->state !== $v) {
			$this->state = $v;
			$this->modifiedColumns[] = RegistrationPeer::STATE;
		}

	} 
	
	public function setZipcode($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->zipcode !== $v) {
			$this->zipcode = $v;
			$this->modifiedColumns[] = RegistrationPeer::ZIPCODE;
		}

	} 
	
	public function hydrate(ResultSet $rs, $startcol = 1)
	{
		try {

			$this->username = $rs->getString($startcol + 0);

			$this->password = $rs->getString($startcol + 1);

			$this->email = $rs->getString($startcol + 2);

			$this->im_email = $rs->getString($startcol + 3);

			$this->im_password = $rs->getString($startcol + 4);

			$this->first_name = $rs->getString($startcol + 5);

			$this->last_name = $rs->getString($startcol + 6);

			$this->work_title = $rs->getString($startcol + 7);

			$this->phone = $rs->getString($startcol + 8);

			$this->lab_affiliation = $rs->getString($startcol + 9);

			$this->addr1 = $rs->getString($startcol + 10);

			$this->addr2 = $rs->getString($startcol + 11);

			$this->city = $rs->getString($startcol + 12);

			$this->state = $rs->getString($startcol + 13);

			$this->zipcode = $rs->getString($startcol + 14);

			$this->resetModified();

			$this->setNew(false);

						return $startcol + 15; 
		} catch (Exception $e) {
			throw new PropelException("Error populating Registration object", $e);
		}
	}

	
	public function delete($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(RegistrationPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			RegistrationPeer::doDelete($this, $con);
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
			$con = Propel::getConnection(RegistrationPeer::DATABASE_NAME);
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


						if ($this->isModified()) {
				if ($this->isNew()) {
					$pk = RegistrationPeer::doInsert($this, $con);
					$affectedRows += 1; 										 										 
					$this->setNew(false);
				} else {
					$affectedRows += RegistrationPeer::doUpdate($this, $con);
				}
				$this->resetModified(); 			}

			if ($this->collDataVisibilitys !== null) {
				foreach($this->collDataVisibilitys as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collUserVisibilitys !== null) {
				foreach($this->collUserVisibilitys as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collAudits !== null) {
				foreach($this->collAudits as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collNetworkVisibilitys !== null) {
				foreach($this->collNetworkVisibilitys as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collOutboxsRelatedByFromuser !== null) {
				foreach($this->collOutboxsRelatedByFromuser as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collOutboxsRelatedByTouser !== null) {
				foreach($this->collOutboxsRelatedByTouser as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collInboxsRelatedByFromuser !== null) {
				foreach($this->collInboxsRelatedByFromuser as $referrerFK) {
					if (!$referrerFK->isDeleted()) {
						$affectedRows += $referrerFK->save($con);
					}
				}
			}

			if ($this->collInboxsRelatedByTouser !== null) {
				foreach($this->collInboxsRelatedByTouser as $referrerFK) {
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


			if (($retval = RegistrationPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}


				if ($this->collDataVisibilitys !== null) {
					foreach($this->collDataVisibilitys as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collUserVisibilitys !== null) {
					foreach($this->collUserVisibilitys as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collAudits !== null) {
					foreach($this->collAudits as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collNetworkVisibilitys !== null) {
					foreach($this->collNetworkVisibilitys as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collOutboxsRelatedByFromuser !== null) {
					foreach($this->collOutboxsRelatedByFromuser as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collOutboxsRelatedByTouser !== null) {
					foreach($this->collOutboxsRelatedByTouser as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collInboxsRelatedByFromuser !== null) {
					foreach($this->collInboxsRelatedByFromuser as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}

				if ($this->collInboxsRelatedByTouser !== null) {
					foreach($this->collInboxsRelatedByTouser as $referrerFK) {
						if (!$referrerFK->validate($columns)) {
							$failureMap = array_merge($failureMap, $referrerFK->getValidationFailures());
						}
					}
				}


			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = RegistrationPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->getByPosition($pos);
	}

	
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
		} 	}

	
	public function toArray($keyType = BasePeer::TYPE_PHPNAME)
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

	
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = RegistrationPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->setByPosition($pos, $value);
	}

	
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
		} 	}

	
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

	
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(RegistrationPeer::DATABASE_NAME);

		$criteria->add(RegistrationPeer::USERNAME, $this->username);

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
									$copyObj->setNew(false);

			foreach($this->getDataVisibilitys() as $relObj) {
				$copyObj->addDataVisibility($relObj->copy($deepCopy));
			}

			foreach($this->getUserVisibilitys() as $relObj) {
				$copyObj->addUserVisibility($relObj->copy($deepCopy));
			}

			foreach($this->getAudits() as $relObj) {
				$copyObj->addAudit($relObj->copy($deepCopy));
			}

			foreach($this->getNetworkVisibilitys() as $relObj) {
				$copyObj->addNetworkVisibility($relObj->copy($deepCopy));
			}

			foreach($this->getOutboxsRelatedByFromuser() as $relObj) {
				$copyObj->addOutboxRelatedByFromuser($relObj->copy($deepCopy));
			}

			foreach($this->getOutboxsRelatedByTouser() as $relObj) {
				$copyObj->addOutboxRelatedByTouser($relObj->copy($deepCopy));
			}

			foreach($this->getInboxsRelatedByFromuser() as $relObj) {
				$copyObj->addInboxRelatedByFromuser($relObj->copy($deepCopy));
			}

			foreach($this->getInboxsRelatedByTouser() as $relObj) {
				$copyObj->addInboxRelatedByTouser($relObj->copy($deepCopy));
			}

		} 

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
			self::$peer = new RegistrationPeer();
		}
		return self::$peer;
	}

	
	public function initDataVisibilitys()
	{
		if ($this->collDataVisibilitys === null) {
			$this->collDataVisibilitys = array();
		}
	}

	
	public function getDataVisibilitys($criteria = null, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		if ($this->collDataVisibilitys === null) {
			if ($this->isNew()) {
			   $this->collDataVisibilitys = array();
			} else {

				$criteria->add(DataVisibilityPeer::USERNAME, $this->getUsername());

				DataVisibilityPeer::addSelectColumns($criteria);
				$this->collDataVisibilitys = DataVisibilityPeer::doSelect($criteria, $con);
			}
		} else {
						if (!$this->isNew()) {
												

				$criteria->add(DataVisibilityPeer::USERNAME, $this->getUsername());

				DataVisibilityPeer::addSelectColumns($criteria);
				if (!isset($this->lastDataVisibilityCriteria) || !$this->lastDataVisibilityCriteria->equals($criteria)) {
					$this->collDataVisibilitys = DataVisibilityPeer::doSelect($criteria, $con);
				}
			}
		}
		$this->lastDataVisibilityCriteria = $criteria;
		return $this->collDataVisibilitys;
	}

	
	public function countDataVisibilitys($criteria = null, $distinct = false, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		$criteria->add(DataVisibilityPeer::USERNAME, $this->getUsername());

		return DataVisibilityPeer::doCount($criteria, $distinct, $con);
	}

	
	public function addDataVisibility(DataVisibility $l)
	{
		$this->collDataVisibilitys[] = $l;
		$l->setRegistration($this);
	}

	
	public function initUserVisibilitys()
	{
		if ($this->collUserVisibilitys === null) {
			$this->collUserVisibilitys = array();
		}
	}

	
	public function getUserVisibilitys($criteria = null, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		if ($this->collUserVisibilitys === null) {
			if ($this->isNew()) {
			   $this->collUserVisibilitys = array();
			} else {

				$criteria->add(UserVisibilityPeer::USERNAME, $this->getUsername());

				UserVisibilityPeer::addSelectColumns($criteria);
				$this->collUserVisibilitys = UserVisibilityPeer::doSelect($criteria, $con);
			}
		} else {
						if (!$this->isNew()) {
												

				$criteria->add(UserVisibilityPeer::USERNAME, $this->getUsername());

				UserVisibilityPeer::addSelectColumns($criteria);
				if (!isset($this->lastUserVisibilityCriteria) || !$this->lastUserVisibilityCriteria->equals($criteria)) {
					$this->collUserVisibilitys = UserVisibilityPeer::doSelect($criteria, $con);
				}
			}
		}
		$this->lastUserVisibilityCriteria = $criteria;
		return $this->collUserVisibilitys;
	}

	
	public function countUserVisibilitys($criteria = null, $distinct = false, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		$criteria->add(UserVisibilityPeer::USERNAME, $this->getUsername());

		return UserVisibilityPeer::doCount($criteria, $distinct, $con);
	}

	
	public function addUserVisibility(UserVisibility $l)
	{
		$this->collUserVisibilitys[] = $l;
		$l->setRegistration($this);
	}

	
	public function initAudits()
	{
		if ($this->collAudits === null) {
			$this->collAudits = array();
		}
	}

	
	public function getAudits($criteria = null, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		if ($this->collAudits === null) {
			if ($this->isNew()) {
			   $this->collAudits = array();
			} else {

				$criteria->add(AuditPeer::USERNAME, $this->getUsername());

				AuditPeer::addSelectColumns($criteria);
				$this->collAudits = AuditPeer::doSelect($criteria, $con);
			}
		} else {
						if (!$this->isNew()) {
												

				$criteria->add(AuditPeer::USERNAME, $this->getUsername());

				AuditPeer::addSelectColumns($criteria);
				if (!isset($this->lastAuditCriteria) || !$this->lastAuditCriteria->equals($criteria)) {
					$this->collAudits = AuditPeer::doSelect($criteria, $con);
				}
			}
		}
		$this->lastAuditCriteria = $criteria;
		return $this->collAudits;
	}

	
	public function countAudits($criteria = null, $distinct = false, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		$criteria->add(AuditPeer::USERNAME, $this->getUsername());

		return AuditPeer::doCount($criteria, $distinct, $con);
	}

	
	public function addAudit(Audit $l)
	{
		$this->collAudits[] = $l;
		$l->setRegistration($this);
	}

	
	public function initNetworkVisibilitys()
	{
		if ($this->collNetworkVisibilitys === null) {
			$this->collNetworkVisibilitys = array();
		}
	}

	
	public function getNetworkVisibilitys($criteria = null, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		if ($this->collNetworkVisibilitys === null) {
			if ($this->isNew()) {
			   $this->collNetworkVisibilitys = array();
			} else {

				$criteria->add(NetworkVisibilityPeer::USERNAME, $this->getUsername());

				NetworkVisibilityPeer::addSelectColumns($criteria);
				$this->collNetworkVisibilitys = NetworkVisibilityPeer::doSelect($criteria, $con);
			}
		} else {
						if (!$this->isNew()) {
												

				$criteria->add(NetworkVisibilityPeer::USERNAME, $this->getUsername());

				NetworkVisibilityPeer::addSelectColumns($criteria);
				if (!isset($this->lastNetworkVisibilityCriteria) || !$this->lastNetworkVisibilityCriteria->equals($criteria)) {
					$this->collNetworkVisibilitys = NetworkVisibilityPeer::doSelect($criteria, $con);
				}
			}
		}
		$this->lastNetworkVisibilityCriteria = $criteria;
		return $this->collNetworkVisibilitys;
	}

	
	public function countNetworkVisibilitys($criteria = null, $distinct = false, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		$criteria->add(NetworkVisibilityPeer::USERNAME, $this->getUsername());

		return NetworkVisibilityPeer::doCount($criteria, $distinct, $con);
	}

	
	public function addNetworkVisibility(NetworkVisibility $l)
	{
		$this->collNetworkVisibilitys[] = $l;
		$l->setRegistration($this);
	}

	
	public function initOutboxsRelatedByFromuser()
	{
		if ($this->collOutboxsRelatedByFromuser === null) {
			$this->collOutboxsRelatedByFromuser = array();
		}
	}

	
	public function getOutboxsRelatedByFromuser($criteria = null, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		if ($this->collOutboxsRelatedByFromuser === null) {
			if ($this->isNew()) {
			   $this->collOutboxsRelatedByFromuser = array();
			} else {

				$criteria->add(OutboxPeer::FROMUSER, $this->getUsername());

				OutboxPeer::addSelectColumns($criteria);
				$this->collOutboxsRelatedByFromuser = OutboxPeer::doSelect($criteria, $con);
			}
		} else {
						if (!$this->isNew()) {
												

				$criteria->add(OutboxPeer::FROMUSER, $this->getUsername());

				OutboxPeer::addSelectColumns($criteria);
				if (!isset($this->lastOutboxRelatedByFromuserCriteria) || !$this->lastOutboxRelatedByFromuserCriteria->equals($criteria)) {
					$this->collOutboxsRelatedByFromuser = OutboxPeer::doSelect($criteria, $con);
				}
			}
		}
		$this->lastOutboxRelatedByFromuserCriteria = $criteria;
		return $this->collOutboxsRelatedByFromuser;
	}

	
	public function countOutboxsRelatedByFromuser($criteria = null, $distinct = false, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		$criteria->add(OutboxPeer::FROMUSER, $this->getUsername());

		return OutboxPeer::doCount($criteria, $distinct, $con);
	}

	
	public function addOutboxRelatedByFromuser(Outbox $l)
	{
		$this->collOutboxsRelatedByFromuser[] = $l;
		$l->setRegistrationRelatedByFromuser($this);
	}

	
	public function initOutboxsRelatedByTouser()
	{
		if ($this->collOutboxsRelatedByTouser === null) {
			$this->collOutboxsRelatedByTouser = array();
		}
	}

	
	public function getOutboxsRelatedByTouser($criteria = null, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		if ($this->collOutboxsRelatedByTouser === null) {
			if ($this->isNew()) {
			   $this->collOutboxsRelatedByTouser = array();
			} else {

				$criteria->add(OutboxPeer::TOUSER, $this->getUsername());

				OutboxPeer::addSelectColumns($criteria);
				$this->collOutboxsRelatedByTouser = OutboxPeer::doSelect($criteria, $con);
			}
		} else {
						if (!$this->isNew()) {
												

				$criteria->add(OutboxPeer::TOUSER, $this->getUsername());

				OutboxPeer::addSelectColumns($criteria);
				if (!isset($this->lastOutboxRelatedByTouserCriteria) || !$this->lastOutboxRelatedByTouserCriteria->equals($criteria)) {
					$this->collOutboxsRelatedByTouser = OutboxPeer::doSelect($criteria, $con);
				}
			}
		}
		$this->lastOutboxRelatedByTouserCriteria = $criteria;
		return $this->collOutboxsRelatedByTouser;
	}

	
	public function countOutboxsRelatedByTouser($criteria = null, $distinct = false, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		$criteria->add(OutboxPeer::TOUSER, $this->getUsername());

		return OutboxPeer::doCount($criteria, $distinct, $con);
	}

	
	public function addOutboxRelatedByTouser(Outbox $l)
	{
		$this->collOutboxsRelatedByTouser[] = $l;
		$l->setRegistrationRelatedByTouser($this);
	}

	
	public function initInboxsRelatedByFromuser()
	{
		if ($this->collInboxsRelatedByFromuser === null) {
			$this->collInboxsRelatedByFromuser = array();
		}
	}

	
	public function getInboxsRelatedByFromuser($criteria = null, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		if ($this->collInboxsRelatedByFromuser === null) {
			if ($this->isNew()) {
			   $this->collInboxsRelatedByFromuser = array();
			} else {

				$criteria->add(InboxPeer::FROMUSER, $this->getUsername());

				InboxPeer::addSelectColumns($criteria);
				$this->collInboxsRelatedByFromuser = InboxPeer::doSelect($criteria, $con);
			}
		} else {
						if (!$this->isNew()) {
												

				$criteria->add(InboxPeer::FROMUSER, $this->getUsername());

				InboxPeer::addSelectColumns($criteria);
				if (!isset($this->lastInboxRelatedByFromuserCriteria) || !$this->lastInboxRelatedByFromuserCriteria->equals($criteria)) {
					$this->collInboxsRelatedByFromuser = InboxPeer::doSelect($criteria, $con);
				}
			}
		}
		$this->lastInboxRelatedByFromuserCriteria = $criteria;
		return $this->collInboxsRelatedByFromuser;
	}

	
	public function countInboxsRelatedByFromuser($criteria = null, $distinct = false, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		$criteria->add(InboxPeer::FROMUSER, $this->getUsername());

		return InboxPeer::doCount($criteria, $distinct, $con);
	}

	
	public function addInboxRelatedByFromuser(Inbox $l)
	{
		$this->collInboxsRelatedByFromuser[] = $l;
		$l->setRegistrationRelatedByFromuser($this);
	}

	
	public function initInboxsRelatedByTouser()
	{
		if ($this->collInboxsRelatedByTouser === null) {
			$this->collInboxsRelatedByTouser = array();
		}
	}

	
	public function getInboxsRelatedByTouser($criteria = null, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		if ($this->collInboxsRelatedByTouser === null) {
			if ($this->isNew()) {
			   $this->collInboxsRelatedByTouser = array();
			} else {

				$criteria->add(InboxPeer::TOUSER, $this->getUsername());

				InboxPeer::addSelectColumns($criteria);
				$this->collInboxsRelatedByTouser = InboxPeer::doSelect($criteria, $con);
			}
		} else {
						if (!$this->isNew()) {
												

				$criteria->add(InboxPeer::TOUSER, $this->getUsername());

				InboxPeer::addSelectColumns($criteria);
				if (!isset($this->lastInboxRelatedByTouserCriteria) || !$this->lastInboxRelatedByTouserCriteria->equals($criteria)) {
					$this->collInboxsRelatedByTouser = InboxPeer::doSelect($criteria, $con);
				}
			}
		}
		$this->lastInboxRelatedByTouserCriteria = $criteria;
		return $this->collInboxsRelatedByTouser;
	}

	
	public function countInboxsRelatedByTouser($criteria = null, $distinct = false, $con = null)
	{
				if ($criteria === null) {
			$criteria = new Criteria();
		}
		elseif ($criteria instanceof Criteria)
		{
			$criteria = clone $criteria;
		}

		$criteria->add(InboxPeer::TOUSER, $this->getUsername());

		return InboxPeer::doCount($criteria, $distinct, $con);
	}

	
	public function addInboxRelatedByTouser(Inbox $l)
	{
		$this->collInboxsRelatedByTouser[] = $l;
		$l->setRegistrationRelatedByTouser($this);
	}

} 