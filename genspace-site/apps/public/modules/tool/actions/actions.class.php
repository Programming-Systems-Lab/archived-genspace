<?php

/**
 * tool actions.
 *
 * @package    genspace_site
 * @subpackage tool
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php,v 1.3 2009-12-23 18:46:17 swapneel Exp $
 */

class toolActions extends sfActions
{
 /**
  * Executes index action
  *
  * @param sfRequest $request A request object
  */
  
  public function executeIndex(sfwebrequest  $request)
    {
	   $this->form= new ToolCommentsForm();
	    	$this->toolId = $this->getRequestParameter('id');  	
			$this->user = $this->getRequestParameter('user');  
   if($this->toolId==NULL){
	   if($this->user==NULL){
$this->displayAll = true;
		$this->getResponse()->setTitle("geWorkbench Tools ");
		$c = new Criteria();
		$c->addAscendingOrderByColumn(ToolsPeer::TOOL);
		$this->tools = ToolsPeer::doSelect($c);
	   }
	   else{
		   $this->displayUser=true;
		   $this->comments = ToolRatingsPeer::getUserComments($this->getUser()->getAttribute('username'));
 
	//		$this->getResponse()->setTitle($this->user."'s Profile");
	   
	   }
   }
if($this->toolId!=NULL){
	  		$this->rating = ToolRatingsPeer::getToolRating($this->getUser()->getAttribute('username'), $this->toolId);
  		$this->tool = ToolsPeer::retrieveByPK($this->toolId);
  		$this->getResponse()->setTitle("About " . $this->tool->getTool());
  		$this->comments = ToolCommentsPeer::getToolComments($this->getUser()->getAttribute('username'), $this->toolId);
  }
  }
  
  public function executeDelete(sfwebrequest  $request){
	ToolRatingsPeer::deleteRating($this->getRequestParameter('user'), $this->getRequestParameter('id') );
	
 ToolRatingsPeer::deleteComment($this->getRequestParameter('user'), $this->getRequestParameter('id'));
 $cacheDir = sfConfig::get('sf_cache_dir').'/public/prod/';

           //Clear cache
            $cache = new sfFileCache(array('cache_dir' => $cacheDir));
           $cache->clean();
		
 $this->redirect('tool/index?user='.$this->getRequestParameter('user'));
  
  }
  public function executeEdit(sfwebrequest  $request){
	  
	ToolRatingsPeer::editRating($request->getPostParameter('comment[user]'), $request->getPostParameter('comment[id]'), $request->getPostParameter('comment[rating]') );
	
 ToolRatingsPeer::editComment($request->getPostParameter('comment[user]'), $request->getPostParameter('comment[id]'), $request->getPostParameter('comment[comment]'));
 $cacheDir = sfConfig::get('sf_cache_dir').'/public/prod/';

           //Clear cache
            $cache = new sfFileCache(array('cache_dir' => $cacheDir));
           $cache->clean();
		
 $this->redirect('tool/index?user='.$request->getPostParameter('comment[user]'));
  
  }
  

    public function executeComment(sfwebrequest  $request)
  {
	 // $this->form=new ToolCommentsForm();
	   	$this->toolId = $request->getPostParameter('comment[id]');  	
  
//  	if($request->isMethod('post')){
        $comm= $request->getPostParameter('comment[comment]');
		$rat= $request->getPostParameter('comment[rating]');
		  
		if ($comm!=NULL&&$rat!=NULL){
		  	$this->numcomments = ToolRatingsPeer::getUserComment($this->getUser()->getAttribute('username'), $this->toolId);
			 	$this->numratings = ToolRatingsPeer::getUserRating($this->getUser()->getAttribute('username'), $this->toolId);
		if($numcomments==0&&$numratings==0){
  		$comment = new ToolComments();
	  		$comment->setUsername($this->getUser()->getAttribute('username'));
	  		$comment->setComment($comm);
	  		$comment->setId($this->toolId);
			$comment->setPostedOn(time());
	  		$comment->save();
			$rating = new ToolRatings();
	  		$rating->setUsername($this->getUser()->getAttribute('username'));
	  		$rating->setRating($rat);
	  		$rating->setId($this->toolId);
	  		$rating->save();
			$feed = new sfAtom1Feed();
	
  
	  		$this->getUser()->setFlash('rating_msg', 'Rating submitted!');
			$cacheDir = sfConfig::get('sf_cache_dir').'/public/prod/';

           //Clear cache
            $cache = new sfFileCache(array('cache_dir' => $cacheDir));
           $cache->clean();
		}
			
  		}
			$this->forward('tool', 'index');
	  	
	//	}
  		  		
  
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
				  	$this->getUser()->setAuthenticated(false);

			$this->forward('tool','index');
 	
  
			
		}
		if($request->getPostParameter('login[password]')==NULL){
				$this->getUser()->setFlash('nopass', 'Password Required!');
				  	$this->getUser()->setAuthenticated(false);
             $this->forward('tool','index');
 	
			
		}
		
		if ($this->form->isValid()) {
			$username=$this->form->getValue('username');
	        $password=$this->form->getValue('password');
			  $c = new Criteria();
	        $c->add(RegistrationPeer::USERNAME, $username); 
            $c->add(RegistrationPeer::PASSWORD, sha1($password));
	        $result = RegistrationPeer::doSelect($c);
  		
if ($result){
		$this->getUser()->setAttribute('username', $username);
 	$this->getUser()->setAttribute('name', $result[0]->getFirstName() . " " . $result[0]->getLastName());
  	$this->getUser()->setAuthenticated(true);
		$this->getUser()->setFlash('msg', 'Login successful!');
   $cacheDir = sfConfig::get('sf_cache_dir').'/public/prod/';

  //Clear cache
  $cache = new sfFileCache(array('cache_dir' => $cacheDir));
  $cache->clean();
	$this->forward('tool','index');
 		
  	}
 	else {
  	$this->getUser()->setFlash('error', 'Login failed! Check username and password and try again.');
  	$this->getUser()->setAuthenticated(false);
	$this->forward('tool','index');
 		}
  
   
}
  // $this->forward('tool', 'index');

     }
	 
	  public function executeForgotpass(sfwebrequest $request)
  {	 
  
    $email=$request->getPostParameter('email');
     	if( $email!=NULL){
			  
		$result = RegistrationPeer::resetPassword($email);
	$this->getMailer()->composeAndSend('ddc2119@columbia.edu', $email, 'Genspace Forgot Password', 'The password for '.$email.' is '.$result);
	$this->redirect('tool/index');
 	
		}
     }
	 
	 public function executeLogout(sfwebrequest $request){
  	
  	if ($this->getUser()->isAuthenticated()){
  		$this->getUser()->getAttributeHolder()->clear();
  		$this->getUser()->setAuthenticated(false);
  		$this->getUser()->setFlash('loggedout', 'You are logged out.');	
  	 $cacheDir = sfConfig::get('sf_cache_dir').'/public/prod/';

  //Clear cache
  $cache = new sfFileCache(array('cache_dir' => $cacheDir));
  $cache->clean();
		$this->forward('tool','index');
   
	}
  }
    
}
