package ojt.test.domain;

import org.springframework.web.multipart.MultipartFile;

public class UploadVO {
	private int fno;
	private int bno;
	private String original_fname;
	private String upload_fname;
	private Long fsize;
	private String ftype;
	
	private MultipartFile uploadFile;

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getOriginal_fname() {
		return original_fname;
	}

	public void setOriginal_fname(String original_fname) {
		this.original_fname = original_fname;
	}

	public String getUpload_fname() {
		return upload_fname;
	}

	public void setUpload_fname(String upload_fname) {
		this.upload_fname = upload_fname;
	}

	public Long getFsize() {
		return fsize;
	}

	public void setFsize(Long fsize) {
		this.fsize = fsize;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	@Override
	public String toString() {
		return "UploadVO [fno=" + fno + ", bno=" + bno + ", original_fname=" + original_fname + ", upload_fname="
				+ upload_fname + ", fsize=" + fsize + ", ftype=" + ftype + ", uploadFile=" + uploadFile + "]";
	}

}
