package com.tj.movieReviewSite.service;

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
import com.tj.movieReviewSite.dao.MovieDao;
import com.tj.movieReviewSite.dto.MovieDto;

public class MovieModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("movieData");
		int maxSize = 1024 * 1024;
		String movieData = "", tempMovieData="";
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			while (params.hasMoreElements()) {
				String param = params.nextElement();
				movieData = mRequest.getFilesystemName(param);
			}
			 String movieName = mRequest.getParameter("movieName");
			 String dbMovieData = mRequest.getParameter("dbMovieData");
			 if(movieData==null) {
				 movieData = dbMovieData;
			 }
			 String movieContent = mRequest.getParameter("movieContent");
			 int movieNo = Integer.parseInt(mRequest.getParameter("movieNo"));
			 MovieDao mDao = MovieDao.getInstance();
			 MovieDto dto = new MovieDto(movieNo, movieName, dbMovieData, movieContent, null);
			int result = mDao.movie_board_modify(movieName, movieData, movieContent, movieNo);
			if (result == MovieDao.SUCCESS) {
				request.setAttribute("movie_modifyResult", "영화정보 수정 완료");
			} else {
				request.setAttribute("errorMsg", "영화정보 수정 실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		File serverFile = new File(path + "/" + movieData);
		if (tempMovieData!=null && serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/mega_IT/source/6_jsp/individual_project/WebContent/movieData/" + movieData);
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
