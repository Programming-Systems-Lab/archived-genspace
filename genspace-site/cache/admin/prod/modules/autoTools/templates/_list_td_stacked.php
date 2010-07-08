<td colspan="3">
    <?php echo link_to($tools->getId() ? $tools->getId() : __('-'), 'tools/edit?id='.$tools->getId()) ?>
     - 
    <?php echo $tools->getTool() ?>
     - 
    <?php echo $tools->getDescription() ?>
     - 
</td>