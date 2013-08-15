package de.accso.jug_demo_5.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import de.accso.jug_demo_5.ContactsActivity;
import de.accso.jug_demo_5.R;
import de.accso.jug_demo_5.fragments.ContactListFragment.Contact;

public class ContactDetailFragment extends Fragment {
	
	public static final String ARGUMENT_EXTRA_CONTACT_INDEX = "de.accso.jug_demo_5.argument_extra.ContactIndex";
	
	private String contactName;
	
	private String contactMobileNr;
	
	private int contactPortraitDrawableId;
	
	private View rootView;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		int contactsIndex = 0;
		if(savedInstanceState != null) {
			if(savedInstanceState.containsKey(ARGUMENT_EXTRA_CONTACT_INDEX)) {
				contactsIndex = savedInstanceState.getInt(ARGUMENT_EXTRA_CONTACT_INDEX);
			}
		} else {
			Bundle arguments = getArguments();
			if(arguments.containsKey(ARGUMENT_EXTRA_CONTACT_INDEX)) {
				contactsIndex = arguments.getInt(ARGUMENT_EXTRA_CONTACT_INDEX);
			}
		}
		
		Contact contact = ContactsActivity.contacts[contactsIndex];
		
		contactName = contact.name;
		contactMobileNr = contact.mobileNr;
		contactPortraitDrawableId = contact.portraitImageDrawableId;
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_contact_detail, null);
		
		
		ImageView portraitImageView = (ImageView) rootView.findViewById(R.id.portrait_image);
		portraitImageView.setImageDrawable(getActivity().getResources().getDrawable(contactPortraitDrawableId));
		
		TextView mobileNrTextView = (TextView) rootView.findViewById(R.id.phone_number);
		mobileNrTextView.setText(contactMobileNr);
		
		Button callButton = (Button) rootView.findViewById(R.id.call_button);
		callButton.setText(getString(R.string.call_contact, new Object[] {contactName}));
		
		
		return rootView;
	}
	
	

}
