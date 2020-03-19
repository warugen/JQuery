package com.tj.movieReviewSite.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.movieReviewSite.dao.MovieDao;
import com.tj.movieReviewSite.dto.MovieDto;

public class MovieWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("movieData");
		int maxSize = 1024 * 1024;
		String movieData = "";
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while (params.hasMoreElements()) {
				String param = params.nextElement();
				movieData = mRequest.getFilesystemName(param);
			}
			movieData = (movieData == null) ? "" : movieData;
			 String movieName = mRequest.getParameter("movieName");
			 String movieContent = mRequest.getParameter("movieContent");
			 MovieDao mDao = MovieDao.getInstance();
			int result = mDao.movie_upload(movieName, movieData, movieContent);
			if (result == MovieDao.SUCCESS) {
				request.setAttribute("movieResult", "업로드완료");
			} else {
				request.setAttribute("errorMsg", "업로드실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		File serverFile = new File(path + "/" + movieData);
		if (!movieData.equals("") && serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:\\mega_IT\\source\\6_jsp\\individual_project\\WebContent\\movieData/" + movieData);
				byte[] bs = new byte[(int) serverFile.length()];
				while (true) {
					int readbyteCnt = is.read(bs);
					if (readbyteCnt == -1)
						break;
					os.write(bs, 0, readbyteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(os!= null) os.close();
					if(is!= null) is.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		}

	}

}
