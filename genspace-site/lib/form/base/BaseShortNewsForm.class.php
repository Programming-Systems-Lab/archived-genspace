<?php

/**
 * ShortNews form base class.
 *
 * @method ShortNews getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseShortNewsForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'snid'            => new sfWidgetFormInputHidden(),
      'publishing_time' => new sfWidgetFormDateTime(),
      'news_type'       => new sfWidgetFormInputText(),
      'author'          => new sfWidgetFormInputText(),
      'subject'         => new sfWidgetFormInputText(),
      'body'            => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'snid'            => new sfValidatorPropelChoice(array('model' => 'ShortNews', 'column' => 'snid', 'required' => false)),
      'publishing_time' => new sfValidatorDateTime(),
      'news_type'       => new sfValidatorString(array('max_length' => 10, 'required' => false)),
      'author'          => new sfValidatorString(array('max_length' => 128)),
      'subject'         => new sfValidatorString(array('max_length' => 128)),
      'body'            => new sfValidatorString(array('max_length' => 1024)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'ShortNewsPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('short_news[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'ShortNews';
  }


}
