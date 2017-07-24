
/* string format - START. 2013.10.20 */
/* '{0} {0} {1} {2}'.format(3.14, 'a{2}bc', 'foo'); */
String.prototype.format = function () {   
	var args = arguments;   
	return this.replace(/\{(\d+)\}/g, function (m, n) { return args[n]; }); 
}; 	
/* string format - END. 2013.10.20 */

/* javascript guid generator START. 2013.10.18 */
jQuery.extend({
	getId: function(cval) {
		var aes = new AesUtil(128, 1);
		var salt = "0123456789ABCDEF";// Not modified!!!(see : AESUtil.java)
		var iv = "10006000000000000000000000000001";// Not modified!!!(see : AESUtil.java)
		var passPhrase = context_root;
		var lidPart = aes.decrypt(salt, iv, passPhrase, cval);
		return lidPart.split('-')[1];
	},
	getLogin: function() {
		var aes = new AesUtil(128, 1);
		var salt = "0123456789ABCDEF";// Not modified!!!(see : AESUtil.java)
		var iv = "10006000000000000000000000000001";// Not modified!!!(see : AESUtil.java)
		var passPhrase = context_root;	
		var timestamp = this.makeTimeStamp();
		if($('#xid').length > 0) $('#xid').remove();
		if($('#xpw').length > 0) $('#xpw').remove();
		if($('#xtm').length > 0) $('#xtm').remove();
		$('<input>').attr({
		    type: 'hidden',
		    id: 'xid',
		    name: 'xid',
		    value: aes.encrypt(salt, iv, passPhrase, this.getGuid(32, false, true) + '-' + $('#id').val() + '-' + timestamp)
		}).appendTo('#adminLoginform');
		$('<input>').attr({
		    type: 'hidden',
		    id: 'xpw',
		    name: 'xpw',
		    value: aes.encrypt(salt, iv, passPhrase, this.getGuid(32, false, true) + '-' + $('#pw').val() + '-' + timestamp)
		}).appendTo('#adminLoginform');
		$('<input>').attr({
		    type: 'hidden',
		    id: 'xtm',
		    name: 'xtm',
		    value: aes.encrypt(salt, iv, passPhrase, this.getGuid(32, false, true) + '-' + timestamp)
		}).appendTo('#adminLoginform');		
		$('#id').val('');
		$('#pw').val('');
	},
	getLoginResult: function(data) {
		var cipherText = data.loginEncResult;
		var aes = new AesUtil(128, 1);
		var salt = "0123456789ABCDEF";// Not modified!!!(see : AESUtil.java)
		var iv = "10006000000000000000000000000001";// Not modified!!!(see : AESUtil.java)
		var passPhrase = context_root;
		var loginDecResultPart = aes.decrypt(salt, iv, passPhrase, cipherText);
		var loginDecResult = loginDecResultPart.split('-')[0];//.substring(0, 1);
		if (loginDecResult == data.loginResult) {
			return data;
		} else {
			data.loginResult = loginDecResult;
			return data;
		}
	},
	getGuid: function (length, special, tmstamp) {
		var iteration = 0;
		var guid = '';
		var randomNumber;
		if(special == undefined){
			var special = false;
		}
		while(iteration < length){
			randomNumber = (Math.floor((Math.random() * 100)) % 94) + 33;
			if(!special){
				if ((randomNumber >=33) && (randomNumber <=47)) { continue; }
				if ((randomNumber >=58) && (randomNumber <=64)) { continue; }
				if ((randomNumber >=91) && (randomNumber <=96)) { continue; }
				if ((randomNumber >=123) && (randomNumber <=126)) { continue; }
			}
			iteration++;
			guid += String.fromCharCode(randomNumber);
		}
		return (tmstamp) ? this.makeTimeStamp() + guid : guid;
	},
	makeTimeStamp : function() {
		var d = new Date();
		var y = d.getUTCFullYear(),
		M = d.getUTCMonth() + 1,
		D = d.getUTCDate(),
		h = d.getUTCHours(),
		m = d.getUTCMinutes(),
		s = d.getUTCSeconds(),
		i = d.getUTCMilliseconds(),
		pad = function(x) {
			x = x + '';
			if (x.length === 1) {
				return '0' + x;
			}
			return x;
		};
		return y + pad(M) + pad(D) + pad(h) + pad(m) + pad(s) + i;
	},
	getJsQueryStrings: function(jsname) {
		var vars = [], hash, jssrc;
		var scripts = document.getElementsByTagName("script");
		for (var i = 0; i < scripts.length; i++) {
			if (scripts[i].src == '' || scripts[i].src.indexOf('/ndaum/') > 0) {
				continue;
			}
			if (scripts[i].src.indexOf(jsname) >= 0) {
				jssrc = scripts[i].src;
				break;
			}
		}
		var hashes = jssrc.slice(jssrc.indexOf('?') + 1).split('&');
		for(var i = 0; i < hashes.length; i++) {
			hash = hashes[i].split('=');
			vars.push(hash[0]);
			vars[hash[0]] = hash[1];
		}
		return vars;
	},
	getJsQueryString: function(jsname, name) {
		return this.getJsQueryStrings(jsname)[name];
	}	
	
});	
/* javascript guid generator END. 2013.10.18 */

