<template>
	<div class="app-container">
		<el-row :gutter="10" class="mb8">
		  <el-col :span="1.5">
		    <el-select size="mini" v-model="selectedProject" placeholder="请选择" @change="myLen()">
		        <el-option
		          v-for="project in projectInfoList"
		          :key="project.vid"
		          :label="project.vname"
		          :value="project.vid">
		          <span style="float: left">{{ project.vname }}</span>
		          <span style="float: right; color: #8492a6; font-size: 13px">{{ project.vid }}</span>
		        </el-option>
		    </el-select>
		  </el-col>
		  <el-col :span="1.5">
		    <el-button
		      type="primary"
		      plain
		      icon="el-icon-download"
		      size="mini"
		      @click="downloadData"
		    >获取</el-button>
		  </el-col>
		  <el-col :span="1.5">
		    <el-button
		      type="success"
		      plain
		      icon="el-icon-edit"
		      size="mini"
		      :disabled="single"
		      @click="verSign"
		    >验证</el-button>
		  </el-col>
		  <el-col :span="1.5">
		    <el-button
		      type="danger"
		      plain
		      icon="el-icon-plus"
		      size="mini"
		      :disabled="multiple"
		      @click="delD()"
		    >存储</el-button>
		  </el-col>
		  
		  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
		</el-row>
	  <el-form>
		  <el-form-item label="当前链上的数据:">
			<span>{{mylength}} 条</span>
		  </el-form-item>
	  </el-form>
	  <el-table :data="voteInfo">
	    <el-table-column type="selection" width="55" align="center" />
	    <el-table-column label="投票编号" align="center" prop="vid" />
	    <el-table-column label="得票人" align="center" prop="vVote" :show-overflow-tooltip="true" />
	    <el-table-column label="验证信息" align="center" prop="vFlag" :show-overflow-tooltip="true" />
	  </el-table>
	</div>
</template>

<script>
	import { listProject,addVoteResult11,delVote11} from "@/api/system/vProject";
	import {myCon,defaultAccount} from '@/api/BlockChain'
	import { verifyRing } from '@/api/key'
	export default {
		name: "DownloadVote",
		data(){
			return {
				projectInfoList:[],
				selectedProject: '',
				voteInfo:[],
				mylength:null
			}
		},
		methods:{
			getAllProjectInfo: function(){
				listProject().then(response=>{
					this.projectInfoList = response
				})
			},
			myLen: function(){
				myCon.methods.readVoteNum(this.selectedProject).call().then(response => {
					this.mylength = response
				})
			},
			downloadData: function(){
				this.voteInfo = []
				for (var i=0; i<this.mylength; i++) {
					myCon.methods.readVotes(this.selectedProject,i).call().then(response => {
						console.log(response)
						this.voteInfo.push({
							vid: this.selectedProject,
							vVote: response['vVote'], 
							vSig: window.atob(response['vSig']),
							vFlag: '未验证'
						})
					})
				}
			},
			verSign: function(){
				let nowI;
				let allState = []
				console.log(this.mylength)
				for(var i=0;i<this.mylength;i++){
					console.log(i)
					verifyRing(i, this.voteInfo[i].vSig, this.voteInfo[i].vVote).then(response=>{
						if(response.data.result.result){
							this.voteInfo[response.data.id].vFlag = '验证成功'
						}else{
							this.voteInfo[response.data.id].vFlag = '验证失败'
						}
					})
				}
			},
			// 删除与存储
			delD: function(){
				delVote11(this.selectedProject).then(response=>{
					this.saveD().then(response=>{})
				})
			},
			
			// 存储
			saveD: function(){
				let idx
				let tmpD = this.voteInfo
				for (idx in tmpD){
					console.log(idx)
					if(tmpD[idx].vFlag==='验证成功'){
						let laD = {
							vid: tmpD[idx].vid,
							cid: tmpD[idx].vVote
						}
						addVoteResult11(laD).then(response=>{})
					}
				}
				
			}
		},
		mounted:function(){
			this.getAllProjectInfo()
		}
	}
</script>

<style>
</style>
