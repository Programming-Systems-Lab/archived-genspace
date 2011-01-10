<?php

/**
 * login actions.
 *
 * @package    sfproject
 * @subpackage login
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
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
$this->form = new LoginForm();

  }
  function clear_cache ($app, $env)
{
  $cacheDir = sfConfig::get('sf_cache_dir').'/'. $app.'/'.$env.'/';

  //Clear cache
  $cache = new sfFileCache(array('cache_dir' => $cacheDir));
  $cache->clean();
}
  public function executeLogin(sfwebrequest $request)
  {	 $this->form = new LoginForm();
	
	  $this->forward404Unless($request->isMethod('post'));
$params = array(
		    'username'    => $request->getPostParameter('login[username]'),
		    'password'   => $request->getPostParameter('login[password]'),
		  
);
	 	$this->form->bind($params);	
     	if( $request->getPostParameter('login[username]')==NULL){
				$this->getUser()->setFlash('nouser', 'Username Required!');
				$this->redirect('tool/index');
 	
  
			
		}
		if($request->getPostParameter('login[password]')==NULL){
				$this->getUser()->setFlash('nopass', 'Password Required!');
			$this->redirect('tool/index');
 		
  
			
		}
		
		if ($this->form->isValid()) {
			$username=$this->form->getValue('username');
	        $password=$this->form->getValue('password');
			  $c = new Criteria();
	        $c->add(RegistrationPeer::USERNAME, $username); 
            $c->add(RegistrationPeer::PASSWORD, $password);
	        $result = RegistrationPeer::doSelect($c);
  		
if ($result){
		$this->getUser()->setAttribute('username', $username);
 	$this->getUser()->setAttribute('name', $result[0]->getFirstName() . " " . $result[0]->getLastName());
  	$this->getUser()->setAuthenticated(true);
		$this->getUser()->setFlash('msg', 'Login successful!');
  
	$this->redirect('tool/index');
 		
  	}
 	else {
  	$this->getUser()->setFlash('error', 'Login failed! Check username and password and try again.');
  	$this->getUser()->setAuthenticated(false);
	$this->redirect('tool/index');
 		}
  
   
}
  // $this->forward('tool', 'index');

     }
	 
	
	 
	  public function executeLogout(sfwebrequest $request){
  	
  	if ($this->getUser()->isAuthenticated()){
  		$this->getUser()->getAttributeHolder()->clear();
  		$this->getUser()->setAuthenticated(false);
  		$this->getUser()->setFlash('loggedout', 'You are logged out.');	
  	
		$this->redirect('tool/index');
   
	}
  	
   	
  	
  }
}
?>