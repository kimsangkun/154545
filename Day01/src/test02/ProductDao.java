package test02;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductDao {

	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://127.0.0.1::3306/jsp";
	public static final String DB_ID = "root";
	public static final String DB_PW = "sds902";

	private Connection con;

	public ProductDao() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� ��������");
			e.printStackTrace();
		}
	}

	public void startTransction() {
		try {
			con = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);
			con.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println("Ŀ�ؼ� ��������");
			e.printStackTrace();
		}
	}

	public void commitTransaction() {
		try {
			con.commit();
		} catch (SQLException e) {
			System.out.println("Ŀ�� ����");
			e.printStackTrace();
		}
	}

	public void rollbackTransaction() {
		try {
			con.rollback();
		} catch (SQLException e) {
			System.out.println("�ѹ� ����");
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Ŀ�ؼ� ���� ����");
				e.printStackTrace();
			}
		}
	}

	public int updateProductQuantity(int productNum, int quantity) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "update product set " + "quantity=quantity-? where product_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, productNum);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateProductQuantity ����");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}

	public int insertSaleRecord(int productNum, Date saleDate, String buyer, int saleCount) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "insert into sale" + "(product_num,sale_date,buyer,sale_count)" + "values(?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(saleDate);

			pstmt.setInt(1, productNum); // ��ǰ ��ȣ
			pstmt.setString(2, currentTime);
			pstmt.setString(3, buyer); // ������
			pstmt.setInt(4, saleCount); // �Ǹż���
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insertSaleRecord ����");
			e.printStackTrace();
		}
		return result;
	}
}
