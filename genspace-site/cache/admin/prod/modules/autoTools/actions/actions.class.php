<?php

/**
 * autoTools actions.
 *
 * @package    ##PROJECT_NAME##
 * @subpackage autoTools
 * @author     Fabien Potencier <fabien.potencier@symfony-project.com>
 * @version    SVN: $Id: actions.class.php,v 1.1 2009-06-19 19:11:52 swapneel Exp $
 */
class autoToolsActions extends sfActions
{
  public function executeIndex()
  {
    return $this->forward('tools', 'list');
  }

  public function executeList()
  {
    $this->processSort();

    $this->processFilters();


    // pager
    $this->pager = new sfPropelPager('Tools', 20);
    $c = new Criteria();
    $this->addSortCriteria($c);
    $this->addFiltersCriteria($c);
    $this->pager->setCriteria($c);
    $this->pager->setPage($this->getRequestParameter('page', $this->getUser()->getAttribute('page', 1, 'sf_admin/tools')));
    $this->pager->init();
    // save page
    if ($this->getRequestParameter('page')) {
        $this->getUser()->setAttribute('page', $this->getRequestParameter('page'), 'sf_admin/tools');
    }
  }

  public function executeCreate()
  {
    return $this->forward('tools', 'edit');
  }

  public function executeSave()
  {
    return $this->forward('tools', 'edit');
  }


  public function executeDeleteSelected()
  {
    $this->selectedItems = $this->getRequestParameter('sf_admin_batch_selection', array());

    try
    {
      foreach (ToolsPeer::retrieveByPks($this->selectedItems) as $object)
      {
        $object->delete();
      }
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Toolss. Make sure they do not have any associated items.');
      return $this->forward('tools', 'list');
    }

    return $this->redirect('tools/list');
  }

  public function executeEdit()
  {
    $this->tools = $this->getToolsOrCreate();

    if ($this->getRequest()->isMethod('post'))
    {
      $this->updateToolsFromRequest();

      try
      {
        $this->saveTools($this->tools);
      }
      catch (PropelException $e)
      {
        $this->getRequest()->setError('edit', 'Could not save the edited Toolss.');
        return $this->forward('tools', 'list');
      }

      $this->getUser()->setFlash('notice', 'Your modifications have been saved');

      if ($this->getRequestParameter('save_and_add'))
      {
        return $this->redirect('tools/create');
      }
      else if ($this->getRequestParameter('save_and_list'))
      {
        return $this->redirect('tools/list');
      }
      else
      {
        return $this->redirect('tools/edit?id='.$this->tools->getId());
      }
    }
    else
    {
      $this->labels = $this->getLabels();
    }
  }

  public function executeDelete()
  {
    $this->tools = ToolsPeer::retrieveByPk($this->getRequestParameter('id'));
    $this->forward404Unless($this->tools);

    try
    {
      $this->deleteTools($this->tools);
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Tools. Make sure it does not have any associated items.');
      return $this->forward('tools', 'list');
    }

    return $this->redirect('tools/list');
  }

  public function handleErrorEdit()
  {
    $this->preExecute();
    $this->tools = $this->getToolsOrCreate();
    $this->updateToolsFromRequest();

    $this->labels = $this->getLabels();

    return sfView::SUCCESS;
  }

  protected function saveTools($tools)
  {
    $tools->save();

  }

  protected function deleteTools($tools)
  {
    $tools->delete();
  }

  protected function updateToolsFromRequest()
  {
    $tools = $this->getRequestParameter('tools');

    if (isset($tools['tool']))
    {
      $this->tools->setTool($tools['tool']);
    }
    if (isset($tools['description']))
    {
      $this->tools->setDescription($tools['description']);
    }
  }

  protected function getToolsOrCreate($id = 'id')
  {
    if ($this->getRequestParameter($id) === ''
     || $this->getRequestParameter($id) === null)
    {
      $tools = new Tools();
    }
    else
    {
      $tools = ToolsPeer::retrieveByPk($this->getRequestParameter($id));

      $this->forward404Unless($tools);
    }

    return $tools;
  }

  protected function processFilters()
  {
  }

  protected function processSort()
  {
    if ($this->getRequestParameter('sort'))
    {
      $this->getUser()->setAttribute('sort', $this->getRequestParameter('sort'), 'sf_admin/tools/sort');
      $this->getUser()->setAttribute('type', $this->getRequestParameter('type', 'asc'), 'sf_admin/tools/sort');
    }

    if (!$this->getUser()->getAttribute('sort', null, 'sf_admin/tools/sort'))
    {
    }
  }

  protected function addFiltersCriteria($c)
  {
  }

  protected function addSortCriteria($c)
  {
    if ($sort_column = $this->getUser()->getAttribute('sort', null, 'sf_admin/tools/sort'))
    {
      $sort_column = sfInflector::camelize(strtolower($sort_column));
      $sort_column = ToolsPeer::translateFieldName($sort_column, BasePeer::TYPE_PHPNAME, BasePeer::TYPE_COLNAME);
      if ($this->getUser()->getAttribute('type', null, 'sf_admin/tools/sort') == 'asc')
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
      'tools{id}' => 'Id:',
      'tools{tool}' => 'Tool:',
      'tools{description}' => 'Description:',
    );
  }
}
