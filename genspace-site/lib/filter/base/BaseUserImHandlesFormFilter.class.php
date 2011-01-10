<?php

/**
 * UserImHandles filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseUserImHandlesFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username'   => new sfWidgetFormFilterInput(),
      'IM_handle'  => new sfWidgetFormFilterInput(),
      'IM_service' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'username'   => new sfValidatorPass(array('required' => false)),
      'IM_handle'  => new sfValidatorPass(array('required' => false)),
      'IM_service' => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('user_im_handles_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'UserImHandles';
  }

  public function getFields()
  {
    return array(
      'username'   => 'Text',
      'IM_handle'  => 'Text',
      'IM_service' => 'Text',
      'id'         => 'Number',
    );
  }
}
