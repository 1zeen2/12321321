package com.sist.model;

import java.text.DecimalFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;
import com.sist.vo.DressVO;

public class DressModel {
	
	 private static final DecimalFormat df = new DecimalFormat("#,###");

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
            Element d_image = doc.selectFirst("div.xans-element- ul li:first-child div a img");
            System.out.println("https:" + d_image.text());
            Elements link = doc.select("ul.prdList strong a");
            for(int j=0; j < link.size(); j++ ) {
            	try {
            		
            		// 링크
	               String url = "https://www.labitorosa.com" + link.get(j).attr("href"); 
	               System.out.println(url);
	               Document doc2 = Jsoup.connect(url).get();
	               Element d_subject = doc2.selectFirst("div.headingarea");
	               System.out.println(d_subject.text());
	               Element d_price = doc2.selectFirst("span.quantity_price");
	               String formattedPrice = df.format(Long.parseLong(d_price.text()));
	               System.out.println(formattedPrice + "원");
	               Element d_content = doc2.selectFirst("div#accordInfo ul li:first-child .contents");
	               System.out.println(d_content.text());
	               Element d_delivery = doc2.selectFirst("span.delv_price_B");
	               System.out.println(d_delivery.text());
	               Elements d_return_exchange = doc2.select("li.depth1 div:nth-child(2) div:nth-child(2)");
	               System.out.println(d_return_exchange.text());
	               Element d_detail_image = doc2.selectFirst("#prdDetail > div:nth-child(2) img");
	               System.out.println("https:" + d_detail_image.attr("ec-data-src"));
//	               Element score = doc2.selectFirst(".product li div div a img");
//	               Element meta = doc.selectFirst("meta[property=og:image]");
//	               String imageUrl = meta.attr("content");
//	               System.out.println("Image URL: " + imageUrl);
//	                
//	               // URL 출력
//	               System.out.println(imageUrl);
	               
	               
	               
	               
	               
	               
//	               String imageUrl = doc.selectFirst("meta[property=og:image]").attr("content");
//	               System.out.println("https:" + imageUrl);
	               System.out.println("==============================================================");
	               
//	               System.out.println(name.text().substring(0, name.text().indexOf("[")));
//	               System.out.println(phone.text());
//	               System.out.println(address.text());
//	               Element theme = doc2.selectFirst("div.areaBasic dd.Theme");
//	               System.out.println(theme.text());
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
