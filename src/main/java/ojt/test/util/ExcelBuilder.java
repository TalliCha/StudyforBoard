package ojt.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import ojt.test.domain.BoardVO;

@SuppressWarnings("deprecation")
public class ExcelBuilder extends AbstractExcelView {
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception  {

		@SuppressWarnings("unchecked")
		
		List<BoardVO> getList = (List<BoardVO>) model.get("getList");
		
		HSSFSheet sheet = workbook.createSheet("test1");
		
		sheet.setColumnWidth((short)0, (short) 2000);// 번호 컬럼 길이
		sheet.setColumnWidth((short)1, (short) 15000);// 제목 컬럼 길이
		sheet.setColumnWidth((short)2, (short) 2000);// 댓글 수 컬럼 길이.
		sheet.setColumnWidth((short)3, (short) 2000);// 작성자 컴럼 길이.
		sheet.setColumnWidth((short)4, (short) 8000);// 작성일 컬럼 길이.
		sheet.setColumnWidth((short)5, (short) 2000);// 첨부파일 컬럼 길이.
		sheet.setColumnWidth((short)6, (short) 2000);// 조회수 컬럼 길이.
		
		//해더부분셀에 스타일을 주기위한 인스턴스 생성   
        HSSFCellStyle cellStyle = workbook.createCellStyle();            
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);                     //스타일인스턴스의 속성 ?V팅           
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);              //테두리 설정   
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);   
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);   
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);   
        HSSFFont font = workbook.createFont();                                    //폰트 조정 인스턴스 생성   
        font.setBoldweight((short)700);        
        cellStyle.setFont(font);   
           
        //가운데 정렬과 얇은 테두리를 위한 스타일 인스턴스 생성   
        HSSFCellStyle cellStyle0 = workbook.createCellStyle();                           
        cellStyle0.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        cellStyle0.setBorderRight(HSSFCellStyle.BORDER_THIN);   
        cellStyle0.setBorderLeft(HSSFCellStyle.BORDER_THIN);   
        cellStyle0.setBorderTop(HSSFCellStyle.BORDER_THIN);   
        cellStyle0.setBorderBottom(HSSFCellStyle.BORDER_THIN);   
           
        //얇은 테두리를 위한 스타일 인스턴스 생성   
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();           
        cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);   
        cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);   
        cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);   
        cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
		
		HSSFRow head_row = sheet.createRow(0);
		
		HSSFCell cell_rno = head_row.createCell((short) 0);
		HSSFCell cell_title = head_row.createCell((short) 1);
		HSSFCell cell_count_cno = head_row.createCell((short) 2);
		HSSFCell cell_writer = head_row.createCell((short) 3);
		HSSFCell cell_regdate = head_row.createCell((short) 4);
		HSSFCell cell_upload_file = head_row.createCell((short) 5);
		HSSFCell cell_viewCnt = head_row.createCell((short) 6);

		cell_rno.setCellValue("글번호");
		cell_title.setCellValue("제목");
		cell_count_cno.setCellValue("댓글 수");
		cell_writer.setCellValue("작성자");
		cell_regdate.setCellValue("작성일");
		cell_upload_file.setCellValue("첨부파일");
		cell_viewCnt.setCellValue("조회수");
		
		cell_rno.setCellStyle(cellStyle);
		cell_title.setCellStyle(cellStyle);
		cell_count_cno.setCellStyle(cellStyle);
		cell_writer.setCellStyle(cellStyle);
		cell_regdate.setCellStyle(cellStyle);
		cell_upload_file.setCellStyle(cellStyle);
		cell_viewCnt.setCellStyle(cellStyle);
		
		int i, cnt =  getList.size();
		for ( i = 0; i < cnt; i++) {
			Integer rno =getList.get(i).getRno();
			String title =getList.get(i).getTitle();
			
			System.out.println("$$$$$$rno:"+rno);
			
			Integer count_cno = getList.get(i).getCount_cno();

			
			String writer = getList.get(i).getWriter();
			
			String regdate = getList.get(i).getRegdate();
			
			if( getList.get(i).getParent_bno() != null && getList.get(i).getParent_bno() == 0 ){
				title = "-원글이 삭제된 답변입니다."+title;
			}
			
			for (int j = 0; j < getList.get(i).getReply_lv(); j++) {
				title = "▶"+title;
			}
			
			String upload_file = "";
			if( getList.get(i).getUpload_file() > 0 ){
				upload_file = "있음";
			}
			
			Integer viewCnt = getList.get(i).getViewcnt();
			
			HSSFRow row = sheet.createRow(i+1);
			
			 cell_rno = row.createCell((short) 0);
			 cell_title = row.createCell((short) 1);
			 cell_count_cno = row.createCell((short) 2);
			 cell_writer = row.createCell((short) 3);
			 cell_regdate = row.createCell((short) 4);
			 cell_upload_file = row.createCell((short) 5);
			 cell_viewCnt = row.createCell((short) 6);
			
			
			cell_rno.setCellValue(rno);
			cell_title.setCellValue(title);
			cell_count_cno.setCellValue(count_cno);
			cell_writer.setCellValue(writer);
			cell_regdate.setCellValue(regdate);
			cell_upload_file.setCellValue(upload_file);
			cell_viewCnt.setCellValue(viewCnt);
			
			
			cell_rno.setCellStyle(cellStyle0);
			cell_title.setCellStyle(cellStyle0);
			cell_count_cno.setCellStyle(cellStyle0);
			cell_writer.setCellStyle(cellStyle0);
			cell_regdate.setCellStyle(cellStyle0);
			cell_upload_file.setCellStyle(cellStyle0);
			cell_viewCnt.setCellStyle(cellStyle0);
		}
		
		final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String fileName = "list_"+SDF.format(new Date())+".xls";
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");	
	}

}
