/*
 * Copyright 2007-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itappservices.web.servlet.view.tiles2;

import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.js.ajax.AjaxUrlBasedViewResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

/**
 * <p>Extends <code>AjaxUrlBasedViewResolver</code> and provides 
 * some properties to set tiles values if the view is a 
 * <code>DynamicTilesView</code>.</p>
 *
 * @author Irepan Chavez
 */
public class TilesAjaxUrlBasedViewResolver extends AjaxUrlBasedViewResolver {

	private String tilesDefinitionName = null;
	private String tilesBodyAttributeName = null;
	private String tilesDefinitionDelimiter = null;

	/**
	 * Main template name.
	 */
	public void setTilesDefinitionName(String tilesDefinitionName) {
		this.tilesDefinitionName = tilesDefinitionName;
	}

	/**
	 * Tiles body attribute name. 
	 */
	public void setTilesBodyAttributeName(String tilesBodyAttributeName) {
		this.tilesBodyAttributeName = tilesBodyAttributeName;
	}

	/**
	 * Sets Tiles definition delimiter.  
	 */
	public void setTilesDefinitionDelimiter(String tilesDefinitionDelimiter) {
		this.tilesDefinitionDelimiter = tilesDefinitionDelimiter;
	}

	/**
	 * Does everything the <code>UrlBasedViewResolver</code> does and 
	 * also sets some Tiles specific values on the view.
	 * 
	 * @param viewName the name of the view to build
	 * @return the View instance
	 * @throws Exception if the view couldn't be resolved
	 * @see #loadView(String, java.util.Locale)
	 */
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		String url = getPrefix() + viewName + getSuffix();
		InputStream stream = getServletContext().getResourceAsStream(url);
		if (stream == null) {
			return new NonExistentView();
		}

		AbstractUrlBasedView view = super.buildView(viewName);
		
		// if DynamicTilesView, set tiles specific values
		if (view instanceof DynamicTilesView) {
			DynamicTilesView dtv = (DynamicTilesView)view;
			
			if (StringUtils.hasLength(tilesDefinitionName)) {
				dtv.setTilesDefinitionName(tilesDefinitionName);
			}
			
			if (StringUtils.hasLength(tilesBodyAttributeName)) {
				dtv.setTilesBodyAttributeName(tilesBodyAttributeName);
			}

			if (tilesDefinitionDelimiter != null) {
				dtv.setTilesDefinitionDelimiter(tilesDefinitionDelimiter);
			}
		}
		
		return view;
	}
	
	private static class NonExistentView extends AbstractUrlBasedView {
		@Override
		protected boolean isUrlRequired() {
			return false;
		}

		@Override
		public boolean checkResource(Locale locale) throws Exception {
			return false;
		}

		@Override
		protected void renderMergedOutputModel(Map<String, Object> model,
											   HttpServletRequest request,
											   HttpServletResponse response) throws Exception {
			// Purposely empty, it should never get called
		}
	}
}
