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
import com.tj.culture4u.dao.NoticeDao;
import com.tj.culture4u.dto.AdminDto;

public class NWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("adminUp");
		int maxSize = 1024 * 1024 * 10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String nFileName = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while (params.hasMoreElements()) {
				String param = (String) params.nextElement();
				nFileName = mRequest.getFilesystemName(param);
				System.out.println("첨부파일 넘어온 파라미터 이름"+param+" / 첨부파일이름 : "+nFileName);
			}
			// mId, fTitle, fContent, fileName, fIp
			HttpSession httpSession = request.getSession();
			// 세션에서 mId값 가져오기
			String aId = ((AdminDto) httpSession.getAttribute("admin")).getaId();
			String nTitle = mRequest.getParameter("nTitle");
			String nContent = mRequest.getParameter("nContent");
			
			System.out.println("mid = "+ aId);
			System.out.println("fTitle = "+ nTitle);
			System.out.println("fContent = "+ nContent);
			NoticeDao boardDao = NoticeDao.getInstance();
			int result = boardDao.write(aId, nTitle, nContent, nFileName);
			
			// joinMember결과에 따라 적절히 request.setAttribute
			if (result == NoticeDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("resultMsg", "글쓰기 성공");
			} else {
				request.setAttribute("resultMsg", "글쓰기 실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		File serverFile = new File(path + "/" + nFileName);
		if (serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream(
						"C:\\mega_IT\\source\\JQuery\\culture4u\\WebContent\\adminUp/" + nFileName);
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
