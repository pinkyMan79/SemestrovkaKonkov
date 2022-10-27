package com.miusicmaker.musmk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MusicFile {

    private String path;
    private Long fId;
    private Long uId;
    private String name;

}
