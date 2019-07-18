package com.example.rest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @EnableWebMvc
@RequestMapping("/api")
public class ArrayController {
	@PostMapping(path = "/makeonearray", consumes = "application/json", produces = "application/json")	
	public ResponseEntity<String>  makeOneArray(@RequestBody Map<String, Object> json) {
		JSONObject jsonObject  = new JSONObject(json);
		Iterator<String> keys = jsonObject.keys();
		Set<Integer> set = new HashSet<Integer>();
		String key = "";
		JSONArray jsonArr = null;
		while(keys.hasNext()) {
			key = keys.next();
			jsonArr  = jsonObject.getJSONArray(key);
			for(int i = 0; i < jsonArr.length(); i++) {
				// removes duplicates when the elements are added to Set
                set.add(jsonArr.getInt(i));
			}			
		}
		List<Integer> list = new ArrayList<Integer>(set);
		// Sorting the list
		Collections.sort(list);
		JSONObject response = new JSONObject();
		response.put("Array", list);
		HttpHeaders headers = new HttpHeaders();
		headers.add("pragma", "no-cache");
		headers.add("content-Type", "application/json; charset=UTF-8");
		headers.add("cache-control","no-cache");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(response.toString());
	}
}
