package com.gachon.UntactPhotoBooth.Service;


import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

@RequiredArgsConstructor
@Service
public class DetectFaceService {

    @Value("${clientId}")
    private String clientIdReal;

    @Value("${clientSecret}")
    private String clientSecretReal;

    public String faceAPI(ArrayList<String> imgUrls){


        StringBuffer reqStr = new StringBuffer();
        String clientId = clientIdReal;//애플리케이션 클라이언트 아이디;
        String clientSecret = clientSecretReal;//애플리케이션 클라이언트 시크릿;

        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgUrl = imgUrls.get(0);
            String[] split = imgUrl.split("\\.");
            String imgFormatFromImgUrls = split[split.length-1];
            String imgFilePath = "src/main/webapp/static/assets/test_image." + imgFormatFromImgUrls; // src/main/resources/static/test_image.
            String imgFormat = imgFormatFromImgUrls;

            getImageFromUrl(imgUrl, imgFilePath, imgFormat);
            System.out.println("S3 저장 후 NEW FILE 이전");
            //String imgFile =
            File uploadFile = new File(imgFilePath);

            String apiURL = "https://openapi.naver.com/v1/vision/face"; // 얼굴 감지
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                return response.toString();
            } else {
                System.out.println("error !!!");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void getImageFromUrl(String imgUrl, String imgFilePath, String imgFormat){
        try{
            BufferedImage image = ImageIO.read(new URL(imgUrl));
            File imgFile = new File(imgFilePath);
            ImageIO.write(image, imgFormat, imgFile);
        } catch (Exception e){

        }
    }
}