package hackaton.com.br.hackatonapp.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import hackaton.com.br.hackatonapp.MagicParameters;
import hackaton.com.br.hackatonapp.PandorabotsAPI;
import hackaton.com.br.hackatonapp.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView mNavigationView;
    private DrawerLayout mDrawer;
    public boolean local = true;
    String client_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        startRobot();

        UserViewFragment userViewFragment = new UserViewFragment();
        // Insert the fragment by replacing any existing fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentMainFrameLayout, userViewFragment)
                .commit();

        mNavigationView.setCheckedItem(R.id.nav_user_view);
    }

    private void startRobot(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        client_name = prefs.getString("client_name", "");
        // set client name if there is none
        if (client_name == "") {
            PandorabotsAPI pApi = new PandorabotsAPI(MagicParameters.hostname, MagicParameters.username, MagicParameters.userkey, "");
            client_name = pApi.debugBot("","init", false, false, false, true);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("client_name", client_name);
            editor.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_user_view) {
            UserViewFragment userViewFragment = new UserViewFragment();
            // Insert the fragment by replacing any existing fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentMainFrameLayout, userViewFragment)
                    .commit();

            mNavigationView.setCheckedItem(R.id.nav_user_view);
            setTitle(item.getTitle());
        } else if (id == R.id.nav_products) {
            FragmentProducts fragmentProducts = new FragmentProducts();
            // Insert the fragment by replacing any existing fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentMainFrameLayout, fragmentProducts)
                    .commit();

//            // Highlight the selected item, update the title, and close the drawer
//            mNavigationView.setCheckedItem(R.id.nav_streamers);
            setTitle(item.getTitle());

        } else if (id == R.id.nav_feed) {
            FragmentFeed fragmentFeed = new FragmentFeed();
            Bundle bundle = new Bundle();
            bundle.putString("client_name", client_name);
            fragmentFeed.setArguments(bundle);

            // Insert the fragment by replacing any existing fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentMainFrameLayout, fragmentFeed)
                    .commit();

//            // Highlight the selected item, update the title, and close the drawer
//            mNavigationView.setCheckedItem(R.id.nav_streamers);
            setTitle(item.getTitle());
        } else if (id == R.id.nav_settings) {

        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
