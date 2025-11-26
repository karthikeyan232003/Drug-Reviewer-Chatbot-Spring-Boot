package com.example.pharmapulse.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
	@Autowired
	private ChatModel chatModel;
	@PostMapping("/predict")
	public List<Object> runse(@RequestBody Map<String,String> req)
	{
		String p = (req.get("symptoms"));
		String res = chatModel.call(p);
		return Arrays.asList("hi",res);
	}

}
