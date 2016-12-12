package ver1_2;

public class Car {
	private Tire tire;
	
	public Car(){
		tire = new KoreaTire();
//		tire = new ChinaTire();
	}
	
	public void printCarInfo(){
		System.out.println
			("현재 차량 장착 타이어 : "+ tire.getModel());
	}}
