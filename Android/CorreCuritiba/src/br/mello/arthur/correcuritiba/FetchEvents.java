package br.mello.arthur.correcuritiba;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

public class FetchEvents extends AsyncTask<String, Object, List<Event>>{
	private final Activity activity;
	private final static String JSON_URL = "http://caio.ueberalles.net/corridas";
	
	public FetchEvents(Activity activity) {
		this.activity = activity;
	}
	
	@Override
	protected List<Event> doInBackground(String... params) {
		try {
        	String jsonEvents = new WebClient(JSON_URL).get();
        	List<Event> events = new EventsParser().listFromJSON(jsonEvents);
			return events;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void onPostExecute(List<Event> results) {
		super.onPostExecute(results);
		if (results != null && results.size() > 0) {
			MainActivity.events = new Event[results.size()];
			results.toArray(MainActivity.events);
		}
		this.activity.startActivity(new Intent(this.activity, MainActivity.class));
		this.activity.finish();
	}

}
