package com.example.boardpractice.auth.dto;

import com.example.boardpractice.domain.Member;
import com.example.boardpractice.domain.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        // 여기서 네이버와 카카오 등 구분 (ofNaver, ofKakao)
        if("kakao".equals(registrationId)){

        }

        return ofKaKao("id",attributes);
    }
    
    //카카오 데이터 받아오기
    private static OAuthAttributes ofKaKao(String userNameAttributeName, Map<String, Object> attributes) {
        //Kakao는 Kakao_account에 유저 정보가 있습니다. (email)
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        //Kakao_account안에 또 profile이라는 JSON객체가 있습니다.
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .picture((String) kakaoProfile.get("picture_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity(){
        return Member.MemberBuilder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.USER) // 기본 권한
                .build();
    }

}
