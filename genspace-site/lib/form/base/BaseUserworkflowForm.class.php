<?php

/**
 * Userworkflow form base class.
 *
 * @method Userworkflow getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseUserworkflowForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'          => new sfWidgetFormInputHidden(),
      'NAME'        => new sfWidgetFormInputText(),
      'WORKFLOW_ID' => new sfWidgetFormInputText(),
      'OWNER_ID'    => new sfWidgetFormInputText(),
      'FOLDER_ID'   => new sfWidgetFormInputText(),
      'CREATEDAT'   => new sfWidgetFormDateTime(),
    ));

    $this->setValidators(array(
      'ID'          => new sfValidatorPropelChoice(array('model' => 'Userworkflow', 'column' => 'ID', 'required' => false)),
      'NAME'        => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'WORKFLOW_ID' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'OWNER_ID'    => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'FOLDER_ID'   => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'CREATEDAT'   => new sfValidatorDateTime(array('required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'UserworkflowPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('userworkflow[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Userworkflow';
  }


}
