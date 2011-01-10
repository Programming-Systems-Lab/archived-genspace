
	<?php
	
	
	 if ($sf_user->hasFlash('msg')): ?>
		<strong>Logged in as <?php echo $sf_user->getAttribute('name');?>. </strong> 
		<?php echo link_to('Logout', 'login/logout'); ?>
	
	<?php else:
	if ($sf_user->hasFlash('error')):  echo $sf_user->getFlash('error'); endif;
	if ($sf_user->hasFlash('loggedout')):  echo "<span style='color:red'>".$sf_user->getFlash('loggedout')."</span>"; endif;
	  ?>	  
      
  <form action="/login/login" method="post" >
	  Username: <input type="text" name="login[username]" id="login_username" value=""/><br><br>

	Password: <input type="password" name="login[password]" id="login_password" value="" /><br><br>
<input type="submit" value="Login"/> 
	</form>

	<?php endif;?>