<?php

/**
 * WorkspaceUser filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseWorkspaceUserFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'wspid' => new sfWidgetFormPropelChoice(array('model' => 'Workspace', 'add_empty' => true)),
      'uid'   => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'gid'   => new sfWidgetFormPropelChoice(array('model' => 'Access', 'add_empty' => true)),
    ));

    $this->setValidators(array(
      'wspid' => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Workspace', 'column' => 'id')),
      'uid'   => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'ID')),
      'gid'   => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Access', 'column' => 'id')),
    ));

    $this->widgetSchema->setNameFormat('workspace_user_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'WorkspaceUser';
  }

  public function getFields()
  {
    return array(
      'id'    => 'Number',
      'wspid' => 'ForeignKey',
      'uid'   => 'ForeignKey',
      'gid'   => 'ForeignKey',
    );
  }
}
