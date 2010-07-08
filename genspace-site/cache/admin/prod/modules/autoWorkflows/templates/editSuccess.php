<?php use_helper('Object', 'Validation', 'ObjectAdmin', 'I18N', 'Date') ?>

<?php use_stylesheet('/sf/sf_admin/css/main') ?>

<div id="sf_admin_container">

<h1><?php echo __('edit workflows', 
array()) ?></h1>

<div id="sf_admin_header">
<?php include_partial('workflows/edit_header', array('workflows' => $workflows)) ?>
</div>

<div id="sf_admin_content">
<?php include_partial('workflows/edit_messages', array('workflows' => $workflows, 'labels' => $labels)) ?>
<?php include_partial('workflows/edit_form', array('workflows' => $workflows, 'labels' => $labels)) ?>
</div>

<div id="sf_admin_footer">
<?php include_partial('workflows/edit_footer', array('workflows' => $workflows)) ?>
</div>

</div>
