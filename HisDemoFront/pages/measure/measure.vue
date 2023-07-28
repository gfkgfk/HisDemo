<template>
	<view class="content">
		<view class="title">
			<view class="item" @click="changeActive(1)">
				<view>测量数据</view>
				<view class="indicator" @click="changeActive(1)" v-if="active==1"></view>
			</view>
			<view class="item" @click="changeActive(2)">
				<view>历史数据</view>
				<view class="indicator" v-if="active==2"></view>
			</view>
		</view>

		<view v-if="active == 1">
			<view>
				<uni-data-select :placeholder="'请选择设备'" v-model="selectDevice" :localdata="devices"
					@change="changeDevice"></uni-data-select>
			</view>
			<view class="measure-ui">
				<view>{{high}} / {{low}}</view>

			</view>
			<button class="measure-button" type="primary" :loading="loading"
				@click="start()">{{loading?'测量中':'开始测量'}}</button>
		</view>
		<view v-if="active == 2">
			<view>
				<uni-card v-for="i in 4" :is-shadow="false" is-full>
					<view>
						<text class="uni-h6">测量设备:</text>
						<text class="uni-h6">测量结果:</text>
					</view>
				</uni-card>
			</view>
		</view>
	</view>
</template>

<script>
	import * as utils from '@/utils/utils.js'
	export default {
		data() {
			return {
				high: '---',
				low: '---',
				active: 1,
				selectDevice: 1,
				devices: [{
					value: 1,
					text: "血压计"
				}],
				loading: false,
				highFinish: false,
				lowFinish: false
			}
		},
		methods: {
			changeActive(select) {
				this.active = select
			},
			changeDevice() {
				console.log(this.selectDevice);
			},
			start() {
				if (!this.selectDevice) {
					utils.showToast('请选择设备')
				}
				this.high = '---'
				this.low = '---'
				this.high = 0
				this.low = 0
				this.highFinish = false
				this.lowFinish = false
				this.loading = true
				let maxHigh = Math.floor(90 + Math.random() * (130 - 90))
				let maxHighTimer = setInterval(() => {
					this.high++
					if (this.high >= maxHigh) {
						clearInterval(maxHighTimer)
						this.highFinish = true
						this.checkFinish()
					}
				}, 30)

				let maxLow = Math.floor(60 + Math.random() * (90 - 60))
				let maxLowTimer = setInterval(() => {
					this.low++
					if (this.low >= maxLow) {
						clearInterval(maxLowTimer)
						this.lowFinish = true
						this.checkFinish()
					}
				}, 60)

			},
			checkFinish() {
				if (this.lowFinish && this.highFinish) {
					this.loading = false
					this.uploaddata()
				}
			},
			uploaddata() {
				this.$api.uploadMeasureData({
					deviceType:this.selectDevice,
					measureValue:this.high+'/'+this.low
				}).then(res=>{
					if(res.statusCode==200 && res.data.resultCode==200){
						utils.showToast('上传数据完成')
					}else{
						utils.showToast('网络错误')
					}
				}).catch(error=>{
					utils.showError('网络请求错误')
				})
				
			}


		}
	}
</script>

<style lang="scss">
	.content {
		box-sizing: border-box;
	}

	.title {
		display: flex;
		align-items: center;
		justify-content: space-around;

		.item {
			padding: 30rpx;

			.indicator {
				margin-top: 5rpx;
				height: 5rpx;
				background-color: $uni-color-primary;
			}
		}
	}

	.measure-ui {
		height: 700rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 100rpx;
	}

	.measure-button {
		width: 70%;
	}

	.card {
		height: 250rpx;
		// border: 1rpx solid $uni-color-primary;
		border-radius: 25rpx;
		background: linear-gradient(to right, $uni-color-primary, white);

		display: flex;
		align-items: center;
		justify-content: center;

		.card-text {
			font-size: 60rpx;
			color: white;
		}
	}

	.divide {
		height: 2rpx;
		margin-left: 50rpx;
		margin-right: 50rpx;
		border-radius: 50%;
		background-color: gray;
	}

	.option {
		width: 750rpx;
		display: flex;

		.history {
			height: 180rpx;
			width: 310rpx;
			// border: 1rpx solid $uni-color-primary;
			border-radius: 25rpx;
			background: linear-gradient(to right, $uni-color-warning, white);
			display: flex;
			align-items: center;
			justify-content: center;

			.card-text {
				font-size: 40rpx;
				color: white;
			}
		}

		.record {
			height: 180rpx;
			width: 320rpx;
			// border: 1rpx solid $uni-color-primary;
			border-radius: 25rpx;
			background: linear-gradient(to right, $uni-color-success, white);

			display: flex;
			align-items: center;
			justify-content: center;

			.card-text {
				font-size: 40rpx;
				color: white;
			}
		}
	}
</style>