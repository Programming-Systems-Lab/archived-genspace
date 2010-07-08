<?php


abstract class BaseWorkflowComments extends BaseObject  implements Persistent {


	
	protected static $peer;


	
	protected $pk;


	
	protected $id;


	
	protected $comment;


	
	protected $username;


	
	protected $posted_on;

	
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

	
	public function getComment()
	{

		return $this->comment;
	}

	
	public function getUsername()
	{

		return $this->username;
	}

	
	public function getPostedOn($format = 'Y-m-d H:i:s')
	{

		if ($this->posted_on === null || $this->posted_on === '') {
			return null;
		} elseif (!is_int($this->posted_on)) {
						$ts = strtotime($this->posted_on);
			if ($ts === -1 || $ts === false) { 				throw new PropelException("Unable to parse value of [posted_on] as date/time value: " . var_export($this->posted_on, true));
			}
		} else {
			$ts = $this->posted_on;
		}
		if ($format === null) {
			return $ts;
		} elseif (strpos($format, '%') !== false) {
			return strftime($format, $ts);
		} else {
			return date($format, $ts);
		}
	}

	
	public function setPk($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->pk !== $v) {
			$this->pk = $v;
			$this->modifiedColumns[] = WorkflowCommentsPeer::PK;
		}

	} 
	
	public function setId($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->id !== $v) {
			$this->id = $v;
			$this->modifiedColumns[] = WorkflowCommentsPeer::ID;
		}

	} 
	
	public function setComment($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->comment !== $v) {
			$this->comment = $v;
			$this->modifiedColumns[] = WorkflowCommentsPeer::COMMENT;
		}

	} 
	
	public function setUsername($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->username !== $v) {
			$this->username = $v;
			$this->modifiedColumns[] = WorkflowCommentsPeer::USERNAME;
		}

	} 
	
	public function setPostedOn($v)
	{

		if ($v !== null && !is_int($v)) {
			$ts = strtotime($v);
			if ($ts === -1 || $ts === false) { 				throw new PropelException("Unable to parse date/time value for [posted_on] from input: " . var_export($v, true));
			}
		} else {
			$ts = $v;
		}
		if ($this->posted_on !== $ts) {
			$this->posted_on = $ts;
			$this->modifiedColumns[] = WorkflowCommentsPeer::POSTED_ON;
		}

	} 
	
	public function hydrate(ResultSet $rs, $startcol = 1)
	{
		try {

			$this->pk = $rs->getInt($startcol + 0);

			$this->id = $rs->getInt($startcol + 1);

			$this->comment = $rs->getString($startcol + 2);

			$this->username = $rs->getString($startcol + 3);

			$this->posted_on = $rs->getTimestamp($startcol + 4, null);

			$this->resetModified();

			$this->setNew(false);

						return $startcol + 5; 
		} catch (Exception $e) {
			throw new PropelException("Error populating WorkflowComments object", $e);
		}
	}

	
	public function delete($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(WorkflowCommentsPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			WorkflowCommentsPeer::doDelete($this, $con);
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
			$con = Propel::getConnection(WorkflowCommentsPeer::DATABASE_NAME);
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
					$pk = WorkflowCommentsPeer::doInsert($this, $con);
					$affectedRows += 1; 										 										 
					$this->setPk($pk);  
					$this->setNew(false);
				} else {
					$affectedRows += WorkflowCommentsPeer::doUpdate($this, $con);
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


			if (($retval = WorkflowCommentsPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}



			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = WorkflowCommentsPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
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
				return $this->getComment();
				break;
			case 3:
				return $this->getUsername();
				break;
			case 4:
				return $this->getPostedOn();
				break;
			default:
				return null;
				break;
		} 	}

	
	public function toArray($keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = WorkflowCommentsPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getPk(),
			$keys[1] => $this->getId(),
			$keys[2] => $this->getComment(),
			$keys[3] => $this->getUsername(),
			$keys[4] => $this->getPostedOn(),
		);
		return $result;
	}

	
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = WorkflowCommentsPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
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
				$this->setComment($value);
				break;
			case 3:
				$this->setUsername($value);
				break;
			case 4:
				$this->setPostedOn($value);
				break;
		} 	}

	
	public function fromArray($arr, $keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = WorkflowCommentsPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setPk($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setId($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setComment($arr[$keys[2]]);
		if (array_key_exists($keys[3], $arr)) $this->setUsername($arr[$keys[3]]);
		if (array_key_exists($keys[4], $arr)) $this->setPostedOn($arr[$keys[4]]);
	}

	
	public function buildCriteria()
	{
		$criteria = new Criteria(WorkflowCommentsPeer::DATABASE_NAME);

		if ($this->isColumnModified(WorkflowCommentsPeer::PK)) $criteria->add(WorkflowCommentsPeer::PK, $this->pk);
		if ($this->isColumnModified(WorkflowCommentsPeer::ID)) $criteria->add(WorkflowCommentsPeer::ID, $this->id);
		if ($this->isColumnModified(WorkflowCommentsPeer::COMMENT)) $criteria->add(WorkflowCommentsPeer::COMMENT, $this->comment);
		if ($this->isColumnModified(WorkflowCommentsPeer::USERNAME)) $criteria->add(WorkflowCommentsPeer::USERNAME, $this->username);
		if ($this->isColumnModified(WorkflowCommentsPeer::POSTED_ON)) $criteria->add(WorkflowCommentsPeer::POSTED_ON, $this->posted_on);

		return $criteria;
	}

	
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(WorkflowCommentsPeer::DATABASE_NAME);

		$criteria->add(WorkflowCommentsPeer::PK, $this->pk);

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

		$copyObj->setComment($this->comment);

		$copyObj->setUsername($this->username);

		$copyObj->setPostedOn($this->posted_on);


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
			self::$peer = new WorkflowCommentsPeer();
		}
		return self::$peer;
	}

} 