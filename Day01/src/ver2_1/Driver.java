package ver2_1;

// 운전자는 차를 운전하려고 한다.
// (서블릿은 클라이언트의 요청을 처리해야 한다.)
public class Driver {
	public static void main(String[] args) {
		// Car 객체가 의존하는 타이어객체를 외부에서 주입함.
		KoreaTire t = new KoreaTire();
//		ChinaTire t = new ChinaTire();
		Car myCar = new Car(t);
		
		myCar.printCarInfo();
	}

}
