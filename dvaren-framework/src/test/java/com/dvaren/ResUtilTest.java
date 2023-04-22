package com.dvaren;

import com.dvaren.domain.entity.Song;
import com.dvaren.utils.RestUtil;
import com.dvaren.utils.TextUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResUtilTest {

    private final static String HOST = "https://api.dvaren.xyz";

    @Test
    void Test1(){
        String url = HOST + "/song/detail?ids=347230";
        HttpHeaders headers = new HttpHeaders();
        String s = RestUtil.doGetRequest(url, headers);
        System.out.println(s);
    }

    @Test
    void Test2(){
        String res = "{\"songs\":[{\"name\":\"海阔天空\",\"id\":347230,\"pst\":0,\"t\":0,\"ar\":[{\"id\":11127,\"name\":\"Beyond\",\"tns\":[],\"alias\":[]}],\"alia\":[],\"pop\":100,\"st\":0,\"rt\":\"600902000004240302\",\"fee\":1,\"v\":113,\"crbt\":null,\"cf\":\"\",\"al\":{\"id\":34209,\"name\":\"海阔天空\",\"picUrl\":\"https://p2.music.126.net/8y8KJC1eCSO_vUKf2MyZwA==/109951165796899183.jpg\",\"tns\":[],\"pic_str\":\"109951165796899183\",\"pic\":109951165796899180},\"dt\":326000,\"h\":{\"br\":320001,\"fid\":0,\"size\":13042460,\"vd\":-5649,\"sr\":44100},\"m\":{\"br\":192001,\"fid\":0,\"size\":7825493,\"vd\":-3083,\"sr\":44100},\"l\":{\"br\":128001,\"fid\":0,\"size\":5217010,\"vd\":-1486,\"sr\":44100},\"sq\":{\"br\":798710,\"fid\":0,\"size\":32547445,\"vd\":-5601,\"sr\":44100},\"hr\":null,\"a\":null,\"cd\":\"1\",\"no\":1,\"rtUrl\":null,\"ftype\":0,\"rtUrls\":[],\"djId\":0,\"copyright\":1,\"s_id\":0,\"mark\":8192,\"originCoverType\":1,\"originSongSimpleData\":null,\"tagPicList\":null,\"resourceState\":true,\"version\":113,\"songJumpInfo\":null,\"entertainmentTags\":null,\"awardTags\":null,\"single\":0,\"noCopyrightRcmd\":null,\"rtype\":0,\"rurl\":null,\"mst\":9,\"cp\":7002,\"mv\":376199,\"publishTime\":746812800000,\"tns\":[\"Boundless Oceans, Vast Skies\"]}],\"privileges\":[{\"id\":347230,\"fee\":1,\"payed\":0,\"st\":0,\"pl\":0,\"dl\":0,\"sp\":0,\"cp\":0,\"subp\":0,\"cs\":false,\"maxbr\":999000,\"fl\":0,\"toast\":false,\"flag\":260,\"preSell\":false,\"playMaxbr\":999000,\"downloadMaxbr\":999000,\"maxBrLevel\":\"lossless\",\"playMaxBrLevel\":\"lossless\",\"downloadMaxBrLevel\":\"lossless\",\"plLevel\":\"none\",\"dlLevel\":\"none\",\"flLevel\":\"none\",\"rscl\":null,\"freeTrialPrivilege\":{\"resConsumable\":true,\"userConsumable\":false,\"listenType\":null},\"chargeInfoList\":[{\"rate\":128000,\"chargeUrl\":null,\"chargeMessage\":null,\"chargeType\":1},{\"rate\":192000,\"chargeUrl\":null,\"chargeMessage\":null,\"chargeType\":1},{\"rate\":320000,\"chargeUrl\":null,\"chargeMessage\":null,\"chargeType\":1},{\"rate\":999000,\"chargeUrl\":null,\"chargeMessage\":null,\"chargeType\":1}]}],\"code\":200}";
        Pattern reg = Pattern.compile("\"name\":.*?\"(.*?)\",\"id\":(.*?),.*\"ar\".*?\"name\":\"(.*?)\",.*\"picUrl\":\"(.*?)\".*\"dt\":(.*?),");
        Song song = new Song();
        Matcher m = reg.matcher(res);
        if(m.find()){
            song.setName(m.group(1));
            song.setId(Integer.valueOf(m.group(2)));
            song.setArtist(m.group(3));
            song.setPic(m.group(4));
            song.setTime(Integer.valueOf(m.group(5)));
        }
        System.out.println(song);
    }

    @Test
    void time(){
        Long secondsNextEarlyMorning = TextUtil.getSecondsNextEarlyMorning(2);
        System.out.println(secondsNextEarlyMorning);
    }
}
