<?php

/**
 * Workflowratings filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseWorkflowratingsFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'CREATEDAT'   => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
      'RATING'      => new sfWidgetFormFilterInput(),
      'WORKFLOW_ID' => new sfWidgetFormFilterInput(),
      'CREATOR_ID'  => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
    ));

    $this->setValidators(array(
      'CREATEDAT'   => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'RATING'      => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'WORKFLOW_ID' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'CREATOR_ID'  => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'ID')),
    ));

    $this->widgetSchema->setNameFormat('workflowratings_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflowratings';
  }

  public function getFields()
  {
    return array(
      'ID'          => 'Number',
      'CREATEDAT'   => 'Date',
      'RATING'      => 'Number',
      'WORKFLOW_ID' => 'Number',
      'CREATOR_ID'  => 'ForeignKey',
    );
  }
}
