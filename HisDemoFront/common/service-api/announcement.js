import {
	request
} from '@/utils/request.js'


export const getAllAnnouncement = (data) => {
	return request({
		url: getApp().globalData.BASE_URL + "api/v1/community/getAllAnnouncement",
		method: "POST",
		data: data,
	});
};

export const getLatestAnnouncement = (data) => {
	return request({
		url: getApp().globalData.BASE_URL + "api/v1/community/getLatestAnnouncement",
		method: "POST",
		data: data,
	});
};

export const getAnnouncementById = (data) => {
	return request({
		url: getApp().globalData.BASE_URL + "api/v1/community/getAnnouncementById",
		method: "POST",
		data: data,
	});
};