<?php

/**
 * Workflows filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseWorkflowsFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'parent' => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'tool'   => new sfWidgetFormFilterInput(array('with_empty' => false)),
    ));

    $this->setValidators(array(
      'parent' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'tool'   => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('workflows_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflows';
  }

  public function getFields()
  {
    return array(
      'id'     => 'Number',
      'parent' => 'Number',
      'tool'   => 'Text',
    );
  }
}
