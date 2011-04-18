<?php

/**
 * Analysiseventparameter form base class.
 *
 * @method Analysiseventparameter getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseAnalysiseventparameterForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'             => new sfWidgetFormInputHidden(),
      'PARAMETERVALUE' => new sfWidgetFormInputText(),
      'PARAMETERKEY'   => new sfWidgetFormInputText(),
      'EVENT_ID'       => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'             => new sfValidatorPropelChoice(array('model' => 'Analysiseventparameter', 'column' => 'ID', 'required' => false)),
      'PARAMETERVALUE' => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'PARAMETERKEY'   => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'EVENT_ID'       => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'AnalysiseventparameterPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('analysiseventparameter[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Analysiseventparameter';
  }


}
