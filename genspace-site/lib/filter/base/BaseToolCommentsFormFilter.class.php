<?php

/**
 * Toolcomments filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseToolcommentsFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'CREATEDAT'  => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
      'COMMENT'    => new sfWidgetFormFilterInput(),
      'CREATOR_ID' => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'TOOL_ID'    => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'CREATEDAT'  => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'COMMENT'    => new sfValidatorPass(array('required' => false)),
      'CREATOR_ID' => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'ID')),
      'TOOL_ID'    => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('toolcomments_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Toolcomments';
  }

  public function getFields()
  {
    return array(
      'ID'         => 'Number',
      'CREATEDAT'  => 'Date',
      'COMMENT'    => 'Text',
      'CREATOR_ID' => 'ForeignKey',
      'TOOL_ID'    => 'Number',
    );
  }
}