/**
 * Javascript Array에는 remove 기능 추가
 */
Array.prototype.remove = function(object) {
	var from = this.indexOf(object);
	var to = from;
	var rest = this.slice((to || from) + 1 || this.length);
  	this.length = from < 0 ? this.length + from : from;
  	return this.push.apply(this, rest);
};

/**
* n번째 인덱스의 배열요소를 삭제
* @param index
* @return
 */
Array.prototype.removeAt = function(index) {
	var arr = this.slice(0, index).concat(this.slice(index + 1));
	return arr;
};

/**
 * Javascript Array에 indexOf 기능 추가
 * String의 indexOf와 같음
 */
Array.prototype.indexOf = function(object) {
	for (var i = 0, length = this.length; i < length; i++)
		if (this[i] == object) return i;
	return -1;
};

String.prototype.escapeHTML = function() {
	return this.replace(/</g,'&lt;').replace(/>/g, '&gt;');
};

/**
 * function same as Java's HashMap
 * 
 * ex) var cache = new HashMap();
 *     cache.put('test','good');
 *     alert(cache.get('test'));
 */
/*
var HashMap = function() {
	this.initialize();
}

HashMap.prototype = {
	initialize: function() {
		this.entrySize = 0;
        this.keyEntry = new Array();
        this.valueEntry = new Array();
	},

	indexOf: function(arr, obj) {
		for(i=0; i < arr.length; i++) {
			if(arr[i]==obj) {
				return i;
			}
		}
		return -1;
	},
	
    clear: function() {
    	var stack = new Array();
    	for(var i=0; i < this.keyEntry.length; i++) {
    		stack.push(this.keyEntry[i]);
    	}
    	while(stack.length > 0) {
    		this.remove(stack.pop());
    	}
        this.entrySize = 0;
    },

    put: function(key, value) {
    	var idx = this.indexOf(this.keyEntry,key);
    	if(idx > -1) {
	        this.valueEntry[idx] = value;
    	}
    	else {
	        this.keyEntry[this.keyEntry.length] = key;
	        this.valueEntry[this.valueEntry.length] = value;
	        this.entrySize++;
    	}
    },

    get: function(key) {
        var idx = this.indexOf(this.keyEntry,key);
        if(idx > -1 && this.valueEntry[idx]!=null) {
            return this.valueEntry[idx];
        } else {
            return null;
        }
    },

    remove: function(key) {
        var idx = this.indexOf(this.keyEntry,key);
        if(idx==-1) {
            return null;
        }
        var retValue = this.valueEntry[idx];
        
		this.keyEntry.splice(idx,1);
		this.valueEntry.splice(idx,1);		        
        this.entrySize--;
        return retValue;
    },

    size: function() {
        return this.keyEntry.length;
    },

    containsKey: function(key) {
        var idx = this.indexOf(this.keyEntry,key);
        return idx > -1;
    },

    containsValue: function(value) {
        var idx = this.indexOf(this.valueEntry,key);        
        return idx > -1;
    },
    
    toString: function() {
    	var str = "[";
    	for(var i=0; i < this.keyEntry.length; i++) {
    		var comma = ",";
    		if(i==this.keyEntry.length-1) {
    			comma = "";
    		}
    		str += this.keyEntry[i] + ":" + this.valueEntry[i] + comma; 
    	}
    	str += "]";
    	return str;
    }
}
*/
	// refactor HashMap START. 2013.10.24
	HashMap = function() {
		this.orderKeys = [];
		this.map = {};
	};
	HashMap.prototype = {
		put : function(key, value) {
			if (! this.containsKey(key)) {
				this.map[key] = value;
				this.orderKeys.push(key);
			}
		},
		get : function(key) {
			return this.map[key];
		},
		containsKey : function(key) {
			return key in this.map;
		},
		containsValue : function(value) {
			for (var prop in this.map) {
				if (this.map[prop] == value) {
					return true;
				}
			}
			return false;
		},
		clear : function() {
			for (var prop in this.map) {
				delete this.map[prop];
			}
			this.orderKeys.length = 0;
		},
		remove : function(key) {
			delete this.map[key];
			for (var i = 0; i < this.orderKeys.length; i++) {
				if (this.orderKeys[i] == key) {
					this.orderKeys.splice(i, 1); 
					break;
				}
			}
		},
		keys : function() {
			var keys =[];
			for (var prop in this.map) {
				keys.push(prop);
			}
			return keys;
		},
		keysOrder : function() { //순차적으로 키 정보를 반환
			var keys =[];
			var count = this.orderKeys.length;
			for (var i = 0; i < count; i++) {
				keys.push(this.orderKeys[i]);
			}
			return keys;
		},
		values : function() {
			var values = [];
			for (var prop in this.map) {
				values.push(this.map[prop]);
			}
			return values;
		},
		valuesOrder : function() { //순차적으로 값 정보를 반환 
			var values = [];
			var count = this.orderKeys.length;
			for (var i = 0; i < count; i++) {
				values.push(this.map[this.orderKeys[i]]);
			}
			return values;
		},
		size : function() {
			return this.orderKeys.length;
		},
		isEmpty : function() {
			return (this.orderKeys.length == 0);
		}, 
		indexByKey : function(key) { //키 위치(0 부터 시작)를 반환한다. (리턴값 '-1' : 키가 없음)
			var count = this.orderKeys.length;
			for (var i = 0; i < count; i++) {
				if (this.orderKeys[i] == key) {
					return i;
				}
			}
			return -1;
		},
		keyByIndex : function(index) { //인덱스 값에 있는 키를 반환
			var key = this.orderKeys[index];
			if (typeof key === 'undefined') { return ''; }  
			return key;
		},
		change : function(key1, key2) { //키의 정보를 바꾼다
			var value1 = this.get(key1);
			var value2 = this.get(key2);
			if (typeof value1 === 'undefined' || typeof value2 === 'undefined') { return; }
			var index1 = this.indexByKey(key1);
			var index2 = this.indexByKey(key2);
			var keys = this.keysOrder();
			var values = this.valuesOrder();
			keys[index1] = key2;
			values[index1] = value2;
			keys[index2] = key1;
			values[index2] = value1;
			this.clear();
			for (var i = 0; i < keys.length; i++) {
				this.put(keys[i], values[i]);
			}
		},
		toString : function() {
			var keys = this.keysOrder();
			var values = this.valuesOrder();
			for (var i = 0; i < keys.length; i++) {
				console.log('HashMap [ ' + i + ' ] --> (' + keys[i] + ', ' + values[i] + ')');
			}
		}
	};
	//refactor HashMap END. 2013.10.24

