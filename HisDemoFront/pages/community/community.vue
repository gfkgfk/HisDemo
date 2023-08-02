<template>
	<view>
		<uni-section title="社区公告" type="line">
			<uni-card v-for="(item,index) in announcementArr" :key="index" :title="item.title" extra="点击阅读">
				<text class="content">item.announcement</text>
			</uni-card>
		</uni-section>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				announcementArr: []
			}
		},
		mounted() {},
		methods: {}
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