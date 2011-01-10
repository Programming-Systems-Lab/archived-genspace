<?php

/**
 * ToolComments filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseToolCommentsFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'        => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'comment'   => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'username'  => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'posted_on' => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
    ));

    $this->setValidators(array(
      'id'        => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'comment'   => new sfValidatorPass(array('required' => false)),
      'username'  => new sfValidatorPass(array('required' => false)),
      'posted_on' => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
    ));

    $this->widgetSchema->setNameFormat('tool_comments_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'ToolComments';
  }

  public function getFields()
  {
    return array(
      'pk'        => 'Number',
      'id'        => 'Number',
      'comment'   => 'Text',
      'username'  => 'Text',
      'posted_on' => 'Date',
    );
  }
}
