<?php

/**
 * TigPairs form base class.
 *
 * @method TigPairs getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseTigPairsForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'nid'  => new sfWidgetFormPropelChoice(array('model' => 'TigNodes', 'add_empty' => true)),
      'uid'  => new sfWidgetFormPropelChoice(array('model' => 'TigUsers', 'add_empty' => false)),
      'pkey' => new sfWidgetFormInputText(),
      'pval' => new sfWidgetFormTextarea(),
      'id'   => new sfWidgetFormInputHidden(),
    ));

    $this->setValidators(array(
      'nid'  => new sfValidatorPropelChoice(array('model' => 'TigNodes', 'column' => 'nid', 'required' => false)),
      'uid'  => new sfValidatorPropelChoice(array('model' => 'TigUsers', 'column' => 'uid')),
      'pkey' => new sfValidatorString(array('max_length' => 255)),
      'pval' => new sfValidatorString(array('required' => false)),
      'id'   => new sfValidatorPropelChoice(array('model' => 'TigPairs', 'column' => 'id', 'required' => false)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'TigPairsPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/Projects/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('tig_pairs[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'TigPairs';
  }


}
