/**
 * @description 自定义路由拦截
 */


import * as storage from '@/utils/storage.js'

// 白名单
const whiteList = [
	'/', // 注意入口页必须直接写 '/'
	'/pages/index/index',
	// { pattern: /^\/pages\/login\/*/ }
]

export default async function() {
	const list = ['navigateTo', 'redirectTo', 'reLaunch', 'switchTab']
	// 用遍历的方式分别为,uni.navigateTo,uni.redirectTo,uni.reLaunch,uni.switchTab这4个路由方法添加拦截器
	list.forEach(item => {
		uni.addInterceptor(item, {
			invoke(e) {
				// 获取要跳转的页面路径（url去掉"?"和"?"后的参数）
				const url = e.url.split('?')[0]

				// 判断当前窗口是白名单，如果是则不重定向路由
				let pass
				if (whiteList) {
					pass = whiteList.some((item) => {
						if (typeof(item) === 'object' && item.pattern) {
							return item.pattern.test(url)
						}
						return url === item
					})
				}

				// 不是白名单并且没有token
				if (!pass && !storage.getToken()) {
					uni.reLaunch({
						url: "/pages/index/index"
					})
					return false
				}
				return e
			},
			fail(err) { // 失败回调拦截
				console.log(err)
			}
		})
	})
}