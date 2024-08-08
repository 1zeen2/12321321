package com.sist.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;
import com.sist.vo.DressVO;

public class DressModel {

   public static void main(String[] args) {
      DressModel dm = new DressModel();
      dm.dressData();
   }

   public void dressData() {
      DressDAO dao = DressDAO.newInstance();
      int k = 1;
      try {
         for(int i = 1; i <= 5; i++) {
            Document doc= Jsoup
                  .connect("https://labitorosa.com/product/list.html?cate_no=24&page="+i).get();
            Elements link = doc.select("ul.prdList strong a");
            for(int j=0; j < link.size(); j++ ) {
            	try {
            		
            		// 링크
	               String url = "https://www.labitorosa.com" + link.get(j).attr("href");
	               System.out.println(url);
	               
	               Document doc2 = Jsoup.connect(url).get();
	               Element content=doc2.selectFirst("div#accordInfo ul li:first-child .contents");
	               System.out.println(content.text());
	               Element img = doc2.selectFirst("#prdDetail > div:nth-child(2) img");
	               System.out.println("https:" + img.attr("ec-data-src"));
	               System.out.println("==============================================================");
	               
	               
//	               System.out.println("업체 번호 : " + k);
//	               System.out.println(name.text().substring(0, name.text().indexOf("[")));
//	               Element type = doc2.selectFirst("div.areaBasic dd.type");
//	               System.out.println(type.text());
//	               Element phone = doc2.selectFirst("div.areaBasic dd.tel1");
//	               System.out.println(phone.text());
//	               Element address = doc2.selectFirst("div.areaBasic dd.add1");
//	               System.out.println(address.text());
//	               Element score = doc2.selectFirst("div.areaBasic span.total");
//	               System.out.println(score.text());
//	               Element theme = doc2.selectFirst("div.areaBasic dd.Theme");
//	               System.out.println(theme.text());
//	               Element content = doc2.selectFirst("div.article div#info_ps_f");
//	               System.out.println(content.text());
//	               System.out.println("==================================================");
//	               DressVO vo = new DressVO();
//	               vo.setD_content(content.text());
//	               vo.setD_image(img.attr("src"));
//	               vo.setName(name.text());
//	               vo.setPhone(phone.text());
//	               vo.setType(type.text());
//	               vo.setAddress(address.text());
//	               vo.setTheme(theme.text());
//	               vo.setScore(Double.parseDouble(score.text()));
//	               dao.foodInsert(vo);
//	               
            	} 
            	catch (Exception ex) {
            		ex.printStackTrace();
            	}
//            	k++;
        }
       }
         System.out.println("저장 완료.");
      }catch(Exception ex) {}
   }

}
