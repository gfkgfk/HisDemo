export const showToast=(title,duration)=>{
	uni.showToast({
		icon:'none',
		title: title?title:'提示信息',
		duration: duration?duration:2000
	});
}

export const showSuccess=(title,duration)=>{
	uni.showToast({
		icon:'success',
		title: title?title:'提示信息',
		duration: duration?duration:2000
	});
}

export const showError=(title,duration)=>{
	uni.showToast({
		icon:'error',
		title: title?title:'提示信息',
		duration: duration?duration:2000
	});
}