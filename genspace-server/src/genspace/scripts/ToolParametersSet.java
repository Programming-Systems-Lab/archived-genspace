package genspace.scripts;

/**
 * A set of parameters values for a given Tool
 * with the number of times this set has been used for the specified tool
 * @author fp2229
 *
 */
public class ToolParametersSet {
	
	//Main attributes
	public String parameters = "";// Parameter string e.g., "attr1 = 3, attr2 = 4, ..."
	public int id; //tool id
	public int count = 1; //num usage, must be one initially
	
	//Attributes used only in the script loading phase
	String tid;
	String date;
	
	public ToolParametersSet(int id, String parameters){
		this.id = id;
		this.parameters = parameters;
	}
	
	public boolean equals(Object o){
		if(o instanceof ToolParametersSet) {
			ToolParametersSet t = (ToolParametersSet)o;
			return t.id == id && t.parameters == parameters;
		}
		return false;
	}
	
	public void addParameter(String key, String value){
		String add = key + " = " + value;
		if(parameters == null || parameters.equals(""))
			parameters = add;
		else parameters+=", "+add;
	}
	
	public void addParameter(String param){
		if(parameters == null || parameters.equals(""))
			parameters = param;
		else parameters+=", "+param;
	}
	
	
	
	public int hashCode(){
		//the key is (id, param)
		return (id+"_"+parameters).hashCode();
	}
	
	/**
	 * checks weather a row from analysis_event_parameter is part of this set
	 * @return
	 */
	public boolean sameSet(ToolParametersSet t){
		return t.id == id  && t.tid.equals(tid) && t.date.equals(date);
	}
}
