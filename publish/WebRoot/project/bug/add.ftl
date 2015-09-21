<#include "/project/menu.ftl" />

<div class="admin-right-content">
	<form action="/adminproject/bugsave.html" method="post" enctype="multipart/form-data">
		<input type="hidden" name="token">
		<input type="hidden" name="bug.id" value="${(bug.id)!}">
		　　　标题　<input name="bug.title" value="${(bug.title)!}" style="width:500px;"><br><br>
		　　　描述　<div style="margin-left:78px;"><textarea name="bug.content" style="width:715px;height:100px;">${(bug.content)!}</textarea></div><br>
		　　　图片　<div style="margin-left:78px;"><textarea name="bug.files" style="width:715px;height:100px;">${(bug.files)!}</textarea></div><br>
		　　　测试　<input type="radio" name="bug.tester" value="王铅" <#if bug?? && bug.tester=="王铅">checked</#if>>王铅<br><br>
		　　　开发　<input type="radio" name="bug.developer" value="董晓鹏" <#if bug?? && bug.developer=="董晓鹏">checked</#if>>董晓鹏　　
							 <input type="radio" name="bug.developer" value="汪磊" <#if bug?? && bug.developer=="汪磊">checked</#if>>汪磊　　
							 <input type="radio" name="bug.developer" value="文金庚" <#if bug?? && bug.developer=="文金庚">checked</#if>>文金庚　　
							 <input type="radio" name="bug.developer" value="郭元壮" <#if bug?? && bug.developer=="郭元壮">checked</#if>>郭元壮　　
							 <input type="radio" name="bug.developer" value="杨晓洋" <#if bug?? && bug.developer=="杨晓洋">checked</#if>>杨晓洋<br><br>
		　　　状态　<input type="radio" name="bug.status" value="0" <#if !bug?? || (bug?? && bug.status==0)>checked</#if>>未解决　　
							 <input type="radio" name="bug.status" value="1" <#if bug?? && bug.status==1>checked</#if>>已解决　　
							 <input type="radio" name="bug.status" value="2" <#if bug?? && bug.status==2>checked</#if>>已验证　　
							 <input type="radio" name="bug.status" value="3" <#if bug?? && bug.status==3>checked</#if>>无法重现　　
							 <input type="radio" name="bug.status" value="4" <#if bug?? && bug.status==4>checked</#if>>外部原因　　
							 <input type="radio" name="bug.status" value="5" <#if bug?? && bug.status==5>checked</#if>>重新打开<br><br>
		<div class="submit_div"><input type="submit" class="submit" style="margin-left:79px;" value="提交"></div>
	</form>
</div>