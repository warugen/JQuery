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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.culture4u.dao.ReviewBoardDao;

public class RModifyService implements Service {

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
			String dbFileName = mRequest.getParameter("dbFileName");
			if (rFileName == null) {
				rFileName = dbFileName;
			}
			// rId, rTitle, rContent, rfileName, rIp
			int rId = Integer.parseInt(mRequest.getParameter("rId"));
			String rTitle = mRequest.getParameter("rTitle");
			String rContent = mRequest.getParameter("rContent");
			String rIp = request.getRemoteAddr();
			ReviewBoardDao boardDao = ReviewBoardDao.getInstance();
			int result = boardDao.modify(rId, rTitle, rContent, rFileName, rIp);
			// joinMember결과에 따라 적절히 request.setAttribute
			if (result == ReviewBoardDao.SUCCESS) { // 글 수정 성공
				request.setAttribute("resultMsg", "글수정 성공");
				request.setAttribute("modifyResult", 1);
			} else {
				request.setAttribute("resultMsg", "글수정 실패");
				request.setAttribute("modifyResult", 0);
			}
			// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
			String pageNum = mRequest.getParameter("pageNum");
			request.setAttribute("pageNum", pageNum);
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
