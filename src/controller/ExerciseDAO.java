package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.ExerciseVO;

public class ExerciseDAO {

	// 강의 목록 출력
	public void getExerciseTotalList() {

		ExerciseVO ex = new ExerciseVO();

		String sql = "SELECT * FROM EXERCISE ORDER BY E_NO ASC";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("\t\t\t\t\t\t\t    강의 목록 출력");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------");
			String header = String.format("%-8s %-10s %-10s %-13s %-10s %-18s %-10s", "일련번호", "운동종목", "가격", "날짜",
					"시작시간", "장소", "정원");
			System.out.println("\t\t      " + header);
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------");
			while (rs.next()) {
				ex.setE_no(rs.getInt("E_NO"));
				ex.setE_name(rs.getString("E_NAME"));
				ex.setE_price(rs.getInt("E_PRICE"));
				ex.setE_date(rs.getString("E_DATE"));
				ex.setE_time(rs.getString("E_TIME"));
				ex.setE_addr(rs.getString("E_ADDR"));
				ex.setE_maxmem(rs.getInt("E_MAXMEM"));
				ex.setE_memCount(rs.getInt("E_MEMCOUNT"));
				System.out.println("\t\t      " + ex.toString());
			}
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------");
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
	}

	// 강의 개설
	public void setExerciseRegister(ExerciseVO ex) {
		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL EXERCISE_INSERT(?, ?, ?, ?, ?, ?, ?)}");

			cstmt.setString(1, ex.getE_name());
			cstmt.setInt(2, ex.getE_price());
			cstmt.setString(3, ex.getE_date());
			cstmt.setString(4, ex.getE_time());
			cstmt.setString(5, ex.getE_addr());
			cstmt.setInt(6, ex.getE_memCount());
			cstmt.setInt(7, ex.getE_maxmem());

			int i = cstmt.executeUpdate();

			if (i == 1) {
				System.out.println(ex.getE_name() + " 강의 등록 완료.");
				System.out.println("강의 등록 성공!!!");
			} else {
				System.out.println("강의 등록 실패!!!");
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

	// 강의 수정
	public boolean setExerciseUpdate(ExerciseVO ex) {
		String sql = "UPDATE EXERCISE SET E_PRICE = ?, E_DATE = ?, E_TIME = ?, E_ADDR = ? WHERE E_NO = ?";
		Connection con = null;
		CallableStatement cstmt = null;
		boolean success = false;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL EXERCISE_UPDATE(?, ?, ?, ?, ?, ?)}");

			cstmt.setInt(1, ex.getE_price());
			cstmt.setString(2, ex.getE_date());
			cstmt.setString(3, ex.getE_time());
			cstmt.setString(4, ex.getE_addr());
			cstmt.setInt(5, ex.getE_no());
			cstmt.registerOutParameter(6, Types.NUMERIC);
			cstmt.executeUpdate();

			if (cstmt.getInt(6) == 1) {
				System.out.println(ex.getE_name() + " 강의 수정 완료.");
				System.out.println("강의 수정 성공!!!");
				success = true;
			} else {
				System.out.println("강의 수정 실패!!!");
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

		return success;
	}

	// 강의 삭제
	public void setExerciseDelete(int e_no) {

		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL EXERCISE_DELETE(?, ?, ?)}");

			cstmt.setInt(1, e_no);
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.registerOutParameter(3, Types.NUMERIC);
			int i = cstmt.executeUpdate();

			if (cstmt.getInt(3) == 0) {
				System.out.println("강의 삭제 완료.");
				System.out.println("강의 삭제 성공!!!");
			} else {
				System.out.println("강의 삭제 실패!!!");
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

	// 강의 검색
		public void getExerciseSearch(String e_name) {

			String sql = "SELECT * FROM EXERCISE WHERE E_NAME = ? ORDER BY E_NO ASC";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = DBUtil.makeConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, e_name);
				rs = pstmt.executeQuery();

				if (!rs.next()) {
					System.out.println("해당 종목의 강의가 존재하지 않습니다.");
				} else {
					System.out.println("\t\t\t\t\t\t\t    강의 목록 출력");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
					String header = String.format("%-8s %-10s %-10s %-13s %-10s %-18s %-10s",
		                    "일련번호", "운동종목", "가격", "날짜", "시작시간", "장소", "정원");
					System.out.println("\t\t      " + header);
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
					do {
						ExerciseVO ev = new ExerciseVO();
						ev.setE_no(rs.getInt("E_NO"));
						ev.setE_name(rs.getString("E_NAME"));
						ev.setE_price(rs.getInt("E_PRICE"));
						ev.setE_date(rs.getString("E_DATE"));
						ev.setE_time(rs.getString("E_TIME"));
						ev.setE_addr(rs.getString("E_ADDR"));
						ev.setE_maxmem(rs.getInt("E_MAXMEM"));
						ev.setE_memCount(rs.getInt("E_MEMCOUNT"));
						System.out.println("\t\t      " + ev.toString());
					} while (rs.next());
				}
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
				System.out.println();
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
		}

	// 운동 일련번호로 신청인원 수 가져오기
	public int getMemCount(int e_no) {

		String sql = "SELECT E_MEMCOUNT FROM EXERCISE WHERE E_NO = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int memCount = 0;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, e_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memCount = rs.getInt("E_MEMCOUNT");
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
		return 0;
	}

	// 신청인원 늘리기
	public void memCountIncrease(int e_no, int memCount) {

		String sql = "UPDATE EXERCISE SET E_MEMCOUNT = ? WHERE E_NO = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, memCount);
			pstmt.setInt(2, e_no);
			int i = pstmt.executeUpdate();

			if (i > 0) {
				System.out.println("신청인원 추가 성공!");
			} else {
				System.out.println("신청인원 추가 실패!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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

	}

	// 신청인원 줄이기
	public void memCountReduce(Map<Integer, Integer> e_noCountList) {

		String sql = "UPDATE EXERCISE SET E_MEMCOUNT = ? WHERE E_NO = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			for (Integer key : e_noCountList.keySet()) {
				pstmt.setInt(1, e_noCountList.get(key) - 1);
				pstmt.setInt(2, key);
				i = pstmt.executeUpdate();
			}
			if (i > 0) {
				System.out.println("신청인원 감소 성공!");
			} else {
				System.out.println("신청인원 감소 실패!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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
	}

}
