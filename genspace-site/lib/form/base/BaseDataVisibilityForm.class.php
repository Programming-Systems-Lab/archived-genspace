<?php

/**
 * DataVisibility form base class.
 *
 * @method DataVisibility getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseDataVisibilityForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username'       => new sfWidgetFormInputHidden(),
      'logdata'        => new sfWidgetFormInputText(),
      'datavisibility' => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'username'       => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'username', 'required' => false)),
      'logdata'        => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
      'datavisibility' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
    ));


Warning: call_user_func(DataVisibilityPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('data_visibility[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'DataVisibility';
  }


}
