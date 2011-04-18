<?php

/**
 * Annotation filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseAnnotationFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'wspid'      => new sfWidgetFormPropelChoice(array('model' => 'Workspace', 'add_empty' => true)),
      'annotation' => new sfWidgetFormFilterInput(),
      'creator'    => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'createdAt'  => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
    ));

    $this->setValidators(array(
      'wspid'      => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Workspace', 'column' => 'id')),
      'annotation' => new sfValidatorPass(array('required' => false)),
      'creator'    => new sfValidatorPropelChoice(array('required' => false, 'model' => 'Registration', 'column' => 'ID')),
      'createdAt'  => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
    ));

    $this->widgetSchema->setNameFormat('annotation_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Annotation';
  }

  public function getFields()
  {
    return array(
      'id'         => 'Number',
      'wspid'      => 'ForeignKey',
      'annotation' => 'Text',
      'creator'    => 'ForeignKey',
      'createdAt'  => 'Date',
    );
  }
}
