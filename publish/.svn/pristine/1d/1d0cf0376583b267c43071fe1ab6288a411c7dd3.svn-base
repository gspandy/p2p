<#include "/crm/menu.ftl" />

<table class="tables">
	<tr class="th">
		<th>银行卡号</th>
		<th>本金</th>
		<th>收益</th>
		<th>返现红包</th>
		<th>回款日期</th>
		<th>付款时间</th>
		<th>状态</th>
	</tr>
	<#if list??>
		<#list list as p>
			<tr class="th">
				<td>${(p.accountbankcard.bankcardno)!}</td>
				<td>${(p.amount)!}</td>
				<td>${(p.income)!}</td>
				<td>${(p.bonus)!}</td>
				<td>${(p.createtime?string('yyyy-MM-dd'))!}</td>
				<td>${(p.submittime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
				<td><#if p.status==0>待付款<#elseif p.status==1>系统已核实<#elseif p.status==2>财务已对账<#elseif p.status==3>已提交银行</#if></td>
			</tr>
		</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/admincrm/cashback.html?accountid=${(account.id)!}","${(map.get("page").currentPage)!}","${(map.get("page").totalPages)!}")</script>
</div>