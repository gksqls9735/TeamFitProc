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

import model.CartVO;
import model.ExerciseVO;

public class CartDAO {

	// 기존에 신청한 강의인지 확인
	public boolean confirmCart(String id, int e_no) {
		String sql = "SELECT COUNT(*) FROM CART WHERE U_ID = ? AND E_NO = ?";
		boolean check = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, e_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("COUNT(*)") == 1) {
					check = true;
					System.out.println("기존에 신청한 강의입니다.");
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

	// 수강 신청
	public void setCartRegister(CartVO c) {
		
		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL CART_INSERT(?, ?, ?)}");

			cstmt.setString(1, c.getU_id());
			cstmt.setInt(2, c.getE_no());
			cstmt.setString(3, c.getC_payment_status());

			int i = cstmt.executeUpdate();
			if (i == 1) {
				System.out.println("강의 신청 완료.");
				System.out.println("강의 신청 성공!!!");
			} else {
				System.out.println("강의 신청 실패!!!");
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

	// 개별 수강 취소
	public void setCartDelete(int c_no) {
		String sql = "DELETE FROM CART WHERE C_NO = ?";

		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL CART_DELETE(?, ?, ?)}");

			cstmt.setInt(1, c_no);
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.registerOutParameter(3, Types.NUMERIC);
			
			cstmt.executeUpdate();
			if (cstmt.getInt(3) == 0) {
				System.out.println("강의 신청 취소 완료.");
				System.out.println("강의 신청 취소 성공!!!");
			} else {
				System.out.println("강의 신청 취소 실패!!!");
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

	// 카트가 존재하는지 확인
	public boolean checkCart(String u_id) {
		String sql = "SELECT COUNT(*) FROM CART WHERE U_ID = ?";
		boolean check = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("COUNT(*)") == 0) {
					check = true;
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

	// 카트 전체 삭제
	public void allCartDelete(String u_id) {

		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL CART_ALL_DELETE(?, ?, ?)}");

			cstmt.setString(1, u_id);
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.registerOutParameter(3, Types.NUMERIC);
			cstmt.executeUpdate();
			if (cstmt.getInt(3) == 0) { // 변경된 조건
				System.out.println("강의 신청 취소 완료.");
			} else {
				System.out.println("강의 신청 취소 실패!!!");
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 수강 내역 !
	public void getCartTotal(String u_id) {
		String sql = "SELECT C.C_NO AS C_NO, C.U_ID AS U_ID, U.U_NAME AS U_NAME, C.E_NO AS E_NO, "
				+ "E.E_NAME AS E_NAME, E.E_DATE AS E_DATE, E.E_TIME AS E_TIME, E.E_ADDR AS E_ADDR, "
				+ "E.E_PRICE AS E_PRICE, C.C_PAYMENT_STATUS AS C_PAYMENT_STATUS " + "FROM CART C, EXERCISE E, USERT U "
				+ "WHERE C.U_ID = ? AND C.E_NO = E.E_NO AND C.U_ID = U.U_ID " + "ORDER BY C_NO ASC";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("---------------------------------");
				System.out.println("일련번호\t|" + rs.getInt("C_NO") + "\n학생ID\t|" + u_id + "\n유저이름\t|"
						+ rs.getString("U_NAME") + "\n강의번호\t|" + rs.getInt("E_NO") + "\n운동종목\t|"
						+ rs.getString("E_NAME") + "\n강의날짜\t|" + rs.getString("E_DATE") + "\n강의시간\t|"
						+ rs.getString("E_TIME") + "\n강의장소\t|" + rs.getString("E_ADDR") + "\n가격\t|"
						+ rs.getInt("E_PRICE") + "\n결제여부\t|" + rs.getString("C_PAYMENT_STATUS"));
				System.out.println("---------------------------------");
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
	}

	// 강의 코드 일련번호 1개 가져오기 !
	public Map<Integer, Integer> getCartE_NO(int c_no) {
		Map<Integer, Integer> e_noCountMap = new HashMap<>();
	    String sql = "SELECT C.E_NO, E.E_MEMCOUNT FROM CART C INNER JOIN EXERCISE E ON C.E_NO = E.E_NO WHERE C.C_NO = ?";
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        con = DBUtil.makeConnection();
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, c_no);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            e_noCountMap.put(rs.getInt("E_NO"), rs.getInt("E_MEMCOUNT"));
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
	    return e_noCountMap;
	}

	// 강의 코드 일련번호 리스트 가져오기 !
	public Map<Integer, Integer> getCartE_NOList(String id) {
		Map<Integer, Integer> e_noCountMapList = new HashMap<>();
		String sql = "SELECT C.E_NO, E.E_MEMCOUNT FROM CART C INNER JOIN EXERCISE E ON C.E_NO = E.E_NO WHERE C.U_ID = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				e_noCountMapList.put(rs.getInt("E_NO"), rs.getInt("E_MEMCOUNT"));
				System.out.println(count++);
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
		return e_noCountMapList;
	}

}
