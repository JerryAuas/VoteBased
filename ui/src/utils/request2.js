import axios from 'axios'
// 签名 axios 实例
const signService = axios.create({
  baseURL: 'http://101.35.83.26:8888', // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

export default signService