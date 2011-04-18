<?php

/**
 * Network filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseNetworkFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'NAME'  => new sfWidgetFormFilterInput(),
      'owner' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'NAME'  => new sfValidatorPass(array('required' => false)),
      'owner' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('network_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Network';
  }

  public function getFields()
  {
    return array(
      'ID'    => 'Number',
      'NAME'  => 'Text',
      'owner' => 'Number',
    );
  }
}
