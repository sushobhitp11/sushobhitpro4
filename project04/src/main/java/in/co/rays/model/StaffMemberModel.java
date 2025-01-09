package in.co.rays.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import in.co.rays.bean.StaffMemberBean;
import in.co.rays.bean.StaffMemberBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

public class StaffMemberModel {
	
	public Integer nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_staffmember");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);
		}
		JDBCDataSource.closeConnection(conn);

		return pk + 1;
	}
	public void add(StaffMemberBean bean) throws Exception {

		StaffMemberBean existBean = findByFullName(bean.getFullName());

		if (existBean != null) {
			throw new DuplicateRecordException("FullName already exist..!!");
		}
		int pk = nextPk();

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_staffmember values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getFullName());
		pstmt.setDate(3, new java.sql.Date(bean.getJoiningDate().getTime()));
		pstmt.setString(4, bean.getDivision());
		pstmt.setString(5, bean.getPreviousEmployer());
		pstmt.setString(6, bean.getCreatedBy());
		pstmt.setString(7, bean.getModifiedBy());
		pstmt.setTimestamp(8, bean.getCreatedDatetime());
		pstmt.setTimestamp(9, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data inserted => " + i);
	}
	public void update(StaffMemberBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_staffmember set full_name = ?, joining_date = ?, `division` = ?, previous_employer = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getFullName());
		pstmt.setDate(2, new java.sql.Date(bean.getJoiningDate().getTime()));
		pstmt.setString(3, bean.getDivision());
		pstmt.setString(4, bean.getPreviousEmployer());
		pstmt.setString(5, bean.getCreatedBy());
		pstmt.setString(6, bean.getModifiedBy());
		pstmt.setTimestamp(7, bean.getCreatedDatetime());
		pstmt.setTimestamp(8, bean.getModifiedDatetime());
		pstmt.setLong(9, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data updated" + i);

	}
	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_staffmember where id =?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data deleted => " + i);
	}
	public StaffMemberBean findByPk(long id) throws Exception {

		Connection conn = null;
		StaffMemberBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_staffmember where id=?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new StaffMemberBean();
				bean.setId(rs.getLong(1));
				bean.setFullName(rs.getString(2));
				bean.setJoiningDate(rs.getDate(3));
				bean.setDivision(rs.getString(4));
				bean.setPreviousEmployer(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting StaffMember by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}
	private StaffMemberBean findByFullName(String fullName) throws SQLException {
		
		Connection conn = null;
		
		StaffMemberBean bean = null;

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_staffmember where full_name = ?");
			pstmt.setString(1, fullName);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new StaffMemberBean();
				bean.setId(rs.getLong(1));
				bean.setFullName(rs.getString(2));
				bean.setJoiningDate(rs.getDate(3));
				bean.setDivision(rs.getString(4));
				bean.setPreviousEmployer(rs.getString(5));
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
	public List search(StaffMemberBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_staffmember where 1=1");

		if (bean != null) {
			if (bean.getFullName()!= null && bean.getFullName().length() > 0) {
				sql.append(" and full_name like '" + bean.getFullName() + "%'");
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
				bean = new StaffMemberBean();
				bean.setId(rs.getLong(1));
				bean.setFullName(rs.getString(2));
				bean.setJoiningDate(rs.getDate(3));
				bean.setDivision(rs.getString(4));
				bean.setPreviousEmployer(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search task " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
