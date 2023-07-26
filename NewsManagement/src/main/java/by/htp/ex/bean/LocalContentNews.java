package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class LocalContentNews implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int idNews;
	private String title;
	private String link;
	private String local;
	
	public LocalContentNews() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdNews() {
		return idNews;
	}

	public void setIdNews(int idNews) {
		this.idNews = idNews;
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idNews, link, local, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocalContentNews other = (LocalContentNews) obj;
		return id == other.id && idNews == other.idNews && Objects.equals(link, other.link)
				&& Objects.equals(local, other.local) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "LocalContentNews [id=" + id + ", idNews=" + idNews + ", title=" + title + ", link=" + link + ", local="
				+ local + "]";
	}

	
	
}
