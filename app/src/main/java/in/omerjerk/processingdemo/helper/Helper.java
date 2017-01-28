package in.omerjerk.processingdemo.helper;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import in.omerjerk.processingdemo.MainActivity;
import in.omerjerk.processingdemo.R;

/**
 * Created by Andy Modla on 1/28/2017.
 */

public class Helper extends Fragment {
    MainActivity activity;
    public Helper() {
        super();
        activity = (MainActivity) getActivity();
    }

    Switch toggle;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_helper, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        toggle = (Switch) view.findViewById(R.id.switch1);
        activity = (MainActivity) getActivity();
        toggle.setChecked(activity.isToggleSwitch());
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                activity.setToggleSwitch(isChecked);
            }
        });
    }

}
