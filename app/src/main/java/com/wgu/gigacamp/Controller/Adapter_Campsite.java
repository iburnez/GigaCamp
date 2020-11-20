package com.wgu.gigacamp.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu.gigacamp.DetailsCampgroundActivity;
import com.wgu.gigacamp.DetailsCampsiteActivity;
import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Campground;
import com.wgu.gigacamp.Model.Campsite;
import com.wgu.gigacamp.MyCampgroundsActivity;
import com.wgu.gigacamp.R;

import java.util.List;

public class Adapter_Campsite extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Account userAccount;
    private List<Campsite> campsiteList;
    private Campsite campsite;
    private Context campsiteContext;
    private LayoutInflater campsiteInflater;

    /**
     * DEFAULT CONSTRUCTOR FOR ADAPTER
     * @param campsiteContext context from calling activity
     * @param campsiteList list of assessments passed to adapter to display in RV
     */
    public Adapter_Campsite(Context campsiteContext, List<Campsite> campsiteList, Account userAccount){
        this.campsiteList = campsiteList;
        this.campsiteContext = campsiteContext;
        this.userAccount = userAccount;
        System.out.println("Adapter User account" + userAccount.getEmail());

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
        campsiteInflater = LayoutInflater.from(campsiteContext);
        View view = campsiteInflater.inflate(R.layout.campsite_list_item, parent, false);
        return new Adapter_Campsite.ViewHolder(view);
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
        Adapter_Campsite.ViewHolder viewHolder = (Adapter_Campsite.ViewHolder) holder;
        campsite = campsiteList.get(position);

        viewHolder.campsiteListItemSiteNumber.setText(campsite.getCampsiteNum());
        viewHolder.campsiteListItemPrice.setText(Integer.toString(campsite.getPrice()));
        viewHolder.campsiteListItemResButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(campsiteContext, DetailsCampsiteActivity.class);
                intent.putExtra("campsiteID", campsiteList.get(position).getCsID());
                intent.putExtra("accountID", userAccount.getAccountID());
                campsiteContext.startActivity(intent);
            }
        });
        viewHolder.campsiteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(campsiteContext, DetailsCampsiteActivity.class);
                intent.putExtra("campsiteID", campsiteList.get(position).getCsID());
                intent.putExtra("accountID", userAccount.getAccountID());
                campsiteContext.startActivity(intent);
            }
        });

    }

    /**
     * GET ARRAY LIST SIZE
     * @return array list size
     */
    @Override
    public int getItemCount() {
        return campsiteList.size();
    }

    /**
     * INITIALIZE VIEW HOLDER WITH XML LAYOUT
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView campsiteListItemSiteNumber;
        public TextView campsiteListItemPrice;
        public Button campsiteListItemResButton;
        public View campsiteView;

        public ViewHolder(View itemView) {
            super(itemView);
            campsiteListItemSiteNumber = (TextView) itemView.findViewById(R.id.campsiteListItemSiteNumber);
            campsiteListItemPrice = (TextView) itemView.findViewById(R.id.campsiteListItemPrice);
            campsiteListItemResButton = (Button) itemView.findViewById(R.id.campsiteListItemResButton);
            campsiteView = itemView;
        }
    }
}
