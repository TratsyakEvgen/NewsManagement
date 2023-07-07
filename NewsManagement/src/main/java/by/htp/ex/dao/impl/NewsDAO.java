package by.htp.ex.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;

public class NewsDAO implements INewsDAO {
	private List<Integer> images = new ArrayList<>();
	private List<Integer> styles = new ArrayList<>();

	public NewsDAO() {
		super();
		this.images.add(1);
		this.styles.add(1);
		this.styles.add(2);
		this.styles.add(3);
	}

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException, ParseException {
		List<News> result = new ArrayList<News>();
		result.add(new News(1,
				"Someone played the last game of hockey. It's a holiday in the country, and champagne has run out in stores",
				"brief1brief1brief1brief1brief1brief1brief1",
				new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2023-06-13 12:30"), styles, images));
		result.add(new News(2, "title2", "brief2brief2brief2brief2brief2brief2brief2",
				new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2023-06-13 11:30"), styles, images));
		result.add(new News(3, "title3", "brief3brief3brief3brief3brief3brief3brief3",
				new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2023-06-13 12:30"), styles, images));
		result.add(new News(4, "title4", "brief4brief4brief4brief4brief4brief4brief4",
				new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2023-06-13 10:30"), styles, images));
		result.add(new News(5, "title5", "brief5brief5brief5brief5brief5brief5brief5",
				new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2023-06-13 13:30"), styles, images));

		return result;
	}

	@Override
	public List<News> getList() throws NewsDAOException, ParseException {
		List<News> result = new ArrayList<News>();

		result.add(new News(1,
				"Someone played the last game of hockey. It's a holiday in the country, and champagne has run out in stores",
				"brief1brief1brief1brief1brief1brief1brief1",
				new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2023-06-13 12:30"), styles, images));
		result.add(new News(2, "title2", "brief2brief2brief2brief2brief2brief2brief2",
				new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2023-06-13 11:30"), styles, images));
		result.add(new News(3, "title3", "brief3brief3brief3brief3brief3brief3brief3",
				new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2023-06-13 12:30"), styles, images));
		result.add(new News(4, "title4", "brief4brief4brief4brief4brief4brief4brief4",
				new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2023-06-13 10:30"), styles, images));
		result.add(new News(5, "title5", "brief5brief5brief5brief5brief5brief5brief5",
				new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2023-06-13 13:30"), styles, images));
		return result;
	}

	@Override
	public News fetchById(int id) throws NewsDAOException, ParseException {
		return new News(1,
				"Someone played the last game of hockey. It's a holiday in the country, and champagne has run out in stores",
				"brief1brief1brief1brief1brief1brief1brief1",
				new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2023-06-13 12:30"), styles, images);
	}

	@Override
	public int addNews(News news) throws NewsDAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateNews(News news) throws NewsDAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteNewses(String[] idNewses) throws NewsDAOException {
		// TODO Auto-generated method stub

	}

}
