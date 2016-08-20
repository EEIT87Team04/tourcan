//package com.tourcan.att.model;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//@SuppressWarnings("unused")
//public class AttDAO_JDBC implements AttDAO_interface {
//
//	// Ver.JDBC
//	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private static final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Tourcan";
//	private static final String userid = "sa";
//	private static final String passwd = "sa123456";
//
//	private static int STMT_SHIFT = 1; // 1 for JDBC, 0 for Hibernate
//
//	// SQL statements
//	private static final String INSERT_STMT = "INSERT INTO att ( "
//			+ "att_name, att_staytime, region_id, att_addr, att_price, att_phone, att_url, att_eat, att_intro, att_open, att_lat, att_lng ) "
//			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
//	private static final String GET_ALL_STMT = "SELECT "
//			+ "att_name, att_staytime, region_id, att_addr, att_price, att_phone, att_url, att_eat, att_intro, att_open, att_lat, att_lng, att_id "
//			+ "FROM att order by att_id";
//	private static final String GET_BY_ID_STMT = "SELECT "
//			+ "att_name, att_staytime, region_id, att_addr, att_price, att_phone, att_url, att_eat, att_intro, att_open, att_lat, att_lng, att_id "
//			+ "FROM att where att_id = ?";
//	private static final String GET_BY_NAME_STMT = "SELECT "
//			+ "att_name, att_staytime, region_id, att_addr, att_price, att_phone, att_url, att_eat, att_intro, att_open, att_lat, att_lng, att_id "
//			+ "FROM att where att_name like ?";
//	private static final String DELETE_STMT = "DELETE FROM att where att_id = ?";
//	private static final String UPDATE_STMT = "UPDATE att set "
//			+ "att_name=?, att_staytime=?, region_id=?, att_addr=?, att_price=?, att_phone=?, att_url=?, att_eat=?, att_intro=?, att_open=?, att_lat=?, att_lng=? "
//			+ "where att_id = ?";
//
//	@Override
//	public void insert(AttVO attVO) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userid, passwd);
//			pstmt = conn.prepareStatement(INSERT_STMT);
//
//			// pstmt.setInt(0 + STMT_SHIFT, attVO.getAtt_area());
//			pstmt.setString(0 + STMT_SHIFT, attVO.getAtt_name());
//			pstmt.setInt(1 + STMT_SHIFT, attVO.getAtt_staytime());
//			pstmt.setInt(2 + STMT_SHIFT, attVO.getRegion_id());
//			pstmt.setString(3 + STMT_SHIFT, attVO.getAtt_addr());
//			pstmt.setObject(4 + STMT_SHIFT, attVO.getAtt_price());
//			pstmt.setString(5 + STMT_SHIFT, attVO.getAtt_phone());
//			pstmt.setString(6 + STMT_SHIFT, attVO.getAtt_url());
//			pstmt.setBoolean(7 + STMT_SHIFT, attVO.getAtt_eat());
//			pstmt.setString(8 + STMT_SHIFT, attVO.getAtt_intro());
//			pstmt.setString(9 + STMT_SHIFT, attVO.getAtt_open());
//			pstmt.setDouble(10 + STMT_SHIFT, attVO.getAtt_lat());
//			pstmt.setDouble(11 + STMT_SHIFT, attVO.getAtt_lng());
//			// pstmt.setInt(12 + STMT_SHIFT, attVO.getAtt_id());
//
//			pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	@Override
//	public void update(AttVO attVO) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userid, passwd);
//			pstmt = conn.prepareStatement(UPDATE_STMT);
//
//			// pstmt.setInt(0 + STMT_SHIFT, attVO.getAtt_area());
//			pstmt.setString(0 + STMT_SHIFT, attVO.getAtt_name());
//			pstmt.setInt(1 + STMT_SHIFT, attVO.getAtt_staytime());
//			pstmt.setInt(2 + STMT_SHIFT, attVO.getRegion_id());
//			pstmt.setString(3 + STMT_SHIFT, attVO.getAtt_addr());
//			pstmt.setDouble(4 + STMT_SHIFT, attVO.getAtt_price());
//			pstmt.setString(5 + STMT_SHIFT, attVO.getAtt_phone());
//			pstmt.setString(6 + STMT_SHIFT, attVO.getAtt_url());
//			pstmt.setBoolean(7 + STMT_SHIFT, attVO.getAtt_eat());
//			pstmt.setString(8 + STMT_SHIFT, attVO.getAtt_intro());
//			pstmt.setString(9 + STMT_SHIFT, attVO.getAtt_open());
//			pstmt.setDouble(10 + STMT_SHIFT, attVO.getAtt_lat());
//			pstmt.setDouble(11 + STMT_SHIFT, attVO.getAtt_lng());
//			pstmt.setInt(12 + STMT_SHIFT, attVO.getAtt_id());
//
//			pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	@Override
//	public void delete(AttVO attVO) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userid, passwd);
//			pstmt = conn.prepareStatement(DELETE_STMT);
//
//			pstmt.setInt(0 + STMT_SHIFT, attVO.getAtt_id());
//
//			pstmt.executeUpdate();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	@Override
//	public AttVO findById(Integer att_id) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		AttVO att = null;
//
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userid, passwd);
//
//			// Map<String, Class<?>> map = conn.getTypeMap();
//			// System.out.println("test start: we have " + map.size());
//			// for (String key : map.keySet())
//			// System.out.println(key + '\t' + map.get(key).getName());
//			// System.out.println("test end");
//
//			pstmt = conn.prepareStatement(GET_BY_ID_STMT);
//			pstmt.setInt(0 + STMT_SHIFT, att_id);
//			ResultSet rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				// rs.getMetaData();
//				Object dat = null;
//				att = new AttVO();
//
//				// att_area,att_name,att_staytime,region_id,att_addr,att_price,
//				// dat = ((dat = rs.getString("att_area")) == null) ? null :
//				// Integer.valueOf((String) dat);
//				// att.setAtt_area((Integer) dat);
//
//				att.setAtt_name(rs.getString("att_name"));
//
//				dat = ((dat = rs.getString("att_staytime")) == null) ? null : Integer.valueOf((String) dat);
//				att.setAtt_staytime((Integer) dat);
//
//				dat = ((dat = rs.getString("region_id")) == null) ? null : Integer.valueOf((String) dat);
//				att.setRegion_id((Integer) dat);
//
//				att.setAtt_addr(rs.getString("att_addr"));
//
//				dat = ((dat = rs.getString("att_price")) == null) ? null : Double.valueOf((String) dat);
//				att.setAtt_price((Double) dat);
//
//				// att_phone, att_url, att_eat, att_intro, att_open, att_id
//				att.setAtt_phone(rs.getString("att_phone"));
//
//				att.setAtt_url(rs.getString("att_url"));
//
//				att.setAtt_eat(rs.getInt("att_eat") == 1);
//
//				att.setAtt_intro(rs.getString("att_intro"));
//
//				att.setAtt_open(rs.getString("att_open"));
//
//				att.setAtt_lat(rs.getDouble("att_latt"));
//
//				att.setAtt_lng(rs.getDouble("att_lng"));
//
//				dat = ((dat = rs.getString("att_id")) == null) ? null : Integer.valueOf((String) dat);
//				att.setAtt_id((Integer) dat);
//			}
//
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return att;
//	}
//
//	@Override
//	public List<AttVO> findByName(String att_name) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		List<AttVO> atts = new ArrayList<AttVO>();
//
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userid, passwd);
//
//			pstmt = conn.prepareStatement(GET_BY_NAME_STMT);
//			pstmt.setString(0 + STMT_SHIFT, att_name);
//			ResultSet rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// rs.getMetaData();
//				Object dat = null;
//				AttVO att = new AttVO();
//
//				// att_area,att_name,att_staytime,region_id,att_addr,att_price,
//				// dat = ((dat = rs.getString("att_area")) == null) ? null :
//				// Integer.valueOf((String) dat);
//				// att.setAtt_area((Integer) dat);
//
//				att.setAtt_name(rs.getString("att_name"));
//
//				dat = ((dat = rs.getString("att_staytime")) == null) ? null : Integer.valueOf((String) dat);
//				att.setAtt_staytime((Integer) dat);
//
//				dat = ((dat = rs.getString("region_id")) == null) ? null : Integer.valueOf((String) dat);
//				att.setRegion_id((Integer) dat);
//
//				att.setAtt_addr(rs.getString("att_addr"));
//
//				dat = ((dat = rs.getString("att_price")) == null) ? null : Double.valueOf((String) dat);
//				att.setAtt_price((Double) dat);
//
//				// att_phone, att_url, att_eat, att_intro, att_open, att_id
//				att.setAtt_phone(rs.getString("att_phone"));
//
//				att.setAtt_url(rs.getString("att_url"));
//
//				att.setAtt_eat(rs.getInt("att_eat") == 1);
//
//				att.setAtt_intro(rs.getString("att_intro"));
//
//				att.setAtt_open(rs.getString("att_open"));
//
//				att.setAtt_lat(rs.getDouble("att_latt"));
//
//				att.setAtt_lng(rs.getDouble("att_lng"));
//
//				dat = ((dat = rs.getString("att_id")) == null) ? null : Integer.valueOf((String) dat);
//				att.setAtt_id((Integer) dat);
//
//				atts.add(att);
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return atts;
//	}
//
//	@Override
//	public List<AttVO> getAll() {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		List<AttVO> atts = new ArrayList<AttVO>();
//
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userid, passwd);
//
//			pstmt = conn.prepareStatement(GET_ALL_STMT);
//			ResultSet rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// rs.getMetaData();
//				Object dat = null;
//				AttVO att = new AttVO();
//
//				// att_area,att_name,att_staytime,region_id,att_addr,att_price,
//				// dat = ((dat = rs.getString("att_area")) == null) ? null :
//				// Integer.valueOf((String) dat);
//				// att.setAtt_area((Integer) dat);
//
//				att.setAtt_name(rs.getString("att_name"));
//
//				dat = ((dat = rs.getString("att_staytime")) == null) ? null : Integer.valueOf((String) dat);
//				att.setAtt_staytime((Integer) dat);
//
//				dat = ((dat = rs.getString("region_id")) == null) ? null : Integer.valueOf((String) dat);
//				att.setRegion_id((Integer) dat);
//
//				att.setAtt_addr(rs.getString("att_addr"));
//
//				dat = ((dat = rs.getString("att_price")) == null) ? null : Double.valueOf((String) dat);
//				att.setAtt_price((Double) dat);
//
//				// att_phone, att_url, att_eat, att_intro, att_open, att_id
//				att.setAtt_phone(rs.getString("att_phone"));
//
//				att.setAtt_url(rs.getString("att_url"));
//
//				att.setAtt_eat(rs.getInt("att_eat") == 1);
//
//				att.setAtt_intro(rs.getString("att_intro"));
//
//				att.setAtt_open(rs.getString("att_open"));
//
//				att.setAtt_lat(rs.getDouble("att_latt"));
//
//				att.setAtt_lng(rs.getDouble("att_lng"));
//
//				dat = ((dat = rs.getString("att_id")) == null) ? null : Integer.valueOf((String) dat);
//				att.setAtt_id((Integer) dat);
//
//				atts.add(att);
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return atts;
//	}
//
//	public static void main(String[] args) {
//		// findByIdTest();
//		// findByNameTest();
//		getAllTest();
//		// dumpFileToDB();
//	}
//
//	private static void findByIdTest() {
//		AttDAO dao = new AttDAO();
//		for (int id : new int[] { 1196, 1198, 1219, 1242, 1254 }) {
//			AttVO att = dao.findById(id);
//			System.out.println("--------------" + att.getAtt_id() + "--------------");
//			System.out.println(att.getAtt_name());
//			System.out.println(att.getAtt_phone());
//			System.out.println(att.getAtt_price());
//			System.out.println(att.getAtt_eat() ? "吃貨です" : "吃貨じゃない");
//			System.out.println(att.getAtt_url());
//			System.out.println(att.getAtt_intro());
//		}
//
//	}
//
//	private static void findByNameTest() {
//		AttDAO dao = new AttDAO();
//		for (AttVO att : dao.findByName("%清境%")) {
//			System.out.println("--------------" + att.getAtt_id() + "--------------");
//			System.out.println(att.getAtt_name());
//			System.out.println(att.getAtt_phone());
//			System.out.println(att.getAtt_price());
//			System.out.println(att.getAtt_eat() ? "吃貨です" : "吃貨じゃない");
//			System.out.println(att.getAtt_url());
//			System.out.println(att.getAtt_intro());
//		}
//	}
//
//	private static void getAllTest() {
//		AttDAO dao = new AttDAO();
//		for (AttVO att : dao.getAll()) {
//			System.out.println("--------------" + att.getAtt_id() + "--------------");
//			System.out.println(att.getAtt_name());
//			System.out.println(att.getAtt_phone());
//			System.out.println(att.getAtt_price());
//			System.out.println(att.getAtt_eat() ? "吃貨です" : "吃貨じゃない");
//			System.out.println(att.getAtt_url());
//			System.out.println(att.getAtt_intro());
//		}
//	}
//
//	private static void dumpFileToDB() {
//		AttDAO dao = new AttDAO();
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		InputStream is = null;
//		InputStreamReader isr = null;
//		BufferedReader br = null;
//
//		String line = null;
//		int count = 0;
//
//		try {
//			Class.forName(driver);
//			conn = DriverManager.getConnection(url, userid, passwd);
//			pstmt = conn.prepareStatement(INSERT_STMT);
//
//			is = new FileInputStream("E:/att.csv");
//			isr = new InputStreamReader(is);
//			br = new BufferedReader(isr);
//
//			conn.setAutoCommit(false);
//			while ((line = br.readLine()) != null) {
//				if (count++ < 1)
//					continue;
//				if (count > 72)
//					break;
//
//				// System.out.println(line);
//				String[] vars = line.concat(",EOF").split(",");
//				AttVO att = new AttVO();
//				for (int i = 0; i < vars.length; i++) {
//					vars[i] = vars[i].trim();
//					vars[i] = "".equalsIgnoreCase(vars[i]) ? null : vars[i];
//					vars[i] = "null".equalsIgnoreCase(vars[i]) ? null : vars[i];
//				}
//				// System.out.print(vars.length);
//				// System.out.print('\n');
//				// att.setAtt_area(vars[0] == null ? null :
//				// Integer.valueOf(vars[0]));
//				att.setAtt_name(vars[1] == null ? null : String.valueOf(vars[1]));
//				// att.setAtt_id(Integer.valueOf(vars[2]));
//				att.setAtt_staytime(vars[3] == null ? null : Integer.valueOf(vars[3]));
//				att.setRegion_id(vars[4] == null ? null : Integer.valueOf(vars[4]));
//				att.setAtt_addr(String.valueOf(vars[5]));
//				att.setAtt_price(vars[6] == null ? null : Double.valueOf(vars[6]));
//				att.setAtt_phone(vars[7] == null ? null : String.valueOf(vars[7]));
//				att.setAtt_url(vars[8] == null ? null : String.valueOf(vars[8]));
//				att.setAtt_eat(Boolean.valueOf(vars[9]));
//				att.setAtt_intro(vars[10] == null ? null : String.valueOf(vars[10]));
//				att.setAtt_open(vars[11] == null ? null : String.valueOf(vars[11]));
//
//				dao.insert(att);
//			}
//			pstmt.executeBatch();
//			conn.commit();
//
//		} catch (Throwable e) {
//			e.printStackTrace();
//			System.out.println("ERROR @ count = " + count);
//			System.out.println(line);
//		} finally {
//			try {
//				if (pstmt != null)
//					pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (br != null)
//					br.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (isr != null)
//					isr.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (is != null)
//					is.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
