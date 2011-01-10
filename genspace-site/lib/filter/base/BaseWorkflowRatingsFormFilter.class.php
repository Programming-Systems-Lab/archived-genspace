<?php

/**
 * WorkflowRatings filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseWorkflowRatingsFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'       => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'username' => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'rating'   => new sfWidgetFormFilterInput(array('with_empty' => false)),
    ));

    $this->setValidators(array(
      'id'       => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'username' => new sfValidatorPass(array('required' => false)),
      'rating'   => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('workflow_ratings_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'WorkflowRatings';
  }

  public function getFields()
  {
    return array(
      'pk'       => 'Number',
      'id'       => 'Number',
      'username' => 'Text',
      'rating'   => 'Number',
    );
  }
}
