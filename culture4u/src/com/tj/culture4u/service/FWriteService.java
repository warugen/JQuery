package com.tj.culture4u.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.culture4u.dao.FreeBoardDao;
import com.tj.culture4u.dto.MemberDto;

public class FWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("freeboardFiles");
		int maxSize = 1024 * 1024 * 10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String fFileName = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while (params.hasMoreElements()) {
				String param = (String) params.nextElement();
				fFileName = mRequest.getFilesystemName(param);
				System.out.println("첨부파일 넘어온 파라미터 이름"+param+" / 첨부파일이름 : "+fFileName);
			}
			// mId, fTitle, fContent, fileName, fIp
			HttpSession httpSession = request.getSession();
			// 세션에서 mId값 가져오기
			String mId = ((MemberDto) httpSession.getAttribute("member")).getmId();
			String fTitle = mRequest.getParameter("fTitle");
			String fContent = mRequest.getParameter("fContent");
			String fIp = request.getRemoteAddr();
			
			System.out.println("mid = "+ mId);
			System.out.println("fTitle = "+ fTitle);
			System.out.println("fContent = "+ fContent);
			System.out.println("fIp = "+ fIp);
			System.out.println("fFileName = "+ fFileName);
			
			FreeBoardDao boardDao = FreeBoardDao.getInstance();
			int result = boardDao.write(mId, fTitle, fContent, fFileName, fIp);
			
			// joinMember결과에 따라 적절히 request.setAttribute
			if (result == boardDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("resultMsg", "글쓰기 성공");
			} else {
				request.setAttribute("resultMsg", "글쓰기 실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		File serverFile = new File(path + "/" + fFileName);
		if (serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream(
						"D:\\mega_IT\\source\\7_jQuery\\culture4u\\WebContent\\freeboardFiles/" + fFileName);
				byte[] bs = new byte[(int) serverFile.length()];
				while (true) {
					int nByteCnt = is.read(bs);
					if (nByteCnt == -1)
						break;
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (os != null) os.close();
					if (is != null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}

}
