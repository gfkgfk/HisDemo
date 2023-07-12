<template>
	<view class="content">
		<view class="space"></view>
		<view class="login_title">
			<view>
				用户登录
			</view>
			<view class="line"></view>
		</view>
		<view class="login_name">
			<uni-easyinput :inputBorder="false" v-model="userName" placeholder="请输入账号"></uni-easyinput>
			<view class="gray_line_2 "></view>
		</view>
		<view class="space"></view>
		<view class="login_pwd">
			<view class="pwd_line">
				<view class="pwd">
					<uni-easyinput width="10rpx" :inputBorder="false" v-model="userPwd"
						placeholder="请输入密码"></uni-easyinput>
				</view>
				<view class="vline_2" style="height: 40rpx;"></view>
				<view class="forget" @click="forget()"> 忘记密码</view>
			</view>

			<view class="gray_line_2 "></view>
		</view>
		<view class="login_button">
			<button type="primary" plain="true" @click="login()">登录</button>
		</view>

		<view class="foot">
			<view class="foot_content">xxxxxxx</view>
		</view>
	</view>
</template>

<script>
	import * as storage from '@/utils/storage.js'
	import * as utils from '@/utils/utils.js'
	export default {
		data() {
			return {
				title: 'Hello',
				userName: '',
				userPwd: '',
			}
		},
		onLoad() {
		},
		methods: {
			forget() {
				console.log('忘记密码');
			},
			test() {
				console.log(getApp().globalData.BASE_URL);
				this.$api.test().then(res => {
					console.log('结果:', res);
				})
			},
			login() {
				if(!this.userName || !this.userPwd){
					utils.showToast('用户名密码不正确')
					return
				}
				
				this.$api.login({
					userName: this.userName,
					password:this.userPwd,
					
				}).then(res=>{
					if(res.statusCode==200 && res.data.resultCode==200){
						storage.setToken(res.data.data.token);
						this.navTo('/pages/main/main')
					}else{
						utils.showToast('用户名密码错误')
					}
				}).catch(error=>{
					utils.showError('网络请求错误')
				})

			},
			navTo(url) {
				uni.switchTab({
					url: url
				})
			}
		}
	}
</script>

<style lang="scss">
	.content {
		height: 100%;
		display: flex;
		flex-direction: column;
	}

	.space {
		height: 100rpx;
	}

	.login_title {
		padding: 100rpx;
		font-size: $uni-font-size-title;
		font-weight: 600;
	}

	.login_name {
		padding: 0rpx 100rpx 0rpx 100rpx;
	}

	.login_pwd {
		padding: 0rpx 100rpx 0rpx 100rpx;

		.pwd_line {
			display: flex;
			align-items: center;

			.pwd {
				width: 400rpx;
			}
		}

		.forget {
			padding: 0rpx 20rpx 0rpx 20rpx;
			font-size: 16rpx;
			color: $uni-text-color-grey;
		}

		.forget:active {
			color: $uni-color-primary;
		}
	}

	.line {
		width: 150rpx;
		margin-top: 10rpx;
		height: 4rpx;
		background: linear-gradient(to right, $uni-color-primary, white);
	}

	.login_button {
		margin-top: 200rpx;
		padding: 0rpx 150rpx 0rpx 150rpx;
	}

	.foot {
		width: 750rpx;
		position: fixed;
		bottom: 0rpx;
		padding-bottom: 50rpx;
		display: flex;
		justify-content: center;

		.foot_content {
			width: 100%;
			display: flex;
			justify-content: center;
			font-size: $uni-font-size-base;
			color: $uni-text-color-grey;
		}
	}
</style>