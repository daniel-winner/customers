/**
 * 数字按千分位显示
 * Fans99
 * 2017.06.07
 * */
function showThousandth(s){
	if(s==""){
		return;
	}
	if(isNaN(s)){
            //return s;
		return;
    }
    else{  
            s=parseFloat(s);//去除首位输入的0，如002，小数不影响parseFloat字符串转数字  
            s=s.toString();  
            s = s.replace(/^(\d*)$/, "$1.");  
            s = (s + "00").replace(/(\d*\.\d\d)\d*/, "$1");  
            s = s.replace(".", ",");  
            var re = /(\d)(\d{3},)/;  
            while (re.test(s))  
                s = s.replace(re, "$1,$2");  
            s = s.replace(/,(\d\d)$/, ".$1");  
            return s.replace(/^\./, "0."); 
         }
}

/**
 * 模拟form提交
 * @param url
 * @param params
 * @param target 可选参数 _blank
 */
function postMyForm( url, params, target){  
    var tempform = document.createElement("form");  
    tempform.action = url;  
    tempform.method = "post";  
    tempform.style.display="none"  
    if(target) {  
        tempform.target = target;  
    }  
  
    for (var x in params) {  
        var opt = document.createElement("input");  
        opt.name = x;  
        opt.value = params[x];  
        tempform.appendChild(opt);  
    }  
  
    var opt = document.createElement("input");  
    opt.type = "submit";  
    tempform.appendChild(opt);  
    document.body.appendChild(tempform);  
    tempform.submit();  
    document.body.removeChild(tempform);  
}

/**
 * 日期时间函数
 * @param v
 * @returns {String}
 */
function f_datetime(v){
	/*
	if(v === null){
		return "";
	}*/
	if(!v){
		return "";
	}
	var date=new Date(v);
	var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? ('0' + m) : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();  
    minute = minute < 10 ? ('0' + minute) : minute;
    var second = date.getSeconds();
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
}

/**
 * 日期函数
 * @param v
 * @returns {String}
 */
function f_date(v){
	/*
	if(v === null){
		return "";
	}*/
	if(!v){
		return "";
	}
	var date=new Date(v);
	var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? '0' + m : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    return y + '-' + m + '-' + d;
}

/**
 * 获取承兑类型下拉列表
 * @param val
 * @param keyMap
 * @returns
 */
function getValueByKeyMap(val,keyMap){
	
	if(val === null || val === ""){
		return "";
	}
//	if(!val){
//		if(!isNaN(val)){
//			return "";
//		}
//	}
	return keyMap[val];
}

/**
 * 获取页面参数
 * @param name
 * @returns
 */
function getQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}