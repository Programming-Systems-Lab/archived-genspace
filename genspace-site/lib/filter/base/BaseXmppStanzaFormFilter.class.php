<?php

/**
 * XmppStanza filter form base class.
 *
 * @package    sfproject
 * @subpackage filter
 * @author     Your name here
 */
abstract class BaseXmppStanzaFormFilter extends BaseFormFilterPropel
{
  public function setup()
  {
    $this->setWidgets(array(
      'stanza' => new sfWidgetFormFilterInput(array('with_empty' => false)),
    ));

    $this->setValidators(array(
      'stanza' => new sfValidatorPass(array('required' => false)),
    ));

    $this->widgetSchema->setNameFormat('xmpp_stanza_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    parent::setup();
  }

  public function getModelName()
  {
    return 'XmppStanza';
  }

  public function getFields()
  {
    return array(
      'id'     => 'Number',
      'stanza' => 'Text',
    );
  }
}
