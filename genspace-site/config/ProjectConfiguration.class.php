<?php

require_once '/Users/jon/Documents/PSL/projects/genspace/genspace-site/lib/vendor/symfony/lib/autoload/sfCoreAutoload.class.php';
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
