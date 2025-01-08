package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CustomerBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

public class CustomerModel {

	public Integer nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_customer");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);
		}
		JDBCDataSource.closeConnection(conn);

		return pk + 1;
	}

	public void add(CustomerBean bean) throws Exception {

		CustomerBean existBean = findByClientName(bean.getClientName());

		if (existBean != null) {
			throw new DuplicateRecordException("ClientName already exist..!!");
		}
		int pk = nextPk();

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_customer values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getClientName());
		pstmt.setString(3, bean.getLocation());
		pstmt.setString(4, bean.getContactNumber());
		pstmt.setString(5, bean.getImportance());
		pstmt.setString(6, bean.getCreatedBy());
		pstmt.setString(7, bean.getModifiedBy());
		pstmt.setTimestamp(8, bean.getCreatedDatetime());
		pstmt.setTimestamp(9, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data inserted => " + i);
	}

	public void update(CustomerBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_CustomerBean set client_name = ?, location = ?, `contact_number` = ?, importance = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getClientName());
		pstmt.setString(2, bean.getLocation());
		pstmt.setString(3, bean.getContactNumber());
		pstmt.setString(4, bean.getImportance());
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

		PreparedStatement pstmt = conn.prepareStatement("delete from st_customer where id =?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data deleted => " + i);
	}

	public CustomerBean findByPk(long id) throws Exception {

		Connection conn = null;
		CustomerBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_customer where id=?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CustomerBean();
				bean.setId(rs.getLong(1));
				bean.setClientName(rs.getString(2));
				bean.setLocation(rs.getString(3));
				bean.setContactNumber(rs.getString(4));
				bean.setImportance(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Customer by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	private CustomerBean findByClientName(String clientName) throws Exception {
		Connection conn = null;
		CustomerBean bean = null;

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_customer where client_name = ?");
			pstmt.setString(1, clientName);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CustomerBean();
				bean.setId(rs.getLong(1));
				bean.setClientName(rs.getString(2));
				bean.setLocation(rs.getString(3));
				bean.setContactNumber(rs.getString(4));
				bean.setImportance(rs.getString(5));
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
	public List search(CustomerBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_customer where 1=1");

		if (bean != null) {
			if (bean.getClientName() != null && bean.getClientName().length() > 0) {
				sql.append(" and client_name like '" + bean.getClientName() + "%'");
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
				bean = new CustomerBean();
				bean.setId(rs.getLong(1));
				bean.setClientName(rs.getString(2));
				bean.setLocation(rs.getString(3));
				bean.setContactNumber(rs.getString(4));
				bean.setImportance(rs.getString(5));
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
