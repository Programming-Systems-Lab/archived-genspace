<?php

/**
 * Workflow filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseWorkflowFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'USAGECOUNT'             => new sfWidgetFormFilterInput(),
      'CREATEDAT'              => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
      'CREATOR_ID'             => new sfWidgetFormFilterInput(),
      'PARENT_ID'              => new sfWidgetFormFilterInput(),
      'CREATIONTRANSACTION_ID' => new sfWidgetFormFilterInput(),
      'NUMRATING'              => new sfWidgetFormFilterInput(),
      'SUMRATING'              => new sfWidgetFormFilterInput(),
      'legacy_id'              => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'USAGECOUNT'             => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'CREATEDAT'              => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'CREATOR_ID'             => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'PARENT_ID'              => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'CREATIONTRANSACTION_ID' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'NUMRATING'              => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'SUMRATING'              => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'legacy_id'              => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('workflow_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflow';
  }

  public function getFields()
  {
    return array(
      'ID'                     => 'Number',
      'USAGECOUNT'             => 'Number',
      'CREATEDAT'              => 'Date',
      'CREATOR_ID'             => 'Number',
      'PARENT_ID'              => 'Number',
      'CREATIONTRANSACTION_ID' => 'Number',
      'NUMRATING'              => 'Number',
      'SUMRATING'              => 'Number',
      'legacy_id'              => 'Number',
    );
  }
}
