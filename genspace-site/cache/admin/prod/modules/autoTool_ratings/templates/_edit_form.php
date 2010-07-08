<?php echo form_tag('tool_ratings/save', array(
  'id'        => 'sf_admin_edit_form',
  'name'      => 'sf_admin_edit_form',
  'multipart' => true,
)) ?>

<?php echo object_input_hidden_tag($tool_ratings, 'getPk') ?>

<fieldset id="sf_fieldset_none" class="">

<div class="form-row">
  <?php echo label_for('tool_ratings[id]', __($labels['tool_ratings{id}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('tool_ratings{id}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('tool_ratings{id}')): ?>
    <?php echo form_error('tool_ratings{id}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($tool_ratings, 'getId', array (
  'size' => 7,
  'control_name' => 'tool_ratings[id]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('tool_ratings[rating]', __($labels['tool_ratings{rating}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('tool_ratings{rating}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('tool_ratings{rating}')): ?>
    <?php echo form_error('tool_ratings{rating}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($tool_ratings, 'getRating', array (
  'size' => 7,
  'control_name' => 'tool_ratings[rating]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('tool_ratings[username]', __($labels['tool_ratings{username}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('tool_ratings{username}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('tool_ratings{username}')): ?>
    <?php echo form_error('tool_ratings{username}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($tool_ratings, 'getUsername', array (
  'size' => 80,
  'control_name' => 'tool_ratings[username]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

</fieldset>

<?php include_partial('edit_actions', array('tool_ratings' => $tool_ratings)) ?>

</form>

<ul class="sf_admin_actions">
      <li class="float-left"><?php if ($tool_ratings->getPk()): ?>
<?php echo button_to(__('delete'), 'tool_ratings/delete?pk='.$tool_ratings->getPk(), array (
  'post' => true,
  'confirm' => __('Are you sure?'),
  'class' => 'sf_admin_action_delete',
)) ?><?php endif; ?>
</li>
  </ul>
