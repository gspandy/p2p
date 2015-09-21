<#include "/crm/menu.ftl" />

<table class="tables">
	<tr class="th">
		<th>投资产品</th>
		<th>投资本金</th>
		<th>订单号</th>
		<th>银行卡号</th>
		<th>手机型号</th>
		<th>日志</th>
		<th>投资时间</th>
		<th>支付时间</th>
		<th>状态</th>
	</tr>
	<#if list??>
		<#list list as p>
			<tr class="th">
				<td>${(p.selfitem.name)!}，${(p.selfitem.annualrate)!}%<#if p.selfitem.annualrateextra!=0>+${(p.selfitem.annualrateextra)!}%</#if>，${(p.selfitem.incomedays)!}天</td>
				<td>${(p.amount)!}</td>
				<td>${(p.orderno)!}</td>
				<td>${(p.accountbankcard.bankcardno)!}</td>
				<td>${(p.createtime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
				<td>${(p.paysuccesstime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
				<td>${(p.phone)!}</td>
				<td>${(p.getField("errorMsg"))!}</td>
				<td><#if p.status==0>未支付<#elseif p.status==1>正在支付<#elseif p.status==2>支付超时<#elseif p.status==3>支付成功<#elseif p.status==4>支付失败</#if></td>
			</tr>
		</#list>
	</#if>
</table>

<div class="admin-right-content" style="border:0;">
	<script type="text/javascript">page("/admincrm/trade.html?accountid=${(account.id)!}","${(map.get("page").currentPage)!}","${(map.get("page").totalPages)!}")</script>
</div>