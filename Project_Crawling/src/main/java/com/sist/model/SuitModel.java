package com.sist.model;

import java.text.DecimalFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.SuitDAO;
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
//            		
//            		// 링크
                   String d_image = "https:" + thumb.get(j).attr("src");
                   System.out.println(d_image);
            		
	               String url = "https://www.jjinsuit.com/" + link.get(j).attr("href"); 
	               System.out.println(url);
            		
	               Document doc2 = Jsoup.connect(url).get();
	               Element su_subject = doc2.selectFirst(".name");
	               System.out.println(su_subject.text());
//	               
	               Element su_content = doc2.selectFirst("tr:contains(상품간략설명) td"); // ***** 재확인 해야 함
	               String su_content_text = (su_content != null) ? su_content.text().trim() : "";
	               System.out.println(su_content_text);
//	               
	               Element su_delivery_text = doc2.selectFirst("tr:contains(택배비 안내) td");
	               String su_delivery = (su_delivery_text != null) ? su_delivery_text.text().trim() : "";
	               System.out.println(su_delivery);

	               Elements su_return_exchange = doc2.select("#prdDetail li:nth-child(2) a");
	               System.out.println(su_return_exchange.attr("https://" + "href")); // ****************안됨
	               
	               Element su_detail_image_jacket = doc2.selectFirst("div.cont img:nth-child(2)");
	               System.out.println("https://www.jjinsuit.com" + su_detail_image_jacket);
	               Element su_detail_image_shirts = doc2.selectFirst("div.cont img:nth-child(3)");
	               System.out.println("https://www.jjinsuit.com" + su_detail_image_shirts);
	               Element su_detail_image_acc_element = doc.selectFirst("img[ec-data-src='/0725new/images/tuxedo/acc.jpg']");
	               String su_detail_image_acc = (su_detail_image_acc_element != null) ? su_detail_image_acc_element.attr("ec-data-src") : "No Image.";
	               System.out.println("https://www.jjinsuit.com" + su_detail_image_acc);
	               
	               Element su_detail_image_size_element = doc.selectFirst("img[ec-data-src='/0725new/images/tuxedo/size.jpg']");
	               String su_detail_image_size = (su_detail_image_size_element != null)
	            		    ? su_detail_image_size_element.attr("ec-data-src")
	            		    : "No Image.";
	               System.out.println("https://www.jjinsuit.com" + su_detail_image_size);
	               
	               Element su_detail_image_info_element = doc.selectFirst("img[ec-data-src='/0725new/images/tuxedo/shopinfo.jpg']");
	               String su_detail_image_info = (su_detail_image_size_element != null)
	            		    ? su_detail_image_size_element.attr("ec-data-src")
	            		    : "No Image.";
	               System.out.println("https://www.jjinsuit.com" + su_detail_image_info);

	               Element su_price = doc2.selectFirst("div.sale_rate");
	               String formattedPrice = df.format(Long.parseLong(su_price.attr("item_price")));
	               System.out.println(formattedPrice + "원");	// 전부 18만원인거 수정해야 함

	               System.out.println("==========" + (j + 1) + "번 째 상세 정보 ==========");
            	} 
            	catch (Exception ex) {
            		ex.printStackTrace();
            	}
        }
//       }
         System.out.println("저장 완료.");
      } catch(Exception ex) {
    	  
      }
   }

}
