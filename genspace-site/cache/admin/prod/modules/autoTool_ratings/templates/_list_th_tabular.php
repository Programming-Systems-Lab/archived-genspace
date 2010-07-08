  <th id="sf_admin_list_th_pk">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tool_ratings/sort') == 'pk'): ?>
      <?php echo link_to(__('Pk'), 'tool_ratings/list?sort=pk&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_ratings/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_ratings/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Pk'), 'tool_ratings/list?sort=pk&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_id">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tool_ratings/sort') == 'id'): ?>
      <?php echo link_to(__('Id'), 'tool_ratings/list?sort=id&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_ratings/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_ratings/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Id'), 'tool_ratings/list?sort=id&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_rating">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tool_ratings/sort') == 'rating'): ?>
      <?php echo link_to(__('Rating'), 'tool_ratings/list?sort=rating&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_ratings/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_ratings/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Rating'), 'tool_ratings/list?sort=rating&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_username">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tool_ratings/sort') == 'username'): ?>
      <?php echo link_to(__('Username'), 'tool_ratings/list?sort=username&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_ratings/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_ratings/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Username'), 'tool_ratings/list?sort=username&type=asc') ?>
      <?php endif; ?>
          </th>
