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
			<button class="manual-measure-button" type="primary" :loading="loading"
				@click="inputDialogToggle()">{{loading?'测量中':'人工录入'}}</button>
		</view>
		<view v-if="active == 2">
			<view>
				<uni-card v-for="(item, index) in hisData" :key="index" :is-shadow="false" is-full>
					<view>
						<div class="uni-h6">测量设备:{{item.deviceType==1?'血压计':'未知'}}</div>
						<div class="uni-h6">测量结果:{{item.measureValue?item.measureValue:'--'}}</div>
					</view>
				</uni-card>
			</view>
		</view>



		<!-- Dialog -->
		<view>
			
			<uni-popup ref="inputDialog" type="dialog">
				<uni-popup-dialog ref="inputClose" mode="input" title="人工录入" @confirm="dialogInputConfirm">
					<view class="dialog-manual" >
						<view style="width: 150rpx;">录入值:</view>
						<view class="dialog-manual-val">
							<uni-easyinput v-model="currentHigh" :clearable=false focus/>
							<span class="dialog-spreader">/</span>
							<uni-easyinput v-model="currentLow" :clearable=false focus/>
						</view>
					</view>
				</uni-popup-dialog>
			</uni-popup>
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
				lowFinish: false,
				hisData: [],
				currentHigh: 0,
				currentLow: 0,
			}
		},
		methods: {
			changeActive(select) {
				this.active = select
				if (this.active == 2) {
					this.getHistoryData()
				}
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
			dialogInputConfirm() {
				if (!this.currentHigh || !this.currentLow || this.currentHigh <= 0 || this.currentLow <= 0) {
					utils.showToast('请输入值')
					return
				}
				this.uploaddata(this.selectDevice, this.currentHigh + '/' + this.currentLow)
			},
			inputDialogToggle() {
				this.$refs.inputDialog.open()
			},
			checkFinish() {
				if (this.lowFinish && this.highFinish) {
					this.loading = false
					this.uploaddata(this.selectDevice, this.high + '/' + this.low)
				}
			},
			uploaddata(deviceType, measureValue) {
				this.$api.uploadMeasureData({
					deviceType: deviceType,
					measureValue: measureValue
				}).then(res => {
					if (res.statusCode == 200) {
						utils.showToast('上传数据完成')
					} else {
						utils.showToast('网络错误')
					}
				}).catch(error => {
					utils.showError('网络请求错误')
				})
			},
			getHistoryData() {
				this.$api.getMeasureHistory({}).then(res => {
					if (res.statusCode == 200) {
						this.hisData = res.data.data
					} else {
						utils.showToast('网络错误')
					}
				}).catch(error => {
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

	.manual-measure-button {
		width: 70%;
		margin-top: 50rpx;
	}


	.dialog-manual {
		display: flex;
		justify-content: center;
		align-items: center;
		width: 100%;

		.dialog-manual-val {
			width: 250rpx;
			margin-left: 10rpx;
			display: flex;
			justify-content: center;
			align-items: center;
			padding: 5rpx;

			.dialog-spreader {
				padding: 15rpx;
			}
		}
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