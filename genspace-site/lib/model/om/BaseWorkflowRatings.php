<?php


abstract class BaseWorkflowRatings extends BaseObject  implements Persistent {


	
	protected static $peer;


	
	protected $pk;


	
	protected $id;


	
	protected $username;


	
	protected $rating;

	
	protected $alreadyInSave = false;

	
	protected $alreadyInValidation = false;

	
	public function getPk()
	{

		return $this->pk;
	}

	
	public function getId()
	{

		return $this->id;
	}

	
	public function getUsername()
	{

		return $this->username;
	}

	
	public function getRating()
	{

		return $this->rating;
	}

	
	public function setPk($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->pk !== $v) {
			$this->pk = $v;
			$this->modifiedColumns[] = WorkflowRatingsPeer::PK;
		}

	} 
	
	public function setId($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->id !== $v) {
			$this->id = $v;
			$this->modifiedColumns[] = WorkflowRatingsPeer::ID;
		}

	} 
	
	public function setUsername($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->username !== $v) {
			$this->username = $v;
			$this->modifiedColumns[] = WorkflowRatingsPeer::USERNAME;
		}

	} 
	
	public function setRating($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->rating !== $v) {
			$this->rating = $v;
			$this->modifiedColumns[] = WorkflowRatingsPeer::RATING;
		}

	} 
	
	public function hydrate(ResultSet $rs, $startcol = 1)
	{
		try {

			$this->pk = $rs->getInt($startcol + 0);

			$this->id = $rs->getInt($startcol + 1);

			$this->username = $rs->getString($startcol + 2);

			$this->rating = $rs->getInt($startcol + 3);

			$this->resetModified();

			$this->setNew(false);

						return $startcol + 4; 
		} catch (Exception $e) {
			throw new PropelException("Error populating WorkflowRatings object", $e);
		}
	}

	
	public function delete($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(WorkflowRatingsPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			WorkflowRatingsPeer::doDelete($this, $con);
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
			$con = Propel::getConnection(WorkflowRatingsPeer::DATABASE_NAME);
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
					$pk = WorkflowRatingsPeer::doInsert($this, $con);
					$affectedRows += 1; 										 										 
					$this->setPk($pk);  
					$this->setNew(false);
				} else {
					$affectedRows += WorkflowRatingsPeer::doUpdate($this, $con);
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


			if (($retval = WorkflowRatingsPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}



			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = WorkflowRatingsPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->getByPosition($pos);
	}

	
	public function getByPosition($pos)
	{
		switch($pos) {
			case 0:
				return $this->getPk();
				break;
			case 1:
				return $this->getId();
				break;
			case 2:
				return $this->getUsername();
				break;
			case 3:
				return $this->getRating();
				break;
			default:
				return null;
				break;
		} 	}

	
	public function toArray($keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = WorkflowRatingsPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getPk(),
			$keys[1] => $this->getId(),
			$keys[2] => $this->getUsername(),
			$keys[3] => $this->getRating(),
		);
		return $result;
	}

	
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = WorkflowRatingsPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->setByPosition($pos, $value);
	}

	
	public function setByPosition($pos, $value)
	{
		switch($pos) {
			case 0:
				$this->setPk($value);
				break;
			case 1:
				$this->setId($value);
				break;
			case 2:
				$this->setUsername($value);
				break;
			case 3:
				$this->setRating($value);
				break;
		} 	}

	
	public function fromArray($arr, $keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = WorkflowRatingsPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setPk($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setId($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setUsername($arr[$keys[2]]);
		if (array_key_exists($keys[3], $arr)) $this->setRating($arr[$keys[3]]);
	}

	
	public function buildCriteria()
	{
		$criteria = new Criteria(WorkflowRatingsPeer::DATABASE_NAME);

		if ($this->isColumnModified(WorkflowRatingsPeer::PK)) $criteria->add(WorkflowRatingsPeer::PK, $this->pk);
		if ($this->isColumnModified(WorkflowRatingsPeer::ID)) $criteria->add(WorkflowRatingsPeer::ID, $this->id);
		if ($this->isColumnModified(WorkflowRatingsPeer::USERNAME)) $criteria->add(WorkflowRatingsPeer::USERNAME, $this->username);
		if ($this->isColumnModified(WorkflowRatingsPeer::RATING)) $criteria->add(WorkflowRatingsPeer::RATING, $this->rating);

		return $criteria;
	}

	
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(WorkflowRatingsPeer::DATABASE_NAME);

		$criteria->add(WorkflowRatingsPeer::PK, $this->pk);

		return $criteria;
	}

	
	public function getPrimaryKey()
	{
		return $this->getPk();
	}

	
	public function setPrimaryKey($key)
	{
		$this->setPk($key);
	}

	
	public function copyInto($copyObj, $deepCopy = false)
	{

		$copyObj->setId($this->id);

		$copyObj->setUsername($this->username);

		$copyObj->setRating($this->rating);


		$copyObj->setNew(true);

		$copyObj->setPk(NULL); 
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
			self::$peer = new WorkflowRatingsPeer();
		}
		return self::$peer;
	}

} 