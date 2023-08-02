<template>
	<view>
		<view class="top">
			<view class="user-info">
				<uni-icons color="white" class="avatar" type="contact" size="60"></uni-icons>
				<view class="user-pannel">
					<view class="user-name">用户名: {{userName}}</view>
					<view class="user-id">ID: {{userId}}</view>
				</view>
			</view>

		</view>
		<view class="middle">
			<uni-card title="常用服务" extra="服务列表">
				<uni-list >
					<uni-list-item link title="测量" note="采集健康数据" @click="toMeasure()"></uni-list-item>
					<uni-list-item link title="历史数据" note="查看历史测量数据" @click="toHistory()"></uni-list-item>
					<uni-list-item link title="社区" note="社区公告" @click="toCommunity()"></uni-list-item>
					<uni-list-item link title="首页" note="首页列表" @click="toHome()"></uni-list-item>
				</uni-list>
			</uni-card>
		</view>
	</view>
</template>

<script>
	import * as storage from '@/utils/storage.js'
	export default {
		data() {
			return {
				userName: '--',
				userId: '--'
			}
		},
		onLoad() {
			this.getUserInfo()
		},
		methods: {
			getUserInfo() {
				this.$api.getUserInfo({}).then(res => {
					console.log('user:', res);
					if (res.statusCode == 200 && res.data.state == 200) {
						this.userName = res.data.data.userName
						this.userId = res.data.data.id
					}
				})
			},
			toMeasure() {
				uni.switchTab({
					url: '/pages/measure/measure'
				})
			},
			toHistory() {
				storage.setItem('measureActiveIndex',2)
				uni.switchTab({
					url: '/pages/measure/measure'
				})
			},
			toCommunity() {
				uni.switchTab({
					url: '/pages/community/community'
				})
			},
			toHome() {
				uni.switchTab({
					url: '/pages/main/main'
				})
			},
			navTo(url) {
				uni.navigateTo({
					url: url
				});
			}
		}
	}
</script>

<style lang="scss">
	.top {
		background-image: url('@/static/img/user/user_bg.png');
		height: 275rpx;
		display: flex;
		align-items: center;

		.user-info {
			width: 100%;
			display: flex;
			padding-left: 20rpx;

			.user-pannel {
				padding-left: 20rpx;
				justify-content: center;
				display: flex;
				flex-direction: column;

				.user-name {
					color: white;
				}

				.user-id {
					color: white;
					font-size: $uni-font-size-sm;
				}
			}
		}

	}

	.middle {}
</style>