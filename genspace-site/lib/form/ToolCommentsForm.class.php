<?php

/**
 * ToolComments form.
 *
 * @package    genspace_site
 * @subpackage form
 * @author     Your name here
 */
class ToolCommentsForm extends BaseToolCommentsForm
{
 
	  public function configure()
  {
    $this->setWidgets(array(
      'comment' => new sfWidgetFormTextarea()
     
   ));
  
   
  
    $this->widgetSchema->setNameFormat('comment[%s]');
	 $this->setValidators(array(
      'comment' => new sfValidatorString(array('required' => false))
    ));

  }

 
}
