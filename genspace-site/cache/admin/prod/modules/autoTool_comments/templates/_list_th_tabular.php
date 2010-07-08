  <th id="sf_admin_list_th_pk">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tool_comments/sort') == 'pk'): ?>
      <?php echo link_to(__('Pk'), 'tool_comments/list?sort=pk&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_comments/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_comments/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Pk'), 'tool_comments/list?sort=pk&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_id">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tool_comments/sort') == 'id'): ?>
      <?php echo link_to(__('Id'), 'tool_comments/list?sort=id&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_comments/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_comments/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Id'), 'tool_comments/list?sort=id&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_comment">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tool_comments/sort') == 'comment'): ?>
      <?php echo link_to(__('Comment'), 'tool_comments/list?sort=comment&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_comments/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_comments/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Comment'), 'tool_comments/list?sort=comment&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_username">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tool_comments/sort') == 'username'): ?>
      <?php echo link_to(__('Username'), 'tool_comments/list?sort=username&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_comments/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_comments/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Username'), 'tool_comments/list?sort=username&type=asc') ?>
      <?php endif; ?>
          </th>
  <th id="sf_admin_list_th_posted_on">
          <?php if ($sf_user->getAttribute('sort', null, 'sf_admin/tool_comments/sort') == 'posted_on'): ?>
      <?php echo link_to(__('Posted on'), 'tool_comments/list?sort=posted_on&type='.($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_comments/sort') == 'asc' ? 'desc' : 'asc')) ?>
      (<?php echo __($sf_user->getAttribute('type', 'asc', 'sf_admin/tool_comments/sort')) ?>)
      <?php else: ?>
      <?php echo link_to(__('Posted on'), 'tool_comments/list?sort=posted_on&type=asc') ?>
      <?php endif; ?>
          </th>
