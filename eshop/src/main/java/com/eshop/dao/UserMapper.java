package com.eshop.dao;

import org.apache.ibatis.annotations.Param;

import com.eshop.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    //哪个属性不为空就更新哪个->选择性更新
    int updateByPrimaryKeySelective(User record);

    //全部更新
    int updateByPrimaryKey(User record);
    
    int checkUserName(String username);
    
    int checkUserByEamil(String email);
    
    //@Param注解指定了sql中的参数名字
    User selectLogin(@Param("username")String username, @Param("password")String password);
    
    String selectQuestionByUsername(String username);
    
    int checkAnswer(@Param("username")String username, @Param("question")String question, @Param("answer")String answer);
    
    int updatePasswordByUsername(@Param("username")String username, @Param("passwordNew")String passwordNew);
    
    int checkPassword(@Param("password")String password, @Param("userId")Integer userId);
    
    int checkEmailByUserId(@Param("email")String email, @Param("userId")Integer userId);
    
}