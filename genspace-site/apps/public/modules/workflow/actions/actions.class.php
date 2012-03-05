<?php

/**
 * workflow actions.
 *
 * @package    genspace_site
 * @subpackage tool
 * @author     Josh Nankin
 * @version    SVN: $Id: actions.class.php,v 1.3 2009-12-23 18:46:17 swapneel Exp $
 */
class workflowActions extends sfActions
{
 /**
  * Executes index action
  *
  * @param sfRequest $request A request object
  */
  public function executeIndex($request)
  {
  	$this->workflowId = $this->getRequestParameter('id');  	
	if($_GET['flash'] != null)
		$this->getUser()->setFlash('error', $_GET['flash']);
  	if ($this->workflowId){

  		$this->getResponse()->addJavascript('prototype.lite.js');
  		$this->getResponse()->addStylesheet('stars');
  		$this->getResponse()->addJavascript('stars');
  		
  		if ($request->getParameter('comment')){
	  		$comment = new WorkflowComment();
	  		$comment->setCreatorId($this->getUser()->getAttribute('username'));
	  		$comment->setComment($request->getParameter('comment'));
	  		$comment->setWorkflowId($this->workflowId);
			  $comment->setCreatedat(date("Y-m-d H:i:s"));
	  		$comment->save();
	  		$this->getUser()->setFlash('comment_msg', 'Comment posted!');
  		}
  		else if ($request->getParameter('rating')){
  			$rating = new WorkflowRating();
	  		$rating->setCreatorId($this->getUser()->getAttribute('username'));
	  		$rating->setRating($request->getParameter('rating'));
	  		$rating->setWorkflowId($this->workflowId);
			$rating->setCreatedat(date("Y-m-d H:i:s"));

	  		$rating->save();
	  		$this->getUser()->setFlash('rating_msg', 'Rating submitted!');
  		}

	  $wkflw = WorkflowPeer::retrieveByPK($this->workflowId);
  		$this->rating = WorkflowRatingPeer::getWorkflowRating($this->getUser()->getAttribute('username'), $this->workflowId);
  		$this->wfName = $wkflw->getName();
		$this->wf = $wkflw;
		  
  		$this->getResponse()->setTitle("Workflow Detail");

		  $c = new Criteria();
		  $c->add(WorkflowCommentPeer::WORKFLOW_ID,$this->workflowId);

  		$this->comments = WorkflowCommentPeer::doSelect($c);
				//  getWorkflowComments($this->getUser()->getAttribute('username'), $this->workflowId);
  	}
  }
    public function executeLogin(sfwebrequest $request)
	{
		$this->form = new LoginForm();

		$redirectBase = $_SERVER['HTTP_REFERER'];
		if(strrpos($redirectBase,"?") > 0)
			$redirectBase = substr($redirectBase,0,strrpos($redirectBase,"?"));
		
		$this->forward404Unless($request->isMethod('post'));
		$params = array(
			'username' => $request->getPostParameter('login[username]'),
			'password' => $request->getPostParameter('login[password]'),

		);
		$this->form->bind($params);
		if ($request->getPostParameter('login[username]') == NULL) {
			$this->getUser()->setFlash('nouser', 'Username Required!');
			$this->getUser()->setAuthenticated(false);
			$this->redirect($redirectBase."?flash=Username+Required");
//			$this->forward('tool', 'index');


		}
		if ($request->getPostParameter('login[password]') == NULL) {
			$this->getUser()->setFlash('nopass', 'Password Required!');
			$this->getUser()->setAuthenticated(false);
//			$this->forward('tool', 'index');
			$this->redirect($redirectBase."?flash=Password+Required");


		}

		if ($this->form->isValid()) {
			$username = $this->form->getValue('username');
			$password = $this->form->getValue('password');
			$c = new Criteria();
			$c->add(RegistrationPeer::USERNAME, $username);
			$c->add(RegistrationPeer::PASSWORD, sha1($password));
			$result = RegistrationPeer::doSelect($c);

			if ($result) {
				$this->getUser()->setAttribute('username', $result[0]->getId());
				$this->getUser()->setAttribute('name', $result[0]->getFirstName() . " " . $result[0]->getLastName());
				$this->getUser()->setAuthenticated(true);
				$this->getUser()->setFlash('msg', 'Login successful!');
				$cacheDir = sfConfig::get('sf_cache_dir') . '/public/prod/';

				//Clear cache
				$cache = new sfFileCache(array('cache_dir' => $cacheDir));
				$cache->clean();
//				$this->forward('tool', 'index');
				$this->redirect($redirectBase);

			}
			else {
				$this->getUser()->setFlash('error', 'Login failed! Check username and password and try again.');
				$this->getUser()->setAuthenticated(false);
				$this->redirect($redirectBase."?flash=Login+failed.+Check+username+and+password+and+try+again");

//				$this->forward('tool', 'index');
			}


		}
		// $this->forward('tool', 'index');

	}

	public function executeForgotpass(sfwebrequest $request)
	{

		$email = $request->getPostParameter('email');
		if ($email != NULL) {

			$result = RegistrationPeer::resetPassword($email);
			$this->getMailer()->composeAndSend('ddc2119@columbia.edu', $email, 'Genspace Forgot Password', 'The password for ' . $email . ' is ' . $result);
			$this->redirect('tool/index');

		}
	}

	public function executeLogout(sfwebrequest $request)
	{

		if ($this->getUser()->isAuthenticated()) {

			$redirectBase = $_SERVER['HTTP_REFERER'];
		if(strrpos($redirectBase,"?") > 0)
			$redirectBase = substr($redirectBase,0,strrpos($redirectBase,"?"));
			
			$this->getUser()->getAttributeHolder()->clear();
			$this->getUser()->setAuthenticated(false);
			$this->getUser()->setFlash('loggedout', 'You are logged out.');
			$cacheDir = sfConfig::get('sf_cache_dir') . '/public/prod/';

			//Clear cache
			$cache = new sfFileCache(array('cache_dir' => $cacheDir));
			$cache->clean();

			$cacheDir = sfConfig::get('sf_cache_dir') . '/public/dev/';

			//Clear cache
			$cache = new sfFileCache(array('cache_dir' => $cacheDir));
			$cache->clean();

//			$this->forward('tool', 'index');

			$this->redirect($redirectBase);
		}
	}
}
