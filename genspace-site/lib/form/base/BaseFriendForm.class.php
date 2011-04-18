<?php

/**
 * Friend form base class.
 *
 * @method Friend getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseFriendForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'      => new sfWidgetFormInputHidden(),
      'MUTUAL'  => new sfWidgetFormInputText(),
      'VISIBLE' => new sfWidgetFormInputText(),
      'id_1'    => new sfWidgetFormInputText(),
      'id_2'    => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'      => new sfValidatorPropelChoice(array('model' => 'Friend', 'column' => 'ID', 'required' => false)),
      'MUTUAL'  => new sfValidatorInteger(array('min' => -128, 'max' => 127, 'required' => false)),
      'VISIBLE' => new sfValidatorInteger(array('min' => -128, 'max' => 127, 'required' => false)),
      'id_1'    => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'id_2'    => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'FriendPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('friend[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Friend';
  }


}
