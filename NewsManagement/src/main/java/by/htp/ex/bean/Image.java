package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class Image implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String link;
	private boolean status;
	
	public Image() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, link, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return id == other.id && Objects.equals(link, other.link) && status == other.status;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", link=" + link + ", status=" + status + "]";
	}	

}
