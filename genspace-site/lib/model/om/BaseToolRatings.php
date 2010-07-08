<?php


abstract class BaseToolRatings extends BaseObject  implements Persistent {


	
	protected static $peer;


	
	protected $pk;


	
	protected $id;


	
	protected $rating;


	
	protected $username;

	
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

	
	public function getRating()
	{

		return $this->rating;
	}

	
	public function getUsername()
	{

		return $this->username;
	}

	
	public function setPk($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->pk !== $v) {
			$this->pk = $v;
			$this->modifiedColumns[] = ToolRatingsPeer::PK;
		}

	} 
	
	public function setId($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->id !== $v) {
			$this->id = $v;
			$this->modifiedColumns[] = ToolRatingsPeer::ID;
		}

	} 
	
	public function setRating($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->rating !== $v) {
			$this->rating = $v;
			$this->modifiedColumns[] = ToolRatingsPeer::RATING;
		}

	} 
	
	public function setUsername($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->username !== $v) {
			$this->username = $v;
			$this->modifiedColumns[] = ToolRatingsPeer::USERNAME;
		}

	} 
	
	public function hydrate(ResultSet $rs, $startcol = 1)
	{
		try {

			$this->pk = $rs->getInt($startcol + 0);

			$this->id = $rs->getInt($startcol + 1);

			$this->rating = $rs->getInt($startcol + 2);

			$this->username = $rs->getString($startcol + 3);

			$this->resetModified();

			$this->setNew(false);

						return $startcol + 4; 
		} catch (Exception $e) {
			throw new PropelException("Error populating ToolRatings object", $e);
		}
	}

	
	public function delete($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(ToolRatingsPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			ToolRatingsPeer::doDelete($this, $con);
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
			$con = Propel::getConnection(ToolRatingsPeer::DATABASE_NAME);
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
					$pk = ToolRatingsPeer::doInsert($this, $con);
					$affectedRows += 1; 										 										 
					$this->setPk($pk);  
					$this->setNew(false);
				} else {
					$affectedRows += ToolRatingsPeer::doUpdate($this, $con);
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


			if (($retval = ToolRatingsPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}



			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = ToolRatingsPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
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
				return $this->getRating();
				break;
			case 3:
				return $this->getUsername();
				break;
			default:
				return null;
				break;
		} 	}

	
	public function toArray($keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = ToolRatingsPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getPk(),
			$keys[1] => $this->getId(),
			$keys[2] => $this->getRating(),
			$keys[3] => $this->getUsername(),
		);
		return $result;
	}

	
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = ToolRatingsPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
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
				$this->setRating($value);
				break;
			case 3:
				$this->setUsername($value);
				break;
		} 	}

	
	public function fromArray($arr, $keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = ToolRatingsPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setPk($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setId($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setRating($arr[$keys[2]]);
		if (array_key_exists($keys[3], $arr)) $this->setUsername($arr[$keys[3]]);
	}

	
	public function buildCriteria()
	{
		$criteria = new Criteria(ToolRatingsPeer::DATABASE_NAME);

		if ($this->isColumnModified(ToolRatingsPeer::PK)) $criteria->add(ToolRatingsPeer::PK, $this->pk);
		if ($this->isColumnModified(ToolRatingsPeer::ID)) $criteria->add(ToolRatingsPeer::ID, $this->id);
		if ($this->isColumnModified(ToolRatingsPeer::RATING)) $criteria->add(ToolRatingsPeer::RATING, $this->rating);
		if ($this->isColumnModified(ToolRatingsPeer::USERNAME)) $criteria->add(ToolRatingsPeer::USERNAME, $this->username);

		return $criteria;
	}

	
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(ToolRatingsPeer::DATABASE_NAME);

		$criteria->add(ToolRatingsPeer::PK, $this->pk);

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

		$copyObj->setRating($this->rating);

		$copyObj->setUsername($this->username);


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
			self::$peer = new ToolRatingsPeer();
		}
		return self::$peer;
	}

} 