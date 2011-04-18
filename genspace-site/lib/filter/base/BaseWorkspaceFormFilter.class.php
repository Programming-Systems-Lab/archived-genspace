<?php

/**
 * Workspace filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseWorkspaceFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'title'          => new sfWidgetFormFilterInput(),
      'creator'        => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'description'    => new sfWidgetFormFilterInput(),
      'location'       => new sfWidgetFormFilterInput(),
      'createdAt'      => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
      'version'        => new sfWidgetFormFilterInput(),
      'lastSync'       => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
      'locked'         => new sfWidgetFormFilterInput(),
      'lastLockedUser' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'title'          => new sfValidatorPass(array('required' => false)),
      'creator'        => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'ID')),
      'description'    => new sfValidatorPass(array('required' => false)),
      'location'       => new sfValidatorPass(array('required' => false)),
      'createdAt'      => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'version'        => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'lastSync'       => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'locked'         => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'lastLockedUser' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('workspace_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Workspace';
  }

  public function getFields()
  {
    return array(
      'id'             => 'Number',
      'title'          => 'Text',
      'creator'        => 'ForeignKey',
      'description'    => 'Text',
      'location'       => 'Text',
      'createdAt'      => 'Date',
      'version'        => 'Number',
      'lastSync'       => 'Date',
      'locked'         => 'Number',
      'lastLockedUser' => 'Number',
    );
  }
}
