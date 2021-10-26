package com.gachon.UntactPhotoBooth.Controller;

import com.gachon.UntactPhotoBooth.Configuration.Oauth.LoginUser;
import com.gachon.UntactPhotoBooth.Configuration.Oauth.dto.SessionUser;
import com.gachon.UntactPhotoBooth.Service.DetectFaceService;
import com.gachon.UntactPhotoBooth.Service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;


/**
 * 얼굴분석 컨트롤러 ( 클라 <--> 네이버 클로바 )
 * AWS S3 서비스 의존 추가
 */

@Slf4j
@RequiredArgsConstructor
@Controller
public class DetectFaceController {

    private final DetectFaceService detectFaceService;
    private final S3Service s3Service;

    @PostMapping("/face")
    public String detectFace(Model model, @RequestParam("files") MultipartFile[] files, @LoginUser SessionUser user) throws JSONException {
        ArrayList<String> imgUrl = s3Service.uploadImageToS3(files);
        String result = detectFaceService.faceAPI(imgUrl);
        JSONObject jsonObject = new JSONObject(result);
        JSONArray face = jsonObject.getJSONArray("faces");
        JSONObject face_list = face.getJSONObject(0);
        JSONObject gender = face_list.getJSONObject("gender");
        JSONObject age = face_list.getJSONObject("age");
        JSONObject emotion = face_list.getJSONObject("emotion");
        JSONObject pose = face_list.getJSONObject("pose");

        JSONObject imgObject = new JSONObject();

        imgObject.put("image", imgUrl.get(0));

        if(user != null){ // @LoginUser 를 통해 이제 어느 컨트롤러에서도 세션 정보를 가져올 수 있게 되었다.
            model.addAttribute("userEmail", user.getEmail()); // 세션에 값이 있을때만 유저 이름이 보이게 하고 그 외엔 로그인 버튼
        }

        model.addAttribute("gender", gender);
        model.addAttribute("age", age);
        model.addAttribute("emotion", emotion);
        model.addAttribute("pose", pose);
        model.addAttribute("image", imgObject);
        return "/result";
    }
}
