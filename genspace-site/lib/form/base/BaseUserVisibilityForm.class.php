<?php

/**
 * UserVisibility form base class.
 *
 * @method UserVisibility getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseUserVisibilityForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username'       => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => false)),
      'uservisibility' => new sfWidgetFormInputText(),
      'id'             => new sfWidgetFormInputHidden(),
    ));

    $this->setValidators(array(
      'username'       => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'username')),
      'uservisibility' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
      'id'             => new sfValidatorPropelChoice(array('model' => 'UserVisibility', 'column' => 'id', 'required' => false)),
    ));


Warning: call_user_func(UserVisibilityPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('user_visibility[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'UserVisibility';
  }


}
