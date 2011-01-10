<?php

/**
 * LoginEvents form base class.
 *
 * @method LoginEvents getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseLoginEventsForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username' => new sfWidgetFormInputText(),
      'date'     => new sfWidgetFormDateTime(),
      'id'       => new sfWidgetFormInputHidden(),
    ));

    $this->setValidators(array(
      'username' => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'date'     => new sfValidatorDateTime(array('required' => false)),
      'id'       => new sfValidatorPropelChoice(array('model' => 'LoginEvents', 'column' => 'id', 'required' => false)),
    ));


Warning: call_user_func(LoginEventsPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('login_events[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'LoginEvents';
  }


}
