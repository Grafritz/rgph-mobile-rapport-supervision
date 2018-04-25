package ht.ihsi.rgph.rapport.supervision.Views.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.rapport.supervision.Constant.Constant;
import ht.ihsi.rgph.rapport.supervision.Exceptions.ManagerException;
import ht.ihsi.rgph.rapport.supervision.Managers.QueryRecordMngr;
import ht.ihsi.rgph.rapport.supervision.Managers.QueryRecordMngrImpl;
import ht.ihsi.rgph.rapport.supervision.Models.Agent_Evaluation_ExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.FormulaireExercicesModel;
import ht.ihsi.rgph.rapport.supervision.Models.KeyValueModel;
import ht.ihsi.rgph.rapport.supervision.Models.RowDataListModel;
import ht.ihsi.rgph.rapport.supervision.R;
import ht.ihsi.rgph.rapport.supervision.Utilities.Tools;

/**
 * Created by ajordany on 4/7/2016.
 */
public class DisplayListAdapter extends RecyclerView.Adapter<DisplayListAdapter.DetailRowViewHolder> {

    private List<RowDataListModel> targetList;
    private Context context;
    private LayoutInflater mInflater;
    private OnItemClickListener onItemClickListener;
    //private OnMenuItemClickListener onMenuItemClickListener;
    private int listType;
    private int profilId;
    private int statutModule;
    private long getPersId;

    public DisplayListAdapter(Context context, List<RowDataListModel> targetList, int listType, long getPersId){
        this.targetList=targetList;
        this.context=context;
        this.mInflater=LayoutInflater.from(context);
        this.listType=listType;
        this.profilId=0;
        this.getPersId=getPersId;
        //this.statutModule=Constant.STATUT_MODULE_KI_FINI_1;

    }

    public DisplayListAdapter(Context context, List<RowDataListModel> targetList, int listType, int profilId, int statutModule){
        this.targetList=targetList;
        this.context=context;
        this.mInflater=LayoutInflater.from(context);
        this.listType=listType;
        this.profilId=profilId;
        this.statutModule=statutModule;

    }

