
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;


//Heba Alaa Ahmed Diaa Abdelrazek 37-5660 T18
// To Generate a textfile, please change the path on line 600 (Main method in ScroogeCoin)
/* Notes on the project & design:
 * 
 * 1. I used MessageDigest for hashing, KeyPairGenerator for generating private/public key pairs for users and scrooge,
 * 	  Signature library for signing anything with private key and verifying it with the public key.
 * 
 * 2. After Running, please wait for 2-4 minutes so that ScroogeCoin creates users, coins, and transfers the coins to the users, 
 *    then the randomization process starts.
 *    
 * 3. Terminate using Space, as long as you didn't close the GUI before it.
 * 
 * 4. As for the printed text file, I followed the following guidelines mentioned in the description:
 * 
 *	❖ Print initially the public key and the amount of coins for each user.
 *	❖ Scoorge should print the accumulator for each new transaction added (include the transaction details).
 *	❖ Print the blockchain after a new block is appended.
 *  I am also printing the invalid transactions.
 * 
 * 5. In the text file saved after termination, 
 *    - to search for the accumulator search for "Block Under Construction",
 * 	  - to search for any invalid transactions search for "invalid",
 *    - to search for the public keys and amount of coins after initialization and distributing the coins to users search for "Public Key"
 *    - when the block chain is updated it states "BlockChain After a new block is added"
 *    
 * 6. I have two types of transactions: CreateCoin when scrooge creates a new coin, and PayCoin when it's sent from user->user or scrooge->user
 * 	  both are 1 coin/transaction
 * 
 * */



class Coin {
	String coinID;		//coin_1 to coin_1000 since we have 100 users and 10 coins each
	String transHash; 	//Hash of the latest transaction that was performed on this coin
	Transaction trans;	//The latest transaction that was performed on this coin
	
	public Coin(String coinID) {
		this.coinID = coinID;
	}
	
}

abstract class Transaction{
	String transactionID;
	String senderSignature; //The sender of the transaction signs with his private key the transactioniD, coin ID, (senderID, receiverID for PayCoin and the prevtranshash
	Coin coin;
	
	String transHash;
	String prevTransHash; //Used to the history of the transactions performed on this coin in this transaction
	
	public Transaction(String transactionID, PrivateKey privateKeySender, Coin coin) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
		this.transactionID = transactionID;
		this.coin = coin;
	}
}

class CreateCoin extends Transaction {
	public CreateCoin(String transactionID, PrivateKey privateKeySender, Coin coin) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		
		super(transactionID, privateKeySender, coin);
		this.transHash = computeHash1();
		
		Signature privateSignature = Signature.getInstance("SHA256withRSA");
		 privateSignature.initSign(privateKeySender);
		 
		 String data_to_be_signed = "" + transactionID + coin.coinID + this.prevTransHash;
		 byte[] bytes = data_to_be_signed.getBytes();
	     privateSignature.update(bytes);
	      
		 byte[] signature = privateSignature.sign();
		 this.senderSignature = Base64.getEncoder().encodeToString(signature);
	}
	public String computeHash1() {
		String dataToHash = "" + this.transactionID + this.coin.coinID + this.prevTransHash;
		MessageDigest digest;
		String encoded = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			encoded = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.transHash = encoded;
		return encoded;
	}
}



class PayCoin extends Transaction{
	String senderID;
	String receiverID;
	
	public PayCoin(String transactionID, PrivateKey privateKeySender, Coin coin, String senderID, String receiverID) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		super(transactionID, privateKeySender, coin);
		this.receiverID =  receiverID;
		this.senderID = senderID;
		this.transHash = computeHash2();
		this.prevTransHash = coin.transHash;
		
		Signature privateSignature = Signature.getInstance("SHA256withRSA");
		privateSignature.initSign(privateKeySender);
		 
		String data_to_be_signed = "" + transactionID + coin.coinID + senderID + receiverID + this.prevTransHash;
		byte[] bytes = data_to_be_signed.getBytes();
	    privateSignature.update(bytes);
	      
		byte[] signature = privateSignature.sign();
		this.senderSignature = Base64.getEncoder().encodeToString(signature);	
	}
	public String computeHash2() {
		String dataToHash = "" + this.transactionID + this.coin.coinID + this.senderID + this.receiverID + this.prevTransHash;
		MessageDigest digest;
		String encoded = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			encoded = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.transHash = encoded;
		return encoded;
	}
	
}

