<?php

/**
 * UserImHandles form base class.
 *
 * @method UserImHandles getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseUserImHandlesForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username'   => new sfWidgetFormInputText(),
      'IM_handle'  => new sfWidgetFormInputText(),
      'IM_service' => new sfWidgetFormInputText(),
      'id'         => new sfWidgetFormInputHidden(),
    ));

    $this->setValidators(array(
      'username'   => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'IM_handle'  => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'IM_service' => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'id'         => new sfValidatorPropelChoice(array('model' => 'UserImHandles', 'column' => 'id', 'required' => false)),
    ));


Warning: call_user_func(UserImHandlesPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('user_im_handles[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'UserImHandles';
  }


}
