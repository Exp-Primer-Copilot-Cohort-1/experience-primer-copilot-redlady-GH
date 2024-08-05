package edu.study.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.study.vo.BoardVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/test.do",method= RequestMethod.GET)
	public String test() {
		return "sample/sample";
		// sample/sample
	}
	
	@RequestMapping(value="/oper/calc.do", method=RequestMethod.GET)
	public String calc() {
		return "oper/calculator";
	}
	
	@RequestMapping(value="/oper/calc.do", method=RequestMethod.POST)
	public String calc(int num1, int num2, String oper,Model model) {
		
		int result = 0;
		
		if(oper != null && !oper.equals("")) {
			if(oper.equals("plus")) {
				result = num1+num2;
			}else if(oper.equals("minus")) {
				result = num1-num2;
			}else if(oper.equals("multi")) {
				result = num1*num2;
			}else if(oper.equals("div")) {
				result = num1/num2;
			}
			
			model.addAttribute("result", result);
		}else {
			model.addAttribute("result", "��ȿ���� ���� �������Դϴ�.");
		}
		
		return "oper/result";
	}
	
	@RequestMapping(value="/fileupload.do",method=RequestMethod.GET)
	public String fileupload() {
		return "file/upload";
	}
	
	@RequestMapping(value="/fileupload.do",method=RequestMethod.POST)
//	public String fileupload(MultipartFile uploadF1,MultipartFile uploadF2,HttpServletRequest req, BoardVO vo) throws IllegalStateException, IOException {
	public String fileupload(MultipartHttpServletRequest mreq, HttpServletRequest req, BoardVO vo) throws IllegalStateException, IOException {
		System.out.println("title=>"+vo.getTitle());
		
//		String path ="C:\\CODE\\Spring\\springMVC\\src\\main\\webapp\\resources\\upload";
		
		//시스템에서 사용하는 resources/upload 의 절대 경로가 어디인지 알아오는 소스코드
		String path  = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		System.out.println(path);
		
		File dir = new File(path);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
/*		if(!uploadF1.getOriginalFilename().isEmpty()) {
			int pos = uploadF1.getOriginalFilename().lastIndexOf(".");
	        String ext = uploadF1.getOriginalFilename().substring(pos + 1);
	        
	        Date now = new Date();
	        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(now);

	        int random = (int) ((Math.random() * 100) + 1);
	        String result = today + random;
	        
			uploadF1.transferTo(new File(path,result+"."+ext));
			
		}
		
		if(!uploadF2.getOriginalFilename().isEmpty()) {
			int pos = uploadF2.getOriginalFilename().lastIndexOf(".");
	        String ext = uploadF2.getOriginalFilename().substring(pos + 1);
	        
	        Date now = new Date();
	        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(now);

	        int random = (int) ((Math.random() * 100) + 1);
	        String result = today + random;
	        
			uploadF2.transferTo(new File(path,result+"."+ext));
			
		}	*/
		List<MultipartFile> fileList = mreq.getFiles("uploads");
		
		for( MultipartFile upload : fileList )
		{
			if(!upload.getOriginalFilename().isEmpty()) {
				int pos = upload.getOriginalFilename().lastIndexOf(".");
		        String ext = upload.getOriginalFilename().substring(pos + 1);
		        
		        
		        Date now = new Date();
		        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(now);
		
		        int random = (int) ((Math.random() * 100) + 1);
		        String result = today + random;
		        
		        upload.transferTo(new File(path,result+"."+ext));
				
			}
		}
		return "";
	}
}