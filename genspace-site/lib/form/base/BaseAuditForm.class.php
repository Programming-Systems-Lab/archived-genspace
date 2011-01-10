<?php

/**
 * Audit form base class.
 *
 * @method Audit getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseAuditForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username'    => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => false)),
      'action'      => new sfWidgetFormInputText(),
      'tablename'   => new sfWidgetFormInputText(),
      'beforevalue' => new sfWidgetFormInputText(),
      'aftervalue'  => new sfWidgetFormInputText(),
      'time'        => new sfWidgetFormDateTime(),
      'id'          => new sfWidgetFormInputHidden(),
    ));

    $this->setValidators(array(
      'username'    => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'username')),
      'action'      => new sfValidatorString(array('max_length' => 500)),
      'tablename'   => new sfValidatorString(array('max_length' => 50)),
      'beforevalue' => new sfValidatorString(array('max_length' => 1000)),
      'aftervalue'  => new sfValidatorString(array('max_length' => 1000)),
      'time'        => new sfValidatorDateTime(),
      'id'          => new sfValidatorPropelChoice(array('model' => 'Audit', 'column' => 'id', 'required' => false)),
    ));


Warning: call_user_func(AuditPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('audit[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Audit';
  }


}
