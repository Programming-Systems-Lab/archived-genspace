<?php

/**
 * WorkspaceUser form base class.
 *
 * @method WorkspaceUser getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseWorkspaceUserForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'    => new sfWidgetFormInputHidden(),
      'wspid' => new sfWidgetFormPropelChoice(array('model' => 'Workspace', 'add_empty' => true)),
      'uid'   => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'gid'   => new sfWidgetFormPropelChoice(array('model' => 'Access', 'add_empty' => true)),
    ));

    $this->setValidators(array(
      'id'    => new sfValidatorPropelChoice(array('model' => 'WorkspaceUser', 'column' => 'id', 'required' => false)),
      'wspid' => new sfValidatorPropelChoice(array('model' => 'Workspace', 'column' => 'id', 'required' => false)),
      'uid'   => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'ID', 'required' => false)),
      'gid'   => new sfValidatorPropelChoice(array('model' => 'Access', 'column' => 'id', 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'WorkspaceUserPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('workspace_user[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'WorkspaceUser';
  }


}
