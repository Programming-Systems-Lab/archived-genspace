<?php

/**
 * autoRegistration actions.
 *
 * @package    ##PROJECT_NAME##
 * @subpackage autoRegistration
 * @author     Fabien Potencier <fabien.potencier@symfony-project.com>
 * @version    SVN: $Id: actions.class.php,v 1.1 2009-06-19 19:11:51 swapneel Exp $
 */
class autoRegistrationActions extends sfActions
{
  public function executeIndex()
  {
    return $this->forward('registration', 'list');
  }

  public function executeList()
  {
    $this->processSort();

    $this->processFilters();


    // pager
    $this->pager = new sfPropelPager('Registration', 20);
    $c = new Criteria();
    $this->addSortCriteria($c);
    $this->addFiltersCriteria($c);
    $this->pager->setCriteria($c);
    $this->pager->setPage($this->getRequestParameter('page', $this->getUser()->getAttribute('page', 1, 'sf_admin/registration')));
    $this->pager->init();
    // save page
    if ($this->getRequestParameter('page')) {
        $this->getUser()->setAttribute('page', $this->getRequestParameter('page'), 'sf_admin/registration');
    }
  }

  public function executeCreate()
  {
    return $this->forward('registration', 'edit');
  }

  public function executeSave()
  {
    return $this->forward('registration', 'edit');
  }


  public function executeDeleteSelected()
  {
    $this->selectedItems = $this->getRequestParameter('sf_admin_batch_selection', array());

    try
    {
      foreach (RegistrationPeer::retrieveByPks($this->selectedItems) as $object)
      {
        $object->delete();
      }
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Registrations. Make sure they do not have any associated items.');
      return $this->forward('registration', 'list');
    }

    return $this->redirect('registration/list');
  }

  public function executeEdit()
  {
    $this->registration = $this->getRegistrationOrCreate();

    if ($this->getRequest()->isMethod('post'))
    {
      $this->updateRegistrationFromRequest();

      try
      {
        $this->saveRegistration($this->registration);
      }
      catch (PropelException $e)
      {
        $this->getRequest()->setError('edit', 'Could not save the edited Registrations.');
        return $this->forward('registration', 'list');
      }

      $this->getUser()->setFlash('notice', 'Your modifications have been saved');

      if ($this->getRequestParameter('save_and_add'))
      {
        return $this->redirect('registration/create');
      }
      else if ($this->getRequestParameter('save_and_list'))
      {
        return $this->redirect('registration/list');
      }
      else
      {
        return $this->redirect('registration/edit?username='.$this->registration->getUsername());
      }
    }
    else
    {
      $this->labels = $this->getLabels();
    }
  }

  public function executeDelete()
  {
    $this->registration = RegistrationPeer::retrieveByPk($this->getRequestParameter('username'));
    $this->forward404Unless($this->registration);

    try
    {
      $this->deleteRegistration($this->registration);
    }
    catch (PropelException $e)
    {
      $this->getRequest()->setError('delete', 'Could not delete the selected Registration. Make sure it does not have any associated items.');
      return $this->forward('registration', 'list');
    }

    return $this->redirect('registration/list');
  }

  public function handleErrorEdit()
  {
    $this->preExecute();
    $this->registration = $this->getRegistrationOrCreate();
    $this->updateRegistrationFromRequest();

    $this->labels = $this->getLabels();

    return sfView::SUCCESS;
  }

  protected function saveRegistration($registration)
  {
    $registration->save();

  }

  protected function deleteRegistration($registration)
  {
    $registration->delete();
  }

  protected function updateRegistrationFromRequest()
  {
    $registration = $this->getRequestParameter('registration');

    if (isset($registration['password']))
    {
      $this->registration->setPassword($registration['password']);
    }
    if (isset($registration['email']))
    {
      $this->registration->setEmail($registration['email']);
    }
    if (isset($registration['im_email']))
    {
      $this->registration->setImEmail($registration['im_email']);
    }
    if (isset($registration['im_password']))
    {
      $this->registration->setImPassword($registration['im_password']);
    }
    if (isset($registration['first_name']))
    {
      $this->registration->setFirstName($registration['first_name']);
    }
    if (isset($registration['last_name']))
    {
      $this->registration->setLastName($registration['last_name']);
    }
    if (isset($registration['work_title']))
    {
      $this->registration->setWorkTitle($registration['work_title']);
    }
    if (isset($registration['phone']))
    {
      $this->registration->setPhone($registration['phone']);
    }
    if (isset($registration['lab_affiliation']))
    {
      $this->registration->setLabAffiliation($registration['lab_affiliation']);
    }
    if (isset($registration['addr1']))
    {
      $this->registration->setAddr1($registration['addr1']);
    }
    if (isset($registration['addr2']))
    {
      $this->registration->setAddr2($registration['addr2']);
    }
    if (isset($registration['city']))
    {
      $this->registration->setCity($registration['city']);
    }
    if (isset($registration['state']))
    {
      $this->registration->setState($registration['state']);
    }
    if (isset($registration['zipcode']))
    {
      $this->registration->setZipcode($registration['zipcode']);
    }
  }

  protected function getRegistrationOrCreate($username = 'username')
  {
    if ($this->getRequestParameter($username) === ''
     || $this->getRequestParameter($username) === null)
    {
      $registration = new Registration();
    }
    else
    {
      $registration = RegistrationPeer::retrieveByPk($this->getRequestParameter($username));

      $this->forward404Unless($registration);
    }

    return $registration;
  }

  protected function processFilters()
  {
  }

  protected function processSort()
  {
    if ($this->getRequestParameter('sort'))
    {
      $this->getUser()->setAttribute('sort', $this->getRequestParameter('sort'), 'sf_admin/registration/sort');
      $this->getUser()->setAttribute('type', $this->getRequestParameter('type', 'asc'), 'sf_admin/registration/sort');
    }

    if (!$this->getUser()->getAttribute('sort', null, 'sf_admin/registration/sort'))
    {
    }
  }

  protected function addFiltersCriteria($c)
  {
  }

  protected function addSortCriteria($c)
  {
    if ($sort_column = $this->getUser()->getAttribute('sort', null, 'sf_admin/registration/sort'))
    {
      $sort_column = sfInflector::camelize(strtolower($sort_column));
      $sort_column = RegistrationPeer::translateFieldName($sort_column, BasePeer::TYPE_PHPNAME, BasePeer::TYPE_COLNAME);
      if ($this->getUser()->getAttribute('type', null, 'sf_admin/registration/sort') == 'asc')
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
      'registration{username}' => 'Username:',
      'registration{password}' => 'Password:',
      'registration{email}' => 'Email:',
      'registration{im_email}' => 'Im email:',
      'registration{im_password}' => 'Im password:',
      'registration{first_name}' => 'First name:',
      'registration{last_name}' => 'Last name:',
      'registration{work_title}' => 'Work title:',
      'registration{phone}' => 'Phone:',
      'registration{lab_affiliation}' => 'Lab affiliation:',
      'registration{addr1}' => 'Addr1:',
      'registration{addr2}' => 'Addr2:',
      'registration{city}' => 'City:',
      'registration{state}' => 'State:',
      'registration{zipcode}' => 'Zipcode:',
    );
  }
}
