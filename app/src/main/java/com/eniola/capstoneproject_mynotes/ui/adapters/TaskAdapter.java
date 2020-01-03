package com.eniola.capstoneproject_mynotes.ui.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.eniola.capstoneproject_mynotes.databinding.ItemTaskBinding;
import com.eniola.capstoneproject_mynotes.models.Tasks;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Tasks> taskItem;

    public TaskAdapter(List<Tasks> taskItems) {
        this.taskItem = taskItems;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemTaskBinding itemTaskBinding = ItemTaskBinding.inflate(layoutInflater, parent, false);
        return new TaskAdapter.ViewHolder(itemTaskBinding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Tasks tasks = taskItem.get(position);
        holder.bindDataToView(tasks);
    }

    @Override
    public int getItemCount() {
        return taskItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemTaskBinding itemTaskBinding;

        public ViewHolder(ItemTaskBinding itemTaskBinding) {
            super(itemTaskBinding.getRoot());
            this.itemTaskBinding = itemTaskBinding;
        }

        private void bindDataToView(final Tasks currentTask){
            String taskDescription = currentTask.getDescription();
            itemTaskBinding.taskDescription.setText(taskDescription);
        }
    }
}
