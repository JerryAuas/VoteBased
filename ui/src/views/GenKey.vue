<template>
	<div style="padding: 10px; background-color: #f0f2f5;">
		<el-row>
			<el-col :span="12">
				<el-card shadow="never" style="height: 600px;">
					<strong type="danger">私钥: </strong>
					<el-input type="textarea" :rows="25" disabled v-model="privateKey"></el-input>
				</el-card>
			</el-col>
			<el-col :span="12">
				<el-card shadow="never" style="height: 500px;">
					<strong>公钥: </strong>
					<el-input type="textarea" v-model="publicKey" :rows="20" disabled></el-input>
				</el-card>
				<el-card shadow="never" style="height: 100px;">
					<el-tag type="warning">{{publicKeyIndex}}</el-tag>
					<el-button style="margin-left: 10px;" size="mini" class="item" type="success" @click="test()">生成公私钥</el-button>
					
				</el-card>
			</el-col>
		</el-row>
		
	</div>
</template>

<script>
	import { joinRing,getPriK,getPubK } from '@/api/key'
	import { getVoteByUid,listCandidate,updateS,updateI } from "@/api/system/vProject";
	import Cookies from "js-cookie";
	export default {
		name: 'GenKey',
		data: function(){
			return {
				publicKey: '',
				privateKey: '',
				publicKeyList: '',
				publicKeyIndex: ''
			}
		},
		mounted:function(){
			let indee = Cookies.get("avatar")
			if(indee==="9999") {
				console.log('222')
			} else {
				getPubK(indee).then(response=>{
					this.publicKey = response.data.result.result
				})
				getPriK(indee).then(response=>{
					this.privateKey = response.data.result.result
				})
			}
		},
		methods: {
			test: function(){
				joinRing().then(response=>{
					console.log('11111111111111111')
					
					this.publicKey = response.data.result.public_key
					this.privateKey = response.data.result.private_key
					this.publicKeyIndex = response.data.result.result
					Cookies.set("avatar",this.publicKeyIndex)
					this.updateIIII()
				})
			},
			
			updateIIII: function(){
				updateI({
					voterId: Cookies.get("userId"),
					projectId: this.publicKeyIndex
				}).then(response=>{})
			}
		}
	}
</script>

<style>
</style>
