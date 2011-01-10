<?php

require_once(dirname(__FILE__).'/../config/ProjectConfiguration.class.php');

$configuration = ProjectConfiguration::getApplicationConfiguration('public', 'prod', true);
sfContext::createInstance($configuration)->dispatch();
