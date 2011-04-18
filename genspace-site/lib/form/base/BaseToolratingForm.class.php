<?php

/**
 * Toolrating form base class.
 *
 * @method Toolrating getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseToolratingForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'         => new sfWidgetFormInputHidden(),
      'CREATEDAT'  => new sfWidgetFormDateTime(),
      'RATING'     => new sfWidgetFormInputText(),
      'TOOL_ID'    => new sfWidgetFormInputText(),
      'CREATOR_ID' => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'         => new sfValidatorPropelChoice(array('model' => 'Toolrating', 'column' => 'ID', 'required' => false)),
      'CREATEDAT'  => new sfValidatorDateTime(array('required' => false)),
      'RATING'     => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'TOOL_ID'    => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'CREATOR_ID' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'ToolratingPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('toolrating[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Toolrating';
  }


}
