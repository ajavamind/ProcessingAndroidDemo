package in.omerjerk.processingdemo;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import in.omerjerk.processingdemo.helper.Helper;
import in.omerjerk.processingdemo.helper.HelperSketch;
import in.omerjerk.processingdemo.sketch.Directional;
import in.omerjerk.processingdemo.sketch.EmptySketch;
import in.omerjerk.processingdemo.sketch.Particles;
import in.omerjerk.processingdemo.sketch.Reflection;
import processing.android.PFragment;
import processing.core.PApplet;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    // values shared among sketches and fragments
    public boolean toggleSwitch = false;

    public boolean isToggleSwitch() {
        return toggleSwitch;
    }

    public void setToggleSwitch(boolean toggleSwitch) {
        this.toggleSwitch = toggleSwitch;
        //commit preferences on change
        //final SharedPreferences prefs = getSharedPreferences(PREFERENCES,0);
        //final SharedPreferences.Editor editor = prefs.edit();
        //editor.putBoolean("your_key", toggleSwitch);
        //editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = new PFragment();
        HelperSketch helperFragment = new HelperSketch();
        PApplet sketch;
        boolean child = false;
        switch (position) {
            case 0:
                sketch = new Directional();
                ((PFragment) fragment).setSketch(sketch);
                break;
            case 1:
                sketch = new Reflection();
                ((PFragment) fragment).setSketch(sketch);
                break;
            case 2:
                sketch = new Particles();
                ((PFragment) fragment).setSketch(sketch);
                break;
            case 3:
                sketch = new EmptySketch();
                ((PFragment) fragment).setSketch(sketch);
                break;
            case 4: // add Android View fragment
                fragment = new Helper();
                break;
            case 5: // add Android View fragment with child sketch
                sketch = new Directional();
                ((PFragment) fragment).setSketch(sketch);
                helperFragment.setSketch(fragment);
                child = true;
                break;
            case 6: // add Android View fragment with child sketch
                sketch = new Particles();
                ((PFragment) fragment).setSketch(sketch);
                helperFragment.setSketch(fragment);
                child = true;
                break;
            default:
                throw new UnsupportedOperationException("Invalid position");
        }

        if (child) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, helperFragment)
                    .commit();
        }
        else {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_directional);
                break;
            case 2:
                mTitle = getString(R.string.title_empty);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


}
