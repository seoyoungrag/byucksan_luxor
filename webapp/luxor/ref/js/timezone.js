(function($) {
	var curGMT ="";
	
    $.fn.extend({

        jdigiclock: function(options) {

            var defaults = {
                clockImagesPath: '/ep/luxor/ref/image/portlet/timezone/clock/',
                gmt: '0',
                am_pm: false,
                targetId: 'timePortlet',
            	monthNames:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
    	    	dayNames:['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
    	    	yearSuffix:'',
    	    	dateSuffix:''
            };
            
            var options = $.extend(defaults, options);

            return this.each(function() {
                var $this = $(this);
                var o = options;
                var targetId = o.targetId;
                $this.clockImagesPath = o.clockImagesPath;
                $this.monthNames = o.monthNames;
                $this.dayNames = o.dayNames;
                $this.yearSuffix = o.yearSuffix;
                $this.dateSuffix = o.dateSuffix;
                $this.targetId = o.targetId;
                $this.am_pm = o.am_pm;
                $this.gmt = o.gmt;
                curGMT = o.gmt;
                $this.currDate = '';
                $this.timeUpdate = '';
                var html = '<div id="plugin_container">';
                html    += '<div id="digital_container">';
                html    += '<div id="clock"></div>';
                html    += '</div>';
                html    += '</div>';

                $this.html(html);

                $this.displayClock($this);

                var panel_pos = ($('#plugin_container > div').length - 1) * 500;
                var next = function() {
                    $('#right_arrow').unbind('click', next);
                    $('#plugin_container > div').filter(function(i) {
                        $(this).animate({'left': (i * 500) - 500 + 'px'}, 400, function() {
                            if (i == 0) {
                                $(this).appendTo('#plugin_container').css({'left':panel_pos + 'px'});
                            }
                            $('#right_arrow').bind('click', next);
                        });                        
                    });
                };
                $('#right_arrow').bind('click', next);

                var prev = function() {
                    $('#left_arrow').unbind('click', prev);
                    $('#plugin_container > div:last').prependTo('#plugin_container').css({'left':'-500px'});
                    $('#plugin_container > div').filter(function(i) {
                        $(this).animate({'left': i * 500 + 'px'}, 400, function() {
                            $('#left_arrow').bind('click', prev);
                        });
                    });
                };
                $('#left_arrow').bind('click', prev);
                $('#'+targetId).show();
            });
        }
    });
   
    
    $.fn.displayClock = function(el) {
        $.fn.getTime(el);
        setTimeout(function() {$.fn.displayClock(el)}, $.fn.delay());
    };

    $.fn.delay = function() {
        var now = new Date();
        var delay = (60 - now.getSeconds()) * 1000;
        return delay;
    };

    $.fn.getTime = function(el) {
    	if(curGMT==el.gmt) {
	        var now = new Date(); 
	        var old = new Date();
	        old.setTime(now.getTime() - 60000);
	        
	        var now_hours, now_minutes, old_hours, old_minutes, timeOld = '';
	        now.setUTCHours(now.getUTCHours()+parseInt(el.gmt), now.getUTCMinutes(), now.getUTCSeconds(), now.getUTCMilliseconds());
	        old.setUTCHours(old.getUTCHours()+parseInt(el.gmt), old.getUTCMinutes(), old.getUTCSeconds(), old.getUTCMilliseconds());
	        now_hours =  now.getUTCHours();
	        now_minutes = now.getUTCMinutes();
	        old_hours =  old.getUTCHours();
	        old_minutes = old.getUTCMinutes();
	        
	   	 	var year, month, date, week = '';
	   	 	year = now.getUTCFullYear();
	     	month = now.getUTCMonth();
	     	date = now.getUTCDate();
	     	week = now.getUTCDay();
	     	
	        $('#timePortlet .dateview').html(year+el.yearSuffix+" "+el.monthNames[month]+" "+date+el.dateSuffix+" "+el.dayNames[week]);
	        
	        if (el.am_pm) {
	            var am_pm = now_hours > 11 ? 'PM' : 'AM';
	            now_hours = ((now_hours > 12) ? now_hours - 12 : now_hours);
	            old_hours = ((old_hours > 12) ? old_hours - 12 : old_hours);
	        } 
	
	        now_hours   = ((now_hours <  10) ? "0" : "") + now_hours;
	        now_minutes = ((now_minutes <  10) ? "0" : "") + now_minutes;
	        old_hours   = ((old_hours <  10) ? "0" : "") + old_hours;
	        old_minutes = ((old_minutes <  10) ? "0" : "") + old_minutes;
	        // date
	        //el.currDate = el.lang.dayNames[now.getDay()] + ',&nbsp;' + now.getDate() + '&nbsp;' + el.lang.monthNames[now.getMonth()];
	        // time update
	        el.timeUpdate = el.currDate + ',&nbsp;' + now_hours + ':' + now_minutes;
	
	        var firstHourDigit = old_hours.substr(0,1);
	        var secondHourDigit = old_hours.substr(1,1);
	        var firstMinuteDigit = old_minutes.substr(0,1);
	        var secondMinuteDigit = old_minutes.substr(1,1);
	        
	        timeOld += '<div id="hours"><div class="line"></div>';
	        timeOld += '<div id="hours_bg"><img src="' + el.clockImagesPath + 'clockbg1.png" /></div>';
	        timeOld += '<img src="' + el.clockImagesPath + firstHourDigit + '.png" id="fhd" class="first_digit" alt="First Hour" />';
	        timeOld += '<img src="' + el.clockImagesPath + secondHourDigit + '.png" id="shd" class="second_digit" alt="Second Hour" />';
	        timeOld += '</div>';
	        timeOld += '<div id="minutes"><div class="line"></div>';
	        if (el.am_pm) {
	            timeOld += '<div id="am_pm" class="time-font-gray03">' + am_pm + '</div>';
	        }
	        timeOld += '<div id="minutes_bg"><img src="' + el.clockImagesPath + 'clockbg1.png" /></div>';
	        timeOld += '<img src="' + el.clockImagesPath + firstMinuteDigit + '.png" id="fmd" class="first_digit" alt="First Minute" />';
	        timeOld += '<img src="' + el.clockImagesPath + secondMinuteDigit + '.png" id="smd" class="second_digit" alt="Second Minute" />';
	        timeOld += '</div>';
	       
	        el.find('#clock').html(timeOld);
	
	        // set minutes
	        if (secondMinuteDigit != '9') {
	            firstMinuteDigit = firstMinuteDigit + '1';
	        }
	
	        if (old_minutes == '59') {
	            firstMinuteDigit = '511';
	        }
	
	        setTimeout(function() {
	            $('#fmd').attr('src', el.clockImagesPath + firstMinuteDigit + '-1.png');
	            $('#minutes_bg img').attr('src', el.clockImagesPath + 'clockbg2.png');
	        },200);
	        setTimeout(function() { $('#minutes_bg img').attr('src', el.clockImagesPath + 'clockbg3.png')},250);
	        setTimeout(function() {
	            $('#fmd').attr('src', el.clockImagesPath + firstMinuteDigit + '-2.png');
	            $('#minutes_bg img').attr('src', el.clockImagesPath + 'clockbg4.png');
	        },400);
	        setTimeout(function() { $('#minutes_bg img').attr('src', el.clockImagesPath + 'clockbg5.png')},450);
	        setTimeout(function() {
	            $('#fmd').attr('src', el.clockImagesPath + firstMinuteDigit + '-3.png');
	            $('#minutes_bg img').attr('src', el.clockImagesPath + 'clockbg6.png');
	        },600);
	
	        setTimeout(function() {
	            $('#smd').attr('src', el.clockImagesPath + secondMinuteDigit + '-1.png');
	            $('#minutes_bg img').attr('src', el.clockImagesPath + 'clockbg2.png');
	        },200);
	        setTimeout(function() { $('#minutes_bg img').attr('src', el.clockImagesPath + 'clockbg3.png')},250);
	        setTimeout(function() {
	            $('#smd').attr('src', el.clockImagesPath + secondMinuteDigit + '-2.png');
	            $('#minutes_bg img').attr('src', el.clockImagesPath + 'clockbg4.png');
	        },400);
	        setTimeout(function() { $('#minutes_bg img').attr('src', el.clockImagesPath + 'clockbg5.png')},450);
	        setTimeout(function() {
	            $('#smd').attr('src', el.clockImagesPath + secondMinuteDigit + '-3.png');
	            $('#minutes_bg img').attr('src', el.clockImagesPath + 'clockbg6.png');
	        },600);
	
	        setTimeout(function() {$('#fmd').attr('src', el.clockImagesPath + now_minutes.substr(0,1) + '.png')},800);
	        setTimeout(function() {$('#smd').attr('src', el.clockImagesPath + now_minutes.substr(1,1) + '.png')},800);
	        setTimeout(function() { $('#minutes_bg img').attr('src', el.clockImagesPath + 'clockbg1.png')},850);
	
	        // set hours
	        if (now_minutes == '00') {
	           
	            if (el.am_pm) {
	                if (now_hours == '00') {                   
	                    firstHourDigit = firstHourDigit + '1';
	                    now_hours = '12';
	                } else if (now_hours == '01') {
	                    firstHourDigit = '001';
	                    secondHourDigit = '111';
	                } else {
	                    firstHourDigit = firstHourDigit + '1';
	                }
	            } else {
	                if (now_hours != '10') {
	                    firstHourDigit = firstHourDigit + '1';
	                }
	
	                if (now_hours == '20') {
	                    firstHourDigit = '1';
	                }
	
	                if (now_hours == '00') {
	                    firstHourDigit = firstHourDigit + '1';
	                    secondHourDigit = secondHourDigit + '11';
	                }
	            }
	
	            setTimeout(function() {
	                $('#fhd').attr('src', el.clockImagesPath + firstHourDigit + '-1.png');
	                $('#hours_bg img').attr('src', el.clockImagesPath + 'clockbg2.png');
	            },200);
	            setTimeout(function() { $('#hours_bg img').attr('src', el.clockImagesPath + 'clockbg3.png')},250);
	            setTimeout(function() {
	                $('#fhd').attr('src', el.clockImagesPath + firstHourDigit + '-2.png');
	                $('#hours_bg img').attr('src', el.clockImagesPath + 'clockbg4.png');
	            },400);
	            setTimeout(function() { $('#hours_bg img').attr('src', el.clockImagesPath + 'clockbg5.png')},450);
	            setTimeout(function() {
	                $('#fhd').attr('src', el.clockImagesPath + firstHourDigit + '-3.png');
	                $('#hours_bg img').attr('src', el.clockImagesPath + 'clockbg6.png');
	            },600);
	
	            setTimeout(function() {
	            $('#shd').attr('src', el.clockImagesPath + secondHourDigit + '-1.png');
	            $('#hours_bg img').attr('src', el.clockImagesPath + 'clockbg2.png');
	            },200);
	            setTimeout(function() { $('#hours_bg img').attr('src', el.clockImagesPath + 'clockbg3.png')},250);
	            setTimeout(function() {
	                $('#shd').attr('src', el.clockImagesPath + secondHourDigit + '-2.png');
	                $('#hours_bg img').attr('src', el.clockImagesPath + 'clockbg4.png');
	            },400);
	            setTimeout(function() { $('#hours_bg img').attr('src', el.clockImagesPath + 'clockbg5.png')},450);
	            setTimeout(function() {
	                $('#shd').attr('src', el.clockImagesPath + secondHourDigit + '-3.png');
	                $('#hours_bg img').attr('src', el.clockImagesPath + 'clockbg6.png');
	            },600);
	
	            setTimeout(function() {$('#fhd').attr('src', el.clockImagesPath + now_hours.substr(0,1) + '.png')},800);
	            setTimeout(function() {$('#shd').attr('src', el.clockImagesPath + now_hours.substr(1,1) + '.png')},800);
	            setTimeout(function() { $('#hours_bg img').attr('src', el.clockImagesPath + 'clockbg1.png')},850);
	        }
    	}
    };
})(jQuery);