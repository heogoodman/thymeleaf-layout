package com.basic.myspringboot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResDto {
    private Long id;
    private String name;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
}
