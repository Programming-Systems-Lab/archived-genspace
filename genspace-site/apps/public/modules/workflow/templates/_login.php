<li style="letter-spacing: none; padding: 5px; border: 1px solid #CCCCCC;">
	
	<?php if ($sf_user->isAuthenticated()): ?>
		<strong>Logged in as <?php echo $sf_user->getAttribute('name');?>. </strong> 
		<?php echo link_to('Logout', 'login/logout'); ?>
	
	<?php else: 
	
		echo "<h3>Login</h3>";
		echo use_helper('Form');
		echo form_tag('login/login');
	?>
	
	Username: <?php echo input_tag('username'); ?><br><br>
	Password: <?php echo input_password_tag('password'); ?><br><br>
	<?php echo submit_tag('login'); ?><br>
	
	
		
	<?php endif; ?>
	
	
</li>