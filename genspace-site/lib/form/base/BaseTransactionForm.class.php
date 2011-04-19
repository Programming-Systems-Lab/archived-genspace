<?php

/**
 * Transaction form base class.
 *
 * @method Transaction getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseTransactionForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'ID'          => new sfWidgetFormInputHidden(),
      'DATASETNAME' => new sfWidgetFormInputText(),
      'CLIENTID'    => new sfWidgetFormInputText(),
      'HOSTNAME'    => new sfWidgetFormInputText(),
      'DATE'        => new sfWidgetFormDateTime(),
      'USER_ID'     => new sfWidgetFormInputText(),
      'WORKFLOW_ID' => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'ID'          => new sfValidatorPropelChoice(array('model' => 'Transaction', 'column' => 'ID', 'required' => false)),
      'DATASETNAME' => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'CLIENTID'    => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'HOSTNAME'    => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'DATE'        => new sfValidatorDateTime(array('required' => false)),
      'USER_ID'     => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'WORKFLOW_ID' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'TransactionPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('transaction[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Transaction';
  }


}