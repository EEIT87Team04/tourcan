package com.tourcan.region.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionJdbcDAO implements RegionDAO {

	// Ver.JDBC
	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Tourcan";
	private static final String userid = "sa";
	private static final String passwd = "P@ssw0rd";

	private static int STMT_SHIFT = 1; // 1 for JDBC, 0 for Hibernate
	// private boolean manual_mode = false;
//	private Connection conn = null;
//	private PreparedStatement pstmt = null;

	// SQL statements
	private static final String INSERT_STMT = "INSERT INTO region ( " + "region_name, region_area ) "
			+ "VALUES ( ?, ? )";
	private static final String GET_ALL_STMT = "SELECT " + "region_name, region_area, region_id "
			+ "FROM region order by region_id";
	private static final String GET_BY_ID_STMT = "SELECT " + "region_name, region_area, region_id "
			+ "FROM region where region_id = ?";
	private static final String GET_BY_AREA_STMT = "SELECT " + "region_name, region_area, region_id "
			+ "FROM region where region_area = ?";
	private static final String GET_BY_NAME_STMT = "SELECT " + "region_name, region_area, region_id "
			+ "FROM region where region_name like ?";
	private static final String DELETE_STMT = "DELETE FROM region where region_id = ?";
	private static final String UPDATE_STMT = "UPDATE region set " + "region_name=?, region_area=? "
			+ "where region_id = ?";

	@Override
	public void insert(RegionVO regionVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setString(0 + STMT_SHIFT, regionVO.getRegion_name());
			pstmt.setInt(1 + STMT_SHIFT, regionVO.getRegion_area());
			// pstmt.setInt(2 + STMT_SHIFT, regionVO.getRegion_id());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(RegionVO regionVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(UPDATE_STMT);

			pstmt.setString(0 + STMT_SHIFT, regionVO.getRegion_name());
			pstmt.setInt(1 + STMT_SHIFT, regionVO.getRegion_area());
			pstmt.setInt(2 + STMT_SHIFT, regionVO.getRegion_id());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(RegionVO regionVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(DELETE_STMT);

			pstmt.setInt(0 + STMT_SHIFT, regionVO.getRegion_id());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public RegionVO findById(Integer region_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		RegionVO region = null;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);

			pstmt = conn.prepareStatement(GET_BY_ID_STMT);
			pstmt.setInt(0 + STMT_SHIFT, region_id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				// rs.getMetaData();
				region = new RegionVO();

				region.setRegion_name(rs.getString("region_name"));

				region.setRegion_area(rs.getInt("region_area"));
				;

				region.setRegion_id(rs.getInt("region_id"));
				;

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return region;
	}

	@Override
	public List<RegionVO> findByArea(Integer region_area) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<RegionVO> regions = new ArrayList<RegionVO>();

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);

			pstmt = conn.prepareStatement(GET_BY_AREA_STMT);
			pstmt.setInt(0 + STMT_SHIFT, region_area);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// rs.getMetaData();
				RegionVO region = new RegionVO();

				region.setRegion_name(rs.getString("region_name"));

				region.setRegion_area(rs.getInt("region_area"));
				;

				region.setRegion_id(rs.getInt("region_id"));
				;

				regions.add(region);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return regions;
	}

	@Override
	public List<RegionVO> findByName(String region_name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<RegionVO> regions = new ArrayList<RegionVO>();

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);

			pstmt = conn.prepareStatement(GET_BY_NAME_STMT);
			pstmt.setString(0 + STMT_SHIFT, region_name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// rs.getMetaData();
				RegionVO region = new RegionVO();

				region.setRegion_name(rs.getString("region_name"));

				region.setRegion_area(rs.getInt("region_area"));
				;

				region.setRegion_id(rs.getInt("region_id"));
				;

				regions.add(region);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return regions;
	}

	@Override
	public List<RegionVO> getAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<RegionVO> regions = new ArrayList<RegionVO>();

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);

			pstmt = conn.prepareStatement(GET_ALL_STMT);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// rs.getMetaData();
				RegionVO region = new RegionVO();

				region.setRegion_name(rs.getString("region_name"));

				region.setRegion_area(rs.getInt("region_area"));
				;

				region.setRegion_id(rs.getInt("region_id"));
				;

				regions.add(region);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return regions;
	}

	public static void main(String[] args) {
		for (RegionVO att : new RegionJdbcDAO().getAll()) {
			System.out.println(att.getRegion_id() + ": " + att.getRegion_name() + " @ " + att.getRegion_area());
		}
	}

}
