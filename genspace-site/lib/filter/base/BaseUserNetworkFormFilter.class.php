<?php

/**
 * UserNetwork filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseUserNetworkFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'VISIBLE'    => new sfWidgetFormFilterInput(),
      'VERIFIED'   => new sfWidgetFormFilterInput(),
      'network_id' => new sfWidgetFormFilterInput(),
      'user_id'    => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'VISIBLE'    => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'VERIFIED'   => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'network_id' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'user_id'    => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('user_network_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'UserNetwork';
  }

  public function getFields()
  {
    return array(
      'ID'         => 'Number',
      'VISIBLE'    => 'Number',
      'VERIFIED'   => 'Number',
      'network_id' => 'Number',
      'user_id'    => 'Number',
    );
  }
}
