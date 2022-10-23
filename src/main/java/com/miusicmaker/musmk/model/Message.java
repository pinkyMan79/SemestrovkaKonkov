package com.miusicmaker.musmk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    private String msgRoot;
    private String sender;
    private Long mId;
    private Long uId;
    private String date;

}
