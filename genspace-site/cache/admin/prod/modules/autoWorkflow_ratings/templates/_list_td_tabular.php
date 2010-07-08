    <td><?php echo link_to($workflow_ratings->getPk() ? $workflow_ratings->getPk() : __('-'), 'workflow_ratings/edit?pk='.$workflow_ratings->getPk()) ?></td>
    <td><?php echo $workflow_ratings->getId() ?></td>
      <td><?php echo $workflow_ratings->getUsername() ?></td>
      <td><?php echo $workflow_ratings->getRating() ?></td>
  