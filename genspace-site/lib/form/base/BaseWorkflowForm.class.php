<?php

/**
 * Workflow form base class.
 *
 * @method Workflow getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseWorkflowForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'                     => new sfWidgetFormInputHidden(),
      'USAGECOUNT'             => new sfWidgetFormInputText(),
      'CREATEDAT'              => new sfWidgetFormDateTime(),
      'CREATOR_ID'             => new sfWidgetFormInputText(),
      'PARENT_ID'              => new sfWidgetFormInputText(),
      'CREATIONTRANSACTION_ID' => new sfWidgetFormInputText(),
      'NUMRATING'              => new sfWidgetFormInputText(),
      'SUMRATING'              => new sfWidgetFormInputText(),
      'legacy_id'              => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'                     => new sfValidatorPropelChoice(array('model' => 'Workflow', 'column' => 'ID', 'required' => false)),
      'USAGECOUNT'             => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'CREATEDAT'              => new sfValidatorDateTime(array('required' => false)),
      'CREATOR_ID'             => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'PARENT_ID'              => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'CREATIONTRANSACTION_ID' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'NUMRATING'              => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'SUMRATING'              => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'legacy_id'              => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'WorkflowPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('workflow[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflow';
  }


}
