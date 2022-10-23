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

    String path;
    Integer fId;
    Integer uId;
    String name;

}
