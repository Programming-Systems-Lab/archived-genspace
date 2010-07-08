<?php echo form_tag('registration/save', array(
  'id'        => 'sf_admin_edit_form',
  'name'      => 'sf_admin_edit_form',
  'multipart' => true,
)) ?>

<?php echo object_input_hidden_tag($registration, 'getUsername') ?>

<fieldset id="sf_fieldset_none" class="">

<div class="form-row">
  <?php echo label_for('registration[password]', __($labels['registration{password}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('registration{password}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{password}')): ?>
    <?php echo form_error('registration{password}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getPassword', array (
  'size' => 50,
  'control_name' => 'registration[password]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[email]', __($labels['registration{email}']), '') ?>
  <div class="content<?php if ($sf_request->hasError('registration{email}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{email}')): ?>
    <?php echo form_error('registration{email}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getEmail', array (
  'size' => 50,
  'control_name' => 'registration[email]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[im_email]', __($labels['registration{im_email}']), '') ?>
  <div class="content<?php if ($sf_request->hasError('registration{im_email}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{im_email}')): ?>
    <?php echo form_error('registration{im_email}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getImEmail', array (
  'size' => 50,
  'control_name' => 'registration[im_email]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[im_password]', __($labels['registration{im_password}']), '') ?>
  <div class="content<?php if ($sf_request->hasError('registration{im_password}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{im_password}')): ?>
    <?php echo form_error('registration{im_password}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getImPassword', array (
  'size' => 50,
  'control_name' => 'registration[im_password]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[first_name]', __($labels['registration{first_name}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('registration{first_name}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{first_name}')): ?>
    <?php echo form_error('registration{first_name}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getFirstName', array (
  'size' => 50,
  'control_name' => 'registration[first_name]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[last_name]', __($labels['registration{last_name}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('registration{last_name}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{last_name}')): ?>
    <?php echo form_error('registration{last_name}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getLastName', array (
  'size' => 50,
  'control_name' => 'registration[last_name]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[work_title]', __($labels['registration{work_title}']), '') ?>
  <div class="content<?php if ($sf_request->hasError('registration{work_title}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{work_title}')): ?>
    <?php echo form_error('registration{work_title}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getWorkTitle', array (
  'size' => 50,
  'control_name' => 'registration[work_title]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[phone]', __($labels['registration{phone}']), '') ?>
  <div class="content<?php if ($sf_request->hasError('registration{phone}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{phone}')): ?>
    <?php echo form_error('registration{phone}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getPhone', array (
  'size' => 50,
  'control_name' => 'registration[phone]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[lab_affiliation]', __($labels['registration{lab_affiliation}']), 'class="required" ') ?>
  <div class="content<?php if ($sf_request->hasError('registration{lab_affiliation}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{lab_affiliation}')): ?>
    <?php echo form_error('registration{lab_affiliation}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getLabAffiliation', array (
  'size' => 80,
  'control_name' => 'registration[lab_affiliation]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[addr1]', __($labels['registration{addr1}']), '') ?>
  <div class="content<?php if ($sf_request->hasError('registration{addr1}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{addr1}')): ?>
    <?php echo form_error('registration{addr1}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getAddr1', array (
  'size' => 50,
  'control_name' => 'registration[addr1]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[addr2]', __($labels['registration{addr2}']), '') ?>
  <div class="content<?php if ($sf_request->hasError('registration{addr2}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{addr2}')): ?>
    <?php echo form_error('registration{addr2}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getAddr2', array (
  'size' => 50,
  'control_name' => 'registration[addr2]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[city]', __($labels['registration{city}']), '') ?>
  <div class="content<?php if ($sf_request->hasError('registration{city}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{city}')): ?>
    <?php echo form_error('registration{city}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getCity', array (
  'size' => 50,
  'control_name' => 'registration[city]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[state]', __($labels['registration{state}']), '') ?>
  <div class="content<?php if ($sf_request->hasError('registration{state}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{state}')): ?>
    <?php echo form_error('registration{state}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getState', array (
  'size' => 50,
  'control_name' => 'registration[state]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

<div class="form-row">
  <?php echo label_for('registration[zipcode]', __($labels['registration{zipcode}']), '') ?>
  <div class="content<?php if ($sf_request->hasError('registration{zipcode}')): ?> form-error<?php endif; ?>">
  <?php if ($sf_request->hasError('registration{zipcode}')): ?>
    <?php echo form_error('registration{zipcode}', array('class' => 'form-error-msg')) ?>
  <?php endif; ?>

  <?php $value = object_input_tag($registration, 'getZipcode', array (
  'size' => 20,
  'control_name' => 'registration[zipcode]',
)); echo $value ? $value : '&nbsp;' ?>
    </div>
</div>

</fieldset>

<?php include_partial('edit_actions', array('registration' => $registration)) ?>

</form>

<ul class="sf_admin_actions">
      <li class="float-left"><?php if ($registration->getUsername()): ?>
<?php echo button_to(__('delete'), 'registration/delete?username='.$registration->getUsername(), array (
  'post' => true,
  'confirm' => __('Are you sure?'),
  'class' => 'sf_admin_action_delete',
)) ?><?php endif; ?>
</li>
  </ul>
