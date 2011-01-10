<?php

/**
 * TestAnalysisEvents filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseTestAnalysisEventsFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username'         => new sfWidgetFormFilterInput(),
      'host'             => new sfWidgetFormFilterInput(),
      'date'             => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
      'analysis'         => new sfWidgetFormFilterInput(),
      'dataset'          => new sfWidgetFormFilterInput(),
      'transaction_id'   => new sfWidgetFormFilterInput(),
      'is_genspace_user' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'username'         => new sfValidatorPass(array('required' => false)),
      'host'             => new sfValidatorPass(array('required' => false)),
      'date'             => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'analysis'         => new sfValidatorPass(array('required' => false)),
      'dataset'          => new sfValidatorPass(array('required' => false)),
      'transaction_id'   => new sfValidatorPass(array('required' => false)),
      'is_genspace_user' => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('test_analysis_events_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'TestAnalysisEvents';
  }

  public function getFields()
  {
    return array(
      'username'         => 'Text',
      'host'             => 'Text',
      'date'             => 'Date',
      'analysis'         => 'Text',
      'dataset'          => 'Text',
      'transaction_id'   => 'Text',
      'is_genspace_user' => 'Text',
      'id'               => 'Number',
    );
  }
}
