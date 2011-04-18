<?php

/**
 * Annotation form base class.
 *
 * @method Annotation getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseAnnotationForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'         => new sfWidgetFormInputHidden(),
      'wspid'      => new sfWidgetFormPropelChoice(array('model' => 'Workspace', 'add_empty' => true)),
      'annotation' => new sfWidgetFormInputText(),
      'creator'    => new sfWidgetFormPropelChoice(array('model' => 'Registration', 'add_empty' => true)),
      'createdAt'  => new sfWidgetFormDateTime(),
    ));

    $this->setValidators(array(
      'id'         => new sfValidatorPropelChoice(array('model' => 'Annotation', 'column' => 'id', 'required' => false)),
      'wspid'      => new sfValidatorPropelChoice(array('model' => 'Workspace', 'column' => 'id', 'required' => false)),
      'annotation' => new sfValidatorString(array('max_length' => 1000, 'required' => false)),
      'creator'    => new sfValidatorPropelChoice(array('model' => 'Registration', 'column' => 'ID', 'required' => false)),
      'createdAt'  => new sfValidatorDateTime(),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'AnnotationPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('annotation[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'Annotation';
  }


}
