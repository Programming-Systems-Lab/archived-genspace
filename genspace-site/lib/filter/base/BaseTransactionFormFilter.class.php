<?php

/**
 * Transaction filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseTransactionFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'DATASETNAME' => new sfWidgetFormFilterInput(),
      'CLIENTID'    => new sfWidgetFormFilterInput(),
      'HOSTNAME'    => new sfWidgetFormFilterInput(),
      'DATE'        => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
      'USER_ID'     => new sfWidgetFormFilterInput(),
      'WORKFLOW_ID' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'DATASETNAME' => new sfValidatorPass(array('required' => false)),
      'CLIENTID'    => new sfValidatorPass(array('required' => false)),
      'HOSTNAME'    => new sfValidatorPass(array('required' => false)),
      'DATE'        => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'USER_ID'     => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'WORKFLOW_ID' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('transaction_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Transaction';
  }

  public function getFields()
  {
    return array(
      'ID'          => 'Number',
      'DATASETNAME' => 'Text',
      'CLIENTID'    => 'Text',
      'HOSTNAME'    => 'Text',
      'DATE'        => 'Date',
      'USER_ID'     => 'Number',
      'WORKFLOW_ID' => 'Number',
    );
  }
}
