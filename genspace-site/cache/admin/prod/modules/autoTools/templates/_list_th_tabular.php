  <th id="sf_admin_list_th_id">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tools/sort') == 'id'): ?>
      <?php echo link_to(__('Id'), 'tools/list?sort=id&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tools/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tools/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Id'), 'tools/list?sort=id&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_tool">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tools/sort') == 'tool'): ?>
      <?php echo link_to(__('Tool'), 'tools/list?sort=tool&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tools/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tools/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Tool'), 'tools/list?sort=tool&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_description">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tools/sort') == 'description'): ?>
      <?php echo link_to(__('Description'), 'tools/list?sort=description&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tools/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tools/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Description'), 'tools/list?sort=description&type=asc') ?>
      <?php endif; ?>
          </th>
