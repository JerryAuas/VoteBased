<template>
	<div class="app-container">
		<el-row :gutter="10" class="mb8">
		  <el-col :span="1.5">
		    <el-button
		      type="primary"
		      plain
		      icon="el-icon-plus"
		      size="mini"
		      @click="addProjectOpen=true"
		    >新增</el-button>
		  </el-col>
		  <el-col :span="1.5">
		    <el-button
		      type="danger"
		      plain
		      icon="el-icon-refresh"
		      size="mini"
		      @click="handleRefreshCache"
		    >刷新缓存</el-button>
		  </el-col>
		  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
		</el-row>
		
		<el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
		  <el-table-column type="selection" width="55" align="center" />
		  <el-table-column label="项目编号" align="center" prop="vid" />
		  <el-table-column label="项目名称" align="center" prop="vname" :show-overflow-tooltip="true" />
		  <el-table-column label="项目描述" align="center" prop="vdesc" :show-overflow-tooltip="true" />
		  <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
		    <template slot-scope="scope">
		      <el-button
		        size="mini"
		        type="text"
		        icon="el-icon-edit"
		        @click="addVoter(scope.row)"
		      >添加投票者</el-button>
			  <el-button
			    size="mini"
			    type="text"
			    icon="el-icon-edit"
			    @click="addCandidate(scope.row)"
			  >添加候选人</el-button>
<!-- 		      <el-button
		        size="mini"
		        type="text"
		        icon="el-icon-delete"
		        @click="handleDelete(scope.row)"
		      >删除</el-button> -->
		    </template>
		  </el-table-column>
		</el-table>
		
		
		<!-- 添加投票者 -->
		<el-dialog title="添加与查看投票者" :visible.sync="addVoterOpen" width="500px" append-to-body>
		  <el-row :gutter="10" class="mb8">
		    <el-col :span="1.5">
		      <el-select size="mini" v-model="selectedUser" placeholder="请选择">
		          <el-option
		            v-for="user in userInfoList"
		            :key="user.userId"
		            :label="user.nickName"
		            :value="user.userId">
		            <span style="float: left">{{ user.nickName }}</span>
		            <span style="float: right; color: #8492a6; font-size: 13px">{{ user.userId }}</span>
		          </el-option>
		      </el-select>
		    </el-col>
			<el-col :span="1.5">
			  <el-button
			    type="primary"
			    plain
			    icon="el-icon-plus"
			    size="mini"
			    @click="handleAddVoter"
			  >新增</el-button>
			</el-col>
		    <el-col :span="1.5">
		      <el-button
		        type="danger"
		        plain
		        icon="el-icon-refresh"
		        size="mini"
		        :disabled="multiple"
		        @click="handleRefVoter"
		      >刷新</el-button>
		    </el-col>
		  </el-row>
		  <el-table :data="voterList" v-loading="voterLoading">
		    <el-table-column type="selection" width="55" align="center" />
		    <el-table-column label="用户编号" align="center" prop="userId" />
		    <el-table-column label="姓名" align="center" prop="nickName" :show-overflow-tooltip="true" />
		    <el-table-column label="邮箱" align="center" prop="email" :show-overflow-tooltip="true" />
		  </el-table>
		  <div slot="footer" class="dialog-footer">
		    <el-button type="primary" @click="addVoterOpen = false">确 定</el-button>
		    <el-button @click="addVoterOpen = false">取 消</el-button>
		  </div>
		</el-dialog>
		
		<!-- 添加候选人 -->
		<el-dialog title="添加与查看候选人" :visible.sync="addCandidateOpen" width="500px" append-to-body>
		  <el-row :gutter="10" class="mb8">
		    <el-col :span="1.5">
		      <el-select size="mini" v-model="selectedUser" placeholder="请选择">
		          <el-option
		            v-for="user in userInfoList"
		            :key="user.userId"
		            :label="user.nickName"
		            :value="user.userId">
		            <span style="float: left">{{ user.nickName }}</span>
		            <span style="float: right; color: #8492a6; font-size: 13px">{{ user.userId }}</span>
		          </el-option>
		      </el-select>
		    </el-col>
			<el-col :span="1.5">
			  <el-button
			    type="primary"
			    plain
			    icon="el-icon-plus"
			    size="mini"
			    @click="handleAddCandidate"
			  >新增</el-button>
			</el-col>
		    <el-col :span="1.5">
		      <el-button
		        type="danger"
		        plain
		        icon="el-icon-refresh"
		        size="mini"
		        :disabled="multiple"
		        @click="handleRefCandidate"
		      >刷新</el-button>
		    </el-col>
		  </el-row>
		  <el-table :data="candidateList" v-loading="candidateLoading">
		    <el-table-column type="selection" width="55" align="center" />
		    <el-table-column label="用户编号" align="center" prop="userId" />
		    <el-table-column label="姓名" align="center" prop="nickName" :show-overflow-tooltip="true" />
		    <el-table-column label="邮箱" align="center" prop="email" :show-overflow-tooltip="true" />
		  </el-table>
		  <div slot="footer" class="dialog-footer">
		    <el-button type="primary" @click="addCandidateOpen = false">确 定</el-button>
		    <el-button @click="addCandidateOpen = false">取 消</el-button>
		  </div>
		</el-dialog>
		
		<!-- 新增投票项目 -->
		<el-dialog title="新增投票项目" :visible.sync="addProjectOpen" width="500px" append-to-body>
			<el-form>
				<el-form-item label="项目编号">
					<el-input v-model="projectId"></el-input>
				</el-form-item>
				<el-form-item label="项目名称">
					<el-input v-model="projectName"></el-input>
				</el-form-item>
				<el-form-item label="项目介绍">
					<el-input type="textarea" v-model="projectDesc"></el-input>
				</el-form-item>
				<el-form-item>
				    <el-button type="primary" @click="addProject()">立即创建</el-button>
				    <el-button @click="addProjectOpen=false">取消</el-button>
				  </el-form-item>
			</el-form>
		</el-dialog>
	</div>
