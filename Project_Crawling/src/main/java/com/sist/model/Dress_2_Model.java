package com.sist.model;

import java.text.DecimalFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;
import com.sist.vo.DressVO;

public class Dress_2_Model {
	
	 private static final DecimalFormat df = new DecimalFormat("#,###");

   public static void main(String[] args) {
      Dress_2_Model dm = new Dress_2_Model();
      dm.dressData();
   }

   public void dressData() {
      DressDAO dao = DressDAO.newInstance();
      try {
            Document doc= Jsoup
                  .connect("https://ygdress.com/product/list.html?cate_no=36&page=1").get();
            Elements link = doc.select("#anchorBoxId_31304 > div.description > p > a");
            for(int j=0; j < link.size(); j++ ) {
            	try {
            		// 상세정보부터 이어서
//            		// 링크
//            	   Element d_image = doc.selectFirst("div.xans-element- div:nth-child(2) ul li:first-child div a img");
//                   System.out.println("https:" + d_image.attr("src"));
//                   System.out.println("=====");
//	               String url = "https://www.labitorosa.com" + link.get(j).attr("href"); 
//	               System.out.println(url);
//	               Document doc2 = Jsoup.connect(url).get();
//	               Element d_subject = doc2.selectFirst("div.headingarea");
//	               System.out.println(d_subject.text());
//	               Element d_price = doc2.selectFirst("span.quantity_price");
//	               String formattedPrice = df.format(Long.parseLong(d_price.text()));
//	               System.out.println(formattedPrice + "원");
//	               Element d_content = doc2.selectFirst("div#accordInfo ul li:first-child .contents");
//	               System.out.println(d_content.text());
//	               Element d_delivery = doc2.selectFirst("span.delv_price_B");
//	               System.out.println(d_delivery.text());
//	               Elements d_return_exchange = doc2.select("li.depth1 div:nth-child(2) div:nth-child(2)");
//	               System.out.println(d_return_exchange.text());
//	               Element d_detail_image = doc2.selectFirst("#prdDetail > div:nth-child(2) img");
//	               System.out.println("https:" + d_detail_image.attr("ec-data-src"));
//	               System.out.println("==============================================================");
//  
            	} 
            	catch (Exception ex) {
            		ex.printStackTrace();
            	}
        }
         System.out.println("저장 완료.");
      }catch(Exception ex) {}
   }

}
