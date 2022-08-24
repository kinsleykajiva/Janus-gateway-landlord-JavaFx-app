package africa.jopen.network.services;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.json.JSONObject;

public class GetReqService extends Service<JSONObject> {


	private final StringProperty url = new SimpleStringProperty();

	public final String getUrl () {
		return url.get();
	}

	public final void setUrl (String value) {
		url.set(value);
	}

	public final StringProperty urlProperty () {
		return url;
	}


	@Override
	protected Task<JSONObject> createTask () {
		return new Task<>() {
			@Override
			protected JSONObject call () {


				return null;

			}
		};
	}

}
