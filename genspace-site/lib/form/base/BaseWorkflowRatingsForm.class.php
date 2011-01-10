<?php

/**
 * WorkflowRatings form base class.
 *
 * @method WorkflowRatings getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseWorkflowRatingsForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'pk'       => new sfWidgetFormInputHidden(),
      'id'       => new sfWidgetFormInputText(),
      'username' => new sfWidgetFormInputText(),
      'rating'   => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'pk'       => new sfValidatorPropelChoice(array('model' => 'WorkflowRatings', 'column' => 'pk', 'required' => false)),
      'id'       => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
      'username' => new sfValidatorString(array('max_length' => 100)),
      'rating'   => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
    ));


Warning: call_user_func(WorkflowRatingsPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('workflow_ratings[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'WorkflowRatings';
  }


}
