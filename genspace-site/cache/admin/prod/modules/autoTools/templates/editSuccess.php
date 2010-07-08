<?php use_helper('Object', 'Validation', 'ObjectAdmin', 'I18N', 'Date') ?>

<?php use_stylesheet('/sf/sf_admin/css/main') ?>

<div id="sf_admin_container">

<h1><?php echo __('edit tools', 
array()) ?></h1>

<div id="sf_admin_header">
<?php include_partial('tools/edit_header', array('tools' => $tools)) ?>
</div>

<div id="sf_admin_content">
<?php include_partial('tools/edit_messages', array('tools' => $tools, 'labels' => $labels)) ?>
<?php include_partial('tools/edit_form', array('tools' => $tools, 'labels' => $labels)) ?>
</div>

<div id="sf_admin_footer">
<?php include_partial('tools/edit_footer', array('tools' => $tools)) ?>
</div>

</div>
