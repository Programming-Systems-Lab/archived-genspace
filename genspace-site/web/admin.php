<?php

if($_SERVER['REMOTE_ADDR'] != '127.0.0.1')
{
print "Not authorized.";
exit();
}
require_once(dirname(__FILE__).'/../config/ProjectConfiguration.class.php');

$configuration = ProjectConfiguration::getApplicationConfiguration('admin', 'prod', false);
sfContext::createInstance($configuration)->dispatch();
