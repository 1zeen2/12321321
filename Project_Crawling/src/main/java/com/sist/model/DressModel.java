package com.sist.model;

import java.text.DecimalFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.sist.dao.DressDAO;
import com.sist.vo.DressVO;

public class DressModel {
	
	private static final DecimalFormat df = new DecimalFormat("#,###");

   public static void main(String[] args) {
      DressModel dm = new DressModel();
      dm.dressData();
   }

   public void dressData() {
      DressDAO dao = DressDAO.newInstance();
      try {
         for (int i = 1; i <= 5; i++) {
            Document doc = Jsoup
                  .connect("https://labitorosa.com/product/list.html?cate_no=24&page=" + i).get();
            Elements link = doc.select("ul.prdList strong a");
            System.out.println(link);
            Elements thumb = doc.select(".thumbnail > a img");
            for (int j = 0; j < link.size(); j++) {
               try {
                  // 링크
                  String d_image = "https:" + thumb.get(j).attr("src");
                  System.out.println(d_image);
                  String url = "https://www.labitorosa.com" + link.get(j).attr("href");
                  System.out.println(url);
                  Document doc2 = Jsoup.connect(url).get();
                  Element d_subject = doc2.selectFirst("div.headingarea");
                  System.out.println(d_subject.text());
                  Element d_content = doc2.selectFirst("#accordInfo li:first-child");
                  System.out.println(d_content.text());
                  Element d_delivery = doc2.selectFirst("span.delv_price_B");
                  System.out.println(d_delivery.text()); 
                  
//                  Elements d_return_exchange = doc2.select("li.depth1 div:nth-child(2) div:nth-child(2)");
//                  System.out.println(d_return_exchange.text());
               
                  Element d_return_exchange = doc2.select("div.contents").first(); // 첫 번째 div.contents 요소만 선택
                  String content = d_return_exchange.text(); // 텍스트를 추출
                  System.out.println(content); // 원하는 부분만 출력

                  
                  Element d_detail_image = doc2.selectFirst("#prdDetail > div:nth-child(2) img");
                  System.out.println("https:" + d_detail_image.attr("ec-data-src"));
                  Element d_price = doc2.selectFirst("span.quantity_price");
                  String formattedPrice = df.format(Long.parseLong(d_price.text().replaceAll("[^0-9]", "")));
                  System.out.println(formattedPrice + "원");
                  System.out.println("===========" + i + (j + 1) + "번째 상세보기 완료=============");
                  
                  DressVO vo = new DressVO();
                  vo.setD_no(0);
                  vo.setD_image(d_image);
//	              vo.setD_subject(d_subject.text().substring(0, d_subject.text().indexOf("[")));
                  vo.setD_subject(d_subject.text());
                  vo.setD_content(d_content.text());
                  vo.setD_delivery(d_delivery.text());
                  vo.setD_return_exchange(d_return_exchange.text());
                  vo.setD_detail_image(d_detail_image.attr("ec-data-src"));
                  vo.setD_price(d_price.text());
                  
                  dao.dressInsert(vo);
               } catch (Exception ex) {
                  ex.printStackTrace();
               }
            }
         }
         System.out.println("저장 완료.");
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}

