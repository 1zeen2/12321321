package com.sist.model;

import java.text.DecimalFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.SuitDAO;
import com.sist.vo.DressVO;
import com.sist.vo.SuitVO;

public class SuitModel {
	
	 private static final DecimalFormat df = new DecimalFormat("#,###");

   public static void main(String[] args) {
      SuitModel sm = new SuitModel();
      sm.suitData();
   }

   public void suitData() {
      SuitDAO dao = SuitDAO.newInstance();
      try {
            Document doc= Jsoup
            		.connect("https://www.jjinsuit.com/product/list.html?cate_no=55").get();
            Elements link = doc.select("div.prdImg_thumb a");
            System.out.println(link);
            Elements thumb = doc.select("div.prdImg_thumb a img");
            
            for(int j=0; j <= 140; j++ ) {
            	try {
                   String d_image = "https:" + thumb.get(j).attr("src");
                   System.out.println(d_image);
            		
	               String url = "https://www.jjinsuit.com/" + link.get(j).attr("href"); 
	               System.out.println(url);
            		
	               Document doc2 = Jsoup.connect(url).get();
	               Element su_subject = doc2.selectFirst(".name");
	               System.out.println(su_subject.text());

	               Element su_content_Text = doc2.select("tr:has(th:contains(상품간략설명)) td").first();
	               String su_content = (su_content_Text != null) ? su_content_Text.text().trim() : "";
	               System.out.println(su_content);

	               Element su_delivery_text = doc2.selectFirst("tr:contains(택배비 안내) td");
	               String su_delivery = (su_delivery_text != null) ? su_delivery_text.text().trim() : "";
	               System.out.println(su_delivery);

	               Elements su_return_exchange = doc2.select("#prdDetail li:nth-child(2) a");
	               System.out.println(su_return_exchange.attr("https://" + "href")); 
	            
	               Elements su_detail_images = doc2.select("div.cont img"); // div.cont 내부의 모든 img 태그 선택

	               StringBuilder su_detail_image_urls = new StringBuilder(); // 이미지 URL들을 저장할 StringBuilder

	               for (Element img : su_detail_images) {
	                   
	                   String imgSrc = img.attr("ec-data-src"); // ec-data-src 속성의 값을 가져옴
	                   
	                   if (!imgSrc.startsWith("http")) {
	                       imgSrc = "https:" + imgSrc; // 상대 경로를 절대 경로로 변환
	                   }
	                   
	                   su_detail_image_urls.append(imgSrc).append(", "); // StringBuilder에 추가
	               }

	               String su_detail_image = su_detail_image_urls.toString();
	               if (su_detail_image.endsWith(", ")) { 
	            	   su_detail_image = su_detail_image.substring(0, su_detail_image.length() - 2); // 마지막에 추가된 쉼표와 공백을 제거
	               }

	               System.out.println(su_detail_image);

	               
	               Element su_price = doc2.selectFirst("div.sale_rate");
	               String formattedPrice = df.format(Long.parseLong(su_price.attr("item_price")));
	               System.out.println(formattedPrice + "원");	// 전부 18만원인거 수정해야 함

	               System.out.println("==========" + (j + 1) + "번 째 상세 정보 ==========");
	               
	               SuitVO vo = new SuitVO();
	                  vo.setSu_no(j);
	                  vo.setSu_image(d_image);
	                  vo.setSu_subject(su_subject.text());
	                  vo.setSu_content(su_content);
	                  vo.setSu_delivery(su_delivery);
	                  vo.setSu_return_exchange(su_return_exchange.text());
	                  vo.setSu_detail_image(su_detail_image);
	                  vo.setSu_price(su_price.text());
	                  
	                  dao.suitInsert(vo);
            	} catch (Exception ex) {
            		ex.printStackTrace();
            	}
            }
         System.out.println("저장 완료.");
      } catch(Exception ex) {
    	  
      }
   }

}
