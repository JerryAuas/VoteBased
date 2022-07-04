import Web3 from 'web3'

let myWeb3 = window.web3
if (typeof myWeb3 !== 'undefined') {
	myWeb3 = new Web3(web3.currentProvider);
	
} else {
	// set the provider you want from Web3.providers
	myWeb3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:7545"));
}
export default myWeb3