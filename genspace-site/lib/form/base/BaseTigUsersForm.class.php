<?php

/**
 * TigUsers form base class.
 *
 * @method TigUsers getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseTigUsersForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'uid'             => new sfWidgetFormInputHidden(),
      'user_id'         => new sfWidgetFormInputText(),
      'sha1_user_id'    => new sfWidgetFormInputText(),
      'user_pw'         => new sfWidgetFormInputText(),
      'acc_create_time' => new sfWidgetFormDateTime(),
      'last_login'      => new sfWidgetFormDateTime(),
      'last_logout'     => new sfWidgetFormDateTime(),
      'online_status'   => new sfWidgetFormInputText(),
      'failed_logins'   => new sfWidgetFormInputText(),
      'account_status'  => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'uid'             => new sfValidatorPropelChoice(array('model' => 'TigUsers', 'column' => 'uid', 'required' => false)),
      'user_id'         => new sfValidatorString(array('max_length' => 2049)),
      'sha1_user_id'    => new sfValidatorString(array('max_length' => 128)),
      'user_pw'         => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'acc_create_time' => new sfValidatorDateTime(),
      'last_login'      => new sfValidatorDateTime(),
      'last_logout'     => new sfValidatorDateTime(),
      'online_status'   => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'failed_logins'   => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'account_status'  => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'TigUsersPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('tig_users[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'TigUsers';
  }


}
