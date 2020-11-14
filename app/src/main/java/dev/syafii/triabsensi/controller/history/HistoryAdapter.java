package dev.syafii.triabsensi.controller.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.syafii.triabsensi.R;
import dev.syafii.triabsensi.model.DataResponse;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<DataResponse> responseList;

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    public void setDataList(List<DataResponse> responseList){
        this.responseList = responseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataResponse data = responseList.get(position);
        holder.tvName.setText(data.getNama());
        holder.tvNik.setText(data.getNik());
        holder.tvAbsentIn.setText(data.getWaktuIn());
        holder.tvAbsentOut.setText(data.getWaktuOut());
        holder.tvStatus.setText(data.getKeterangan());
        holder.tvDate.setText(data.getTglAbsensi());

    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_history)
        TextView tvName;
        @BindView(R.id.tv_nik_history)
        TextView tvNik;
        @BindView(R.id.tv_absent_in)
        TextView tvAbsentIn;
        @BindView(R.id.tv_absent_out)
        TextView tvAbsentOut;
        @BindView(R.id.tv_status_history)
        TextView tvStatus;
        @BindView(R.id.tv_date_history)
        TextView tvDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
