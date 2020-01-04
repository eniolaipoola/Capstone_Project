package com.eniola.capstoneproject_mynotes.ui.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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
            final String taskDescription = currentTask.getDescription();
            itemTaskBinding.taskDescription.setText(taskDescription);
            itemTaskBinding.taskStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(itemTaskBinding.taskStatus.isChecked()){
                        //update task status
                        currentTask.setStatus("done");
                        if(currentTask.getStatus().equals("done")){
                            itemTaskBinding.taskDescription.setPaintFlags(
                                    itemTaskBinding.taskDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
                            );
                        }
                    }
                }
            });
        }
    }
}
