package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.PositionBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

public class PositionModel {

	public Integer nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_position");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);
		}
		JDBCDataSource.closeConnection(conn);

		return pk + 1;

	}

	public void add(PositionBean bean) throws Exception {

		Connection conn = null;

		int pk = nextPk();

		PositionBean existBean = findBydesignation(bean.getDesignation());

		if (existBean != null) {
			throw new DuplicateRecordException("designation already exist..!!");
		}
		try {
			pk = nextPk();

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_position values(?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getDesignation());
			pstmt.setDate(3, new java.sql.Date(bean.getOpeningDate().getTime()));
			pstmt.setString(4, bean.getReqiredExperience());
			pstmt.setString(5, bean.getCondition());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println(" data inserted => " + i);

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception: add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception: Exception in add Position " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(PositionBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update st_position set designation = ?, opening_date = ?, required_experience = ?, `condition` = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
		pstmt.setString(1, bean.getDesignation());
		pstmt.setDate(2, new java.sql.Date(bean.getOpeningDate().getTime()));
		pstmt.setString(3, bean.getReqiredExperience());
		pstmt.setString(4, bean.getCondition());
		pstmt.setString(5, bean.getCreatedBy());
		pstmt.setString(6, bean.getModifiedBy());
		pstmt.setTimestamp(7, bean.getCreatedDatetime());
		pstmt.setTimestamp(8, bean.getModifiedDatetime());
		pstmt.setLong(9, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data updated" + i);

	}

	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_position where id =?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data deleted => " + i);
	}

	public PositionBean findByPk(long id) throws Exception {

		Connection conn = null;
		PositionBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_position where id=?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new PositionBean();
				bean.setId(rs.getLong(1));
				bean.setDesignation(rs.getString(2));
				bean.setOpeningDate(rs.getDate(3));
				bean.setReqiredExperience(rs.getString(4));
				bean.setCondition(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Position by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	public PositionBean findBydesignation(String designation) throws Exception {
		Connection conn = null;
		PositionBean bean = null;

		conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt;
		
			pstmt = conn.prepareStatement("select * from st_position where designation=?");

			pstmt.setString(1, designation);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new PositionBean();
				bean.setId(rs.getLong(1));
				bean.setDesignation(rs.getString(2));
				bean.setOpeningDate(rs.getDate(3));
				bean.setReqiredExperience(rs.getString(4));
				bean.setCondition(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

			}
		
			JDBCDataSource.closeConnection(conn);
		return bean;
		

	}

	public List list() throws Exception {
		return search(null, 0, 0);
	}

	public List search(PositionBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_position where 1=1");

		if (bean != null) {
			if (bean.getDesignation() != null && bean.getDesignation().length() > 0) {
				sql.append(" and designation like '" + bean.getDesignation() + "%'");
			}
			if (bean.getOpeningDate() != null && bean.getOpeningDate().getTime() > 0) {
				sql.append(" and opening_Date like '" + new java.sql.Date(bean.getOpeningDate().getTime()) + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql ==>> " + sql.toString());

		Connection conn = null;
		List list = new ArrayList();

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new PositionBean();
				bean.setId(rs.getLong(1));
				bean.setId(rs.getLong(1));
				bean.setDesignation(rs.getString(2));
				bean.setOpeningDate(rs.getDate(3));
				bean.setReqiredExperience(rs.getString(4));
				bean.setCondition(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search position " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
