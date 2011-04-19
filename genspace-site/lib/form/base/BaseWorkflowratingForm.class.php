<?php

/**
 * Workflowrating form base class.
 *
 * @method Workflowrating getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseWorkflowratingForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'          => new sfWidgetFormInputHidden(),
      'CREATEDAT'   => new sfWidgetFormDateTime(),
      'RATING'      => new sfWidgetFormInputText(),
      'WORKFLOW_ID' => new sfWidgetFormInputText(),
      'CREATOR_ID'  => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'          => new sfValidatorPropelChoice(array('model' => 'Workflowrating', 'column' => 'ID', 'required' => false)),
      'CREATEDAT'   => new sfValidatorDateTime(array('required' => false)),
      'RATING'      => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'WORKFLOW_ID' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'CREATOR_ID'  => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'WorkflowratingPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('workflowrating[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflowrating';
  }


}