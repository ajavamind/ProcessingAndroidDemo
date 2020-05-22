package in.omerjerk.processingdemo.helper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import in.omerjerk.processingdemo.MainActivity;
import in.omerjerk.processingdemo.R;
import processing.android.PFragment;

/**
 * Created by Andy Modla on 1/28/2017.
 */

public class HelperSketch extends PFragment {
    MainActivity activity;
    Switch toggle;
    PFragment sketch;

    public void setSketch(PFragment sketch) {
        this.sketch = sketch;
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_helpersketch, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        toggle = (Switch) view.findViewById(R.id.switch1);
        activity = (MainActivity) getActivity();
        toggle.setChecked(activity.isToggleSwitch());
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                activity.setToggleSwitch(isChecked);
            }
        });
        insertNestedFragment();
    }

    // Embeds the child fragment Processing sketch fragment dynamically
    private void insertNestedFragment() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, sketch).commit();
    }

}
