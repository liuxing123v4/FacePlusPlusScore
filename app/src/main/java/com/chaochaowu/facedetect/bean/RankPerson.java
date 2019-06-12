package com.chaochaowu.facedetect.bean;

import org.litepal.crud.DataSupport;

public class RankPerson extends DataSupport {
	private int id;
	private String rankname;
	private String rankage;
	private double score;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRankname() {
		return rankname;
	}

	public void setRankname(String rankname) {
		this.rankname = rankname;
	}

	public String getRankage() {
		return rankage;
	}

	public void setRankage(String rankage) {
		this.rankage = rankage;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}
