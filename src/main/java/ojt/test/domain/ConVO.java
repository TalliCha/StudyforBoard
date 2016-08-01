package ojt.test.domain;

import ojt.test.util.SearchKeyWord;

public class ConVO {
	int bno; // 글 번호
	int pno; // 페이지 번호
	int pageSize; // 한번에 보여줄 페이지 수
	int maxPage; // 검색 결과에 대한 페이지 갯수
	int naviSize; // 한번에 보여줄 네비 수
	
	String keyWord; // 검색어
	String category; // 카테고리
	
	int pageStart;

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

	@Override
	public String toString() {
		return "ConVO [bno=" + bno + ", pno=" + pno + ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", naviSize="
				+ naviSize + ", keyWord=" + keyWord + ", category=" + category + ", pageStart=" + pageStart + "]";
	}
	
}
