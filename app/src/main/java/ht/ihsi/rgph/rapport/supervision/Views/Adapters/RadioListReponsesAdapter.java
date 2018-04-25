package ht.ihsi.rgph.rapport.supervision.Views.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Models.ReponsesModel;
import ht.ihsi.rgph.rapport.supervision.R;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;

/**
 * Created by JFDuverseau on 5/15/2017.
 */
public class RadioListReponsesAdapter extends RecyclerView.Adapter<RadioListReponsesAdapter.DetailRowViewHolder> {

    public int mSelectedItem = -1;
    private List<ReponsesModel> reponseModelList;
    private ReponsesModel reponseModelSelectionner=null;
    private Context context;
    private int typeEvenement;
    private LayoutInflater mInflater;
    private View.OnClickListener onClickListener;
    private OnItemReponsesClickListener onItemReponseClickListener;
    private OnItemCheckedChangedListener onItemCheckedChangedListener;
    private OnBindViewHolderListener onBindViewHolderListener;
    //private OnMenuItemClickListener onMenuItemClickListener;
    public DetailRowViewHolder viewHolder;

    public RadioListReponsesAdapter(Context context, int typeEvenement, List<ReponsesModel> questionReponseModelList){
        this.reponseModelSelectionner=null;
        this.reponseModelList = questionReponseModelList;
        this.context = context;
        this.typeEvenement = typeEvenement;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public DetailRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_questionnaire, null);
        DetailRowViewHolder detailRowViewHolder= new DetailRowViewHolder(view, this.typeEvenement);
        return detailRowViewHolder;
    }

    @Override
    public void onBindViewHolder(DetailRowViewHolder viewHolder, int position) {
        ReponsesModel row = reponseModelList.get(position);
        viewHolder.bind(row);
        viewHolder.radioButton.setChecked(position == mSelectedItem);
        this.viewHolder = viewHolder;

        if(mSelectedItem == position) {
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
            reponseModelSelectionner = viewHolder.getReponseModelSelectionner();
            reponseModelSelectionner = row;

        }//else
        //    viewHolder.itemView.setBackgroundColor(Color.RED);

        //if (onBindViewHolderListener != null) {
        //    onBindViewHolderListener.onBindViewHolder(reponseModelList);
        //}
        //if( viewHolder.radioButton.isChecked() ){
        //    reponseModelSelectionner = reponseModelList.get(position);
        //}
        //reponseModelSelectionner = viewHolder.getReponseModelSelectionner();
    }

    //public void setOnBindViewHolderListener(OnItemClickListener listener) {
    //    this.onItemReponseClickListener = listener;
    //}

    public void setOnItemReponseClickListener(OnItemReponsesClickListener listener) {
        if( this.typeEvenement != Constant.ACTION_AFFICHER ) {
            this.onItemReponseClickListener = listener;
        }
    }

    public void setOnItemCheckedChangedListener(OnItemCheckedChangedListener listener) {
        this.onItemCheckedChangedListener = listener;
    }

    public ReponsesModel getReponseModelSelectionner() {
        return reponseModelSelectionner;
    }
    public void setReponseModelSelectionner(ReponsesModel reponseModelSelectionner){
        this.reponseModelSelectionner = reponseModelSelectionner;
    }
    //region GETTER / SETTER
    public List<ReponsesModel> getReponseModelList() {
        return reponseModelList;
    }

    //endregion

    @Override
    public int getItemCount() {
        return (null!= reponseModelList ? reponseModelList.size(): 0);
    }

    public void setFilter(List<ReponsesModel> filteredList){
        reponseModelList =new ArrayList<ReponsesModel>();
        reponseModelList.addAll(filteredList);
        notifyDataSetChanged();
    }

    public boolean tryMoveSelection(RecyclerView.LayoutManager layoutManager, int direction) {
        try {
            int nextSelectItem = mSelectedItem + direction;
            int itemCount = getItemCount();
            //ToastUtility.LogCat("E", "tryMoveSelection() nextSelectItem : " + nextSelectItem + " \n|  direction : " + direction
            //        + " \n|  mSelectedItem : " + mSelectedItem
            //        + " \n|  itemCount : " + itemCount);
            // If still within valid bounds, move the selection, notify to redraw, and scroll
            if (nextSelectItem >= 0 && nextSelectItem < itemCount) {
                //ToastUtility.LogCat("W", "tryMoveSelection() nextSelectItem : " + nextSelectItem + " |  getItemCount : " + itemCount);
                //ToastUtility.LogCat("E", "tryMoveSelection() if (nextSelectItem >= 0 && nextSelectItem < itemCount) {");
                //notifyItemChanged(mSelectedItem);
                mSelectedItem = nextSelectItem;
                //notifyItemChanged(mSelectedItem);
                layoutManager.scrollToPosition(mSelectedItem);
                //ToastUtility.LogCat("W", "2 tryMoveSelection() nextSelectItem : " + nextSelectItem + " |  getItemCount : " + itemCount);
                return true;
            } else {
                //ToastUtility.LogCat("W", "tryMoveSelection() else (nextSelectItem >= 0 && nextSelectItem < itemCount) \n nextSelectItem : " + nextSelectItem + " |  getItemCount : " + itemCount);
                //return false;
            }
        }catch (Exception ex) {
            Tools.LogCat( "Exception: tryMoveSelection(): ", ex);
            ex.printStackTrace();
        }
        return false;
    }

    public int getItemSelected(){

        return 1;
    }

    public class DetailRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        public RadioButton radioButton;
        public TextView tReponse;
        public int typeEvenement;
        private ReponsesModel reponseModel;
        private ReponsesModel reponseModelSelectionner;

        public DetailRowViewHolder(View view, int typeEvenement){
            super(view);

            this.typeEvenement=typeEvenement;

            radioButton = (RadioButton) view.findViewById(R.id.radio);
            tReponse = (TextView) view.findViewById(R.id.text);
            view.setClickable(true);
            //view.animate();
            view.setOnClickListener(this);
            /*View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyItemRangeChanged(0, reponseModelList.size());
                }
            };
            itemView.setOnClickListener(clickListener);*/
            radioButton.setOnClickListener(this);
            //radioButton.setOnCheckedChangeListener(this);
        }

        //region GETTER SETTER
        public ReponsesModel getReponseModelSelectionner() {
            return reponseModelSelectionner;
        }

        public RadioButton getRadioButton() {
            return radioButton;
        }

        public void setRadioButton(RadioButton radioButton) {
            this.radioButton = radioButton;
        }

        public TextView gettReponse() {
            return tReponse;
        }

        public void setReponse(TextView tReponse) {
            this.tReponse = tReponse;
        }

        public ReponsesModel getReponseModel() {
            //reponseModel.radioButton = getRadioButton();
            //reponseModel.tReponse = gettReponse();
            return reponseModel;
        }

        public void setReponseModel(ReponsesModel reponseModel) {
            //reponseModel.radioButton = getRadioButton();
            //reponseModel.tReponse = gettReponse();
            this.reponseModel = reponseModel;
        }

        //endregion

        @Override
        public void onClick(View v) {
            //ToastUtility.ToastMessage(context, "Event onClick IN RadioListReponsesAdapter: " + reponseModel.getCodeQuestion()+ " :-: " + reponseModel.getLibelleReponse() );
            if( this.typeEvenement != Constant.ACTION_AFFICHER ) {
                reponseModelSelectionner = reponseModel;
                if (onItemReponseClickListener != null) {
                    onItemReponseClickListener.onItemClick(reponseModel);
                }
                mSelectedItem = getAdapterPosition();
                notifyItemRangeChanged(0, reponseModelList.size());
                v.setBackgroundColor(Color.RED);
            }

        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked) {
                //selectedStrings.add(tv.getText().toString());
                reponseModelSelectionner = reponseModel;
                if (onItemCheckedChangedListener != null) {
                    onItemCheckedChangedListener.OnItemCheckedChangedListener(reponseModel);
                }
                Tools.LogCat("onCheckedChanged | " + reponseModel.toString());
            }else{
                //selectedStrings.remove(tv.getText().toString());
            }
            //mSelectedItem = getAdapterPosition();
        }

        public void bind(ReponsesModel row){
            row.radioButton = getRadioButton();
            row.tReponse = gettReponse();
            this.reponseModel = row;
            this.tReponse.setText(row.getLibelleReponse());
            //this.radioButton.setText(row.getLibelleReponse());
            if ( row.radioButton.isChecked() ) {
                reponseModelSelectionner = row;
            }
        }//
    }


    //Interface on reponseModel click listener
    public interface OnBindViewHolderListener {
        void onBindViewHolder(List<ReponsesModel> questionReponseModelList);
    }

    public interface OnItemReponsesClickListener {
        void onItemClick(ReponsesModel entity);
        //void onItemClick_2(QuestionReponseModel entity  );
        //void onItemMenuClick(QuestionReponseModel entity);
    }

    public interface OnItemCheckedChangedListener {
        void OnItemCheckedChangedListener(ReponsesModel entity);
    }

    /*public interface OnMenuItemClickListener{
        void onMenuItemModifye(QuestionReponseModel row);
        void onMenuItemKontinye(QuestionReponseModel row);
        void onMenuItemRapport(QuestionReponseModel row);
    }*/
}
