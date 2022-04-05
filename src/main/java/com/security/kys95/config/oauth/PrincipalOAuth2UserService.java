package com.security.kys95.config.oauth;

import com.security.kys95.config.auth.PrincipalDetails;
import com.security.kys95.model.User;
import com.security.kys95.config.oauth.provider.FacebookUserInfo;
import com.security.kys95.config.oauth.provider.GoogleUserInfo;
import com.security.kys95.config.oauth.provider.NaverUserInfo;
import com.security.kys95.config.oauth.provider.OAuth2UserInfo;
import com.security.kys95.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo userInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            userInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            userInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")){
            userInfo = new NaverUserInfo((Map) oAuth2User.getAttributes().get("response"));
        }


        String provider = userInfo.getProvider();
        String providerId = userInfo.getProviderId();
        String username = provider + "_" + providerId;
        String password = bCryptPasswordEncoder.encode("kys95");
        String email =  userInfo.getEmail();
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername("username");
        if(userEntity == null){
            userEntity = new User().builder()
                    .username(username)
                    .provider(provider)
                    .providerId(providerId)
                    .password(password)
                    .email(email)
                    .role(role)
                    .build();
            userRepository.save(userEntity);
        }

        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
