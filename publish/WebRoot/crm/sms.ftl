<#include "/crm/menu.ftl" />

<table class="tables">
	<tr class="th">
		<th>内容</th>
		<th>发送时间</th>
		<th>状态</th>
	</tr>
	<#if list??>
		<#list list as p>
			<tr class="th">
				<td>${(p.content!)}</td>
				<td>${(p.sendtime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
				<td><#if p.status==0>待发送<#elseif p.status==1>已发送<#elseif p.status==3>已验证</#if></td>
			</tr>
		</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/admincrm/sms.html?accountid=${(account.id)!}","${(map.get("page").currentPage)!}","${(map.get("page").totalPages)!}")</script>
</div>