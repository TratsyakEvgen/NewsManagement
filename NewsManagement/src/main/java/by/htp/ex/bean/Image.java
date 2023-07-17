package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class Image implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String link;
	
	public Image() {		
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

	@Override
	public int hashCode() {
		return Objects.hash(id, link);
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
		return id == other.id && Objects.equals(link, other.link);
	}

	@Override
	public String toString() {
		return "Images [id=" + id + ", link=" + link + "]";
	}
	
	
	

}
