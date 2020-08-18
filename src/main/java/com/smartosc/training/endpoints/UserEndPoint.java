package com.smartosc.training.endpoints;


import com.example.UserDTO;
import com.example.UserRequest;
import com.example.UserResponse;
import com.smartosc.training.dao.UserDAO;
import com.smartosc.training.entities.User;
import com.smartosc.training.utils.HibernateUtils;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class UserEndPoint {
    private static final String NAMESPACE_URI = "http://example.com";
    private UserDAO userDAO;
    @Autowired
    private ModelMapper modelMapper;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UserRequest")
    @ResponsePayload
    public UserResponse findById(@RequestPayload UserRequest userRequest) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        User user = UserDAO.build(session).find(userRequest.getId());
        UserResponse userResponse = new UserResponse();
        userResponse.getData().add(modelMapper.map(user, UserDTO.class));
        return userResponse;
    }
//    @Cacheable(value = "", key = "#requestUser")
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "requestUser")
//    @ResponsePayload
//    public ResponseUser findByName(@RequestPayload RequestUser requestUser) {
//        Session session = HibernateUtils.getSessionFactory().openSession();
//        ResponseUser responseUser = new ResponseUser();
//        User user = userDAO.build(session).find(requestUser.getData().getId());
//        responseUser.s(typeRoomResponses);
//        return typeRoomResponse;
//    }

}
