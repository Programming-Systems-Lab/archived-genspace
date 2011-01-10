<?php

/**
 * NetworkVisibility form base class.
 *
 * @method NetworkVisibility getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseNetworkVisibilityForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'               => new sfWidgetFormInputHidden(),
      'username'         => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => false)),
      'user_data_option' => new sfWidgetFormInputText(),
      'networkname'      => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'id'               => new sfValidatorPropelChoice(array('model' => 'NetworkVisibility', 'column' => 'id', 'required' => false)),
      'username'         => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'username')),
      'user_data_option' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
      'networkname'      => new sfValidatorString(array('max_length' => 50)),
    ));


Warning: call_user_func(NetworkVisibilityPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('network_visibility[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'NetworkVisibility';
  }


}
