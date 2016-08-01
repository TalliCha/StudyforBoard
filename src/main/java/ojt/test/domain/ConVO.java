package ojt.test.domain;

import ojt.test.util.SearchKeyWord;

public class ConVO {
	int bno; // 글 번호

	int pno; // 페이지 번호
	int maxPage; // 검색 결과에 대한 페이지 갯수
	int pageSize; // 한번에 보여줄 페이지 수
	int naviSize; // 한번에 보여줄 네비 수
	
	int comm_pno; 
	int comm_maxPage;
	int comm_pageSize;
	int comm_naviSize;
	
	String keyWord; // 검색어
	String category; // 카테고리
	
	int pageStart;
	int comm_pageStart;

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
		setStart(); // pno 변경에 따른 start 번호 변경
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		setStart(); // pageSize 변경에 따른 start 번호 변경
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getNaviSize() {
		return naviSize;
	}

	public void setNaviSize(int naviSize) {
		this.naviSize = naviSize;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = SearchKeyWord.trans(keyWord);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	
	private void setStart() {
		this.pageStart = ( this.pno < 1 )?  0 : (this.pno - 1) * this.pageSize;
	}
	
	
	public int getComm_pno() {
		return comm_pno;
	}

	public void setComm_pno(int comm_pno) { // comm_pno 변경에 따른 comm_start 번호 변경
		this.comm_pno = comm_pno;
		setComm_Start();
	}

	public int getComm_maxPage() {
		return comm_maxPage;
	}

	public void setComm_maxPage(int comm_maxPage) {
		this.comm_maxPage = comm_maxPage;
	}

	public int getComm_pageSize() {
		return comm_pageSize;
	}
 
	public void setComm_pageSize(int comm_pageSize) { // comm_pageSize 변경에 따른 comm_start 번호 변경
		this.comm_pageSize = comm_pageSize;
		setComm_Start();
	}

	public int getComm_naviSize() {
		return comm_naviSize;
	}

	public void setComm_naviSize(int comm_naviSize) {
		this.comm_naviSize = comm_naviSize;
	}
	
	private void setComm_Start() {
		this.comm_pageStart = ( this.comm_pno < 1 )?  0 : (this.comm_pno - 1) * this.comm_pageSize;
	}

	@Override
	public String toString() {
		return "ConVO [bno=" + bno + ", pno=" + pno + ", maxPage=" + maxPage + ", pageSize=" + pageSize + ", naviSize="
				+ naviSize + ", comm_pno=" + comm_pno + ", comm_maxPage=" + comm_maxPage + ", comm_pageSize="
				+ comm_pageSize + ", comm_naviSize=" + comm_naviSize + ", keyWord=" + keyWord + ", category=" + category
				+ ", pageStart=" + pageStart + ", comm_pageStart=" + comm_pageStart + "]";
	}
	
}
