<?php

/**
 * Incomingworkflow filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseIncomingworkflowFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'CREATEDAT'   => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
      'NAME'        => new sfWidgetFormFilterInput(),
      'SENDER_ID'   => new sfWidgetFormFilterInput(),
      'WORKFLOW_ID' => new sfWidgetFormFilterInput(),
      'RECEIVER_ID' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'CREATEDAT'   => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'NAME'        => new sfValidatorPass(array('required' => false)),
      'SENDER_ID'   => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'WORKFLOW_ID' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'RECEIVER_ID' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('incomingworkflow_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Incomingworkflow';
  }

  public function getFields()
  {
    return array(
      'ID'          => 'Number',
      'CREATEDAT'   => 'Date',
      'NAME'        => 'Text',
      'SENDER_ID'   => 'Number',
      'WORKFLOW_ID' => 'Number',
      'RECEIVER_ID' => 'Number',
    );
  }
}
