    <td><?php echo link_to($registration->getUsername() ? $registration->getUsername() : __('-'), 'registration/edit?username='.$registration->getUsername()) ?></td>
    <td><?php echo $registration->getPassword() ?></td>
      <td><?php echo $registration->getEmail() ?></td>
      <td><?php echo $registration->getImEmail() ?></td>
      <td><?php echo $registration->getImPassword() ?></td>
      <td><?php echo $registration->getFirstName() ?></td>
      <td><?php echo $registration->getLastName() ?></td>
      <td><?php echo $registration->getWorkTitle() ?></td>
      <td><?php echo $registration->getPhone() ?></td>
      <td><?php echo $registration->getLabAffiliation() ?></td>
      <td><?php echo $registration->getAddr1() ?></td>
      <td><?php echo $registration->getAddr2() ?></td>
      <td><?php echo $registration->getCity() ?></td>
      <td><?php echo $registration->getState() ?></td>
      <td><?php echo $registration->getZipcode() ?></td>
  