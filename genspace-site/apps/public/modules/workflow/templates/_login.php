
<li style="letter-spacing: none; padding: 5px; border: 1px solid #f1f1f1;background-color: #f5f5f5; background-image:url(/images/bottom_fade.png); background-repeat:repeat-x; background-position:bottom">
	<h3>Login</h3>
	<?php
	
	
	if ($sf_user->isAuthenticated()): ?>
		<strong>Logged in as <?php echo $sf_user->getAttribute('name');?>. </strong> 
		<?php echo link_to('Logout', 'tool/logout'); echo " | "; echo "<a href='/tool/index?user=".$sf_user->getAttribute('username')."'>My Profile</a>"; ?>
	
	<?php else:
	if ($sf_user->hasFlash('error')):  echo "<span style='color:red'>".$sf_user->getFlash('error')."</span><br/>"; endif;
	if ($sf_user->hasFlash('loggedout')):  echo "<span style='color:red'>".$sf_user->getFlash('loggedout')."</span>";   endif;
	  ?>	  
      
  <form action="/index.php/workflow/login" method="post" ><?php if ($sf_user->hasFlash('nouserorpass')):  echo "<span style='color:red'>". $sf_user->getFlash('nouserorpass')."</span><br/>"; endif; 	?><?php if ($sf_user->hasFlash('nouser')):  echo "<span style='color:red'>". $sf_user->getFlash('nouser')."</span><br/>"; endif; 	?>
	  Username: <input type="text" name="login[username]" id="login_username" value=""/><br/><br/>
<?php if ($sf_user->hasFlash('nopass')):  echo "<span style='color:red'>". $sf_user->getFlash('nopass')."</span><br/>"; endif; 	?>
	Password: <input type="password" name="login[password]" id="login_password" value="" /><br/><a class="forgotpass" href="javascript:;" style="text-decoration:none">Forgot Password?</a><br/>
 <input name="image" type="image" style="border:0px;" src="/images/login_butt.png" onclick="/tool/index">
	</form>
<?php endif;?>
	<?php ?>
</li>