    @Override
    public DetailRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.template_list_row,null);
        DetailRowViewHolder detailRowViewHolder= new DetailRowViewHolder(view);
        return detailRowViewHolder;
    }

    @Override
    public void onBindViewHolder(DetailRowViewHolder holder, int position) {
        holder.bind(targetList.get(position));
    }

    /*public void setOnMenuItemClickListener(OnMenuItemClickListener listener){
        this.onMenuItemClickListener=listener;
    }*/

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
       return (null!= targetList ? targetList.size(): 0);
    }

    public void setFilter(List<RowDataListModel> filteredList){
        targetList=new ArrayList<RowDataListModel>();
        targetList.addAll(filteredList);
        notifyDataSetChanged();
    }

    public class DetailRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        public TextView title;
        public TextView desc;
        public Button btnModule;
        public Button btnMenu;
        private ImageView overflowIcon;
        private RowDataListModel row;

        public DetailRowViewHolder(View view){
            super(view);

            imageView= (ImageView) view.findViewById(R.id.imageView);
            title= (TextView) view.findViewById(R.id.title);
            desc= (TextView) view.findViewById(R.id.desc);
            overflowIcon= (ImageView) view.findViewById(R.id.overflowIcon);
            overflowIcon.setOnClickListener(this);
            view.setClickable(true);
            // view.animate();
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(!row.isEmpty()){
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(row);
                }
            }
        }

        /*private View.OnClickListener getClickModuleListener(){
            return new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemModuleClick(row);
                    }
                }
            };
        }

        private View.OnClickListener getClickMenuListener(){
            return new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemMenuClick(row);
                    }
                }
            };
        }*/

        public void bind(RowDataListModel row){
            this.row=row;
            int icon=getIcon();
            Picasso.with(context).load(icon).placeholder(icon)
                    .into(this.imageView);
            Picasso.with(context).load(R.drawable.dots_vertical).placeholder(R.drawable.dots_vertical)
                    .into(this.overflowIcon);
            this.overflowIcon.setVisibility(View.VISIBLE);
            if( listType == Constant.LIST_TYPE_EXERCICE ){
                KeyValueModel keyValueModel = (KeyValueModel) row.getModel();

                if( keyValueModel.getKey() == ""+Constant.EXERCICE_ENTRAINEMENT_1 ){
                    Picasso.with(context).load(R.drawable.ic_eva_entrainement).placeholder(R.drawable.ic_eva_entrainement).into(this.imageView);

                }else if( keyValueModel.getKey() == ""+Constant.EXERCICE_FORMATIVE_2 ){
                    Picasso.with(context).load(R.drawable.ic_eva_formative).placeholder(R.drawable.ic_eva_formative).into(this.imageView);

                }else if( keyValueModel.getKey() == ""+Constant.EXERCICE_SOMMATIVE_3 ){
                    Picasso.with(context).load(R.drawable.ic_eva_somrmative).placeholder(R.drawable.ic_eva_somrmative).into(this.imageView);

                }else if( keyValueModel.getKey() == ""+Constant.EXERCICE_OBSERVATION_4 ){
                    Picasso.with(context).load(R.drawable.ic_eva_observation).placeholder(R.drawable.ic_eva_observation).into(this.imageView);
                }
                this.overflowIcon.setVisibility(View.VISIBLE);
                this.title.setText(Html.fromHtml((row.getTitle())));
                this.desc.setText(Html.fromHtml(row.getDesc()));
                this.desc.setVisibility(View.GONE);

            }else  if( listType == Constant.LIST_MODULE_EXERCICES ){
                FormulaireExercicesModel formExercicesModel = (FormulaireExercicesModel) row.getModel();

                if( getAgentCanGoToEvaluation(formExercicesModel.getCodeExercice()) ){//if( formExercicesModel.getStatut() == Constant.OUI_1){
                    Picasso.with(context).load(R.drawable.ic_doc_star).placeholder(R.drawable.ic_doc_star)
                            .into(this.imageView);
                    this.overflowIcon.setVisibility(View.GONE);
                }else{
                    Picasso.with(context).load(R.drawable.ic_doc).placeholder(R.drawable.ic_doc)
                            .into(this.imageView);
                    this.overflowIcon.setVisibility(View.VISIBLE);
                }
                this.title.setText(Html.fromHtml((row.getTitle())));
                this.desc.setText(Html.fromHtml(row.getDesc()));
                if(row.isEmpty()){
                    overflowIcon.setVisibility(View.INVISIBLE);
                }
            }


        }
        public boolean getAgentCanGoToEvaluation(long codeExercice) {
            try{
                QueryRecordMngr queryRecordMngr=new QueryRecordMngrImpl(context);
                Agent_Evaluation_ExercicesModel aee = queryRecordMngr.getAgent_Evaluation_Exercices_ByIdAgent(codeExercice, getPersId);
                if( aee!=null && aee.getCodeExercice()!=null ){
                    return true;
                }
            } catch (ManagerException ex) {
                Tools.LogCat("ManagerException-getAgentCanGoToEvaluation():  ", ex);
                return false;
            } catch (Exception ex) {
                Tools.LogCat("Exception-getAgentCanGoToEvaluation():  ", ex);
                return false;
            }
            return false;
        }
        /*public boolean getAgentCanGoToEvaluation(FormulaireExercicesModel formExercicesModel) {
            try{
                FormDataMngrImpl formDataMngr = new FormDataMngrImpl(context);
                Agent_Evaluation_ExercicesModel aee = .getAgent_Evaluation_Exercices_ByIdAgent(formExercicesModel.getCodeExercice(), getPersId);
                if( aee!=null && aee.getCodeExercice()!=null ){
                   return true;
                }
            } catch (ManagerException ex) {
                Tools.LogCat("ManagerException-getAgentCanGoToEvaluation():  ", ex);
                return false;
            } catch (Exception ex) {
                Tools.LogCat("Exception-getAgentCanGoToEvaluation():  ", ex);
                return false;
           }
            return false;
        }*/

public int getIcon(){
            return R.drawable.ic_doc;
        }

    }

    //Interface on row click listener
    public interface OnItemClickListener {
        void onItemClick(RowDataListModel entity);
    }

}
