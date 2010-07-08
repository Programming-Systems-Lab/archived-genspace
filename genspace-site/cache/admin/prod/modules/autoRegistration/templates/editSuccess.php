<?php use_helper('Object', 'Validation', 'ObjectAdmin', 'I18N', 'Date') ?>

<?php use_stylesheet('/sf/sf_admin/css/main') ?>

<div id="sf_admin_container">

<h1><?php echo __('edit registration', 
array()) ?></h1>

<div id="sf_admin_header">
<?php include_partial('registration/edit_header', array('registration' => $registration)) ?>
</div>

<div id="sf_admin_content">
<?php include_partial('registration/edit_messages', array('registration' => $registration, 'labels' => $labels)) ?>
<?php include_partial('registration/edit_form', array('registration' => $registration, 'labels' => $labels)) ?>
</div>

<div id="sf_admin_footer">
<?php include_partial('registration/edit_footer', array('registration' => $registration)) ?>
</div>

</div>
