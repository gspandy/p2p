<#include "/crm/menu.ftl" />

<table class="tables">
	<tr class="th">
		<th>标题</th>
		<th>积分</th>
		<th>日期</th>
		<th>状态</th>
	</tr>
	<#if list??>
		<#list list as p>
		<tr class="th">
			<td>${(p.title)!}</td>
			<td><#if p.inouttype==0>+<#elseif p.inouttype==1>-</#if>${(p.score)!}</td>
			<td>${(p.createtime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
			<td><#if p.status==0>有效<#elseif p.status==1>无效</#if></td>
		</tr>
		</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/admincrm/score.html?accountid=${(account.id)!}","${(map.get("page").currentPage)!}","${(map.get("page").totalPages)!}")</script>
</div>