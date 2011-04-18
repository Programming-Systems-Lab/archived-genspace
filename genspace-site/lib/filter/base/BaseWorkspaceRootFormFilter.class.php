<?php

/**
 * WorkspaceRoot filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseWorkspaceRootFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'location' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'location' => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('workspace_root_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'WorkspaceRoot';
  }

  public function getFields()
  {
    return array(
      'id'       => 'Number',
      'location' => 'Text',
    );
  }
}
