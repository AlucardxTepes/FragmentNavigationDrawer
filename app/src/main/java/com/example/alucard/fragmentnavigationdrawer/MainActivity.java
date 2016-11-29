package com.example.alucard.fragmentnavigationdrawer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private DrawerLayout mDrawer;
  private Toolbar toolbar;
  private NavigationView nvDrawer;
  private ActionBarDrawerToggle drawerToggle;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Set a toolbar to replace the actionbar and show nav icon
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
    setSupportActionBar(toolbar);

    // UI references
    mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    nvDrawer = (NavigationView) findViewById(R.id.nvView);

    // Setup drawer
    setupDrawerContent(nvDrawer);
  }

  private void setupDrawerContent(NavigationView navigationView) {
    navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
              }
            }
    );
  }

  private void selectDrawerItem(MenuItem item) {
    // Create a new fragment and specify the fragment to show based on item clicked
    Fragment fragment = null;
    Class fragmentClass;
    switch(item.getItemId()){
      case R.id.nav_first_fragment:
        fragmentClass = SampleFragment.class;
        break;
      default:
        fragmentClass = SampleFragment.class;
    }

    try {
      fragment = (Fragment) fragmentClass.newInstance();
    } catch (Exception e){
      e.printStackTrace();
    }

    // Insert the fragment by replacing any existing fragment
    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

    // highlight selected item in nav menu
    item.setCheckable(true);
    // Set action bar title
    setTitle(item.getTitle());
    // close the nav drawer
    mDrawer.closeDrawers();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // The action bar home/up action should open or close the drawer
    switch (item.getItemId()) {
      case android.R.id.home:
        mDrawer.openDrawer(GravityCompat.START);
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }


}
