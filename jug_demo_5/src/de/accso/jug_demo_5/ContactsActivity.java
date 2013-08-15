package de.accso.jug_demo_5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import de.accso.jug_demo_5.fragments.ContactDetailFragment;
import de.accso.jug_demo_5.fragments.ContactListFragment;
import de.accso.jug_demo_5.fragments.ContactListFragment.Contact;
import de.accso.jug_demo_5.fragments.ContactListFragment.ContactListEventListener;

public class ContactsActivity extends ActionBarActivity implements ContactListEventListener {
	
	public static Contact[] contacts = {
		new ContactListFragment.Contact("Patrick Dempsey", "0151-44230219", R.drawable.patrick_dempsey_portrait),
		new ContactListFragment.Contact("Angela Merkel", "0151-45632189", R.drawable.angela_merkel_portrait)
	};
	
	
	private boolean isPhone = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		
		if(findViewById(R.id.tablet_contact_details_container) != null) {
			// we're in tablet mode!
			isPhone = false;
		}
		
		if(isPhone) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.phone_contact_list_container, new ContactListFragment());
			ft.commit(); 
		} else {
			// tablet...
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.tablet_contact_list_container, new ContactListFragment());
			
			ContactDetailFragment contactDetailFrag = new ContactDetailFragment();
			Bundle args = new Bundle();
			args.putInt(ContactDetailFragment.ARGUMENT_EXTRA_CONTACT_INDEX, 0);
			contactDetailFrag.setArguments(args);
			ft.replace(R.id.tablet_contact_details_container, contactDetailFrag);
			
			ft.commit(); 
		}
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.demo, menu);
		return true;
	}

	@Override
	public void onContactSelection(int contactInd) {
		if(isPhone) {
			Intent intent = new Intent(this, ContactDetailsActivity.class);
			intent.putExtra(ContactDetailFragment.ARGUMENT_EXTRA_CONTACT_INDEX, contactInd);
			startActivity(intent);
		} else {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			
			ContactDetailFragment contactDetailFrag = new ContactDetailFragment();
			Bundle args = new Bundle();
			args.putInt(ContactDetailFragment.ARGUMENT_EXTRA_CONTACT_INDEX, contactInd);
			contactDetailFrag.setArguments(args);
			ft.replace(R.id.tablet_contact_details_container, contactDetailFrag);
			
			ft.commit(); 
		}
		
	}

	
	
}
