package genspace.scripts;

public class TestScript2 extends Scripter {
	
	public void doThis() {
		// TODO Auto-generated method stub
		System.out.println("World " + getTimeout());
	}
	
	
	public long getTimeout() {
		return 500;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestScript2 t = new TestScript2();
		Thread th = new Thread(t);
		th.start();
	}

}
