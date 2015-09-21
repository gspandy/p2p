<#include "/cms/menu.ftl" />

 <#if message?? && "${message!}"=='1'><script>alert('提交成功')</script></#if>
<form action="modifybanner.html" method="post">
   <table id="tb1" class="tables">
        <tr>
            <th style="width:50px;">次序</th><th>图片地址</th><th>banner链接</th><th>操作</th>
        </tr>
		<#if list?? && list?size gt 0>
       <#list list as l>
           <tr>
            <td >${list?size-l_index}</td>
            <td><input type="text" id="imageUrls"  name="imageUrls" value="${l.imageUrls!}" style="width:320px;"></td>
            <td><input type="text" id="linkUrls" name="linkUrls" value="${l.linkUrls!}" style="width:320px;"></td>
            <td style="width:100px;"><a href="#" onclick="return del(this)">删除</a>&nbsp;<a href="#" onclick="up(this)">上移</a>&nbsp;<a href="#" onclick="down(this)">下移</a>
            <input type="hidden" id="errorMsg" name="errorMsg" value="${l.errorMsg!}"  >
			<input type="hidden" id="terminalAccountCount" name="terminalAccountCount" value="${l.terminalAccountCount!}" >
			<input type="hidden" id="id" name="id" value="${l.id!}"  ></td>
           </tr> 
		</#list>
		</#if>	
	</table>
	<div class="admin-right-content" style="border:0;padding:0;margin:0;width:832px;">
		<input type="submit" class="submit"  onclick="return sub()"   value="提交">　　
		<input type="button" id="btn1" class="submit"  value="添加"/>
		<br><br>
		<font color="red">注意：App第一个banner显示在列表的最后一行，最后一个banner显示在列表的第一行！</font><br>
		 <font color="red">${error!}</font>
	</div>
</form>

<script>
	String.prototype.trim = function () {
		return this .replace(/^\s\s*/, '' ).replace(/\s\s*$/, '' );
	 }
	 
	function up(obj) { 
		var objParentTR = $(obj).parent().parent(); 
		var prevTR = objParentTR.prev(); 
		if (prevTR.length > 0) { 
			prevTR.insertAfter(objParentTR); 
		} 
	} 
	
	function down(obj) { 
		var objParentTR = $(obj).parent().parent(); 
		var nextTR = objParentTR.next(); 
		if (nextTR.length > 0) { 
			nextTR.insertBefore(objParentTR); 
		} 
	} 
	
	function sub() { 
		if(confirm("你确定要提交吗？")){
			return true;
		}else{
			return false;
		}
	} 
	
	function del(obj) { 
		if(confirm("你确认要删除这条banner吗?")){
			var objParentTR = $(obj).parent().parent(); 
			objParentTR.remove();
			return true;
		}else{
			return false;
		}
	} 
	  
	window.onload=function(){
		var oTab=document.getElementById('tb1');
		var oBtn=document.getElementById('btn1');
		oBtn.onclick=function(){
			var oTr=document.createElement('tr');//创建一个tr
			//创建第一个td
			var oTd=document.createElement('td');
			//oTd.innerHTML=oTab.tBodies[0].rows.length+1;
			oTr.appendChild(oTd);//将td插入tr
			//创建第二个td
			var oTd=document.createElement('td');
			oTd.innerHTML='<input type="text" id="imageUrls"  name="imageUrls" style="width:320px;">'
			oTr.appendChild(oTd);//将td插入tr
			//创建第三个td
			var oTd=document.createElement('td');
			oTd.innerHTML='<input type="text" id="linkUrls"  name="linkUrls" style="width:320px;">'
			oTr.appendChild(oTd);//将td插入tr
			//创建第四个td
			var oTd=document.createElement('td');
			oTd.innerHTML='<a href="#" onclick="return del(this)">删除</a>&nbsp;<a href="#" onclick="up(this)">上移</a>&nbsp;<a href="#" onclick="down(this)">下移</a><input type="hidden" id="errorMsg" name="errorMsg"  ><input type="hidden" id="terminalAccountCount" name="terminalAccountCount"  ><input type="hidden" id="id" name="id" >'
			oTr.appendChild(oTd);//将td插入tr
			oTab.tBodies[0].appendChild(oTr);//将整个tr插入到表格中
		}
	}
</script>