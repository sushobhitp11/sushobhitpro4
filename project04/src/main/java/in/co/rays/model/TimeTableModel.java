package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.bean.TimeTableBean;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

public class TimeTableModel {
	
	public Integer nextPK() throws Exception {
		
		int pk = 0;
		
			Connection conn = JDBCDataSource.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM st_timetable");
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				pk = rs.getInt(1);

			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}
	public static void add(TimeTableBean bean) throws Exception, DuplicateRecordException {
		
		
		int pk = 0;

		CourseModel cModel = new CourseModel();
		CourseBean CourseBean = cModel.findByPk(bean.getCourseId());
		bean.setCourseName(CourseBean.getName());

		SubjectModel smodel = new SubjectModel();
		SubjectBean SubjectBean = smodel.findByPk(bean.getSubjectId());
		bean.setSubjectName(SubjectBean.getName());
		
			Connection conn = JDBCDataSource.getConnection();
			
			conn.setAutoCommit(false);
			
			PreparedStatement pstmt = conn.prepareStatement("INSERT st_timetable values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getSemester());
			pstmt.setString(3, bean.getDescription());
			pstmt.setDate(4, new java.sql.Date(bean.getExamDate().getTime()));
			System.out.println("Date______________________________________________" + bean.getExamDate());
			pstmt.setString(5, bean.getExamTime());
			pstmt.setLong(6, bean.getCourseId());
			pstmt.setString(7, bean.getCourseName());
			pstmt.setLong(8, bean.getSubjectId());
			pstmt.setString(9, bean.getSubjectName());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			System.out.println("record inserted" + i);
			conn.commit();
			pstmt.close();
			
			JDBCDataSource.closeConnection(conn);
	  }
	  

	  public void delete(TimeTableBean bean) throws Exception {
		
			 Connection conn = JDBCDataSource.getConnection();
			 
			conn.setAutoCommit(false);
			
			PreparedStatement pstmt = conn.prepareStatement("delete from st_timetable where id=?");
			
			pstmt.setLong(1, bean.getId());
			
			int i = pstmt.executeUpdate();
			
			System.out.println("record delete " + i);
			conn.commit();
			pstmt.close();
		
			JDBCDataSource.closeConnection(conn);
		
	}
	public void update(TimeTableBean bean) throws Exception, DuplicateRecordException {
		

		CourseModel cModel = new CourseModel();
		CourseBean CourseBean = cModel.findByPk(bean.getCourseId());
		bean.setCourseName(CourseBean.getName());

		SubjectModel smodel = new SubjectModel();
		SubjectBean SubjectBean = smodel.findByPk(bean.getSubjectId());
		bean.setSubjectName(SubjectBean.getName());

			 Connection conn = JDBCDataSource.getConnection();
			 
			conn.setAutoCommit(false);
			
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_timetable set semester=?,description=?,exam_time=?,exam_date=?,couse_id=?,course_name=?,subject_id=? ,subject_name=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");

			
			pstmt.setString(1, bean.getSemester());
			pstmt.setString(2, bean.getDescription());
			pstmt.setDate(3, new java.sql.Date(bean.getExamDate().getTime()));
			System.out.println("Date______________________________________________" + bean.getExamDate());
			pstmt.setString(4, bean.getExamTime());
			pstmt.setLong(5, bean.getCourseId());
			pstmt.setString(6, bean.getCourseName());
			pstmt.setLong(7, bean.getSubjectId());
			pstmt.setString(8, bean.getSubjectName());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());

			pstmt.executeUpdate();
			System.out.println("timetable update");
			conn.commit();
			pstmt.close();
		
			JDBCDataSource.closeConnection(conn);
		}
	
	    public TimeTableBean findByPk(long pk) throws Exception {
		
		StringBuffer sql = new StringBuffer("select * from st_timetable where id=?");
		
			 Connection conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			TimeTableBean bean = null;
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));

			}
			rs.close();
		
			JDBCDataSource.closeConnection(conn);
		
		return bean;
	}

	

	public List list() throws Exception {
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws Exception {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_timetable");

		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			TimeTableBean bean = null;
			while (rs.next()) {
				System.out.println("aghjgjhgjg");
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));

				list.add(bean);
				System.out.println("list");

			}
		
			JDBCDataSource.closeConnection(conn);
		
		
		return list;
	}


	public List search(TimeTableBean bean, int pageNo, int pageSize) throws Exception {
		
		StringBuffer sql = new StringBuffer("select * from st_timetable where 1=1 ");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append("AND id=" + bean.getId());
			}
			if (bean.getCourseId() > 0) {
				sql.append("AND Course_ID=" + bean.getCourseId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append("AND courseName like '" + bean.getCourseName() + "%'");
			}
			if (bean.getSubjectId() > 0) {
				sql.append("AND Subject_ID=" + bean.getSubjectId());
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append("AND subjectName like '" + bean.getSubjectName() + "%'");
			}
			if (bean.getExamDate() != null && bean.getExamDate().getTime() > 0) {
				Date d = new Date(bean.getExamDate().getTime());
				System.out.println(d);
				sql.append("AND EXAM_DATE = '" + DataUtility.getDateString(d) + "'");
			}
			if (bean.getExamTime() != null && bean.getExamTime().length() > 0) {
				System.out.println("done");
				sql.append("AND EXAM_TIME like '" + bean.getExamTime() + "%'");
			}

		}
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
				list.add(bean);
			}
			rs.close();
		
			JDBCDataSource.closeConnection(conn);
		
		return list;

	}

	public static TimeTableBean checkByCourseName(long CourseId, java.util.Date ExamDate) throws Exception {
		
		TimeTableBean bean = null;

		Date Exdate = new Date(ExamDate.getTime());

		StringBuffer sql = new StringBuffer("SELECT * FROM TIMETABLE WHERE COURSE_ID=? " + "AND EXAM_DATE=?");

		
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			// ps.setDate(2, examdate);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setExamTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDatetime(rs.getTimestamp(12));
				bean.setModifiedDatetime(rs.getTimestamp(13));
			}
		
		return bean;
	}

}
