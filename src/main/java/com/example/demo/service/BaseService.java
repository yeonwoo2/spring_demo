package com.example.demo.service;

import com.example.demo.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public abstract class BaseService<Req,Res,Entity> implements CrudInterface<Req,Res> {

    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository; //인터페이스구현한놈 받겠다
                            //JpaRepository<Item,Long>

}
