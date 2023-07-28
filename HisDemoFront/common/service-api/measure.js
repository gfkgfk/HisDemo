import {
	request
} from '@/utils/request.js'


export const uploadMeasureData = () => {
	return request({
		url: getApp().globalData.BASE_URL + "api/v1/measure/uploadMeasureData",
		method: "POST",
		data: {},
	});
};

