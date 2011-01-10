<?php

/**
 * Networks filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseNetworksFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'network' => new sfWidgetFormFilterInput(array('with_empty' => false)),
    ));

    $this->setValidators(array(
      'network' => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('networks_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Networks';
  }

  public function getFields()
  {
    return array(
      'id'      => 'Number',
      'network' => 'Text',
    );
  }
}
