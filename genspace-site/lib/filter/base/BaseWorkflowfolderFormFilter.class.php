<?php

/**
 * Workflowfolder filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseWorkflowfolderFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'NAME'      => new sfWidgetFormFilterInput(),
      'OWNER_ID'  => new sfWidgetFormFilterInput(),
      'PARENT_ID' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'NAME'      => new sfValidatorPass(array('required' => false)),
      'OWNER_ID'  => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'PARENT_ID' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('workflowfolder_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflowfolder';
  }

  public function getFields()
  {
    return array(
      'ID'        => 'Number',
      'NAME'      => 'Text',
      'OWNER_ID'  => 'Number',
      'PARENT_ID' => 'Number',
    );
  }
}
