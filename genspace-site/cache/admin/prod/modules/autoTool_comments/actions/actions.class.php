<?php

/**
 * autoTool_comments actions.
 *
 * @package    ##PROJECT_NAME##
 * @subpackage autoTool_comments
 * @author     Fabien Potencier <fabien.potencier@symfony-project.com>
 * @version    SVN: $Id: actions.class.php,v 1.1 2009-06-19 19:11:51 swapneel Exp $
 */
class autoTool_commentsActions extends sfActions
{
  public function executeIndex()
  {
    return $this->forward('tool_comments', 'list');
  }

  public function executeList()
  {
    $this->processSort();

    $this->processFilters();


    // pager
    $this->pager = new sfPropelPager('ToolComments', 20);
    $c = new Criteria();
    $this->addSortCriteria($c);
    $this->addFiltersCriteria($c);
    $this->pager->setCriteria($c);
    $this->pager->setPage($this->getRequestParameter('page', $this->getUser()->getAttribute('page', 1, 'sf_admin/tool_comments')));
    $this->pager->init();
    // save page
    if ($this->getRequestParameter('page')) {
        $this->getUser()->setAttribute('page', $this->getRequestParameter('page'), 'sf_admin/tool_comments');
    }
  }

  public function executeCreate()
  {
    return $this->forward('tool_comments', 'edit');
  }

  public function executeSave()
  {
    return $this->forward('tool_comments', 'edit');
  }


  public function executeDeleteSelected()
  {
    $this->selectedItems = $this->getRequestParameter('sf_admin_batch_selection', array());

    try
    {
      foreach (ToolCommentsPeer::retrieveByPks($this->selectedItems) as $object)
      {
        $object->delete();
      }
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Tool commentss. Make sure they do not have any associated items.');
      return $this->forward('tool_comments', 'list');
    }

    return $this->redirect('tool_comments/list');
  }

  public function executeEdit()
  {
    $this->tool_comments = $this->getToolCommentsOrCreate();

    if ($this->getRequest()->isMethod('post'))
    {
      $this->updateToolCommentsFromRequest();

      try
      {
        $this->saveToolComments($this->tool_comments);
      }
      catch (PropelException $e)
      {
        $this->getRequest()->setError('edit', 'Could not save the edited Tool commentss.');
        return $this->forward('tool_comments', 'list');
      }

      $this->getUser()->setFlash('notice', 'Your modifications have been saved');

      if ($this->getRequestParameter('save_and_add'))
      {
        return $this->redirect('tool_comments/create');
      }
      else if ($this->getRequestParameter('save_and_list'))
      {
        return $this->redirect('tool_comments/list');
      }
      else
      {
        return $this->redirect('tool_comments/edit?pk='.$this->tool_comments->getPk());
      }
    }
    else
    {
      $this->labels = $this->getLabels();
    }
  }

  public function executeDelete()
  {
    $this->tool_comments = ToolCommentsPeer::retrieveByPk($this->getRequestParameter('pk'));
    $this->forward404Unless($this->tool_comments);

    try
    {
      $this->deleteToolComments($this->tool_comments);
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Tool comments. Make sure it does not have any associated items.');
      return $this->forward('tool_comments', 'list');
    }

    return $this->redirect('tool_comments/list');
  }

  public function handleErrorEdit()
  {
    $this->preExecute();
    $this->tool_comments = $this->getToolCommentsOrCreate();
    $this->updateToolCommentsFromRequest();

    $this->labels = $this->getLabels();

    return sfView::SUCCESS;
  }

  protected function saveToolComments($tool_comments)
  {
    $tool_comments->save();

  }

  protected function deleteToolComments($tool_comments)
  {
    $tool_comments->delete();
  }

  protected function updateToolCommentsFromRequest()
  {
    $tool_comments = $this->getRequestParameter('tool_comments');

    if (isset($tool_comments['id']))
    {
      $this->tool_comments->setId($tool_comments['id']);
    }
    if (isset($tool_comments['comment']))
    {
      $this->tool_comments->setComment($tool_comments['comment']);
    }
    if (isset($tool_comments['username']))
    {
      $this->tool_comments->setUsername($tool_comments['username']);
    }
    if (isset($tool_comments['posted_on']))
    {
      if ($tool_comments['posted_on'])
      {
        try
        {
          $dateFormat = new sfDateFormat($this->getUser()->getCulture());
                              if (!is_array($tool_comments['posted_on']))
          {
            $value = $dateFormat->format($tool_comments['posted_on'], 'I', $dateFormat->getInputPattern('g'));
          }
          else
          {
            $value_array = $tool_comments['posted_on'];
            $value = $value_array['year'].'-'.$value_array['month'].'-'.$value_array['day'].(isset($value_array['hour']) ? ' '.$value_array['hour'].':'.$value_array['minute'].(isset($value_array['second']) ? ':'.$value_array['second'] : '') : '');
          }
          $this->tool_comments->setPostedOn($value);
        }
        catch (sfException $e)
        {
          // not a date
        }
      }
      else
      {
        $this->tool_comments->setPostedOn(null);
      }
    }
  }

  protected function getToolCommentsOrCreate($pk = 'pk')
  {
    if ($this->getRequestParameter($pk) === ''
     || $this->getRequestParameter($pk) === null)
    {
      $tool_comments = new ToolComments();
    }
    else
    {
      $tool_comments = ToolCommentsPeer::retrieveByPk($this->getRequestParameter($pk));

      $this->forward404Unless($tool_comments);
    }

    return $tool_comments;
  }

  protected function processFilters()
  {
  }

  protected function processSort()
  {
    if ($this->getRequestParameter('sort'))
    {
      $this->getUser()->setAttribute('sort', $this->getRequestParameter('sort'), 'sf_admin/tool_comments/sort');
      $this->getUser()->setAttribute('type', $this->getRequestParameter('type', 'asc'), 'sf_admin/tool_comments/sort');
    }

    if (!$this->getUser()->getAttribute('sort', null, 'sf_admin/tool_comments/sort'))
    {
    }
  }

  protected function addFiltersCriteria($c)
  {
  }

  protected function addSortCriteria($c)
  {
    if ($sort_column = $this->getUser()->getAttribute('sort', null, 'sf_admin/tool_comments/sort'))
    {
      $sort_column = sfInflector::camelize(strtolower($sort_column));
      $sort_column = ToolCommentsPeer::translateFieldName($sort_column, BasePeer::TYPE_PHPNAME, BasePeer::TYPE_COLNAME);
      if ($this->getUser()->getAttribute('type', null, 'sf_admin/tool_comments/sort') == 'asc')
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
      'tool_comments{pk}' => 'Pk:',
      'tool_comments{id}' => 'Id:',
      'tool_comments{comment}' => 'Comment:',
      'tool_comments{username}' => 'Username:',
      'tool_comments{posted_on}' => 'Posted on:',
    );
  }
}
