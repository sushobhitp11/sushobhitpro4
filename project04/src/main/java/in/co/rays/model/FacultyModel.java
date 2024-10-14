package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.bean.FacultyBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

public class FacultyModel {
	
	public Integer nextPk() throws Exception {
		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_faculty");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		JDBCDataSource.closeConnection(conn);
		return pk + 1;
	}

	public void add(FacultyBean bean) throws Exception {

		CollegeModel collegeModel = new CollegeModel();

		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());

		bean.setCollegeName(collegeBean.getName());

		CourseModel courseModel = new CourseModel();

		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());

		bean.setCourseName(courseBean.getName());

		SubjectModel subjectModel = new SubjectModel();

		SubjectBean subjectBean = subjectModel.findByPk(bean.getSubjectId());

		bean.setSubjectName(subjectBean.getName());

		int pk = nextPk();

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn
				.prepareStatement("insert into st_faculty values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getFirstName());
		pstmt.setString(3, bean.getLastName());
		pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(5, bean.getGender());
		pstmt.setString(6, bean.getMobileNo());
		pstmt.setString(7, bean.getEmailId());
		pstmt.setLong(8, bean.getCollegeId());
		pstmt.setString(9, bean.getCollegeName());
		pstmt.setLong(10, bean.getCourseId());
		pstmt.setString(11, bean.getCourseName());
		pstmt.setLong(12, bean.getSubjectId());
		pstmt.setString(13, bean.getSubjectName());
		pstmt.setString(14, bean.getCreatedBy());
		pstmt.setString(15, bean.getModifiedBy());
		pstmt.setTimestamp(16, bean.getCreatedDatetime());
		pstmt.setTimestamp(17, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data inserted => " + i);
	}

	public void update(FacultyBean bean) throws Exception {
		
		CollegeModel collegeModel = new CollegeModel();

		CollegeBean collegeBean = collegeModel.findByPk(bean.getCollegeId());

		bean.setCollegeName(collegeBean.getName());

		CourseModel courseModel = new CourseModel();

		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());

		bean.setCourseName(courseBean.getName());

		SubjectModel subjectModel = new SubjectModel();

		SubjectBean subjectBean = subjectModel.findByPk(bean.getSubjectId());

		bean.setSubjectName(subjectBean.getName());

		FacultyBean existBean = findByPk(bean.getId());

		if (existBean != null && bean.getId() != existBean.getId()) {
			throw new DuplicateRecordException("login already exist..!!");
		}

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_faculty set first_name = ?, last_name = ?, dob = ?, gender = ?, email_id = ?, college_id = ?, college_name = ?, course_id = ?, course_name = ?, subject_id = ?, subject_name = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ?  where id = ?");

		
		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
		pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(4, bean.getGender());
		pstmt.setString(5, bean.getMobileNo());
		pstmt.setString(6, bean.getEmailId());
		pstmt.setLong(7, bean.getCollegeId());
		pstmt.setString(8, bean.getCollegeName());
		pstmt.setLong(9, bean.getCourseId());
		pstmt.setString(10, bean.getCourseName());
		pstmt.setLong(11, bean.getSubjectId());
		pstmt.setString(12, bean.getSubjectName());
		pstmt.setString(13, bean.getCreatedBy());
		pstmt.setString(14, bean.getModifiedBy());
		pstmt.setTimestamp(15, bean.getCreatedDatetime());
		pstmt.setTimestamp(16, bean.getModifiedDatetime());
		pstmt.setLong(17, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data updated => " + i);

	}

	public void delete(FacultyBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_faculty where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data deleted => " + i);

	}

	public FacultyBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty where id = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		FacultyBean bean = null;

		while (rs.next()) {
			bean = new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(6));
			bean.setGender(rs.getString(9));
			bean.setEmailId(rs.getString(6));
			bean.setCollegeId(rs.getLong(7));
			bean.setCollegeName(rs.getString(8));
			bean.setCourseId(rs.getLong(9));
			bean.setCourseName(rs.getString(10));
			bean.setSubjectId(rs.getLong(11));
			bean.setSubjectName(rs.getString(12));
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
			
		}
		JDBCDataSource.closeConnection(conn);
		return bean;
	}

	public FacultyBean findByLogin(String login) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty where login = ?");

		pstmt.setString(1, login);

		ResultSet rs = pstmt.executeQuery();

		FacultyBean bean = null;

		while (rs.next()) {
			bean = new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(6));
			bean.setGender(rs.getString(9));
			bean.setEmailId(rs.getString(6));
			bean.setCollegeId(rs.getLong(7));
			bean.setCollegeName(rs.getString(8));
			bean.setCourseId(rs.getLong(9));
			bean.setCourseName(rs.getString(10));
			bean.setSubjectId(rs.getLong(11));
			bean.setSubjectName(rs.getString(12));
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
		}
		JDBCDataSource.closeConnection(conn);
		return bean;
	}

	public FacultyBean authenticate(String loginId, String password) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty where login = ? and password = ?");

		pstmt.setString(1, loginId);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		FacultyBean bean = null;

		while (rs.next()) {
			bean = new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(6));
			bean.setGender(rs.getString(9));
			bean.setEmailId(rs.getString(6));
			bean.setCollegeId(rs.getLong(7));
			bean.setCollegeName(rs.getString(8));
			bean.setCourseId(rs.getLong(9));
			bean.setCourseName(rs.getString(10));
			bean.setSubjectId(rs.getLong(11));
			bean.setSubjectName(rs.getString(12));
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
		}
		JDBCDataSource.closeConnection(conn);
		return bean;
	}

	public List search(FacultyBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		StringBuffer sql = new StringBuffer("select * from st_faculty where 1=1");

		if (bean != null) {
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getTime() > 0) {
				sql.append(" and dob like '" + new java.sql.Date(bean.getDob().getTime()) + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql ==>> " + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {
			bean = new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getDate(6));
			bean.setGender(rs.getString(9));
			bean.setEmailId(rs.getString(6));
			bean.setCollegeId(rs.getLong(7));
			bean.setCollegeName(rs.getString(8));
			bean.setCourseId(rs.getLong(9));
			bean.setCourseName(rs.getString(10));
			bean.setSubjectId(rs.getLong(11));
			bean.setSubjectName(rs.getString(12));
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
			list.add(bean);
		}
		JDBCDataSource.closeConnection(conn);
		return list;
	}

}
