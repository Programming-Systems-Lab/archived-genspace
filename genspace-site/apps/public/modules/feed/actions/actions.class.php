<?php

/**
 * feed actions.
 *
 * @package    sfproject
 * @subpackage feed
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class feedActions extends sfActions
{
 /**
  * Executes index action
  *
  * @param sfRequest $request A request object
  */
  public function executeIndex(sfWebRequest $request)
  {
  		$feed = new sfAtom1Feed();
	
  $feed->setTitle('geWorkbench Tool RSS Feed');
  $feed->setLink('http://bambi.cs.columbia.edu');
       $comments=ToolRatingsPeer::getAllComments();
	 $dates=ToolRatingsPeer::getAllDates();
	 $tools=ToolRatingsPeer::getAllToolNames();
	  $ids=ToolRatingsPeer::getAllIDs();
	    $pks=ToolRatingsPeer::getAllPKs();
     $ratings=ToolRatingsPeer::getAllRatings();
   
    $i=0;
	foreach ($comments as $comment){
    $item = new sfFeedItem();
    $item->setTitle($tools[$i]."-".$ratings[$i]."/5");
    $item->setLink('http://bambi.cs.columbia.edu/tool/index/'.$ids[$i]);
    $item->setPubDate(strtotime($dates[$i]));	
	$item->setUniqueId($pks[$i]);
		
    $item->setDescription($comment);
 
 
    $feed->addItem($item);
	
 //}
	// }
	 //}
	 $i++;
	}
	$this->feed = $feed;
   
  
   }
}
