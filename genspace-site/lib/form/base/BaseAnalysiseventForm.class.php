<?php

/**
 * Analysisevent form base class.
 *
 * @method Analysisevent getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseAnalysiseventForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'             => new sfWidgetFormInputHidden(),
      'CREATEDAT'      => new sfWidgetFormDateTime(),
      'TRANSACTION_ID' => new sfWidgetFormInputText(),
      'TOOL_ID'        => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'             => new sfValidatorPropelChoice(array('model' => 'Analysisevent', 'column' => 'ID', 'required' => false)),
      'CREATEDAT'      => new sfValidatorDateTime(array('required' => false)),
      'TRANSACTION_ID' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'TOOL_ID'        => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'AnalysiseventPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('analysisevent[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Analysisevent';
  }


}
