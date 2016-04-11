$(function() {
	/**
	 * 在标签上加addStar属性，会添加星号
	 */
	$("[addStar]").each(function() {
		var $addStar = $("<strong class='high' style='color:red'> *</strong>"); // 创建元素
		$(this).append($addStar); // 然后将它追加到文档中
	});

	/**
	 * 当input框失去焦点时校验输入是否为空
	 */
	$("[required]").blur(function() {
		if (($(this)[0].type) == "text") {// 判断是不是文本输入框
			this.style.borderColor = "";
			var val = $(this).val();
			if (validJs.isEmpty(val)) {
				this.style.borderColor = "#ff0000";
			}
		}
	});

	/**
	 * 再次点击输入框时取消输入框上的红色边框线提醒
	 */
	$("[required]").click(function() {
		this.style.borderColor = "";
	});

	/**
	 * 在输入框上加postcode属性，失去焦点时校验是否是合法的邮编
	 */
	$("[postcode]").blur(function() {
		var zip = $(this).val();
		if(!validJs.isZip(zip)){
			this.style.borderColor = "#ff0000";
		}
	});
	/**
	 * 再次点击输入框时取消输入框上的红色边框线提醒
	 */
	$("[postcode]").click(function() {
		this.style.borderColor = "";
	});
	
	/**
	 * 在输入框上加email属性，失去焦点时校验是否是合法的邮编
	 */
	$("[email]").blur(function() {
		var val = $(this).val();
		if(!validJs.isEmail(val)){
			this.style.borderColor = "#ff0000";
		}
	});
	/**
	 * 再次点击输入框时取消输入框上的红色边框线提醒
	 */
	$("[email]").click(function() {
		this.style.borderColor = "";
	});
	
	/**
	 * 在输入框上加mobile属性，失去焦点时校验是否是合法的邮编
	 */
	$("[mobile]").blur(function() {
		var val = $(this).val();
		if(!validJs.isMobile(val)){
			this.style.borderColor = "#ff0000";
		}
	});
	/**
	 * 再次点击输入框时取消输入框上的红色边框线提醒
	 */
	$("[mobile]").click(function() {
		this.style.borderColor = "";
	});
	/**
	 * 在输入框上加mobile属性，失去焦点时校验是否是合法长度
	 */
	$("[maxLen]").blur(function() {
		var len = $(this).attr("maxLen");
		var val = $(this).val();
		if(val.length>len){
			this.style.borderColor = "#ff0000";
		}
	});
	/**
	 * 再次点击输入框时取消输入框上的红色边框线提醒
	 */
	$("[maxLen]").click(function() {
		this.style.borderColor = "";
	});
	/**
	 * 在输入框上加number属性，失去焦点时校验是否是合法的邮编
	 */
	$("[number]").blur(function() {
		var val = $(this).val();
		if(!validJs.isNumber(val)){
			this.style.borderColor = "#ff0000";
		}
	});
	/**
	 * 再次点击输入框时取消输入框上的红色边框线提醒
	 */
	$("[number]").click(function() {
		this.style.borderColor = "";
	});
	/**
	 * 在输入框上加passwd属性，失去焦点时校验是否是合法的密码
	 */
	$("[passwd]").blur(function() {
		var val = $(this).val();
		if(!validJs.isPasswd(val)){
			this.style.borderColor = "#ff0000";
		}
	});
	/**
	 * 再次点击输入框时取消输入框上的红色边框线提醒
	 */
	$("[passwd]").click(function() {
		this.style.borderColor = "";
	});
})

