package manager.icia.util;

import manager.icia.vo.Pagination;

public class PagingUtil {
	public static Pagination getPagination(int pageNo, int cntOfArticle){
		int articlePerPage = 10;
		int pagePerBlock = 5;
		// (사원숫자-1/페이지당사원수)+1
		int countOfPage = (cntOfArticle-1)/articlePerPage + 1;
		// 1-5 ->1, 6-10 ->2 11-15 ->3
		// (페이지숫자-1/블록당페이지수)+1
		int blockNo = (pageNo-1)/pagePerBlock + 1;
		// (페이지번호-1)*페이지당사원수+1
		int startArticle = (pageNo-1)*articlePerPage + 1;
		// 페이지번호*페이지당 사원수		> 사원숫자->사원숫자로
		int endArticle = pageNo*articlePerPage;
		if(endArticle>cntOfArticle) 
			endArticle = cntOfArticle;
		int startPage = (blockNo-1)*pagePerBlock+1;
		// 블록번호*블록당 페이지수	
		int endPage = blockNo*pagePerBlock;
		if(endPage>countOfPage)
			endPage = countOfPage;
		int prev = startPage - 1;
		if(prev<1) prev = -1;
		int next = endPage + 1;
		if(next>countOfPage) next=-1;
		Pagination pagination = new Pagination();
		pagination.setPageNo(pageNo);
		pagination.setEndArticle(endArticle);
		pagination.setEndPage(endPage);
		pagination.setNext(next);
		pagination.setPrev(prev);
		pagination.setStartArticle(startArticle);
		pagination.setStartPage(startPage);
		return pagination;
	}
}
