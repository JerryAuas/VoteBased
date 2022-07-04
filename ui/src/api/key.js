import signService from '@/utils/request2'


// 加入环
export function joinRing(){
	let formData = {
		id: 1,
		jsonrpc: "2.0",
		method: "join_ring",
		params: {
			ring_name: "test",
			}
		}
	return signService.request(
	{
		url: '/',
		method: 'post',
		data: formData
	})
}
// 签名
export function signRing(indexId,messageIn){
	let formData = {
		id: 1,
		jsonrpc: "2.0",
		method: "linkable_ring_sig",
		params: {
			ring_name: "test",
			id: indexId,
			message: messageIn,
			ring_size: "2"
			}
		}
	return signService.request(
	{
		url: '/',
		method: 'post',
		data: formData
	})
}

// 验证
export function verifyRing(myId,sigC,messageC){
	let formData = {
		id: myId,
		jsonrpc: "2.0",
		method: "linkable_ring_verify",
		params: {
			ring_name: "test",
			sig: sigC,
			message: messageC
			}
		}
	return signService.request(
	{
		url: '/',
		method: 'post',
		data: formData
	})
}

// 生成公钥私钥
export function generateKey(){
	let formData = {
		id: 1,
		jsonrpc: "2.0",
		method: "join_ring",
		params: {
			ring_name: "test",
			},
		}
	return signService.request(
	{
		url: '/',
		method: 'post',
		data: formData
	})
}
// 获取公钥
export function getPubK(ind){
	let formData = {
		id: 1,
		jsonrpc: "2.0",
		method: "get_public_key",
		params: {
			ring_name: "test",
			id: ind
			},
		}
	return signService.request(
	{
		url: '/',
		method: 'post',
		data: formData
	})
}

// 获取私钥
export function getPriK(ind){
	let formData = {
		id: 1,
		jsonrpc: "2.0",
		method: "get_private_key",
		params: {
			ring_name: "test",
			id: ind
			},
		}
	return signService.request(
	{
		url: '/',
		method: 'post',
		data: formData
	})
}