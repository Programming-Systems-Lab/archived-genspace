<?php

/**
 * Setting filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseSettingFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'data_value' => new sfWidgetFormFilterInput(),
      'data_key'   => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'data_value' => new sfValidatorPass(array('required' => false)),
      'data_key'   => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('setting_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Setting';
  }

  public function getFields()
  {
    return array(
      'ID'         => 'Number',
      'data_value' => 'Text',
      'data_key'   => 'Text',
    );
  }
}
