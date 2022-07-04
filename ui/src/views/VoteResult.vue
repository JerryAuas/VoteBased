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
		      @click="getVoteCount"
		    >获取</el-button>
		  </el-col>
		  
		  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
		</el-row>
		
		<el-table :data="voteResultList">
		  <el-table-column type="selection" width="55" align="center" />
		  <el-table-column label="候选人ID" align="center" prop="userId" />
		  <el-table-column label="候选人" align="center" prop="nickName" :show-overflow-tooltip="true" />
		  <el-table-column label="得票数" align="center" prop="voteNum" :show-overflow-tooltip="true" />
		</el-table>
	</div>
</template>

<script>
	import { listProject,addVoteResult11,delVote11,getCount11} from "@/api/system/vProject";
	import {myCon,defaultAccount} from '@/api/BlockChain'
	import { uploadSign, verifyMessage,verifyRing } from '@/api/key'
	export default {
		name: 'VoteResult',
		data(){
			return {
				projectInfoList:[],
				voteResultList:[],
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
			// 获取得票数
			getVoteCount: function(){
				getCount11(this.selectedProject).then(response=>{
					this.voteResultList = response
				})
			}
		},
		mounted:function(){
			this.getAllProjectInfo()
		}
	}
</script>

<style>
</style>
