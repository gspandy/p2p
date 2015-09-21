/*	
	提交表单
*/
function submitForm(formId,beforeSubmitFunction){
	if(_checkfield(formId)){
		if(beforeSubmitFunction == null || eval(beforeSubmitFunction)){
			$('#' + formId).submit();
		}
	}
}


// check all field
function _checkfield(formId){
	var isAllow = true;
	$('#' + formId + ' input').each(function(index){
		if($(this).attr('type') == 'text' || $(this).attr('type') == 'password'){
			if(!_field($(this))){
				isAllow = false;
				return false;
			}
		}
	});
	if(!isAllow){
		return false;
	}
	$('#' + formId + ' textarea').each(function(index){
		if(!_field($(this))){
			isAllow = false;
			return false;
		}
	});
	if(!isAllow){
		return false;
	}
	return true;
}

// check single field
/* 
 * example:
 * 必填且为中文 --> check="required,chinese:必填项<->汉字"
 * 
 * required --> check="required:必填项"
 * length --> check="length:字符长度应在2-10之间:2,10"
 * email --> check="email:邮箱格式"
 * chinese --> check="chinese:汉字"
 * url
 * password
 * number
 * mobile
 * idcard
 * phone
 * 
 * 
 * 
 * 
 * 
 * 
 */
function _field(field){
	if(field.attr('check') != undefined && field.attr('check') != ''){
		var val = jQuery.trim(field.val()).replace(/\n/g,'');
		var data = field.attr('check').split(':');
		var types = data[0].split(',');
		var tips = data[1].split('<->');
		for(var i = 0; i < types.length; i++){
			var result = true;
			if(types[i] == 'length'){
				if(val.length < data[2].split(',')[0] || val.length > data[2].split(',')[1]){
					result = false;
				}
			}else if(types[i] == 'required'){
				if(val == ''){
					result = false;
				}
			}else if(types[i] == 'email'){
				var t = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				if(!t.test(val)){
					result = false;
				}
			}else if(types[i] == 'chinese'){
				var t = /^[\u4e00-\u9fa5]{0,}$/;
				if(!t.test(val)){
					result = false;
				}
			}else if(types[i] == 'url'){
				var t = /^http:\/\/([\w-]+\.)+[\w-]+(\/[\w-.\/?%&=]*)?$/;
				if(!t.test(val)){
					result = false;
				}
			}else if(types[i] == 'password'){
				var t = /^[a-zA-Z0-9]\w{5,17}$/;
				if(!t.test(val)){
					result = false;
				}
			}else if(types[i] == 'number'){
				var t = /^[0-9]*$/;
				if(!t.test(val)){
					result = false;
				}
			}else if(types[i] == 'mobile'){
				var t = /^1[3-9]\d{9}$/;
				if(val != '' && !t.test(val)){
					result = false;
				}
			}else if(types[i] == 'idcard'){
				var t = /^\d{15}|\d{18}$/;
				if(!t.test(val)){
					result = false;
				}
			}else if(types[i] == 'phone'){
				var t = /^1\d{10}|((0(\d{3}|\d{2}))-)?\d{7,8}(-\d*)?$/;
				if(!t.test(val)){
					result = false;
				}
			}
			if(!result){
				if(field.next().html() == null){
					$('<div class="fielderrortip" style="border:1px solid #ED9947;color:#ED9947;font-size:12px;padding-top:1px;width:205px;background:url(/style/image/icon.gif) no-repeat -260px -269px;">　　'+tips[i]+'</div>').insertAfter(field);
				}else{
					fancybox(tips[i]);
				}
				return false;
			}else{
				if(field.next().attr('class') == 'fielderrortip'){
					field.next().remove();
				}
			}
		}
	}
	return true;
}