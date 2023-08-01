import {
	request
} from '@/utils/request.js'


export const uploadMeasureData = (data) => {
	return request({
		url: getApp().globalData.BASE_URL + "api/v1/measure/uploadMeasureData",
		method: "POST",
		data: data,
	});
};

export const getMeasureHistory = (data) => {
	return request({
		url: getApp().globalData.BASE_URL + "api/v1/measure/getMeasureHistory",
		method: "POST",
		data: data,
	});
};

