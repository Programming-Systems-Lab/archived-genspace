<?php

/**
 * DataVisibility filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseDataVisibilityFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'logdata'        => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'datavisibility' => new sfWidgetFormFilterInput(array('with_empty' => false)),
    ));

    $this->setValidators(array(
      'logdata'        => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'datavisibility' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('data_visibility_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'DataVisibility';
  }

  public function getFields()
  {
    return array(
      'username'       => 'ForeignKey',
      'logdata'        => 'Number',
      'datavisibility' => 'Number',
    );
  }
}
