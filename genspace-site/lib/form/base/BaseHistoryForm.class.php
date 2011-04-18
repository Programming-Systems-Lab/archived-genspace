<?php

/**
 * History form base class.
 *
 * @method History getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseHistoryForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'         => new sfWidgetFormInputHidden(),
      'wspid'      => new sfWidgetFormPropelChoice(array('model' => 'Workspace', 'add_empty' => true)),
      'uid'        => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'type'       => new sfWidgetFormInputText(),
      'accessedAt' => new sfWidgetFormDateTime(),
    ));

    $this->setValidators(array(
      'id'         => new sfValidatorPropelChoice(array('model' => 'History', 'column' => 'id', 'required' => false)),
      'wspid'      => new sfValidatorPropelChoice(array('model' => 'Workspace', 'column' => 'id', 'required' => false)),
      'uid'        => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'ID', 'required' => false)),
      'type'       => new sfValidatorString(array('max_length' => 1, 'required' => false)),
      'accessedAt' => new sfValidatorDateTime(),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'HistoryPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('history[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'History';
  }


}
