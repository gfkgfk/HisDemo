import {
	request
} from '@/utils/request.js'


export const login = (data) => {
	return request({
		url: getApp().globalData.BASE_URL + "api/v1/login",
		method: "POST",
		data: data,
	});
};
