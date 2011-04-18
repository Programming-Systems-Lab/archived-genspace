<?php

/**
 * ShortNews filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseShortNewsFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'publishing_time' => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
      'news_type'       => new sfWidgetFormFilterInput(),
      'author'          => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'subject'         => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'body'            => new sfWidgetFormFilterInput(array('with_empty' => false)),
    ));

    $this->setValidators(array(
      'publishing_time' => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDate(array('required' => false)), 'to_date' => new sfValidatorDate(array('required' => false)))),
      'news_type'       => new sfValidatorPass(array('required' => false)),
      'author'          => new sfValidatorPass(array('required' => false)),
      'subject'         => new sfValidatorPass(array('required' => false)),
      'body'            => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('short_news_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'ShortNews';
  }

  public function getFields()
  {
    return array(
      'snid'            => 'Number',
      'publishing_time' => 'Date',
      'news_type'       => 'Text',
      'author'          => 'Text',
      'subject'         => 'Text',
      'body'            => 'Text',
    );
  }
}
