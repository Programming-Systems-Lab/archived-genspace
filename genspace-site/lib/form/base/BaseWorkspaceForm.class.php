<?php

/**
 * Workspace form base class.
 *
 * @method Workspace getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseWorkspaceForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'             => new sfWidgetFormInputHidden(),
      'title'          => new sfWidgetFormInputText(),
      'creator'        => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'description'    => new sfWidgetFormInputText(),
      'location'       => new sfWidgetFormInputText(),
      'createdAt'      => new sfWidgetFormDateTime(),
      'version'        => new sfWidgetFormInputText(),
      'lastSync'       => new sfWidgetFormDateTime(),
      'locked'         => new sfWidgetFormInputText(),
      'lastLockedUser' => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'id'             => new sfValidatorPropelChoice(array('model' => 'Workspace', 'column' => 'id', 'required' => false)),
      'title'          => new sfValidatorString(array('max_length' => 200, 'required' => false)),
      'creator'        => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'ID', 'required' => false)),
      'description'    => new sfValidatorString(array('max_length' => 1000, 'required' => false)),
      'location'       => new sfValidatorString(array('max_length' => 1000, 'required' => false)),
      'createdAt'      => new sfValidatorDateTime(),
      'version'        => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
      'lastSync'       => new sfValidatorDateTime(),
      'locked'         => new sfValidatorInteger(array('min' => -128, 'max' => 127, 'required' => false)),
      'lastLockedUser' => new sfValidatorInteger(array('min' => -2147483648, 'max' => 2147483647, 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'WorkspacePeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('workspace[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workspace';
  }


}
