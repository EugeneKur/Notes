package ru.geekbrains.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container1, new ListNotesFragment())
                .commit();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.action_about){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .addToBackStack("")
                            .add(R.id.fragment_container1, new AboutFragment())
                            .commit();

                    drawerLayout.closeDrawers();

                    return true;
                } else if (id == R.id.action_look) {
                    // TODO метод для смены вида расположения списка заметок, например, список/плитка
                    return true;
                } else if (id == R.id.action_search) {
                    // TODO метод для поиска заметки
                    return true;
                } else if (id == R.id.action_back) {
                    finish();
                    return true;
                } else if (id == R.id.action_font_size) {
                    // TODO метод для выбора размера шрифта
                    return true;
                } else if (id == R.id.action_font_style) {
                    // TODO метод для выбора стиля шрифта
                    return true;
                } else if (id == R.id.action_sort) {
                    // TODO метод для выбора сортировки заметки
                    return true;
                } else if (id == R.id.action_color_app) {
                    // TODO метод для выбора цветовой схемы приложения
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().size() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItemDelete = menu.findItem(R.id.action_delete);
        if (menuItemDelete != null) {
            menuItemDelete.setVisible(false);
        }

        MenuItem menuItemShare = menu.findItem(R.id.action_share);
        if (menuItemShare != null) {
            menuItemShare.setVisible(false);
        }

        MenuItem menuItemRemind = menu.findItem(R.id.action_remind);
        if (menuItemRemind != null) {
            menuItemRemind.setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about){
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .add(R.id.fragment_container1, new AboutFragment())
                    .commit();

            return true;
        } else if (id == R.id.action_look) {
            // TODO метод для смены вида расположения списка заметок, например, список/плитка
            return true;
        } else if (id == R.id.action_search) {
            // TODO метод для поиска заметки
            return true;
        } else if (id == R.id.action_back) {
            finish();
            return true;
        } else if (id == R.id.action_font_size) {
            // TODO метод для выбора размера шрифта
            return true;
        } else if (id == R.id.action_font_style) {
            // TODO метод для выбора стиля шрифта
            return true;
        } else if (id == R.id.action_sort) {
            // TODO метод для выбора сортировки заметки
            return true;
        } else if (id == R.id.action_color_app) {
            // TODO метод для выбора цветовой схемы приложения
            return true;
        }



        return super.onOptionsItemSelected(item);
    }
}