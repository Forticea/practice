package manager.icia.test;

import org.junit.Test;

import manager.icia.util.PagingUtil;
import manager.icia.vo.Pagination;

public class PagingUtilTest {
	@Test
	public void pagingTest(){
		for(int i=1; i<=15; i++){
			Pagination p = PagingUtil.getPagination(i, 106);
			System.out.print(p.getPageNo()+ " : ");
			if(p.getPrev()!=-1) System.out.print("이전으로 ");
			for(int j=p.getStartPage(); j<=p.getEndPage(); j++)
				System.out.print(j + " ");
			if(p.getNext()!=-1) System.out.print("다음으로");
			System.out.println();
		}
	}
}
