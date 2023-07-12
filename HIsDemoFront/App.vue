<script>
	import {
		watch
	} from 'vue'
	import * as storage from '@/utils/storage.js'
	import routingIntercept from '@/utils/routingIntercept.js'
	export default {
		globalData: {
			BASE_URL: 'http://localhost:8082/', //网络请求url地址
		},
		onLaunch: function() {
			console.log('App Launch')
			// 对路由进行统一拦截，实现路由导航守卫 router.beforeEach 功能
			routingIntercept()
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		},
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	page {
		width: 750rpx;
		margin: 0 auto;
	}

	uni-tabbar .uni-tabbar {
		-webkit-box-shadow: 0 -2px 7px 0 hsla(0, 0%, 89.8%, .5);
		box-shadow: 0 -2px 7px 0 hsla(0, 0%, 89.8%, .5);
		width: 750rpx !important;
		left: auto !important;
		right: auto !important;
	}

	uni-tabbar {
		right: 0;
		display: flex;
		justify-content: center;
	}

	uni-page-head .uni-page-head {
		width: 750rpx !important;
		left: auto !important;
		right: auto !important;
	}

	uni-page-head {
		display: flex !important;
		justify-content: center;
	}


	.gray_line_2 {
		height: 2rpx;
		background-color: gray;
	}

	.vline_2 {
		width: 2rpx;
		background-color: gray;
	}
</style>