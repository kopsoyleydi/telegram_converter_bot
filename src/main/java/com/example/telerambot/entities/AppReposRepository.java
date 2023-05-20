package com.example.telerambot.entities;

import com.example.telerambot.entities.AppRepos;
import com.example.telerambot.entities.TelegramDatas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AppReposRepository extends CrudRepository<AppRepos, Long> {

}
