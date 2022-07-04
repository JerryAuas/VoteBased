<template>
	<div class="mixin-components-container">
		<!-- 待投票项目 -->
		<el-row :gutter="20">
		  <el-col :span="6" v-for="(vote,index) in voteList" v-bind:class="{colTopActive: index>3}">
		    <el-card style="width: 300px;height: 200px;" shadow="always">
		      <div slot="header" class="clearfix">
		        <strong>{{vote.vname}}</strong>
		      </div>
			  <div>
				  <el-tag>描述</el-tag><el-input type="textarea" disabled v-model="vote.vdesc"></el-input>
			  </div>
		      <div style="text-align: center; margin-top: 10px;" class="bottom clearfix">
		    	  <el-button v-if="vote.flag1 === 0" @click="toVote(vote.vid)" type="primary" size="mini">
					  去投票
				  </el-button>
				  <el-button disabled v-if="vote.flag1 === 1" @click="" type="primary" size="mini">
				  	  已投票
				  </el-button>
		      </div>
		    </el-card>
		  </el-col>
		</el-row>
		

		<el-dialog title="投票与上传" :visible.sync='dialogVisible' class="dialogStyle">
			<el-divider></el-divider>
			<p><strong>候选人详情：</strong></p>
						<el-row :gutter="20">
						  <el-col :span="12" v-for="(candidate,index) in candidateList" v-bind:class="{colTopActive: index>3}">
						    <el-card style="margin-top: 5px; background-color: #E4E7ED;" shadow="always">
						      <div slot="header" class="clearfix">
						        <strong>{{candidate.userId}}</strong>
						      </div>
							  <div>
								  <el-avatar style="width: 100px; height: 100px; margin-left: 100px;" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
								  <el-form>
									  <el-form-item label="姓名" prop="desc">
										  <span>{{candidate.nickName}}</span>
									  </el-form-item>
									  <el-form-item label="介绍" prop="desc">
										  <el-input type="textarea" disabled v-model="candidate.email" style="width: 250px;"></el-input>
									  </el-form-item>
								  </el-form>
							  </div>
						    </el-card>
						  </el-col>
						</el-row>
						<el-divider></el-divider>
					<el-form style="margin-top: 20px;">
						<!-- 环签名 -->
						<el-form-item label="请选择候选人">
							<el-select v-model="voteResult" placeholder="请选择">
							    <el-option
							      v-for="candidate in candidateList"
							      :key="candidate.userId"
							      :label="candidate.nickName"
							      :value="candidate.userId">
								  <span style="float: left">{{ candidate.nickName }}</span>
								  <span style="float: right; color: #8492a6; font-size: 13px">{{ candidate.userId }}</span>
							    </el-option>
							  </el-select>
						</el-form-item>
						<!-- <el-form-item>
							<el-row :gutter="20" style="text-align: center;">
								<el-col :span="12">
									<el-upload action="http://127.0.0.1:5000/secret_key">
										<el-button type="primary">个人私钥</el-button>
									</el-upload>
								</el-col>
								<el-col :span="12">
									<el-button type="primary" @click="getPkeyList">获取环公钥</el-button>
								</el-col>
							</el-row>
						</el-form-item> -->
						<!-- <el-form-item>
							<el-input placeholder="输入公钥索引" v-model="pkIndex"></el-input>
						</el-form-item> -->
						<el-form-item style="text-align: center;">
						    <el-button type="danger" @click="onSign" style="width: 100px;">环签名</el-button>
						</el-form-item>
						<el-form-item label="环签名结果:">
							<el-input disabled type="textarea" :autosize="{ minRows: 6, maxRows: 15}" v-model="signResult"></el-input>
						</el-form-item>
						<el-form-item style="text-align: center;">
						    <el-button type="danger" @click="voteChain" style="width: 100px;">投票</el-button>
						</el-form-item>
					</el-form>
		</el-dialog>
		<!-- 展示环公钥 -->
		<el-dialog
		  title="环公钥"
		  :visible.sync="singResultVisible"
		  width="50%">
		  <el-input disabled type="textarea" :autosize="{ minRows: 6, maxRows: 15}" v-model="publicKeyList"></el-input>
		  <span slot="footer" class="dialog-footer">
		    <el-button @click="singResultVisible = false">取 消</el-button>
		    <el-button type="primary" @click="singResultVisible = false">确 定</el-button>
		  </span>
		</el-dialog>
		<!-- 展示区块链 -->
		<el-dialog
		  title="投票结果"
		  :visible.sync="voteResultVisible"
		  width="50%">
		  <json-viewer :value="jsonData" copyable boxed></json-viewer>
		  <span slot="footer" class="dialog-footer">
		    <el-button @click="voteResultVisible = false">取 消</el-button>
		    <el-button type="primary" @click="voteResultVisible = false">确 定</el-button>
		  </span>
		</el-dialog>
	</div>
</template>

<script>
	import { signRing } from '@/api/key'
	import {myCon,defaultAccount} from '@/api/BlockChain'
	import { getVoteByUid,listCandidate,updateS } from "@/api/system/vProject";
	import Cookies from "js-cookie";
	export default {
		name: "ToVote",
		data() {
			return {
				voteList: [
					{vid:'v001', vname:'优秀学生投票', vdesc:'投票选择您心目中的优秀学生' },
					{vid:'v001', vname:'优秀教师投票', vdesc:'投票选择您心目中的优秀的教师' }
				],
				candidateList: [
					{cid:'c001', cname:'王一', cdesc:'请选择我'},
					{cid:'c002', cname:'李二', cdesc:'谢谢大家的支持！'},
					{cid:'c003', cname:'李三', cdesc:'谢谢大家的支持！'}
				],
				voteResult: '',
				publicKeyList: '',
				pkIndex: '',
				signResult: '',
				jsonData: null,
				dialogVisible: false,
				singResultVisible: false,
				voteResultVisible: false,
				currentVoteId: ''
			}
		},
		mounted:function(){
			this.getToVoteP()
		},
		methods:{
			toVote: function(vid) {
				this.currentVoteId = vid
				this.dialogVisible = true
				listCandidate(vid).then(response=>{
					this.candidateList = response
				})
			},
			// 获取需要投票的项目
			getToVoteP: function(){
				getVoteByUid(Cookies.get("userId")).then(response=>{
					this.voteList = response
				})
			},
			
			// 环签名
			onSign: function(){
				let indee = Cookies.get("avatar")
				if(indee==="9999") {
					this.$notify.error({
					          title: '错误',
					          message: '请前往生成密钥'
					        });
				} else {
					this.pkIndex = indee
				}
				signRing(this.pkIndex,this.voteResult.toString()).then(response=>{
					this.signResult = response.data.result.sig
				})
			},
			// 区块链
			voteChain: function(){
				// 当前投票项目 vid
				var vid = this.currentVoteId
				var vmsg = this.voteResult.toString()
				var vsig = window.btoa(this.signResult)
				console.log(vid,vmsg,vsig)
				myCon.methods.writeVotes(vid,vmsg,vsig).send({from: defaultAccount, gas: 75000000}).then(rsp=>{
					this.voteResultVisible = true
					this.jsonData = rsp
					updateS({
						voterId: Cookies.get("userId"),
						projectId: this.currentVoteId
					}).then(response=>{this.getToVoteP()})
					this.$notify({
					          title: '成功',
					          message: '投票成功',
					          type: 'success'
					        });
				})
			}
			
		}
	}
</script>

<style>
	.mixin-components-container {
	  background-color: #f0f2f5;
	  padding: 30px;
	  min-height: calc(100vh - 84px);
	}
</style>
