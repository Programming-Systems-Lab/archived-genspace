<?php

/**
 * Analysiseventparameter filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseAnalysiseventparameterFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'PARAMETERVALUE' => new sfWidgetFormFilterInput(),
      'PARAMETERKEY'   => new sfWidgetFormFilterInput(),
      'EVENT_ID'       => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'PARAMETERVALUE' => new sfValidatorPass(array('required' => false)),
      'PARAMETERKEY'   => new sfValidatorPass(array('required' => false)),
      'EVENT_ID'       => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('analysiseventparameter_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Analysiseventparameter';
  }

  public function getFields()
  {
    return array(
      'ID'             => 'Number',
      'PARAMETERVALUE' => 'Text',
      'PARAMETERKEY'   => 'Text',
      'EVENT_ID'       => 'Number',
    );
  }
}
