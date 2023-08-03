<template>
	<view class="all">
		<view class="title">
			{{title}}
		</view>
		<view class="time">
			发布时间：{{createTime}}
		</view>
		<view class="content">
			{{content}}
		</view>
	</view>
</template>

<script>
	import * as utils from '@/utils/utils.js'
	export default {
		data() {
			return {
				announcementId:-1,
				title:'XXXXX',
				createTime:'XXXXX',
				content:'XXXXXXXXXXXXXXXXXXXXXXX'
			}
		},
		onLoad(option) {
			if(option.id){
				this.announcementId=option.id
			}
			
			if(this.announcementId>0){
				this.getAnnouncementById()
			}
		},
		methods: {
			getAnnouncementById(){
				if(this.announcementId<=0){
					return
				}
				this.$api.getAnnouncementById({id:this.announcementId}).then(res=>{
					console.log('res',res);
					if(res.statusCode==200 &&res.data.state==200 ){
						this.title = res.data.data.title
						this.content = res.data.data.content
						this.createTime=utils.dateFormat("yyyy-MM-dd hh:mm:ss",res.data.data.createTime)
					}else{
						utils.showToast('网络错误')
					}
				}).catch(error=>{
					utils.showError('网络请求错误')
					console.log(error);
				})
			}
		}
	}
</script>

<style lang="scss">
	.all{
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.title{
		padding: 10rpx;
		font-weight: 600;
		font-size: 40rpx;
	}
	.time{
		text-align: right;
		padding: 10rpx;
		padding-right: 100rpx;
		width: 100%;
		color: gray;
		font-size: $uni-font-size-sm;
	}
	
	.content{
		font-size: 30rpx;
		padding: 5rpx;
		font-weight: 400;
	}

</style>
