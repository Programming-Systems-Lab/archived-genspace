<?php use_helper('Object', 'Validation', 'ObjectAdmin', 'I18N', 'Date') ?>

<?php use_stylesheet('/sf/sf_admin/css/main') ?>

<div id="sf_admin_container">

<h1><?php echo __('edit workflow_ratings', 
array()) ?></h1>

<div id="sf_admin_header">
<?php include_partial('workflow_ratings/edit_header', array('workflow_ratings' => $workflow_ratings)) ?>
</div>

<div id="sf_admin_content">
<?php include_partial('workflow_ratings/edit_messages', array('workflow_ratings' => $workflow_ratings, 'labels' => $labels)) ?>
<?php include_partial('workflow_ratings/edit_form', array('workflow_ratings' => $workflow_ratings, 'labels' => $labels)) ?>
</div>

<div id="sf_admin_footer">
<?php include_partial('workflow_ratings/edit_footer', array('workflow_ratings' => $workflow_ratings)) ?>
</div>

</div>
