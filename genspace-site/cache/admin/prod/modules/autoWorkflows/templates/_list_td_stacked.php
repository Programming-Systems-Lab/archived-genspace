<td colspan="3">
    <?php echo link_to($workflows->getId() ? $workflows->getId() : __('-'), 'workflows/edit?id='.$workflows->getId()) ?>
     - 
    <?php echo $workflows->getParent() ?>
     - 
    <?php echo $workflows->getTool() ?>
     - 
</td>