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
  	
  	if ($this->workflowId){
  		$this->getResponse()->addJavascript('prototype.lite.js');
  		$this->getResponse()->addStylesheet('stars');
  		$this->getResponse()->addJavascript('stars');
  		
  		if ($request->getParameter('comment')){
	  		$comment = new WorkflowComments();
	  		$comment->setCreatorId($this->getUser()->getAttribute('username'));
	  		$comment->setComment($request->getParameter('comment'));
	  		$comment->setWorkflowId($this->workflowId);
	  		$comment->save();
	  		$this->getUser()->setFlash('comment_msg', 'Comment posted!');
  		}
  		else if ($request->getParameter('rating')){
  			$rating = new WorkflowRatings();
	  		$rating->setCreatorId($this->getUser()->getAttribute('username'));
	  		$rating->setRating($request->getParameter('rating'));
	  		$rating->setWorkflowId($this->workflowId);
	  		$rating->save();
	  		$this->getUser()->setFlash('rating_msg', 'Rating submitted!');
  		}
  		  		
  		$this->rating = WorkflowRatingsPeer::getWorkflowRating($this->getUser()->getAttribute('username'), $this->workflowId);
  		$this->wfName = WorkflowsPeer::getWorkflowName($this->workflowId);
  		$this->getResponse()->setTitle("Workflow Detail");
  		$this->comments = WorkflowCommentsPeer::getWorkflowComments($this->getUser()->getAttribute('username'), $this->workflowId);
  	}
  }
    
}
