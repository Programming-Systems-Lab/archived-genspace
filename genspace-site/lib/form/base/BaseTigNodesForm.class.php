<?php

/**
 * TigNodes form base class.
 *
 * @method TigNodes getObject() Returns the current form's model object
 *
 * @package    sfproject
 * @subpackage form
 * @author     Your name here
 */
abstract class BaseTigNodesForm extends BaseFormPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'nid'        => new sfWidgetFormInputHidden(),
      'parent_nid' => new sfWidgetFormInputText(),
      'uid'        => new sfWidgetFormPropelChoice(array('model' => 'TigUsers', 'add_empty' => false)),
      'node'       => new sfWidgetFormInputText(),
    ));

    $this->setValidators(array(
      'nid'        => new sfValidatorPropelChoice(array('model' => 'TigNodes', 'column' => 'nid', 'required' => false)),
      'parent_nid' => new sfValidatorInteger(array('min' => -9.2233720368548E+18, 'max' => 9223372036854775807, 'required' => false)),
      'uid'        => new sfValidatorPropelChoice(array('model' => 'TigUsers', 'column' => 'uid')),
      'node'       => new sfValidatorString(array('max_length' => 255)),
    ));


Warning: call_user_func() expects parameter 1 to be a valid callback, class 'TigNodesPeer' does not have a method 'getUniqueColumnNames' in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485

Warning: Invalid argument supplied for foreach() in /Users/jon/Documents/PSL/genspace/genspace-site/plugins/sfPropel15Plugin/lib/generator/sfPropelFormGenerator.class.php on line 485
    $this->widgetSchema->setNameFormat('tig_nodes[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'TigNodes';
  }


}
