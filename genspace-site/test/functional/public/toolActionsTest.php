<?php

include(dirname(__FILE__).'/../../bootstrap/functional.php');

// create a new test browser
$browser = new sfTestBrowser();

$browser->
  get('/tool/index')->
  isStatusCode(200)->
  isRequestParameter('module', 'tool')->
  isRequestParameter('action', 'index')->
  checkResponseElement('body', '!/This is a temporary page/');
