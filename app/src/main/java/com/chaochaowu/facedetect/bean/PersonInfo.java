package com.chaochaowu.facedetect.bean;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.UUID;

public class PersonInfo extends DataSupport {
//	private UUID mId;
	private int id;
	private String username;
	private String password;
	private double score;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}



//	public Person() {
//		this(UUID.randomUUID());
//	}
//
//	public Person(UUID id) {
//		mId = id;
//	}

//	public UUID getId() {
//		return mId;
//	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
