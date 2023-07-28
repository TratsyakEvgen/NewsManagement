package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class NewsHeader implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date newsDate;
	private boolean status;
	private int idUser;

	public NewsHeader() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idUser, newsDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsHeader other = (NewsHeader) obj;
		return id == other.id && idUser == other.idUser && Objects.equals(newsDate, other.newsDate)
				&& status == other.status;
	}

	@Override
	public String toString() {
		return "NewsHeader [id=" + id + ", newsDate=" + newsDate + ", status=" + status + ", idUser=" + idUser + "]";
	}
	
	

}
