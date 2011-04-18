<?php

/**
 * Toolratings form base class.
 *
 * @method Toolratings getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseToolratingsForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'         => new sfWidgetFormInputHidden(),
      'CREATEDAT'  => new sfWidgetFormDateTime(),
      'RATING'     => new sfWidgetFormInputText(),
      'TOOL_ID'    => new sfWidgetFormInputText(),
      'CREATOR_ID' => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
    ));

    $this->setValidators(array(
      'ID'         => new sfValidatorPropelChoice(array('model' => 'Toolratings', 'column' => 'ID', 'required' => false)),
      'CREATEDAT'  => new sfValidatorDateTime(array('required' => false)),
      'RATING'     => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'TOOL_ID'    => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'CREATOR_ID' => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'ID', 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'ToolRatingsPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('toolratings[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Toolratings';
  }


}
