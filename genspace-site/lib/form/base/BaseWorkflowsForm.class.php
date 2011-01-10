<?php

/**
 * Workflows form base class.
 *
 * @method Workflows getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseWorkflowsForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'     => new sfWidgetFormInputHidden(),
      'parent' => new sfWidgetFormInputText(),
      'tool'   => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'id'     => new sfValidatorPropelChoice(array('model' => 'Workflows', 'column' => 'id', 'required' => false)),
      'parent' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
      'tool'   => new sfValidatorString(array('max_length' => 200)),
    ));


Warning: call_user_func(WorkflowsPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('workflows[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflows';
  }


}
