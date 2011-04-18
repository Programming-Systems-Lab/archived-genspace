<?php

/**
 * TigNodes filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseTigNodesFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'parent_nid' => new sfWidgetFormFilterInput(),
      'uid'        => new sfWidgetFormPropelChoice(array('model' => 'TigUsers', 'add_empty' => true)),
      'node'       => new sfWidgetFormFilterInput(array('with_empty' => false)),
    ));

    $this->setValidators(array(
      'parent_nid' => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'uid'        => new sfValidatorPropelChoice(array('required' => false, 'model' => 'TigUsers', 'column' => 'uid')),
      'node'       => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('tig_nodes_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'TigNodes';
  }

  public function getFields()
  {
    return array(
      'nid'        => 'Number',
      'parent_nid' => 'Number',
      'uid'        => 'ForeignKey',
      'node'       => 'Text',
    );
  }
}
