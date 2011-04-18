<?php

/**
 * Registration filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseRegistrationFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'PHONE'           => new sfWidgetFormFilterInput(),
      'INTERESTS'       => new sfWidgetFormFilterInput(),
      'STATE'           => new sfWidgetFormFilterInput(),
      'online_status'   => new sfWidgetFormFilterInput(),
      'PASSWORD'        => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'CITY'            => new sfWidgetFormFilterInput(),
      'USERNAME'        => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'CREATEDAT'       => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
      'first_name'      => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'DATAVISIBILITY'  => new sfWidgetFormFilterInput(),
      'work_title'      => new sfWidgetFormFilterInput(),
      'last_name'       => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'ZIPCODE'         => new sfWidgetFormFilterInput(),
      'lab_affiliation' => new sfWidgetFormFilterInput(),
      'ADDR1'           => new sfWidgetFormFilterInput(),
      'ADDR2'           => new sfWidgetFormFilterInput(),
      'EMAIL'           => new sfWidgetFormFilterInput(),
      'LOGDATA'         => new sfWidgetFormFilterInput(),
      'ROOTFOLDER_ID'   => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'PHONE'           => new sfValidatorPass(array('required' => false)),
      'INTERESTS'       => new sfValidatorPass(array('required' => false)),
      'STATE'           => new sfValidatorPass(array('required' => false)),
      'online_status'   => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'PASSWORD'        => new sfValidatorPass(array('required' => false)),
      'CITY'            => new sfValidatorPass(array('required' => false)),
      'USERNAME'        => new sfValidatorPass(array('required' => false)),
      'CREATEDAT'       => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'first_name'      => new sfValidatorPass(array('required' => false)),
      'DATAVISIBILITY'  => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'work_title'      => new sfValidatorPass(array('required' => false)),
      'last_name'       => new sfValidatorPass(array('required' => false)),
      'ZIPCODE'         => new sfValidatorPass(array('required' => false)),
      'lab_affiliation' => new sfValidatorPass(array('required' => false)),
      'ADDR1'           => new sfValidatorPass(array('required' => false)),
      'ADDR2'           => new sfValidatorPass(array('required' => false)),
      'EMAIL'           => new sfValidatorPass(array('required' => false)),
      'LOGDATA'         => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'ROOTFOLDER_ID'   => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('registration_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Registration';
  }

  public function getFields()
  {
    return array(
      'ID'              => 'Number',
      'PHONE'           => 'Text',
      'INTERESTS'       => 'Text',
      'STATE'           => 'Text',
      'online_status'   => 'Number',
      'PASSWORD'        => 'Text',
      'CITY'            => 'Text',
      'USERNAME'        => 'Text',
      'CREATEDAT'       => 'Date',
      'first_name'      => 'Text',
      'DATAVISIBILITY'  => 'Number',
      'work_title'      => 'Text',
      'last_name'       => 'Text',
      'ZIPCODE'         => 'Text',
      'lab_affiliation' => 'Text',
      'ADDR1'           => 'Text',
      'ADDR2'           => 'Text',
      'EMAIL'           => 'Text',
      'LOGDATA'         => 'Number',
      'ROOTFOLDER_ID'   => 'Number',
    );
  }
}
