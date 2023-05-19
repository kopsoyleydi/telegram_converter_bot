package com.example.telerambot.service;


import com.example.telerambot.entities.TelegramDatas;
import com.example.telerambot.repository.AppReposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImp implements RequestService{
    @Autowired
    private AppReposRepository appRepos;
    @Override
    public TelegramDatas addMessages(TelegramDatas appRequest) {
        return appRepos.save(appRequest);
    }
}
