<?php


abstract class BaseAnalysisEvents extends BaseObject  implements Persistent {


	
	protected static $peer;


	
	protected $username;


	
	protected $host;


	
	protected $date;


	
	protected $analysis;


	
	protected $dataset;


	
	protected $transaction_id;


	
	protected $is_genspace_user;


	
	protected $id;

	
	protected $alreadyInSave = false;

	
	protected $alreadyInValidation = false;

	
	public function getUsername()
	{

		return $this->username;
	}

	
	public function getHost()
	{

		return $this->host;
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

	
	public function getAnalysis()
	{

		return $this->analysis;
	}

	
	public function getDataset()
	{

		return $this->dataset;
	}

	
	public function getTransactionId()
	{

		return $this->transaction_id;
	}

	
	public function getIsGenspaceUser()
	{

		return $this->is_genspace_user;
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
			$this->modifiedColumns[] = AnalysisEventsPeer::USERNAME;
		}

	} 
	
	public function setHost($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->host !== $v) {
			$this->host = $v;
			$this->modifiedColumns[] = AnalysisEventsPeer::HOST;
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
			$this->modifiedColumns[] = AnalysisEventsPeer::DATE;
		}

	} 
	
	public function setAnalysis($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->analysis !== $v) {
			$this->analysis = $v;
			$this->modifiedColumns[] = AnalysisEventsPeer::ANALYSIS;
		}

	} 
	
	public function setDataset($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->dataset !== $v) {
			$this->dataset = $v;
			$this->modifiedColumns[] = AnalysisEventsPeer::DATASET;
		}

	} 
	
	public function setTransactionId($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->transaction_id !== $v) {
			$this->transaction_id = $v;
			$this->modifiedColumns[] = AnalysisEventsPeer::TRANSACTION_ID;
		}

	} 
	
	public function setIsGenspaceUser($v)
	{

						if ($v !== null && !is_string($v)) {
			$v = (string) $v; 
		}

		if ($this->is_genspace_user !== $v) {
			$this->is_genspace_user = $v;
			$this->modifiedColumns[] = AnalysisEventsPeer::IS_GENSPACE_USER;
		}

	} 
	
	public function setId($v)
	{

						if ($v !== null && !is_int($v) && is_numeric($v)) {
			$v = (int) $v;
		}

		if ($this->id !== $v) {
			$this->id = $v;
			$this->modifiedColumns[] = AnalysisEventsPeer::ID;
		}

	} 
	
	public function hydrate(ResultSet $rs, $startcol = 1)
	{
		try {

			$this->username = $rs->getString($startcol + 0);

			$this->host = $rs->getString($startcol + 1);

			$this->date = $rs->getTimestamp($startcol + 2, null);

			$this->analysis = $rs->getString($startcol + 3);

			$this->dataset = $rs->getString($startcol + 4);

			$this->transaction_id = $rs->getString($startcol + 5);

			$this->is_genspace_user = $rs->getString($startcol + 6);

			$this->id = $rs->getInt($startcol + 7);

			$this->resetModified();

			$this->setNew(false);

						return $startcol + 8; 
		} catch (Exception $e) {
			throw new PropelException("Error populating AnalysisEvents object", $e);
		}
	}

	
	public function delete($con = null)
	{
		if ($this->isDeleted()) {
			throw new PropelException("This object has already been deleted.");
		}

		if ($con === null) {
			$con = Propel::getConnection(AnalysisEventsPeer::DATABASE_NAME);
		}

		try {
			$con->begin();
			AnalysisEventsPeer::doDelete($this, $con);
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
			$con = Propel::getConnection(AnalysisEventsPeer::DATABASE_NAME);
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
					$pk = AnalysisEventsPeer::doInsert($this, $con);
					$affectedRows += 1; 										 										 
					$this->setId($pk);  
					$this->setNew(false);
				} else {
					$affectedRows += AnalysisEventsPeer::doUpdate($this, $con);
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


			if (($retval = AnalysisEventsPeer::doValidate($this, $columns)) !== true) {
				$failureMap = array_merge($failureMap, $retval);
			}



			$this->alreadyInValidation = false;
		}

		return (!empty($failureMap) ? $failureMap : true);
	}

	
	public function getByName($name, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = AnalysisEventsPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->getByPosition($pos);
	}

	
	public function getByPosition($pos)
	{
		switch($pos) {
			case 0:
				return $this->getUsername();
				break;
			case 1:
				return $this->getHost();
				break;
			case 2:
				return $this->getDate();
				break;
			case 3:
				return $this->getAnalysis();
				break;
			case 4:
				return $this->getDataset();
				break;
			case 5:
				return $this->getTransactionId();
				break;
			case 6:
				return $this->getIsGenspaceUser();
				break;
			case 7:
				return $this->getId();
				break;
			default:
				return null;
				break;
		} 	}

	
	public function toArray($keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = AnalysisEventsPeer::getFieldNames($keyType);
		$result = array(
			$keys[0] => $this->getUsername(),
			$keys[1] => $this->getHost(),
			$keys[2] => $this->getDate(),
			$keys[3] => $this->getAnalysis(),
			$keys[4] => $this->getDataset(),
			$keys[5] => $this->getTransactionId(),
			$keys[6] => $this->getIsGenspaceUser(),
			$keys[7] => $this->getId(),
		);
		return $result;
	}

	
	public function setByName($name, $value, $type = BasePeer::TYPE_PHPNAME)
	{
		$pos = AnalysisEventsPeer::translateFieldName($name, $type, BasePeer::TYPE_NUM);
		return $this->setByPosition($pos, $value);
	}

	
	public function setByPosition($pos, $value)
	{
		switch($pos) {
			case 0:
				$this->setUsername($value);
				break;
			case 1:
				$this->setHost($value);
				break;
			case 2:
				$this->setDate($value);
				break;
			case 3:
				$this->setAnalysis($value);
				break;
			case 4:
				$this->setDataset($value);
				break;
			case 5:
				$this->setTransactionId($value);
				break;
			case 6:
				$this->setIsGenspaceUser($value);
				break;
			case 7:
				$this->setId($value);
				break;
		} 	}

	
	public function fromArray($arr, $keyType = BasePeer::TYPE_PHPNAME)
	{
		$keys = AnalysisEventsPeer::getFieldNames($keyType);

		if (array_key_exists($keys[0], $arr)) $this->setUsername($arr[$keys[0]]);
		if (array_key_exists($keys[1], $arr)) $this->setHost($arr[$keys[1]]);
		if (array_key_exists($keys[2], $arr)) $this->setDate($arr[$keys[2]]);
		if (array_key_exists($keys[3], $arr)) $this->setAnalysis($arr[$keys[3]]);
		if (array_key_exists($keys[4], $arr)) $this->setDataset($arr[$keys[4]]);
		if (array_key_exists($keys[5], $arr)) $this->setTransactionId($arr[$keys[5]]);
		if (array_key_exists($keys[6], $arr)) $this->setIsGenspaceUser($arr[$keys[6]]);
		if (array_key_exists($keys[7], $arr)) $this->setId($arr[$keys[7]]);
	}

	
	public function buildCriteria()
	{
		$criteria = new Criteria(AnalysisEventsPeer::DATABASE_NAME);

		if ($this->isColumnModified(AnalysisEventsPeer::USERNAME)) $criteria->add(AnalysisEventsPeer::USERNAME, $this->username);
		if ($this->isColumnModified(AnalysisEventsPeer::HOST)) $criteria->add(AnalysisEventsPeer::HOST, $this->host);
		if ($this->isColumnModified(AnalysisEventsPeer::DATE)) $criteria->add(AnalysisEventsPeer::DATE, $this->date);
		if ($this->isColumnModified(AnalysisEventsPeer::ANALYSIS)) $criteria->add(AnalysisEventsPeer::ANALYSIS, $this->analysis);
		if ($this->isColumnModified(AnalysisEventsPeer::DATASET)) $criteria->add(AnalysisEventsPeer::DATASET, $this->dataset);
		if ($this->isColumnModified(AnalysisEventsPeer::TRANSACTION_ID)) $criteria->add(AnalysisEventsPeer::TRANSACTION_ID, $this->transaction_id);
		if ($this->isColumnModified(AnalysisEventsPeer::IS_GENSPACE_USER)) $criteria->add(AnalysisEventsPeer::IS_GENSPACE_USER, $this->is_genspace_user);
		if ($this->isColumnModified(AnalysisEventsPeer::ID)) $criteria->add(AnalysisEventsPeer::ID, $this->id);

		return $criteria;
	}

	
	public function buildPkeyCriteria()
	{
		$criteria = new Criteria(AnalysisEventsPeer::DATABASE_NAME);

		$criteria->add(AnalysisEventsPeer::ID, $this->id);

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

		$copyObj->setHost($this->host);

		$copyObj->setDate($this->date);

		$copyObj->setAnalysis($this->analysis);

		$copyObj->setDataset($this->dataset);

		$copyObj->setTransactionId($this->transaction_id);

		$copyObj->setIsGenspaceUser($this->is_genspace_user);


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
			self::$peer = new AnalysisEventsPeer();
		}
		return self::$peer;
	}

} 