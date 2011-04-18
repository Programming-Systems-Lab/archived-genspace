<?php

/**
 * Setting form base class.
 *
 * @method Setting getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseSettingForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'         => new sfWidgetFormInputHidden(),
      'data_value' => new sfWidgetFormInputText(),
      'data_key'   => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'         => new sfValidatorPropelChoice(array('model' => 'Setting', 'column' => 'ID', 'required' => false)),
      'data_value' => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'data_key'   => new sfValidatorString(array('max_length' => 255, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'SettingPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('setting[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Setting';
  }


}
