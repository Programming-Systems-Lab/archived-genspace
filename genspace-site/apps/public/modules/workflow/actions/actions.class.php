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
    
}
