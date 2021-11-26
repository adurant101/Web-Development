package dao;

/*
 * Database to connect to JDBC for MySql
 * 
 */
import java.sql.*;

public class Database {
    // JDBC driver name and database URL
	//connect to mysql
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";
    private Connection conn = null;
    private ResultSet resultSet = null;
    private int connIdx=1;
    static final String[][] connections = {
        {"com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/test", "root", ""},
        {"com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost/test", "root", ""}
    };
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public Database() {
        conn = getConn(0);
    }

    public Database(int i) {
        connIdx=i-1;
        conn = getConn(i-1);
    }
    public void close()
    {
    	try {
    		conn.close();
    	}catch (Exception e) {
    		
    	}
    }
    public Database(int driver, String server, String database, String usr, String pwd) {

    }

    public static Connection getConn(int idx) {
        try {

            Class.forName(connections[idx][0]);

            //STEP 3: Open a connection
            return DriverManager.getConnection(connections[idx][1], connections[idx][2], connections[idx][3]);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //prepare sql statement
    public PreparedStatement prepare(String spName) {

        String preparedSql = String.format("{call %s}", spName);

        try {
            return getConn(connIdx).prepareStatement(preparedSql);
        } catch (Exception e) {
            return null;
        }
    }

    public CallableStatement prepareCall(String spName) {

        String preparedSql = String.format("{call %s}", spName);

        try {
            return getConn(connIdx).prepareCall(preparedSql);
        } catch (Exception e) {
            // use cstmt here
            return null;
        }
    }

    //put info into mysql
    public int execute(String sql, Object... values) {
        try {
            String preparedSql = String.format("{call %s}", sql);
            PreparedStatement stmt = getConn(connIdx).prepareStatement(sql);
            int i = 0;
            for (Object obj : values) {
                stmt.setObject(++i, obj);
            }
            stmt.execute();

            return 1;
        } catch (Exception e) {
            //use cstmt here
        	System.err.println(e);
            return -1;
        }
    }

    //get info from sql
    public ResultSet query(String query) {
        try {
            Statement stmt = getConn(connIdx).createStatement();

            resultSet = stmt.executeQuery(query);
            return resultSet;
        } catch (Exception e) {
            // use cstmt here
            e.printStackTrace();
            return null;
        }
    }

    //go to next sql
    public boolean next() {
        try {
            return resultSet.next();
        } catch (Exception e) {
            return false;
        }
    }

    //get string from sql
    public String getString(int i) {
        try {
            return resultSet.getString(i);
        } catch (Exception e) {
            return null;
        }
    }

    public String getString(String s) {
        try {
            return resultSet.getString(s);
        } catch (Exception e) {
            return null;
        }
    }

    //get int
    public int getInt(int i) {
        try {
            return resultSet.getInt(i);
        } catch (Exception e) {
            return -1;
        }
    }

    public int getInt(String s) {
        try {
            return resultSet.getInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

    //get results
    public ResultSet getResultSet(String query) {
        try {
            Statement stmt = getConn(connIdx).createStatement();

            return stmt.executeQuery(query);
        } catch (Exception e) {
            return null;
        }
    }
    public static int parseInt(String val, int alt)
    {
        try {
            return Integer.parseInt(val);
        }
        catch (Exception e)
        {
            return alt;
        }
    }
}

