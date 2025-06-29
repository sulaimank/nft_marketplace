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
    public static final String BINARY = "60806040525f6009555f600a55348015610017575f5ffd5b50604051611e2d380380611e2d83398101604081905261003691610180565b3382825f6100448382610269565b5060016100518282610269565b5050506001600160a01b03811661008157604051631e4fbdf760e01b81525f600482015260240160405180910390fd5b61008a81610092565b505050610323565b600780546001600160a01b038381166001600160a01b0319831681179093556040519116919082907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0905f90a35050565b634e487b7160e01b5f52604160045260245ffd5b5f82601f830112610106575f5ffd5b81516001600160401b0381111561011f5761011f6100e3565b604051601f8201601f19908116603f011681016001600160401b038111828210171561014d5761014d6100e3565b604052818152838201602001851015610164575f5ffd5b8160208501602083015e5f918101602001919091529392505050565b5f5f60408385031215610191575f5ffd5b82516001600160401b038111156101a6575f5ffd5b6101b2858286016100f7565b602085015190935090506001600160401b038111156101cf575f5ffd5b6101db858286016100f7565b9150509250929050565b600181811c908216806101f957607f821691505b60208210810361021757634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561026457805f5260205f20601f840160051c810160208510156102425750805b601f840160051c820191505b81811015610261575f815560010161024e565b50505b505050565b81516001600160401b03811115610282576102826100e3565b6102968161029084546101e5565b8461021d565b6020601f8211600181146102c8575f83156102b15750848201515b5f19600385901b1c1916600184901b178455610261565b5f84815260208120601f198516915b828110156102f757878501518255602094850194600190920191016102d7565b508482101561031457868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b611afd806103305f395ff3fe608060405260043610610126575f3560e01c8063715018a6116100a8578063b88d4fde1161006d578063b88d4fde14610316578063c87b56dd14610335578063d6077fbf14610354578063e985e9c514610382578063f2fde38b146103a1578063f779a193146103c0575f5ffd5b8063715018a6146102935780638da5cb5b146102a757806395d89b41146102c4578063a22cb465146102d8578063ad05f1b4146102f7575f5ffd5b8063305a67a8116100ee578063305a67a8146101f657806342842e0e1461021557806351ed8288146102345780636352211e1461024757806370a0823114610266575f5ffd5b806301ffc9a71461012a57806306fdde031461015e578063081812fc1461017f578063095ea7b3146101b657806323b872dd146101d7575b5f5ffd5b348015610135575f5ffd5b506101496101443660046116f8565b6103df565b60405190151581526020015b60405180910390f35b348015610169575f5ffd5b50610172610409565b6040516101559190611741565b34801561018a575f5ffd5b5061019e610199366004611753565b610498565b6040516001600160a01b039091168152602001610155565b3480156101c1575f5ffd5b506101d56101d036600461177e565b6104bf565b005b3480156101e2575f5ffd5b506101d56101f13660046117a8565b6104ce565b348015610201575f5ffd5b506101d5610210366004611753565b61055c565b348015610220575f5ffd5b506101d561022f3660046117a8565b61061a565b6101d5610242366004611753565b610639565b348015610252575f5ffd5b5061019e610261366004611753565b610843565b348015610271575f5ffd5b506102856102803660046117e6565b61084d565b604051908152602001610155565b34801561029e575f5ffd5b506101d5610892565b3480156102b2575f5ffd5b506007546001600160a01b031661019e565b3480156102cf575f5ffd5b506101726108a5565b3480156102e3575f5ffd5b506101d56102f236600461180e565b6108b4565b348015610302575f5ffd5b506101d5610311366004611845565b6108bf565b348015610321575f5ffd5b506101d561033036600461188b565b610a6e565b348015610340575f5ffd5b5061017261034f366004611753565b610a86565b34801561035f575f5ffd5b5061028561036e366004611753565b5f9081526008602052604090206003015490565b34801561038d575f5ffd5b5061014961039c36600461196c565b610b91565b3480156103ac575f5ffd5b506101d56103bb3660046117e6565b610bbe565b3480156103cb575f5ffd5b506101d56103da366004611845565b610bfb565b5f6001600160e01b03198216632483248360e11b1480610403575061040382610ecb565b92915050565b60605f805461041790611998565b80601f016020809104026020016040519081016040528092919081815260200182805461044390611998565b801561048e5780601f106104655761010080835404028352916020019161048e565b820191905f5260205f20905b81548152906001019060200180831161047157829003601f168201915b5050505050905090565b5f6104a282610f1a565b505f828152600460205260409020546001600160a01b0316610403565b6104ca828233610f52565b5050565b6001600160a01b0382166104fc57604051633250574960e11b81525f60048201526024015b60405180910390fd5b5f610508838333610f5f565b9050836001600160a01b0316816001600160a01b031614610556576040516364283d7b60e01b81526001600160a01b03808616600483015260248201849052821660448201526064016104f3565b50505050565b5f818152600860205260409020546001600160a01b031633146105d65760405162461bcd60e51b815260206004820152602c60248201527f596f75206d757374206265207468652073656c6c657220746f2063616e63656c60448201526b20746865206c697374696e6760a01b60648201526084016104f3565b5f90815260086020526040812080546001600160a01b031990811682556001820183905560028201805490911690556003810191909155600401805460ff19169055565b61063483838360405180602001604052805f815250610a6e565b505050565b5f81815260086020908152604091829020825160a08101845281546001600160a01b03908116825260018301549382019390935260028201549092169282019290925260038201546060820181905260049092015460ff1615156080820152906106e55760405162461bcd60e51b815260206004820152601760248201527f4e4654206e6f74206c697374656420666f722073616c6500000000000000000060448201526064016104f3565b80606001513410156107305760405162461bcd60e51b8152602060048201526014602482015273125b9cdd59999a58da595b9d081c185e5b595b9d60621b60448201526064016104f3565b80515f83815260086020526040812080546001600160a01b031990811682556001820183905560028201805490911690556003810191909155600401805460ff1916905561077f813385611051565b60608201516040516001600160a01b0383169180156108fc02915f818181858888f193505050501580156107b5573d5f5f3e3d5ffd5b50604080518481523360208201526001600160a01b0383168183015290517ff076e74c3b31fba126074acbffcdd9d113b723bf30ad339f58dfe3002a8310df9181900360600190a1816060015134111561063457606082015133906108fc9061081e90346119e4565b6040518115909202915f818181858888f19350505050158015610556573d5f5f3e3d5ffd5b5f61040382610f1a565b5f6001600160a01b038216610877576040516322718ad960e21b81525f60048201526024016104f3565b506001600160a01b03165f9081526003602052604090205490565b61089a6110fe565b6108a35f61112b565b565b60606001805461041790611998565b6104ca33838361117c565b336108c983610843565b6001600160a01b03161461091f5760405162461bcd60e51b815260206004820152601f60248201527f596f75206d757374206f776e20746865204e465420746f206c6973742069740060448201526064016104f3565b5f811161096e5760405162461bcd60e51b815260206004820152601f60248201527f5072696365206d7573742062652067726561746572207468616e207a65726f0060448201526064016104f3565b6040805160a0810182523380825260208083018681526001600160a01b03888116858701908152606086018881526001608088018181525f8c8152600890975295899020975188549085166001600160a01b03199182161789559451908801559051600287018054919093169316929092179055516003840155516004909201805492151560ff199093169290921790915560095491517f18c93addaf4d0e5982a13cb88444441b90223974eaf9d6e898151cb4d96bf15392610a619290918791879187909485526001600160a01b03938416602086015260408501929092529091166060830152608082015260a00190565b60405180910390a1505050565b610a798484846104ce565b610556338585858561121a565b6060610a9182610f1a565b505f8281526006602052604081208054610aaa90611998565b80601f0160208091040260200160405190810160405280929190818152602001828054610ad690611998565b8015610b215780601f10610af857610100808354040283529160200191610b21565b820191905f5260205f20905b815481529060010190602001808311610b0457829003601f168201915b505050505090505f610b3d60408051602081019091525f815290565b905080515f03610b4e575092915050565b815115610b80578082604051602001610b68929190611a0e565b60405160208183030381529060405292505050919050565b610b8984611342565b949350505050565b6001600160a01b039182165f90815260056020908152604080832093909416825291909152205460ff1690565b610bc66110fe565b6001600160a01b038116610bef57604051631e4fbdf760e01b81525f60048201526024016104f3565b610bf88161112b565b50565b5f8111610c4a5760405162461bcd60e51b815260206004820152601f60248201527f5072696365206d7573742062652067726561746572207468616e207a65726f0060448201526064016104f3565b6040516331a9108f60e11b815260048101839052839033906001600160a01b03831690636352211e90602401602060405180830381865afa158015610c91573d5f5f3e3d5ffd5b505050506040513d601f19601f82011682018060405250810190610cb59190611a22565b6001600160a01b031614610d0b5760405162461bcd60e51b815260206004820152601f60248201527f596f75206d757374206f776e20746865204e465420746f2073656c6c2069740060448201526064016104f3565b60405163e985e9c560e01b81523360048201523060248201526001600160a01b0382169063e985e9c590604401602060405180830381865afa158015610d53573d5f5f3e3d5ffd5b505050506040513d601f19601f82011682018060405250810190610d779190611a3d565b610dcf5760405162461bcd60e51b8152602060048201526024808201527f4e4654206d75737420626520617070726f76656420666f72206d61726b6574706044820152636c61636560e01b60648201526084016104f3565b60098054905f610dde83611a58565b90915550506040805160a080820183523380835260208084018881526001600160a01b038a811686880181815260608089018c815260016080808c01828152600980545f90815260088c528f90209d518e54908a166001600160a01b0319918216178f559951938e0193909355945160028d018054919098169816979097179095555160038a015590516004909801805498151560ff19909916989098179097559054875190815292830152948101889052928301529181018490527f18c93addaf4d0e5982a13cb88444441b90223974eaf9d6e898151cb4d96bf153910160405180910390a150505050565b5f6001600160e01b031982166380ac58cd60e01b1480610efb57506001600160e01b03198216635b5e139f60e01b145b8061040357506301ffc9a760e01b6001600160e01b0319831614610403565b5f818152600260205260408120546001600160a01b03168061040357604051637e27328960e01b8152600481018490526024016104f3565b61063483838360016113b3565b5f828152600260205260408120546001600160a01b0390811690831615610f8b57610f8b8184866114b7565b6001600160a01b03811615610fc557610fa65f855f5f6113b3565b6001600160a01b0381165f90815260036020526040902080545f190190555b6001600160a01b03851615610ff3576001600160a01b0385165f908152600360205260409020805460010190555b5f8481526002602052604080822080546001600160a01b0319166001600160a01b0389811691821790925591518793918516917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef91a4949350505050565b6001600160a01b03821661107a57604051633250574960e11b81525f60048201526024016104f3565b5f61108683835f610f5f565b90506001600160a01b0381166110b257604051637e27328960e01b8152600481018390526024016104f3565b836001600160a01b0316816001600160a01b031614610556576040516364283d7b60e01b81526001600160a01b03808616600483015260248201849052821660448201526064016104f3565b6007546001600160a01b031633146108a35760405163118cdaa760e01b81523360048201526024016104f3565b600780546001600160a01b038381166001600160a01b0319831681179093556040519116919082907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e0905f90a35050565b6001600160a01b0382166111ae57604051630b61174360e31b81526001600160a01b03831660048201526024016104f3565b6001600160a01b038381165f81815260056020908152604080832094871680845294825291829020805460ff191686151590811790915591519182527f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a3505050565b6001600160a01b0383163b1561133b57604051630a85bd0160e11b81526001600160a01b0384169063150b7a029061125c908890889087908790600401611a70565b6020604051808303815f875af1925050508015611296575060408051601f3d908101601f1916820190925261129391810190611aac565b60015b6112fd573d8080156112c3576040519150601f19603f3d011682016040523d82523d5f602084013e6112c8565b606091505b5080515f036112f557604051633250574960e11b81526001600160a01b03851660048201526024016104f3565b805181602001fd5b6001600160e01b03198116630a85bd0160e11b1461133957604051633250574960e11b81526001600160a01b03851660048201526024016104f3565b505b5050505050565b606061134d82610f1a565b505f61136360408051602081019091525f815290565b90505f8151116113815760405180602001604052805f8152506113ac565b8061138b8461151b565b60405160200161139c929190611a0e565b6040516020818303038152906040525b9392505050565b80806113c757506001600160a01b03821615155b15611488575f6113d684610f1a565b90506001600160a01b038316158015906114025750826001600160a01b0316816001600160a01b031614155b801561141557506114138184610b91565b155b1561143e5760405163a9fbf51f60e01b81526001600160a01b03841660048201526024016104f3565b81156114865783856001600160a01b0316826001600160a01b03167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45b505b50505f90815260046020526040902080546001600160a01b0319166001600160a01b0392909216919091179055565b6114c28383836115ab565b610634576001600160a01b0383166114f057604051637e27328960e01b8152600481018290526024016104f3565b60405163177e802f60e01b81526001600160a01b0383166004820152602481018290526044016104f3565b60605f6115278361160c565b60010190505f8167ffffffffffffffff81111561154657611546611877565b6040519080825280601f01601f191660200182016040528015611570576020820181803683370190505b5090508181016020015b5f19016f181899199a1a9b1b9c1cb0b131b232b360811b600a86061a8153600a850494508461157a57509392505050565b5f6001600160a01b03831615801590610b895750826001600160a01b0316846001600160a01b031614806115e457506115e48484610b91565b80610b895750505f908152600460205260409020546001600160a01b03908116911614919050565b5f8072184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b831061164a5772184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b830492506040015b6d04ee2d6d415b85acef81000000008310611676576d04ee2d6d415b85acef8100000000830492506020015b662386f26fc10000831061169457662386f26fc10000830492506010015b6305f5e10083106116ac576305f5e100830492506008015b61271083106116c057612710830492506004015b606483106116d2576064830492506002015b600a83106104035760010192915050565b6001600160e01b031981168114610bf8575f5ffd5b5f60208284031215611708575f5ffd5b81356113ac816116e3565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b602081525f6113ac6020830184611713565b5f60208284031215611763575f5ffd5b5035919050565b6001600160a01b0381168114610bf8575f5ffd5b5f5f6040838503121561178f575f5ffd5b823561179a8161176a565b946020939093013593505050565b5f5f5f606084860312156117ba575f5ffd5b83356117c58161176a565b925060208401356117d58161176a565b929592945050506040919091013590565b5f602082840312156117f6575f5ffd5b81356113ac8161176a565b8015158114610bf8575f5ffd5b5f5f6040838503121561181f575f5ffd5b823561182a8161176a565b9150602083013561183a81611801565b809150509250929050565b5f5f5f60608486031215611857575f5ffd5b83356118628161176a565b95602085013595506040909401359392505050565b634e487b7160e01b5f52604160045260245ffd5b5f5f5f5f6080858703121561189e575f5ffd5b84356118a98161176a565b935060208501356118b98161176a565b925060408501359150606085013567ffffffffffffffff8111156118db575f5ffd5b8501601f810187136118eb575f5ffd5b803567ffffffffffffffff81111561190557611905611877565b604051601f8201601f19908116603f0116810167ffffffffffffffff8111828210171561193457611934611877565b60405281815282820160200189101561194b575f5ffd5b816020840160208301375f6020838301015280935050505092959194509250565b5f5f6040838503121561197d575f5ffd5b82356119888161176a565b9150602083013561183a8161176a565b600181811c908216806119ac57607f821691505b6020821081036119ca57634e487b7160e01b5f52602260045260245ffd5b50919050565b634e487b7160e01b5f52601160045260245ffd5b81810381811115610403576104036119d0565b5f81518060208401855e5f93019283525090919050565b5f610b89611a1c83866119f7565b846119f7565b5f60208284031215611a32575f5ffd5b81516113ac8161176a565b5f60208284031215611a4d575f5ffd5b81516113ac81611801565b5f60018201611a6957611a696119d0565b5060010190565b6001600160a01b03858116825284166020820152604081018390526080606082018190525f90611aa290830184611713565b9695505050505050565b5f60208284031215611abc575f5ffd5b81516113ac816116e356fea264697066735822122080881072d8749969866032b3c310cd014c8b7d0b762357a0adbf814aa3988d6464736f6c634300081c0033";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BUYNFT = "buyNFT";

    public static final String FUNC_CANCELLISTING = "cancelListing";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_GETLISTINGPRICE = "getListingPrice";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_LISTNFT = "listNFT";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SELLNFT = "sellNFT";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

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

    public static final Event NFTLISTED_EVENT = new Event("NFTListed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event NFTSOLD_EVENT = new Event("NFTSold", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BATCHMETADATAUPDATE_EVENT, transactionReceipt);
        ArrayList<BatchMetadataUpdateEventResponse> responses = new ArrayList<BatchMetadataUpdateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BATCHMETADATAUPDATE_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(METADATAUPDATE_EVENT, transactionReceipt);
        ArrayList<MetadataUpdateEventResponse> responses = new ArrayList<MetadataUpdateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(METADATAUPDATE_EVENT, log);
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

    public List<NFTListedEventResponse> getNFTListedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NFTLISTED_EVENT, transactionReceipt);
        ArrayList<NFTListedEventResponse> responses = new ArrayList<NFTListedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NFTListedEventResponse typedResponse = new NFTListedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.listingId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.nftContract = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.seller = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NFTListedEventResponse> nFTListedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NFTListedEventResponse>() {
            @Override
            public NFTListedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NFTLISTED_EVENT, log);
                NFTListedEventResponse typedResponse = new NFTListedEventResponse();
                typedResponse.log = log;
                typedResponse.listingId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.nftContract = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.seller = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NFTListedEventResponse> nFTListedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NFTLISTED_EVENT));
        return nFTListedEventFlowable(filter);
    }

    public List<NFTSoldEventResponse> getNFTSoldEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NFTSOLD_EVENT, transactionReceipt);
        ArrayList<NFTSoldEventResponse> responses = new ArrayList<NFTSoldEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NFTSoldEventResponse typedResponse = new NFTSoldEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.seller = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NFTSoldEventResponse> nFTSoldEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NFTSoldEventResponse>() {
            @Override
            public NFTSoldEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NFTSOLD_EVENT, log);
                NFTSoldEventResponse typedResponse = new NFTSoldEventResponse();
                typedResponse.log = log;
                typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.seller = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NFTSoldEventResponse> nFTSoldEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NFTSOLD_EVENT));
        return nFTSoldEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
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
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
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
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> buyNFT(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUYNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> cancelListing(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CANCELLISTING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getListingPrice(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLISTINGPRICE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> listNFT(String nftContract, BigInteger tokenId, BigInteger price) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LISTNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, nftContract), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> sellNFT(String nftContract, BigInteger tokenId, BigInteger price) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SELLNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, nftContract), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
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

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
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
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
        return deployRemoteCall(NFT_Token.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<NFT_Token> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
        return deployRemoteCall(NFT_Token.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NFT_Token> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
        return deployRemoteCall(NFT_Token.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NFT_Token> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
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

    public static class NFTListedEventResponse extends BaseEventResponse {
        public BigInteger listingId;

        public String nftContract;

        public BigInteger tokenId;

        public String seller;

        public BigInteger price;
    }

    public static class NFTSoldEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public String buyer;

        public String seller;
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
