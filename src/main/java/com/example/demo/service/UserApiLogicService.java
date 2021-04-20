package com.example.demo.service;

import com.example.demo.ifs.CrudInterface;
import com.example.demo.model.entity.User;
import com.example.demo.model.enumclass.UserStatus;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.Pagination;
import com.example.demo.model.network.request.UserApiRequest;
import com.example.demo.model.network.response.UserApiResponse;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    // 1. request data 가져오기
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);

        // 3. 생서된 데이터기준 -> userApiResponse return


        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        //id -> repository getOne, getById
        //user -> userApiResponse return
        return userRepository.findById(id)
                .map(user -> response(user))
                .map(userApiResponse -> Header.OK(userApiResponse))
                .orElseGet(()->Header.ERROR("데이터없음"));

    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터를 찾기
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            // 3. data -> update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());

                return user;
            })
            .map(user -> userRepository.save(user)) //update
            .map(updateUser -> response(updateUser))    //userApiResponse
            .map(userApiResponse -> Header.OK(userApiResponse))
            .orElseGet(() -> Header.ERROR("데이터 없음"));


        // 3. update

        // 4. userApiResponse return


    }

    @Override
    public Header delete(Long id) {
        // 1. id -> repository -> user

        Optional<User> optional = userRepository.findById(id);

        // 2. repository -> delete

        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));
        // 3. response

    }

    private UserApiResponse response(User user){
        //user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) //todo 암호와, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        //Header + data return
        return userApiResponse;
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {

        Page<User> users =userRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        return Header.OK(userApiResponseList, pagination);
    }
}
