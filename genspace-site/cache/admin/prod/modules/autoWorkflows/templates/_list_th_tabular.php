  <th id="sf_admin_list_th_id">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/workflows/sort') == 'id'): ?>
      <?php echo link_to(__('Id'), 'workflows/list?sort=id&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/workflows/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/workflows/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Id'), 'workflows/list?sort=id&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_parent">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/workflows/sort') == 'parent'): ?>
      <?php echo link_to(__('Parent'), 'workflows/list?sort=parent&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/workflows/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/workflows/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Parent'), 'workflows/list?sort=parent&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_tool">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/workflows/sort') == 'tool'): ?>
      <?php echo link_to(__('Tool'), 'workflows/list?sort=tool&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/workflows/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/workflows/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Tool'), 'workflows/list?sort=tool&type=asc') ?>
      <?php endif; ?>
          </th>
