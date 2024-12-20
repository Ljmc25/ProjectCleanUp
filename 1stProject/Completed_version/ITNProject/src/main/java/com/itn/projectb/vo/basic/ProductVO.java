package com.itn.projectb.vo.basic;

import com.itn.projectb.vo.common.Criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class ProductVO extends Criteria{
	private String pdid;
	private String title;
	private String contents;
	private String director;
	private String genre;
	private int runningTime;
	private String rate;
	private String openingDate;
	private String movieCast;
	private String imageMainUrl;
	private String imageSmallUrl;   
	private String imageBackgroundUrl;   
	private String imageThril1Url;   
	private String imageThril2Url;   
	private String imageThril3Url;   
	private String trailerMainUrl; 
	private String trailer1Url; 
	private String trailer2Url; 
	private int cumulative;
	private int	happyPoint;
	private String insertTime;
	private String updateTime;
	private String rateUrl;
	
	// 영화 실관람평 리뷰 관련 VO
	private String prid;
	private String review;
	private String writer;	
	
    public ProductVO(String title, String contents, String director, String genre,
            int runningTime, String rate, String rateUrl, String openingDate,
            String movieCast, String imageMainUrl, String imageSmallUrl,
            String imageBackgroundUrl, String imageThril1Url, String imageThril2Url,
            String imageThril3Url, String trailerMainUrl, String trailer1Url,
            String trailer2Url) {

		this.title = title;
		this.contents = contents;
		this.director = director;
		this.genre = genre;
		this.runningTime = runningTime;
		this.rate = rate;
		this.rateUrl = rateUrl;
		this.openingDate = openingDate;
		this.movieCast = movieCast;
		this.imageMainUrl = imageMainUrl;
		this.imageSmallUrl = imageSmallUrl;
		this.imageBackgroundUrl = imageBackgroundUrl;
		this.imageThril1Url = imageThril1Url;
		this.imageThril2Url = imageThril2Url;
		this.imageThril3Url = imageThril3Url;
		this.trailerMainUrl = trailerMainUrl;
		this.trailer1Url = trailer1Url;
		this.trailer2Url = trailer2Url;
    }
}
