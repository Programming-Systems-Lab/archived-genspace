<?php

/**
 * Registration form base class.
 *
 * @method Registration getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseRegistrationForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username'        => new sfWidgetFormInputHidden(),
      'password'        => new sfWidgetFormInputText(),
      'email'           => new sfWidgetFormInputText(),
      'im_email'        => new sfWidgetFormInputText(),
      'im_password'     => new sfWidgetFormInputText(),
      'first_name'      => new sfWidgetFormInputText(),
      'last_name'       => new sfWidgetFormInputText(),
      'work_title'      => new sfWidgetFormInputText(),
      'phone'           => new sfWidgetFormInputText(),
      'lab_affiliation' => new sfWidgetFormInputText(),
      'addr1'           => new sfWidgetFormInputText(),
      'addr2'           => new sfWidgetFormInputText(),
      'city'            => new sfWidgetFormInputText(),
      'state'           => new sfWidgetFormInputText(),
      'zipcode'         => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'username'        => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'username', 'required' => false)),
      'password'        => new sfValidatorString(array('max_length' => 50)),
      'email'           => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'im_email'        => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'im_password'     => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'first_name'      => new sfValidatorString(array('max_length' => 50)),
      'last_name'       => new sfValidatorString(array('max_length' => 50)),
      'work_title'      => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'phone'           => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'lab_affiliation' => new sfValidatorString(array('max_length' => 100)),
      'addr1'           => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'addr2'           => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'city'            => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'state'           => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'zipcode'         => new sfValidatorString(array('max_length' => 5, 'required' => false)),
    ));


Warning: call_user_func(RegistrationPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('registration[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Registration';
  }


}
