<?php



/**
 * Skeleton subclass for performing query and update operations on the 'tools' table.
 *
 * 
 *
 * You should add additional methods to this class to meet the
 * application requirements.  This class will only be generated as
 * long as it does not already exist in the output directory.
 *
 * @package    propel.generator.lib.model
 */
class ToolsPeer extends BaseToolsPeer {
  public static function doSelectStmt(Criteria $criteria, PropelPDO $con = null)
  {
    $criteria->add(self::REPLACEDBY_ID, null, Criteria::ISNULL);

    return parent::doSelectStmt($criteria, $con);
  }
} // ToolsPeer
