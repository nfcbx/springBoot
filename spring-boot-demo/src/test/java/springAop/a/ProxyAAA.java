package springAop.a;

public class ProxyAAA {
	
	private AAA aaa;
	
	public ProxyAAA(AAA aaa){
		this.aaa = aaa;
	}
	
	public void test() {
		Logger.before();
		aaa.test();
		Logger.after();
	}

}
