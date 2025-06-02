package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCutils {
	public static Connection getConnection() {
        Connection connection = null;
        FileInputStream fileInputStream = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // 從 JAR 外部讀取 properties 檔
            File file = new File("jdbc.properties");
            if (!file.exists()) {
                throw new FileNotFoundException("找不到 jdbc.properties，請確認檔案是否與 jar 同層！");
            }

            fileInputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");

            connection = DriverManager.getConnection(url, user, password);
            boolean status = !connection.isClosed();
            System.out.println("✅ 資料庫連線成功：" + status);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return connection;
	}

	public static void closeResource(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeResource(Connection connection, Statement statment) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (statment != null) {
				statment.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeResource(Connection connection, Statement statment, ResultSet resultSet) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (statment != null) {
				statment.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
