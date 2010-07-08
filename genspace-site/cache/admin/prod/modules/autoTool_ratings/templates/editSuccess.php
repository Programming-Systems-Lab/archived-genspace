<?php use_helper('Object', 'Validation', 'ObjectAdmin', 'I18N', 'Date') ?>

<?php use_stylesheet('/sf/sf_admin/css/main') ?>

<div id="sf_admin_container">

<h1><?php echo __('edit tool_ratings', 
array()) ?></h1>

<div id="sf_admin_header">
<?php include_partial('tool_ratings/edit_header', array('tool_ratings' => $tool_ratings)) ?>
</div>

<div id="sf_admin_content">
<?php include_partial('tool_ratings/edit_messages', array('tool_ratings' => $tool_ratings, 'labels' => $labels)) ?>
<?php include_partial('tool_ratings/edit_form', array('tool_ratings' => $tool_ratings, 'labels' => $labels)) ?>
</div>

<div id="sf_admin_footer">
<?php include_partial('tool_ratings/edit_footer', array('tool_ratings' => $tool_ratings)) ?>
</div>

</div>
