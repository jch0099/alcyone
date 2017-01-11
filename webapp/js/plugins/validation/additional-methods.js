/**
 * 新增自定义验证规则
 * @author pigy
 * 2014.12.25
 */

// 身份证验证   
jQuery.validator.addMethod("idCard", function(value, element, param) {   
    var idCard = /^(\d{18}$|^\d{17}(\d|X|x))$/;
    if(idCard.test(value)){
       if($(param).length>0){
       	  var birthday;
       	  if(value.length == 15){  
      	  	birthday = "19"+value.substr(6,6);  
 	      } else if(value.length == 18){  
      	  	birthday = value.substr(6,8);  
   	   	  }
       	  birthday = birthday.replace(/(.{4})(.{2})/,"$1-$2-");
       	  $(param).val(birthday);
       }
    }
    return this.optional(element) || (idCard.test(value));
}, "请正确填写您的身份证号码");
//增加身份证class验证规则
jQuery.validator.addClassRules("idCard", {
  idCard: true
});

//手机号码验证
jQuery.validator.addMethod("mobile", function(value, element) {
    var length = value.length;
    var mobile =  /^(13|14|15|18)[0-9]{9}$/
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "請正確填寫您的手機號碼"); 

//增加手机号码class验证规则
jQuery.validator.addClassRules("mobile", {
	mobile: true
});

/**
 *	特殊验证：当应用此验证规则时，如果值为空，则设置默认值0
 */
jQuery.validator.addMethod("defaultInt", function(value, element) {
    var length = value.length;
    if(length == 0 || "" == value){
    	$(element).val(0);
    }
    return true;
}, "");


//增加特殊验证class验证规则
jQuery.validator.addClassRules("defaultInt", {
	defaultInt: true
});