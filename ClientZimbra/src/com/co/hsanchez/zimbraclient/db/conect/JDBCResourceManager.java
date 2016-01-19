package com.co.hsanchez.zimbraclient.db.conect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.co.hsanchez.zimbraclient.io.FileManager;
import com.co.hsanchez.zimbraclient.io.InfoDB;



public class JDBCResourceManager {
	
	private Connection connection;
	
	/**
	 * Basic constructor
	 * @param ds_jndi_name, JNDI Name to get the connection to the database
	 */
	public JDBCResourceManager(){
	}
	
	//-----------------------------------------------------------------
    // Methods
    //-----------------------------------------------------------------

	/**
	 * Gets the connection from OracleDBM. The connection could be taken from the container if file 'test.properties' exists and its value 'test_environment' is true. 
	 * Otherwise the connection must be taken out of container through a direct JDBC connection using the database information
	 * @return connect
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException
    {
//		System.out.println("Obteniendo Conexion..");
		
		if(connection == null || connection.isClosed()){
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			     
			     FileManager fm = new FileManager();
		        InfoDB info = fm.chargeProps();
		        String host = info.getHost();
		        if(info.getPort() != null && info.getPort().length() >1){
		        	host += ":"+info.getPort();
		        }
		        String sqlCon = "jdbc:mysql://"+host+"/"+info.getDb()+"?" +
		                "user="+info.getUser()+"&password="+info.getPassw();
		        if(info.getAdds() != null && info.getAdds().length() >1){
		        	sqlCon += info.getAdds();
		        }
//		    	System.out.println(sqlCon);
		    	
		    	connection = DriverManager.getConnection(sqlCon);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
		}
    	return connection;

    }

	/**
	 * To close a JDBC connection 
	 * @param Connection, The JDBC active connection
	 */
	protected void closeResources(  ){
		try {
			if( connection != null ){
				if(!connection.isClosed( )) {
					connection.close( );
				}
			}
		}catch ( Exception e ) {
			e.printStackTrace( );
		}
	}


}

