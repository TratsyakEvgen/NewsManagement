package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id = 0;
	private String title;
	private String link;
	private Date newsDate;
	private boolean status;
	private List<Image> images;
	private String local;
	private User author;
	
	public News() {		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, images, link, local, newsDate, status, title);
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
				&& Objects.equals(images, other.images) && Objects.equals(link, other.link)
				&& Objects.equals(local, other.local) && Objects.equals(newsDate, other.newsDate)
				&& status == other.status && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", link=" + link + ", newsDate=" + newsDate + ", status="
				+ status + ", images=" + images + ", local=" + local + ", author=" + author + "]";
	}

	



}
