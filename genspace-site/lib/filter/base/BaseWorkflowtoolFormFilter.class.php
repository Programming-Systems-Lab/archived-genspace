<?php

/**
 * Workflowtool filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseWorkflowtoolFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'CARDINALITY' => new sfWidgetFormFilterInput(),
      'WORKFLOW_ID' => new sfWidgetFormFilterInput(),
      'TOOL_ID'     => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'CARDINALITY' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'WORKFLOW_ID' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'TOOL_ID'     => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('workflowtool_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflowtool';
  }

  public function getFields()
  {
    return array(
      'ID'          => 'Number',
      'CARDINALITY' => 'Number',
      'WORKFLOW_ID' => 'Number',
      'TOOL_ID'     => 'Number',
    );
  }
}
