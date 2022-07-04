import Web3 from 'web3'
import myWeb3 from '@/utils/newWeb3'


var abi = [
	{
		"constant": false,
		"inputs": [
			{
				"name": "vid",
				"type": "string"
			},
			{
				"name": "voteInfo",
				"type": "string"
			},
			{
				"name": "voteSig",
				"type": "string"
			}
		],
		"name": "writeVotes",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "vid",
				"type": "string"
			}
		],
		"name": "readVoteNum",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "vid",
				"type": "string"
			},
			{
				"name": "index",
				"type": "uint256"
			}
		],
		"name": "readVotes",
		"outputs": [
			{
				"components": [
					{
						"name": "vVote",
						"type": "string"
					},
					{
						"name": "vSig",
						"type": "string"
					}
				],
				"name": "",
				"type": "tuple"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	}
];

const address = '0x9DfC505f9E2cE80015fbBa436F83a7e6D72049cF';

const myCon = new myWeb3.eth.Contract(abi, address);
let defaultAccount = '0x6c88eE97AC5D2fF9e9c251ceADB26248fa046172';

export {
	myCon,
	defaultAccount
}