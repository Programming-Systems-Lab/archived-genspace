<?php

/**
 * Userworkflow filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseUserworkflowFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'NAME'        => new sfWidgetFormFilterInput(),
      'WORKFLOW_ID' => new sfWidgetFormFilterInput(),
      'OWNER_ID'    => new sfWidgetFormFilterInput(),
      'FOLDER_ID'   => new sfWidgetFormFilterInput(),
      'CREATEDAT'   => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
    ));

    $this->setValidators(array(
      'NAME'        => new sfValidatorPass(array('required' => false)),
      'WORKFLOW_ID' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'OWNER_ID'    => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'FOLDER_ID'   => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'CREATEDAT'   => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
    ));

    $this->widgetSchema->setNameFormat('userworkflow_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Userworkflow';
  }

  public function getFields()
  {
    return array(
      'ID'          => 'Number',
      'NAME'        => 'Text',
      'WORKFLOW_ID' => 'Number',
      'OWNER_ID'    => 'Number',
      'FOLDER_ID'   => 'Number',
      'CREATEDAT'   => 'Date',
    );
  }
}
