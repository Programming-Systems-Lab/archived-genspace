<?php

/**
 * Toolrating filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseToolratingFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'CREATEDAT'  => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
      'RATING'     => new sfWidgetFormFilterInput(),
      'TOOL_ID'    => new sfWidgetFormFilterInput(),
      'CREATOR_ID' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'CREATEDAT'  => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'RATING'     => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'TOOL_ID'    => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'CREATOR_ID' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
    ));

    $this->widgetSchema->setNameFormat('toolrating_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Toolrating';
  }

  public function getFields()
  {
    return array(
      'ID'         => 'Number',
      'CREATEDAT'  => 'Date',
      'RATING'     => 'Number',
      'TOOL_ID'    => 'Number',
      'CREATOR_ID' => 'Number',
    );
  }
}
