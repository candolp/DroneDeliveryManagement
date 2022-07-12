package com.candolp.common.generic;


public class EntitySaveResults<T> {
    private String status;
    private T entity;
    private  String error;

    public EntitySaveResults() {
        this.status = "SUCCESS";
        this.error  = null;
        this.setEntity(null);
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getEntity() {
        return this.entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
