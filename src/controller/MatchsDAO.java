package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

public class MatchsDAO {
	// 강사 매칭
	public void setMatchs(String inst_id, String mem_id) {

		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL MATCHS_INSERT(?, ?)}");
			cstmt.setString(1, inst_id);
			cstmt.setString(2, mem_id);

			int i = cstmt.executeUpdate();
			if (i != 0) {
				System.out.println("매칭이 취소되었습니다.");
				System.out.println("매칭 취소 성공!!!");
			} else {
				System.out.println("매칭 취소 실패!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null) {
					cstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 매칭 취소
	public void deleteMatchs(String mem_id) {

		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL MATCHS_DELETE(?, ?, ?)}");
			cstmt.setString(1, mem_id);
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.registerOutParameter(3, Types.NUMERIC);
			cstmt.executeUpdate();
			if (cstmt.getInt(3) == 0) {
				System.out.println("매칭이 취소되었습니다.");
				System.out.println("매칭 취소 성공!!!");
			} else {
				System.out.println("매칭 취소 실패!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null) {
					cstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 내 강사목록 보여주기!
	public boolean getMyInstInfo(String mem_id) {
		String sql = "SELECT M.M_NO AS M_NO, M.MEM_ID AS MEM_ID,  U.U_NAME AS INST_NAME, "
				+ "U.U_PHONE AS INST_PHONE, M.M_DATE AS M_DATE "
				+ "FROM MATCHS M INNER JOIN USERT U ON M.INST_ID = U.U_ID WHERE MEM_ID = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("\t\t\t\t\t\t\t    강사 매칭 정보");
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------------------------");
				String header = String.format("%-7s %-8s %-10s %-15s %-13s", "일련번호", "내아이디", "강사이름", "전화번호", "등록일자");
				System.out.println("\t\t\t\t    " + header);
				int m_no = rs.getInt("M_NO");
				String myid = rs.getString("MEM_ID");
				String inst_name = rs.getString("INST_NAME");
				String inst_phone = rs.getString("INST_PHONE");
				Date m_date = rs.getDate("M_DATE");
				check = true;
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("\t\t\t\t    "
						+ String.format("%-8d %-10s %-10s %-16s %-10s", m_no, myid, inst_name, inst_phone, m_date));
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return check;
	}

	// 강사가 이미 매칭되어있으면 돌려주기
	public boolean checkMatchs(String mem_id) {
		String sql = "SELECT COUNT(*) FROM MATCHS WHERE MEM_ID = ?";
		boolean check = false;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				int count = rs.getInt("COUNT(*)");

				if (count == 0) {
					check = true;
				} else {
					check = false;
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return check;
	}
}
