package com.eshop.dao;

import org.apache.ibatis.annotations.Param;

import com.eshop.pojo.User;

/**
 * 
 * @author Paula Lin
 *
 */
public interface UserMapper {
    int deleteUserByPrimaryKey(Integer id);

    int insertUser(User record);

    int insertUserSelective(User record);

    User selectUserByPrimaryKey(Integer id);

    //哪个属性不为空就更新哪个->选择性更新
    int updateUserByPrimaryKeySelective(User record);

    //全部更新
    int updateUserByPrimaryKey(User record);
    
    int checkUsesUserName(String username);
    
    int checkUsersInfoByEamil(String email);
    
    //@Param注解指定了sql中的参数名字
    User selectUserLoginInfo(@Param("username")String username, @Param("password")String password);
    
    String selectFaftyQuestionByUsername(String username);
    
    int checkSaftyAnswer(@Param("username")String username, @Param("question")String question, @Param("answer")String answer);
    
    int updateUsersPasswordByUsername(@Param("username")String username, @Param("passwordNew")String passwordNew);
    
    int checkUsersPassword(@Param("password")String password, @Param("userId")Integer userId);
    
    int checkUsersEmailInfoByUserId(@Param("email")String email, @Param("userId")Integer userId);
    
}