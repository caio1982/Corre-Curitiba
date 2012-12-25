package br.mello.arthur.correcuritiba;

import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class EventAdapter extends ArrayAdapter<Event> {
	private Context context;
	private int textViewResourceId;
	private Event[] objects;
	private long lastDate;

	public EventAdapter(Context context, int textViewResourceId, Event[] objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.objects = objects;
		this.lastDate = 0;
	}

	@Override
	@SuppressLint("SimpleDateFormat")
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View item = inflater.inflate(textViewResourceId, parent, false);
		
		Event event = objects[position];
		
		TextView nameText = (TextView) item.findViewById(R.id.name);
		nameText.setText(event.getName());
		
		TextView distanceText = (TextView) item.findViewById(R.id.distance);
		int distance = event.getDistance();
		if (distance > 0)
			distanceText.setText(Util.formatDistance(distance));
		
		try {
			int color = 0x808080;
			
			if (distance <= 5000) {				// 5K are easy
				color = 0xff7d9ec0; 
			} else if (distance <= 10000) {		// 10K are okay		
				color = 0xffe3cf57;
			} else if (distance <= 22000) {		// Half-marathons are hard
				color = 0xffff9912;
			} else if (distance <= 43000) {		// Marathons are painful
				color = 0xffff7256;
			} else if (distance <= 100000) {	// You better know what you're doing
				color = 0xff8e388e;
			} else {
				color = 0xffc0c0c0;
			}

			View tag = item.findViewById(R.id.tag);
			tag.setBackgroundColor(color);
		} catch(Exception e) {

		}

		TextView dateText = (TextView) item.findViewById(R.id.date);
	
		long newDate = event.getDate();
		
		if (newDate == lastDate) {
			dateText.setVisibility(View.GONE);
		} else {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			dateText.setText(df.format(newDate));
		}
		
		lastDate = newDate;
		
		return item;
	}
}
