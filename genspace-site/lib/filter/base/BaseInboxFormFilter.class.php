<?php

/**
 * Inbox filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseInboxFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'Date'      => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
      'FromUser'  => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'ToUser'    => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'Message'   => new sfWidgetFormFilterInput(array('with_empty' => false)),
    ));

    $this->setValidators(array(
      'Date'      => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'FromUser'  => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'username')),
      'ToUser'    => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'username')),
      'Message'   => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('inbox_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Inbox';
  }

  public function getFields()
  {
    return array(
      'MessageID' => 'Number',
      'Date'      => 'Date',
      'FromUser'  => 'ForeignKey',
      'ToUser'    => 'ForeignKey',
      'Message'   => 'Text',
    );
  }
}
