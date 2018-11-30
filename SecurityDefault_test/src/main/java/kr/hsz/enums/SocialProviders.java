package kr.hsz.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import kr.hsz.security.social.KakaoUserProperty;
import kr.hsz.security.social.NaverUserProperty;
import kr.hsz.security.social.SocialUserProperty;
import lombok.Getter;

@Getter
public enum SocialProviders {

    KAKAO("https://kapi.kakao.com/v1/user/me", KakaoUserProperty.class),
    NAVER("https://openapi.naver.com/v1/nid/me", NaverUserProperty.class);

    private String userinfoEndpoint;
    private Class<? extends SocialUserProperty> propertyMetaclass;

    SocialProviders(String userinfoEndpoint, Class<? extends SocialUserProperty> propertyMetaclass) {
        this.userinfoEndpoint = userinfoEndpoint;
        this.propertyMetaclass = propertyMetaclass;
    }

    @JsonValue
    public String getProviderName() {
        return this.name();
    }

}
