/**
 * 框架基础
 */

(function (object) {
  var _$window = $(window),
      _$header = $("header"),
      _$mainContainer = $("#mainContainer"),
      _$navTree = $("#navTree"),
	  _$href = location.href;

  //外部方法
  object.base = {
    autoload : function(isFirst){
      if(isFirst){
		  _func.loadMenu();
      }
      _func.fixContainer();
    }
  };

  //内部方法
  var _func = {

    //页面加载时处理左右框架高度
    fixContainer : function(){
      var wh = _$window.height(),
          hh = _$header.outerHeight(true),
          lh = _$navTree.outerHeight(true),
          rh = _$mainContainer.outerHeight(true);
      var mh = wh - hh - 1;

      if(lh > rh){
        if(mh > lh){
          lh = mh;
        }
        _$mainContainer.css('height', lh);
      }else{
        if(mh > rh){
          rh = mh;
        }
        _$navTree.css('height', rh);
      }
    },
	
  //加载顶部跟左边菜单
	loadMenu : function(){
	   this.fixContainer();
       var loading = $('<div class="leftLoading"></div>');
	   $('#navTree').append(loading);
	   
	   _$href = _$href.replace('http://','');
	   var fristIdx = _$href.indexOf('/'),matchHref = _$href.substring(fristIdx);
	   if(typeof _path !== 'undefined'){
	      matchHref = matchHref.replace(_path,'');
	   }
	   matchHref = matchHref.substring(matchHref.indexOf('/')+1,matchHref.lastIndexOf('/'));
	   window.Util.ajax.post(_path + '/admin/loadmenu','',function(data){
		   var items = data.item;
		   $.each(items,function(key,value){
			   _func.loadTabs(value,matchHref);
		   });
		   loading.remove();
	   });
	},

    //加载总模块
    loadTabs : function(tabData,matchHref){
	   var code = tabData.code,title = tabData.title,url = tabData.url;
	   var childList = $('<li data-code="'+code+'"><a href="'+_path+'/'+url+'">'+title+'</a></li>');
	   $('#topMenu').append(childList);
	   var istrue = false,subUrl;
	   $.each(tabData.item,function(name,value){
		   var items = value.item;
		   $.each(items,function(kay,data){
			   var iurl = data.url,urlLastIdx = iurl.lastIndexOf('/'), matchUrl = iurl.substring(0,urlLastIdx);
			   if(matchHref == matchUrl){
				  istrue = true;
				  subUrl = matchUrl;
			   }
		   });
	   });
	   if(istrue){
		   childList.addClass('active');
		   $.each(tabData.item,function(key,data){
		     _func.loadNavTree(data,subUrl);
		   });
		   this.fixContainer();
	   	   this.navTreeSlide();
	   }
    },

    //加载左边菜单
    loadNavTree : function(navData,subUrl){
	   var code = navData.code,title = navData.title,icon = navData.icon,itemData = navData.item;
	   if(icon == '')
	     icon = 'list';
	   var childList = $('<li data-code="'+code+'"><a href="#'+code+'" class="nav-header js-header collapsed" data-toggle="collapse"><i class="icon-'+icon+'"></i>'+title+'</a>'
	                   + '<ul id="'+code+'" class="nav collapse nav-list" style="heigt:0;"></ul></li>');
	   var isCurrent = false;
	   $.each(itemData,function(name,value){
		   var code = value.code,title = value.title,url = value.url;
		   var itemList = $('<li data-code="'+code+'"><a href="'+_path+'/'+url+'">'+title+'</a></li>');
		   var urlLastIdx = url.lastIndexOf('/'), matchUrl = url.substring(0,urlLastIdx);
		   if(matchUrl == subUrl){
			   isCurrent = true;
			   itemList.addClass('active');
		   }
		   childList.find('ul').append(itemList);
	   });
	   $('#navTree').children('ul').append(childList);
	   if(isCurrent){
		   childList.find('.js-header').trigger('click');
	   }
    },
	
	//左边菜单效果
	navTreeSlide : function(){
		$(document).on('click','#navTree .js-header',function(){
			var siblings = $(this).parent().siblings();
			siblings.children('ul.in').slideUp('fast',function(){
				siblings.children('ul.in').removeClass('in').addClass('collapse').css({
					height : 0,
					display : 'block'
				}).prev().addClass('collapsed');
			});
		});
	}
  };
})(window.Core = window.Core || {});

//自动加载
$(function(){
  window.Core.base.autoload(true);
});

$(window).on("load resize", function(){
  window.Core.base.autoload(false);
});
