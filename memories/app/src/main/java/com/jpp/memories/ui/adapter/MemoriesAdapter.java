package com.jpp.memories.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jpp.memories.R;
import com.jpp.memories.databinding.RowMemoriesBinding;
import com.jpp.memories.model.Memory;
import com.jpp.memories.ui.vm.ItemMemoryVM;

import java.util.List;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class MemoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Memory> data;
    private Context context;
    private int variableId;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    public MemoriesAdapter(@NonNull Context context, @NonNull List<Memory> data, int variableId) {
        this.context = context;
        this.data = data;
        this.variableId = variableId;
    }

    public List<Memory> getItems() {
        return this.data;
    }

    public void addList(List<Memory> items) {
        this.data.addAll(items);
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowMemoriesBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_memories, parent, false);
        return new VHRow(viewDataBinding);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof VHRow) {
            VHRow vhRow = (VHRow) holder;
            final Memory memory = this.data.get(position);
            vhRow.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnRecyclerViewItemClickListener.onItemClick(memory, position);
                }
            });
            vhRow.binding.setVariable(this.variableId, new ItemMemoryVM(memory, context.getResources()));
            vhRow.binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        if (this.data != null) {
            return this.data.size();
        } else {
            return 0;
        }
    }

    private class VHRow extends RecyclerView.ViewHolder {
        final RowMemoriesBinding binding;

        VHRow(RowMemoriesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setmOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener) {
        this.mOnRecyclerViewItemClickListener = mOnRecyclerViewItemClickListener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(Memory memory, int position);
    }
}
