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
import com.tj.culture4u.dao.ReviewBoardDao;
import com.tj.culture4u.dto.MemberDto;

public class RWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("fileboardUp");
		int maxSize = 1024 * 1024 * 10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String rFileName = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			rFileName = mRequest.getFilesystemName(param);
			// mId, fTitle, fContent, fileName, fIp
			HttpSession httpSession = request.getSession();
			// 세션에서 mId값 가져오기
			String mId = ((MemberDto) httpSession.getAttribute("member")).getmId();
			String rTitle = mRequest.getParameter("fTitle");
			String rContent = mRequest.getParameter("fContent");
			String rIp = request.getRemoteAddr();
			
			ReviewBoardDao boardDao = ReviewBoardDao.getInstance();
			int result = boardDao.write(mId, rTitle, rContent, rFileName, rIp);
			
			// 결과에 따라 적절히 request.setAttribute
			if (result == boardDao.SUCCESS) {
				// 게시글쓰기 성공
				request.setAttribute("resultMsg", "글쓰기 성공");
			} else {
				// 게시글쓰기 실패
				request.setAttribute("resultMsg", "글쓰기 실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		File serverFile = new File(path + "/" + rFileName);
		if (serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream(
						"D:\\mega_IT\\source\\7_jQuery\\culture4u\\WebContent\\reviewUp/" + rFileName);
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
					if (os != null)
						os.close();
					if (is != null)
						is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
