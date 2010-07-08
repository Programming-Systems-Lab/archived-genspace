<?php

/**
 * static actions.
 *
 * @package    genspace_site
 * @subpackage static
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php,v 1.3 2009-12-23 18:46:17 swapneel Exp $
 */
class staticActions extends sfActions
{
 /**
  * Executes index action
  *
  * @param sfRequest $request A request object
  */
  public function executeIndex($request)
  {
    $this->content = $this->getRequestParameter('content');
    
    if (!$this->content)
    	$this->content = "about_us";
  }
}
