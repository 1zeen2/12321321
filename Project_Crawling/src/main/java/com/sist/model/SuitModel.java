package com.sist.model;

import java.text.DecimalFormat;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.sist.dao.SuitDAO;
import com.sist.vo.SuitVO;

public class SuitModel {

    private static final DecimalFormat df = new DecimalFormat("#,###");
    private static final Random random = new Random(); // Random 객체 생성

    public static void main(String[] args) {
        SuitModel sm = new SuitModel();
        sm.suitData();
    }

    public void suitData() {
        SuitDAO dao = SuitDAO.newInstance();
        try {
            Document doc = Jsoup.connect("https://www.jjinsuit.com/product/list.html?cate_no=55").get();
            Elements link = doc.select("div.prdImg_thumb a");
            // '/tiny/'가 포함된 이미지 선택
//            Elements thumb = doc.select("div.prdImg_thumb img[src*='/tiny/']");
//            Elements thumb = doc.select("div.prdImg_thumb a img:nth-child(2)");
//            Elements thumb = doc.select("div.prdImg_thumb img.prdImg_hover");
            
            for (int j = 0; j < 141; j++) {
            	try {
//                    String su_image = "https:" + thumb.get(j).attr("src");
//                    if (su_image != null && !su_image.trim().isEmpty()) {
//                        if (!su_image.startsWith("http:") && !su_image.startsWith("https:")) {
//                            su_image = "https:" + su_image; // 기본으로 https 사용
//                        }
//                    }
//                    System.out.println(su_image);

                    // 링크와 관련된 URL 처리
                    String url = "https://www.jjinsuit.com/" + link.get(j).attr("href");
                    System.out.println(url);
                    
                    Document doc2 = Jsoup.connect(url).get();
                    
                    Element su_image_temp = doc2.selectFirst(".thumbnail_hidden img");
                    String su_image = su_image_temp.attr("src");
                    su_image = formatImageUrl(su_image);
                    System.out.println(su_image);

//                    Document doc2 = Jsoup.connect(url).get();
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
                    Elements su_detail_images = doc2.select("div.cont img");
                    StringBuilder su_detail_image_urls = new StringBuilder();

                    for (Element img : su_detail_images) {
                        String imgSrc = img.attr("ec-data-src");
                        if (!imgSrc.startsWith("//jjinhomme")) {
                        	  imgSrc = "https://www.jjinsuit.com" + imgSrc;
                        }
                        su_detail_image_urls.append(imgSrc).append(", ");
                    }

                    String su_detail_image = su_detail_image_urls.toString();
                    if (su_detail_image.endsWith(", ")) {
                        su_detail_image = su_detail_image.substring(0, su_detail_image.length() - 2);
                    }
                    System.out.println(su_detail_image);

                    // 가격을 180,000원에서 450,000원 사이의 랜덤 값으로 설정
                    int minPrice = 180000;
                    int maxPrice = 450000;
                    int price = minPrice + (random.nextInt((maxPrice - minPrice) / 5000 + 1) * 5000);
                    String formattedPrice = df.format(price) + "원";
                    System.out.println(formattedPrice);

                    System.out.println("==========" + (j + 1) + "번 째 상세 정보 ==========");

                    SuitVO vo = new SuitVO();
                    vo.setSu_no(j);
                    vo.setSu_image(su_image);
                    vo.setSu_subject(su_subject.text());
                    vo.setSu_content(su_content);
                    vo.setSu_delivery(su_delivery);
                    vo.setSu_return_exchange(su_return_exchange.text());
                    vo.setSu_detail_image(su_detail_image);
                    vo.setSu_price(formattedPrice); // 가격을 문자열로 설정

                    dao.suitInsert(vo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("저장 완료.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private String formatImageUrl(String imgSrc) {
        if (imgSrc == null || imgSrc.isEmpty()) {
            return "";
        }
        if (imgSrc.startsWith("//")) {
            imgSrc = "https:" + imgSrc;
        } else if (imgSrc.startsWith("/")) {
            imgSrc = "https://www.jjinsuit.com" + imgSrc;
        }

        // 이미지 URL이 'big'을 포함하는 경우에만 반환
        if (imgSrc.contains("/big/")) {
            return imgSrc;
        }
        
        return "";	
	}
}