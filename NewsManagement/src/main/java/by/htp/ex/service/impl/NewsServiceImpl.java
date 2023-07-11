package by.htp.ex.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;

public class NewsServiceImpl implements INewsService {

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();

	@Override
	public List<News> getActiveNewsByLocalSortedByDate(String local) throws ServiceException {
		List<News> news = null;

		try {
			news = newsDAO.getList(local);
			if (news != null) {
				news = news.stream().filter(News::isStatus).sorted(Comparator.comparing(News::getNewsDate))
						.collect(Collectors.toList());
			}
		} catch (NewsDAOException e) {
			throw new ServiceException("Can't get ative news by local sorted by date", e);
		}
		return news;

	}

}
