<?php

/**
 * Inbox form base class.
 *
 * @method Inbox getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseInboxForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'MessageID' => new sfWidgetFormInputHidden(),
      'Date'      => new sfWidgetFormDateTime(),
      'FromUser'  => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => false)),
      'ToUser'    => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => false)),
      'Message'   => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'MessageID' => new sfValidatorPropelChoice(array('model' => 'Inbox', 'column' => 'MessageID', 'required' => false)),
      'Date'      => new sfValidatorDateTime(),
      'FromUser'  => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'username')),
      'ToUser'    => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'username')),
      'Message'   => new sfValidatorString(array('max_length' => 200)),
    ));


Warning: call_user_func(InboxPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('inbox[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Inbox';
  }


}
