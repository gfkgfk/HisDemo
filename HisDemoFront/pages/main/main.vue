<template>
	<view class="content">
		<swiper class="swiper" circular :indicator-dots="indicatorDots" :autoplay="autoplay" :interval="interval"
			:duration="duration">
			<swiper-item v-for="(item,index) in imgArray" :key="index">
				<view>
					<image :src="item.src" style="width: 100%" alt="" srcset="" />
				</view>
			</swiper-item>
		</swiper>
		<uni-section title="最新热点" type="line">
			<uni-card title="公告" sub-title="测试信息" extra="点击阅读" @click="cardClick">
				<text class="uni-body">这是一段测试信息;这是一段测试信息;这是一段测试信息;这是一段测试信息;这是一段测试信息</text>
			</uni-card>
		</uni-section>
		<uni-section title="自定义列数" type="line" padding>
			<uni-grid :column="4" :highlight="true">
				<uni-grid-item v-for="(item, index) in list" :index="index" :key="index">
					<view class="grid-item-box" style="background-color: #fff;" @click="clickGrid(item)">
						<uni-icons :type="item.type" :size="30" color="#777" />
						<text class="text">{{item.text}}</text>
					</view>
				</uni-grid-item>
			</uni-grid>
		</uni-section>

	</view>
</template>

<script>
	import * as utils from '@/utils/utils'
	export default {
		data() {
			return {
				indicatorDots: true,
				duration: 500,
				interval: 2000,
				autoplay: true,
				imgArray: [{
						src: '/static/img/lunbo1.jpg'
					},
					{
						src: '/static/img/lunbo2.jpg'
					},
				],
				list: [{
						text: '测试 1',
						type: 'contact',

					},
					{
						text: '测试 2',
						type: 'wallet'
					},
					{
						text: '测试 3',
						type: 'settings'
					},
					{
						text: '测试 4',
						type: 'notification',
					},
					{
						text: '测试 5',
						type: 'compose'
					},
					{
						text: '测试 6',
						type: 'vip'
					}
				]
			}
		},
		mounted() {
		},
		methods: {
			cardClick() {
				utils.showToast('功能暂未开放')
			},
			change(e) {
				let {
					index,
					text
				} = e.detail
				console.log(e.detail);
				uni.showToast({
					title: `${text}`,
					icon: 'none'
				})
			},
			clickGrid(item) {
				utils.showToast(item.text)
			}
		}
	}
</script>

<style>
	.content {}

	.grid-item-box {
		flex: 1;
		// position: relative;
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: column;
		align-items: center;
		justify-content: center;
		padding: 15px 0;
	}
</style>