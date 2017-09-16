package com.lxtech.novel.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.assertj.core.util.Strings;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

public class ParameterInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	/**
	 * parameter check
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		String apikey = req.getParameter(Constants.APIKEY);
		String spid = req.getParameter(Constants.SPID);
		
		String prop_key = SystemConfig.getProperty(Constants.APIKEY);
		String prop_spid = SystemConfig.getProperty(Constants.SPID);
		if (Strings.isNullOrEmpty(apikey) || Strings.isNullOrEmpty(spid) 
				|| !apikey.equals(prop_key) || !spid.equals(prop_spid)) {
			ObjectMapper mapper = new ObjectMapper();
			Map map = ImmutableMap.of("code", 403, "msg", "Failure");
			resp.setContentType("application/json");
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			resp.getWriter().print(mapper.writeValueAsString(map));
			return false;
		} else {
			return true;
		}
	}

}
