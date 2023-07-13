<template>
	<view>
		<p>JQuery测试点击消失</p>
		<button @click="show">重置</button>
	</view>
</template>

<script>
	//#ifdef H5
	import $ from '@/static/js/jquery-3.7.0.min.js'
	//#endif  
	export default {
		data() {
			return {

			}
		},
		mounted() {
			$(document).ready(function() {
				$("p").click(function() {
					$(this).hide();
				});
			});
		},
		methods: {
			show() {
				$("p").show()
			}
		}
	}
</script>

<style>

</style>