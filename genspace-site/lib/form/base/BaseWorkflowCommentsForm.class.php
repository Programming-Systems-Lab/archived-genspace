<?php

/**
 * WorkflowComments form base class.
 *
 * @method WorkflowComments getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseWorkflowCommentsForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'pk'        => new sfWidgetFormInputHidden(),
      'id'        => new sfWidgetFormInputText(),
      'comment'   => new sfWidgetFormTextarea(),
      'username'  => new sfWidgetFormInputText(),
      'posted_on' => new sfWidgetFormDateTime(),
    ));

    $this->setValidators(array(
      'pk'        => new sfValidatorPropelChoice(array('model' => 'WorkflowComments', 'column' => 'pk', 'required' => false)),
      'id'        => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
      'comment'   => new sfValidatorString(array('max_length' => 2147483647)),
      'username'  => new sfValidatorString(array('max_length' => 200)),
      'posted_on' => new sfValidatorDateTime(),
    ));


Warning: call_user_func(WorkflowCommentsPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('workflow_comments[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'WorkflowComments';
  }


}
