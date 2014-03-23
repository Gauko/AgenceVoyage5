package m2tiil.agence.voyage.shared.util.critere;

import java.io.Serializable;
import java.util.Date;

public abstract class Critere<T extends Object, TVALUE extends Object>  implements Serializable {

	public enum Comparator {SUPERIOR,INFERIOR,EQUAL,BETWEEN};
	
	protected Comparator comparator;
	protected boolean activated;
	
	protected TVALUE value1;
	protected TVALUE value2;
	
	
	/**
	 * NE PAS UTILISER CÔTE CLIENT !!!
	 * @param object
	 * @return
	 */
	public abstract boolean correspond(T object);
	
	
	
	
	public void setFirstValue(TVALUE o){
		value1 = o;
	}
	public void setSecondValue(TVALUE o){
		value2 = o;
	}
	
	public TVALUE getFirstValue(){
		return value1;
	}
	public TVALUE getSecondValue(){
		return value2;
	}
	
	
	
	
	public void setComparator(Comparator c){
		comparator = c;
	}




	public boolean isActivated() {
		return activated;
	}




	public void setActivated(boolean activated) {
		this.activated = activated;
	}




	public Comparator getComparator() {
		return comparator;
	}
	
}