class Block{
	String blockID;
	String blockHash;
	String prevBlockHash;
	
	ArrayList<Transaction> transactions; 
	
	public Block(String blockID, ArrayList<Transaction> transactions) {
		this.transactions = transactions;
		this.blockHash = computeHash();
		this.blockID = blockID;
	}
	
	public String computeHash() {
			String dataToHash = "" + this.blockID + this.transactions + this.prevBlockHash;
			MessageDigest digest;
			String encoded = null;
			try {
				digest = MessageDigest.getInstance("SHA-256");
				byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
				encoded = Base64.getEncoder().encodeToString(hash);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			this.blockHash = encoded;
			return encoded;
	}

	public String getBlockID() {
		return blockID;
	}

	public void setBlockID(String blockID) {
		this.blockID = blockID;
	}

	public String getBlockHash() {
		return blockHash;
	}

	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}

	public String getPrevBlockHash() {
		return prevBlockHash;
	}

	public void setPrevBlockHash(String prevBlockHash) {
		this.prevBlockHash = prevBlockHash;
	}
}


class BlockChain{
	ArrayList<Block> blockChain;
	//The first block of the block chain is added on initialization, it's block_0 and it's an empty block with no transactions but added for sake of design
	public BlockChain() { 
		blockChain = new ArrayList<Block>();
		Block firstBlock = new Block("block_0", new ArrayList<Transaction>());
		firstBlock.setPrevBlockHash(null);
		firstBlock.computeHash();
		blockChain.add(firstBlock);
	}
	
	public void addBlock(Block blk) {
		Block newBlock = blk;
		newBlock.setPrevBlockHash(blockChain.get(blockChain.size()-1).getBlockHash());
		newBlock.computeHash();
		this.blockChain.add(newBlock);
	}

}

class User{
	String userID;
	ArrayList<Coin> userCoins = new ArrayList<Coin>();
	ArrayList<String> userCoinIDs = new ArrayList<String>();
	PublicKey publicKey;
	PrivateKey privateKey;

	public User(String userID) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		 KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		 generator.initialize(2048, new SecureRandom());
		 KeyPair pair = generator.generateKeyPair();
		 this.publicKey = pair.getPublic();
		 this.privateKey = pair.getPrivate();
		 
		 this.userID = userID;
	}
	
	
}

public class ScroogeCoin {
	String scroogeID = "scrooge";
	public PublicKey publicKey;
	PrivateKey privateKey;
	
	static ArrayList<User> users = new ArrayList<User>();
	ArrayList<Coin> coins = new ArrayList<Coin>();
	static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	static ArrayList<Transaction> accumulator = new ArrayList<Transaction>(); 
	
	BlockChain scroogeBlockChain = new BlockChain();
	
	static int transCount = 0;
	static int blocksCount = 0;
	
	public static String finalPointerSignature;
	
	FileWriter fileWriter;
	
	public ScroogeCoin(FileWriter fileWriter) throws Exception {
		 KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		 generator.initialize(2048, new SecureRandom());
		 KeyPair pair = generator.generateKeyPair();
		 this.publicKey = pair.getPublic();
		 this.privateKey = pair.getPrivate();
		 
		 this.fileWriter = fileWriter;
		 
		 createUsers(); 
		 createCoins();
		 transferCoinsToUsers();
		 
		 //Print initially the public key and the amount of coins for each user.
		 printPublicKeysCoins(fileWriter);
		 
		 
	}
	
	public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
	    Signature publicSignature = Signature.getInstance("SHA256withRSA");
	    
