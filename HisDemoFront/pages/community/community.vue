<template>
	<view>
		<uni-section title="社区公告" type="line">
			<uni-card v-for="(item,index) in announcementArr" :key="index" :title="item.title" extra="点击阅读" @click="toDetail(item)">
				<text class="content">{{item.content}}</text>
			</uni-card>
		</uni-section>
	</view>
</template>

<script>
	import * as utils from '@/utils/utils.js'
	export default {
		data() {
			return {
				announcementArr: []
			}
		},
		mounted() {
			this.getAnnouncementArr()
		},
		methods: {
			getAnnouncementArr(){
				this.$api.getAllAnnouncement({}).then(res=>{
					console.log('res',res);
					if(res.statusCode==200 &&res.data.state==200 ){
						this.announcementArr = res.data.data
					}else{
						utils.showToast('网络错误')
					}
				}).catch(error=>{
					utils.showError('网络请求错误')
				})
			},
			toDetail(item){
				let url = '/pages/announcement/announcement'
				url = `${url}?id=${item.id}`;
				uni.navigateTo({
					url:url,
				})
			}
		}
	}
</script>

<style lang="scss">
	.content {
		white-space: normal;
		display: -webkit-box;
		-webkit-box-orient: vertical;
		overflow: hidden;
		-webkit-line-clamp: 2;
	}
</style>