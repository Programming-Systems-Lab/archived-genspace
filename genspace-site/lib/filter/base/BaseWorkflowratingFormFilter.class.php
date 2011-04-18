<?php

/**
 * Workflowrating filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseWorkflowratingFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'CREATEDAT'   => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
      'RATING'      => new sfWidgetFormFilterInput(),
      'WORKFLOW_ID' => new sfWidgetFormFilterInput(),
      'CREATOR_ID'  => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'CREATEDAT'   => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'RATING'      => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'WORKFLOW_ID' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'CREATOR_ID'  => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('workflowrating_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflowrating';
  }

  public function getFields()
  {
    return array(
      'ID'          => 'Number',
      'CREATEDAT'   => 'Date',
      'RATING'      => 'Number',
      'WORKFLOW_ID' => 'Number',
      'CREATOR_ID'  => 'Number',
    );
  }
}
