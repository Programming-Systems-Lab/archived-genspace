    <td><?php echo link_to($tool_comments->getPk() ? $tool_comments->getPk() : __('-'), 'tool_comments/edit?pk='.$tool_comments->getPk()) ?></td>
    <td><?php echo $tool_comments->getId() ?></td>
      <td><?php echo $tool_comments->getComment() ?></td>
      <td><?php echo $tool_comments->getUsername() ?></td>
      <td><?php echo ($tool_comments->getPostedOn() !== null && $tool_comments->getPostedOn() !== '') ? format_date($tool_comments->getPostedOn(), "f") : '' ?></td>
  