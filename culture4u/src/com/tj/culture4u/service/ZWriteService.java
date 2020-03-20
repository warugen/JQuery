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
import com.tj.culture4u.dao.MagazineDao;

public class ZWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("magazineUp");
		int maxSize = 1024 * 1024 * 10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String zFileName = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while (params.hasMoreElements()) {
				String param = (String) params.nextElement();
				zFileName = mRequest.getFilesystemName(param);
				System.out.println("첨부파일 넘어온 파라미터 이름"+param+" / 첨부파일이름 : "+zFileName);
			}
			
			// 커버이미지 선택안하면 기본 이미지로 설정해준다.
			if(zFileName.equals("")) {
				zFileName = "default.jpg";
			}
			// zId, zTitle, zContent, zFileName, zIp
			String zTitle = mRequest.getParameter("fTitle");
			String zContent = mRequest.getParameter("fContent");
			String zIp = request.getRemoteAddr();
			
			MagazineDao zDao = MagazineDao.getInstance();
			int result = zDao.write(zTitle, zContent, zFileName, zIp);
			
			// 결과에 따라 적절히 request.setAttribute
			if (result == MagazineDao.SUCCESS) {
				// 게시글쓰기 성공
				request.setAttribute("resultMsg", "글쓰기 성공");
			} else {
				// 게시글쓰기 실패
				request.setAttribute("resultMsg", "글쓰기 실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "글쓰기 실패 catch절");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		File serverFile = new File(path + "/" + zFileName);
		if (serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream(
						"D:\\mega_IT\\source\\7_jQuery\\culture4u\\WebContent\\magazineUp/" + zFileName);
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
