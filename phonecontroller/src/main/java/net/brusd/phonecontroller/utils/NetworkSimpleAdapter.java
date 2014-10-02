package net.brusd.phonecontroller.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import android.widget.SimpleAdapter;
import android.widget.TextView;

import net.brusd.phonecontroller.Constant;
import net.brusd.phonecontroller.R;



import java.util.List;
import java.util.Map;



/**
 * Created by BruSD on 6/7/2014.
 */
public class NetworkSimpleAdapter extends SimpleAdapter {

    private Activity parentActivity;
    private List<? extends Map<String, ?>> data;
    private String associatedString;
    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    public NetworkSimpleAdapter(Activity context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);

        this.parentActivity =  context;
        this.data = data;
        associatedString = parentActivity.getResources().getString(R.string.associated_with_string);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView wifiNetworkNameTextView, wifiAssociateStatusTextView;
        ImageButton wifiAddRemoveImageButton;

        LayoutInflater li = (LayoutInflater) parentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.item_list_of_network_layout, parent, false);

        wifiNetworkNameTextView = (TextView) convertView.findViewById(R.id.wifi_network_name_text_view);
        String wifiName = data.get(position).get(Constant.WIFI_NAME).toString();

        wifiAssociateStatusTextView = (TextView)convertView.findViewById(R.id.associate_with_mode_text_view);


        int relatedMode = Integer.valueOf(data.get(position).get(Constant.MODE_NAME).toString());
        wifiNetworkNameTextView.setText(wifiName);

        wifiAddRemoveImageButton = (ImageButton)convertView.findViewById(R.id.wifi_add_remove_image_button);

        String asociete = associatedString;
        switch (relatedMode){
            case Constant.MODE_FULL:
                asociete += " "+ parentActivity.getString(R.string.full_volume_mode_string);
                break;
            case Constant.MODE_MEDIUM:
                asociete += " "+ parentActivity.getString(R.string.medium_volume_mode_string);
                break;
            case Constant.MODE_SILENT:
                asociete += " "+ parentActivity.getString(R.string.silent_volume_mode_string);
                break;
            default:
                wifiAddRemoveImageButton.setImageDrawable(parentActivity.getResources().getDrawable(android.R.drawable.ic_menu_add));
                break;
        }
        wifiAssociateStatusTextView.setText(asociete);

        return convertView;
    }
}
