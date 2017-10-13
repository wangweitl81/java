package com.lxtech.novel.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.lxtech.novel.util.Constants;

@RestController
public class NovelController {
	@GetMapping("/")
	public Map mainEntry(HttpServletRequest req, HttpServletResponse resp) {
		String api = req.getParameter("api");
		Map resultMap = null;
		switch (api) {
		case Constants.API_BOOKLIST:
			resultMap = getBookList(req, resp);
			break;
		case Constants.API_BOOKINFO:
			resultMap = getBookInfo(req, resp);
			break;
		case Constants.API_CHAPLIST:
			resultMap = getChapList(req, resp);
			break;
		case Constants.API_CHAPINFO:
			resultMap = getChapInfo(req, resp);
			break;
			
		default:
			break;
		}
		return resultMap;
	}
	
/*	bookid: 2006,  
    chapterid: 196709,
    chaptercontent: " 月圆之夜，华山之巅，一剑西来，天外飞仙。 浩瀚宇宙无限苍穹一道闪亮的流星当空划过。。。" */
	private Map getChapInfo(HttpServletRequest req, HttpServletResponse resp) {
		Map map = ImmutableMap.of("bookid", 2006, "chapterid", "196709", "chaptercontent", "月圆之夜，华山之巅，一剑西来，天外飞仙。 浩瀚宇宙无限苍穹一道闪亮的流星当空划过。。。");
		return ImmutableMap.of("code", 200, "data", map, "msg", Constants.SUCCESS_MSG);
	}

/*	"chapterid": 196709,
    "chaptername":"章节名称1",
    "free":0,
    "chaptersize" : 3200 ,
    "createtime": "2016-11-04 15:04:00",
    "lastupdatetime" : "2016-11-04 15:04:00"*/
	private Map getChapList(HttpServletRequest req, HttpServletResponse resp) {
		List<Map> volumnList = Lists.newArrayList();
		List<Map> chapterList = Lists.newArrayList();
		chapterList.add(ImmutableMap.builder().put("chapterid", 196709).put("chaptername", "章节名称1").put("free", 0).put("chaptersize", 3200)
				.put("createtime", "2016-11-04 15:04:00").put("lastupdatetime", "2016-11-04 15:04:00").build());
		Map map = ImmutableMap.of("volumeid", 1, "volumename", "第一卷", "chapters", chapterList);
		volumnList.add(map);
		return ImmutableMap.of("code", 200, "data", volumnList, "msg", Constants.SUCCESS_MSG);
	}

	/*{
        "code":200,
        "data":{
                       bookstatus: 1,
                       description: "月圆之夜，华山之巅，一剑西来……老子穿越了！贱男来到南宋，翩翩公子没资格做，混入丐帮做起了小喽啰，奇遇、艳福、神功，他无力承受。",
                       keyword: "总裁,穿越,霸道",
                       title: "混在南宋的日子",                 
                       creattime: "2016-05-04 15:04:00",
                       author: "断欲",
                       free: 1,         
                       bookid: 2006,
                       cover_url: "http://xxx.jpg",                
                       category: 55,
                       lastupdatetime: "2016-11-04 15:04:00",
                       word_count: 112323,
               },
        "msg":"Success"
     }*/
	private Map getBookInfo(HttpServletRequest req, HttpServletResponse resp) {
		Map bookInfo = ImmutableMap.builder().put("bookstatus", 1)
				.put("description", "月圆之夜，华山之巅，一剑西来……老子穿越了！贱男来到南宋，翩翩公子没资格做，混入丐帮做起了小喽啰，奇遇、艳福、神功，他无力承受。")
				.put("keyword", "总裁,穿越,霸道")
				.put("title", "混在南宋的日子")
				.put("creattime", "2016-05-04 15:04:00")
				.put("author", "断欲")
				.put("free", 1)
				.put("bookid", 2006)
				.put("cover_url", "http://xxx.jpg")
				.put("category", 55)
				.put("lastupdatetime", "2016-11-04 15:04:00")
				.put("word_count", 112323)
				.build();
		Map map = ImmutableMap.of("code", 200, "data", bookInfo, "msg", Constants.SUCCESS_MSG);
		return map;
	}

	//http://www.tcss88.com/?s=JsonApi&a=index&api=get.spbooks&apikey={0}&spid={1}
		 /*{
	         "code": 200,
	         "data":[
	                  {
	                    "bookid": 4004
	                  }, 
	                  {
	                    "bookid": 4032
	                  }, 
	                  {
	                    "bookid": 6050
	                  }, 
	                  {
	                    "bookid": 7004
	                  }
	                ],
	         "msg":"Success"
	     }*/
	public Map getBookList(HttpServletRequest req, HttpServletResponse resp) {
		List<Map> bookList = Lists.newArrayList();
		bookList.add(ImmutableMap.of("bookid", 4004));
		bookList.add(ImmutableMap.of("bookid", 4032));
		bookList.add(ImmutableMap.of("bookid", 6050));
		bookList.add(ImmutableMap.of("bookid", 7004));
		Map map = ImmutableMap.of("code", 200, "data", bookList, "msg", Constants.SUCCESS_MSG);
		return map;
	}
}
