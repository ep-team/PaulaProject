package com.eshop.service;

import com.eshop.common.ServerResponse;
import com.eshop.pojo.User;

public interface IUserService {
	public ServerResponse<User> login(String username, String password);
	public ServerResponse<String> register(User user);
	public ServerResponse<String> checkValid(String str, String type);
	public ServerResponse selectSecretQuestion(String username);
	public ServerResponse<String> checkAnswer(String username, String question, String answer);
	public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);
	public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);
	public ServerResponse<User> updateInfomation(User user); 
	public ServerResponse<User> getInfomation(Integer userId);
	ServerResponse checkAdminRole(User user);
}
