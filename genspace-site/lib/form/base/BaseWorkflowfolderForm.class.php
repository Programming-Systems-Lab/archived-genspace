<?php

/**
 * Workflowfolder form base class.
 *
 * @method Workflowfolder getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseWorkflowfolderForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'        => new sfWidgetFormInputHidden(),
      'NAME'      => new sfWidgetFormInputText(),
      'OWNER_ID'  => new sfWidgetFormInputText(),
      'PARENT_ID' => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'        => new sfValidatorPropelChoice(array('model' => 'Workflowfolder', 'column' => 'ID', 'required' => false)),
      'NAME'      => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'OWNER_ID'  => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'PARENT_ID' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'WorkflowfolderPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('workflowfolder[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workflowfolder';
  }


}
