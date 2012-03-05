<?php

/**
 * Tool form base class.
 *
 * @method Tool getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseToolForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'                        => new sfWidgetFormInputHidden(),
      'MOSTCOMMONPARAMETERS'      => new sfWidgetFormInputText(),
      'USAGECOUNT'                => new sfWidgetFormInputText(),
      'DESCRIPTION'               => new sfWidgetFormInputText(),
      'NAME'                      => new sfWidgetFormInputText(),
      'MOSTCOMMONPARAMETERSCOUNT' => new sfWidgetFormInputText(),
      'WFCOUNTHEAD'               => new sfWidgetFormInputText(),
      'NUMRATING'                 => new sfWidgetFormInputText(),
      'SUMRATING'                 => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'                        => new sfValidatorPropelChoice(array('model' => 'Tool', 'column' => 'ID', 'required' => false)),
      'MOSTCOMMONPARAMETERS'      => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'USAGECOUNT'                => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'DESCRIPTION'               => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'NAME'                      => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'MOSTCOMMONPARAMETERSCOUNT' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'WFCOUNTHEAD'               => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'NUMRATING'                 => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'SUMRATING'                 => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'ToolPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('tool[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Tool';
  }


}
