<?php echo form_tag('workflow_ratings/save', array(
  'id'        => 'sf_admin_edit_form',
  'name'      => 'sf_admin_edit_form',
  'multipart' => true,
)) ?>

<?php echo object_input_hidden_tag($workflow_ratings, 'getPk') ?>

<fieldset id="sf_fieldset_none" class="">

<div class="form-row">
  <?php echo label_for('workflow_ratings[id]', __($labels['workflow_ratings{id}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('workflow_ratings{id}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('workflow_ratings{id}')): ?>
    <?php echo form_error('workflow_ratings{id}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($workflow_ratings, 'getId', array (
  'size' => 7,
  'control_name' => 'workflow_ratings[id]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('workflow_ratings[username]', __($labels['workflow_ratings{username}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('workflow_ratings{username}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('workflow_ratings{username}')): ?>
    <?php echo form_error('workflow_ratings{username}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($workflow_ratings, 'getUsername', array (
  'size' => 80,
  'control_name' => 'workflow_ratings[username]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('workflow_ratings[rating]', __($labels['workflow_ratings{rating}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('workflow_ratings{rating}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('workflow_ratings{rating}')): ?>
    <?php echo form_error('workflow_ratings{rating}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($workflow_ratings, 'getRating', array (
  'size' => 7,
  'control_name' => 'workflow_ratings[rating]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

</fieldset>

<?php include_partial('edit_actions', array('workflow_ratings' => $workflow_ratings)) ?>

</form>

<ul class="sf_admin_actions">
      <li class="float-left"><?php if ($workflow_ratings->getPk()): ?>
<?php echo button_to(__('delete'), 'workflow_ratings/delete?pk='.$workflow_ratings->getPk(), array (
  'post' => true,
  'confirm' => __('Are you sure?'),
  'class' => 'sf_admin_action_delete',
)) ?><?php endif; ?>
</li>
  </ul>
