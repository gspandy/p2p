<#include "/pbs/menu.ftl" />

<div class="admin-right-content">
	<form action="/adminpbs/projectsave.html" method="post" enctype="multipart/form-data">
		<input type="hidden" name="token">
		<input type="hidden" name="project.id" value="${(project.id)!}">
		　　　标题　<input name="project.title" value="${(project.title)!}" style="width:500px;"><br><br>
		　关联协议　<select name="project.selfitemagreementid">
								<option>
								<#if list?exists>
								<#list list as p>
								<option value="${(p.id)!}" <#if project?? && project.selfitemagreementid?? && project.selfitemagreementid==p.id>selected</#if>>${(p.title)!}
								</#list>
								</#if>
							 </select><br><br>
		　资金用途　<div style="margin-left:78px;"><textarea name="zijinyongtu" style="width:715px;height:100px;">${(project.getField("zijinyongtu"))!}</textarea></div><br>
		　　抵押物　<div style="margin-left:78px;"><textarea name="diyawu" style="width:715px;height:100px;">${(project.getField("diyawu"))!}</textarea></div><br>
		　还款来源　<div style="margin-left:78px;"><textarea name="huankuanlaiyuan" style="width:715px;height:100px;">${(project.getField("huankuanlaiyuan"))!}</textarea></div><br>
		　项目文件　<div style="margin-left:78px;"><textarea name="projectfiles" style="width:715px;height:100px;">${(project.getField("projectfiles"))!}</textarea></div><br>
		　担保方式　<div style="margin-left:78px;"><textarea name="danbaofangshi" style="width:715px;height:100px;">${(project.getField("danbaofangshi"))!}</textarea></div><br>
		　　担保方　<div style="margin-left:78px;"><textarea name="danbaofang" style="width:715px;height:100px;">${(project.getField("danbaofang"))!}</textarea></div><br>
		　资金安全　<div style="margin-left:78px;"><textarea name="zijinanquan" style="width:715px;height:100px;">${(project.getField("zijinanquan"))!}</textarea></div><br>
		　担保文件　<div style="margin-left:78px;"><textarea name="securityfiles" style="width:715px;height:100px;">${(project.getField("securityfiles"))!}</textarea></div><br>
		<div class="submit_div"><input type="submit" class="submit" style="margin-left:79px;" value="提交"></div>
	</form>
</div>