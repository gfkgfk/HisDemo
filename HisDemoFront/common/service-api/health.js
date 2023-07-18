import {
	request
} from '@/utils/request.js'


export const test = () => {
	return request({
		url: getApp().globalData.BASE_URL + "v3/helpdesk/seller/chat/list",
		method: "GET",
		data: {},
	});
};


export const test2 = () => {
	return request({
		url: getApp().globalData.BASE_URL + "v3/helpdesk/seller/chat/list",
		method: "get",
		data: {}
	});
};