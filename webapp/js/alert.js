//层垂直居中,修复ie不支持fixed bug
function JHCenter(selector){
	var left="";
	if($.browser.msie&&$.browser.version=='6.0'){
		left=parseInt($(window).scrollLeft())+parseInt($(window).width())/2-parseInt($("."+selector).outerWidth())/2;
		$("."+selector).css("left",left+"px");
		$("."+selector).css("position","absolute");
	}else{
		left=parseInt($("."+selector).outerWidth())/2;
		$("."+selector).css("margin-left",-left+"px");
	}
}
//层居中,修复ie不支持fixed bug
function JIEFixed(selector){
	var top="";
	var left="";
	if($.browser.msie&&$.browser.version=='6.0'){
		getTop=parseInt($(window).scrollTop())+parseInt($(window).height())/2-parseInt($("."+selector).outerHeight())/2;
		getLeft=parseInt($(window).scrollLeft())+parseInt($(window).width())/2-parseInt($("."+selector).outerWidth())/2;
		$("."+selector).css("top",getTop+"px");
		$("."+selector).css("left",getLeft+"px");
		$("."+selector).css("position","absolute");
	}else{
		getTop=parseInt($("."+selector).outerHeight())/2;
		getLeft=parseInt($("."+selector).outerWidth())/2;
		$("."+selector).css("margin-top",-getTop+"px");
		$("."+selector).css("margin-left",-getLeft+"px");
	}
}
//消息提示,自动消失
function JSmartMsg(selector,str){
	JFadeMsg(selector,2,str);
}
//消息提示,等待
function JFadeWaitMsg(selector,str){
	if($("."+selector).length==0)
		$("body").append($("<div class=\"comm_overlay\"></div><div class=\""+selector+"\"></div>"));
	$("."+selector).html(str);
	JIEFixed(selector);
	$(".comm_overlay").show();
	$("."+selector).fadeIn(200);
}
//消息提示,提示后关闭
function JFadeCloseMsg(selector,str){
	if($("."+selector).length==0)
		$("body").append($("<div class=\"comm_overlay\"></div><div class=\""+selector+"\"></div>"));
	$("."+selector).html(str);
	JIEFixed(selector);
	setTimeout(function(){
		$(".comm_overlay").hide();
		$("."+selector).fadeOut(200);
	},2000);
}

//关闭消息框
function JFadeClose(selector){
	$(".comm_overlay").hide();
	$("."+selector).fadeOut(200);
}

//消息提示,通用
function JFadeMsg(selector,maxTime,str){
	var alertTimer;
	var fadeTime=maxTime;
	if($("."+selector).length==0){
		$("body").append($("<div class=\"comm_overlay\"></div><div class=\""+selector+"\"></div>"));
		$("."+selector).html(str);
	}else{
		$("."+selector).html(str);
	}
	JIEFixed(selector);
	$(".comm_overlay").show();
	$("."+selector).fadeIn(200);
	alertTimer=setInterval(function(){
		fadeTime--;
		//到时间隐藏
		if(fadeTime<0){
			try{
				$(".comm_overlay").hide();
				$("."+selector).fadeOut(200,function(){
					try{
						fadeTime=maxTime;
						clearInterval(alertTimer);
					}catch(e){}
				});
			}catch(e){}
		}
	},400);
}