<?php echo form_tag('tool_comments/save', array(
  'id'        => 'sf_admin_edit_form',
  'name'      => 'sf_admin_edit_form',
  'multipart' => true,
)) ?>

<?php echo object_input_hidden_tag($tool_comments, 'getPk') ?>

<fieldset id="sf_fieldset_none" class="">

<div class="form-row">
  <?php echo label_for('tool_comments[id]', __($labels['tool_comments{id}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('tool_comments{id}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('tool_comments{id}')): ?>
    <?php echo form_error('tool_comments{id}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($tool_comments, 'getId', array (
  'size' => 7,
  'control_name' => 'tool_comments[id]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('tool_comments[comment]', __($labels['tool_comments{comment}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('tool_comments{comment}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('tool_comments{comment}')): ?>
    <?php echo form_error('tool_comments{comment}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_textarea_tag($tool_comments, 'getComment', array (
  'size' => '30x3',
  'control_name' => 'tool_comments[comment]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('tool_comments[username]', __($labels['tool_comments{username}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('tool_comments{username}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('tool_comments{username}')): ?>
    <?php echo form_error('tool_comments{username}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($tool_comments, 'getUsername', array (
  'size' => 80,
  'control_name' => 'tool_comments[username]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('tool_comments[posted_on]', __($labels['tool_comments{posted_on}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('tool_comments{posted_on}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('tool_comments{posted_on}')): ?>
    <?php echo form_error('tool_comments{posted_on}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_date_tag($tool_comments, 'getPostedOn', array (
  'rich' => true,
  'withtime' => true,
  'calendar_button_img' => '/sf/sf_admin/images/date.png',
  'control_name' => 'tool_comments[posted_on]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

</fieldset>

<?php include_partial('edit_actions', array('tool_comments' => $tool_comments)) ?>

</form>

<ul class="sf_admin_actions">
      <li class="float-left"><?php if ($tool_comments->getPk()): ?>
<?php echo button_to(__('delete'), 'tool_comments/delete?pk='.$tool_comments->getPk(), array (
  'post' => true,
  'confirm' => __('Are you sure?'),
  'class' => 'sf_admin_action_delete',
)) ?><?php endif; ?>
</li>
  </ul>
