    <td><?php echo link_to($workflows->getId() ? $workflows->getId() : __('-'), 'workflows/edit?id='.$workflows->getId()) ?></td>
    <td><?php echo $workflows->getParent() ?></td>
      <td><?php echo $workflows->getTool() ?></td>
  