package test02;
 
import java.util.Date;
import java.util.Random;
 
public class ProductService {
    // 샘플 판매 데이터
    private int saleQuantity=1; // 판매수량
    private String buyer = "Yangyu"; // 구매자
    private Date saleDate = new Date(); // 구매일자
    private int productNum = 2; // 상품번호
//////////////////////////////////////////////////////  
    private ProductDao dao = new ProductDao();
 
    // 상품 판매는 재고수량 변경과 판매이력 추가를 해야함. 
    public void saleProduct() {
        try {
            dao.startTransction(); // 트랜잭션 시작
            dao.updateProductQuantity
                (productNum, saleQuantity);
            if (new Random().nextBoolean()) {
                throw new Exception("어쩌다 괜히 에러남;;");
            }
            dao.insertSaleRecord
                (productNum, saleDate, buyer, saleQuantity);
            dao.commitTransaction(); // 트랜잭션 커밋
            System.out.println("상품 판매 완료");
        } catch (Exception e) {
            dao.rollbackTransaction(); // 트랜잭션 롤백
            System.out.println("상품 판매 실패");
            e.printStackTrace();
        }finally{
        	dao.closeConnection();
        }
    }
}