/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wicketTutorial.metadataroles;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.wicketTutorial.commons.bootstrap.layout.BootstrapBasePage;
import org.wicketTutorial.commons.bootstrap.BootstrapApp;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.wicketTutorial.metadataroles.admin.AdminOnlyPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.wicketTutorial.metadataroles.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication{    		
	@Override
	public Class<? extends BootstrapBasePage> getHomePage(){
		return HomePage.class;
	}
	
	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return BasicAuthenticationSession.class;
	}

	@Override
	protected Class<? extends BootstrapBasePage> getSignInPageClass() {
		return SignInPage.class;
	}
	
	@Override
	public void init(){	
		getSecuritySettings().setAuthorizationStrategy(new MetaDataRoleAuthorizationStrategy(this));
		MetaDataRoleAuthorizationStrategy.authorize(AdminOnlyPage.class, Roles.ADMIN);
	}
}
