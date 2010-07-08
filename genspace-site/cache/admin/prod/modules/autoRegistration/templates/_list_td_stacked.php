<td colspan="15">
    <?php echo link_to($registration->getUsername() ? $registration->getUsername() : __('-'), 'registration/edit?username='.$registration->getUsername()) ?>
     - 
    <?php echo $registration->getPassword() ?>
     - 
    <?php echo $registration->getEmail() ?>
     - 
    <?php echo $registration->getImEmail() ?>
     - 
    <?php echo $registration->getImPassword() ?>
     - 
    <?php echo $registration->getFirstName() ?>
     - 
    <?php echo $registration->getLastName() ?>
     - 
    <?php echo $registration->getWorkTitle() ?>
     - 
    <?php echo $registration->getPhone() ?>
     - 
    <?php echo $registration->getLabAffiliation() ?>
     - 
    <?php echo $registration->getAddr1() ?>
     - 
    <?php echo $registration->getAddr2() ?>
     - 
    <?php echo $registration->getCity() ?>
     - 
    <?php echo $registration->getState() ?>
     - 
    <?php echo $registration->getZipcode() ?>
     - 
</td>