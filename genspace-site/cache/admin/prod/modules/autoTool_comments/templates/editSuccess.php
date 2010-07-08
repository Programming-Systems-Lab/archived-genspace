<?php use_helper('Object', 'Validation', 'ObjectAdmin', 'I18N', 'Date') ?>

<?php use_stylesheet('/sf/sf_admin/css/main') ?>

<div id="sf_admin_container">

<h1><?php echo __('edit tool_comments', 
array()) ?></h1>

<div id="sf_admin_header">
<?php include_partial('tool_comments/edit_header', array('tool_comments' => $tool_comments)) ?>
</div>

<div id="sf_admin_content">
<?php include_partial('tool_comments/edit_messages', array('tool_comments' => $tool_comments, 'labels' => $labels)) ?>
<?php include_partial('tool_comments/edit_form', array('tool_comments' => $tool_comments, 'labels' => $labels)) ?>
</div>

<div id="sf_admin_footer">
<?php include_partial('tool_comments/edit_footer', array('tool_comments' => $tool_comments)) ?>
</div>

</div>
