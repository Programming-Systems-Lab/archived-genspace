<?php

/**
 * Audit filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseAuditFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username'    => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'action'      => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'tablename'   => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'beforevalue' => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'aftervalue'  => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'time'        => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
    ));

    $this->setValidators(array(
      'username'    => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'username')),
      'action'      => new sfValidatorPass(array('required' => false)),
      'tablename'   => new sfValidatorPass(array('required' => false)),
      'beforevalue' => new sfValidatorPass(array('required' => false)),
      'aftervalue'  => new sfValidatorPass(array('required' => false)),
      'time'        => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
    ));

    $this->widgetSchema->setNameFormat('audit_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Audit';
  }

  public function getFields()
  {
    return array(
      'username'    => 'ForeignKey',
      'action'      => 'Text',
      'tablename'   => 'Text',
      'beforevalue' => 'Text',
      'aftervalue'  => 'Text',
      'time'        => 'Date',
      'id'          => 'Number',
    );
  }
}
