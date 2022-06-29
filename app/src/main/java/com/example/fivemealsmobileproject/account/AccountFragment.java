package com.example.fivemealsmobileproject.account;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.User;
import com.example.fivemealsmobileproject.home.HomeProductDetailsFragmentDirections;
import com.example.fivemealsmobileproject.login.PreLoginActivity;
import com.example.fivemealsmobileproject.login.SessionManager;
import com.example.fivemealsmobileproject.main.MainActivity;

import java.util.List;
import java.util.Random;


public class AccountFragment extends Fragment {

    private Context context;


    private static String[] profilePictures = new String[] {
            "http://tcap.pbworks.com/f/1435170280/myAvatar.png",
            "https://www.f6s.com/content-resource/profiles/3072512_original.jpg",
            "https://layogroup.net/wp-content/uploads/2019/07/522569-1eWJyL1490726864.png",
            "https://cdn.dribbble.com/users/2364329/screenshots/5930135/aa.jpg"
    };



    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.context = container.getContext();
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    private Random random = new Random();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.imageViewAccountImage);
        TextView username = view.findViewById(R.id.textViewAccountUsername);
        TextView email = view.findViewById(R.id.textViewAccountEmail);
        TextView password = view.findViewById(R.id.textViewAccountChangePassword);
        TextView logout = view.findViewById(R.id.textViewAccountLogout);
        TextView help = view.findViewById(R.id.textViewAccountHelp);

        Glide.with(this.context).load(profilePictures[random.nextInt(profilePictures.length)]).into(imageView);
        String user = SessionManager.getActiveSession(this.context);
        username.setText(user);

        User userActive = AppDataBase.getInstance(this.context).getUserDAO().getUserByUsername(user);
        email.setText(userActive.getEmail());

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = (NavDirections) AccountFragmentDirections.actionAccountFragmentToChangePasswordFragment2();
                Navigation.findNavController(view).navigate(action);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                    SessionManager.clearSession(context);
                    PreLoginActivity.startActivity(context);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = (NavDirections) AccountFragmentDirections.actionAccountFragmentToHelpFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });


        //TODO Trazer info completo do user
    }

}