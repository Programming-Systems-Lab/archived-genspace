package genspace.db;

//a pojo that is mapped to a row in the workflows table
public class WorkflowNode {
	//
		
		private int id;
		private int parent;
		private String toolName;
		
		public WorkflowNode (int i, int p, String t){
			id = i;
			parent = p;
			toolName = t;
		}

		public String getToolName() {
			return toolName;
		}

		public void setToolName(String toolName) {
			this.toolName = toolName;
		}

		public int getId() {
			return id;
		}

		public int getParent() {
			return parent;
		}
		
		public String toString(){
			return "(id:" + id + " parent:" + parent + " tool:" + toolName + ")";
					
		}
}
