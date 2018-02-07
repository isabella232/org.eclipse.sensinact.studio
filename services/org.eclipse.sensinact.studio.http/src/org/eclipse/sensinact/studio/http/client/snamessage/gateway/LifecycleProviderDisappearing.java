/**
 * Copyright (c) 2018 CEA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *     CEA - initial API and implementation and/or initial documentation
 */
package org.eclipse.sensinact.studio.http.client.snamessage.gateway;

import org.eclipse.sensinact.studio.http.client.snamessage.LifecycleMessage;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Etienne Gandrille
 */
public class LifecycleProviderDisappearing extends LifecycleMessage {

	public static final String KEY = "PROVIDER_DISAPPEARING";
	
	public LifecycleProviderDisappearing(JSONObject message) throws JSONException {
		super(message,KEY, toTab(message.getString("uri")), 1);
	}

	@Override
	public String getProvider() {
		return super.getProvider();
	}
}
