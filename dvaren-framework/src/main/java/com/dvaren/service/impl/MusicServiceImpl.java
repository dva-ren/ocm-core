package com.dvaren.service.impl;

import com.alibaba.fastjson2.JSON;
import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Song;
import com.dvaren.service.IMusicService;
import com.dvaren.utils.RestUtil;
import com.dvaren.utils.TextUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MusicServiceImpl implements IMusicService {

    private final static String HOST = "http://127.0.0.1:3000";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Song getSong(String id) throws ApiException {
        if(TextUtil.isEmpty(id)){
            throw new ApiException("参数错误");
        }
        Song song;
        String stringValue = stringRedisTemplate.opsForValue().get(id);
        if(TextUtil.isEmpty(stringValue)){
            song = MusicServiceImpl.fetchSong(id);
            stringRedisTemplate.opsForValue().set(id, JSON.toJSONString(song));
            stringRedisTemplate.expire(id, TextUtil.getSecondsNextEarlyMorning(1), TimeUnit.SECONDS);
        }
        else {
            song = JSON.parseObject(stringValue, Song.class);
        }
        return song;
    }

    private static Song fetchSong(String id) throws ApiException {
        String url = HOST + "/song/detail?ids=" + id;
        HttpHeaders headers = new HttpHeaders();
        Song song = new Song();
        try {
            String res = RestUtil.doGetRequest(url, headers);
            Pattern reg = Pattern.compile("\"name\":.*?\"(.*?)\",\"id\":(.*?),.*\"ar\":(.*?),\"alia\".*\"picUrl\":\"(.*?)\".*\"dt\":(.*?),");
            Matcher m = reg.matcher(res);
            if(m.find()){
                song.setName(m.group(1));
                song.setId(Integer.valueOf(m.group(2)));
                song.setArtist(m.group(3));
                song.setPic(m.group(4));
                song.setTime(Integer.valueOf(m.group(5)));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ApiException("API请求异常");
        }
        String url2 = HOST + "/song/url/v1?id=" + id + "&level=exhigh";
        try {
            String res = RestUtil.doGetRequest(url2, headers);
            Pattern reg = Pattern.compile("\"url\":.*?\"(.*?)\"");
            Matcher m1 = reg.matcher(res);
            if(m1.find()){
                song.setUrl(m1.group(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        String url3 = HOST + "/lyric?id=" + id;
        try{
            String res = RestUtil.doGetRequest(url3, headers);
            Pattern reg = Pattern.compile("\"lyric\":.*?\"(.*?)\"");
            Matcher m = reg.matcher(res);
            if(m.find()){
                song.setLrc(m.group(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return song;
    }

}
