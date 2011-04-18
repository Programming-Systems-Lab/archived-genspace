<?php

/**
 * Friend filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseFriendFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'MUTUAL'  => new sfWidgetFormFilterInput(),
      'VISIBLE' => new sfWidgetFormFilterInput(),
      'id_1'    => new sfWidgetFormFilterInput(),
      'id_2'    => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'MUTUAL'  => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'VISIBLE' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'id_1'    => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'id_2'    => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('friend_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Friend';
  }

  public function getFields()
  {
    return array(
      'ID'      => 'Number',
      'MUTUAL'  => 'Number',
      'VISIBLE' => 'Number',
      'id_1'    => 'Number',
      'id_2'    => 'Number',
    );
  }
}
