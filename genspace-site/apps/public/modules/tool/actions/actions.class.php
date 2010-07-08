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
  public function executeIndex($request)
  {
  	$this->toolId = $this->getRequestParameter('id');  	
  	
  	if ($this->toolId){
  		$this->getResponse()->addJavascript('prototype.lite.js');
  		$this->getResponse()->addStylesheet('stars');
  		$this->getResponse()->addJavascript('stars');
  		
  		if ($request->getParameter('comment')){
	  		$comment = new ToolComments();
	  		$comment->setUsername($this->getUser()->getAttribute('username'));
	  		$comment->setComment($request->getParameter('comment'));
	  		$comment->setId($this->toolId);
			$comment->setPostedOn(time());
	  		$comment->save();
	  		$this->getUser()->setFlash('comment_msg', 'Comment posted!');
  		}
  		else if ($request->getParameter('rating')){
  			$rating = new ToolRatings();
	  		$rating->setUsername($this->getUser()->getAttribute('username'));
	  		$rating->setRating($request->getParameter('rating'));
	  		$rating->setId($this->toolId);
	  		$rating->save();
	  		$this->getUser()->setFlash('rating_msg', 'Rating submitted!');
  		}
  		  		
  		$this->rating = ToolRatingsPeer::getToolRating($this->getUser()->getAttribute('username'), $this->toolId);
  		$this->tool = ToolsPeer::retrieveByPK($this->toolId);
  		$this->getResponse()->setTitle("About " . $this->tool->getTool());
  		$this->comments = ToolCommentsPeer::getToolComments($this->getUser()->getAttribute('username'), $this->toolId);
  	}
	else {
		$this->displayAll = true;
		$this->getResponse()->setTitle("geWorkbench Tools ");
		$c = new Criteria();
		$c->addAscendingOrderByColumn(ToolsPeer::TOOL);
		$this->tools = ToolsPeer::doSelect($c);
	}	

  }
    
}
