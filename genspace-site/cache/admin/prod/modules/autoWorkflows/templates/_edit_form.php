<?php echo form_tag('workflows/save', array(
  'id'        => 'sf_admin_edit_form',
  'name'      => 'sf_admin_edit_form',
  'multipart' => true,
)) ?>

<?php echo object_input_hidden_tag($workflows, 'getId') ?>

<fieldset id="sf_fieldset_none" class="">

<div class="form-row">
  <?php echo label_for('workflows[parent]', __($labels['workflows{parent}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('workflows{parent}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('workflows{parent}')): ?>
    <?php echo form_error('workflows{parent}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($workflows, 'getParent', array (
  'size' => 7,
  'control_name' => 'workflows[parent]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('workflows[tool]', __($labels['workflows{tool}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('workflows{tool}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('workflows{tool}')): ?>
    <?php echo form_error('workflows{tool}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($workflows, 'getTool', array (
  'size' => 80,
  'control_name' => 'workflows[tool]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

</fieldset>

<?php include_partial('edit_actions', array('workflows' => $workflows)) ?>

</form>

<ul class="sf_admin_actions">
      <li class="float-left"><?php if ($workflows->getId()): ?>
<?php echo button_to(__('delete'), 'workflows/delete?id='.$workflows->getId(), array (
  'post' => true,
  'confirm' => __('Are you sure?'),
  'class' => 'sf_admin_action_delete',
)) ?><?php endif; ?>
</li>
  </ul>
