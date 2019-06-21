package cn.itcast.QLN.document.service;

import java.util.List;


import org.springframework.transaction.annotation.Transactional;

import cn.itcast.QLN.document.dao.DocumentDao;
import cn.itcast.QLN.document.vo.Document;
import cn.itcast.QLN.user.vo.User;
import cn.itcast.QLN.utils.PageBean;
import cn.itcast.QLN.utils.PageBeanUtil;

@Transactional
public class DocumentService {
//注入DAO
	private DocumentDao documentDao;

	public void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}
	
	//页面跳转，完成DAO的调用	
	public PageBean<Document> findByPage(Integer page,Integer uid){
		
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findCount(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findByPage(uid,begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findByCid(Integer cid, int page,int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findCountCid(cid,uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始: 
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findByCid(cid, begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Document document) {
		// TODO Auto-generated method stub
		documentDao.save(document);
	}
	
	
	public PageBean<Document> findByKey(String docsearch, int page,int uid){
		// TODO Auto-generated method stub
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findKeyCount(docsearch,uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findByKey(docsearch,begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public Document findByDid(Integer did) {
		// TODO Auto-generated method stub
		return documentDao.findByDid(did);
	}

	public void delete(Document document) {
		// TODO Auto-generated method stub
		documentDao.delete(document);
	}

	public Document checkState(Integer did) {
		// TODO Auto-generated method stub
		return documentDao.checkState(did);
	}

	public void update(Document existDocument) {
		// TODO Auto-generated method stub
		documentDao.update(existDocument);
	}

	public PageBean<Document> findByManagerPage(int page) {
		// TODO Auto-generated method stub

		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =6;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findManagerCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findByManagerPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findManagerUncheck(int page) {
		
		// TODO Auto-generated method stub

				PageBean<Document> pageBean = new PageBean<Document>();
				// 设置当前页数:
				pageBean.setPage(page);
				// 设置每页显示记录数:
				int limit =6;
				pageBean.setLimit(limit);
				// 设置总记录数:
				int totalCount = 0;
				totalCount = documentDao.findManagerUncheckCount();
				pageBean.setTotalCount(totalCount);
				// 设置总页数:
				int totalPage = 0;
				// Math.ceil(totalCount / limit);
				if (totalCount % limit == 0) {
					totalPage = totalCount / limit;
				} else {
					totalPage = totalCount / limit + 1;
				}
				pageBean.setTotalPage(totalPage);
				// 每页显示的数据集合:
				// 从哪开始:
				int begin = (page - 1) * limit;
				List<Document> list = documentDao.findManagerUncheck(begin, limit);
				pageBean.setList(list);
				return pageBean;
	}

	public PageBean<Document> findManagerChecked(int page) {
		// TODO Auto-generated method stub

		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =6;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findManagerCheckedCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findManagerChecked(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findUnsubmit(int page,int uid) {

		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findUnsubmitCount(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findUnsubmit(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findUnpass(int page,int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findUnpassCount(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findUnpass(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findSubmited(int page, int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findSubmitedCount(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findSubmited(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findCheckpass1(int page,int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findCheckpass1Count(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findCheckpass1(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	
	public PageBean<Document> findCheckpass2(int page,int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findCheckpass2Count(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findCheckpass2(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findManagerUnpass(int page) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =6;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findManagerUnpassCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findManagerUnpass(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findByLeaderPage(int page) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =6;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findByLeaderPageCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findByLeaderPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findLeaderChecked(int page) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =6;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findLeaderCheckedCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findLeaderChecked(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findLeaderUncheck(int page) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =6;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findLeaderUncheckCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findLeaderUncheck(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findBySecPage(int page) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =6;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findBySecPageCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findBySecPages(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findSecUnplacePage(int page) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =6;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findSecUnplacePageCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findSecUnplacePage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findSecPlacedPage(int page) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =6;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findSecPlacedPageCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findSecPlacedPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> findManCheckpass2(int page) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =6;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findManCheckpass2Count();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.findManCheckpass2(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	
	public PageBean<Document> timeasc(int page, int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findCount(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.timeasc(uid,begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> typeasc(int page, int uid) {
		// TODO Auto-generated method stub
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findCount(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.typeasc(uid,begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> CidTimeasc(Integer cid, int page, int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findCountCid(cid,uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始: 
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.CidTimeasc(cid, begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> unsubmitTimeasc(int page, int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findUnsubmitCount(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.unsubmitTimeasc(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> unsubmittypedesc(int page, int uid) {
		// TODO Auto-generated method stub
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findUnsubmitCount(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.unsubmittypedesc(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> manTimeasc(int page, int uid) {
		// TODO Auto-generated method stub
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findUnpassCount(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.manTimeasc(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> mantypedesc(int page, int uid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				PageBean<Document> pageBean = new PageBean<Document>();
				// 设置当前页数:
				pageBean.setPage(page);
				// 设置每页显示记录数:
				int limit =14;
				pageBean.setLimit(limit);
				// 设置总记录数:
				int totalCount = 0;
				totalCount = documentDao.findUnpassCount(uid);
				pageBean.setTotalCount(totalCount);
				// 设置总页数:
				int totalPage = 0;
				// Math.ceil(totalCount / limit);
				if (totalCount % limit == 0) {
					totalPage = totalCount / limit;
				} else {
					totalPage = totalCount / limit + 1;
				}
				pageBean.setTotalPage(totalPage);
				// 每页显示的数据集合:
				// 从哪开始:
				int begin = (page - 1) * limit;
				List<Document> list = documentDao.mantypedesc(begin, limit,uid);
				pageBean.setList(list);
				return pageBean;
	}

	public PageBean<Document> submitedteacherTimeasc(int page, int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findSubmitedCount(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.submitedteacherTimeasc(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> submitedtypedesc(int page, int uid) {
		// TODO Auto-generated method stub
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findSubmitedCount(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.submitedtypedesc(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> checkpass1Timeasc(int page, int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findCheckpass1Count(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.checkpass1Timeasc(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> checkpass2Timeasc(int page, int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findCheckpass2Count(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.checkpass2Timeasc(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> checkpass1typedesc(int page, int uid) {
		// TODO Auto-generated method stub
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findCheckpass2Count(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.checkpass1typedesc(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> checkpass2typedesc(int page, int uid) {
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findCheckpass2Count(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.checkpass2typedesc(begin, limit,uid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> managercheckunpass(int page) {
		// TODO Auto-generated method stub
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =14;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.managercheckunpassCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.managercheckunpass(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Document> managerAllPageTimeasc(int page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

				PageBean<Document> pageBean = new PageBean<Document>();
				// 设置当前页数:
				pageBean.setPage(page);
				// 设置每页显示记录数:
				int limit =6;
				pageBean.setLimit(limit);
				// 设置总记录数:
				int totalCount = 0;
				totalCount = documentDao.findManagerCount();
				pageBean.setTotalCount(totalCount);
				// 设置总页数:
				int totalPage = 0;
				// Math.ceil(totalCount / limit);
				if (totalCount % limit == 0) {
					totalPage = totalCount / limit;
				} else {
					totalPage = totalCount / limit + 1;
				}
				pageBean.setTotalPage(totalPage);
				// 每页显示的数据集合:
				// 从哪开始:
				int begin = (page - 1) * limit;
				List<Document> list = documentDao.managerAllPageTimeasc(begin, limit);
				pageBean.setList(list);
				return pageBean;
	}

	public PageBean<Document> managerAllPageTypedesc(int page) {
		// TODO Auto-generated method stub
		PageBean<Document> pageBean = new PageBean<Document>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit =6;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = documentDao.findManagerCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Document> list = documentDao.managerAllPageTypedesc(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void pageQuery(PageBeanUtil<Document> pageBeanUtil) {
		// TODO Auto-generated method stub
		documentDao.pageQuery(pageBeanUtil);
	}

	

}
