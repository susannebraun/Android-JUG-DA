package de.accso.jug_demo_5.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import de.accso.jug_demo_5.ContactsActivity;
import de.accso.jug_demo_5.R;

public class ContactListFragment extends Fragment {
	
	
	private ContactListEventListener mContactListEventListener;
	
	public interface ContactListEventListener {
		void onContactSelection(int contactInd);
	}
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_contact_list, null);
		
		ListView listView = (ListView) rootView.findViewById(R.id.contact_list);
		final ContactsAdapter listViewAdapter = new ContactsAdapter(getActivity());
		listView.setAdapter(listViewAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if(mContactListEventListener != null) {
					mContactListEventListener.onContactSelection(position);
				}
				
			}
			
		});
		
		
		return rootView;
	}

	

	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if(getActivity() instanceof ContactListEventListener) {
			mContactListEventListener = (ContactListEventListener) getActivity();
		} else {
			mContactListEventListener = null;
			throw new IllegalArgumentException("Activity must implement ContactEventListener!");
		}
	}


	private class ContactsAdapter extends BaseAdapter {
		
		Context ctx;
		
		private Contact[] contacts;

		public ContactsAdapter(Context ctx) {
			super();
			this.ctx = ctx;
			contacts = ContactsActivity.contacts;
		}

		@Override
		public int getCount() {
			return contacts.length;
		}

		@Override
		public Object getItem(int position) {
			return contacts[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View listItemView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
	        	listItemView = LayoutInflater.from(ctx).inflate(R.layout.contact_list_item, null);
	        } else {
	        	listItemView = convertView;
	        }

	       ImageView portraitImageView = (ImageView) listItemView.findViewById(R.id.contact_portrait_thump);
	       portraitImageView.setImageDrawable( ctx.getResources().getDrawable(contacts[position].portraitImageDrawableId));
	       
	       TextView contactNameTextView = (TextView) listItemView.findViewById(R.id.contact_name);
	       contactNameTextView.setText(contacts[position].name);
	        
	        return listItemView;
		}
		
	}
	
	public static class Contact {
		public String name;
		
		public String mobileNr;
		
		public int portraitImageDrawableId;
		
		public Contact(String name, String mobileNr, int portraitImage) {
			this.name = name;
			this.mobileNr = mobileNr;
			this.portraitImageDrawableId = portraitImage;
		}
	}
	

}
