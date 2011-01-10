<?php

/**
 * ToolComments form base class.
 *
 * @method ToolComments getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseToolCommentsForm extends BaseFormPropel
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
      'pk'        => new sfValidatorPropelChoice(array('model' => 'ToolComments', 'column' => 'pk', 'required' => false)),
      'id'        => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647)),
      'comment'   => new sfValidatorString(array('max_length' => 2147483647)),
      'username'  => new sfValidatorString(array('max_length' => 200)),
      'posted_on' => new sfValidatorDateTime(),
    ));



    $this->widgetSchema->setNameFormat('tool_comments[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'ToolComments';
  }


}
