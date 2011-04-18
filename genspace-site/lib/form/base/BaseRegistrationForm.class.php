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
      'ID'              => new sfWidgetFormInputHidden(),
      'PHONE'           => new sfWidgetFormInputText(),
      'INTERESTS'       => new sfWidgetFormTextarea(),
      'STATE'           => new sfWidgetFormInputText(),
      'online_status'   => new sfWidgetFormInputText(),
      'PASSWORD'        => new sfWidgetFormInputText(),
      'CITY'            => new sfWidgetFormInputText(),
      'USERNAME'        => new sfWidgetFormInputText(),
      'CREATEDAT'       => new sfWidgetFormDateTime(),
      'first_name'      => new sfWidgetFormInputText(),
      'DATAVISIBILITY'  => new sfWidgetFormInputText(),
      'work_title'      => new sfWidgetFormInputText(),
      'last_name'       => new sfWidgetFormInputText(),
      'ZIPCODE'         => new sfWidgetFormInputText(),
      'lab_affiliation' => new sfWidgetFormInputText(),
      'ADDR1'           => new sfWidgetFormInputText(),
      'ADDR2'           => new sfWidgetFormInputText(),
      'EMAIL'           => new sfWidgetFormInputText(),
      'LOGDATA'         => new sfWidgetFormInputText(),
      'ROOTFOLDER_ID'   => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'              => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'ID', 'required' => false)),
      'PHONE'           => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'INTERESTS'       => new sfValidatorString(array('required' => false)),
      'STATE'           => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'online_status'   => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'PASSWORD'        => new sfValidatorString(array('max_length' => 50)),
      'CITY'            => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'USERNAME'        => new sfValidatorString(array('max_length' => 50)),
      'CREATEDAT'       => new sfValidatorDateTime(array('required' => false)),
      'first_name'      => new sfValidatorString(array('max_length' => 50)),
      'DATAVISIBILITY'  => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'work_title'      => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'last_name'       => new sfValidatorString(array('max_length' => 50)),
      'ZIPCODE'         => new sfValidatorString(array('max_length' => 5, 'required' => false)),
      'lab_affiliation' => new sfValidatorString(array('max_length' => 100, 'required' => false)),
      'ADDR1'           => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'ADDR2'           => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'EMAIL'           => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'LOGDATA'         => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'ROOTFOLDER_ID'   => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'RegistrationPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('registration[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Registration';
  }


}
