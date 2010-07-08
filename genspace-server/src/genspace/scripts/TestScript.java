package genspace.scripts;

public class TestScript extends Scripter { 

	public void doThis() {
		// TODO Auto-generated method stub
		System.out.println("Hello " + getTimeout());
	}
	
	/*
	public int getTimeout() {
		return 50;
	}*/

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestScript t = new TestScript();
		Thread th = new Thread(t);
		th.start();
	}

}
