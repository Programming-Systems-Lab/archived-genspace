<?php
class ContactForm extends BaseForm
{
  public function configure()
  {
    $this->setWidgets(array(
      'username'    => new sfWidgetFormInputText(),
      'password'   => new sfWidgetFormInputText(),
    ));
  }
}
?>