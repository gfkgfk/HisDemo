<script>
	export default {
		globalData:{  
			  BASE_URL:'http://localhost:8001/',//网络请求url地址
		}, 
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	page {
		width: 750rpx;
		margin: 0 auto;
		height: 100%;
	}
</style>
