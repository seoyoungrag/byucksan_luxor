package luxor;

public class TestMain {

	public static void main(String[] args) {
		Test t = new Test();
		
		for (int i = 0; i < 10000; i++) {
			//System.out.println(i+"="+t.getLastTime());
			System.out.println(i+"="+System.currentTimeMillis());
		}
	}

}
