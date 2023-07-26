package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date newsDate;
	private boolean status;
	private List<LocalContentNews> listLocalContentNews;
	private List<Image> images;
	private User author;
	
	public News() {		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<LocalContentNews> getListLocalContentNews() {
		return listLocalContentNews;
	}

	public void setListLocalContentNews(List<LocalContentNews> listLocalContentNews) {
		this.listLocalContentNews = listLocalContentNews;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, images, listLocalContentNews, newsDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(author, other.author) && Objects.equals(id, other.id)
				&& Objects.equals(images, other.images)
				&& Objects.equals(listLocalContentNews, other.listLocalContentNews)
				&& Objects.equals(newsDate, other.newsDate) && status == other.status;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", newsDate=" + newsDate + ", status=" + status + ", listLocalContentNews="
				+ listLocalContentNews + ", images=" + images + ", author=" + author + "]";
	}

}
