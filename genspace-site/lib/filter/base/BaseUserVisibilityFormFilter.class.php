<?php

/**
 * UserVisibility filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseUserVisibilityFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username'       => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'uservisibility' => new sfWidgetFormFilterInput(array('with_empty' => false)),
    ));

    $this->setValidators(array(
      'username'       => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'username')),
      'uservisibility' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('user_visibility_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'UserVisibility';
  }

  public function getFields()
  {
    return array(
      'username'       => 'ForeignKey',
      'uservisibility' => 'Number',
      'id'             => 'Number',
    );
  }
}
