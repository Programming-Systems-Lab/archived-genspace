<?php

/**
 * Network form base class.
 *
 * @method Network getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseNetworkForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'    => new sfWidgetFormInputHidden(),
      'NAME'  => new sfWidgetFormInputText(),
      'owner' => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'    => new sfValidatorPropelChoice(array('model' => 'Network', 'column' => 'ID', 'required' => false)),
      'NAME'  => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'owner' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'NetworkPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('network[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Network';
  }


}
