package com.ls.wrenslibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Link navigation bar to required fragments
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.mainLibraryFragment, R.id.addBookFragment, R.id.editBookFragment).build();

        // Find nav controller
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);

        // Link nav controller to navigation bar
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }
}