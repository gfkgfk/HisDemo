const tokenKey = 'token'
import * as storage from '@/utils/storage.js'
import {
	fail
} from 'assert';


export const request = (args) => {
	//配置默认参数
	var url, data, header, method

	url = args['url'] || '';
	data = args['data']
	method = args['method'] || 'GET';
	let defaultHeader = {
		"Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
		// "Content-Type": "application/json;charset=utf-8"
	};
	header = args['header'] || defaultHeader

	header = Object.assign(defaultHeader, header);
	header[tokenKey] = storage.getToken(tokenKey)


	let httpDefaultOpts = {
		url: url,
		data: data,
		method: method,
		header: header,
	}

	let promise = new Promise(function(resolve, reject) {
		uni.request({
			...httpDefaultOpts,
			success: function(res) {
				// TODO:Token过期重新获取
				resolve(res)
			},
			fail: function(error) {
				reject(error)
			},
			complete: function(res) {}
		})
	})
	return promise
};