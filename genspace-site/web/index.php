<?php

#header('Location:  http://lenox.cs.columbia.edu/mediawiki/index.php/Main_Page');


require_once(dirname(__FILE__).'/../config/ProjectConfiguration.class.php');

$configuration = ProjectConfiguration::getApplicationConfiguration('public', 'prod', false);
sfContext::createInstance($configuration)->dispatch();

