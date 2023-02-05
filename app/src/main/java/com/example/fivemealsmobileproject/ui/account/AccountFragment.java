package com.example.fivemealsmobileproject.ui.account;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.ui.home.fragment.HomeFragment;
import com.example.fivemealsmobileproject.ui.login.view.PreLoginActivity;

import java.util.Random;


public class AccountFragment extends Fragment {

    private Context context;
    private View view;
    private AccountFragmentLogOut accountFragmentLogOut;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Navigation.findNavController(view).popBackStack();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
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
        this.view = view;

        AccountFragmentViewModel viewModel = new ViewModelProvider(requireActivity()).get(AccountFragmentViewModel.class);
        viewModel.initializeRepository(requireActivity());
        ImageView imageView = view.findViewById(R.id.imageViewAccountImage);
        TextView email = view.findViewById(R.id.textViewAccountEmail);
        TextView logout = view.findViewById(R.id.textViewAccountLogout);

        imageView.setImageResource(R.drawable.profile_picture);

        email.setText(viewModel.getSavedEmail());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    viewModel.CLEAR_ALL_DATA();
                    PreLoginActivity.startActivity(context);
                    accountFragmentLogOut.onLogoutClick();
            }
        });

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AccountFragmentLogOut){
            this.accountFragmentLogOut = (AccountFragmentLogOut) context;
        }
    }
    public interface AccountFragmentLogOut{
        void onLogoutClick();
    }
}