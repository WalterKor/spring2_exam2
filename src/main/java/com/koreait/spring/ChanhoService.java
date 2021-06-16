package com.koreait.spring;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreait.spring.vo.InsertEntity;
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

        List<ApartmentInfoEntity> dbList = mapper.selApartmentInfoList(param);

        if(dbList.size() > 0){
            return;
        }



        final String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
        final String serivceKey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX%2BNgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA%3D%3D";
        final String decodeServiceKey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX+NgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA==";
//        String decodeServiceKey = null;
        //서비스키를 URL 인코딩해주는 작업

//        try {
//            decodeServiceKey = URLDecoder.decode(serivceKey, "UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        /*
        XML로 받고싶다.
        final HttpHeaders HEADERS = new HttpHeaders();
        HEADERS.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        final HttpEntity<String>  entity = new HttpEntity<String>(HEADERS);
        */

        final HttpHeaders HEADERS = new HttpHeaders();
        HEADERS.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final HttpEntity<String>  entity = new HttpEntity<String>(HEADERS);



        String deal_ym = String.format("%s%02d", param.getDeal_year(),param.getDeal_month());

        //쿼리스트링 보내주는 부분
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("LAWD_CD", param.getExternal_cd())
                .queryParam("DEAL_YMD", deal_ym)
                .queryParam("serviceKey", decodeServiceKey)
                .queryParam("numOfRows", "1000")
                .build(false); //인코딩 2번하게되면 서로 다른 값이 되기때문에

//        String UrlWithParam = url + "?LAWD_CD=" + param.getExternal_cd()+"&DEAL_YMD=" + deal_ym +


        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        //rest.exchange에서 인코딩을 한다. build true로 하면 인코딩이된다.
        ResponseEntity<String> respEntity = rest.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        String result = respEntity.getBody(); //result 문자열을 받아온거를 자바객체로 바꿔야한다.

        //받은 제이슨형태를 자바객체로 바꿔주는 일
        ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = null;
        ApartmentInfoEntity[] list = null;
        try {
            jsonNode = om.readTree(result);

            //객체화해서 값을 넣어준다
            list = om.treeToValue(jsonNode.path("response").path("body").path("items").path("item"),
            ApartmentInfoEntity[].class);


            System.out.println("list.length : " +list.length);
        }catch (Exception e){
            e.printStackTrace();
        }

        for (ApartmentInfoEntity item : list){
            item.setLocation_cd(Integer.parseInt(param.getExternal_cd()));
            mapper.insApartmentInfoArr(item);
        }



    }


}
