<td colspan="4">
    <?php echo link_to($workflow_ratings->getPk() ? $workflow_ratings->getPk() : __('-'), 'workflow_ratings/edit?pk='.$workflow_ratings->getPk()) ?>
     - 
    <?php echo $workflow_ratings->getId() ?>
     - 
    <?php echo $workflow_ratings->getUsername() ?>
     - 
    <?php echo $workflow_ratings->getRating() ?>
     - 
</td>