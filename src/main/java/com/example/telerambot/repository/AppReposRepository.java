package com.example.telerambot.repository;

import com.example.telerambot.entities.TelegramDatas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AppReposRepository extends JpaRepository<TelegramDatas, Long> {

}
