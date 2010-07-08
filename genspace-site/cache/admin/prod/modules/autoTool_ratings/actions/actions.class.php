<?php

/**
 * autoTool_ratings actions.
 *
 * @package    ##PROJECT_NAME##
 * @subpackage autoTool_ratings
 * @author     Fabien Potencier <fabien.potencier@symfony-project.com>
 * @version    SVN: $Id: actions.class.php,v 1.1 2009-06-19 19:11:52 swapneel Exp $
 */
class autoTool_ratingsActions extends sfActions
{
  public function executeIndex()
  {
    return $this->forward('tool_ratings', 'list');
  }

  public function executeList()
  {
    $this->processSort();

    $this->processFilters();


    // pager
    $this->pager = new sfPropelPager('ToolRatings', 20);
    $c = new Criteria();
    $this->addSortCriteria($c);
    $this->addFiltersCriteria($c);
    $this->pager->setCriteria($c);
    $this->pager->setPage($this->getRequestParameter('page', $this->getUser()->getAttribute('page', 1, 'sf_admin/tool_ratings')));
    $this->pager->init();
    // save page
    if ($this->getRequestParameter('page')) {
        $this->getUser()->setAttribute('page', $this->getRequestParameter('page'), 'sf_admin/tool_ratings');
    }
  }

  public function executeCreate()
  {
    return $this->forward('tool_ratings', 'edit');
  }

  public function executeSave()
  {
    return $this->forward('tool_ratings', 'edit');
  }


  public function executeDeleteSelected()
  {
    $this->selectedItems = $this->getRequestParameter('sf_admin_batch_selection', array());

    try
    {
      foreach (ToolRatingsPeer::retrieveByPks($this->selectedItems) as $object)
      {
        $object->delete();
      }
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Tool ratingss. Make sure they do not have any associated items.');
      return $this->forward('tool_ratings', 'list');
    }

    return $this->redirect('tool_ratings/list');
  }

  public function executeEdit()
  {
    $this->tool_ratings = $this->getToolRatingsOrCreate();

    if ($this->getRequest()->isMethod('post'))
    {
      $this->updateToolRatingsFromRequest();

      try
      {
        $this->saveToolRatings($this->tool_ratings);
      }
      catch (PropelException $e)
      {
        $this->getRequest()->setError('edit', 'Could not save the edited Tool ratingss.');
        return $this->forward('tool_ratings', 'list');
      }

      $this->getUser()->setFlash('notice', 'Your modifications have been saved');

      if ($this->getRequestParameter('save_and_add'))
      {
        return $this->redirect('tool_ratings/create');
      }
      else if ($this->getRequestParameter('save_and_list'))
      {
        return $this->redirect('tool_ratings/list');
      }
      else
      {
        return $this->redirect('tool_ratings/edit?pk='.$this->tool_ratings->getPk());
      }
    }
    else
    {
      $this->labels = $this->getLabels();
    }
  }

  public function executeDelete()
  {
    $this->tool_ratings = ToolRatingsPeer::retrieveByPk($this->getRequestParameter('pk'));
    $this->forward404Unless($this->tool_ratings);

    try
    {
      $this->deleteToolRatings($this->tool_ratings);
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Tool ratings. Make sure it does not have any associated items.');
      return $this->forward('tool_ratings', 'list');
    }

    return $this->redirect('tool_ratings/list');
  }

  public function handleErrorEdit()
  {
    $this->preExecute();
    $this->tool_ratings = $this->getToolRatingsOrCreate();
    $this->updateToolRatingsFromRequest();

    $this->labels = $this->getLabels();

    return sfView::SUCCESS;
  }

  protected function saveToolRatings($tool_ratings)
  {
    $tool_ratings->save();

  }

  protected function deleteToolRatings($tool_ratings)
  {
    $tool_ratings->delete();
  }

  protected function updateToolRatingsFromRequest()
  {
    $tool_ratings = $this->getRequestParameter('tool_ratings');

    if (isset($tool_ratings['id']))
    {
      $this->tool_ratings->setId($tool_ratings['id']);
    }
    if (isset($tool_ratings['rating']))
    {
      $this->tool_ratings->setRating($tool_ratings['rating']);
    }
    if (isset($tool_ratings['username']))
    {
      $this->tool_ratings->setUsername($tool_ratings['username']);
    }
  }

  protected function getToolRatingsOrCreate($pk = 'pk')
  {
    if ($this->getRequestParameter($pk) === ''
     || $this->getRequestParameter($pk) === null)
    {
      $tool_ratings = new ToolRatings();
    }
    else
    {
      $tool_ratings = ToolRatingsPeer::retrieveByPk($this->getRequestParameter($pk));

      $this->forward404Unless($tool_ratings);
    }

    return $tool_ratings;
  }

  protected function processFilters()
  {
  }

  protected function processSort()
  {
    if ($this->getRequestParameter('sort'))
    {
      $this->getUser()->setAttribute('sort', $this->getRequestParameter('sort'), 'sf_admin/tool_ratings/sort');
      $this->getUser()->setAttribute('type', $this->getRequestParameter('type', 'asc'), 'sf_admin/tool_ratings/sort');
    }

    if (!$this->getUser()->getAttribute('sort', null, 'sf_admin/tool_ratings/sort'))
    {
    }
  }

  protected function addFiltersCriteria($c)
  {
  }

  protected function addSortCriteria($c)
  {
    if ($sort_column = $this->getUser()->getAttribute('sort', null, 'sf_admin/tool_ratings/sort'))
    {
      $sort_column = sfInflector::camelize(strtolower($sort_column));
      $sort_column = ToolRatingsPeer::translateFieldName($sort_column, BasePeer::TYPE_PHPNAME, BasePeer::TYPE_COLNAME);
      if ($this->getUser()->getAttribute('type', null, 'sf_admin/tool_ratings/sort') == 'asc')
      {
        $c->addAscendingOrderByColumn($sort_column);
      }
      else
      {
        $c->addDescendingOrderByColumn($sort_column);
      }
    }
  }

  protected function getLabels()
  {
    return array(
      'tool_ratings{pk}' => 'Pk:',
      'tool_ratings{id}' => 'Id:',
      'tool_ratings{rating}' => 'Rating:',
      'tool_ratings{username}' => 'Username:',
    );
  }
}
