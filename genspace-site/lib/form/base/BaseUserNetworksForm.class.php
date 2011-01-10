<?php

/**
 * UserNetworks form base class.
 *
 * @method UserNetworks getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseUserNetworksForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username' => new sfWidgetFormInputHidden(),
      'network'  => new sfWidgetFormInputHidden(),
    ));

    $this->setValidators(array(
      'username' => new sfValidatorPropelChoice(array('model' => 'Networks', 'column' => 'id', 'required' => false)),
      'network'  => new sfValidatorPropelChoice(array('model' => 'Networks', 'column' => 'network', 'required' => false)),
    ));


Warning: call_user_func(UserNetworksPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('user_networks[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'UserNetworks';
  }


}
