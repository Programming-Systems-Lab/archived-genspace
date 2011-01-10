<?php

/**
 * ToolRatings form base class.
 *
 * @method ToolRatings getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseToolRatingsForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'pk'       => new sfWidgetFormInputHidden(),
      'id'       => new sfWidgetFormInputText(),
      'rating'   => new sfWidgetFormInputText(),
      'username' => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'pk'       => new sfValidatorPropelChoice(array('model' => 'ToolRatings', 'column' => 'pk', 'required' => false)),
      'id'       => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
      'rating'   => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
      'username' => new sfValidatorString(array('max_length' => 200)),
    ));


Warning: call_user_func(ToolRatingsPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('tool_ratings[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'ToolRatings';
  }


}
