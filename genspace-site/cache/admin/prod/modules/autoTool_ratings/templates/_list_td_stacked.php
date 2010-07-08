<td colspan="4">
    <?php echo link_to($tool_ratings->getPk() ? $tool_ratings->getPk() : __('-'), 'tool_ratings/edit?pk='.$tool_ratings->getPk()) ?>
     - 
    <?php echo $tool_ratings->getId() ?>
     - 
    <?php echo $tool_ratings->getRating() ?>
     - 
    <?php echo $tool_ratings->getUsername() ?>
     - 
</td>