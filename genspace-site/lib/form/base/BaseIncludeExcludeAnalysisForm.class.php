<?php

/**
 * IncludeExcludeAnalysis form base class.
 *
 * @method IncludeExcludeAnalysis getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseIncludeExcludeAnalysisForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'username' => new sfWidgetFormInputText(),
      'analysis' => new sfWidgetFormInputText(),
      'action'   => new sfWidgetFormInputText(),
      'id'       => new sfWidgetFormInputHidden(),
    ));

    $this->setValidators(array(
      'username' => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'analysis' => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'action'   => new sfValidatorString(array('max_length' => 50, 'required' => false)),
      'id'       => new sfValidatorPropelChoice(array('model' => 'IncludeExcludeAnalysis', 'column' => 'id', 'required' => false)),
    ));


Warning: call_user_func(IncludeExcludeAnalysisPeer::getUniqueColumnNames): First argument is expected to be a valid callback in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in C:\dev\sfproject\plugins\sfPropel15Plugin\lib\generator\sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('include_exclude_analysis[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'IncludeExcludeAnalysis';
  }


}
