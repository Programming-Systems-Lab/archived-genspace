<?php

/**
 * Workflowcomments filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseWorkflowcommentsFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'CREATEDAT'   => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
      'COMMENT'     => new sfWidgetFormFilterInput(),
      'WORKFLOW_ID' => new sfWidgetFormFilterInput(),
      'CREATOR_ID'  => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
    ));

    $this->setValidators(array(
      'CREATEDAT'   => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'COMMENT'     => new sfValidatorPass(array('required' => false)),
      'WORKFLOW_ID' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'CREATOR_ID'  => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'ID')),
    ));

    $this->widgetSchema->setNameFormat('workflowcomments_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflowcomments';
  }

  public function getFields()
  {
    return array(
      'ID'          => 'Number',
      'CREATEDAT'   => 'Date',
      'COMMENT'     => 'Text',
      'WORKFLOW_ID' => 'Number',
      'CREATOR_ID'  => 'ForeignKey',
    );
  }
}
