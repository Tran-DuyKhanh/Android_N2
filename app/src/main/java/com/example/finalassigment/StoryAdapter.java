//package com.example.finalassigment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
//
//    private final List<Story> stories;
//    private OnItemClickListener onItemClickListener;
//    public StoryAdapter(List<Story> stories) {
//        this.stories = stories;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_story, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Story story = stories.get(position);
//        holder.title.setText(story.getTitle());
//        holder.views.setText(String.format("%dM Views", story.getViews() / 1000000));
//
//        // For image loading (using Glide)
//        holder.image.setImageResource(story.getImageRes());
//        // Glide.with(holder.itemView.getContext()).load(story.getImageUrl()).into(holder.image);
//    }
//
//    @Override
//    public int getItemCount() {
//        return Math.min(stories.size(), 6); // Limit to max 6 items
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView image;
//        TextView title;
//        TextView views;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            image = itemView.findViewById(R.id.ivStory);
//            title = itemView.findViewById(R.id.tvTitle);
//            views = itemView.findViewById(R.id.tvViews);
//            itemView.setOnClickListener(v -> {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(stories.get(getAdapterPosition()));
//                }
//            });
//        }
//    }
//}
package com.example.finalassigment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private final List<Story> stories;
    private OnItemClickListener onItemClickListener;

    // Interface tùy chỉnh để xử lý sự kiện click
    public interface OnItemClickListener {
        void onItemClick(Story story);
    }

    // Constructor với listener
    public StoryAdapter(List<Story> stories, OnItemClickListener listener) {
        this.stories = stories;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.title.setText(story.getTitle());
        holder.views.setText(String.format("%dM Views", story.getViews() / 1000000));

        // Tải hình ảnh
        holder.image.setImageResource(story.getImageRes());
        // Nếu dùng Glide: Glide.with(holder.itemView.getContext()).load(story.getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return Math.min(stories.size(), 6); // Giới hạn tối đa 6 item
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView views;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ivStory);
            title = itemView.findViewById(R.id.tvTitle);
            views = itemView.findViewById(R.id.tvViews);

            // Xử lý sự kiện click
            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(stories.get(getAdapterPosition()));
                }
            });
        }
    }
}