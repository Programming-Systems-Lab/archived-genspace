<td colspan="5">
    <?php echo link_to($tool_comments->getPk() ? $tool_comments->getPk() : __('-'), 'tool_comments/edit?pk='.$tool_comments->getPk()) ?>
     - 
    <?php echo $tool_comments->getId() ?>
     - 
    <?php echo $tool_comments->getComment() ?>
     - 
    <?php echo $tool_comments->getUsername() ?>
     - 
    <?php echo ($tool_comments->getPostedOn() !== null && $tool_comments->getPostedOn() !== '') ? format_date($tool_comments->getPostedOn(), "f") : '' ?>
     - 
</td>