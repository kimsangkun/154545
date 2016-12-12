package test02;
 
import java.util.Date;
import java.util.Random;
 
public class ProductService {
    // ���� �Ǹ� ������
    private int saleQuantity=1; // �Ǹż���
    private String buyer = "Yangyu"; // ������
    private Date saleDate = new Date(); // ��������
    private int productNum = 2; // ��ǰ��ȣ
//////////////////////////////////////////////////////  
    private ProductDao dao = new ProductDao();
 
    // ��ǰ �ǸŴ� ������ ����� �Ǹ��̷� �߰��� �ؾ���. 
    public void saleProduct() {
        try {
            dao.startTransction(); // Ʈ����� ����
            dao.updateProductQuantity
                (productNum, saleQuantity);
            if (new Random().nextBoolean()) {
                throw new Exception("��¼�� ���� ������;;");
            }
            dao.insertSaleRecord
                (productNum, saleDate, buyer, saleQuantity);
            dao.commitTransaction(); // Ʈ����� Ŀ��
            System.out.println("��ǰ �Ǹ� �Ϸ�");
        } catch (Exception e) {
            dao.rollbackTransaction(); // Ʈ����� �ѹ�
            System.out.println("��ǰ �Ǹ� ����");
            e.printStackTrace();
        }finally{
        	dao.closeConnection();
        }
    }
}