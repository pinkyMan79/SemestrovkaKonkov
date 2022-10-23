package com.miusicmaker.musmk.repositories;

import com.miusicmaker.musmk.model.Message;

import java.util.List;

public interface MsgRepository {

    List<Message> findAll();

    Message findById(Long id);

    void save(Message u);

    void update(Message u);

    void delete(Long id);

}
