package com.miusicmaker.musmk.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// при помощи ломбока можно не париться о правильных equals() и hashcode(), кайф
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    private Long id;
    private String email;
    private String password;
    private String nickname;

}
