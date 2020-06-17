package package1;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.sql.*;


public  class DataProcessing {
	static Connection connection;
	static Statement statement;
	static ResultSet rs;
	
	private static String driverName="com.mysql.jdbc.Driver";               // 加载数据库驱动类
    private static String url="jdbc:mysql://localhost:3306/document";       // 声明数据库的URL
    private static String user="root";                                      // 数据库用户
    private static String password="123456";
    
	private static boolean connectToDB=false;
	static Hashtable<String, User> users;
	static Hashtable<String, Doc> docs;
	

	static {
		users = new Hashtable<String, User>();
		users.put("jack", new Operator("jack","123","operator"));
		users.put("rose", new Browser("rose","123","browser"));
		users.put("kate", new Administrator("kate","123","administrator"));
		Init();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		docs = new Hashtable<String,Doc>();
		docs.put("0001",new Doc("0001","jack",timestamp,"Doc Source Java","Doc.java"));
		
		
	}
	
	public static  void Init(){
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement( 
			         ResultSet.TYPE_SCROLL_INSENSITIVE,
			         ResultSet.CONCUR_READ_ONLY );
		} catch (ClassNotFoundException e) {
			System.out.println("数据驱动错误");
		} catch (SQLException e) {
			System.out.println("数据库错误");
		}
		
		// connect to database
		
		// update database connection status
//		if (Math.random()>0.2)
//			connectToDB = true;
//		else
//			connectToDB = false;
	}
	public static void End()
	{
		                      
        try {
        	rs.close();  
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}                        
	}
	
	public static Doc searchDoc(String ID) throws SQLException {
		/*
		if (docs.containsKey(ID)) {
			Doc temp =docs.get(ID);
			return temp;
		}
		return null;
		*/
		Init();
		String sql = "select * from doc_info";
		rs=statement.executeQuery(sql);
		Doc temp = null;
		while(rs.next())
		{
			String iden = rs.getString("id");
			if(iden.equals(ID))
			{
				String creator=rs.getString("creator");
				String description=rs.getString("description");
				Timestamp timestamp = rs.getTimestamp("timestamp");
				String filename=rs.getString("filename");
				temp=new Doc(ID, creator, timestamp,description,filename);
				return temp;
			}
		}
		return null;
	}
	
	public static Enumeration<Doc> getAllDocs() throws SQLException{		
		//Enumeration<Doc> e  = docs.elements();
		Enumeration<Doc> e = null;
		Vector<Doc> Docs = new Vector<Doc>();
		Init();
		String sql = "select * from doc_info";
		rs=statement.executeQuery(sql);
		while(rs.next())
		{
			String ID = rs.getString("id");
			String creator = rs.getString("creator");
			Timestamp timestamp= rs.getTimestamp("timestamp");
			String description = rs.getString("description");
			String filename = rs.getString("filename");
			Doc temp = new Doc(ID, creator, timestamp,description,filename);
			Docs.addElement(temp);
		}
		End();
		e=Docs.elements();
		return e;
	} 
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
		/*
		Doc doc;		
		if (docs.containsKey(ID))
			return false;
		else{
			doc = new Doc(ID,creator,timestamp,description,filename);
			docs.put(ID, doc);
			return true;
		}
		*/
		Init();
		Doc temp = new Doc(ID, creator, timestamp,description,filename);
		String sql="insert into doc_info (Id,creator,timestamp,description,filename) values ('"+ID+"','"+creator+"', '"+timestamp+"',  '"+description+"','"+filename+"')";
		int rs = statement.executeUpdate(sql);
		return true;
	} 
	
	public static User searchUser(String name) throws SQLException{
//		if ( !connectToDB ) 
//			throw new SQLException( "Not Connected to Database" );
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
		Init();
		String sql="select * from user_info";
		rs = statement.executeQuery(sql);
		while(rs.next())
		{
			if(name.equals(rs.getString("username")))
			{
				String role = rs.getString("role");
				User temp;
				if(role.equals("administrator"))
					temp =new Administrator(name,password,role);
				else if (role.equals("operator"))
					temp = new Operator(name,password,role);
				else
					temp = new Browser(name,password,role);
				return temp;
			}
		}
		End();
		return null;
		/*
		if (users.containsKey(name)) {
			return users.get(name);			
		}
		return null;
		*/
	}
	
	public static User searchUser(String name, String password) throws SQLException {
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
		Init();
		String sql="select * from user_info";
		rs = statement.executeQuery(sql);
		while(rs.next())
		{
			if(name.equals(rs.getString("username")) && password.equals(rs.getString("password")))
			{
				String role = rs.getString("role");
				User temp;
				if(role.equals("administrator"))
					temp =new Administrator(name,password,role);
				else if (role.equals("operator"))
					temp = new Operator(name,password,role);
				else
					temp = new Browser(name,password,role);
				return temp;
			}
		}
		End();
		return null;
		
		/*
		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
		*/
	}
	
	public static Enumeration<User> getAllUser() throws SQLException{
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
		Init();
		Enumeration<User> e  = null;
		Vector<User> Users = new Vector<User>();
		String sql = "select * from user_info";
		rs = statement.executeQuery(sql);
		while(rs.next())
		{
			User temp = null;
			String role = rs.getString("role");
			String password = rs.getString("password");
			String name = rs.getString("username");
			if(role.equals("administrator"))
				temp =new Administrator(name,password,role);
			else if (role.equals("operator"))
				temp = new Operator(name,password,role);
			else
				temp = new Browser(name,password,role);
			Users.add(temp);
		}
		End();
		e=Users.elements();
		return e;
	}
	
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException{
		User user;
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Update" );
		Init();
		String sql="select * from user_info";
		rs = statement.executeQuery(sql);
		while(rs.next())
		{
			if(name.equals(rs.getString("username")))
			{
				String opwd=rs.getString("password");
				String orole = rs.getString("role");
				sql = "UPDATE user_info SET username = '"+name+"', role = '"+role+"', password = '"+password+"' WHERE username = '"+rs.getString("username")+"' ";
				statement.execute(sql);
				End();
				return true;
			}
		}
		return false;
		/*
		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}else
			return false;
		*/
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException{
		Init();
		String sql="replace INTO user_info (username,password,role) VALUES ('"+name+"','"+password+"','"+role+"')";
		statement.execute(sql);
		End();
		return true;
//		User user;
		
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Insert" );
		/*
		if (users.containsKey(name))
			return false;
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}
		*/
	}
	
	public static boolean deleteUser(String name) throws SQLException{
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Delete" );
		Init();
		String sql = "DELETE FROM user_info WHERE username = '"+name+"'";
		statement.execute(sql);
		End();
		return true;
		/*
		if (users.containsKey(name)){
			users.remove(name);
			return true;
		}else
			return false;
		*/
		
	}	
            
	


	
}
