package com.skarmali.sk_nft_marketplace.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class NFT_Token extends Contract {
    public static final String BINARY = "608060405234801561000f575f5ffd5b5060405161168d38038061168d83398101604081905261002e9161017c565b3382825f61003c8382610265565b5060016100498282610265565b5050506001600160a01b03811661007957604051631e4fbdf760e01b81525f600482015260240160405180910390fd5b6100828161008e565b50505f6008555061031f565b600780546001600160a01b038381166001600160a01b0319831681179093556040519116919082907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0905f90a35050565b634e487b7160e01b5f52604160045260245ffd5b5f82601f830112610102575f5ffd5b81516001600160401b0381111561011b5761011b6100df565b604051601f8201601f19908116603f011681016001600160401b0381118282101715610149576101496100df565b604052818152838201602001851015610160575f5ffd5b8160208501602083015e5f918101602001919091529392505050565b5f5f6040838503121561018d575f5ffd5b82516001600160401b038111156101a2575f5ffd5b6101ae858286016100f3565b602085015190935090506001600160401b038111156101cb575f5ffd5b6101d7858286016100f3565b9150509250929050565b600181811c908216806101f557607f821691505b60208210810361021357634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561026057805f5260205f20601f840160051c8101602085101561023e5750805b601f840160051c820191505b8181101561025d575f815560010161024a565b50505b505050565b81516001600160401b0381111561027e5761027e6100df565b6102928161028c84546101e1565b84610219565b6020601f8211600181146102c4575f83156102ad5750848201515b5f19600385901b1c1916600184901b17845561025d565b5f84815260208120601f198516915b828110156102f357878501518255602094850194600190920191016102d3565b508482101561031057868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b6113618061032c5f395ff3fe608060405234801561000f575f5ffd5b5060043610610111575f3560e01c80638da5cb5b1161009e578063c87b56dd1161006e578063c87b56dd14610233578063d082e38114610246578063da2ed03e1461024f578063e985e9c514610262578063f2fde38b14610275575f5ffd5b80638da5cb5b146101f457806395d89b4114610205578063a22cb4651461020d578063b88d4fde14610220575f5ffd5b806323b872dd116100e457806323b872dd1461019257806342842e0e146101a55780636352211e146101b857806370a08231146101cb578063715018a6146101ec575f5ffd5b806301ffc9a71461011557806306fdde031461013d578063081812fc14610152578063095ea7b31461017d575b5f5ffd5b610128610123366004610e9a565b610288565b60405190151581526020015b60405180910390f35b6101456102b2565b6040516101349190610ee3565b610165610160366004610ef5565b610341565b6040516001600160a01b039091168152602001610134565b61019061018b366004610f22565b610368565b005b6101906101a0366004610f4a565b610377565b6101906101b3366004610f4a565b610405565b6101656101c6366004610ef5565b610424565b6101de6101d9366004610f84565b61042e565b604051908152602001610134565b610190610473565b6007546001600160a01b0316610165565b610145610486565b61019061021b366004610f9d565b610495565b61019061022e366004611061565b6104a0565b610145610241366004610ef5565b6104b8565b6101de60085481565b6101de61025d3660046110d8565b6105c3565b61012861027036600461111d565b610600565b610190610283366004610f84565b61062d565b5f6001600160e01b03198216632483248360e11b14806102ac57506102ac8261066a565b92915050565b60605f80546102c09061114e565b80601f01602080910402602001604051908101604052809291908181526020018280546102ec9061114e565b80156103375780601f1061030e57610100808354040283529160200191610337565b820191905f5260205f20905b81548152906001019060200180831161031a57829003601f168201915b5050505050905090565b5f61034b826106b9565b505f828152600460205260409020546001600160a01b03166102ac565b6103738282336106f1565b5050565b6001600160a01b0382166103a557604051633250574960e11b81525f60048201526024015b60405180910390fd5b5f6103b18383336106fe565b9050836001600160a01b0316816001600160a01b0316146103ff576040516364283d7b60e01b81526001600160a01b038086166004830152602482018490528216604482015260640161039c565b50505050565b61041f83838360405180602001604052805f8152506104a0565b505050565b5f6102ac826106b9565b5f6001600160a01b038216610458576040516322718ad960e21b81525f600482015260240161039c565b506001600160a01b03165f9081526003602052604090205490565b61047b6107f0565b6104845f61081d565b565b6060600180546102c09061114e565b61037333838361086e565b6104ab848484610377565b6103ff338585858561090c565b60606104c3826106b9565b505f82815260066020526040812080546104dc9061114e565b80601f01602080910402602001604051908101604052809291908181526020018280546105089061114e565b80156105535780601f1061052a57610100808354040283529160200191610553565b820191905f5260205f20905b81548152906001019060200180831161053657829003601f168201915b505050505090505f61056f60408051602081019091525f815290565b905080515f03610580575092915050565b8151156105b257808260405160200161059a92919061119d565b60405160208183030381529060405292505050919050565b6105bb84610a34565b949350505050565b5f6105cc6107f0565b6008546105d93382610aa5565b6105e38184610b06565b60088054905f6105f2836111b1565b90915550909150505b919050565b6001600160a01b039182165f90815260056020908152604080832093909416825291909152205460ff1690565b6106356107f0565b6001600160a01b03811661065e57604051631e4fbdf760e01b81525f600482015260240161039c565b6106678161081d565b50565b5f6001600160e01b031982166380ac58cd60e01b148061069a57506001600160e01b03198216635b5e139f60e01b145b806102ac57506301ffc9a760e01b6001600160e01b03198316146102ac565b5f818152600260205260408120546001600160a01b0316806102ac57604051637e27328960e01b81526004810184905260240161039c565b61041f8383836001610b55565b5f828152600260205260408120546001600160a01b039081169083161561072a5761072a818486610c59565b6001600160a01b03811615610764576107455f855f5f610b55565b6001600160a01b0381165f90815260036020526040902080545f190190555b6001600160a01b03851615610792576001600160a01b0385165f908152600360205260409020805460010190555b5f8481526002602052604080822080546001600160a01b0319166001600160a01b0389811691821790925591518793918516917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef91a4949350505050565b6007546001600160a01b031633146104845760405163118cdaa760e01b815233600482015260240161039c565b600780546001600160a01b038381166001600160a01b0319831681179093556040519116919082907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0905f90a35050565b6001600160a01b0382166108a057604051630b61174360e31b81526001600160a01b038316600482015260240161039c565b6001600160a01b038381165f81815260056020908152604080832094871680845294825291829020805460ff191686151590811790915591519182527f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a3505050565b6001600160a01b0383163b15610a2d57604051630a85bd0160e11b81526001600160a01b0384169063150b7a029061094e9088908890879087906004016111d5565b6020604051808303815f875af1925050508015610988575060408051601f3d908101601f1916820190925261098591810190611211565b60015b6109ef573d8080156109b5576040519150601f19603f3d011682016040523d82523d5f602084013e6109ba565b606091505b5080515f036109e757604051633250574960e11b81526001600160a01b038516600482015260240161039c565b805181602001fd5b6001600160e01b03198116630a85bd0160e11b14610a2b57604051633250574960e11b81526001600160a01b038516600482015260240161039c565b505b5050505050565b6060610a3f826106b9565b505f610a5560408051602081019091525f815290565b90505f815111610a735760405180602001604052805f815250610a9e565b80610a7d84610cbd565b604051602001610a8e92919061119d565b6040516020818303038152906040525b9392505050565b6001600160a01b038216610ace57604051633250574960e11b81525f600482015260240161039c565b5f610ada83835f6106fe565b90506001600160a01b0381161561041f576040516339e3563760e11b81525f600482015260240161039c565b5f828152600660205260409020610b1d8282611270565b506040518281527ff8e1a15aba9398e019f0b49df1a4fde98ee17ae345cb5f6b5e2c27f5033e8ce79060200160405180910390a15050565b8080610b6957506001600160a01b03821615155b15610c2a575f610b78846106b9565b90506001600160a01b03831615801590610ba45750826001600160a01b0316816001600160a01b031614155b8015610bb75750610bb58184610600565b155b15610be05760405163a9fbf51f60e01b81526001600160a01b038416600482015260240161039c565b8115610c285783856001600160a01b0316826001600160a01b03167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45b505b50505f90815260046020526040902080546001600160a01b0319166001600160a01b0392909216919091179055565b610c64838383610d4d565b61041f576001600160a01b038316610c9257604051637e27328960e01b81526004810182905260240161039c565b60405163177e802f60e01b81526001600160a01b03831660048201526024810182905260440161039c565b60605f610cc983610dae565b60010190505f8167ffffffffffffffff811115610ce857610ce8610fd6565b6040519080825280601f01601f191660200182016040528015610d12576020820181803683370190505b5090508181016020015b5f19016f181899199a1a9b1b9c1cb0b131b232b360811b600a86061a8153600a8504945084610d1c57509392505050565b5f6001600160a01b038316158015906105bb5750826001600160a01b0316846001600160a01b03161480610d865750610d868484610600565b806105bb5750505f908152600460205260409020546001600160a01b03908116911614919050565b5f8072184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b8310610dec5772184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b830492506040015b6d04ee2d6d415b85acef81000000008310610e18576d04ee2d6d415b85acef8100000000830492506020015b662386f26fc100008310610e3657662386f26fc10000830492506010015b6305f5e1008310610e4e576305f5e100830492506008015b6127108310610e6257612710830492506004015b60648310610e74576064830492506002015b600a83106102ac5760010192915050565b6001600160e01b031981168114610667575f5ffd5b5f60208284031215610eaa575f5ffd5b8135610a9e81610e85565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b602081525f610a9e6020830184610eb5565b5f60208284031215610f05575f5ffd5b5035919050565b80356001600160a01b03811681146105fb575f5ffd5b5f5f60408385031215610f33575f5ffd5b610f3c83610f0c565b946020939093013593505050565b5f5f5f60608486031215610f5c575f5ffd5b610f6584610f0c565b9250610f7360208501610f0c565b929592945050506040919091013590565b5f60208284031215610f94575f5ffd5b610a9e82610f0c565b5f5f60408385031215610fae575f5ffd5b610fb783610f0c565b915060208301358015158114610fcb575f5ffd5b809150509250929050565b634e487b7160e01b5f52604160045260245ffd5b5f5f67ffffffffffffffff84111561100457611004610fd6565b50604051601f19601f85018116603f0116810181811067ffffffffffffffff8211171561103357611033610fd6565b60405283815290508082840185101561104a575f5ffd5b838360208301375f60208583010152509392505050565b5f5f5f5f60808587031215611074575f5ffd5b61107d85610f0c565b935061108b60208601610f0c565b925060408501359150606085013567ffffffffffffffff8111156110ad575f5ffd5b8501601f810187136110bd575f5ffd5b6110cc87823560208401610fea565b91505092959194509250565b5f602082840312156110e8575f5ffd5b813567ffffffffffffffff8111156110fe575f5ffd5b8201601f8101841361110e575f5ffd5b6105bb84823560208401610fea565b5f5f6040838503121561112e575f5ffd5b61113783610f0c565b915061114560208401610f0c565b90509250929050565b600181811c9082168061116257607f821691505b60208210810361118057634e487b7160e01b5f52602260045260245ffd5b50919050565b5f81518060208401855e5f93019283525090919050565b5f6105bb6111ab8386611186565b84611186565b5f600182016111ce57634e487b7160e01b5f52601160045260245ffd5b5060010190565b6001600160a01b03858116825284166020820152604081018390526080606082018190525f9061120790830184610eb5565b9695505050505050565b5f60208284031215611221575f5ffd5b8151610a9e81610e85565b601f82111561041f57805f5260205f20601f840160051c810160208510156112515750805b601f840160051c820191505b81811015610a2d575f815560010161125d565b815167ffffffffffffffff81111561128a5761128a610fd6565b61129e81611298845461114e565b8461122c565b6020601f8211600181146112d0575f83156112b95750848201515b5f19600385901b1c1916600184901b178455610a2d565b5f84815260208120601f198516915b828110156112ff57878501518255602094850194600190920191016112df565b508482101561131c57868401515f19600387901b60f8161c191681555b50505050600190811b0190555056fea2646970667358221220d65fd348a74ebc0c67bc4cf7337c0e1283eb68e90274c647a0109c89f683103a64736f6c634300081c0033";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_CREATENFT = "createNFT";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOKENCOUNTER = "tokenCounter";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event BATCHMETADATAUPDATE_EVENT = new Event("BatchMetadataUpdate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event METADATAUPDATE_EVENT = new Event("MetadataUpdate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected NFT_Token(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NFT_Token(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NFT_Token(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NFT_Token(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<BatchMetadataUpdateEventResponse> getBatchMetadataUpdateEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(BATCHMETADATAUPDATE_EVENT, transactionReceipt);
        ArrayList<BatchMetadataUpdateEventResponse> responses = new ArrayList<BatchMetadataUpdateEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            BatchMetadataUpdateEventResponse typedResponse = new BatchMetadataUpdateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._fromTokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._toTokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BatchMetadataUpdateEventResponse> batchMetadataUpdateEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BatchMetadataUpdateEventResponse>() {
            @Override
            public BatchMetadataUpdateEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(BATCHMETADATAUPDATE_EVENT, log);
                BatchMetadataUpdateEventResponse typedResponse = new BatchMetadataUpdateEventResponse();
                typedResponse.log = log;
                typedResponse._fromTokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._toTokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BatchMetadataUpdateEventResponse> batchMetadataUpdateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BATCHMETADATAUPDATE_EVENT));
        return batchMetadataUpdateEventFlowable(filter);
    }

    public List<MetadataUpdateEventResponse> getMetadataUpdateEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(METADATAUPDATE_EVENT, transactionReceipt);
        ArrayList<MetadataUpdateEventResponse> responses = new ArrayList<MetadataUpdateEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MetadataUpdateEventResponse typedResponse = new MetadataUpdateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MetadataUpdateEventResponse> metadataUpdateEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MetadataUpdateEventResponse>() {
            @Override
            public MetadataUpdateEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(METADATAUPDATE_EVENT, log);
                MetadataUpdateEventResponse typedResponse = new MetadataUpdateEventResponse();
                typedResponse.log = log;
                typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MetadataUpdateEventResponse> metadataUpdateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(METADATAUPDATE_EVENT));
        return metadataUpdateEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new Address(160, to),
                new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Address(160, owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> createNFT(String tokenURI) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATENFT, 
                Arrays.<Type>asList(new Utf8String(tokenURI)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new Address(160, owner),
                new Address(160, operator)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to),
                new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to),
                new Uint256(tokenId),
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new Address(160, operator),
                new Bool(approved)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> tokenCounter() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENCOUNTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new Uint256(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new Address(160, from),
                new Address(160, to),
                new Uint256(tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(160, newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static NFT_Token load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFT_Token(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NFT_Token load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFT_Token(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NFT_Token load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NFT_Token(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NFT_Token load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NFT_Token(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NFT_Token> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name),
                new Utf8String(symbol)));
        return deployRemoteCall(NFT_Token.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<NFT_Token> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name),
                new Utf8String(symbol)));
        return deployRemoteCall(NFT_Token.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NFT_Token> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name),
                new Utf8String(symbol)));
        return deployRemoteCall(NFT_Token.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NFT_Token> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Utf8String(name),
                new Utf8String(symbol)));
        return deployRemoteCall(NFT_Token.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class BatchMetadataUpdateEventResponse extends BaseEventResponse {
        public BigInteger _fromTokenId;

        public BigInteger _toTokenId;
    }

    public static class MetadataUpdateEventResponse extends BaseEventResponse {
        public BigInteger _tokenId;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }
}
