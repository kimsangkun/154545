package ver1;

// Car 객체는 Tire 객체를 의존함.
// (Service 클래스 객체는 Dao 객체가 필요함)
public class Car {
//	private KoreaTire tire;
	private ChinaTire tire;
	
	public Car() {
		// 의존하는 객체를 직접 new 키워드로 생성함.
//		tire = new KoreaTire();
		tire = new ChinaTire();
	}
	
	public void printCarInfo(){
		System.out.println
			("현재 차량 장착 타이어 : "+ tire.getModelChina());
	}

}