	    publicSignature.initVerify(publicKey);
	    publicSignature.update(plainText.getBytes());
	    byte[] signatureBytes = Base64.getDecoder().decode(signature);
	    return publicSignature.verify(signatureBytes);
	}
	
	public void createUsers() throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		users = new ArrayList<User>();
		for(int i = 1; i < 101 ; i++) { 
			
			User newUser = new User("user_" + i);
			users.add(newUser);
		}
	}
	
	public void createCoins() throws Exception {
		this.coins = new ArrayList<Coin>();
		for(int i = 1; i<1001; i++) {
			Coin newCoin = new Coin("coin_" + i);			
			
			//CreateCoin Transaction
			transCount++;
			CreateCoin createCoinTransaction = new CreateCoin("trans_" + transCount, this.privateKey, newCoin);
			createCoinTransaction.prevTransHash = null; //no steps before this
			
			transactions.add(createCoinTransaction);
			
			//verify signature before adding to accumulator
			String data_to_be_verified = "" + "trans_" + transCount + newCoin.coinID + createCoinTransaction.prevTransHash;
			if(verify(data_to_be_verified, createCoinTransaction.senderSignature, this.publicKey)) {
				accumulator.add(createCoinTransaction);
				//print block underconstruction: accumulator
				fileWriter.write("The Block Under Construction contains those transactions now: " + System.getProperty( "line.separator" ));
				fileWriter.write(" " + System.getProperty( "line.separator" )); 
				displayTransactions(accumulator);
				
				newCoin.transHash = createCoinTransaction.transHash; 
				newCoin.trans = createCoinTransaction;
				
				if(accumulator.size() == 10) {
					blocksCount++;
					ArrayList<Transaction> tempAccumulator = new ArrayList<Transaction>();
					tempAccumulator = (ArrayList<Transaction>) accumulator.clone();
					Block newBlock = new Block("block_"+ blocksCount, tempAccumulator);
					this.scroogeBlockChain.addBlock(newBlock);
					signFinalHashPointer(this.privateKey, newBlock.blockHash);
					displayBlockChain();
					accumulator.clear();
				}
			}
			this.coins.add(newCoin);
		}
	}
	
	public void transferCoinsToUsers() throws Exception { //user at pos 0 takes 0-9, 1 takes 10-19, 2 takes 20-29, 99 takes 990-999
		
		for(int i = 0; i < 100; i++) { //loop on users
			User currentUser = users.get(i);
			ArrayList<String> tempCoinIDs = new ArrayList<String>();
			ArrayList<Coin> tempCoins = new ArrayList<Coin>();
			
			for(int j = 0; j < 10; j++) { //loop on coins
				Coin currentCoin = this.coins.get(j + 10*i); //transfer this currentCoin to the currentUser
				
				transCount++;
				PayCoin payCoinTransaction = new PayCoin("trans_" + transCount, this.privateKey, currentCoin, this.scroogeID, currentUser.userID);
				transactions.add(payCoinTransaction);
				
				String data_to_be_verified =  "" + "trans_" + transCount + currentCoin.coinID + this.scroogeID + currentUser.userID + payCoinTransaction.prevTransHash ;
				if(verify(data_to_be_verified, payCoinTransaction.senderSignature, this.publicKey)) {
					payCoinTransaction.prevTransHash = currentCoin.transHash; //////////////////////////////////////////////////
					currentCoin.transHash = payCoinTransaction.transHash; 
					currentCoin.trans = payCoinTransaction;
					
					tempCoinIDs.add(currentCoin.coinID);
					tempCoins.add(currentCoin);

					accumulator.add(payCoinTransaction);
					fileWriter.write("The Block Under Construction contains those transactions now: " + System.getProperty( "line.separator" ));
					fileWriter.write(" " + System.getProperty( "line.separator" )); 
					displayTransactions(accumulator);
					//Prints block underconstruction: accumulator
					
					if(accumulator.size() == 10) {
						blocksCount++;
						ArrayList<Transaction> tempAccumulator = new ArrayList<Transaction>();
						tempAccumulator = (ArrayList<Transaction>) accumulator.clone();
						Block newBlock = new Block("block_"+ blocksCount, tempAccumulator);
						this.scroogeBlockChain.addBlock(newBlock);
						signFinalHashPointer(this.privateKey, newBlock.blockHash);
						displayBlockChain();
						accumulator.clear();
						
						for(int k = 0; k < tempCoinIDs.size(); k++) {
							currentUser.userCoinIDs.add(tempCoinIDs.get(k)); 
							currentUser.userCoins.add(tempCoins.get(k)); 
						}
					}
				}	
			}
		}
	}

	public static String signFinalHashPointer(PrivateKey privateKeySender, String blockHash) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		
		Signature privateSignature = Signature.getInstance("SHA256withRSA");
		privateSignature.initSign(privateKeySender);
		 
		String data_to_be_signed = "" + blockHash;
		byte[] bytes = data_to_be_signed.getBytes();
	    privateSignature.update(bytes);
	      
		byte[] signature = privateSignature.sign();
		finalPointerSignature = Base64.getEncoder().encodeToString(signature);	
		return finalPointerSignature;	
	}
	
	public static boolean validateTransaction(PayCoin transaction, User sender, Coin coin) throws Exception {
		//1. check signature valid
		String data_to_be_verified =  "" + transaction.transactionID + transaction.coin.coinID + transaction.senderID + transaction.receiverID + transaction.prevTransHash;
		boolean verifySignature = verify(data_to_be_verified, transaction.senderSignature, sender.publicKey);
		
		if(verifySignature) {
			//2. check ownership of coin: block chain
			
			PayCoin latestTransactionOnCoin = (PayCoin) coin.trans;
			if(latestTransactionOnCoin.receiverID.equals(sender.userID)) { //the sender owns the coin since he was the receiver of its last transaction
				
				//3. check double spending mel accumulator
				for(int i = 0; i < accumulator.size() ; i++) {
					PayCoin currentTrans = (PayCoin) accumulator.get(i);
					if(currentTrans.coin.coinID.equals(transaction.coin.coinID) && currentTrans.senderID.equals(transaction.senderID)){ //double spending
						return false;
					}
				}	
			}
			else { //if the last transaction that was done on this coin wasn't received by our sender, then he does not own this coin
				return false;
			}	
			
		}
		else {
			return false;
		}
		
		return true;
	}
	
	public static void printPublicKeysCoins(FileWriter fileWriter) throws IOException {
		
		for(int i = 0; i < users.size(); i++) {
			String publicKeyString =  Base64.getEncoder().encodeToString(users.get(i).publicKey.getEncoded());
			System.out.println("Public Key for " + users.get(i).userID + ": " + publicKeyString);
			System.out.println("Amount of Coins is: " + users.get(i).userCoins.size());
			fileWriter.write("Public Key for " + users.get(i).userID + ": " + publicKeyString + System.getProperty( "line.separator" ));
			fileWriter.write(" " + System.getProperty( "line.separator" ));
			fileWriter.write("Amount of Coins is: " + users.get(i).userCoins.size() + System.getProperty( "line.separator" ));
			fileWriter.write(" " + System.getProperty( "line.separator" ));
		}
	}
	
	public void displayBlockChain() throws IOException {
		System.out.println("BlockChain After a new block is added");
		fileWriter.write("BlockChain After a new block is added" + System.getProperty( "line.separator" ));
		fileWriter.write(" " + System.getProperty( "line.separator" ));
		
			for(int i = 0; i < scroogeBlockChain.blockChain.size(); i++) {
				System.out.println("Block: " + i);
				fileWriter.write("Block: " + i + System.getProperty( "line.separator" ));
				
				System.out.println("BlockID: "+ scroogeBlockChain.blockChain.get(i).getBlockID());
				fileWriter.write("BlockID: "+ scroogeBlockChain.blockChain.get(i).getBlockID() + System.getProperty( "line.separator" ));
				
				System.out.println("PreviousHash: " + scroogeBlockChain.blockChain.get(i).getPrevBlockHash());
				fileWriter.write("PreviousHash: " + scroogeBlockChain.blockChain.get(i).getPrevBlockHash() + System.getProperty( "line.separator" ));
				
				System.out.println("Hash: " + scroogeBlockChain.blockChain.get(i).getBlockHash());
				fileWriter.write("Hash: " + scroogeBlockChain.blockChain.get(i).getBlockHash() + System.getProperty( "line.separator" ));
				
				fileWriter.write(" " + System.getProperty( "line.separator" ));

				System.out.println("Transactions in this block: ");
				fileWriter.write("Transactions in this block: " + System.getProperty( "line.separator" ));
								
				displayTransactions(scroogeBlockChain.blockChain.get(i).transactions);
				
				fileWriter.write(" " + System.getProperty( "line.separator" ));
				fileWriter.write(" " + System.getProperty( "line.separator" ));

				System.out.println();
			}		
	}
	
	public void displayTransactions(ArrayList<Transaction> transArray) throws IOException {
		
		 for(int j = 0 ; j < transArray.size() ; j++) {
			 Transaction trans = transArray.get(j);
			 System.out.println("Transaction ID: " + trans.transactionID);
			 fileWriter.write("Transaction ID: " + trans.transactionID + System.getProperty( "line.separator" ));
			 
			 System.out.println("Coin ID: " + trans.coin.coinID);
			 fileWriter.write("Coin ID: " + trans.coin.coinID + System.getProperty( "line.separator" ));
			 
			 System.out.println("Transaction Hash: " + trans.transHash);
			 fileWriter.write("Transaction Hash: " + trans.transHash + System.getProperty( "line.separator" ));
			 
			 System.out.println("Previous Transaction Hash: " + trans.prevTransHash);
			 fileWriter.write("Previous Transaction Hash: " + trans.prevTransHash + System.getProperty( "line.separator" ));
			 
			 System.out.println("Sender Signature: " + trans.senderSignature);
			 fileWriter.write("Sender Signature: " + trans.senderSignature + System.getProperty( "line.separator" ));
			 
			 
			 if(trans instanceof PayCoin) {
				 PayCoin transNew = (PayCoin) trans;
				 System.out.println("Transaction Type: PayCoin");
				 fileWriter.write("Transaction Type: PayCoin " + System.getProperty( "line.separator" ));

				 System.out.println("Sender ID: " + transNew.senderID);
				 fileWriter.write("Sender ID: " + transNew.senderID + System.getProperty( "line.separator" ));

				 System.out.println("Receiver ID: " + transNew.receiverID);
				 fileWriter.write("Receiver ID: " + transNew.receiverID + System.getProperty( "line.separator" ));
 
			 }
			 else {
				System.out.println("Transaction Type: CreateCoin");
				fileWriter.write("Transaction Type: CreateCoin " + System.getProperty( "line.separator" ));
				 
				 
				 
			 }
			 fileWriter.write(" " + System.getProperty( "line.separator" )); 
			 
		 }
		
	}
	
	public void displayOneTransaction(Transaction trans) throws IOException {
		 fileWriter.write("This is an invalid Transaction: " + System.getProperty( "line.separator" ));
		 System.out.println("Transaction ID: " + trans.transactionID);
		 fileWriter.write("Transaction ID: " + trans.transactionID + System.getProperty( "line.separator" ));
		 
		 System.out.println("Coin ID: " + trans.coin.coinID);
		 fileWriter.write("Coin ID: " + trans.coin.coinID + System.getProperty( "line.separator" ));
		 
		 System.out.println("Transaction Hash: " + trans.transHash);
		 fileWriter.write("Transaction Hash: " + trans.transHash + System.getProperty( "line.separator" ));
		 
		 System.out.println("Previous Transaction Hash: " + trans.prevTransHash);
		 fileWriter.write("Previous Transaction Hash: " + trans.prevTransHash + System.getProperty( "line.separator" ));
		 
		 System.out.println("Sender Signature: " + trans.senderSignature);
		 fileWriter.write("Sender Signature: " + trans.senderSignature + System.getProperty( "line.separator" ));
		 
		 
		 if(trans instanceof PayCoin) {
			 PayCoin transNew = (PayCoin) trans;
			 System.out.println("Transaction Type: PayCoin");
			 fileWriter.write("Transaction Type: PayCoin " + System.getProperty( "line.separator" ));

			 System.out.println("Sender ID: " + transNew.senderID);
			 fileWriter.write("Sender ID: " + transNew.senderID + System.getProperty( "line.separator" ));

			 System.out.println("Receiver ID: " + transNew.receiverID);
			 fileWriter.write("Receiver ID: " + transNew.receiverID + System.getProperty( "line.separator" ));

		 }
		 else {
			System.out.println("Transaction Type: CreateCoin");
			fileWriter.write("Transaction Type: CreateCoin " + System.getProperty( "line.separator" ));
			 
			 
			 
		 }
		 fileWriter.write(" " + System.getProperty( "line.separator" )); 
		 

		
	}
	

	
	public static void main(String[] args) throws Exception {
		KeyListener kl=new KeyAdapter()  
		 {  
		  public void keyPressed(KeyEvent evt)  
		  {  
		   if(evt.getKeyCode()==KeyEvent.VK_SPACE)  
		   {  
		    System.exit(0);  
		   }  
		  }  
		 };  
		   
		JFrame frame=new JFrame("CLOSE JFRAME/TERMINATE USING SPACE!");  
		frame.addKeyListener(kl);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(1000,500);

		JLabel notes = new JLabel("<html> To Generate a textfile, please change the path on line 600 (Main method in ScroogeCoin) <br/><br/><br/><br/> ** Notes on the project & design: <br/><br/> ** 1. I used MessageDigest for hashing, KeyPairGenerator for generating private/public key pairs for users and scrooge, <br/> ** Signature library for signing anything with private key and verifying it with the public key.<br/><br/> ** 2. After Running, please wait for 2-4 minutes so that ScroogeCoin creates users, coins, and transfers the coins to the users, <br/> ** then the randomization process starts. <br/><br/> ** 3. Terminate using Space, as long as you didn't close the GUI before it. <br/><br/> ** 4. As for the printed text file, I followed the following guidelines mentioned in the description: <br/> ** Print initially the public key and the amount of coins for each user.	<br/> ** Scoorge should print the accumulator for each new transaction added (include the transaction details). <br/> ** Print the blockchain after a new block is appended.  <br/> ** I am also printing the invalid transactions.<br/> <br/> ** 5. In the text file saved after termination, <br/> ** - to search for the accumulator search for (Block Under Construction) <br/> ** - to search for any invalid transactions search for (invalid) <br/> ** - to search for the public keys and amount of coins after initialization and distributing the coins to users search for (Public Key) <br/> ** - when the block chain is updated it states (BlockChain After a new block is added)		<br/> <br/> ** 6. I have two types of transactions: CreateCoin when scrooge creates a new coin, and PayCoin when it's sent from user->user or scrooge->user  <br/> ** Both are 1 coin/transaction </html>");
		frame.add(notes);
		frame.setVisible(true);  
		
	    File file = new File("/Users/heba/Downloads/scroogeproject.txt");
	    FileWriter fileWriter = new FileWriter(file);
	  	
		ScroogeCoin scrooge = new ScroogeCoin(fileWriter);
		
		boolean spaceClicked = false;
		while(!spaceClicked) {
			//Randomization
			Random rand = new Random();
			int userA = rand.nextInt(100) + 1;
			int userB = rand.nextInt(100) + 1; 
			String userAID = "user_" + userA;
			String userBID = "user_" + userB;
			User sender = users.get(userA-1); //since user at index 0 is user_1, at indec 1 is user_2
			User receiver = users.get(userB-1);
			
			int amountUpperBound = sender.userCoinIDs.size(); //max number of coins a sender can send mslan 12
			int amount = rand.nextInt(amountUpperBound) + 1; //userA can send from 1 coin to all coins he own to userB 0-11 1-12 
			
			for(int i = 0; i < amount-2; i++) { //for each coin in the "amount" i will make a paycoin transaction
				String currentCoinID = sender.userCoinIDs.get(i); //1st coin id in senders array of coins
				Coin currentCoin = sender.userCoins.get(i);
				
				transCount++;
				PayCoin payCoinTransaction = new PayCoin("trans_" + transCount, sender.privateKey, currentCoin, userAID, userBID);
				
				boolean isTransValid = validateTransaction(payCoinTransaction, sender, currentCoin);
				if(isTransValid) {
					accumulator.add(payCoinTransaction);
					fileWriter.write("The Block Under Construction contains those transactions now: " + System.getProperty( "line.separator" ));
					fileWriter.write(" " + System.getProperty( "line.separator" )); 
					scrooge.displayTransactions(accumulator);
					
					if(accumulator.size() == 10) {
						blocksCount++;
						ArrayList<Transaction> tempAccumulator = new ArrayList<Transaction>();
						tempAccumulator = (ArrayList<Transaction>) accumulator.clone();
						Block newBlock = new Block("block_"+ blocksCount, tempAccumulator);
						
						scrooge.scroogeBlockChain.addBlock(newBlock); //published in block chain
						signFinalHashPointer(scrooge.privateKey, newBlock.blockHash);
						
						//CHANGE OWNERSHIP OF THE COIN
						sender.userCoins.remove(currentCoin);
						sender.userCoinIDs.remove(currentCoinID);
						
						receiver.userCoins.add(currentCoin);
						receiver.userCoinIDs.add(currentCoinID);
						
						payCoinTransaction.prevTransHash = currentCoin.transHash; 
						currentCoin.transHash = payCoinTransaction.transHash; 
						currentCoin.trans = payCoinTransaction; //lastest transaction on this coin
			
						transactions.add(payCoinTransaction);
						scrooge.displayBlockChain();
						accumulator.clear();
					}
				}
				else { //if trans not valid
					transCount--;	
					scrooge.displayOneTransaction(payCoinTransaction); //printing the invalid transaction
				}
			}
		}
		fileWriter.close();
	}	
}
 