</template>

<script>
	import { listProject,listVoter,listUser,addVoterJs,listCandidate,addCandidateJs,addProjectJs } from "@/api/system/vProject";
	export default {
		name: "VoteProject",
		data(){
			return {
				loading: false,
				voterLoading: false,
				candidateLoading: false,
				// 字典表格数据
				projectList: [],  //{vid,vname,vdesc}
				voterList:[], //{userId,nickName,email,phonenumber,sex,avatar}
				candidateList:[], //{userId,nickName,email,phonenumber,sex,avatar}
				userInfoList:[], //{userId,nickName}
				selectedUser:'',
				currentProjectId: '',
				
				projectId: '',
				projectName: '',
				projectDesc: '',
				
				
				addVoterOpen: false,
				addCandidateOpen: false,
				addProjectOpen: false
			}
		},
		mounted:function(){
			this.handleRefreshCache()
		},
		methods: {
			// 刷新项目表格
			handleRefreshCache: function(){
				this.loading = true
				listProject().then(response=>{
					this.projectList = response
					this.loading = false
				})
			},
			addVoter: function(row){
				//添加投票者
				this.currentProjectId = row.vid
				this.addVoterOpen = true
				listUser().then(response=>{
					this.userInfoList = response
				})
				listVoter(row.vid).then(response=>{
					this.voterList = response
				})
			},
			handleAddVoter: function(){
				// 插入投票者到数据库
				var insertData = {
					voterId: this.selectedUser,
					projectId: this.currentProjectId
				}
				addVoterJs(insertData).then(response=>{
					if(response>0) {
						this.$notify({
						          title: '成功',
						          message: '成功添加投票者!',
						          type: 'success'
						        });
						this.handleRefVoter()
					}
				})
			},
			handleRefVoter: function(){
				// 刷新投票者
				this.voterLoading = true
				listVoter(this.currentProjectId).then(response=>{
					this.voterList = response
					this.voterLoading = false
				})
			},
			
			
			addCandidate: function(row){
				//添加候选人
				this.currentProjectId = row.vid
				this.addCandidateOpen = true
				listUser().then(response=>{
					this.userInfoList = response
				})
				listCandidate(row.vid).then(response=>{
					this.candidateList = response
				})
			},
			handleAddCandidate: function(){
				// 插入投票者到数据库
				var insertData = {
					candidateId: this.selectedUser,
					projectId: this.currentProjectId
				}
				addCandidateJs(insertData).then(response=>{
					if(response>0) {
						this.$notify({
						          title: '成功',
						          message: '成功添加候选人!',
						          type: 'success'
						        });
						this.handleRefCandidate()
					}
				})
			},
			handleRefCandidate: function(){
				// 刷新投票者
				this.candidateLoading = true
				listCandidate(this.currentProjectId).then(response=>{
					this.candidateList = response
					this.candidateLoading = false
				})
			},
			
			// 新增项目
			addProject: function(){
				var insertData = {
					vid: this.projectId,
					vname: this.projectName,
					vdesc: this.projectDesc
				}
				addProjectJs(insertData).then(response=>{
					if(response>0) {
						this.handleRefreshCache()
						this.projectId = ''
						this.projectName = ''
						this.projectDesc = ''
						this.addProjectOpen = false
						this.$notify({
						          title: '成功',
						          message: '成功添加投票项目!',
						          type: 'success'
						        });
					}
				})
			}
		}
	}
</script>

<style>
</style>
