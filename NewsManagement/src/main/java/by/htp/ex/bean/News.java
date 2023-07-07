package by.htp.ex.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idNews = 0;
	private String title = "";
	private String briefNews = "";
	private Date newsDate;
	private List<Integer> styles = new ArrayList<>();
	private List<Integer> images = new ArrayList<>();

	public News(int idNews, String title, String briefNews, Date newsDate, List<Integer> styles, List<Integer> images) {
		super();
		this.idNews = idNews;
		this.title = title;
		this.briefNews = briefNews;
		this.newsDate = newsDate;
		this.styles = styles;
		this.images = images;
	}

	public Integer getIdNews() {
		return idNews;
	}

	public void setIdNews(Integer idNews) {
		this.idNews = idNews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBriefNews() {
		return briefNews;
	}

	public void setBriefNews(String briefNews) {
		this.briefNews = briefNews;
	}

	public Date getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public List<Integer> getImages() {
		return images;
	}

	public void setImages(List<Integer> images) {
		this.images = images;
	}

	public List<Integer> getStyles() {
		return styles;
	}

	public void setStyles(List<Integer> styles) {
		this.styles = styles;
	}

}
