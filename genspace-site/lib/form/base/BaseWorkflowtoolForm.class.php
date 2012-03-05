<?php

/**
 * Workflowtool form base class.
 *
 * @method Workflowtool getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseWorkflowtoolForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'          => new sfWidgetFormInputHidden(),
      'CARDINALITY' => new sfWidgetFormInputText(),
      'WORKFLOW_ID' => new sfWidgetFormInputText(),
      'TOOL_ID'     => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'          => new sfValidatorPropelChoice(array('model' => 'Workflowtool', 'column' => 'ID', 'required' => false)),
      'CARDINALITY' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'WORKFLOW_ID' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'TOOL_ID'     => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'WorkflowtoolPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('workflowtool[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflowtool';
  }


}
