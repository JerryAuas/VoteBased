import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 根据用户id获取投票项目
export function getVoteByUid(uid) {
  return request({
    url: '/test/user/myInfo?uid=' + parseStrEmpty(uid),
    method: 'get',
	headers: {
	      isToken: false
	    }
  })
}


// 获取投票项目列表
export function listProject(query) {
  return request({
    url: '/test/user/info',
    method: 'get',
	headers: {
	      isToken: false
	    },
    params: query
  })
}
//获取所有用户的简洁信息
export function listUser(query) {
  return request({
    url: '/test/user/allUser',
    method: 'get',
	headers: {
	      isToken: false
	    },
    params: query
  })
}
// 查询项目对应的投票者
export function listVoter(vid) {
  return request({
    url: '/test/user/myVoterInfo?vid=' + parseStrEmpty(vid),
    method: 'get',
	headers: {
	      isToken: false
	    }
  })
}
// 查询项目对应的候选人
export function listCandidate(vid) {
  return request({
    url: '/test/user/myCandidateInfo?vid=' + parseStrEmpty(vid),
    method: 'get',
	headers: {
	      isToken: false
	    }
  })
}

// 为项目添加投票者
export function addVoterJs(data) {
  return request({
    url: '/test/user/addVoter',
    method: 'post',
    data: data,
	headers: {
	      isToken: false
	    }
  })
}
// 为项目添加候选人
export function addCandidateJs(data) {
  return request({
    url: '/test/user/addCandidate',
    method: 'post',
    data: data,
	headers: {
	      isToken: false
	    }
  })
}
// 新增项目
export function addProjectJs(data) {
  return request({
    url: '/test/user/addProject',
    method: 'post',
    data: data,
	headers: {
	      isToken: false
	    }
  })
}

// 删除投票项目
export function delVote11(vid) {
  return request({
    url: '/test/user/delVote?vid=' + parseStrEmpty(vid),
    method: 'get',
	headers: {
	      isToken: false
	    }
  })
}

//    获取该投票项目下各个候选人得票数
export function getCount11(vid){
	return request({
	  url: '/test/user/getCount?vid=' + parseStrEmpty(vid),
	  method: 'get',
		headers: {
		      isToken: false
		    }
	})
}
//    上传区块链上投票信息至数据库
export function addVoteResult11(data){
	return request({
	  url: '/test/user/addVoteResult',
	  method: 'post',
	  data: data,
		headers: {
		      isToken: false
		    }
	})
}
// 根据投票项目id获取投票信息
export function getVotedInfo11(vid){
	return request({
	  url: '/test/user/getVotedInfo?vid=' + parseStrEmpty(vid),
	  method: 'get',
		headers: {
		      isToken: false
		    }
	})
}
// 更新状态
export function updateS(data){
	return request({
	  url: '/test/user/updateState',
	  method: 'post',
	  data: data,
		headers: {
		      isToken: false
		    }
	})
}

// 更新index
export function updateI(data){
	return request({
	  url: '/test/user/updateIndex',
	  method: 'post',
	  data: data,
		headers: {
		      isToken: false
		    }
	})
}



// 查询用户详细
export function getUser(userId) {
  return request({
    url: '/system/user/' + parseStrEmpty(userId),
    method: 'get'
  })
}

// 新增用户
export function addUser(data) {
  return request({
    url: '/system/user',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateUser(data) {
  return request({
    url: '/system/user',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delUser(userId) {
  return request({
    url: '/system/user/' + userId,
    method: 'delete'
  })
}

// 用户密码重置
export function resetUserPwd(userId, password) {
  const data = {
    userId,
    password
  }
  return request({
    url: '/system/user/resetPwd',
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changeUserStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: '/system/user/changeStatus',
    method: 'put',
    data: data
  })
}

// 查询用户个人信息
export function getUserProfile() {
  return request({
    url: '/system/user/profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updateUserProfile(data) {
  return request({
    url: '/system/user/profile',
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updateUserPwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/system/user/profile/updatePwd',
    method: 'put',
    params: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/system/user/profile/avatar',
    method: 'post',
    data: data
  })
}

// 查询授权角色
export function getAuthRole(userId) {
  return request({
    url: '/system/user/authRole/' + userId,
    method: 'get'
  })
}

// 保存授权角色
export function updateAuthRole(data) {
  return request({
    url: '/system/user/authRole',
    method: 'put',
    params: data
  })
}
