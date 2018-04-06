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
package org.eclipse.sensinact.studio.model.manager.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.sensinact.studio.http.client.agent.Agent;
import org.eclipse.sensinact.studio.http.client.snamessage.MsgFactory;
import org.eclipse.sensinact.studio.http.client.snamessage.MsgSensinact;
import org.eclipse.sensinact.studio.http.server.SensinactServerResource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.resource.Post;

/**
 * @author Nicolas Hili
 */
public class GatewayHttpServerRoute extends SensinactServerResource {

	private static final Logger logger = Logger.getLogger(GatewayHttpServerRoute.class);

	@Post("json")
	public Response getValue(String params) {
		try {
			// CALLBACK : we need to check the embedded messages
			JSONObject json = new JSONObject(params);
			String id = json.getString("callbackId");
			JSONArray array = json.getJSONArray("messages");
			
			List<MsgSensinact> messages = new ArrayList<MsgSensinact>(); 
			for (int i=0; i<array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				messages.add(MsgFactory.build(object));
			}
			
			Status status = Agent.getInstance().callbackRecieved(id, messages);
			
			Response response = getResponse();
			response.setStatus(status);
			return response;
		} catch (JSONException e) {
			logger.error("DeviceInfoRoute - decode error", e);
			return null;
		}
	}
}
