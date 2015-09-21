<#include "/crm/menu.ftl" />

<table class="tables">
	<tr class="th">
		<th>标题</th>
		<th>红包总额</th>
		<th>使用金额</th>
		<th>使用比例</td>
		<th>有效时间</th>
		<th>使用时间</th>
		<th>返现时间</th>
		<th>状态</th>
	</tr>
	
	<#if list??>
		<#list list as p>
		<tr class="th">
			<td>${(p.title)!}</td>
			<td>${(p.totalbonus)!}</td>
			<td>${(p.usebonus)!} </td>
			<td>1：${(p.rate)!}</td>
			<td>${(p.endtime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
			<td>${(p.usetime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
			<td>${(p.backtime?string('yyyy-MM-dd'))!}</td>
			<td><#if p.status==0>未使用<#elseif p.status==1>返现中<#elseif p.status==2>已返现<#elseif p.status==3>已过期</#if></td>
		</tr>
		</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/admincrm/bonus.html?accountid=${(account.id)!}","${(map.get("page").currentPage)!}","${(map.get("page").totalPages)!}")</script>
</div>