<#include "/crm/menu.ftl" />

<#if account?? && account.id??>
<table class="tables">
	<tr class="th">
		<th>投资人</th><td>${(account.realname)!}<${(account.realname)!}></td>
		<th>状态</th><td><#if account.status==0>已激活<#elseif account.status==1>已冻结<#elseif account.status==2>已限制</#if></td>
	</tr>
	<tr>
		<th>当前资产</th><td>${(account.accountasset.selfitemasset)!}</td>
		<th>投资本金</th><td>${(account.accountasset.selfitemamount)!}</td>
	</tr>
	<tr>	
		<th>今日收益</th><td>${(account.accountasset.selfitemtodayincome)!}</td>
		<th>累计收益</th><td>${(account.accountasset.selfitemtotalincome)!}</td>
	</tr>
	<tr>	
		<th>待收利息</th><td>${(account.accountasset.selfitemrestincome)!}</td>
		<th>回款中金额</th><td>${(account.accountasset.selfitemcashbackamount)!}</td>
	</tr>
	<tr>
		<th>最近交易</th><td>${(account.lasttradetime?string('yyyy-MM-dd'))!}</td>
		<th>交易次数</th><td>${(account.tradecount)!}</td>
	</tr>
	<tr>	
		<th>邀请人</th><td>${(inviter.realname)!}<${(inviter.mobile)!}></td>
		<th>注册渠道</th><td>${(account.channel)!}</td>
	</tr>
	<tr>
		<th>当前红包</th><td>${(account.accountasset.bonusasset)!}</td>
		<th>累计红包</th><td>${(account.accountasset.bonustotal)!}</td>
	</tr>
	<tr>	
		<th>当前积分</th><td>${(account.accountasset.scoreasset)!}</td>
		<th>累计积分</th><td>${(account.accountasset.scoretotal)!}</td>
	</tr>
	<tr>	
		<th>最近登录</th><td>${(account.lastlogintime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
		<th>注册时间</th><td>${(account.createtime?string('yyyy-MM-dd HH:mm:ss'))!}</td>
	</tr>
</table>
<#else>
	<table class="tables" style="width:1010px;">
		<tr class="th">
			<th>提问</th>
			<th>回复</th>
			<th>后台人员</th>
			<th>回复时间</th>
			<th>状态</th>
		</tr>
		<#if list??>
			<#list list as p>
				<tr class="th">
					<td>${(p.question)!}</td>
					<td>${(p.reply)!}</td>
					<td>${(p.manager)!}</td>
					<td>${(p.replytime?string('yyyy-MM-dd'))!}</td>
					<td>
						<#if (p.status)??>
							<#switch p.status>
								<#case 0>
									未回复
									<#break>
								<#case 1>
									已回复未解决
									<#break>
								<#case 2>
									已回复已解决
									<#break>
								<#default>
							</#switch>
						</#if>
					</td>
				</tr>
			</#list>
		</#if>
	</table>
</#if>