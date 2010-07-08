<?php echo form_tag('tools/save', array(
  'id'        => 'sf_admin_edit_form',
  'name'      => 'sf_admin_edit_form',
  'multipart' => true,
)) ?>

<?php echo object_input_hidden_tag($tools, 'getId') ?>

<fieldset id="sf_fieldset_none" class="">

<div class="form-row">
  <?php echo label_for('tools[tool]', __($labels['tools{tool}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('tools{tool}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('tools{tool}')): ?>
    <?php echo form_error('tools{tool}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($tools, 'getTool', array (
  'size' => 80,
  'control_name' => 'tools[tool]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('tools[description]', __($labels['tools{description}']), '') ?>
  <div class="content<?php if ($sf_request->hasError('tools{description}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('tools{description}')): ?>
    <?php echo form_error('tools{description}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_textarea_tag($tools, 'getDescription', array (
  'size' => '30x3',
  'control_name' => 'tools[description]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

</fieldset>

<?php include_partial('edit_actions', array('tools' => $tools)) ?>

</form>

<ul class="sf_admin_actions">
      <li class="float-left"><?php if ($tools->getId()): ?>
<?php echo button_to(__('delete'), 'tools/delete?id='.$tools->getId(), array (
  'post' => true,
  'confirm' => __('Are you sure?'),
  'class' => 'sf_admin_action_delete',
)) ?><?php endif; ?>
</li>
  </ul>