/**
 *  공통 함수 정의
 * 호출방법 : util.xxx();
 */
var util = {
	isLoggedIn: false,
	usePersonalMenu: false,
	popupWindow: new Array(),

	/**
	 * jQuery의 $('#id') 와 같은 역할을 함
	 * ActiveX같은 오브젝트에 접근할때 jQuery의 $('#id')를 사용할 수 없어
	 * document.getElementById를 재정의 함
	 * 
	 *  사용법은 jQuery와 같음
	 *  앞에 util만 붙여주면 됨
	 *  
	 *  예) util.$('#objId')
	 */
	$: function(id) {
		if(id.indexOf('#')!=0) {
			return null;
		}
		return document.getElementById(id.substring(1));
	},
		
	/**
	 * Unique ID 생성
	 * 서버의 CommonUtil.generateId를 호출해서 값을 받아옴
	 */
	generateId: function(prefix) {
		if(this.isNullOrEmpty(prefix)) {
			prefix = "";
		}
		var id = "";
		$.ajax({
			url: context_root+"/collaboration/jsp/common/generate_id.jsp?prefix="+prefix+"&cacheTime="+new Date(), 
			global: false,
			async: false, 
			dataType: 'text',
			success: function(data) {	
				id = util.trim(data);
				prefix = null;
			}
		});
		return id;
	},

	/**
	 * 엔터 체크
	 * 이벤트 넘겨주고 엔터일때 처리할 로직 넘겨줌
	 */
	checkEnter: function(e, action) {
		var keycode = e.keyCode || e.which;
		if(keycode==13) {
			eval(action);
		}
	},
	
	showTooltip: function(e,msg) {
		var pos = this.getMousePos(e);		
		this.tooltip.innerHTML = msg;
		this.tooltip.style.display = 'block';
		this.tooltip.style.top = pos.y + 20;
		this.tooltip.style.left = pos.x - this.tooltip.offsetWidth + 20;		
	},
	
	hideTooltip: function() {
		this.tooltip.style.display = 'none';
	},

	stripDash: function(str) {
		return str.replace(/-/,"");
	},
	
	trim: function(str) {
	    return str.replace( /^\s*/, "" ).replace( /\s*$/, "" );
	},
	
	/**
	 * Reference : http://www.bram.us/2008/02/01/javascript-isarray-check-if-an-elementobject-is-an-array/
	 */
	isArray: function(obj) {
	    return obj.constructor == Array;
	},
	
	setOpacity: function(obj,value) {
		obj.style.filter = "alpha(opacity=" + value + ")";
		obj.style.opacity = (value / 100);
		obj.style.MozOpacity = (value / 100);
		obj.style.KhtmlOpacity = (value / 100);
		if(obj.style.display=='none') {
			obj.style.display="block";
		}
	},
	
	LPad : function(s, c, n) {
		if (! s || ! c || s.length >= n) {
			return s;
		}
		var max = (n - s.length)/c.length;
		for (var i = 0; i < max; i++) {
			s = c + s;
		}
		return s;		
	},
	
	isNullOrEmpty: function(str) {
		if(typeof(str) == 'undefined' || !str || str==null || str=='null') {
			return true;
		} else {
			if(typeof(str) == 'string' && this.trim(str)=='') {
				return true;
			}
			return false;
		}
	},
	
	isBlank: function( str ) {
	    var regular = /^ *$/;
	    return str.match( regular );
	},
	
	hasBlank: function( str ) {
		for(var i=1; i < str.length-1; i++) {
			if(str.charCodeAt(i)==32) {
				return true;
			}
		}
		return false;
	},
	
	isEmail: function(str) {
		var rex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
		return rex.test(str);
	},

	isNumber: function(str) {
		var rex = /[^0-9]/;
		return !rex.test(str);
	},

	/**
	 * 특수문자가 있는지 체크 (한글허용안됨)
	 * 알파벳, 숫자, _(언더바)만 허용됨
	 */
	hasSpecialChar: function(str) {
		var rex = /[^_a-zA-Z0-9]/;
		return rex.test(str);
	},
	
	/**
	 * 특수문자가 있는지 체크 (한글허용안됨)
	 * 알파벳, 숫자만 허용됨
	 */
	hasRBGChar: function(str) {
		var rex = /[^#a-zA-Z0-9]/;
		return rex.test(str);
	},
	
	/**
	 * 숫자만 있는지 체크
	 * 숫자만 허용됨
	 */
	is_number : function(str) {
		var rex = /[0-9]/;
		return rex.test(str);
	},

	/**
	 * 특수문자가 있는지 체크 (한글허용)
	 */
	hasSpecialChar2: function(str) {
		var rex = /[\`\~\!\@\#\$\%\^\*\+\=\\\\|\'\;\:\"\?\>\<]/;
		return rex.test(str);
	},

	/**
	 * 문자열중에 특수문자 제거
	 * type이 1이면 알파벳, 숫자, _(언더바)만 남기고 모두 제거 (hasSpecialChar로 체크)
	 * type이 2이면 hasSpecialChar2로 체크해서 특수문자 제거
	 * type이 3이면 숫자만 남기고 제거
	 */
	removeSpecialChar: function(str, type) {
		if(this.isNullOrEmpty(str)) {
			return str;
		}
		if(this.isNullOrEmpty(type)) {
			type = "1";
		}
		
		var newStr = "";
		for(var i=0; i < str.length; i++) {
			var ch = str.charAt(i);
			if(type=="1") {
				if(!this.hasSpecialChar(ch)) {
					newStr += ch;
				}
			}
			if(type=="2") {
				if(!this.hasSpecialChar2(ch)) {
					newStr += ch;
				}
			}
			if (type == '3') {
				if (this.is_number(ch)) {
					newStr += ch;
				}
			}
		}
		
		return newStr;
	},

	/**
	 * 마우스 커서의 현재 위치 정보 구함
	 * jQuery 사용시에는 e.pageX, e.pageY 사용할 것
	 */ 
	getMousePos: function(e) {	
		mouseX = (e.pageX) ? e.pageX : (e.clientX + document.body.scrollLeft - document.body.clientLeft);
		mouseY = (e.pageY) ? e.pageY : (e.clientY + document.body.scrollTop - document.body.clientTop);
		return { x:mouseX, y:mouseY };
	},
	
	/**
	 * 엘리먼트의 위치정보
	 * jQuery 사용시에는 position() 사용할 것
	 */
    getAbsPos: function(element) {
        var left = 0;
        var top  = 0;

        while (element.offsetParent) {
            // add border width
            left += element.offsetLeft;
            top += element.offsetTop;
            element = element.offsetParent;
        }

        left += element.offsetLeft;
        top  += element.offsetTop;

        return {x:left, y:top};
    },

    dialog: function(obj, options, e) {
    	if(typeof(options)=='undefined') { options = { top:50, modal:true }; }
   		if(typeof(options.top)=='undefined') { options.top = 50; }
   		if(typeof(options.modal)=='undefined') { options.modal = true; }
   		
		this.setCenter(obj, options.top);
		
    	if(options.modal) {
    		obj.attr('caller',util.generateId('C'));
    		$('#alphabg').attr('caller',obj.attr('caller'));
    		$('#alphabg').height($(document).height());
    		$('#alphabg').show();
    	}
    	
    	obj.show();
    },
    
    dialogClose: function(obj, finalize) {
    	try { 
    		if(obj.attr('caller')==$('#alphabg').attr('caller')) {
    			$('#alphabg').hide();
    		}
    		obj.hide();
    		if(finalize) finalize();
    	} catch(e) {}
    },

    loading: function(obj, options) {
    	if(typeof(options)=='undefined') { options = { type:'circle', size:'s', caption:'', append:true, modal:false }; }
    	if(typeof(options.type)=='undefined') { options.type = 'circle'; }
   		if(typeof(options.size)=='undefined') { options.size = 's'; }
   		if(typeof(options.caption)=='undefined') { options.caption = ''; }
   		if(typeof(options.append)=='undefined') { options.append = true; }
   		if(typeof(options.modal)=='undefined') { options.modal = false; }
   		obj.html("<div class='loading'><img src='"+context_root+"/collaboration/ref/images/common/orgtree/loading_"+options.type+"_"+options.size+".gif' /><div>"+options.caption+"</div></div>");
   		if(!options.append) {
   			if(options.modal) {
   	    		obj.attr('caller',util.generateId('C'));
   	    		$('#alphabg').attr('caller',obj.attr('caller'));
   				$('#alphabg').height($(document).height());
   				$('#alphabg').show();
   			}
   	   		var pos = obj.offset();
   	   		var objW = obj.width();
   	   		var objH = obj.height();
   	   		var loadingW = obj.find('div.loading').width();
   	   		var loadingH = obj.find('div.loading').height();
   	   		var axisX = pos.left + objW/2 - loadingW/2;
   	   		var axisY = pos.top + objH/2 + loadingH/2;
   	   		if(axisY > 300) axisY -= 100;
   	   		obj.find('div.loading').css('position','absolute');
   	   		obj.find('div.loading').offset({top:axisY, left:axisX});
   		}
   		obj.find('div.loading').show();
    },

    loadingClose: function(obj, finalize) {
    	try {
    		if(obj.attr('caller')==$('#alphabg').attr('caller')) {
    			$('#alphabg').hide();
    		}
    		obj.find('div.loading').remove();
    		if(finalize) finalize();
    	} catch(e) {}
    },

	setCenter: function(obj,moveAmout) {
	    var _xc = parseInt($(document).width()) / 2;
	    var _yc = parseInt($(window).height()) / 2;
	    var box_w_c = parseInt(obj.width()) / 2;
	    var box_h_c = parseInt(obj.height()) / 2;
	    if(!moveAmout) {
	    	moveAmout = 0;
	    }
	    obj.css("left",(_xc - box_w_c + "px"));
	    obj.css("top",($(window).scrollTop() + _yc - box_h_c - moveAmout + "px"));
	},

	setCenterX: function(obj) {
	    var _xc = parseInt($(document).width()) / 2;
	    var box_w_c = parseInt(obj.width()) / 2;
	    obj.css("left",(_xc - box_w_c + "px"));
	},

	setCenterY: function(obj) {
	    var _yc = parseInt($(document).height()) / 2;
	    var box_h_c = parseInt(obj.height()) / 2;
	    obj.css("top",(_yc - box_h_c + "px"));
	},
	
	setAtMousePos: function(e, obj, direction) {		
		if(e.type!='click') {
			this.setCenter(obj);
			return;
		}
		var pos = this.getMousePos(e);
		obj.style.top = pos.y + 20;	
		if(!direction) {
			obj.style.left = pos.x - 20;
		}
		if(direction=='left') {
			obj.style.left = pos.x - obj.offsetWidth + 20;
		}
	},
	
	/**
	 * Validate form's input(text,password type) elements
	 */
	validateForm: function(formElement) {
		var form = $(formElement);
		var inputs = form.getInputs();
		for(var i=0; i < inputs.length; i++) {
			if(inputs[i].type=='text' || inputs[i].type=='password') {
				if(!this.trim(inputs[i].value)) {
					return inputs[i].id;
				}
			}
		}
	},
	
	escapeSpecialChar: function(str) {
		str = str.replace("#","%23");
		str = str.replace("&","%26");
		str = str.replace("\"","%22");
		str = str.replace("<","%3C");
		str = str.replace(">","%3E");
		str = str.replace("+","%2B");
		return str;
	},

	nl2br: function(str) {
		str = str.replace("\n","<br />");
		str = str.replace("\r","<br />");
		str = str.replace("\r\n","<br />");
		return str;
	},

	setCookie: function(key,value,duration) {
		if(this.isNullOrEmpty(duration)) {
			duration = 7;
		}
		var exdate = new Date();
		exdate.setDate(exdate.getDate()+duration);
		document.cookie = key+"="+escape(value)+";path=/;expires="+exdate.toGMTString();
	},
	
	getCookie: function(key) {
		if(document.cookie.length > 0) {
  			c_start=document.cookie.indexOf(key+"=");
  			if(c_start!=-1) {
			    c_start = c_start + key.length+1; 
			    c_end = document.cookie.indexOf(";",c_start);
    			if(c_end==-1) {
    				c_end = document.cookie.length;
    			}
    			return unescape(document.cookie.substring(c_start,c_end));
			}
		}
		return ""
  	},
  	
  	popup: function(url, option) {
  		var option = option;
  		var screenW = screen.availWidth;
  		var screenH = screen.availHeight;
  		var windowW = option.width;
  		var windowH = option.height;
  		var left = (screenW - windowW)/2;
  		var top = (screenH - windowH)/2;
  		
  		if(typeof(option.resizable)=='undefined') option.resizable = "no";
  		if(typeof(option.toolbar)=='undefined') option.toolbar = "no";
  		if(typeof(option.titlebar)=='undefined') option.titlebar = "no";
  		if(typeof(option.status)=='undefined') option.status = "no";
  		if(typeof(option.scrollbars)=='undefined') option.scrollbars = "no";
  		if(typeof(option.location)=='undefined') option.location = "no";
  		if(typeof(option.menubar)=='undefined') option.menubar = "no";
  		if(typeof(option.popname)=='undefined') option.popname = "";
  		var opt = "titlebar="+option.titlebar+",toolbar="+option.toolbar+",scrollbars="+option.scrollbars+",resizable="+option.resizable+",status="+option.status+",location="+option.location+",menubar="+option.menubar+",width="+windowW+",height="+windowH+",left="+left+",top="+top;

  		var openedWinObj = window.open(url,option.popname,opt);
  		if(openedWinObj != null) {
  			openedWinObj.focus();
  		}
  		this.popupWindow.push(openedWinObj);
  	},
  	
  	setOpacity: function(obj,value) {
	    obj.style.filter = "alpha(opacity=" + value + ")";
	    obj.style.opacity = (value / 100);
	    obj.style.MozOpacity = (value / 100);
	    obj.style.KhtmlOpacity = (value / 100);
	    obj.style.display="block";
	},
	
	replaceHtml: function(el, html) {
		var oldEl = typeof el === "string" ? document.getElementById(el) : el;
		var newEl = oldEl.cloneNode(false);
		newEl.innerHTML = html;
		oldEl.parentNode.replaceChild(newEl, oldEl);
		return newEl;
	}, 
	
	_resizeAlphaBG: function() {
		var docHeight = $(document).height();
		var scroll = $(window).height() + $(window).scrollTop();
		if(docHeight == scroll) {
			// 스크롤이 가장 밑으로 내려왔을때
		}
		$('#alphabg').height($(document).height());
	},
	
	setLoadingRed: function() {
		if(!$('#loading_red').is('span')) {
			$('body').append("<span id='loading_red' class='loading-red'>Loading..</span>");
			$('#loading_red').css('top',($(window).height()+$(window).scrollTop()-22));
		}
	},
	
	/* utf-8 byte size */
	charByteSize: function(ch) {
		if (ch == null || ch.length == 0) {
			return 0;
		}
		var charCode = ch.charCodeAt(0);
		if (charCode <= 0x00007F) {
			return 1;
		} else if (charCode <= 0x0007FF) {
			return 2;
		} else if (charCode <= 0x00FFFF) {
			return 3;
		} else {
			return 4;
		}
	},
	
	/* utf-8 string byte size */
	stringByteSize: function(str) {
		if (str == null || str.length == 0) {
			return 0;
		}
		var size = 0;
		for (var i = 0; i < str.length; i++) {
			size += util.charByteSize(str.charAt(i));
		}
		return size;
	},
	
	/* check input max size - use keyup */
	inputSizeChecker: function(elm, size) {
		var tmp_str = elm.val();
		var max_size = elm.attr('chkmaxlength');
		if (max_size == '' || max_size == 'undefined') {
			max_size = size;
		}
		if (max_size != '' && max_size != 'undefined') {
			var reserved_size = max_size - util.stringByteSize(tmp_str);
			
			if (reserved_size < 0) {
				alert(lxr.msg.alert_43);
				tmp_str = tmp_str.substring(0, tmp_str.length - (reserved_size * -1 / 2));
				elm.val(tmp_str);
				elm.focus();
			}
		}
	},
	
	/* required validation check */
	checkRequiredValidation: function() {
		var vidx = 0;
		$('input[type="text"], input[type="password"], textarea, select').each(function() {
			if ($(this).hasClass('required')) {
				if (util.isNullOrEmpty($(this).val())) {
					vidx++;
					$(this).focus();
					return false;
				}			
			}
		});
		
		if (vidx > 0) {
			alert(lxr.msg.alert_44);
			return false;		
		}else{
			return true;
		}
	}
};

$(window).scroll(function() { 
	util._resizeAlphaBG();
	util.setLoadingRed();
});

$(window).resize(function() { 
	util._resizeAlphaBG();
	util.setLoadingRed();
});

/**
 * 열려진 팝업창 모두 닫음
 */
$(window).unload(function() {
	try {
		while(util.popupWindow.length > 0) {
			util.popupWindow.pop().close();
		}
	} catch(e) {}
});


$(document).ready(function() {
	util.setLoadingRed();
	
	// 포탈에서 사용되는 모든 목록에는 hover 적용 (.except-hover)가 들어있는 경우에는 예외
	$('table.table-body:not(.except-hover) > tbody > tr').live('mouseover mouseout', function(e) {
		if(e.type=='mouseover') {
			$(this).addClass('hover');
		} else {
			$(this).removeClass('hover');
		}
	});
	
	// input박스에 ID와 같이 알파벳+숫자+언더바 조합만 허용할 경우 아래와 같이 only-alphabet-number-underbar 클래스를 부여
	// ex) <input type='text' class='only-alphabet-number-underbar' />
	$(':text.only-alphabet-number-underbar').live('keyup', function(e) {
		if(util.hasSpecialChar($(this).val())) {
			alert(lxr.msg.alert_41);
			$(this).val(util.removeSpecialChar($(this).val(), "1"));
			return false;
		}
	});

	// input박스에 특수문자 금지할 경우 아래와 같이 no-special-char 클래스를 부여
	// ex) <input type='text' class='no-special-char' />
	$(':text.no-special-char').live('keyup', function(e) {
		if(util.hasSpecialChar2($(this).val())) {
			alert(lxr.msg.alert_40);
			$(this).val(util.removeSpecialChar($(this).val(), "2"));
			return false;
		}
	});

	// readonly 사용한 input 필드 readonly 처리
	$('input.input-readonly').attr('readonly','readonly');
	
	// input박스, textarea의 Length를 체크할 경우 아래와 같이 chkmaxlength 속성 값을 지정한다. 
	// ex) <input type='text' chkmaxlength='10' />
	$(':text[chkmaxlength], textarea[chkmaxlength]').live('keyup', function(e) {
		util.inputSizeChecker($(this));
	});
});

