<#include "/project/menu.ftl" />

<div class="admin-right-content">
	<h2>${(bug.title)!}</h2><br>
	
	${(StringUtil.convertPageToDatabase(bug.content))!}<br><br>
	
	<hr><br>
	
	<#if bug.pictures?exists>
		<#list bug.pictures as f>
			<a href="${ImageServer!}${f!}" target="_blank"><img src="${ImageServer!}${f!}" style="height:200px;"></a>
		</#list>
	</#if>
</div>