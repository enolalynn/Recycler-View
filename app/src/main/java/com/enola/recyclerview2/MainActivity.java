package com.enola.recyclerview2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.enola.recyclerview2.adapters.FriendItemClickListener;
import com.enola.recyclerview2.adapters.MainAdapter;
import com.enola.recyclerview2.databinding.ActivityMainBinding;
import com.enola.recyclerview2.model.FriendRequest;

import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainAdapter mainAdapter;
    private FriendRequest[] friendRequests = {
            new FriendRequest("Monkey D Luffy", R.drawable.luffy),
            new FriendRequest("Tony Tony Chopper",R.drawable.franky),
            new FriendRequest("Nami", R.drawable.nami),
            new FriendRequest("Roronoa Zoro",R.drawable.zoro),
            new FriendRequest("Vinsmoke Sanji",R.drawable.sanji),
            new FriendRequest("Nico Robin",R.drawable.robin),
            new FriendRequest("Brook",R.drawable.brook),
            new FriendRequest("Boa Hancock",R.drawable.hancock),
            new FriendRequest("Trafalgar D. Water Law",R.drawable.law),
            new FriendRequest("Jimbei",R.drawable.jimbei),
            new FriendRequest("Franky",R.drawable.franky),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initLister();
    }

    private void initUi() {
        mainAdapter = new MainAdapter();
        mainAdapter.setFriendRequests(List.of(friendRequests));
        binding.rvFriends.setAdapter(mainAdapter);
        binding.rvFriends.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initLister() {
        binding.etFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<FriendRequest> filterList = List.of(friendRequests);
                if (!s.toString().isBlank()){
                    filterList = filterList.stream().filter(friendRequest ->
                            friendRequest.name().contains(s.toString()
                            )).collect(Collectors.toList());
                }
                mainAdapter.setFriendRequests(filterList);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mainAdapter.setItemClickListener(new FriendItemClickListener() {
            @Override
            public void onConfirm(FriendRequest friendRequest) {
                Toast.makeText(MainActivity.this, friendRequest.name() + "\nOn Confirm Clicked",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDelete(String friendName) {
                Toast.makeText(MainActivity.this, friendName +"\nOn Delete Clicked!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}