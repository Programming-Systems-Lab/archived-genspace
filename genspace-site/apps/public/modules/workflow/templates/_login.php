<li style="letter-spacing: none; padding: 5px; border: 1px solid #CCCCCC;">
	
	<? if ($sf_user->isAuthenticated()): ?>
		<strong>Logged in as <? echo $sf_user->getAttribute('name');?>. </strong> 
		<? echo link_to('Logout', 'login/logout'); ?>
	
	<? else: 
	
		echo "<h3>Login</h3>";
		echo use_helper('Form');
		echo form_tag('login/login');
	?>
	
	Username: <? echo input_tag('username'); ?><br><br>
	Password: <? echo input_password_tag('password'); ?><br><br>
	<? echo submit_tag('login'); ?><br>
	
	
		
	<? endif; ?>
	
	
</li>