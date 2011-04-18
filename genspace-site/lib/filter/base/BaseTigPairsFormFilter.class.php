<?php

/**
 * TigPairs filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseTigPairsFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'nid'  => new sfWidgetFormPropelChoice(array('model' => 'TigNodes', 'add_empty' => true)),
      'uid'  => new sfWidgetFormPropelChoice(array('model' => 'TigUsers', 'add_empty' => true)),
      'pkey' => new sfWidgetFormFilterInput(array('with_empty' => false)),
      'pval' => new sfWidgetFormFilterInput(),
    ));

    $this->setValidators(array(
      'nid'  => new sfValidatorPropelChoice(array('required' => false, 'model' => 'TigNodes', 'column' => 'nid')),
      'uid'  => new sfValidatorPropelChoice(array('required' => false, 'model' => 'TigUsers', 'column' => 'uid')),
      'pkey' => new sfValidatorPass(array('required' => false)),
      'pval' => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('tig_pairs_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'TigPairs';
  }

  public function getFields()
  {
    return array(
      'nid'  => 'ForeignKey',
      'uid'  => 'ForeignKey',
      'pkey' => 'Text',
      'pval' => 'Text',
      'id'   => 'Number',
    );
  }
}
