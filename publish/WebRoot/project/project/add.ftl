<#include "/project/menu.ftl" />

<div class="admin-right-content">
	<form action="/adminproject/projectsave.html" method="post" enctype="multipart/form-data">
		<input type="hidden" name="token">
		<input type="hidden" name="project.id" value="${(project.id)!}">
		　　　标题　<input name="project.title" value="${(project.title)!}" style="width:500px;"><br><br>
		　项目经理　<input name="project.leader" value="${(project.leader)!}"><br><br>
		　项目成员　<input name="project.partners" value="${(project.partners)!}" style="width:500px;"><br><br>
		　　　进度　<input name="project.progress" value="${(project.progress)!}"><br><br>
		　开始时间　<input name="project.starttime" value="${(project.starttime?string("yyyy-MM-dd"))!}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"><br><br>
		　上线时间　<input name="project.endtime" value="${(project.endtime?string("yyyy-MM-dd"))!}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"><br><br>
		　项目详情　<div style="margin-left:78px;"><textarea name="project.content" style="width:715px;height:100px;">${(project.content)!}</textarea></div><br>
		<div class="submit_div"><input type="submit" class="submit" style="margin-left:79px;" value="提交"></div>
	</form>
</div>