var validJs = {
	/**
	 * 调用此方法可打开所有校验
	 * @returns {Boolean}
	 */
	triggerValid : function() {
		if(!validJs.validRequired()){
			return 0;
		}	
		if(!validJs.validEmail()){
			return 0;
		}
		if(!validJs.validZip()){
			return 0;
		}
		if(!validJs.validMobile()){
			return 0;
		}
		if(!validJs.validMaxLen()){
			return 0;
		}
		if(!validJs.validNumber()){
			return 0;
		}
		if(!validJs.validPasswd()){
			return 0;
		}
		return 1;
	},
	/**
	 * 必填字段校验
	 * @returns {Boolean}
	 */
	validRequired : function(){
		var flag = true;
		 $("[required]").each(
				function() {
					if (($(this)[0].type) == "text"
						|| ($(this)[0].type) == "textarea") {// 判断是不是文本输入框
						this.style.borderColor = "";
						var val = $(this).val();
						var message = $(this).attr("message");
						message += "不能为空！";
						if (validJs.isEmpty(val)) {
							this.style.borderColor = "#ff0000";						
							alert(message);
							flag = false
							return false;
						}						
					}
					if (($(this)[0].type) == "button") {
						this.style.borderColor = "";
						var val = $(this).attr("menu-value");
						var message = $(this).attr("message");
						message += "不能为空！";
						if (validJs.isEmpty(val)) {
							this.style.borderColor = "#ff0000";
							alert(message);
							flag = false;
							return false;
						}
					}
				});
		return flag;
	},
	/**
	 * 邮编校验
	 * @returns {Boolean}
	 */
	validZip : function(){
		var flag = true;
		$("[postcode]").each(function(){
			var val = $(this).val();
			var message = $(this).attr("message");
			message += "格式不正确！";
			if(!validJs.isZip(val)){
				this.style.borderColor = "#ff0000";
				flag = false;
				alert(message);
				flag = false;
				return false;
			}
		});
		return flag;
	},
	/**
	 * 邮箱校验
	 * @returns {Boolean}
	 */
	validEmail : function(){
		var flag = true;
		$("[email]").each(function(){
			var val = $(this).val();
			var message = $(this).attr("message");
			message += "格式不正确！";
			if(!validJs.isEmail(val)){
				this.style.borderColor = "#ff0000";
				alert(message);
				flag = false;
				return false;
			}
		});
		return flag;
	},
	/**
	 * mobile校验
	 * @returns {Boolean}
	 */
	validMobile : function(){
		var flag = true;
		$("[mobile]").each(function(){
			var val = $(this).val();
			var message = $(this).attr("message");
			message += "格式不正确！";
			if(!validJs.isMobile(val)){
				this.style.borderColor = "#ff0000";
				alert(message);
				flag = false;
				return false;
			}
		});
		return flag;
	},
	validMaxLen : function(){
		var flag = true;
		$("[maxLen]").each(function() {
			var len = $(this).attr("maxLen");
			var val = $(this).val();
			var message = $(this).attr("message");
			message += "长度不大于" + len + "!";
			if(val.length>len){
				this.style.borderColor = "#ff0000";
				alert(message);
				flag = false;
				return false;
			}
		});
		return flag;
	},
	/**
	 * number校验
	 * @returns {Boolean}
	 */
	validNumber : function(){
		var flag = true;
		$("[number]").each(function(){
			var val = $(this).val();
			var message = $(this).attr("message");
			message += "只支持数字！";
			if(!validJs.isNumber(val)){
				this.style.borderColor = "#ff0000";
				alert(message);
				flag = false;
				return false;
			}
		});
		return flag;
	},
	/**
	 * number校验
	 * @returns {Boolean}
	 */
	validPasswd : function(){
		var flag = true;
		$("[passwd]").each(function(){
			var val = $(this).val();
			var message = $(this).attr("message");
			message += "以字母开头，长度在6~18之间，只能包含字符、数字和下划线！";
			if(!validJs.isPasswd(val)){
				this.style.borderColor = "#ff0000";
				alert(message);
				flag = false;
				return false;
			}
		});
		return flag;
	},
	/**
	 * 判断变量是否是空或者空串
	 * 
	 * @param val
	 * @returns {Boolean}
	 */
	isEmpty : function(val) {
		val = val.trim();
		if (val.length == 0) {
			return true;
		}
		return false;
	},
	/**
	 * 判断是否是邮编
	 */
	isZip : function(val) {
		val = val.trim();
		if (val.length != 0) {
			reg = /^\d{6}$/;
			return reg.test(val);
		}
		return true;
	},
	/**
	 * 判断是否是邮箱
	 * @param val
	 * @returns {Boolean}
	 */
	isEmail : function(val){
		val = val.trim();
		if (val.length != 0) {
			reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;  
			return reg.test(val);
		}
		return true;
	},
	/**
	 * 判断是否是手机号
	 * @param val
	 * @returns {Boolean}
	 */
	isMobile : function(val){
		val = val.trim();
		if (val.length != 0) {
			reg=/^1\d{10}$/;  
			return reg.test(val);
		}
		return true;
	},
	/**
	 * 判断是否是数字
	 */
	isNumber : function(val){
		val = val.trim();
		if(val.length != 0){
			reg=/^[0-9]*$/;
			return reg.test(val);
		}
		return true;
	},
	
	/**
	 * 判断以字母开头，长度在6~18之间，只能包含字符、数字和下划线
	 */
	isPasswd : function(val){
		val = val.trim();
		if(val.length != 0){
			reg=/^[a-zA-Z]\w{5,17}$/;
			return reg.test(val);
		}
		return true;
	}
	
	
}
