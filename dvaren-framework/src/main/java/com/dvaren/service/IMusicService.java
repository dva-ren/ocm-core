package com.dvaren.service;

import com.dvaren.config.ApiException;
import com.dvaren.domain.entity.Song;

public interface IMusicService {

    Song getSong(String id) throws ApiException;

}
