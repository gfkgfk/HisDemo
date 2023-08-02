import {
	request
} from '@/utils/request.js'


export const getUserInfo = (data) => {
	return request({
		url: getApp().globalData.BASE_URL + "api/v1/user/getUserInfo",
		method: "POST",
		data: data,
	});
};


