<br><br><br><br>

<div style="color:red;margin-left:436px;">
	<#if errorMsg??>
		${errorMsg!}
	</#if>
	<br><br>
</div>

<div class="center">
	<form id="login_form" action="/login.html" method="post">
		<input type="hidden" name="token">
		<input type="hidden" name="url" value="${url!}">
			　　账号：　<input name="mobile" style="width:210px;"><br><br>
			　　密码：　<input name="password" style="width:210px;" type="password" /><br><br>
		<input type="button" onclick="submitForm('login_form')" class="submit" style="margin-left:-72px;margin-left:-68px\9;" value="登录" />
	</form>
</div>

