package ht.ihsi.rgph.rapport.supervision.Models;

import java.io.Serializable;

/**
 * Created by JFDuverseau on 8/12/2017.
 */
public class RowDataListModel implements Serializable{

    private long id;
    private String icon;
    private String title;
    private String desc;
    private String desc2;
    private String desc3;
    private boolean isComplete;
    private boolean isModuleMenu;
    private boolean isEmpty;
    private int tableName;

    private Object model;

    public RowDataListModel(){
        this.icon="";
        this.title="";
        this.desc="";
        this.desc2="";
        this.desc3="";
        this.isModuleMenu=false;
        this.isComplete=true;
    }

    public RowDataListModel(String icon, String title, String desc){
        this.icon=icon;
        this.title=title;
        this.desc=desc;
        //this.desc2=desc2;
        this.isModuleMenu=true;
        this.isComplete=true;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean isModuleMenu() {
        return isModuleMenu;
    }

    public void setIsModuleMenu(boolean isModuleMenu) {
        this.isModuleMenu = isModuleMenu;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public int getTableName() {
        return tableName;
    }

    public void setTableName(int tableName) {
        this.tableName = tableName;
    }
}
