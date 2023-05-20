package com.example.telerambot.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramDatas {
        @Id
        private Long chatId;

        private String firstName;

        private String lastName;

        private String userName;

        private Timestamp registeredAt;
}
