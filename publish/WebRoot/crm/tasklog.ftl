<#include "/crm/menu.ftl" />

<table class="tables">
	<tr class="th">
		<th>任务</th>
		<th>积分</th>
		<th>日期</th>
	</tr>
	<#if list??>
		<#list list as p>
		<tr class="th">
			<td>${(p.task.title)!}</td>
			<td>${(p.score)!}</td>
			<td>${(p.createtime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
		</tr>
		</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/admincrm/task.html?accountid=${(account.id)!}","${(map.get("page").currentPage)!}","${(map.get("page").totalPages)!}")</script>
</div>