<?php

/**
 * Friends form base class.
 *
 * @method Friends getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseFriendsForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'user1' => new sfWidgetFormInputText(),
      'user2' => new sfWidgetFormInputText(),
      'id'    => new sfWidgetFormInputHidden(),
    ));

    $this->setValidators(array(
      'user1' => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'user2' => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'id'    => new sfValidatorPropelChoice(array('model' => 'Friends', 'column' => 'id', 'required' => false)),
    ));


Warning: call_user_func(FriendsPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('friends[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Friends';
  }


}
