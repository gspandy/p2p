<#include "/crm/menu.ftl" />

<table class="tables">
	<tr>
		<th>标题</th>
		<th>内容</th>
		<th>日期</th>
		<th>状态</th>
	</tr>
	<#if list??>
		<#list list as p>
			<tr class="th">
				<td>${(p.title)!}</td>
				<td>${(p.content)!}</td>
				<td>${(p.createtime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
				<td><#if p.status==0>未读<#elseif p.status==1>已读</#if></td>
			</tr>
		</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/admincrm/message.html?accountid=${(account.id)!}","${(map.get("page").currentPage)!}","${(map.get("page").totalPages)!}")</script>
</div>