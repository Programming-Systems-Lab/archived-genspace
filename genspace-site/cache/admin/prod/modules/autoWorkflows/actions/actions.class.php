<?php

/**
 * autoWorkflows actions.
 *
 * @package    ##PROJECT_NAME##
 * @subpackage autoWorkflows
 * @author     Fabien Potencier <fabien.potencier@symfony-project.com>
 * @version    SVN: $Id: actions.class.php,v 1.1 2009-06-19 19:11:53 swapneel Exp $
 */
class autoWorkflowsActions extends sfActions
{
  public function executeIndex()
  {
    return $this->forward('workflows', 'list');
  }

  public function executeList()
  {
    $this->processSort();

    $this->processFilters();


    // pager
    $this->pager = new sfPropelPager('Workflows', 20);
    $c = new Criteria();
    $this->addSortCriteria($c);
    $this->addFiltersCriteria($c);
    $this->pager->setCriteria($c);
    $this->pager->setPage($this->getRequestParameter('page', $this->getUser()->getAttribute('page', 1, 'sf_admin/workflows')));
    $this->pager->init();
    // save page
    if ($this->getRequestParameter('page')) {
        $this->getUser()->setAttribute('page', $this->getRequestParameter('page'), 'sf_admin/workflows');
    }
  }

  public function executeCreate()
  {
    return $this->forward('workflows', 'edit');
  }

  public function executeSave()
  {
    return $this->forward('workflows', 'edit');
  }


  public function executeDeleteSelected()
  {
    $this->selectedItems = $this->getRequestParameter('sf_admin_batch_selection', array());

    try
    {
      foreach (WorkflowsPeer::retrieveByPks($this->selectedItems) as $object)
      {
        $object->delete();
      }
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Workflowss. Make sure they do not have any associated items.');
      return $this->forward('workflows', 'list');
    }

    return $this->redirect('workflows/list');
  }

  public function executeEdit()
  {
    $this->workflows = $this->getWorkflowsOrCreate();

    if ($this->getRequest()->isMethod('post'))
    {
      $this->updateWorkflowsFromRequest();

      try
      {
        $this->saveWorkflows($this->workflows);
      }
      catch (PropelException $e)
      {
        $this->getRequest()->setError('edit', 'Could not save the edited Workflowss.');
        return $this->forward('workflows', 'list');
      }

      $this->getUser()->setFlash('notice', 'Your modifications have been saved');

      if ($this->getRequestParameter('save_and_add'))
      {
        return $this->redirect('workflows/create');
      }
      else if ($this->getRequestParameter('save_and_list'))
      {
        return $this->redirect('workflows/list');
      }
      else
      {
        return $this->redirect('workflows/edit?id='.$this->workflows->getId());
      }
    }
    else
    {
      $this->labels = $this->getLabels();
    }
  }

  public function executeDelete()
  {
    $this->workflows = WorkflowsPeer::retrieveByPk($this->getRequestParameter('id'));
    $this->forward404Unless($this->workflows);

    try
    {
      $this->deleteWorkflows($this->workflows);
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Workflows. Make sure it does not have any associated items.');
      return $this->forward('workflows', 'list');
    }

    return $this->redirect('workflows/list');
  }

  public function handleErrorEdit()
  {
    $this->preExecute();
    $this->workflows = $this->getWorkflowsOrCreate();
    $this->updateWorkflowsFromRequest();

    $this->labels = $this->getLabels();

    return sfView::SUCCESS;
  }

  protected function saveWorkflows($workflows)
  {
    $workflows->save();

  }

  protected function deleteWorkflows($workflows)
  {
    $workflows->delete();
  }

  protected function updateWorkflowsFromRequest()
  {
    $workflows = $this->getRequestParameter('workflows');

    if (isset($workflows['parent']))
    {
      $this->workflows->setParent($workflows['parent']);
    }
    if (isset($workflows['tool']))
    {
      $this->workflows->setTool($workflows['tool']);
    }
  }

  protected function getWorkflowsOrCreate($id = 'id')
  {
    if ($this->getRequestParameter($id) === ''
     || $this->getRequestParameter($id) === null)
    {
      $workflows = new Workflows();
    }
    else
    {
      $workflows = WorkflowsPeer::retrieveByPk($this->getRequestParameter($id));

      $this->forward404Unless($workflows);
    }

    return $workflows;
  }

  protected function processFilters()
  {
  }

  protected function processSort()
  {
    if ($this->getRequestParameter('sort'))
    {
      $this->getUser()->setAttribute('sort', $this->getRequestParameter('sort'), 'sf_admin/workflows/sort');
      $this->getUser()->setAttribute('type', $this->getRequestParameter('type', 'asc'), 'sf_admin/workflows/sort');
    }

    if (!$this->getUser()->getAttribute('sort', null, 'sf_admin/workflows/sort'))
    {
    }
  }

  protected function addFiltersCriteria($c)
  {
  }

  protected function addSortCriteria($c)
  {
    if ($sort_column = $this->getUser()->getAttribute('sort', null, 'sf_admin/workflows/sort'))
    {
      $sort_column = sfInflector::camelize(strtolower($sort_column));
      $sort_column = WorkflowsPeer::translateFieldName($sort_column, BasePeer::TYPE_PHPNAME, BasePeer::TYPE_COLNAME);
      if ($this->getUser()->getAttribute('type', null, 'sf_admin/workflows/sort') == 'asc')
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
      'workflows{id}' => 'Id:',
      'workflows{parent}' => 'Parent:',
      'workflows{tool}' => 'Tool:',
    );
  }
}
