package com.example.demo.repository;

import com.example.demo.DemoApplicationTests;
import com.example.demo.model.entity.Item;
import com.example.demo.model.enumclass.ItemStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;


public class ItemRepositoryTest extends DemoApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){

        Item item = new Item();
        item.setStatus(ItemStatus.REGISTERED);
        item.setName("삼성노트북");
        item.setTitle("삼성노트북 A100");
        item.setContent("2019년 노트북 입니다.");
//        item.setPrice(900000);
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
//        item.setCreatedAt(LocalDateTime.now());
//        item.setCreatedBy("Partner01");
        //item.setPartner(item.getPartner().getId());


        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
    }

    @Test
    public void read(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);
        Assertions.assertTrue(item.isPresent());

    }
}
