<#include "/pbs/menu.ftl" />

<div class="admin-right-content">
	<form action="/adminpbs/selfitemsave.html" method="post" enctype="multipart/form-data">
		<input type="hidden" name="token">
		<input type="hidden" name="selfitem.id" value="${(selfitem.id)!}">
		　产品名称　<input name="selfitem.name" value="${(selfitem.name)!}"><br><br>
		　产品状态　<input type="radio" name="selfitem.status" value="0" <#if !selfitem?? || (selfitem?? && selfitem.status==0)>checked</#if>>未上线　　
							 <input type="radio" name="selfitem.status" value="1" <#if selfitem?? && selfitem.status==1>checked</#if>>已上线<br><br>
		　年化收益　<input name="selfitem.annualrate" value="${(selfitem.annualrate)!}"><br><br>
		　赠送收益　<input name="selfitem.annualrateextra" value="${(selfitem.annualrateextra)!}"><br><br>
		　募集总额　<input name="selfitem.totalamount" value="${(selfitem.totalamount)!}"><br><br>
		　起购金额　<input name="selfitem.unitprice" value="${(selfitem.unitprice)!}"><br><br>
		　计息天数　<input name="selfitem.incomedays" value="${(selfitem.incomedays)!}"><br><br>
		　限购金额　<input name="selfitem.limitamount" value="${(selfitem.limitamount)!}"><br><br>
		　开售时间　<input name="selfitem.starttime" value="${(selfitem.starttime?string("yyyy-MM-dd HH:mm:ss"))!}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"><br><br>
		　截止时间　<input name="selfitem.endtime" value="${(selfitem.endtime?string("yyyy-MM-dd"))!}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"> 23:59:59<br><br>
		　活动标签　<input name="selfitem.activitytitle" value="${(selfitem.activitytitle)!}"><br><br>
		　活动链接　<input name="selfitem.activityurl" value="${(selfitem.activityurl)!}" style="width:500px;"><br><br>
		　活动内容　<div style="margin-left:78px;"><textarea name="selfitem.activitycontent" style="width:715px;height:100px;">${(selfitem.activitycontent)!}</textarea></div><br>
		　关联项目　<select name="selfitem.selfitemprojectid">
								<option>
								<#if list?exists>
								<#list list as p>
								<option value="${(p.id)!}" <#if selfitem?? && selfitem.selfitemprojectid?? && selfitem.selfitemprojectid==p.id>selected</#if>>${(p.title)!}
								</#list>
								</#if>
							 </select>
				<br><br>
		<div class="submit_div"><input type="submit" class="submit" style="margin-left:79px;" value="提交"></div>
	</form>
</div>