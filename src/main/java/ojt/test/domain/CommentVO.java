package ojt.test.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentVO {
	
	
	private Integer rno;
	private Integer cno;
	private Integer bno;
	
	private String content;
	private String writer;
	
	private String regdate;
	
	private Integer ref_cno;
	private Integer reply_cno;
	private Integer reply_lv;
	
	private Integer parent_cno;
	
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Integer getRno() {
		return rno;
	}

	public void setRno(Integer rno) {
		this.rno = rno;
	}

	public Integer getCno() {
		return cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
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

	public Integer getRef_cno() {
		return ref_cno;
	}

	public void setRef_cno(Integer ref_cno) {
		this.ref_cno = ref_cno;
	}

	public Integer getReply_cno() {
		return reply_cno;
	}

	public void setReply_cno(Integer reply_cno) {
		this.reply_cno = reply_cno;
	}

	public Integer getReply_lv() {
		return reply_lv;
	}

	public void setReply_lv(Integer reply_lv) {
		this.reply_lv = reply_lv;
	}

	public Integer getParent_cno() {
		return parent_cno;
	}

	public void setParent_cno(Integer parent_cno) {
		this.parent_cno = parent_cno;
	}

	@Override
	public String toString() {
		return "CommentVO [rno=" + rno + ", cno=" + cno + ", bno=" + bno + ", content=" + content + ", writer=" + writer
				+ ", regdate=" + regdate + ", ref_cno=" + ref_cno + ", reply_cno=" + reply_cno + ", reply_lv="
				+ reply_lv + ", parent_cno=" + parent_cno + "]";
	}

}
