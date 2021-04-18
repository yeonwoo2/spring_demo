package com.example.demo.service;

import com.example.demo.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public abstract class BaseService<Req,Res,Entity> implements CrudInterface<Req,Res> { // 인터페이스부분을 구현안해서 abstract class

    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;
                            //JpaRepository<Item,Long>

}
