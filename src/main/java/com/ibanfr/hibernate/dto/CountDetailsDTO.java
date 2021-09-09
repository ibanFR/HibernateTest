package com.ibanfr.hibernate.dto;

public class CountDetailsDTO {

    private Long count;
    private String createdBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountDetailsDTO{" +
                "count=" + count +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
