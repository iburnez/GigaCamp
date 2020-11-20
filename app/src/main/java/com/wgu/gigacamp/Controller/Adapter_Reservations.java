package com.wgu.gigacamp.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu.gigacamp.DetailsCampsiteActivity;
import com.wgu.gigacamp.DetailsReservationsActivity;
import com.wgu.gigacamp.Model.Account;
import com.wgu.gigacamp.Model.Campsite;
import com.wgu.gigacamp.Model.Reservations;
import com.wgu.gigacamp.R;

import java.util.List;

public class Adapter_Reservations extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Account userAccount;
    private List<Reservations> reservationList;
    private Reservations reservation;
    private Context reservationContext;
    private LayoutInflater reservationInflater;

    /**
     * DEFAULT CONSTRUCTOR FOR ADAPTER
     * @param reservationContext context from calling activity
     * @param reservationList list of assessments passed to adapter to display in RV
     */
    public Adapter_Reservations(Context reservationContext, List<Reservations> reservationList, Account userAccount){
        this.reservationList = reservationList;
        this.reservationContext = reservationContext;
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
        reservationInflater = LayoutInflater.from(reservationContext);
        View view = reservationInflater.inflate(R.layout.reservation_list_item, parent, false);
        return new Adapter_Reservations.ViewHolder(view);
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
        Adapter_Reservations.ViewHolder viewHolder = (Adapter_Reservations.ViewHolder) holder;
        reservation = reservationList.get(position);
        Campsite campsite = DataManager.getCampsite(reservationContext, reservationList.get(position).getCsID());
        String campgroundName = DataManager.getCampground(reservationContext, campsite.getCgID()).getCgName();
        String campgroundPhone = DataManager.getCampground(reservationContext, campsite.getCgID()).getCgPhone();

        viewHolder.resListItemArrival.setText(reservation.getArrivalDate());
        viewHolder.resListItemDeparture.setText(reservation.getDepartureDate());
        viewHolder.resListItemCampGroundName.setText(campgroundName);
        viewHolder.resListItemCampGroundPhone.setText(campgroundPhone);
        viewHolder.reservationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reservationContext, DetailsReservationsActivity.class);
                intent.putExtra("resID", reservationList.get(position).getResID());
                intent.putExtra("accountID", userAccount.getAccountID());
                reservationContext.startActivity(intent);
            }
        });

    }

    /**
     * GET ARRAY LIST SIZE
     * @return array list size
     */
    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    /**
     * INITIALIZE VIEW HOLDER WITH XML LAYOUT
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView resListItemCampGroundName;
        public TextView resListItemCampGroundPhone;
        public TextView resListItemArrival;
        public TextView resListItemDeparture;
        public View reservationView;

        public ViewHolder(View itemView) {
            super(itemView);
            resListItemCampGroundName = (TextView) itemView.findViewById(R.id.resListItemCampGroundName);
            resListItemCampGroundPhone = (TextView) itemView.findViewById(R.id.resListItemCampGroundPhone);
            resListItemArrival = (TextView) itemView.findViewById(R.id.resListItemArrival);
            resListItemDeparture = (TextView) itemView.findViewById(R.id.resListItemDeparture);
            reservationView = itemView;
        }
    }
}
