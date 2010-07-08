package genspace.scripts;

import java.lang.reflect.*;

public class ScriptRunner {

	static String[] list = {"genspace.scripts.ToolUpdateScript", "genspace.scripts.IndexUpdateScript"};

	public ScriptRunner() {
		runScript();
	}

	public void runScript() {
		for (String className : list) {
			System.out.println("Running " + className);
			try {
				Class clazz = Class.forName(className);
				Constructor[] cons = clazz.getConstructors();
				if (cons.length != 0) {
					Object a = cons[0].newInstance();
					Thread thread = new Thread((Runnable)a);
					thread.start();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScriptRunner sr = new ScriptRunner();
	}

}
