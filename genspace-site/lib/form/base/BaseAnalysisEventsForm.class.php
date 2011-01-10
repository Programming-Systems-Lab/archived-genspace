<?php

/**
 * AnalysisEvents form base class.
 *
 * @method AnalysisEvents getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseAnalysisEventsForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username'         => new sfWidgetFormInputText(),
      'host'             => new sfWidgetFormInputText(),
      'date'             => new sfWidgetFormDateTime(),
      'analysis'         => new sfWidgetFormInputText(),
      'dataset'          => new sfWidgetFormInputText(),
      'transaction_id'   => new sfWidgetFormInputText(),
      'is_genspace_user' => new sfWidgetFormInputText(),
      'id'               => new sfWidgetFormInputHidden(),
    ));

    $this->setValidators(array(
      'username'         => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'host'             => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'date'             => new sfValidatorDateTime(array('required' => false)),
      'analysis'         => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'dataset'          => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'transaction_id'   => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'is_genspace_user' => new sfValidatorString(array('max_length' => 1, 'required' => false)),
      'id'               => new sfValidatorPropelChoice(array('model' => 'AnalysisEvents', 'column' => 'id', 'required' => false)),
    ));


Warning: call_user_func(AnalysisEventsPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('analysis_events[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'AnalysisEvents';
  }


}
