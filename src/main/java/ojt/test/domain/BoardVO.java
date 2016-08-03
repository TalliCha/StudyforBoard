package ojt.test.domain;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class BoardVO {

	private Integer rno;
	private Integer bno;

	private String title;
	private String content;
	private String writer;

	private String regdate;
	private Integer viewcnt;

	private Integer ref_bno;
	private Integer reply_bno;
	private Integer reply_lv;
	private Integer parent_bno;

	private Integer count_cno;

	private Integer upload_file;

	private List<UploadVO> uploadVOs;

	private List<String> deleteFiles;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Integer getRno() {
		return rno;
	}

	public void setRno(Integer rno) {
		this.rno = rno;
	}

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = SDF.format(regdate);
	}

	public Integer getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(Integer viewcnt) {
		this.viewcnt = viewcnt;
	}

	public Integer getRef_bno() {
		return ref_bno;
	}

	public void setRef_bno(Integer ref_bno) {
		this.ref_bno = ref_bno;
	}

	public Integer getReply_bno() {
		return reply_bno;
	}

	public void setReply_bno(Integer reply_bno) {
		this.reply_bno = reply_bno;
	}

	public Integer getReply_lv() {
		return reply_lv;
	}

	public void setReply_lv(Integer reply_lv) {
		this.reply_lv = reply_lv;
	}

	public Integer getParent_bno() {
		return parent_bno;
	}

	public void setParent_bno(Integer parent_bno) {
		this.parent_bno = parent_bno;
	}

	public Integer getCount_cno() {
		return count_cno;
	}

	public void setCount_cno(Integer count_cno) {
		this.count_cno = count_cno;
	}

	public Integer getUpload_file() {
		return upload_file;
	}

	public void setUpload_file(Integer upload_file) {
		this.upload_file = upload_file;
	}

	public List<UploadVO> getUploadVOs() {
		return uploadVOs;
	}

	public void setUploadVOs(List<UploadVO> uploadVOs) {
		this.uploadVOs = uploadVOs;
	}

	public List<String> getDeleteFiles() {
		return deleteFiles;
	}

	public void setDeleteFiles(List<String> deleteFiles) {
		this.deleteFiles = deleteFiles;
	}

	@Override
	public String toString() {
		return "BoardVO [rno=" + rno + ", bno=" + bno + ", title=" + title + ", content=" + content + ", writer="
				+ writer + ", regdate=" + regdate + ", viewcnt=" + viewcnt + ", ref_bno=" + ref_bno + ", reply_bno="
				+ reply_bno + ", reply_lv=" + reply_lv + ", parent_bno=" + parent_bno + ", count_cno=" + count_cno
				+ ", upload_file=" + upload_file + ", uploadVOs=" + uploadVOs + ", deleteFiles=" + deleteFiles + "]";
	}
	
}
