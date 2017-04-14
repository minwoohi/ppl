package com.jm.ppl.admin.likecount.vo;

public class ItemVO {

	private String itemId;
	private String itemName;
	private String itemBrand;
	private String itemProductCode;
	private int itemPrice;
	private int itemLikeCount;
	private String itemPost;
	private String movieId;
	private String dramaId;
	private String actorId;
	
	private DramaVO dramaVO;
	private MovieVO movieVO;
	private ActorVO actorVO;
	
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemBrand() {
		return itemBrand;
	}
	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}
	public String getItemProductCode() {
		return itemProductCode;
	}
	public void setItemProductCode(String itemProductCode) {
		this.itemProductCode = itemProductCode;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getItemLikeCount() {
		return itemLikeCount;
	}
	public void setItemLikeCount(int itemLikeCount) {
		this.itemLikeCount = itemLikeCount;
	}
	public String getItemPost() {
		return itemPost;
	}
	public void setItemPost(String itemPost) {
		this.itemPost = itemPost;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getDramaId() {
		return dramaId;
	}
	public void setDramaId(String dramaId) {
		this.dramaId = dramaId;
	}
	public String getActorId() {
		return actorId;
	}
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}
	
	

	public DramaVO getDramaVO() {
		if(dramaVO == null){
			dramaVO = new DramaVO();
			
		}
		
		return dramaVO;
	}
	public void setDramaVO(DramaVO dramaVO) {
		this.dramaVO = dramaVO;
	}
	public MovieVO getMovieVO() {
		if(movieVO == null){
			movieVO = new MovieVO();
			
		}
		return movieVO;
	}
	public void setMovieVO(MovieVO movieVO) {
		this.movieVO = movieVO;
	}
	public ActorVO getActorVO() {
		if(actorVO == null){
			actorVO = new ActorVO();
			
		}
		
		return actorVO;
	}
	public void setActorVO(ActorVO actorVO) {
		this.actorVO = actorVO;
	}
}
