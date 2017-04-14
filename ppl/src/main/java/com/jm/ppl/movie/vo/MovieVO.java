package com.jm.ppl.movie.vo;

public class MovieVO {

	public String movieId;
	public String movieTitle;
	public String movieSubtitle;
	public String movieDirector;
	public String movieActor;
	public String moviePost;
	public String movieIntro;
	public int movieLikeCount;
	public String movieGenre;

	public String getMovieActor() {
		return movieActor;
	}

	public void setMovieActor(String movieActor) {
		this.movieActor = movieActor;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMoviePost() {
		return moviePost;
	}

	public void setMoviePost(String moviePost) {
		this.moviePost = moviePost;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieSubtitle() {
		return movieSubtitle;
	}

	public void setMovieSubtitle(String movieSubtitle) {
		this.movieSubtitle = movieSubtitle;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public String getMovieIntro() {
		return movieIntro;
	}

	public void setMovieIntro(String movieIntro) {
		this.movieIntro = movieIntro;
	}

	public int getMovieLikeCount() {
		return movieLikeCount;
	}

	public void setMovieLikeCount(int movieLikeCount) {
		this.movieLikeCount = movieLikeCount;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

}
