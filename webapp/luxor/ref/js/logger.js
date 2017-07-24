/**
 * @author Eum Jae Hyoung
 * @version $Revision: 1.1.6.1 $ $Date: 2011/02/10 06:06:01 $
 */


var LOG_LEVEL_DEBUG = 0;
var LOG_LEVEL_WARN = 1;
var LOG_LEVEL_ERROR = 2;
var LOG_LEVEL_INFO = 3;

var MSG_LOG_LEVEL = ['DEBUG', 'WARN', 'ERROR', 'INFO'];

var LOG_PERMISSION = LOG_LEVEL_DEBUG;

var Logger = {

	isLoggable: false,
	
	target : null,
	
	setLogger: function(targetPane) {
		if($('#' + targetPane)) {
			this.target = $('#' + targetPane);
			if(this.target.attr('tagName') == 'DIV' || this.target.attr('tagName') == 'SPAN') {
				this.target.html('');
				this.isLoggable = true;
				this.target.dialog({modal:false, autoOpen:false, width:1200, height:300, position:['left',500]});
			}else{
				throw('Target logging element is not DIV or SPAN.');
			}
		}else{
			throw('Target logging DIV is not exist.');
		}
	},
	
	setLoggable : function(isLoggable) {
		this.isLoggable = isLoggable;
		if(this.isLoggable) {
			this.target.dialog({autoOpen:true});
		}else{
			this.target.dialog({autoOpen:false});
		}
	},
	
	log: function (logLevel, message) {
		var logMessage = '';
		if(this.isLoggable && logLevel >= LOG_PERMISSION) {
			logMessage = '[' + MSG_LOG_LEVEL[logLevel] + ']' + message + '<br/>';
			//this.target.html(this.target.html() + logMessage);
			this.target.append(logMessage);
			this.target.attr({ scrollTop: this.target.attr("scrollHeight") });
		}
	},
	
	debug : function (message) {
		this.log(LOG_LEVEL_DEBUG, message);
	},
	
	error : function (message) {
		this.log(LOG_LEVEL_ERROR, message);
	},
	
	info : function (message) {
		this.log(LOG_LEVEL_INFO, message);
	},
	
	warn : function (message) {
		this.log(LOG_LEVEL_WARN, message);
	},
	
	clearPane: function() {
		if(this.isLoggable) {
			this.target.html('');
		}else{
			throw('Logging is not impossible status!');
		}
	}
}



