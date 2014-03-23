package m2tiil.agence.voyage.shared.util.critere;

public abstract class Critere<T extends Object, TVALUE extends Object> {

	public enum Comparator {SUPERIOR,INFERIOR,EQUAL,BETWEEN};
	
	protected Comparator comparator;
	protected boolean activated;
	
		
	public abstract boolean correspond(T object);
	
	
	
	
	public abstract void setFirstValue(TVALUE o);
	public abstract void setSecondValue(TVALUE o);
	
	public abstract String getFirstValue();
	public abstract String getSecondValue();
	
	
	
	
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
