<?php

/**
 * login actions.
 *
 * @package    genspace_site
 * @subpackage login
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php,v 1.3 2009-12-23 18:46:17 swapneel Exp $
 */
class loginActions extends sfActions
{
 /**
  * Executes index action
  *
  * @param sfRequest $request A request object
  */
  public function executeIndex($request)
  {
    
  }
  
  public function executeLogin($request){
  	
  	$username = $request->getParameter('username');
  	$password = $request->getParameter('password');
  	
  	if ($username && $password){
  		$c = new Criteria();
  		$c->add(RegistrationPeer::USERNAME, $username);
  		$c->add(RegistrationPeer::PASSWORD, sha1($password));
  		$result = RegistrationPeer::doSelect($c);
  		
  		if ($result){
  			$this->getUser()->setAttribute('username', $username);
  			$this->getUser()->setAttribute('name', $result[0]->getFirstName() . " " . $result[0]->getLastName());
  			$this->getUser()->setFlash('msg', 'Logged in as ' . $username);
  			$this->getUser()->setAuthenticated(true);
  		}
  		else {
  			$this->getUser()->setFlash('msg', 'Login failed!');
  			$this->getUser()->setAuthenticated(false);
  		}
  		
  	}
  	
  	$this->forward('login', 'index');
    	
  	
  }
  
  
  public function executeLogout($request){
  	
  	if ($this->getUser()->isAuthenticated()){
  		$this->getUser()->getAttributeHolder()->clear();
  		$this->getUser()->setAuthenticated(false);
  		$this->getUser()->setFlash('msg', 'Logged out.');	
  	}
  	
  	$this->forward('login', 'index');
    	
  	
  }
  
}
