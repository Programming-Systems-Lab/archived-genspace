<br><br><div id="content_full">
	<div id="post">
		<? if ($sf_user->getFlash('msg')): ?>
			<center><font color="red" size="2"><strong><? echo $sf_user->getFlash('msg'); ?></strong> <a href="javascript:history.back()">Go back</a></font></center>
		<? else: echo "<h2>Login</h2>";
		echo use_helper('Form');
		echo form_tag('login/login');
	?>
	
	Username: <? echo input_tag('username'); ?><br><br>
	Password: <? echo input_password_tag('password'); ?><br><br>
	<? echo submit_tag('login'); ?><br>
	
	
		<? endif;?>
	</div>
</div>	