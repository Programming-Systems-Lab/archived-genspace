<?php

/**
 * History filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseHistoryFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'wspid'      => new sfWidgetFormPropelChoice(array('model' => 'Workspace', 'add_empty' => true)),
      'uid'        => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'type'       => new sfWidgetFormFilterInput(),
      'accessedAt' => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
    ));

    $this->setValidators(array(
      'wspid'      => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Workspace', 'column' => 'id')),
      'uid'        => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'ID')),
      'type'       => new sfValidatorPass(array('required' => false)),
      'accessedAt' => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
    ));

    $this->widgetSchema->setNameFormat('history_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'History';
  }

  public function getFields()
  {
    return array(
      'id'         => 'Number',
      'wspid'      => 'ForeignKey',
      'uid'        => 'ForeignKey',
      'type'       => 'Text',
      'accessedAt' => 'Date',
    );
  }
}
