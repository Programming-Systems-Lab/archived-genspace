<table cellspacing="0" class="sf_admin_list">
<thead>
<tr>
<?php include_partial('list_th_tabular') ?>
</tr>
</thead>
<tfoot>
<tr><th colspan="4">
<div class="float-right">
<?php if ($pager->haveToPaginate()): ?>
  <?php echo link_to(image_tag(sfConfig::get('sf_admin_web_dir').'/images/first.png', array('align' => 'absmiddle', 'alt' => __('First'), 'title' => __('First'))), 'workflow_ratings/list?page=1') ?>
  <?php echo link_to(image_tag(sfConfig::get('sf_admin_web_dir').'/images/previous.png', array('align' => 'absmiddle', 'alt' => __('Previous'), 'title' => __('Previous'))), 'workflow_ratings/list?page='.$pager->getPreviousPage()) ?>

  <?php foreach ($pager->getLinks() as $page): ?>
    <?php echo link_to_unless($page == $pager->getPage(), $page, 'workflow_ratings/list?page='.$page) ?>
  <?php endforeach; ?>

  <?php echo link_to(image_tag(sfConfig::get('sf_admin_web_dir').'/images/next.png', array('align' => 'absmiddle', 'alt' => __('Next'), 'title' => __('Next'))), 'workflow_ratings/list?page='.$pager->getNextPage()) ?>
  <?php echo link_to(image_tag(sfConfig::get('sf_admin_web_dir').'/images/last.png', array('align' => 'absmiddle', 'alt' => __('Last'), 'title' => __('Last'))), 'workflow_ratings/list?page='.$pager->getLastPage()) ?>
<?php endif; ?>
</div>
<?php echo format_number_choice('[0] no result|[1] 1 result|(1,+Inf] %1% results', array('%1%' => $pager->getNbResults()), $pager->getNbResults()) ?>
</th></tr>
</tfoot>
<tbody>
<?php $i = 1; foreach ($pager->getResults() as $workflow_ratings): $odd = fmod(++$i, 2) ?>
<tr class="sf_admin_row_<?php echo $odd ?>">
<?php include_partial('list_td_batch_actions', array('workflow_ratings' => $workflow_ratings)) ?>
<?php include_partial('list_td_tabular', array('workflow_ratings' => $workflow_ratings)) ?>
<?php include_partial('list_td_actions', array('workflow_ratings' => $workflow_ratings)) ?>
</tr>
<?php endforeach; ?>
</tbody>
</table>
