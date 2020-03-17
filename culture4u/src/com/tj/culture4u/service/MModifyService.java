package com.tj.culture4u.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.culture4u.dao.MemberDao;
import com.tj.culture4u.dto.MemberDto;

public class MModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 회원정보수정하기
		String path = request.getRealPath("memberPhotoUp");
		int maxSize = 1024 * 1024; // 최대 업로드 사이즈 : 1M
		String mPhoto = "";
		try {
			// mRequest 객체 생성한후 파일이름 받아오기
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());

			Enumeration<String> params = mRequest.getFileNames();
			// while(params.hasMoreElements()) {
			String param = params.nextElement();
			// }
			
			// 파파라미터값 받아오기
			String mId = mRequest.getParameter("mId");
			String mPw = mRequest.getParameter("mPw");
			String mName = mRequest.getParameter("mName");
			String mEmail = mRequest.getParameter("mEmail");
			String mBirthStr = mRequest.getParameter("mBirth");
			int mLevel = MemberDao.MEMBER_NORMAL;
			mPhoto = mRequest.getParameter("mPhoto");
			// 정보수정시 사진파일을 첨부안하면 dbmPhoto(원래 사진파일)로
			String dbmPhoto = mRequest.getParameter("dbmPhoto");
			mPhoto = (mPhoto==null)? dbmPhoto : mPhoto;
			
			Date mBirth = null;
			if(!mBirthStr.equals("")) {
				mBirth = Date.valueOf(mBirthStr);
			}
			String mAddress = mRequest.getParameter("mAddress");
			MemberDao mDao = MemberDao.getInstance();
			
			// 회원정보 수정
			//MemberDto member = new MemberDto(mId, mPw, mName, mEmail, mPhoto, mBirth, mAddress, null);
			MemberDto member = new MemberDto(mId, mPw, mName, mEmail, dbmPhoto, mBirth, mAddress, null, mLevel);
			int result = mDao.modifyMember(member);
			if (result == MemberDao.SUCCESS) {// 수정 성공시 세션도 수정
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("member", member);
				request.setAttribute("modifyResult", "회원정보 수정 성공");
			} else {
				// 수정 실패시
				request.setAttribute("modifyResult", "회원정보 수정 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// 업로드된 파일을 소스폴더로 복사
		File serverFile = new File(path + "/" + mPhoto);
		if (!mPhoto.equals("NOIMG.JPG") && serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;

			try {
				is = new FileInputStream(serverFile);
				/*
				 * 집과 학원 저장경로가 다르다 따로 설정해주기
				 * 
				 * 학원 경로 os = new FileOutputStream(
				 * "D:/mega_IT/source/7_jQuery/model2ex/WebContent/memberPhotoUp/"+mPhoto);
				 * 
				 * 집 경로
				 * 
				 * os = new FileOutputStream(
				 * "C:/mega_IT/source/JSP_Study/ch19_mvc3_member/WebContent/memberPhotoUp/"+
				 * mPhoto);
				 */
				os = new FileOutputStream(
						"D:/mega_IT/source/7_jQuery/model2ex/WebContent/memberPhotoUp/"+mPhoto);
				byte[] bs = new byte[(int) serverFile.length()];
				while (true) {
					int nReadByteCnt = is.read(bs);
					if (nReadByteCnt == -1)
						break;
					os.write(bs, 0, nReadByteCnt);
				}
			} catch (Exception e) {
				System.out.println("파일복사예외" + e.getMessage());
			} finally {
				try {
					if (os != null)
						os.close();
					if (is != null)
						is.close();
				} catch (Exception e2) {
				}
			}
		}

	}

}
