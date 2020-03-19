package suitmedia.test.intern.ui.event;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import suitmedia.test.intern.R;
import suitmedia.test.intern.data.EventModel;
import suitmedia.test.intern.ui.ItemClickListener;

public class EventFragment extends Fragment implements MapListener {

    @BindView(R.id.rv_event_fragment)
    RecyclerView rvEvent;
    @BindView(R.id.map_view)
    MapView mapView;
    ArrayList<EventModel> mData;
    ArrayList<Marker> markers;
    EventRVAdapter mAdapter;
    public EventFragment() {
        // Required empty public constructor

    }

    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.bind(this,view);
        setupView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setupView() {
        mAdapter = new EventRVAdapter(getContext(), "mapFragment");
        markers = new ArrayList<>();
        rvEvent.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter.setMapListener(this);
        rvEvent.setAdapter(mAdapter);
        mapSetup();
//        mapView.setVisibility(View.VISIBLE);
    }

    private void mapSetup() {

        //important! set your user agent to prevent getting banned from the osm servers
        Configuration.getInstance().load(getActivity(), PreferenceManager.getDefaultSharedPreferences(getActivity()));

        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setTilesScaledToDpi(true);

        // add multi-touch capability
        mapView.setMultiTouchControls(true);

        // add compass to map
        CompassOverlay compassOverlay = new CompassOverlay(getActivity(), new InternalCompassOrientationProvider(getActivity()), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);

        // get map controller
        IMapController controller = mapView.getController();

        GeoPoint position = new GeoPoint(-6.888525, 107.608396);
        controller.setCenter(position);
        controller.setZoom(18);
    }

    private void addMarker(double latitude, double longitude, String title){
        GeoPoint startPoint = new GeoPoint(latitude, longitude);
        Marker marker = new Marker(mapView);
        marker.setIcon(this.getResources().getDrawable(R.drawable.ic_location_on_blue_30dp));
        marker.setTitle(title);
        marker.setPosition(startPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        markers.add(marker);
        mapView.getOverlays().add(marker);
    }

    public void setData(ArrayList<EventModel> data){
        this.mData = data;
        mAdapter.setData(data);
        //set marker for data
        for(int i = 0; i < mData.size(); i++){
            addMarker(mData.get(i).latitude, mData.get(i).longitude, mData.get(i).eventName);
        }
    }

    @Override
    public void pinPointGeoLoc(double latitude, double longitude) {
        IMapController controller = mapView.getController();
        GeoPoint position = new GeoPoint(latitude, longitude);
        controller.setCenter(position);
        controller.setZoom(18);
    }

    @Override
    public void showInfoMarker(int idx) {
        markers.get(idx).showInfoWindow();
    }
}
