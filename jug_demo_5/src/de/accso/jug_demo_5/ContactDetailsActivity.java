package de.accso.jug_demo_5;

import de.accso.jug_demo_5.fragments.ContactDetailFragment;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class ContactDetailsActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_details);
		
		Intent intent = getIntent();
		int contactsInd = intent.getIntExtra(ContactDetailFragment.ARGUMENT_EXTRA_CONTACT_INDEX, 0);
		
		ContactDetailFragment frag = new ContactDetailFragment();
		Bundle args = new Bundle();
		args.putInt(ContactDetailFragment.ARGUMENT_EXTRA_CONTACT_INDEX, contactsInd);
		frag.setArguments(args);
		
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.phone_contact_details_container, frag);
		ft.commit(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_details, menu);
		return true;
	}

}
