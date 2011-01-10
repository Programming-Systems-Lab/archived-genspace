<?php

/**
 * Registration filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseRegistrationFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'password'        => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'email'           => new sfWidgetFormFilterInput(),
      'im_email'        => new sfWidgetFormFilterInput(),
      'im_password'     => new sfWidgetFormFilterInput(),
      'first_name'      => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'last_name'       => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'work_title'      => new sfWidgetFormFilterInput(),
      'phone'           => new sfWidgetFormFilterInput(),
      'lab_affiliation' => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'addr1'           => new sfWidgetFormFilterInput(),
      'addr2'           => new sfWidgetFormFilterInput(),
      'city'            => new sfWidgetFormFilterInput(),
      'state'           => new sfWidgetFormFilterInput(),
      'zipcode'         => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'password'        => new sfValidatorPass(array('required' => false)),
      'email'           => new sfValidatorPass(array('required' => false)),
      'im_email'        => new sfValidatorPass(array('required' => false)),
      'im_password'     => new sfValidatorPass(array('required' => false)),
      'first_name'      => new sfValidatorPass(array('required' => false)),
      'last_name'       => new sfValidatorPass(array('required' => false)),
      'work_title'      => new sfValidatorPass(array('required' => false)),
      'phone'           => new sfValidatorPass(array('required' => false)),
      'lab_affiliation' => new sfValidatorPass(array('required' => false)),
      'addr1'           => new sfValidatorPass(array('required' => false)),
      'addr2'           => new sfValidatorPass(array('required' => false)),
      'city'            => new sfValidatorPass(array('required' => false)),
      'state'           => new sfValidatorPass(array('required' => false)),
      'zipcode'         => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('registration_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Registration';
  }

  public function getFields()
  {
    return array(
      'username'        => 'Text',
      'password'        => 'Text',
      'email'           => 'Text',
      'im_email'        => 'Text',
      'im_password'     => 'Text',
      'first_name'      => 'Text',
      'last_name'       => 'Text',
      'work_title'      => 'Text',
      'phone'           => 'Text',
      'lab_affiliation' => 'Text',
      'addr1'           => 'Text',
      'addr2'           => 'Text',
      'city'            => 'Text',
      'state'           => 'Text',
      'zipcode'         => 'Text',
    );
  }
}
