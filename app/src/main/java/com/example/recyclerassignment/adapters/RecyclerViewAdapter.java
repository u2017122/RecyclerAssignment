package com.example.recyclerassignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerassignment.R;
import com.example.recyclerassignment.models.Result;
import com.example.recyclerassignment.models.UsersResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Result> results;

    public RecyclerViewAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                   .inflate(R.layout.users,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Result result=results.get(position);
        Picasso.get().load(result.getPicture().getLarge()).into(holder.image);
        holder.fullName.setText("Name: "+result.getName().getFirst()+" "+result.getName().getLast());
        holder.username.setText("Username: "+result.getLogin().getUsername());
        holder.country.setText("Country: "+result.getLocation().getCountry());
        holder.email.setText("Email: "+result.getEmail());
        holder.phone.setText("Phone: "+result.getPhone());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fullName;
        public TextView email;
        public TextView username;
        public TextView phone;
        public TextView country;
        public CircleImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.user_nameId);
            email=itemView.findViewById(R.id.user_emailId);
            phone=itemView.findViewById(R.id.user_phoneId);
            username=itemView.findViewById(R.id.user_usernameId);
            country=itemView.findViewById(R.id.user_countryId);
            image=itemView.findViewById(R.id.user_imgId);
        }
    }
}
