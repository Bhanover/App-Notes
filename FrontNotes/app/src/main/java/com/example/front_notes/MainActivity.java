package com.example.front_notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.front_notes.fragments.MainFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encuentra la barra de herramientas y la establece como la ActionBar de la actividad.
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Encuentra el DrawerLayout que se usará para el menú deslizante.
        drawer = findViewById(R.id.drawer_layout);
        // Configura un ActionBarDrawerToggle que vincula el DrawerLayout y la Toolbar.
        // Este controlador también gestionará los estados abiertos/cerrados del menú deslizante.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // Añade un listener al DrawerLayout para responder a los eventos de abrir/cerrar.
        drawer.addDrawerListener(toggle);
        // Sincroniza el estado del ActionBarDrawerToggle después de que el DrawerLayout esté configurado.
        toggle.syncState();
        // Encuentra la NavigationView y establece esta actividad como la que manejará los eventos de selección de ítems.
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Si la actividad se está creando por primera vez (por ejemplo, no después de un cambio de orientación),
        // entonces se inserta el fragmento AboutFragment por defecto.
        if (savedInstanceState == null) {
            // Crea una instancia de AboutFragment.
            Fragment fragment = new MainFragment();
            // Utiliza el FragmentManager para comenzar una transacción y reemplazar cualquier contenido existente con este fragmento.
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .commit();
            // Marca el ítem en la NavigationView que corresponde a AboutFragment.
            navigationView.setCheckedItem(R.id.nav_inicio);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Inicialmente establece el fragmento a mostrar como nulo.
        Fragment fragment = null;

        // Obtiene el ID del ítem seleccionado.
        int id = item.getItemId();

     /*   // Crea una instancia del fragmento correspondiente al ítem seleccionado.
        if (id == R.id.nav_inicio) {
            fragment = new MainFragment();
        } else if (id == R.id.nav_fragment2) {
            // Asegúrate de que CatalogActivity es realmente un Fragment para poder ser utilizado aquí.
            // Si no es así, necesitarás ajustar este código.
            fragment = new CatalogActivity();
        }
*/
        // Si se seleccionó un fragmento válido, inicia una transacción de fragmentos para mostrarlo.
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, fragment)
                    .commit();
        }

        // Cierra el menú deslizante una vez que se ha realizado una selección.
        drawer.closeDrawer(GravityCompat.START);
        // Devuelve verdadero para indicar que el evento de selección fue manejado.
        return true;
    }
}