    <td><?php echo link_to($tools->getId() ? $tools->getId() : __('-'), 'tools/edit?id='.$tools->getId()) ?></td>
    <td><?php echo $tools->getTool() ?></td>
      <td><?php echo $tools->getDescription() ?></td>
  