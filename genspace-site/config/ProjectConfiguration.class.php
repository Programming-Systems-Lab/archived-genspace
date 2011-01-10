<?php

require_once 'C:\dev\sfproject\lib\vendor\symfony\lib\autoload\sfCoreAutoload.class.php';
sfCoreAutoload::register();

class ProjectConfiguration extends sfProjectConfiguration
{
  public function setup()
  {
	 
    $this->enablePlugins('sfPropel15Plugin');
    $this->enablePlugins('sfGuardPlugin');
    $this->enablePlugins('sfJqueryFormValidationPlugin');
    $this->enablePlugins('sfFeed2Plugin');
  }
}
