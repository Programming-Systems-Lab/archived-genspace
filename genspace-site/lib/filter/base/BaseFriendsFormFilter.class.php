<?php

/**
 * Friends filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseFriendsFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'user1' => new sfWidgetFormFilterInput(),
      'user2' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'user1' => new sfValidatorPass(array('required' => false)),
      'user2' => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('friends_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Friends';
  }

  public function getFields()
  {
    return array(
      'user1' => 'Text',
      'user2' => 'Text',
      'id'    => 'Number',
    );
  }
}
