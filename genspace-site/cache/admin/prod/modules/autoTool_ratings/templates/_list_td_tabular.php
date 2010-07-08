    <td><?php echo link_to($tool_ratings->getPk() ? $tool_ratings->getPk() : __('-'), 'tool_ratings/edit?pk='.$tool_ratings->getPk()) ?></td>
    <td><?php echo $tool_ratings->getId() ?></td>
      <td><?php echo $tool_ratings->getRating() ?></td>
      <td><?php echo $tool_ratings->getUsername() ?></td>
  