package com.koreait.spring;

import com.koreait.spring.vo.LocationCodeEntity;
import com.koreait.spring.vo.SearchDTO;
import com.sun.deploy.nativesandbox.comm.Response;
import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class ChanhoService {

    @Autowired
    private ChanhoMapper mapper;

    public List<LocationCodeEntity> selLocationCodeList(){
        return mapper.selLocationCodeList();
    }


    public void saveData(SearchDTO param){

        final String serivceKey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX%2BNgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA%3D%3D";
        final String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
        String decodeServiceKey = null;
        //서비스키를 URL 인코딩해주는 작업
        try {
            decodeServiceKey = URLDecoder.decode(serivceKey, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }

        final HttpHeaders HEADERS = new HttpHeaders();
        HEADERS.setAccept(Arrays.asList(MediaType.APPLICATION_XML));

        final HttpEntity<String>  entity = new HttpEntity<String>(HEADERS);

        String deal_ym = String.format("%s%02d", param.getDeal_year(),param.getDeal_month());

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("LAWD_CD", param.getExternal_cd())
                .queryParam("DEAL_YMD", deal_ym)
                .queryParam("serviceKey", decodeServiceKey)
                .build(false);

        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        ResponseEntity<String> respEntity = rest.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        String result = respEntity.getBody();

        System.out.println(result);


    }


}
