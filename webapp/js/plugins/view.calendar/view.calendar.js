// JavaScript Document

(function(){
	$.fn.viewCalendar = function(options){
		var defaults = {
            url : null,
			onSelect : null,
			initEvents : null
        },_this=this,$calendar=$(this);
		this.opt = $.extend(defaults, options);
		$date = null;
		
		var _event = {
			lang : {
				yearMonth: '{0}年{1}月',
				yearMonthDay: '{0}-{1}-{2}'
			},
			Appendzero :function(obj) {
              if (obj < 10) return "0" + obj; else return obj;
            },
			initViwe : function(){
				var $child = '<thead>'
                +'<tr class="about-table-bth cm-f14">'
                +'         <th colspan="7">'
                +'            <span class="calendar-btn js-calendar-prev pull-left cm-ml5"><i class="icon icon-caret-left cm-mr5"></i>上月</span>'
                +'            <div class="calendar-select  pull-left" >'
                +'                <input id="dtp_input1" class="js-calendar-caption" type="text" value="2014年12月" readonly>'
                +'                <span class="js-datepicker date form-month" data-date data-date-format="MM yyyy" data-link-format="yyyy年mm月" data-link-field="dtp_input1"></span>'
                +'            </div>'
                +'            <span class="calendar-btn js-calendar-next pull-right cm-mr5">下月<i class="icon icon-caret-right cm-ml5"></i></span>'
                +'         </th>'
                +'      </tr>'
                +'      <tr class="about-table-sth cm-f16">'
                +'         <th>一</th>'
                +'         <th>二</th>'
                +'         <th>三</th>'
                +'         <th>四</th>'
                +'         <th>五</th>'
                +'         <th>六</th>'
                +'         <th>日</th>'
                +'      </tr>'
                +'   </thead>'
                +'   <tbody>'
                +'   </tbody>';
				_this.append($child);
				$('.form-month').datetimepicker({
					language:  'zh-CN',
					weekStart: 1,
					autoclose: 1,
					todayHighlight: 1,
					startView: 3,
					minView: 3,
					forceParse: 0,
					format:'yyyy-mm'
				});
			}, 
			displayMonth : function(){
				var $view = $calendar.find('tbody'),$week_days = $view.find('.js-week-days');
				
				if(!$week_days.length){
					for (i = 0; i < 5; i++)
				  {
					  $tr = $('<tr class="js-week-days"></tr>');
					  for (var j = 0; j < 7; j++)
					  {
						  $tr.append('<td class="js-cell-day"><div class="calendar-day"><span class="calendar-day-number"></span><span class="calendar-day-count"></span><span class="calendar-day-icon"></span></div></td>');
					  }
					  $view.append($tr);
				  }
				}
				
				if($date == null){
					$date = new Date();
				}else if(typeof $date === 'string'){
					$date = new Date($date);
				}
				
				var $weeks = $view.find('.js-week-days'),
					firstDayOfMonth = _event.getFirstDayOfMonth($date),
					firstDay = _event.getNearbyLastWeekDay(firstDayOfMonth),
					today = new Date(),
					thisYear = $date.getFullYear(),
					thisMonth = $date.getMonth(),
					todayMonth = today.getMonth(),
					todayYear = today.getFullYear(),
					todayDate = today.getDate(),
					lastDay = firstDay.clone().addDays(5 * 7).addMilliseconds(-1),
					printDate = firstDay.clone().addDays(1).addMilliseconds(-1),
					$week,
					$day,
					$cell,
					year,
					day,
					month;			  
				 $weeks.each(function(weekIdx){
					 $week = $(this);
					 $week.find('.calendar-day').each(function(dayIndex){
						 $day = $(this);
						 $cell = $day.closest('.js-cell-day');
						 year = printDate.getFullYear();
						 day = printDate.getDate();
						 month = printDate.getMonth();
						 $cell.attr('data-date', _event.lang.yearMonthDay.format(year,_event.Appendzero(month+1),_event.Appendzero(day)));
						 $day.find('.calendar-day-number').text(day);
						 $cell.find('.calendar-day-number').toggleClass('nocurrent', (month > thisMonth || month < thisMonth));
						 $day.toggleClass('today', (day === todayDate && month === todayMonth && year === todayYear));
						 printDate.addDays(1);
					 });
				 });
				 $calendar.find('.js-calendar-caption').val(_event.lang.yearMonth.format(thisYear, thisMonth + 1));
				 //加载数据
				 var $form=_event.lang.yearMonthDay.format(firstDay.getFullYear(),_event.Appendzero(firstDay.getMonth()+1),_event.Appendzero(firstDay.getDate())),
				     $to=_event.lang.yearMonthDay.format(lastDay.getFullYear(),_event.Appendzero(lastDay.getMonth()+1),_event.Appendzero(lastDay.getDate()));
				 var area = $('#areaSel').val();
				  if(parseInt(area) > 0){
					 window.Util.ajax.post(_this.opt.url,{startdate:$form,enddate:$to,communityid:area},function(data){
						 var items = data.items;
						 try{
						 $.each(items,function(key,value){
							 $.each(value,function(date,val){
								 $('.js-cell-day[data-date='+date+']').find('.calendar-day-icon').css('background-image','url('+_path+'/platform/images/status-'+val.status+'.png)');
								 $('.js-cell-day[data-date='+date+']').find('.calendar-day-count').html(val.hours);
							 });
						 });
						 }catch(e){}
					 });
				  }
				 
			},
			getNearbyLastWeekDay : function(date, lastWeek){
				lastWeek = lastWeek || 1;
				var d = date.clone();
				while (d.getDay() != lastWeek)
				{
					d.addDays(-1);
				}
				d.setHours(0);
				d.setMinutes(0);
				d.setSeconds(0);
				d.setMilliseconds(0);
				return d;
			},
			getFirstDayOfMonth : function(date){
				var d = date.clone();
				d.setDate(1);
				return d;
			},
			bindEvents : function(){
				$(document).on('click','.js-calendar-prev',function(){
					$date.addMonths(-1);
					_event.displayMonth();
				});
				$(document).on('click','.js-calendar-next',function(){
					$date.addMonths(1);
					_event.displayMonth();
				});
				$('.js-datepicker').datetimepicker().on('changeDate', function(ev){
					$date = ev.date;
					_event.displayMonth();
				});
				$(document).on('click','.js-cell-day',function(){
					typeof _this.opt.onSelect === 'function' ? _this.opt.onSelect($(this)) : null;
				});
				$(document).on('click','#searchBtn',function(){
					var community = $('#areaSel').val(),communityTxt = $('#areaSel option:selected').html();
					if(parseInt(community) > 0){
						$('.worktime-address').html(communityTxt);
						_event.displayMonth();
						if($('#calendar').find('div.actived').length >0 ){
							var date = $('#calendar').find('div.actived').parent().data('date');
							var nowObj = $('#calendar').find('div.actived').parent();
						}else{
							var date = $('#calendar').find('div.today').parent().data('date')
							var nowObj = $('#calendar').find('div.today').parent();
						}
						nowObj.trigger('click');
						var today = date.split('-');
						$('.worktime-time').html(today[0] + '年' + today[1] + '月' + today[2] + '日');
					}else{
						var msg = $.messager.show('请选择小区', {placement: 'center'});
					}
				});
			}
		};
		$(function(){
			_event.initViwe();
			_event.displayMonth();
	  		_event.bindEvents();
			typeof _this.opt.initEvents === 'function' ? _this.opt.initEvents() : null;
		});
	}
})(jQuery)