package com.bycw.http.Controllers;

import com.bycw.http.CommandHandler;
import com.bycw.http.HttpCommandHandler;
import com.bycw.http.HttpCommandParams;
import com.bycw.http.HttpCommandResponse;

@CommandHandler(cmd="/TestControl")
public class HttpTestControl extends HttpCommandHandler{
	public HttpCommandResponse action(HttpCommandParams httpParams) {
		HttpCommandResponse success = HttpCommandResponse.valueOfSucc();
		success.setMessage("TestControl return Blake");
		return success;
	}
}