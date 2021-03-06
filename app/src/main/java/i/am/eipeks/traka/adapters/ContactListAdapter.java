package i.am.eipeks.traka.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import i.am.eipeks.traka.R;
import i.am.eipeks.traka.util.Contact;


public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.Holder> {

    private Context context;
    private ArrayList<Contact> contacts;
    private CardViewClickListener cardViewClickListener;

    public ContactListAdapter(Context context, ArrayList<Contact> contacts){
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder((CardView) LayoutInflater.from(this.context).inflate(R.layout.contact_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final Contact currentContact = this.contacts.get(position);
        holder.contactName.setText(currentContact.getContactName());
        holder.contactPhone.setText(currentContact.getContactNumber());
        holder.contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("InflateParams")
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setView(LayoutInflater.from(context).inflate(R.layout.contact_information_layout, null))
                        .setCancelable(true)
                        .create();
                dialog.show();
            }
        });
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardViewClickListener != null){
                    cardViewClickListener.onCardViewClick(currentContact, holder.getAdapterPosition());
                }
            }
        });

    }

    public void setOnCardViewClickListener(CardViewClickListener cardViewClickListener){
        this.cardViewClickListener = cardViewClickListener;
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView contactName, contactPhone;
        CardView rootView;
        ImageView contactImage;

        Holder(CardView cardView) {
            super(cardView);
            rootView = cardView;
            contactName = cardView.findViewById(R.id.contact_name);
            contactPhone = cardView.findViewById(R.id.contact_phone_number);
            contactImage = cardView.findViewById(R.id.contact_image);
        }
    }

    public interface CardViewClickListener{
        void onCardViewClick(Contact contact, int position);
    }

}
