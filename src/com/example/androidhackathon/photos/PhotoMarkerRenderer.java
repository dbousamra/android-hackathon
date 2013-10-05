package com.example.androidhackathon.photos;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.androidhackathon.PhotoMarker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

public class PhotoMarkerRenderer extends DefaultClusterRenderer<PhotoMarker> {
    private final ImageView mImageView;
    private final IconGenerator mIconGenerator;
    private final Context context;


    public PhotoMarkerRenderer(Context context, GoogleMap map, ClusterManager<PhotoMarker> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
        this.mImageView = new ImageView(context);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(128, 128));
        this.mIconGenerator = new IconGenerator(context);
        mIconGenerator.setContentView(mImageView);
    }

    @Override
    protected void onBeforeClusterItemRendered(PhotoMarker item, MarkerOptions markerOptions) {
        mImageView.setImageBitmap(item.getBitmap());
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(mIconGenerator.makeIcon())).title(item.getPosition().toString());
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<PhotoMarker> cluster, MarkerOptions markerOptions) {
        super.onBeforeClusterRendered(cluster, markerOptions);
    }

    @Override
    public void setOnClusterItemClickListener(ClusterManager.OnClusterItemClickListener<PhotoMarker> listener) {

        super.setOnClusterItemClickListener(listener);
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        if (cluster.getSize() < 5) {
            return false;
        } else {
            return true;
        }
    }
}
