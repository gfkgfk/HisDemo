export const showToast = (title, duration) => {
	uni.showToast({
		icon: 'none',
		title: title ? title : '提示信息',
		duration: duration ? duration : 2000
	});
}

export const showSuccess = (title, duration) => {
	uni.showToast({
		icon: 'success',
		title: title ? title : '提示信息',
		duration: duration ? duration : 2000
	});
}

export const showError = (title, duration) => {
	uni.showToast({
		icon: 'error',
		title: title ? title : '提示信息',
		duration: duration ? duration : 2000
	});
}

/**
 * 时间格式化
 * @param {Object} fmt
 * @param {Object} date
 */
export const dateFormat = (fmt, date) => {
	date = new Date(date)
	var o = {
		"M+": date.getMonth() + 1, //月份   
		"d+": date.getDate(), //日   
		"h+": date.getHours(), //小时   
		"m+": date.getMinutes(), //分   
		"s+": date.getSeconds(), //秒   
		"q+": Math.floor((date.getMonth() + 3) / 3), //季度   
		"S": date.getMilliseconds() //毫秒   
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k])
				.length)));
	return fmt;
}