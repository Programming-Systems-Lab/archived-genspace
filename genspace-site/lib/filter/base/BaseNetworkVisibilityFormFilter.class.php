<?php

/**
 * NetworkVisibility filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseNetworkVisibilityFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username'         => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'user_data_option' => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'networkname'      => new sfWidgetFormFilterInput(array('with_empty' => false)),
    ));

    $this->setValidators(array(
      'username'         => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'username')),
      'user_data_option' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'networkname'      => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('network_visibility_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'NetworkVisibility';
  }

  public function getFields()
  {
    return array(
      'id'               => 'Number',
      'username'         => 'ForeignKey',
      'user_data_option' => 'Number',
      'networkname'      => 'Text',
    );
  }
}
