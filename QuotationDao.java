package DAO;

import java.nio.file.FileAlreadyExistsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import EK.Quotation;
import utils.JDBCutils;

public class QuotationDao {

	// 新增商品
	public void saveQuotation(Quotation quotation) {
		String sql = "INSERT　INTO Quotation( number,useway, brand, size, fr,"
				+ " loadAndSpeed, price, note)VALUES(?,?,?,?,?,?,?,?)";
		Connection connection = JDBCutils.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, quotation.getNumber());
			preparedStatement.setString(2, quotation.getUseway());
			preparedStatement.setString(3, quotation.getBrand());
			preparedStatement.setString(4, quotation.getSize());
			preparedStatement.setString(5, quotation.getFr());
			preparedStatement.setString(6, quotation.getLoadAndSpeed());
			preparedStatement.setInt(7, quotation.getPrice());
			preparedStatement.setString(8, quotation.getNote());
			preparedStatement.execute();
			}//System.out.println("新增商品成功");

		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutils.closeResource(connection);
		}
	}
	
	//流水號重複
	public boolean numberNotDouble (int number) {
		boolean worng = false;
		String sql = "SELECT 1 FROM quotation WHERE number=?";
		Connection connection = JDBCutils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			worng = resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return worng;
	}
	//流水號確認
		public boolean numberRight (int number) {
			boolean right = true;
			String sql = "SELECT 1 FROM quotation WHERE number=?";
			Connection connection = JDBCutils.getConnection();
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, number);
				resultSet = preparedStatement.executeQuery();
				right = resultSet.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}return right;
		}
	

	// 刪除商品
	public void deleteQuotationByNumber(Integer quotationNumber) {
		String sql = "DELETE FROM quotation WHERE number=?";
		Connection connection = JDBCutils.getConnection();

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, quotationNumber);
			preparedStatement.execute();
			System.out.println("刪除商品number為:" + quotationNumber);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutils.closeResource(connection, preparedStatement);
		}
	}

	// 更新 
	public void changeProduct(int number, String columnName, Object newValue) {
		String sql = "UPDATE quotation SET " + columnName + " = ? WHERE number = ?";
		Connection connection = JDBCutils.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, newValue);
			preparedStatement.setInt(2, number);
			// preparedStatement.setString(1, size);
			int rows = preparedStatement.executeUpdate();
			// System.out.println("更新:" + number + "尺寸");

			if (rows > 0) {
				System.out.println("更新成功");
			} else {
				System.out.println("查無此流水號！");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutils.closeResource(connection, preparedStatement);
		}
	}

	// 查詢單筆，依據id查詢單筆資料
	public void cheackNumber(Integer number) {
		String sql = "SELECT * FROM quotation WHERE number=?";
		Connection connection = JDBCutils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, number);// ?
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			System.out.println(resultSet.getInt("number") + " , " + resultSet.getString("useway") + " , "
					+ resultSet.getString("brand") + " , " + resultSet.getString("size") + " , " + resultSet.getString("fr")
					+ " , " + resultSet.getString("loadAndSpeed") + " , " + resultSet.getInt("price") + " , "
					+ resultSet.getString("note"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutils.closeResource(connection, preparedStatement, resultSet);
		}

	}

	// 查詢全部，轉出json檔案
	public List<Quotation> findAllProduct() {
		List<Quotation> result = new ArrayList<>();
		// Quotation quotation = new Quotation();
		String sql = "SELECT * FROM quotation";
		Connection connection = JDBCutils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Quotation quotation = new Quotation();
				quotation.setNumber(resultSet.getInt("number"));
				quotation.setUseway(resultSet.getString("useway"));
				quotation.setBrand(resultSet.getString("brand"));
				quotation.setSize(resultSet.getString("size"));
				quotation.setFr(resultSet.getString("fr"));
				quotation.setLoadAndSpeed(resultSet.getString("loadAndSpeed"));
				quotation.setPrice(resultSet.getInt("price"));
				quotation.setNote(resultSet.getString("note"));
				/*
				 * System.out.println(resultSet.getInt("number") + "," +
				 * resultSet.getString("useway") + "," + resultSet.getString("brand") + "," +
				 * resultSet.getString("size") + "," + resultSet.getString("fr") + "," +
				 * resultSet.getString("loadAndSpeed") + "," + resultSet.getInt("price") + "," +
				 * resultSet.getString("note"));
				 */
				//System.out.println(quotation);
				result.add(quotation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCutils.closeResource(connection, preparedStatement, resultSet);
		}
		return result;
	}
}
