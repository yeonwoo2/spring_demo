package com.example.demo.controller.api;

import com.example.demo.controller.CrudController;
import com.example.demo.model.entity.Item;
import com.example.demo.model.network.request.ItemApiRequest;
import com.example.demo.model.network.response.ItemApiResponse;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {


}
