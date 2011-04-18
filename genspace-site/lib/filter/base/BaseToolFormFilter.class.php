<?php

/**
 * Tool filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseToolFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'MOSTCOMMONPARAMETERS'      => new sfWidgetFormFilterInput(),
      'USAGECOUNT'                => new sfWidgetFormFilterInput(),
      'DESCRIPTION'               => new sfWidgetFormFilterInput(),
      'NAME'                      => new sfWidgetFormFilterInput(),
      'MOSTCOMMONPARAMETERSCOUNT' => new sfWidgetFormFilterInput(),
      'WFCOUNTHEAD'               => new sfWidgetFormFilterInput(),
      'NUMRATING'                 => new sfWidgetFormFilterInput(),
      'SUMRATING'                 => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'MOSTCOMMONPARAMETERS'      => new sfValidatorPass(array('required' => false)),
      'USAGECOUNT'                => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'DESCRIPTION'               => new sfValidatorPass(array('required' => false)),
      'NAME'                      => new sfValidatorPass(array('required' => false)),
      'MOSTCOMMONPARAMETERSCOUNT' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'WFCOUNTHEAD'               => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'NUMRATING'                 => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'SUMRATING'                 => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('tool_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Tool';
  }

  public function getFields()
  {
    return array(
      'ID'                        => 'Number',
      'MOSTCOMMONPARAMETERS'      => 'Text',
      'USAGECOUNT'                => 'Number',
      'DESCRIPTION'               => 'Text',
      'NAME'                      => 'Text',
      'MOSTCOMMONPARAMETERSCOUNT' => 'Number',
      'WFCOUNTHEAD'               => 'Number',
      'NUMRATING'                 => 'Number',
      'SUMRATING'                 => 'Number',
    );
  }
}
