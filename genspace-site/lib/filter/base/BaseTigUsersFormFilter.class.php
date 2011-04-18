<?php

/**
 * TigUsers filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseTigUsersFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'user_id'         => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'sha1_user_id'    => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'user_pw'         => new sfWidgetFormFilterInput(),
      'acc_create_time' => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
      'last_login'      => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
      'last_logout'     => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
      'online_status'   => new sfWidgetFormFilterInput(),
      'failed_logins'   => new sfWidgetFormFilterInput(),
      'account_status'  => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'user_id'         => new sfValidatorPass(array('required' => false)),
      'sha1_user_id'    => new sfValidatorPass(array('required' => false)),
      'user_pw'         => new sfValidatorPass(array('required' => false)),
      'acc_create_time' => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'last_login'      => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'last_logout'     => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'online_status'   => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'failed_logins'   => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'account_status'  => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('tig_users_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'TigUsers';
  }

  public function getFields()
  {
    return array(
      'uid'             => 'Number',
      'user_id'         => 'Text',
      'sha1_user_id'    => 'Text',
      'user_pw'         => 'Text',
      'acc_create_time' => 'Date',
      'last_login'      => 'Date',
      'last_logout'     => 'Date',
      'online_status'   => 'Number',
      'failed_logins'   => 'Number',
      'account_status'  => 'Number',
    );
  }
}
