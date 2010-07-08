<?php

/**
 * autoWorkflow_ratings actions.
 *
 * @package    ##PROJECT_NAME##
 * @subpackage autoWorkflow_ratings
 * @author     Fabien Potencier <fabien.potencier@symfony-project.com>
 * @version    SVN: $Id: actions.class.php,v 1.1 2009-06-19 19:11:52 swapneel Exp $
 */
class autoWorkflow_ratingsActions extends sfActions
{
  public function executeIndex()
  {
    return $this->forward('workflow_ratings', 'list');
  }

  public function executeList()
  {
    $this->processSort();

    $this->processFilters();


    // pager
    $this->pager = new sfPropelPager('WorkflowRatings', 20);
    $c = new Criteria();
    $this->addSortCriteria($c);
    $this->addFiltersCriteria($c);
    $this->pager->setCriteria($c);
    $this->pager->setPage($this->getRequestParameter('page', $this->getUser()->getAttribute('page', 1, 'sf_admin/workflow_ratings')));
    $this->pager->init();
    // save page
    if ($this->getRequestParameter('page')) {
        $this->getUser()->setAttribute('page', $this->getRequestParameter('page'), 'sf_admin/workflow_ratings');
    }
  }

  public function executeCreate()
  {
    return $this->forward('workflow_ratings', 'edit');
  }

  public function executeSave()
  {
    return $this->forward('workflow_ratings', 'edit');
  }


  public function executeDeleteSelected()
  {
    $this->selectedItems = $this->getRequestParameter('sf_admin_batch_selection', array());

    try
    {
      foreach (WorkflowRatingsPeer::retrieveByPks($this->selectedItems) as $object)
      {
        $object->delete();
      }
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Workflow ratingss. Make sure they do not have any associated items.');
      return $this->forward('workflow_ratings', 'list');
    }

    return $this->redirect('workflow_ratings/list');
  }

  public function executeEdit()
  {
    $this->workflow_ratings = $this->getWorkflowRatingsOrCreate();

    if ($this->getRequest()->isMethod('post'))
    {
      $this->updateWorkflowRatingsFromRequest();

      try
      {
        $this->saveWorkflowRatings($this->workflow_ratings);
      }
      catch (PropelException $e)
      {
        $this->getRequest()->setError('edit', 'Could not save the edited Workflow ratingss.');
        return $this->forward('workflow_ratings', 'list');
      }

      $this->getUser()->setFlash('notice', 'Your modifications have been saved');

      if ($this->getRequestParameter('save_and_add'))
      {
        return $this->redirect('workflow_ratings/create');
      }
      else if ($this->getRequestParameter('save_and_list'))
      {
        return $this->redirect('workflow_ratings/list');
      }
      else
      {
        return $this->redirect('workflow_ratings/edit?pk='.$this->workflow_ratings->getPk());
      }
    }
    else
    {
      $this->labels = $this->getLabels();
    }
  }

  public function executeDelete()
  {
    $this->workflow_ratings = WorkflowRatingsPeer::retrieveByPk($this->getRequestParameter('pk'));
    $this->forward404Unless($this->workflow_ratings);

    try
    {
      $this->deleteWorkflowRatings($this->workflow_ratings);
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Workflow ratings. Make sure it does not have any associated items.');
      return $this->forward('workflow_ratings', 'list');
    }

    return $this->redirect('workflow_ratings/list');
  }

  public function handleErrorEdit()
  {
    $this->preExecute();
    $this->workflow_ratings = $this->getWorkflowRatingsOrCreate();
    $this->updateWorkflowRatingsFromRequest();

    $this->labels = $this->getLabels();

    return sfView::SUCCESS;
  }

  protected function saveWorkflowRatings($workflow_ratings)
  {
    $workflow_ratings->save();

  }

  protected function deleteWorkflowRatings($workflow_ratings)
  {
    $workflow_ratings->delete();
  }

  protected function updateWorkflowRatingsFromRequest()
  {
    $workflow_ratings = $this->getRequestParameter('workflow_ratings');

    if (isset($workflow_ratings['id']))
    {
      $this->workflow_ratings->setId($workflow_ratings['id']);
    }
    if (isset($workflow_ratings['username']))
    {
      $this->workflow_ratings->setUsername($workflow_ratings['username']);
    }
    if (isset($workflow_ratings['rating']))
    {
      $this->workflow_ratings->setRating($workflow_ratings['rating']);
    }
  }

  protected function getWorkflowRatingsOrCreate($pk = 'pk')
  {
    if ($this->getRequestParameter($pk) === ''
     || $this->getRequestParameter($pk) === null)
    {
      $workflow_ratings = new WorkflowRatings();
    }
    else
    {
      $workflow_ratings = WorkflowRatingsPeer::retrieveByPk($this->getRequestParameter($pk));

      $this->forward404Unless($workflow_ratings);
    }

    return $workflow_ratings;
  }

  protected function processFilters()
  {
  }

  protected function processSort()
  {
    if ($this->getRequestParameter('sort'))
    {
      $this->getUser()->setAttribute('sort', $this->getRequestParameter('sort'), 'sf_admin/workflow_ratings/sort');
      $this->getUser()->setAttribute('type', $this->getRequestParameter('type', 'asc'), 'sf_admin/workflow_ratings/sort');
    }

    if (!$this->getUser()->getAttribute('sort', null, 'sf_admin/workflow_ratings/sort'))
    {
    }
  }

  protected function addFiltersCriteria($c)
  {
  }

  protected function addSortCriteria($c)
  {
    if ($sort_column = $this->getUser()->getAttribute('sort', null, 'sf_admin/workflow_ratings/sort'))
    {
      $sort_column = sfInflector::camelize(strtolower($sort_column));
      $sort_column = WorkflowRatingsPeer::translateFieldName($sort_column, BasePeer::TYPE_PHPNAME, BasePeer::TYPE_COLNAME);
      if ($this->getUser()->getAttribute('type', null, 'sf_admin/workflow_ratings/sort') == 'asc')
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
      'workflow_ratings{pk}' => 'Pk:',
      'workflow_ratings{id}' => 'Id:',
      'workflow_ratings{username}' => 'Username:',
      'workflow_ratings{rating}' => 'Rating:',
    );
  }
}
