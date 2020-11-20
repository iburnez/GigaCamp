package com.wgu.gigacamp.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu.gigacamp.DetailsCampgroundActivity;
import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Address;
import com.wgu.gigacamp.Model.City;
import com.wgu.gigacamp.Model.State;
import com.wgu.gigacamp.R;
import com.wgu.gigacamp.Model.Campground;

import java.util.List;

public class Adapter_Campgrounds extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Account userAccount;
    private List<Campground> campgroundsList;
    private Campground campground;
    private Context campgroundContext;
    private LayoutInflater campgroundInflater;

    /**
     * DEFAULT CONSTRUCTOR FOR ADAPTER
     * @param campgroundContext context from calling activity
     * @param campgroundsList list of assessments passed to adapter to display in RV
     */
    public Adapter_Campgrounds(Context campgroundContext, List<Campground> campgroundsList, Account userAccount){
        this.campgroundsList = campgroundsList;
        this.campgroundContext = campgroundContext;
        this.userAccount = userAccount;

    }

    /**
     * CREATE RV VIEW HOLDER
     * Initialize inflater with context
     * Create view from list item layout
     * @param parent -
     * @param viewType -
     * @return viewholder of list_item view
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        campgroundInflater = LayoutInflater.from(campgroundContext);
        View view = campgroundInflater.inflate(R.layout.campground_list_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * BIND DATA TO VIEW HOLDER
     * Pass RV VIEW Holder with list_item view
     * Initialize array list of objects
     * Get Object from list by passing "position" param to .get method
     * Bind object values to list item view
     * Set OnClick Listener for when users click on object in list
     * Create intent to launch object detail activity and pass extra values to detail activity
     * @param holder viewholder holding view of list_item view for RB
     * @param position position in array list
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        campground = campgroundsList.get(position);
        Address cgAddress = DataManager.getAddress(campgroundContext, campground.getAddressID());
        City cgCtiy = DataManager.getCity(campgroundContext, cgAddress.getCityID());
        State cgState = DataManager.getState(campgroundContext, cgCtiy.getStateID());

        viewHolder.campgroundNameLabel.setText(campground.getCgName());
        viewHolder.campgroundPhoneTxt.setText(campground.getCgPhone());
        viewHolder.campgroundListItemCityTxt.setText(cgCtiy.getCityName());
        viewHolder.campgroundListItemState.setText(cgState.getStateName());
        viewHolder.campgroundListItemZipcode.setText(cgCtiy.getZipcode());
        viewHolder.campgroundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(campgroundContext, DetailsCampgroundActivity.class);
                intent.putExtra("campgroundID", campgroundsList.get(position).getCgID());
                intent.putExtra("accountID", userAccount.getAccountID());
                campgroundContext.startActivity(intent);
            }
        });

    }

    /**
     * GET ARRAY LIST SIZE
     * @return array list size
     */
    @Override
    public int getItemCount() {
    return campgroundsList.size();
    }

    /**
     * INITIALIZE VIEW HOLDER WITH XML LAYOUT
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView campgroundNameLabel;
        public TextView campgroundPhoneTxt;
        public TextView campgroundListItemCityTxt;
        public TextView campgroundListItemState;
        public TextView campgroundListItemZipcode;
        public View campgroundView;

        public ViewHolder(View itemView) {
            super(itemView);
            campgroundNameLabel = (TextView) itemView.findViewById(R.id.campgroundListItemNameLabel);
            campgroundPhoneTxt = (TextView) itemView.findViewById(R.id.campgroundListItemPhoneTxt);
            campgroundListItemCityTxt = (TextView) itemView.findViewById(R.id.campgroundListItemCityTxt);
            campgroundListItemState = (TextView) itemView.findViewById(R.id.campgroundListItemState);
            campgroundListItemZipcode = (TextView) itemView.findViewById(R.id.campgroundListItemZipcode);
            campgroundView = itemView;
        }
    }

}


