package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.util.JDBCDataSource;

public class RoleModel {

	public Integer nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_role");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);
		}
		JDBCDataSource.closeConnection(conn);

		return pk + 1;
	}

	public void add(RoleBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_role values(?,?,?,?,?,?,?)");

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getDescription());
		pstmt.setString(4, bean.getCreatedBy());
		pstmt.setString(5, bean.getModifiedBy());
		pstmt.setTimestamp(6, bean.getCreatedDatetime());
		pstmt.setTimestamp(7, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		System.out.println(" data inserted => " + i);
	}

	public void update(RoleBean bean) throws Exception {

		Connection cnn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = cnn.prepareStatement(
				"update st_role set name=?,description=?,created_By=?,modified_By=?,created_DateTime=?,modified_DateTime=? WHERE id=?");

		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getDescription());
		pstmt.setString(3, bean.getCreatedBy());
		pstmt.setString(4, bean.getModifiedBy());
		pstmt.setTimestamp(5, bean.getCreatedDatetime());
		pstmt.setTimestamp(6, bean.getModifiedDatetime());
		pstmt.setLong(7, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data updated => " + i);

	}

	public void delete(RoleBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_role where id =?");
		pstmt.setLong(1, bean.getId());
		pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);
	}

	public RoleBean findByPK(long pk) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_role where ID=?");

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setLong(1, pk);
		ResultSet rs = pstmt.executeQuery();

		RoleBean bean = null;
		while (rs.next()) {
			bean = new RoleBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDatetime(rs.getTimestamp(6));
			bean.setModifiedDatetime(rs.getTimestamp(7));

			JDBCDataSource.closeConnection(conn);

		}
		return bean;

	}
	public RoleBean findByName(String name) throws Exception {


		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from st_role where name =?");
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();

		RoleBean bean = null;
		while (rs.next()) {
			bean = new RoleBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDatetime(rs.getTimestamp(6));
			bean.setModifiedDatetime(rs.getTimestamp(7));

			JDBCDataSource.closeConnection(conn);

		}
		return bean;
	}	

	public List search(RoleBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_role where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION like '" + bean.getDescription() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		ArrayList<RoleBean> list = new ArrayList<RoleBean>();

		while (rs.next()) {
			bean = new RoleBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDatetime(rs.getTimestamp(6));
			bean.setModifiedDatetime(rs.getTimestamp(7));
			list.add(bean);

		}
		return list;
	}
}
