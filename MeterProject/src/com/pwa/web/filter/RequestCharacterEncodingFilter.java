package com.pwa.web.filter;

/**
 * Copyright(c) 2006 Somchai LIMSIRORATANA. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter a null character encoding of request. Some browser does not sent character encoding type of request to server. Then, the character encoding
 * of request will be null. The server such as Tomcat use ISO-8859-1 by default. This filter will detect the null character encoding of request. Then,
 * set the request character encoding corresponse to the response character encoding.
 * 
 * @author Somchai LIMSIRORATANA
 * @version 1.0
 */

public final class RequestCharacterEncodingFilter implements Filter {
	// public static Logger log = Logger.getLogger(RequestCharacterEncodingFilter.class);
	private FilterConfig config = null;
	private String encoding = null;

	public void init(FilterConfig filterConfig) throws ServletException {

		config = filterConfig;
		encoding = config.getInitParameter("encoding");
		System.out.println("Initialize Filter: " + config.getFilterName() + " with encoding=" + encoding);
		// config.getServletContext().log("Initialize Filter: " + config.getFilterName() + " with encoding=" + encoding);
	}

	public void destroy() {
		config.getServletContext().log("Destroy Filter: " + config.getFilterName());
		config = null;
		encoding = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//TODO:(prove it too) check append condition if request use GET Method Set encoding to iso8859 ; and rest of action code not convert anymore 
		
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
		}

		chain.doFilter(request, response);
	}
}
