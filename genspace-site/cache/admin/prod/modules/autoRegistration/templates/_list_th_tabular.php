  <th id="sf_admin_list_th_username">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'username'): ?>
      <?php echo link_to(__('Username'), 'registration/list?sort=username&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Username'), 'registration/list?sort=username&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_password">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'password'): ?>
      <?php echo link_to(__('Password'), 'registration/list?sort=password&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Password'), 'registration/list?sort=password&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_email">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'email'): ?>
      <?php echo link_to(__('Email'), 'registration/list?sort=email&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Email'), 'registration/list?sort=email&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_im_email">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'im_email'): ?>
      <?php echo link_to(__('Im email'), 'registration/list?sort=im_email&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Im email'), 'registration/list?sort=im_email&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_im_password">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'im_password'): ?>
      <?php echo link_to(__('Im password'), 'registration/list?sort=im_password&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Im password'), 'registration/list?sort=im_password&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_first_name">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'first_name'): ?>
      <?php echo link_to(__('First name'), 'registration/list?sort=first_name&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('First name'), 'registration/list?sort=first_name&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_last_name">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'last_name'): ?>
      <?php echo link_to(__('Last name'), 'registration/list?sort=last_name&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Last name'), 'registration/list?sort=last_name&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_work_title">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'work_title'): ?>
      <?php echo link_to(__('Work title'), 'registration/list?sort=work_title&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Work title'), 'registration/list?sort=work_title&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_phone">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'phone'): ?>
      <?php echo link_to(__('Phone'), 'registration/list?sort=phone&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Phone'), 'registration/list?sort=phone&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_lab_affiliation">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'lab_affiliation'): ?>
      <?php echo link_to(__('Lab affiliation'), 'registration/list?sort=lab_affiliation&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Lab affiliation'), 'registration/list?sort=lab_affiliation&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_addr1">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'addr1'): ?>
      <?php echo link_to(__('Addr1'), 'registration/list?sort=addr1&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Addr1'), 'registration/list?sort=addr1&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_addr2">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'addr2'): ?>
      <?php echo link_to(__('Addr2'), 'registration/list?sort=addr2&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Addr2'), 'registration/list?sort=addr2&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_city">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'city'): ?>
      <?php echo link_to(__('City'), 'registration/list?sort=city&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('City'), 'registration/list?sort=city&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_state">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'state'): ?>
      <?php echo link_to(__('State'), 'registration/list?sort=state&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('State'), 'registration/list?sort=state&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_zipcode">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/registration/sort') == 'zipcode'): ?>
      <?php echo link_to(__('Zipcode'), 'registration/list?sort=zipcode&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/registration/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Zipcode'), 'registration/list?sort=zipcode&type=asc') ?>
      <?php endif; ?>
          </th>
