$(document).ready(function(){
	if(String(navigator.userAgent).match('MSIE')!=null && (Number(String(navigator.userAgent).replace(/(.+MSIE\s*)(\d+)(\D+.+)/gi,'$2'))<9)){
		// Do nothing if user browser:
		// 1. is IE (String(navigator.userAgent).match('MSIE')!=null)
		//    AND
		// 2. version lower than 9 (Number(String(navigator.userAgent).replace(/(.+MSIE\s*)(\d+)(\D+.+)/gi,'$2'))<9)
	}else{
		// Add stylish css
		$('head').append('<link rel="stylesheet" href="/hkep2/css/webplatform/effect.css">');
	}
	
    $('div.sort_bar>.sr_sortbar>a').each(function(index, element) {
		$(this).click(function(){
			
			//expand dropdown											
			if(!$(this).hasClass('expanded')){							// if you are NOT selecting the expanded one 
				$('div.sort_bar a').removeClass('expanded');			// 1. collapse all dropdown
				$(this).addClass('expanded').attr('mute','mute');		// 2. add 'expanded' class to expand target dropdown AND add a temp flag ('mute') to the current selecting dropdown
				setTimeout(function(){									// 'mute':	prevent the 'close all dropdown' effect act on the selected dropdown
					$('a[mute]').removeAttr('mute');					//			remove the temp flag ('mute') 
				},5);													//			after 5ms
			}
			
			// close all dropdown
			$('*').click(function(){										// event handler add to all element
				$('div.sort_bar a:not([mute])').removeClass('expanded');	// remove all the 'expanded' class to close all dropdown except the one just clicked to open
			});
			
			// if the temp flag ('mute') is not added, the above 2 event will act at the same time, 
			// i.e. when you click the dropdown, it will expanded and then closed at the same time
		});
	});
});