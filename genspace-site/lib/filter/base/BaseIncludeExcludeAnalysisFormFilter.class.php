<?php

/**
 * IncludeExcludeAnalysis filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseIncludeExcludeAnalysisFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username' => new sfWidgetFormFilterInput(),
      'analysis' => new sfWidgetFormFilterInput(),
      'action'   => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'username' => new sfValidatorPass(array('required' => false)),
      'analysis' => new sfValidatorPass(array('required' => false)),
      'action'   => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('include_exclude_analysis_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'IncludeExcludeAnalysis';
  }

  public function getFields()
  {
    return array(
      'username' => 'Text',
      'analysis' => 'Text',
      'action'   => 'Text',
      'id'       => 'Number',
    );
  }
}
