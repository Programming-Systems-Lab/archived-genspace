<?php

/**
 * UserNetwork form base class.
 *
 * @method UserNetwork getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseUserNetworkForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'         => new sfWidgetFormInputHidden(),
      'VISIBLE'    => new sfWidgetFormInputText(),
      'VERIFIED'   => new sfWidgetFormInputText(),
      'network_id' => new sfWidgetFormInputText(),
      'user_id'    => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'         => new sfValidatorPropelChoice(array('model' => 'UserNetwork', 'column' => 'ID', 'required' => false)),
      'VISIBLE'    => new sfValidatorInteger(array('min' => -128, 'max' => 127, 'required' => false)),
      'VERIFIED'   => new sfValidatorInteger(array('min' => -128, 'max' => 127, 'required' => false)),
      'network_id' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'user_id'    => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'UserNetworkPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('user_network[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'UserNetwork';
  }


}
