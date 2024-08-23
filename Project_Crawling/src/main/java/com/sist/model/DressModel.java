package com.sist.model;

import java.text.DecimalFormat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.sist.dao.DressDAO;
import com.sist.vo.DressVO;

public class DressModel {
    String d_image = "";

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
                
                Elements img_temp = doc.select(".thumbnail a img");
                String thum = img_temp.attr("src");

                // 만약 첫 번째 큰따옴표와 두 번째 큰따옴표 사이의 문자열을 추출하려는 경우
                int startIndex = thum.indexOf("\"") + 1; // 첫 번째 큰따옴표 다음
                int endIndex = thum.indexOf("\"", startIndex); // 두 번째 큰따옴표

                if (startIndex > 0 && endIndex > startIndex) {
                    String d_image_temp = thum.substring(startIndex, endIndex);

                    // HTTP 또는 HTTPS로 시작하지 않는 경우 접두사 추가
                    if (d_image_temp != null && !d_image_temp.trim().isEmpty()) {
                        if (!d_image_temp.startsWith("http:") && !d_image_temp.startsWith("https:")) {
                            d_image = "https:" + d_image_temp; // 기본으로 https 사용
                        }
                    }
                }

                Elements link = doc.select("ul.prdList strong a");
                System.out.println(link);

                for (int j = 0; j < link.size(); j++) {
                    try {
                        // 링크
                        System.out.println(d_image);
                        String url = "https://www.labitorosa.com" + link.get(j).attr("href");
                        System.out.println(url);

                        Document doc2 = Jsoup.connect(url).get();

                        Element d_subject = doc2.selectFirst("div.headingarea");
                        int subIndex = d_subject.text().indexOf("[");
                        System.out.println(d_subject.text().indexOf("["));

                        Element d_content = doc2.selectFirst("#accordInfo li:first-child");
                        System.out.println(d_content.text());

                        Element d_delivery = doc2.selectFirst("span.delv_price_B");
                        System.out.println(d_delivery.text());

                        Element d_return_exchange_temp = doc2.selectFirst("span.delv_price_B");
                        String d_return_exchange = d_return_exchange_temp.text();

                        Element d_detail_image = doc2.selectFirst("#prdDetail > div:nth-child(2) img:nth-child(1)");
                        System.out.println("https:" + d_detail_image.attr("ec-data-src"));

                        Element d_detail_image2_temp = doc2.selectFirst("#prdDetail > div:nth-child(2) img:nth-child(2)");
                        String d_detail_image2 = (d_detail_image2_temp != null) ? "https:" + d_detail_image2_temp.attr("ec-data-src") : "";
                        System.out.println(d_detail_image2);

                        Element d_detail_image3 = doc2.selectFirst("#prdDetail > div:nth-child(2) img:nth-child(3)");
                        System.out.println("https:" + d_detail_image3.attr("ec-data-src"));

                        Element d_price_temp = doc2.selectFirst("span.quantity_price");
                        String priceText = d_price_temp.text().replaceAll("[^0-9]", "");
                        int d_price = Integer.parseInt(priceText);

                        // 가격 포맷팅
                        DecimalFormat df = new DecimalFormat("#,###");
                        String formattedPrice = df.format(d_price) + "원";
                        System.out.println(formattedPrice);

                        System.out.println("===========" + (i - 1) + (j + 1) + "번째 상세보기 완료=============");

                        DressVO vo = new DressVO();
                        vo.setD_no(0);
                        vo.setD_image(d_image); // 포맷팅된 d_image 저장
                        vo.setD_subject(subIndex != -1 ? d_subject.text().substring(0, subIndex) : d_subject.text());
                        vo.setD_content(d_content.text());
                        vo.setD_delivery(d_delivery.text());
                        vo.setD_return_exchange(d_return_exchange);
                        vo.setD_detail_image(d_detail_image.attr("ec-data-src"));
                        vo.setD_detail_image2(d_detail_image2);
                        vo.setD_detail_image3(d_detail_image3.attr("ec-data-src"));
                        vo.setD_price(formattedPrice); // 포맷팅된 가격 저장

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
