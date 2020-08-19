package com.smartosc.training.endpoints;


import com.example.*;
import com.smartosc.training.dao.UserDAO;
import com.smartosc.training.entities.User;
import com.smartosc.training.utils.HibernateUtils;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.stream.Collectors;


@Endpoint
public class UserEndPoint {
    private static final String NAMESPACE_URI = "http://example.com";
    private Session session = HibernateUtils.getSessionFactory().openSession();
    private UserDAO userDAO;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HibernateUtils hibernateUtils;

//    @Cacheable(value = "findUserById", key = "1")
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findUserByIdRequest")
    @ResponsePayload
    public FindUserByIdResponse findUserById(@RequestPayload FindUserByIdRequest userRequest) {
        User user = UserDAO.build(session).findById(userRequest.getId());
        FindUserByIdResponse userResponse = new FindUserByIdResponse();
        userResponse.getUserDTO().add(modelMapper.map(user, UserDTO.class));
        return userResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findAllUserRequest")
    @ResponsePayload
    public FindAllUserResponse findAllUser() {
        FindAllUserResponse responseUser = new FindAllUserResponse();
        List<User> userList = UserDAO.build(session).findAll();
        responseUser.getUserDTO().addAll(userList.stream().map(s->{return modelMapper.map(s, UserDTO.class);}).collect(Collectors.toList()));
        return responseUser;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest userRequest) {
        UpdateUserResponse responseUser = new UpdateUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setMessage("update user successfully");
        serviceStatus.setStatusCode(HttpStatus.OK.toString());
        User user = UserDAO.build(session).findById(userRequest.getId());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user = UserDAO.build(session).save(user);
        responseUser.getUserDTOAndServiceStatus().add(modelMapper.map(user, UserDTO.class));
        responseUser.getUserDTOAndServiceStatus().add(serviceStatus);
        return responseUser;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
    @ResponsePayload
    public AddUserResponse createUser(@RequestPayload AddUserRequest userRequest) {
        AddUserResponse responseUser = new AddUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setMessage("create user successfully");
        serviceStatus.setStatusCode(HttpStatus.OK.toString());
        User user = UserDAO.build(session).save(modelMapper.map(userRequest, User.class));
        responseUser.getUserDTOAndServiceStatus().add(modelMapper.map(user, UserDTO.class));
        responseUser.getUserDTOAndServiceStatus().add(serviceStatus);
        return responseUser;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest userRequest) {
        DeleteUserResponse responseUser = new DeleteUserResponse();
        User user = UserDAO.build(session).findById(userRequest.getId());
        UserDAO.build(session).delete(user);

        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setMessage(" user successfully");
        serviceStatus.setStatusCode(HttpStatus.OK.toString());

        responseUser.setServiceStatus(serviceStatus);
        return responseUser;
    }
}
