package com.example.UserHleper;

public class NEMT {

	private int id;
	private String word_key;
	private String word_phono;
	private String word_example;
	private String word_trans;
	private int word_unit;
	
	public NEMT(int id,String word_key){
		this.word_key=word_key;
		this.id=id;
	}
	
	public NEMT(String wordkey){
		this.word_key=wordkey;
	}
	
	public NEMT(int id,String word_key,String word_phono,String word_trans,String word_example,int word_unit) {
		super();
		this.id=id;
		this.word_example=word_example;
		this.word_key=word_key;
		this.word_trans=word_trans;
		this.word_phono=word_phono;
		this.word_unit=word_unit;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NEMT(int word_unit){
		this.word_unit=word_unit;
	}
	

	public String getWord_trans() {
		return word_trans;
	}

	public void setWord_trans(String word_trans) {
		this.word_trans = word_trans;
	}

	@Override
	public String toString() {
		return "NMET [id=" + id + ", word_key=" + word_key + ", word_phono="
				+ word_phono + ", word_example=" + word_example
				+ ", word_trans=" + word_trans + ", word_unit=" + word_unit
				+ "]";
	}

	public String getWord_key() {
		return word_key;
	}

	public void setWord_key(String word_key) {
		this.word_key = word_key;
	}

	public String getWord_phono() {
		return word_phono;
	}

	public void setWord_phono(String word_phono) {
		this.word_phono = word_phono;
	}

	public String getWord_example() {
		return word_example;
	}

	public void setWord_example(String word_example) {
		this.word_example = word_example;
	}

	public int getWord_unit() {
		return word_unit;
	}

	public void setWord_unit(int word_unit) {
		this.word_unit = word_unit;
	}
	

}
