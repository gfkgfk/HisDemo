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
			<uni-card title="公告" :sub-title="announcement.title?announcement.title:'------'" extra="点击阅读" @click="cardClick">
				<text class="uni-body">{{announcement.content?announcement.content:'-----------'}}</text>
			</uni-card>
		</uni-section>
		<uni-section title="快速入口" type="line" padding>
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
	import * as storage from '@/utils/storage.js'
	export default {
		data() {
			return {
				announcement:{},
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
						text: '我的',
						type: 'contact',

					},
					{
						text: '测量',
						type: 'settings'
					},
					{
						text: '社区',
						type: 'notification',
					},
					{
						text: '历史数据',
						type: 'compose'
					},
					{
						text: '测试',
						type: 'vip'
					}
				]
			}
		},
		mounted() {
			this.getLatestAnnouncement()
		},
		methods: {
			getLatestAnnouncement(){
				this.$api.getLatestAnnouncement().then(res=>{
					if (res.statusCode == 200 && res.data.state == 200) {
						this.announcement = res.data.data
					} else {
						utils.showToast('网络错误')
					}
				}).catch(error => {
						utils.showError('网络请求错误')
				})
			},
			cardClick() {
				if(!this.announcement){
					return
				}
				this.navToAnnouncement('/pages/announcement/announcement',this.announcement.id)
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
			toUser() {
				uni.switchTab({
					url: '/pages/user/user'
				})
			},
			navTo(url) {
				uni.navigateTo({
					url: url
				});
			},
			clickGrid(item) {
				switch (item.text){
					case '我的':
						this.toUser()
						break;
					case '测量':
						this.toMeasure()
						break;
					case '社区':
						this.toCommunity()
						break;
					case '历史数据':
						this.toHistory()
						break;
					default:
						utils.showToast('功能暂未开放')
						break;
				}
			},
			navToAnnouncement(url,param){
				url = `${url}?id=${param}`;
				uni.navigateTo({
					url:url,
				})
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