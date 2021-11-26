package register;
/*
 * Address bean for bean to hold address info getters and setters to send
 * and receive from mysql
 * 
 */
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.Database;
public class AddressBean {
	int id;
	String fname;
	String sname;
	String telephone;
	String mobile;
	String email;
	String address;
	String postalnr;
	String town;
	String country;
	String saveStatus;
	public AddressBean()
	{
		
	}
	
	public AddressBean(int id, String fname, String sname, String telephone, String mobile, String email,
			String address, String postalnr, String town, String country) {
		super();
		this.id = id;
		this.fname = fname;
		this.sname = sname;
		this.telephone = telephone;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.postalnr = postalnr;
		this.town = town;
		this.country = country;
	}

	//delete from sql
	public static int delete (int id)
	{
		Database db = new Database();
		
		db.execute("DELETE FROM test.addresses WHERE id = "+id);
		
		db.close();
		return 1;
	}
	
	//get from sql
	public static ArrayList<AddressBean> getAll ()
	{
		ArrayList<AddressBean> list = new ArrayList<AddressBean>();
		Database db = new Database();
		System.out.println ("get all");
		
		try {
			ResultSet rs = db.query("SELECT * FROM test.addresses");
			while (rs.next())
			{
				System.out.println ("name: "+rs.getString(1));
				list.add(new AddressBean (rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
						,rs.getString(7)
						,rs.getString(8)
						,rs.getString(9)
						,rs.getString(10)));
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	//search sql
	public static ArrayList<AddressBean> search (String column, String value)
	{
		ArrayList<AddressBean> list = new ArrayList<AddressBean>();
		Database db = new Database();
		System.out.println ("get all");
		//db.prepare("SELECT * FROM test.addresses");
		String query = String.format("SELECT * FROM test.addresses WHERE %s = '%s'", column, value);
		System.out.println(query);
		try {
			ResultSet rs = db.query(query);
			while (rs.next())
			{
				System.out.println ("name: "+rs.getString(1));
				list.add(new AddressBean (rs.getInt(1), 
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
						,rs.getString(7)
						,rs.getString(8)
						,rs.getString(9)
						,rs.getString(10)));
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	//insert into sql
	public static final String GC_INSERT_INTO = "INSERT INTO `test`.`addresses`\r\n"
			+ "(`fname`,\r\n"
			+ "`sname`,\r\n"
			+ "`telephone`,\r\n"
			+ "`mobile`,\r\n"
			+ "`email`,\r\n"
			+ "`address`,\r\n"
			+ "`postalnr`,\r\n"
			+ "`town`,\r\n"
			+ "`country`)\r\n"
			+ "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s')";
	
	//validate input
	public String validate ()
	{
		String errors=null;
		
		if ("".equals(fname) || fname==null)
		{
			System.out.println ("fname "+fname);
			errors = "fname";
		}
		if ("".equals(email) || email==null)
		{
			if(errors == null)
				errors= "email"; 
			else
				errors+=",email";
		}
		saveStatus = errors;
		
		return errors;
	}
	public String getInsertInto ()
	{
		return String.format(GC_INSERT_INTO, 
				fname,sname,telephone,mobile,email,address,postalnr,town,country);
	}
	
	//add into sql
	public void setSave (String cmd)
	{
		
		Database db = new Database();
		
		int result = db.execute(getInsertInto());
		saveStatus=result==1?"Saved":"Not Saved";
		return;
	}
	
	private static final String TO_STRING ="{fname:'%s',sname: '%s',telephone:'%s',mobile:'%s',email:'%s',"
			+ "address: '%s',postalnr:'%s',town:'%s',country:'%s', errors:'%s'}";
	
	public String toString ()
	{
		return String.format(TO_STRING, 
				fname==null?"":fname,sname,telephone,mobile,
				email==null?"":email,address,postalnr,town,country, saveStatus);
	}
	
	//getters and setters
	public String getSave()
	{
		return saveStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalnr() {
		return postalnr;
	}
	public void setPostalnr(String postalnr) {
		this.postalnr = postalnr;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
