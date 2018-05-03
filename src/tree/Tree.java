package tree;

import java.io.Serializable;
/*
 *  Cuva jednu instancu klase Node koji predstavlja korenski cvor stabla
 */
@SuppressWarnings("serial")
public class Tree implements Serializable{
	 
    private Node rootElement;
     
    public Tree() {
        super();
    }
 
    public Node getRootElement() {
        return this.rootElement;
    }
 
    public void setRootElement(Node rootElement) {
        this.rootElement = rootElement;
    }
     


}
