<#include "/cms/menu.ftl" />
<div class="admin-right-content">
${errorMsg!}
 <form action="modifystartpage.html" method="post">
	安卓　<input type="text" name="android"  value="${(android)!}" style="width:350px;" > <br><br>
	苹果　<input type="text" name="ios"  value="${(ios)!}" style="width:350px;" >
	<br><br>
	<input type="submit" class="submit"  onclick="return sub()"  value="提交">
</form>

<script>
function sub() { 
	if(confirm("你确定要提交吗？")){
		return true;
	}else{
		return false;
	}
} 
</script>