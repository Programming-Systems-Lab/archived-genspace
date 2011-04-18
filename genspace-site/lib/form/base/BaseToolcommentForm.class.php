<?php

/**
 * Toolcomment form base class.
 *
 * @method Toolcomment getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseToolcommentForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'         => new sfWidgetFormInputHidden(),
      'CREATEDAT'  => new sfWidgetFormDateTime(),
      'COMMENT'    => new sfWidgetFormInputText(),
      'CREATOR_ID' => new sfWidgetFormInputText(),
      'TOOL_ID'    => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'         => new sfValidatorPropelChoice(array('model' => 'Toolcomment', 'column' => 'ID', 'required' => false)),
      'CREATEDAT'  => new sfValidatorDateTime(array('required' => false)),
      'COMMENT'    => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'CREATOR_ID' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'TOOL_ID'    => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));
    $this->widgetSchema->setNameFormat('toolcomment[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Toolcomment';
  }


}
