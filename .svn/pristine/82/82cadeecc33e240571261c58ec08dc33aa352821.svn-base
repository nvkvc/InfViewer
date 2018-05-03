package tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tree.KeyElement;
import tree.NodeElement;
/*
 * Opisuje jedan element cvora. U sebi sadrzi listu KeyElement-a kao i
   jedan atribut tipa int koji u sebi nosi informaciju o adresi bloka u kojoj
   se nalazi vrednost kljuca opisana u listi. Za adresu bloka cemo uzimati
   redni broj sloga unutar datoteke, odnosno redni broj prvog sloga. Kako
   je velicina sloga fiksna vrednost imacemo mogucnost direktnog pristupa
   bloku. 
 */
@SuppressWarnings("serial")
public class NodeElement implements Serializable{

	private int blockAddress;
	
	private List<KeyElement> keyValue;
    
    public NodeElement(int blockAddress,List<KeyElement> keyValue) {
		super();
		this.blockAddress = blockAddress;
		this.keyValue = keyValue;
	}

	public List<KeyElement> getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(List<KeyElement> keyValue) {
		this.keyValue = keyValue;
	}
	public int getBlockAddress() {
		return blockAddress;
	}
	public void setBlockAddress(int blockAddress) {
		this.blockAddress = blockAddress;
	}
	
	public String toString(){
		String value="AE: "+blockAddress+" Key: ";
		for (int i=0;i<keyValue.size();i++){
			value+=keyValue.get(i).getValue()+"|";
		}
		return value;

	}
	
	public NodeElement clone(){
		List<KeyElement> keyValueCopy=new ArrayList<KeyElement>();
		keyValueCopy.addAll(keyValue);
		return new NodeElement(blockAddress,keyValueCopy);
	}
}
